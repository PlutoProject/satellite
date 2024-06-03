package ink.pmc.satellite.models

import kotlinx.serialization.Serializable

@Serializable
data class MarkerModel(
    val name: String,
    val creator: String,
    val location: LocationModel,
    val description: String
)