package de.wipu.mosty

/**
 *  {
 *     "bounding_box" : { "lower_left" : { "lat" : 50.775525067, "lon" : 20.3307602741 },
 *                        "upper_right" : { "lat" : 50.856179595, "lon" : 20.4608978704 }
 *                      }
 *  }
 */
data class BoundingBox(val lowerLeft : ArrayList<Double>,
                       val upperRight : ArrayList<Double>)