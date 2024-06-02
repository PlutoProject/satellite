package ink.pmc.satellite

object SatelliteAPI : ISatelliteAPI by ISatelliteAPI.instance

interface ISatelliteAPI {

    companion object {
        lateinit var instance: ISatelliteAPI
    }

    val markerManager: MarkerManager

}