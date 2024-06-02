package ink.pmc.satellite.marker

import ink.pmc.satellite.Marker
import org.bukkit.Location

data class MarkerImpl(
    override val name: String,
    override val creator: String,
    override val location: Location,
    override val description: String
) : Marker