package location.mocks

import de.lunchtime.location.LocationManager
import de.lunchtime.location.initLocationManager
import platform.CoreLocation.CLLocation
import platform.Foundation.NSError



private const val MOCK_ERROR_NAME = "Mocked"
private const val MOCK_ERROR_CODE = 9999L

private class MockLocationManagerError : LocationManager {
    override fun subscribe(onUpdate: (CLLocation?) -> Unit, onError: (NSError) -> Unit) {
        onError(NSError.errorWithDomain(
                domain = MOCK_ERROR_NAME,
                code = MOCK_ERROR_CODE,
                userInfo = mapOf("foo" to "bar")
        ))
    }
}

private class MockLocationManagerNull : LocationManager {
    override fun subscribe(onUpdate: (CLLocation?) -> Unit, onError: (NSError) -> Unit) {
        onUpdate(null)
    }
}

private class MockLocationManagerSuccess : LocationManager {
    override fun subscribe(onUpdate: (CLLocation?) -> Unit, onError: (NSError) -> Unit) {
        onUpdate(CLLocation.new())
    }
}

fun mockLocationFetchToFail() = initLocationManager(MockLocationManagerError())
fun mockLocationFetchToSucceed() = initLocationManager(MockLocationManagerSuccess())
fun mockLocationFetchToSucceedWithNull() = initLocationManager(MockLocationManagerNull())