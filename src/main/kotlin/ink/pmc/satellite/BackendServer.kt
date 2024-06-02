package ink.pmc.satellite

import ink.pmc.satellite.routes.helloWorldRoute
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
            helloWorldRoute()
        }
    }.start()

    override fun close() {
        engine.stop()
    }

}