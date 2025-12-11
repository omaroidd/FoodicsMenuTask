package com.example.foodicsmenutask.presentation.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodicsmenutask.presentation.ui.component.OrderDialog
import com.example.foodicsmenutask.presentation.ui.component.ProductCard
import com.example.foodicsmenutask.presentation.viewmodel.OrderViewModel
import com.example.foodicsmenutask.presentation.viewmodel.ProductsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TablesScreen(
    productsViewModel: ProductsViewModel,
    orderViewModel: OrderViewModel,
    modifier: Modifier = Modifier
) {
    val query by productsViewModel.query.collectAsState()
    val products by productsViewModel.products.collectAsState()
    val categories by productsViewModel.categories.collectAsState()
    val selectedCategoryId by productsViewModel.selectedCategoryId.collectAsState()
    val orderItems by orderViewModel.orderItems.collectAsState()
    val total by orderViewModel.total.collectAsState()

    var showDialog by remember { mutableStateOf(false) }

    val filteredProducts = if (selectedCategoryId != null) {
        products.filter { it.category.id.toString() == selectedCategoryId }
    } else {
        products
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { productsViewModel.setQuery(it) },
                label = { Text("Search products") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(50.dp)
            ) {
                item {
                    FilterChip(
                        selected = selectedCategoryId == null,
                        onClick = { productsViewModel.selectCategory(null) },
                        label = { Text("All") },
                        modifier = Modifier.padding(end = 8.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = Color.White
                        )
                    )
                }
                items(categories) { category ->
                    FilterChip(
                        selected = selectedCategoryId == category.id.toString(),
                        onClick = { productsViewModel.selectCategory(category.id.toString()) },
                        label = { Text(category.name) },
                        modifier = Modifier.padding(end = 8.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                items(filteredProducts) { product ->
                    ProductCard(
                        product = product,
                        onProductClick = { orderViewModel.addProduct(product.id) }
                    )
                }
            }
        }

        Surface(
            onClick = { showDialog = true },
            shadowElevation = 8.dp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Surface(
                        color = Color.White,
                        modifier = Modifier
                            .height(40.dp)
                            .padding(end = 12.dp),
                        shape = RoundedCornerShape(50)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        ) {
                            Text(
                                text = "${orderItems.size}",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }

                    Text(
                        text = "View order",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        text = "SAR ${"%.2f".format(total)}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(end = 8.dp)
                    )

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "View Order",
                        tint = Color.White,
                        modifier = Modifier
                            .height(20.dp)
                            .padding(start = 4.dp)
                    )
                }
            }
        }
    }

    if (showDialog) {
        OrderDialog(
            items = orderItems,
            total = total,
            onConfirm = {
                orderViewModel.clearOrder()
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }
}
