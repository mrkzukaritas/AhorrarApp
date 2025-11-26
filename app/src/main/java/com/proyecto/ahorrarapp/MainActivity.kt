package com.proyecto.ahorrarapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.proyecto.ahorrarapp.models.Transaction
import com.proyecto.ahorrarapp.screens.*
import com.proyecto.ahorrarapp.components.BottomNavigationBar
import com.proyecto.ahorrarapp.ui.theme.AhorrarAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AhorrarAppTheme {
                AhorrarApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AhorrarApp() {
    val selectedScreen = remember { mutableStateOf(0) }
    val transactions = remember { mutableStateOf(emptyList<Transaction>()) }



    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedItem = selectedScreen.value,
                onItemSelected = { selectedScreen.value = it }
            )
        }
    ) { innerPadding ->
        when (selectedScreen.value) {
            0 -> SummaryScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                transactions = transactions.value,
                onAddTransaction = {
                    selectedScreen.value = 1 // Navegar a agregar transacción
                }
            )
            1 -> AddTransactionScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                onTransactionAdded = { newTransaction ->
                    transactions.value = transactions.value + newTransaction
                    selectedScreen.value = 0 // Volver al resumen
                },
                onCancel = {
                    selectedScreen.value = 0 // Volver al resumen
                }
            )
            2 -> HistoryScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                transactions = transactions.value
            )
            3 -> ProfileScreen(
                profileData = mapOf(
                    "nombre" to "Maria Bermudez",
                    "email" to "maria@gmail.com",
                    "universidad" to "Universidad de Ibague",
                    "estatus" to "Estudiante"
                ),
                transactions =transactions.value// o tu lista real de transacciones
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AhorrarAppTheme {
        AhorrarApp()
    }
}