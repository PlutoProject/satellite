package ink.pmc.satellite.routes

import ink.pmc.common.utils.platform.paper
import ink.pmc.satellite.utils.model
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.playersRoute() {
    get("/players") {
        val players = paper.onlinePlayers.map {
            it.model
        }

        call.respond(players)
    }
}