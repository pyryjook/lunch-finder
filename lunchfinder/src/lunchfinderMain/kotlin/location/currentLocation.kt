package location

import platform.CoreLocation.CLLocationManager
//import kotlinx.coroutines.*

data class Location(val latitude: Double, val longitude: Double)

private val locationManager = CLLocationManager()

fun getCurrentLocation(): Location {
    println("WHO_")
    println(locationManager.location())
    return Location(latitude = 52.54001, longitude =  13.42301)
}
