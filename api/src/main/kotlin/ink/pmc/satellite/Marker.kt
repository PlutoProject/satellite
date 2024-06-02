package ink.pmc.satellite

import org.bukkit.Location

interface Marker {

    val name: String
    val creator: String
    val location: Location
    val description: String

}