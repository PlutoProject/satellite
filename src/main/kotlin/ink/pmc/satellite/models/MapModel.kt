package ink.pmc.satellite.models

import kotlinx.serialization.Serializable

@Serializable
data class MapModel(
    val name: String,
    val alias: String,
    val zoomLevels: Int
)