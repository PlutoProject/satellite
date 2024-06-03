package ink.pmc.satellite

import com.electronwill.nightconfig.core.file.FileConfig
import com.github.shynixn.mccoroutine.bukkit.SuspendingJavaPlugin
import com.github.shynixn.mccoroutine.bukkit.registerSuspendingEvents
import ink.pmc.common.utils.platform.paper
import ink.pmc.satellite.marker.MarkerManagerImpl
import java.io.File
import java.util.*
import java.util.logging.Logger

lateinit var backendServer: BackendServer
lateinit var configFile: File
lateinit var fileConfig: FileConfig
lateinit var serverLogger: Logger
lateinit var satelliteApi: ISatelliteApi
lateinit var markerManager: MarkerManager
lateinit var tileManager: TileManager
val avatarCache = mutableMapOf<UUID, String>()

@Suppress("UNUSED")
class Satellite : SuspendingJavaPlugin() {

    override suspend fun onEnableAsync() {
        serverLogger = logger
        initConfig()

        val port = fileConfig.get<Int>("backend-port")
        backendServer = BackendServer(port)

        logger.info("RESTful API server started at $port")

        markerManager = MarkerManagerImpl()
        tileManager = TileManagerImpl()
        satelliteApi = SatelliteApiImpl(markerManager, tileManager)
        ISatelliteApi.instance = satelliteApi

        paper.pluginManager.registerSuspendingEvents(PlayerListener, this)
    }

    override suspend fun onDisableAsync() {
        backendServer.close()
        logger.info("Stopped API server")
    }

    private fun initConfig() {
        configFile = File(dataFolder, "config.toml")

        if (!configFile.exists()) {
            saveResource("config.toml", false)
        }

        fileConfig = FileConfig.builder(configFile)
            .autosave()
            .autoreload()
            .sync()
            .build()
        fileConfig.load()
    }

}
