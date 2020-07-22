package ru.anikey.core_network_api.data.models

data class TerminalsResponseModel(
    val city: List<City>
)

data class City(
    val terminals: Terminals
)

data class Terminals(
    val terminal: List<Terminal>
)

data class Terminal(
    val id: Int,
    val name: String,
    val address: String,
    val latitude: String,
    val longitude: String,
    val receiveCargo: Boolean,
    val giveoutCargo: Boolean,
    val default: Boolean,
    val mapUrl: String?
)