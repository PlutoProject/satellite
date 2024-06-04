package ink.pmc.satellite

import com.electronwill.nightconfig.core.file.FileConfig
import com.github.shynixn.mccoroutine.bukkit.SuspendingJavaPlugin
import com.github.shynixn.mccoroutine.bukkit.registerSuspendingEvents
import ink.pmc.common.utils.platform.paper
import ink.pmc.satellite.marker.MarkerManagerImpl
import ink.pmc.satellite.map.MapManagerImpl
import ink.pmc.satellite.marker.MarkerFactoryImpl
import java.io.File
import java.util.*
import java.util.logging.Logger

lateinit var backendServer: BackendServer
lateinit var configFile: File
lateinit var fileConfig: FileConfig
lateinit var serverLogger: Logger
lateinit var satelliteApi: ISatelliteApi
lateinit var markerManager: MarkerManager
lateinit var markerFactory: IMarkerFactory
lateinit var mapManager: MapManager
val avatarCache = mutableMapOf<UUID, String>()

const val CONFIG_NAME = "config.conf"

@Suppress("UNUSED")
class Satellite : SuspendingJavaPlugin() {

    override suspend fun onEnableAsync() {
        serverLogger = logger
        initConfig()

        val port = fileConfig.get<Int>("backend-port")
        backendServer = BackendServer(port)

        logger.info("RESTful API server started at $port")

        markerManager = MarkerManagerImpl()
        markerFactory = MarkerFactoryImpl()
        mapManager = MapManagerImpl()
        satelliteApi = SatelliteApiImpl(markerManager, mapManager)
        IMarkerFactory.instance = markerFactory
        ISatelliteApi.instance = satelliteApi

        paper.pluginManager.registerSuspendingEvents(PlayerListener, this)
    }

    override suspend fun onDisableAsync() {
        backendServer.close()
        logger.info("Stopped API server")
    }

    private fun initConfig() {
        configFile = File(dataFolder, CONFIG_NAME)

        if (!configFile.exists()) {
            saveResource(CONFIG_NAME, false)
        }

        fileConfig = FileConfig.builder(configFile)
            .autosave()
            .autoreload()
            .sync()
            .build()
        fileConfig.load()
    }

}
