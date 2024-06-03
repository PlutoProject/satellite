package ink.pmc.satellite.models

import kotlinx.serialization.Serializable
import org.bukkit.GameMode

@Serializable
data class PlayerModel(
    val uuid: String,
    val name: String,
    val location: LocationModel,
    val gameMode: GameMode,
    val avatar: String
)