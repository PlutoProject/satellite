package ink.pmc.satellite.routes

import ink.pmc.common.utils.platform.paper
import ink.pmc.satellite.mapManager
import ink.pmc.satellite.utils.model
import ink.pmc.satellite.utils.notExisted
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.mapsRoute() {
    get("/maps/{world}") {
        val world = call.parameters["world"]?.let { it1 -> paper.getWorld(it1) } ?: return@get notExisted()
        call.respond(mapManager.getMaps(world).map { it.model })
    }
}