package ink.pmc.satellite.models

import kotlinx.serialization.Serializable

@Serializable
data class ServerStatusModel(
    val maxPlayerCount: Int,
    val onlinePlayers: Int,
    val worlds: List<WorldModel>
)