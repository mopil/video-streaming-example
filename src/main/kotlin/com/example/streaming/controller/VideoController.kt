package com.example.streaming.controller

import com.example.streaming.service.VideoService
import com.example.streaming.model.mongo.Video
import org.springframework.core.io.support.ResourceRegion
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
class VideoController(
        private val videoService: VideoService
) {
    @PostMapping("/videos")
    fun uploadVideo(
            @RequestParam("video") videoFile: MultipartFile
    ): Video {
        return videoService.uploadVideo(videoFile)
    }

    @GetMapping("/videos/{videoName}")
    fun getVideo(
            @RequestHeader headers: HttpHeaders,
            @PathVariable videoName: String,
    ): ResponseEntity<ResourceRegion> {
        val videoFile = videoService.getVideo(videoName)
        val contentLength = videoFile.contentLength()
        val rangeHeader = headers.range.stream().findFirst()
        val chunkSize = 1_000_000L
        val resourceRegion = if (rangeHeader.isPresent) {
            val httpRange = rangeHeader.get()
            val start = httpRange.getRangeStart(contentLength)
            val end = httpRange.getRangeEnd(contentLength)
            val rangeLength = minOf(chunkSize, end - start + 1)
            ResourceRegion(videoFile, start, rangeLength)
        } else {
            val rangeLength = minOf(chunkSize, contentLength)
            ResourceRegion(videoFile, 0, rangeLength)
        }
        val mediaType = MediaTypeFactory.getMediaType(videoFile)
                .orElse(MediaType.APPLICATION_OCTET_STREAM)
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(mediaType)
                .body(resourceRegion)
    }
}