package ink.pmc.satellite.utils

import ink.pmc.satellite.avatarCache
import ink.pmc.satellite.models.LocationModel
import ink.pmc.satellite.models.PlayerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.bukkit.Location
import org.bukkit.entity.Player
import java.io.ByteArrayOutputStream
import java.net.URI
import java.util.*
import javax.imageio.ImageIO

private const val DEFAULT_AVATAR =
    "iVBORw0KGgoAAAANSUhEUgAAAAgAAAAICAYAAADED76LAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAD5SURBVChTJY89S8NQFIafe5M0sTWliBgVqQVHB/+AsxRc3R0KLv4EcVZ0c1AoguAkLg516FT8Aw6uCtVqUTuUQImGNB/X5PoeDrych3M4r9houCrJwBSSKE1IkhQpJZZpoBTIAhZKVEbFKVGfK7NUqyDyyvKZWK+76nR/F9sqMeNUCX8mYEj870+O77qI28M9VbZtfqMIw7DxJz6r3gKDt2d9WRZw++CMm4c1nHiq4cW9R+u8Q3V+GXHZ2lLe4gqmXaPT6+mtneYmw/cX+qMxMohTPoZ9Tq7aNGZd3Ufta8ZBmL9pYb5+jciy/yjdp0cMy9Q+CFOieMof8aNXb8sIl7kAAAAASUVORK5CYII="

suspend fun Player.avatar(): String = withContext(Dispatchers.IO) {
    val profile = playerProfile.properties.firstOrNull {
        it.name == "textures"
    } ?: return@withContext DEFAULT_AVATAR

    val value = String(Base64.getDecoder().decode(profile.value))
    val jsonObject = Json.parseToJsonElement(value).jsonObject
    val textures = jsonObject["textures"]?.jsonObject ?: return@withContext DEFAULT_AVATAR
    val skin = textures["SKIN"]?.jsonObject ?: return@withContext DEFAULT_AVATAR
    val url = skin["url"]?.jsonPrimitive?.content ?: return@withContext DEFAULT_AVATAR
    val rawImage = ImageIO.read(URI(url).toURL())
    val avatarImage = rawImage.getSubimage(8, 8, 8, 8)

    // avatarImage 转 Base64
    val outputStream = ByteArrayOutputStream()
    ImageIO.write(avatarImage, "png", outputStream)
    return@withContext Base64.getEncoder().encodeToString(outputStream.toByteArray())
}

val Location.model: LocationModel
    get() {
        return LocationModel(world.name, x, y, z)
    }

val Player.model: PlayerModel
    get() {
        val avatar = avatarCache[uniqueId] ?: DEFAULT_AVATAR
        return PlayerModel(uniqueId.toString(), name, location.model, avatar)
    }