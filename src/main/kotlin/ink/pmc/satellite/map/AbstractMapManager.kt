package ink.pmc.satellite.map

import ink.pmc.satellite.MapManager
import org.bukkit.Chunk
import org.bukkit.World
import org.dynmap.MapType
import org.dynmap.utils.BufferInputStream

abstract class AbstractMapManager : MapManager {

    override suspend fun getTile(chunk: Chunk, typeName: String, zoom: Int): Pair<BufferInputStream?, MapType.ImageEncoding?> {
        return getTile(chunk.world, typeName, chunk.x, chunk.z, zoom)
    }

    override fun getMaps(world: World): List<MapType> {
        return getWorld(world)?.maps ?: listOf()
    }

}