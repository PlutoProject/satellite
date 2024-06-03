package ink.pmc.satellite.utils

import ink.pmc.satellite.DimType
import ink.pmc.satellite.models.WorldModel
import org.bukkit.World

val World.model: WorldModel
    get() {
        return WorldModel(name, name, spawnLocation.model, dim)
    }

val World.dim: DimType
    get() {
        return when(environment) {
            World.Environment.NORMAL -> DimType.OVERWORLD
            World.Environment.NETHER -> DimType.NETHER
            World.Environment.THE_END -> DimType.THE_END
            else -> DimType.OVERWORLD
        }
    }