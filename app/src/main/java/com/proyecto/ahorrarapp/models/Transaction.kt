package com.proyecto.ahorrarapp.models

import java.util.*

data class Transaction(
    val id: Int,
    val name: String,
    val amount: Double,
    val category: String,
    val type: TransactionType,
    val date: Date = Date()
)

enum class TransactionType {
    INCOME, EXPENSE
}

object Categories {
    val categories = mapOf(
        "Comida" to "🍕",
        "Transporte" to "🚗",
        "Entretenimiento" to "🎮",
        "Materiales" to "📚",
        "Trabajo" to "💼",
        "Café" to "☕",
        "Libros" to "📖",
        "Universidad" to "🎓",
        "Otros" to "📦"
    )

    fun getEmoji(category: String): String {
        return categories[category] ?: "📦"
    }
}