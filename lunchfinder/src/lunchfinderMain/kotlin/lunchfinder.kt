import location.getCurrentLocation

fun printLunchPlaces(args: Array<String>) {
    val loc = getCurrentLocation()
    println(loc)
    println(args)
    args.map {
        println(it)
    }
}