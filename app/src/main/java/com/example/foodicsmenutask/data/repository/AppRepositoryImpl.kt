package com.example.foodicsmenutask.data.repository


import android.util.Log
import com.example.foodicsmenutask.data.local.database.dao.CategoryDao
import com.example.foodicsmenutask.data.local.database.dao.OrderDao
import com.example.foodicsmenutask.data.local.database.dao.ProductDao
import com.example.foodicsmenutask.data.local.database.entity.CategoryEntity
import com.example.foodicsmenutask.data.local.database.entity.OrderItemEntity
import com.example.foodicsmenutask.data.local.database.entity.ProductEntity
import com.example.foodicsmenutask.data.remote.RemoteDataSource
import com.example.foodicsmenutask.domain.model.Category
import com.example.foodicsmenutask.domain.model.OrderItem
import com.example.foodicsmenutask.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val categoryDao: CategoryDao,
    private val productDao: ProductDao,
    private val orderDao: OrderDao
) : AppRepository {

    override fun getCategories(): Flow<List<Category>> {
        return categoryDao.getAll().map { entities ->
            entities.map { it.toCategory() }
        }
    }

    override fun getProducts(): Flow<List<Product>> {
        return productDao.getAll().map { entities ->
            entities.map { it.toProduct() }
        }
    }

    override fun searchProducts(query: String): Flow<List<Product>> {
        return productDao.searchProducts(query).map { entities ->
            entities.map { it.toProduct() }
        }
    }

    override suspend fun refreshCategories() {
        try {
            val dtos = remoteDataSource.fetchCategories()
            withContext(Dispatchers.IO) {
                categoryDao.insertAll(dtos.map {
                    CategoryEntity(id = it.id, name = it.name)
                })
            }
        } catch (e: Exception) {
            Log.d("Repo", "Not Fetched categories from API")
            e.printStackTrace()
        }
    }

    override suspend fun refreshProducts() {
        try {
            val dtos = remoteDataSource.fetchProducts()
            Log.d("Repo", "Fetched ${dtos.size} products from API")
            productDao.insertAll(dtos.map {
                ProductEntity(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    image = it.image,
                    price = it.price,
                    categoryId = it.category.id,
                    categoryName = it.category.name
                )
            })
        } catch (e: Exception) {
            Log.d("Repo", "Not Fetched products from API")
            e.printStackTrace()
        }
    }

    override suspend fun addToOrder(productId: Int) {
        val product = productDao.getProductById(productId) ?: return

        val existing = orderDao.getByProductId(productId)

        if (existing != null) {
            val updatedItem = existing.copy(quantity = existing.quantity + 1)
            orderDao.update(updatedItem)

        } else {
            // Insert new item
            val newItem = OrderItemEntity(
                productId = product.id,
                productName = product.name,
                price = product.price,
                quantity = 1,
                image = product.image
            )
            orderDao.insert(newItem)
        }
    }


    override suspend fun clearOrder() {
        orderDao.clearAll()
    }

    override fun getOrderItems(): Flow<List<OrderItem>> {
        return orderDao.getAll().map { entities ->
            entities.map {
                OrderItem(
                    it.productId,
                    it.productName,
                    it.price,
                    it.quantity,
                    it.image
                )
            }
        }
    }

    private fun CategoryEntity.toCategory() = Category(id, name)

    private fun ProductEntity.toProduct() = Product(
        id, name, description, image, price,
        Category(categoryId, categoryName)
    )
}