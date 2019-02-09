package de.wipu.mosty

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.util.*

@Repository
interface TrackRepository : ReactiveMongoRepository<Track, String> {

    fun findByStartAfter(date: Date): Flux<Track>

}