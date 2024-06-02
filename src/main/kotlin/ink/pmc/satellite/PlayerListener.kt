package ink.pmc.satellite

import ink.pmc.satellite.utils.avatar
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

@Suppress("UNUSED")
object PlayerListener : Listener {

    @EventHandler
    suspend fun playerJoinEvent(event: PlayerJoinEvent) {
        val player = event.player
        avatarCache[player.uniqueId] = player.avatar()
    }

    @EventHandler
    fun playerQuitEvent(event: PlayerQuitEvent) {
        avatarCache.remove(event.player.uniqueId)
    }

}