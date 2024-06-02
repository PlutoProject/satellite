package ink.pmc.satellite.models

data class ServerStatusModel(
    val maxPlayerCount: Int,
    val onlinePlayers: Int,
    val worlds: List<WorldModel>
)