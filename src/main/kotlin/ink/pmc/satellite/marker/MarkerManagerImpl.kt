package ink.pmc.satellite.marker

import ink.pmc.satellite.Marker
import ink.pmc.satellite.MarkerDsl
import ink.pmc.satellite.utils.toMarker

class MarkerManagerImpl : AbstractMarkerManager() {

    override fun registerMarker(block: MarkerDsl.() -> Unit): Marker {
        val dsl = MarkerDsl()
        val marker = dsl.toMarker()
        markers.add(marker)
        return marker
    }

}