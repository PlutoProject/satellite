package ink.pmc.satellite

object SatelliteApi : ISatelliteApi by ISatelliteApi.instance

interface ISatelliteApi {

    companion object {
        lateinit var instance: ISatelliteApi
    }

    val markerManager: MarkerManager
    val mapManager: MapManager

}