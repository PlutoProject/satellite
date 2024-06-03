package ink.pmc.satellite

import org.bukkit.Chunk
import org.bukkit.World
import org.dynmap.utils.BufferInputStream

interface TileManager {

    suspend fun get(world: World, typeName: String, chunkX: Int, chunkZ: Int, zoom: Int): Pair<BufferInputStream?, TileFormat?>

    suspend fun get(chunk: Chunk, typeName: String, zoom: Int): Pair<BufferInputStream?, TileFormat?>

}