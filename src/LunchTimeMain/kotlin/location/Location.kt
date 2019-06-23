package de.lunchtime.location

import de.lunchtime.domain.Location
import platform.CoreLocation.CLLocation
import platform.Foundation.NSError
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private fun <T>onLocationData(
        continuation: Continuation<Location>,
        location: T?
) =
    when(location) {
        is CLLocation -> continuation.resume(toLocation(location))
        is NSError -> continuation.resumeWithException(toLocationException(location))
        else -> continuation.resumeWithException(toLocationException())
    }


private suspend fun getLocation(lm: LocationManager): Location =
    suspendCoroutine {continuation ->
        lm.subscribe(
            onUpdate = { location -> onLocationData(continuation, location) },
            onError = { error -> onLocationData(continuation, error) }
        )
    }

internal fun initLocationManager(lm: LocationManager) = suspend { getLocation(lm) }

fun initLocation() = initLocationManager(InternalLocationManager())
