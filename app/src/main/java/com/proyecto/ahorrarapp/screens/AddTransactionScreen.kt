package com.proyecto.ahorrarapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.proyecto.ahorrarapp.models.Categories
import com.proyecto.ahorrarapp.models.Transaction
import com.proyecto.ahorrarapp.models.TransactionType
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    modifier: Modifier = Modifier,
    onTransactionAdded: (Transaction) -> Unit,
    onCancel: () -> Unit
) {
    var transactionType by remember { mutableStateOf(TransactionType.EXPENSE) }
    var amount by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Comida") }

    val scrollState = rememberScrollState()

    Column(modifier = modifier) {
        // Header
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "AhorrarApp",
                    fontWeight = FontWeight.Bold
                )
            },
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Agregar transacción",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            // Selector de tipo
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TransactionTypeChip(
                    type = TransactionType.INCOME,
                    isSelected = transactionType == TransactionType.INCOME,
                    onClick = { transactionType = TransactionType.INCOME }
                )
                TransactionTypeChip(
                    type = TransactionType.EXPENSE,
                    isSelected = transactionType == TransactionType.EXPENSE,
                    onClick = { transactionType = TransactionType.EXPENSE }
                )
            }

            // Monto
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Monto") },
                placeholder = { Text("0.00") },
                singleLine = true
            )

            // Nombre
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Nombre") },
                placeholder = { Text("Nombre de la transacción") },
                singleLine = true
            )

            // Categoría
            Text(
                text = "Categoría",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(Categories.categories.keys.toList().size) { index ->
                    val category = Categories.categories.keys.toList()[index]
                    CategoryChip(
                        category = category,
                        isSelected = selectedCategory == category,
                        onClick = { selectedCategory = category }
                    )
                }
            }

            // Botón agregar
            Button(
                onClick = {
                    if (amount.isNotEmpty() && name.isNotEmpty()) {
                        val newTransaction = Transaction(
                            id = (0..1000).random(),
                            name = name,
                            amount = amount.toDoubleOrNull() ?: 0.0,
                            category = selectedCategory,
                            type = transactionType,
                            date = Date()
                        )
                        onTransactionAdded(newTransaction)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = amount.isNotEmpty() && name.isNotEmpty()
            ) {
                Text("Agregar", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun TransactionTypeChip(
    type: TransactionType,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val (text, color) = when (type) {
        TransactionType.INCOME -> "+ Ingreso" to Color(0xFF2E7D32)
        TransactionType.EXPENSE -> "- Gasto" to Color(0xFFC62828)
    }

    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = { Text(text) },
        colors = FilterChipDefaults.filterChipColors(
            selectedLabelColor = Color.White,
            selectedContainerColor = color
        )
    )
}

@Composable
fun CategoryChip(
    category: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val emoji = Categories.getEmoji(category)

    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = {
            Text("$emoji $category")
        }
    )
}