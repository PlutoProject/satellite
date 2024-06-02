package ink.pmc.satellite.utils

import ink.pmc.satellite.models.WorldModel
import org.bukkit.World

val World.model: WorldModel
    get() {
        return WorldModel(name, name, spawnLocation.model)
    }