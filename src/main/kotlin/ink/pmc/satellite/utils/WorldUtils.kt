package ink.pmc.satellite.utils

import ink.pmc.satellite.models.WorldModel
import org.bukkit.World

fun World.model(): WorldModel {
    return WorldModel(name, name, spawnLocation.model)
}