# Mosty Map Server
This is an experiment. I want to provide additonal services for my bike-tracking homepage at [123k.org](https://123k.org). 
The main theme here is that we invent questions wich require a longer time to be answered and produce geo-information data which will be 
added to the respective view as they appear (a.k.a stream-processing).

# Implementation Details

* We use the lates and greatest spring-boot-reactive-mongo-kotlin thing
* We focus on streaming positions `[lat,long]`
* Only public available `GET`s are implemented - no OAuth/JWT and of course no `POST`s


# Road-Map
Right now this is only a implementation test. Whenever i managed to implement the client-side correctly I try
to put it into production.
