package ink.pmc.satellite.utils

import ink.pmc.satellite.Marker
import ink.pmc.satellite.MarkerDsl
import ink.pmc.satellite.MarkerFactory

fun MarkerDsl.toMarker(): Marker {
    return MarkerFactory.of(name, creator, location, description)
}