package location

import kotlin.test.Test
import kotlin.test.assertEquals

class GreetingTest {

    @Test
    fun `should print hello android from android mpp`() {
        assertEquals(getCurrentLocation(), Location(1.1, 1.2))
    }
}