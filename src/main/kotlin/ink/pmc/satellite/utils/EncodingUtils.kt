package ink.pmc.satellite.utils

import io.ktor.http.*
import org.dynmap.MapType.ImageEncoding

fun ImageEncoding.toContentType(): ContentType {
    return when (this) {
        ImageEncoding.PNG -> ContentType.Image.PNG
        ImageEncoding.JPG -> ContentType.Image.PNG
        ImageEncoding.WEBP -> ContentType.parse("image/webp")
    }
}