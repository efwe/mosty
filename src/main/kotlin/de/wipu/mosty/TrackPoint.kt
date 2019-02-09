package de.wipu.mosty

import com.fasterxml.jackson.annotation.JsonIgnore
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*


@Document(collection = "track_points")
data class TrackPoint(@JsonIgnore val id: String,
                      @JsonIgnore @Field("route_id") val trackId: ObjectId?,
                      val location: ArrayList<Double>,
                      val time: Date,
                      val elevation: Double) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TrackPoint

        if (id != other.id) return false
        if (trackId != other.trackId) return false
        if (location != other.location) return false
        if (time != other.time) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + trackId.hashCode()
        result = 31 * result + location.hashCode()
        result = 31 * result + time.hashCode()
        return result
    }
}