package com.example.streaming.service

import com.example.streaming.model.mongo.Video
import com.example.streaming.model.mongo.VideoRepository
import com.example.streaming.util.getExtension
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.core.io.Resource
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.lang.RuntimeException

@Service
class VideoService(
        private val videoRepository: VideoRepository
) {

    val videoPath = "C:\\dev_main\\Videos\\"

    fun getVideo(videoName: String): Resource {
        val path = "file:$videoPath$videoName.mp4"
        return UrlResource(path)
    }

    @Transactional
    fun uploadVideo(videoFile: MultipartFile): Video {
        val videoDocument = videoRepository.save(
                Video.ofMultipart(videoFile)
        )
        try {
            videoFile.transferTo(
                    File("$videoPath${videoDocument.serverSavedFilename}.${videoFile.getExtension()}")
            )
        } catch (e: Exception) {
            throw RuntimeException("파일 저장에 실패했습니다.")
        }
        return videoDocument
    }
}