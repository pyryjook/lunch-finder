package de.lunchtime

import kotlinx.cinterop.autoreleasepool
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    autoreleasepool {
        Lunchtime.run()
    }
}
