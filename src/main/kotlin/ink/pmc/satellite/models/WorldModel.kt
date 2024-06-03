package ink.pmc.satellite.models

import kotlinx.serialization.Serializable

@Serializable
data class WorldModel(
    val name: String,
    val displayName: String,
    val spawnPoint: LocationModel
)