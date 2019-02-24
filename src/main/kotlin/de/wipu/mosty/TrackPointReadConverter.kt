package de.wipu.mosty

import org.bson.Document
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.ArrayList


@Component
class TrackPointReadConverter : Converter<Document, TrackPoint> {

    override fun convert(source: Document): TrackPoint {
        // mongo has [lon,lat] (20,50) and we need [lat,lon] (50,20)
        val location: ArrayList<Double> = source["location"] as ArrayList<Double>
        // we want to transport only the long and not a complete date
        val time: Long = (source["time"] as Date).toInstant().epochSecond
        location.reverse()
        return TrackPoint(source["_id"].toString()
                , null
                , location
                , time
                , source["elevation"] as Double)
    }
}

