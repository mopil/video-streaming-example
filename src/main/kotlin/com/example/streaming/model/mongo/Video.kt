package com.example.streaming.model.mongo

import com.example.streaming.util.getExtension
import com.example.streaming.util.toGigaByte
import com.example.streaming.util.toKiloByte
import com.example.streaming.util.toMegaByte
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.web.multipart.MultipartFile
import java.util.UUID
import kotlin.math.round

@Document(collection = "video")
open class Video(
        val originalFileName: String,
        val serverSavedFilename: String,
        val byteSize: Long,
        val kiloByteSize: Double,
        val megaByteSize: Double,
        val gigaByteSize: Double,
) : MongoBaseTimeEntity() {
    @Id
    var id: String? = null
        private set



    companion object {
        fun ofMultipart(videoFile: MultipartFile): Video {
            val serverSavedFilename = "${UUID.randomUUID()}.${videoFile.getExtension()}"
            return Video(
                    originalFileName = videoFile.originalFilename!!,
                    serverSavedFilename = serverSavedFilename,
                    byteSize = videoFile.size,
                    kiloByteSize = videoFile.size.toKiloByte(),
                    megaByteSize = videoFile.size.toMegaByte(),
                    gigaByteSize = videoFile.size.toGigaByte()
            )
        }

    }
}