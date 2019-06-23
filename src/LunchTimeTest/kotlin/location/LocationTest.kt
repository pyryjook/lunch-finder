package de.lunchtime.location

import de.lunchtime.domain.Location
import de.lunchtime.exceptions.LocationException
import kotlinx.coroutines.runBlocking
import location.mocks.mockLocationFetchToFail
import location.mocks.mockLocationFetchToSucceed
import location.mocks.mockLocationFetchToSucceedWithNull
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class LocationTest {

    @Test
    fun `should successfully get the location`() {
        // arrange
        val expected = Location(latitude = 0.0, longitude = 0.0)
        val getLocation = mockLocationFetchToSucceed()

        // act
        val result = runBlocking { getLocation() }

        // assert
        assertEquals(result, expected)
    }

    @Test
    fun `should fail with LocationException when location fetching emits error`() {
        // arrange
        val getLocation = mockLocationFetchToFail()

        // assert
        assertFailsWith<LocationException> { runBlocking { getLocation() } }
    }
    
    @Test
    fun `should fail with LocationException when location is null`() {
        // arrange
        val getLocation = mockLocationFetchToSucceedWithNull()

        // assert
        assertFailsWith<LocationException> { runBlocking { getLocation() } }
    }
}