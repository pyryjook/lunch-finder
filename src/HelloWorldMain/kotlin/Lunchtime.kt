package de.lunchtime

import de.lunchtime.location.getLocation
import de.lunchtime.coroutines.MainLoopDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import platform.CoreFoundation.CFRunLoopRun
import platform.posix.EXIT_SUCCESS
import platform.posix.exit

object Lunchtime

fun Lunchtime.run() {
    GlobalScope.launch(MainLoopDispatcher) {
        try {
            val loc = getLocation()
            println(loc)
        } catch (e: Exception) {
            println("Failed: $e")
        }
        exit(EXIT_SUCCESS)
    }
    CFRunLoopRun()
}