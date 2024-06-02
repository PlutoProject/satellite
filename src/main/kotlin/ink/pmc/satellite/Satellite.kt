package ink.pmc.satellite

import com.electronwill.nightconfig.core.file.FileConfig
import com.github.shynixn.mccoroutine.bukkit.SuspendingJavaPlugin
import java.io.File
import java.util.logging.Logger

lateinit var backendServer: BackendServer
lateinit var configFile: File
lateinit var fileConfig: FileConfig
lateinit var serverLogger: Logger

@Suppress("UNUSED")
class Satellite : SuspendingJavaPlugin() {

    override suspend fun onEnableAsync() {
        serverLogger = logger
        initConfig()

        val port = fileConfig.get<Int>("backend-port")
        backendServer = BackendServer(port)

        logger.info("RESTful API server started at $port")
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

        fileConfig = FileConfig.of(configFile)
        fileConfig.load()
    }

}
