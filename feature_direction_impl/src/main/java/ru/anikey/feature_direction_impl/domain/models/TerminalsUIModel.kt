package ru.anikey.feature_direction_impl.domain.models

import ru.anikey.core_database_api.data.models.TerminalsDBModel
import ru.anikey.core_network_api.data.models.TerminalsResponseModel

data class TerminalsUIModel(
    val id: Int,
    val name: String,
    val address: String,
    val latitude: String,
    val longitude: String,
    val receiveCargo: Boolean,
    val giveoutCargo: Boolean,
    val isDefault: Boolean,
    val mapUrl: String?
) {
    fun mapToDB(): TerminalsDBModel = TerminalsDBModel(
        id, name, address, latitude, longitude, receiveCargo, giveoutCargo, isDefault, mapUrl
    )
}

fun TerminalsDBModel.mapToUI(): TerminalsUIModel = TerminalsUIModel(
    id, name, address, latitude, longitude, receiveCargo, giveoutCargo, isDefault, mapUrl
)

fun TerminalsResponseModel.mapToUI(): List<TerminalsUIModel> {
    val terminalsUIModel = mutableListOf<TerminalsUIModel>()
    city.forEach { city ->
        city.terminals.terminal.forEach { terminal ->
            terminalsUIModel.add(
                TerminalsUIModel(
                    id = terminal.id,
                    name = terminal.name,
                    address = terminal.address,
                    latitude = terminal.latitude,
                    longitude = terminal.longitude,
                    receiveCargo = terminal.receiveCargo,
                    giveoutCargo = terminal.giveoutCargo,
                    isDefault = terminal.default,
                    mapUrl = terminal.mapUrl
                )
            )
        }
    }
    return terminalsUIModel
}
