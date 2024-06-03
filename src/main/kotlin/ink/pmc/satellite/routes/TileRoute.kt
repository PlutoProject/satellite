package ink.pmc.satellite.routes

import ink.pmc.common.utils.platform.paper
import ink.pmc.satellite.mapManager
import ink.pmc.satellite.utils.notExisted
import ink.pmc.satellite.utils.toContentType
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.tileRoute() {
    get("/tile/{world}/{map}/{x}/{z}/{zoom}") {
        val params = call.parameters
        val worldName = params["world"] ?: return@get notExisted()
        val map = params["map"] ?: return@get notExisted()
        val x = params["x"]?.toIntOrNull() ?: return@get notExisted()
        val z = params["z"]?.toIntOrNull() ?: return@get notExisted()
        val zoom = params["zoom"]?.toIntOrNull() ?: return@get notExisted()

        val world = paper.getWorld(worldName) ?: return@get notExisted()
        val file = mapManager.getTile(world, map, x, z, zoom)

        val input = file.first ?: return@get notExisted()
        val format = file.second ?: return@get notExisted()

        call.respondOutputStream(format.toContentType(), HttpStatusCode.OK) {
            input.copyTo(this).also { input.close() }
        }
    }
}