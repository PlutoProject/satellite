package ink.pmc.satellite.routes

import ink.pmc.satellite.utils.serverStatusModel
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.serverStatusRoute() {
    get("/status") {
        call.respond(serverStatusModel())
    }
}