package ink.pmc.satellite.marker

import ink.pmc.satellite.Marker
import ink.pmc.satellite.MarkerManager

abstract class AbstractMarkerManager : MarkerManager {

    override val markers = mutableListOf<Marker>()

}