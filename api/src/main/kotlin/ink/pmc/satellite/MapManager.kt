package ink.pmc.satellite

import org.bukkit.Chunk
import org.bukkit.World
import org.dynmap.DynmapWorld
import org.dynmap.MapType
import org.dynmap.MapType.ImageEncoding
import org.dynmap.utils.BufferInputStream

interface MapManager {

    suspend fun getTile(world: World, typeName: String, chunkX: Int, chunkZ: Int, zoom: Int): Pair<BufferInputStream?, ImageEncoding?>

    suspend fun getTile(chunk: Chunk, typeName: String, zoom: Int): Pair<BufferInputStream?, ImageEncoding?>

    fun getWorld(world: World): DynmapWorld?

    fun getMaps(world: World): List<MapType>

    fun getWorldAlias(world: World): String

    fun getMapAlias(map: MapType): String

}