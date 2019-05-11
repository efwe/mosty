package de.wipu.mosty

import org.bson.types.ObjectId
import org.springframework.http.MediaType
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Instant
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping(path = ["/map"])
class Controller(val trackPointRepository: TrackPointRepository, val trackRepository: TrackRepository) {

    @CrossOrigin
    @GetMapping(path = ["/tracks"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    @ResponseBody
    fun getLatestTracks(): Flux<ServerSentEvent<Track>> {
        return trackRepository.findTop10ByOrderByStartDesc()
                .map { track: Track ->
                    ServerSentEvent
                            .builder<Track>()
                            .data(track)
                            .event("track")
                            .build()
                }
    }

    @CrossOrigin
    @GetMapping(value = ["/track/{id}/point-stream"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    @ResponseBody
    fun trackPointStream(@PathVariable("id") trackId: String): Flux<ServerSentEvent<TrackPoint>> {
        return trackPointRepository.findByTrackId(ObjectId(trackId))
                .map { point: TrackPoint ->
                    ServerSentEvent
                            .builder<TrackPoint>()
                            .data(point)
                            .event("point")
                            .build()
                }

    }


    @CrossOrigin
    @GetMapping(value = ["/track/{id}/points"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun trackPoints(@PathVariable("id") trackId: String): Mono<List<Array<Double>>> {
        return trackPointRepository.findByTrackId(ObjectId(trackId))
                .map { point: TrackPoint ->
                    Array(2, point.location::get)
                }
                .collect(Collectors.toList())
    }


}