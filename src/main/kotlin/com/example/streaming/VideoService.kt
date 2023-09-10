package com.example.streaming

import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.core.io.Resource

@Service
class VideoService {

    val videoPath = "C:\\dev_main\\Videos\\"
    
    fun getVideo(videoName: String): Resource {
        val path = "file:$videoPath$videoName.mp4"
        return UrlResource(path)
    }
}