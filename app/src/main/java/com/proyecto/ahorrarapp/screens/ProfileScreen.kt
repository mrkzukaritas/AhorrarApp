package com.proyecto.ahorrarapp.screens

import androidx.compose.foundation.clickable // ✅ ESTA IMPORTACIÓN
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profileData: Map<String, String>,
    transactions: List<com.proyecto.ahorrarapp.models.Transaction>,
    onProfileUpdated: (Map<String, String>) -> Unit,
    onClearAllData: () -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var currentEditingField by remember { mutableStateOf<String?>(null) }
    var tempProfileData by remember { mutableStateOf(profileData) }
    var tempValue by remember { mutableStateOf("") }
    var showLogoutDialog by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(modifier = modifier) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "AhorrarApp",
                    fontWeight = FontWeight.Bold
                )
            },
            //QUITAR ESTO
            actions = {
                if (!isEditing) {
                    IconButton(onClick = {
                        isEditing = true
                        currentEditingField = "nombre"
                        tempValue = tempProfileData["nombre"] ?: ""
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Editar perfil")
                    }
                }
            }
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(scrollState),
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
                        text = tempProfileData["nombre"] ?: "Tu nombre",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = if (tempProfileData["nombre"].isNullOrEmpty()) Color.Gray else Color.Black
                    )
                    Text(
                        text = tempProfileData["email"] ?: "tu.email@ejemplo.com",
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (tempProfileData["email"].isNullOrEmpty()) Color.Gray else Color.Gray
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

            // Campo de edición actual
            if (isEditing && currentEditingField != null) {
                EditingFieldCard(
                    currentField = currentEditingField ?: "",
                    tempValue = tempValue,
                    onValueChange = { tempValue = it },
                    onCancel = {
                        isEditing = false
                        currentEditingField = null
                    },
                    onSave = {
                        val updatedData = tempProfileData + (currentEditingField!! to tempValue)
                        tempProfileData = updatedData
                        onProfileUpdated(updatedData)
                        isEditing = false
                        currentEditingField = null
                    },
                    isValueValid = tempValue.isNotEmpty()
                )
            }

            // TODOS LOS CAMPOS EN UNA SOLA TARJETA
            if (!isEditing) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Título de la sección
                        Text(
                            text = "Información Personal",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        // Campo Nombre
                        ProfileFieldRow(
                            label = "Nombre completo",
                            value = tempProfileData["nombre"],
                            onClick = {
                                currentEditingField = "nombre"
                                tempValue = tempProfileData["nombre"] ?: ""
                                isEditing = true
                            }
                        )

                        Divider(color = Color.LightGray, thickness = 1.dp)

                        // Campo Email
                        ProfileFieldRow(
                            label = "Email",
                            value = tempProfileData["email"],
                            onClick = {
                                currentEditingField = "email"
                                tempValue = tempProfileData["email"] ?: ""
                                isEditing = true
                            }
                        )

                        Divider(color = Color.LightGray, thickness = 1.dp)

                        // Campo Contraseña
                        ProfileFieldRow(
                            label = "Contraseña",
                            value = "••••••••••••••",
                            isPassword = true,
                            onClick = {
                                // Lógica para cambiar contraseña
                            }
                        )

                        Divider(color = Color.LightGray, thickness = 1.dp)

                        // Campo Universidad
                        ProfileFieldRow(
                            label = "Universidad",
                            value = tempProfileData["universidad"],
                            onClick = {
                                currentEditingField = "universidad"
                                tempValue = tempProfileData["universidad"] ?: ""
                                isEditing = true
                            }
                        )

                        Divider(color = Color.LightGray, thickness = 1.dp)

                        // Campo Estatus académico
                        ProfileFieldRow(
                            label = "Estatus académico",
                            value = tempProfileData["estatus"],
                            onClick = {
                                currentEditingField = "estatus"
                                tempValue = tempProfileData["estatus"] ?: ""
                                isEditing = true
                            }
                        )
                    }
                }
            }

            // Botón cerrar sesión
            Button(
                onClick = { showLogoutDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFE0E0),
                    contentColor = Color(0xFFD32F2F)
                )
            ) {
                Text("Cerrar sesión", fontSize = 16.sp)
            }
        }
    }

    // Diálogo de confirmación para cerrar sesión
    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("¿Cerrar sesión?") },
            text = {
                Text("Se borrarán todos tus datos:\n\n• Perfil\n• Transacciones\n• Historial\n\nEsta acción no se puede deshacer.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        onClearAllData()
                        showLogoutDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD32F2F)
                    )
                ) {
                    Text("Sí, cerrar sesión")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showLogoutDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray
                    )
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

// NUEVO COMPONENTE: Fila de campo de perfil (sin tarjeta individual)
@Composable
fun ProfileFieldRow(
    label: String,
    value: String?,
    isPassword: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }, // ✅ ESTO NECESITA LA IMPORTACIÓN
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                fontSize = 14.sp
            )
            Text(
                text = if (isPassword) "••••••••••••••" else (value ?: "Toca para agregar"),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 4.dp),
                color = if (value.isNullOrEmpty()) Color.Gray else Color.Black
            )
        }

        // Icono de edición
        Text(
            text = "✏️",
            color = Color.Gray
        )
    }
}

@Composable
fun EditingFieldCard(
    currentField: String,
    tempValue: String,
    onValueChange: (String) -> Unit,
    onCancel: () -> Unit,
    onSave: () -> Unit,
    isValueValid: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E8))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Editando: ${getFieldLabel(currentField)}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32)
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = tempValue,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(getFieldLabel(currentField)) },
                placeholder = { Text("Escribe tu ${getFieldLabel(currentField).lowercase()}...") },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onCancel,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray
                    )
                ) {
                    Text("Cancelar")
                }
                Button(
                    onClick = onSave,
                    modifier = Modifier.weight(1f),
                    enabled = isValueValid
                ) {
                    Text("Guardar")
                }
            }
        }
    }
}

private fun getFieldLabel(field: String): String {
    return when (field) {
        "nombre" -> "Nombre completo"
        "email" -> "Email"
        "universidad" -> "Universidad"
        "estatus" -> "Estatus académico"
        else -> field
    }
}