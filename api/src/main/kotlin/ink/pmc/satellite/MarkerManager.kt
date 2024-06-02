package ink.pmc.satellite

interface MarkerManager {

    val markers: List<Marker>

    fun registerMarker(block: MarkerDsl.() -> Unit): Marker

}