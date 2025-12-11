package com.example.foodicsmenutask.presentation.ui.component


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foodicsmenutask.domain.model.OrderItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDialog(
    items: List<OrderItem>,
    total: Double,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
        containerColor = Color.Transparent,
        tonalElevation = 0.dp
    ) {
        Surface(
            color = Color.White,
            shape = MaterialTheme.shapes.large,
            shadowElevation = 8.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // X Icon في الأعلى لإغلاق الشيت
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = {
                            onDismiss()
                            scope.launch { sheetState.hide() }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                }

                Text(
                    text = "Order Summary",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyColumn(
                    modifier = Modifier.heightIn(max = 300.dp)
                ) {
                    items(items) { item ->
                        Text(
                            text = "${item.productName} x${item.quantity} - ${item.price * item.quantity}",
                            color = Color.Black,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Total: SAR ${"%.2f".format(total)}",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // زر Clear Orders بعرض كامل
                Button(
                    onClick = {
                        onConfirm()
                        scope.launch { sheetState.hide() }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Clear Orders")
                }
            }
        }
    }
}
