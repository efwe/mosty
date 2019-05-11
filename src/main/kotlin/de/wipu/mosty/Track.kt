package de.wipu.mosty

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

/**
 *  {
 *     "_id" : ObjectId("51737e6c2bb882d748000674"),
 *     "bounding_box" : { "lower_left" : { "lat" : 50.775525067, "lon" : 20.3307602741 },
 *                        "upper_right" : { "lat" : 50.856179595, "lon" : 20.4608978704 }
 *                      },
 *     "content" : "My super application told me via email this morning that the geohashpoint for this day is very near, so I started my second geohash campaign. I actually failed to really reach the point because i think two guys and one tractor shared the very same place with the hashpoint.",
 *     "created_at" : ISODate("2013-04-21T05:51:40.350Z"),
 *     "ascend" : 542.1900000000003,
 *     "descend" : -408.5700000000001,
 *     "distance" : 31157.941433524444,
 *     "geohash_campaign" : "yes",
 *     "suppress_elevation_chart" : "no",
 *     "title" : "Second Geohashing attempt in Bolmin",
 *     "track_start" : ISODate("2013-04-15T10:19:10Z"),
 *     "track_stop" : ISODate("2013-04-15T12:33:40Z"),
 *     "transport" : "bike",
 *     "updated_at" : ISODate("2013-04-21T05:56:00.587Z")
 *  }
 */

@Document(collection = "routes")
data class Track(val id: String,
                 @Field("bounding_box") val boundingBox: HashMap<String, HashMap<String,Double>>,
                 @Field("track_start") val start: Date,
                 @Field("track_stop") val stop: Date,
                 val ascend: Double,
                 val descend: Double,
                 val distance: Double,
                 val transport: Transport,
                 val title: String,
                 @Field("content") val description: String?) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Track

        if (id != other.id) return false
        if (start != other.start) return false
        if (stop != other.stop) return false
        if (ascend != other.ascend) return false
        if (descend != other.descend) return false
        if (distance != other.distance) return false
        if (transport != other.transport) return false
        if (title != other.title) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + start.hashCode()
        result = 31 * result + stop.hashCode()
        result = 31 * result + ascend.hashCode()
        result = 31 * result + descend.hashCode()
        result = 31 * result + distance.hashCode()
        result = 31 * result + transport.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}

