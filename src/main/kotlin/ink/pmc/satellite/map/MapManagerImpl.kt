package ink.pmc.satellite.map

import ink.pmc.common.utils.platform.paper
import ink.pmc.satellite.fileConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bukkit.World
import org.dynmap.DynmapCore
import org.dynmap.DynmapWorld
import org.dynmap.MapType
import org.dynmap.bukkit.DynmapPlugin
import org.dynmap.utils.BufferInputStream

class MapManagerImpl : AbstractMapManager() {

    companion object {
        private val dynmapClass = Class.forName("org.dynmap.bukkit.DynmapPlugin")
        private val dynmap = paper.pluginManager.getPlugin("dynmap") as DynmapPlugin
        private val coreField = dynmapClass.getDeclaredField("core").apply { isAccessible = true }
        private val core = coreField.get(dynmap) as DynmapCore
    }

    private val mapManager = core.mapManager

    override suspend fun getTile(
        world: World,
        typeName: String,
        chunkX: Int,
        chunkZ: Int,
        zoom: Int
    ): Pair<BufferInputStream?, MapType.ImageEncoding?> {
        return withContext(Dispatchers.IO) {
            val dynmapWorld = world.dynmap ?: return@withContext emptyPair()
            val mapStorage = dynmapWorld.mapStorage
            val map = dynmapWorld.maps.firstOrNull { it.name == typeName } ?: return@withContext emptyPair()
            val tile = mapStorage.getTile(dynmapWorld, map, chunkX, chunkZ, zoom, MapType.ImageVariant.STANDARD)
            val read = tile.read() ?: return@withContext emptyPair()
            Pair(read.image, read.format)
        }
    }

    override fun getWorld(world: World): DynmapWorld? {
        return world.dynmap
    }

    override fun getWorldAlias(world: World): String {
        return fileConfig.get("world-aliases.${world.name}")
    }

    override fun getMapAlias(map: MapType): String {
        return fileConfig.get("map-aliases.${map.name}")
    }

    private fun emptyPair() = Pair(null, null)

    private val World.dynmap: DynmapWorld?
        get() {
            return mapManager.getWorld(name)
        }

}