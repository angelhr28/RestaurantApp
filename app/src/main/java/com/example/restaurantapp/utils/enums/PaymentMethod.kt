package com.example.restaurantapp.utils.enums

enum class PaymentMethod(val value: String) {
    CASH("Pago en efectivo"),
    CARD("Tarjeta"),
    DEPOSIT("Deposito a cuenta (YAPE - PLIN - transferencia)")
}