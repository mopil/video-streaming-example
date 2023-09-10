package com.example.streaming.model.mongo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.MongoTransactionManager
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.core.convert.DbRefResolver
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = ["com.example.streaming.model.mongo"])
class MongoConfiguration(
        private val mongoMappingContext: MongoMappingContext
) {
    @Bean
    fun mappingMongoConverter(
            mongoDatabaseFactory: MongoDatabaseFactory,
            mongoMappingContext: MongoMappingContext,
    ): MappingMongoConverter {
        val dbRefResolver: DbRefResolver = DefaultDbRefResolver(mongoDatabaseFactory)
        val converter = MappingMongoConverter(dbRefResolver, mongoMappingContext)
        converter.setTypeMapper(DefaultMongoTypeMapper(null))
        return converter
    }

    @Bean
    fun mongoTransactionManager(
            mongoDatabaseFactory: MongoDatabaseFactory
    ): MongoTransactionManager {
        return MongoTransactionManager(mongoDatabaseFactory)
    }
}