package ink.pmc.satellite.utils

import ink.pmc.common.utils.platform.paper
import ink.pmc.satellite.models.ServerStatusModel
import ink.pmc.satellite.models.WorldModel

fun serverStatusModel(): ServerStatusModel {
    return ServerStatusModel(
        paper.maxPlayers,
        paper.onlinePlayers.size,
        serverWorldsModel()
    )
}

fun serverWorldsModel(): List<WorldModel> {
    return paper.worlds.map { it.model }
}