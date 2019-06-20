package de.lunchtime.location

import de.lunchtime.domain.Location
import de.lunchtime.exceptions.LocationException
import kotlinx.cinterop.useContents
import platform.CoreLocation.CLLocation
import platform.Foundation.NSError

private const val DEFAULT_LOCATION_ERROR_MSG = "Location could not be found"

internal fun toLocationException(origErr: NSError? = null): LocationException =
        LocationException(origErr?.localizedDescription ?: DEFAULT_LOCATION_ERROR_MSG)

internal fun toLocation(origLoc: CLLocation): Location {
    val latitude = origLoc.coordinate.useContents { latitude }
    val longitude = origLoc.coordinate.useContents { longitude }

    return Location(
        latitude = latitude,
        longitude = longitude
    )
}