package com.proyecto.ahorrarapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.proyecto.ahorrarapp.R
import com.proyecto.ahorrarapp.models.Transaction
import com.proyecto.ahorrarapp.models.TransactionType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryScreen(
    modifier: Modifier = Modifier,
    transactions: List<Transaction>,
    onAddTransaction: () -> Unit
) {
    val totalAhorrado = transactions
        .filter { it.type == TransactionType.INCOME }
        .sumOf { it.amount }

    val totalGastos = transactions
        .filter { it.type == TransactionType.EXPENSE }
        .sumOf { it.amount }

    val ingresosMes = totalAhorrado - totalGastos

    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Logo y título
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally)
                        .size(80.dp)

                )
                Text(
                    text = "Tu app de ahorro estudiantil",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }

        item {
            // Estadísticas
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,

            ) {
                Card(
                    modifier = Modifier.weight(1f),

                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E8))

                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),

                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$${"%.0f".format(totalAhorrado)}",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E7D32)
                        )
                        Text(
                            text = "Total ahorrado",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$${"%.0f".format(ingresosMes)}",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1565C0)
                        )
                        Text(
                            text = "Ingresos del mes",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
            }
        }

        item {
            // Tips de ahorro
            Text(
                text = "Tips de ahorro",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            TipCard(
                title = "Libros usados",
                description = "Compra libros de segunda mano o úsalos digitalmente"
            )
            TipCard(
                title = "Café casero",
                description = "Prepara tu café en casa en lugar de comprarlo"
            )
            TipCard(
                title = "Apps de descuentos",
                description = "Usa apps para encontrar ofertas estudiantiles"
            )
        }

        item {
            // Gráfico simple
            Text(
                text = "Tu progreso",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "26% | 17% | 18%",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun TipCard(title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //imagen de los tipos
            Text(
                text = "",
                modifier = Modifier.padding(end = 12.dp)
            )
            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}