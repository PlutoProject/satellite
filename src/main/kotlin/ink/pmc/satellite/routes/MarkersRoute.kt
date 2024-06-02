package ink.pmc.satellite.routes

import ink.pmc.common.utils.platform.paper
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.markersRoute() {
    get("/markers/{world}") {
        val world = call.parameters["world"]

        if (world == null || paper.getWorld(world) == null) {
            call.respond(HttpStatusCode.NotFound)
            return@get
        }
    }
}