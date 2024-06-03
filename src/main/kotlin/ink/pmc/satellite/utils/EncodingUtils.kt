package ink.pmc.satellite.utils

import ink.pmc.satellite.TileFormat
import io.ktor.http.*
import org.dynmap.MapType.ImageEncoding

fun ImageEncoding.toTileFormat(): TileFormat {
    return when (this) {
        ImageEncoding.PNG -> TileFormat.PNG
        ImageEncoding.JPG -> TileFormat.JPG
        ImageEncoding.WEBP -> TileFormat.WEBP
    }
}

fun TileFormat.toContentType(): ContentType {
    return when (this) {
        TileFormat.PNG -> ContentType.Image.PNG
        TileFormat.JPG -> ContentType.Image.PNG
        TileFormat.WEBP -> ContentType.parse("image/webp")
    }
}