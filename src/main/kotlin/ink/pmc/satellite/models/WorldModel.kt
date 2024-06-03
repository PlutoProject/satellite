package ink.pmc.satellite.models

import ink.pmc.satellite.DimType
import kotlinx.serialization.Serializable

@Serializable
data class WorldModel(
    val name: String,
    val alias: String,
    val spawnPoint: LocationModel,
    val dimension: DimType
)