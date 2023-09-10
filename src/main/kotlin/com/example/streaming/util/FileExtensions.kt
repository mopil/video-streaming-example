package com.example.streaming.util

import org.springframework.web.multipart.MultipartFile

fun MultipartFile.getExtension(): String? {
    return this.originalFilename?.substringAfterLast(".")
}