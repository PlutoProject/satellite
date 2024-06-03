package ink.pmc.satellite.utils

import ink.pmc.satellite.DimType
import ink.pmc.satellite.fileConfig
import ink.pmc.satellite.models.WorldModel
import org.bukkit.World

val World.model: WorldModel
    get() {
        return WorldModel(name, alias, spawnLocation.model, dim)
    }

val World.dim: DimType
    get() {
        return when (environment) {
            World.Environment.NORMAL -> DimType.OVERWORLD
            World.Environment.NETHER -> DimType.NETHER
            World.Environment.THE_END -> DimType.THE_END
            else -> DimType.OVERWORLD
        }
    }

val World.alias: String
    get() {
        return fileConfig.get("world-aliases.$name") ?: return name
    }