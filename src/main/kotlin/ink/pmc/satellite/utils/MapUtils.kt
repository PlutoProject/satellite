package ink.pmc.satellite.utils

import ink.pmc.satellite.mapManager
import ink.pmc.satellite.models.MapModel
import org.dynmap.MapType

val MapType.model: MapModel
    get() {
        return MapModel(name, mapManager.getMapAlias(this), mapZoomOutLevels)
    }