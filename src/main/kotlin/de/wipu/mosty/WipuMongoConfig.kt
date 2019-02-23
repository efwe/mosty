package de.wipu.mosty

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import java.util.ArrayList


@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = [TrackPointRepository::class])
class WipuMongoConfig : AbstractReactiveMongoConfiguration() {

    private val converters = ArrayList<Converter<*, *>>()

    override fun customConversions(): MongoCustomConversions {
        converters.add(TrackPointReadConverter())
        return MongoCustomConversions(converters)
    }

    override fun getDatabaseName() = "wipu"

    override fun reactiveMongoClient() = mongoClient()

    @Bean
    fun mongoClient(): MongoClient = MongoClients.create()

    @Bean
    fun reactiveWipuMongoTemplate(): ReactiveMongoTemplate = ReactiveMongoTemplate(mongoClient(), databaseName)
}