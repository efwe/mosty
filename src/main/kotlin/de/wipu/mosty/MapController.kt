package de.wipu.mosty

import org.bson.types.ObjectId
import org.springframework.http.MediaType
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import java.time.Instant
import java.util.*

@RestController
@RequestMapping(path = ["/map"])
class Controller(val trackPointRepository: TrackPointRepository, val trackRepository: TrackRepository) {

    @CrossOrigin
    @GetMapping(path = ["/tracks"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    @ResponseBody
    fun getLatestTracks(): Flux<ServerSentEvent<Track>> {
        return trackRepository.findByStartBetween(Date.from(Instant.parse("2018-04-07T00:00:00.00Z")),Date.from(Instant.parse("2018-05-05T00:00:00.00Z")))
                .map { track: Track ->
                    ServerSentEvent
                            .builder<Track>()
                            .data(track)
                            .event("track")
                            .build()
                }

    }

    @CrossOrigin
    @GetMapping(value = ["/track/{id}/points"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    @ResponseBody
    fun trackPoints(@PathVariable("id") trackId: String): Flux<ServerSentEvent<TrackPoint>> {
        return trackPointRepository.findByTrackId(ObjectId(trackId))
                .map { point: TrackPoint ->
                    ServerSentEvent
                            .builder<TrackPoint>()
                            .data(point)
                            .event("point")
                            .build()
                }

    }

}