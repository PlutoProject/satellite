package ink.pmc.satellite.marker

import ink.pmc.satellite.IMarkerFactory
import ink.pmc.satellite.Marker
import org.bukkit.Location

class MarkerFactoryImpl : IMarkerFactory {

    override fun of(name: String, creator: String, location: Location, description: String): Marker {
        return MarkerImpl(name, creator, location, description)
    }

}