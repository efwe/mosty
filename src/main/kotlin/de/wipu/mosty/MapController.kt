package de.wipu.mosty

import org.bson.types.ObjectId
import org.springframework.http.MediaType
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import java.time.Duration
import java.time.Instant
import java.util.*

@RestController
@RequestMapping(path = ["/map"])
class Controller(val trackPointRepository: TrackPointRepository, val trackRepository: TrackRepository) {

    @CrossOrigin
    @GetMapping(path = ["/tracks"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    @ResponseBody
    fun getLatestTrack(): Flux<ServerSentEvent<Track>> {
        return trackRepository.findByStartAfter(Date.from(Instant.parse("2018-05-01T00:00:00.00Z")))
                .map { track: Track ->
                    ServerSentEvent
                            .builder<Track>()
                            .data(track)
                            .event("track")
                            .build()
                }
                .delayElements(Duration.ofSeconds(1))

    }

    @CrossOrigin
    @GetMapping(value = ["/track/{id}/points"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    @ResponseBody
    fun trackPoints(@PathVariable("id") trackId: String): Flux<TrackPoint> {
        return trackPointRepository.findByTrackId(ObjectId(trackId))
    }

}