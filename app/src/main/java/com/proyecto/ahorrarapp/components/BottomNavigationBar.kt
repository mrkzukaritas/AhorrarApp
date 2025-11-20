 package com.proyecto.ahorrarapp.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.proyecto.ahorrarapp.R

@Composable
fun BottomNavigationBar(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf(
        "Resumen" to R.drawable.ic_summary,
        "Agregar" to R.drawable.ic_add,
        "Historial" to R.drawable.ic_history,
        "Perfil" to R.drawable.ic_profile
    )

    NavigationBar {
        items.forEachIndexed { index, (title, iconRes) ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = iconRes),
                        contentDescription = title
                    )
                },
                label = { Text(title) },
                selected = selectedItem == index,
                onClick = { onItemSelected(index) },

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    indicatorColor = Color(0xA4BEDFD6)   // Verde
                )
            )
        }
    }
}