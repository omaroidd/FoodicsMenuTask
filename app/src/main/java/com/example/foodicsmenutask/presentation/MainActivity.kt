package com.example.foodicsmenutask.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodicsmenutask.presentation.ui.screen.MenuScreen
import com.example.foodicsmenutask.presentation.ui.screen.OrderScreen
import com.example.foodicsmenutask.presentation.ui.screen.SettingsScreen
import com.example.foodicsmenutask.presentation.ui.screen.TablesScreen
import com.example.foodicsmenutask.presentation.viewmodel.ProductsViewModel
import com.example.foodicsmenutask.presentation.ui.theme.MenuAppTheme
import com.example.foodicsmenutask.presentation.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MenuAppTheme {
                var selectedTab by remember { mutableIntStateOf(0) }
                val productsViewModel: ProductsViewModel = koinViewModel()
                val orderViewModel: OrderViewModel = koinViewModel()


                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    bottomBar = {
                        NavigationBar(
                            containerColor = Color.White,
                            tonalElevation = 0.dp
                        ) {
                            val navItems = listOf(
                                Triple(Icons.Outlined.FavoriteBorder, "Tables", 0),
                                Triple(Icons.Default.ShoppingCart, "Orders", 1),
                                Triple(Icons.Default.Menu, "Menu", 2),
                                Triple(Icons.Default.Settings, "Settings", 3)
                            )
                            navItems.forEach { (icon, label, index) ->
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            icon,
                                            contentDescription = label,
                                            modifier = Modifier.padding(4.dp)
                                        )
                                    },
                                    label = {
                                        Text(
                                            label,
                                            fontSize = 11.sp
                                        )
                                    },
                                    selected = selectedTab == index,
                                    onClick = { selectedTab = index },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.primary,
                                        selectedTextColor = MaterialTheme.colorScheme.primary,
                                        indicatorColor = Color.White,
                                        unselectedIconColor = Color.Gray
                                    )
                                )
                            }
                        }
                    }
                ) { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(paddingValues)
                    ) {
                        when (selectedTab) {
                            0 -> TablesScreen(
                                productsViewModel = productsViewModel,
                                orderViewModel = orderViewModel,
                                modifier = Modifier.fillMaxSize()
                            )

                            1 -> OrderScreen(modifier = Modifier.fillMaxSize())
                            2 -> MenuScreen(modifier = Modifier.fillMaxSize())
                            3 -> SettingsScreen(modifier = Modifier.fillMaxSize())
                        }
                    }
                }
            }
        }
    }
}
