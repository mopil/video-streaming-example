package com.example.streaming

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
class StreamingApplication

fun main(args: Array<String>) {
	runApplication<StreamingApplication>(*args)
}
