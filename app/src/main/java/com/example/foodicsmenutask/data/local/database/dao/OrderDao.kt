package com.example.foodicsmenutask.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.foodicsmenutask.data.local.database.entity.OrderItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: OrderItemEntity)

    @Update
    suspend fun update(item: OrderItemEntity)

    @Query("SELECT * FROM order_items")
    fun getAll(): Flow<List<OrderItemEntity>>

    @Query("SELECT * FROM order_items WHERE productId = :productId")
    suspend fun getByProductId(productId: Int): OrderItemEntity?

    @Query("DELETE FROM order_items")
    suspend fun clearAll()
}