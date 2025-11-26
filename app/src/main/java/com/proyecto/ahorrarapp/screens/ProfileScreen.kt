package com.proyecto.ahorrarapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.proyecto.ahorrarapp.R

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profileData: Map<String, String>,
    transactions: List<com.proyecto.ahorrarapp.models.Transaction>
) {
    Column(modifier = modifier) {
        // Logo - MISMAS PROPIEDADES QUE HistoryScreen
        Spacer(modifier = Modifier.height(45.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
                .size(80.dp)
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Información del usuario
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = profileData["nombre"] ?: "Nombre no disponible",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = if (profileData["nombre"].isNullOrEmpty()) Color.Gray else Color.Black
                    )
                    Text(
                        text = profileData["email"] ?: "Email no disponible",
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (profileData["email"].isNullOrEmpty()) Color.Gray else Color.Gray
                    )

                    // Mostrar estadísticas rápidas
                    Spacer(modifier = Modifier.height(8.dp))
                    val totalTransacciones = transactions.size
                    Text(
                        text = "$totalTransacciones transacciones registradas",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }

            // Información Personal (solo lectura)
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Información Personal",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    // Campo Nombre
                    ProfileFieldRow(
                        label = "Nombre completo",
                        value = profileData["nombre"]
                    )

                    Divider(color = Color.LightGray, thickness = 1.dp)

                    // Campo Email
                    ProfileFieldRow(
                        label = "Email",
                        value = profileData["email"]
                    )

                    Divider(color = Color.LightGray, thickness = 1.dp)

                    // Campo Contraseña
                    ProfileFieldRow(
                        label = "Contraseña",
                        value = "••••••••••••••",
                        isPassword = true
                    )

                    Divider(color = Color.LightGray, thickness = 1.dp)

                    // Campo Universidad
                    ProfileFieldRow(
                        label = "Universidad",
                        value = profileData["universidad"]
                    )

                    Divider(color = Color.LightGray, thickness = 1.dp)

                    // Campo Estatus académico
                    ProfileFieldRow(
                        label = "Estatus académico",
                        value = profileData["estatus"]
                    )
                }
            }

            // Mensaje informativo
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Vista de solo lectura",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1976D2)
                    )
                    Text(
                        text = "Esta pantalla es solo para pruebas de visualización",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

// Componente de campo de perfil en solo lectura - MEJORADO
@Composable
fun ProfileFieldRow(
    label: String,
    value: String?,
    isPassword: Boolean = false
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isPassword) "••••••••••••••" else (value ?: "No especificado"),
                style = MaterialTheme.typography.bodyLarge,
                color = if (value.isNullOrEmpty()) Color.Gray else Color.Black,
                fontWeight = FontWeight.Medium
            )
            // Icono de solo lectura
            Text(
                text = "👁️",
                color = Color.LightGray
            )
        }
    }
}