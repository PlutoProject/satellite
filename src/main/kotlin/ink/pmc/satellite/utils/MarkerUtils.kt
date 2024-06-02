package ink.pmc.satellite.utils

import ink.pmc.satellite.Marker
import ink.pmc.satellite.models.MarkerModel

val Marker.model: MarkerModel
    get() {
        return MarkerModel(
            name,
            creator,
            location.model,
            description
        )
    }