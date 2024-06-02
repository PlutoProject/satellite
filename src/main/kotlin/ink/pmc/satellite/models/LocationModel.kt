package ink.pmc.satellite.models

import kotlinx.serialization.Serializable

@Serializable
data class LocationModel(
    val world: String,
    val x: Double,
    val y: Double,
    val z: Double
)