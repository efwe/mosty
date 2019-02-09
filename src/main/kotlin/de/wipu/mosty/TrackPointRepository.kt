package de.wipu.mosty

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface TrackPointRepository : ReactiveMongoRepository<TrackPoint, String> {



    fun findByTrackId(trackId: ObjectId?): Flux<TrackPoint>

}
