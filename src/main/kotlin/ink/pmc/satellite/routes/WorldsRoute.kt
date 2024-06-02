package ink.pmc.satellite.routes

import ink.pmc.satellite.utils.serverWorldsModel
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.worldsRoute() {
    get("/worlds") {
        call.respond(serverWorldsModel())
    }
}