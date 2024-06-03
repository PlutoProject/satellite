package ink.pmc.satellite.utils

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*

suspend fun PipelineContext<Unit, ApplicationCall>.notExisted() {
    call.respond(HttpStatusCode.NotFound)
    return
}