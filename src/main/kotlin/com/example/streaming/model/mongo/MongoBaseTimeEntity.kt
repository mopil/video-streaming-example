package com.example.streaming.model.mongo

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Field
import java.io.Serializable
import java.time.LocalDateTime

abstract class MongoBaseTimeEntity : Serializable {

    @field:Field("createdAt")
    @CreatedDate
    private var _createdAt: LocalDateTime = LocalDateTime.now()

    @field:Field("updatedAt")
    @LastModifiedDate
    private var _updatedAt: LocalDateTime = LocalDateTime.now()
    val createdAt: LocalDateTime
        get() = _createdAt

    val updatedAt: LocalDateTime
        get() = _updatedAt
}