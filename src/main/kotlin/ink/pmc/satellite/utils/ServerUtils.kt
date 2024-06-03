package ink.pmc.satellite.utils

import ink.pmc.common.utils.platform.currentMSPT
import ink.pmc.common.utils.platform.paper
import ink.pmc.common.utils.platform.tpsLast1Minute
import ink.pmc.satellite.models.MemoryInfoModel
import ink.pmc.satellite.models.ServerStatusModel
import ink.pmc.satellite.models.WorldModel

private val runtime = Runtime.getRuntime()

fun serverStatusModel(): ServerStatusModel {
    return ServerStatusModel(
        paper.maxPlayers,
        paper.onlinePlayers.size,
        tpsLast1Minute,
        currentMSPT,
        currentMemoryInfo()
    )
}

fun serverWorldsModel(): List<WorldModel> {
    return paper.worlds.map {
        it.model
    }
}

fun currentMemoryInfo(): MemoryInfoModel {
    val total = runtime.totalMemory()
    val free = runtime.freeMemory()
    val used = total - free
    return MemoryInfoModel(total, free, used)
}