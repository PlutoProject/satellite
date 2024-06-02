package ink.pmc.satellite.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.helloWorldRoute() {
    get("/") {
        call.respondText("Hello World!")
    }
}