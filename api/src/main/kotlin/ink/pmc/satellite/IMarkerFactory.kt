package ink.pmc.satellite

import org.bukkit.Location

object MarkerFactory : IMarkerFactory by IMarkerFactory.instance

interface IMarkerFactory {

    companion object {
        lateinit var instance: IMarkerFactory
    }

    fun of(name: String, creator: String, location: Location, description: String): Marker

}