package ru.anikey.core_database_api.data.models

data class TerminalsDBModel(
    val name: String,
    val address: String,
    val latitude: Float,
    val longitude: Float,
    val receiveCargo: Boolean,
    val giveoutCargo: Boolean,
    val isDefault: Boolean,
    val mapUrl: String
)
