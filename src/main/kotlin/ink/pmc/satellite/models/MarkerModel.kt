package ink.pmc.satellite.models

data class MarkerModel(
    val name: String,
    val creator: String,
    val location: LocationModel,
    val desc: String
)