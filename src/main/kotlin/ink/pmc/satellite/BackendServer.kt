package ink.pmc.satellite

import ink.pmc.satellite.routes.playersRoute
import ink.pmc.satellite.routes.serverStatusRoute
import ink.pmc.satellite.routes.tileRoute
import ink.pmc.satellite.routes.worldsRoute
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import java.io.Closeable

class BackendServer(port: Int) : Closeable {

    private val engine = embeddedServer(Netty, port = port) {
        install(Routing)
        install(ContentNegotiation) {
            json()
        }

        routing {
            serverStatusRoute()
            playersRoute()
            tileRoute()
            worldsRoute()
        }
    }.start()

    override fun close() {
        engine.stop()
    }

}