package com.proyecto.ahorrarapp.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
                onClick = { onItemSelected(index) }
            )
        }
    }
}