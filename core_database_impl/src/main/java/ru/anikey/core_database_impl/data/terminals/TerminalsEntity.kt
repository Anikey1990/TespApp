package ru.anikey.core_database_impl.data.terminals

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.anikey.core_database_api.data.models.TerminalsDBModel

@Entity
data class TerminalsEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val address: String,
    val latitude: String,
    val longitude: String,
    val receiveCargo: Boolean,
    val giveoutCargo: Boolean,
    val isDefault: Boolean,
    val mapUrl: String?
)

fun TerminalsDBModel.mapToEntity(): TerminalsEntity = TerminalsEntity(
    id, name, address, latitude, longitude, receiveCargo, giveoutCargo, isDefault, mapUrl
)

fun TerminalsEntity.mapToDBModel(): TerminalsDBModel = TerminalsDBModel(
    id, name, address, latitude, longitude, receiveCargo, giveoutCargo, isDefault, mapUrl
)
