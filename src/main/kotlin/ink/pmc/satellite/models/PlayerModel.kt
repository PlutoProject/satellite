package ink.pmc.satellite.models

import kotlinx.serialization.Serializable

@Serializable
data class PlayerModel(
    val uuid: String,
    val name: String,
    val location: LocationModel,
    val avatar: String
)