package com.example.streaming.model.mongo

import org.springframework.data.mongodb.repository.MongoRepository

interface VideoRepository : MongoRepository<Video, String> {
}