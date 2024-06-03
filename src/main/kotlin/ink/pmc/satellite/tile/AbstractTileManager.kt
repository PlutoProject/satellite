package ink.pmc.satellite.tile

import ink.pmc.satellite.TileFormat
import ink.pmc.satellite.TileManager
import org.bukkit.Chunk
import org.dynmap.utils.BufferInputStream

abstract class AbstractTileManager : TileManager {

    override suspend fun get(chunk: Chunk, typeName: String, zoom: Int): Pair<BufferInputStream?, TileFormat?> {
        return get(chunk.world, typeName, chunk.x, chunk.z, zoom)
    }

}