package ink.pmc.satellite.tile

import ink.pmc.common.utils.platform.paper
import ink.pmc.satellite.TileFormat
import ink.pmc.satellite.utils.toTileFormat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bukkit.World
import org.dynmap.DynmapCore
import org.dynmap.DynmapWorld
import org.dynmap.MapType
import org.dynmap.bukkit.DynmapPlugin
import org.dynmap.utils.BufferInputStream

class TileManagerImpl : AbstractTileManager() {

    companion object {
        private val dynmapClass = Class.forName("org.dynmap.bukkit.DynmapPlugin")
        private val dynmap = paper.pluginManager.getPlugin("dynmap") as DynmapPlugin
        private val coreField = dynmapClass.getDeclaredField("core").apply { isAccessible = true }
        private val core = coreField.get(dynmap) as DynmapCore
    }

    private val mapManager = core.mapManager

    override suspend fun get(
        world: World,
        typeName: String,
        chunkX: Int,
        chunkZ: Int,
        zoom: Int
    ): Pair<BufferInputStream?, TileFormat?> {
        return withContext(Dispatchers.IO) {
            val dynmapWorld = world.dynmap ?: return@withContext emptyPair()
            val mapStorage = dynmapWorld.mapStorage
            val map = dynmapWorld.maps.firstOrNull { it.name == typeName } ?: return@withContext emptyPair()
            val tile = mapStorage.getTile(dynmapWorld, map, chunkX, chunkZ, zoom, MapType.ImageVariant.STANDARD)
            val read = tile.read() ?: return@withContext emptyPair()
            Pair(read.image, read.format.toTileFormat())
        }
    }

    private fun emptyPair() = Pair(null, null)

    private val World.dynmap: DynmapWorld?
        get() {
            return mapManager.getWorld(name)
        }

}