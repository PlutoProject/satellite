package ink.pmc.satellite.utils

import ink.pmc.satellite.models.LocationModel
import org.bukkit.Location

val Location.model: LocationModel
    get() {
        return LocationModel(world.name, x, y, z)
    }