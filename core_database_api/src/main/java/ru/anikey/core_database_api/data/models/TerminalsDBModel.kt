package ru.anikey.core_database_api.data.models

data class TerminalsDBModel(
    val name: String,
    val address: String,
    val latitude: String,
    val longitude: String,
    val receiveCargo: Boolean,
    val giveoutCargo: Boolean,
    val isDefault: Boolean,
    val mapUrl: String?
)
