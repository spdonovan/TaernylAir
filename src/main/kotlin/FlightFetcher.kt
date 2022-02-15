import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private const val BASE_URL = "http://kotlin-book.bignerdranch.com/2e"
private const val FLIGHT_ENDPOINT = "$BASE_URL/flight"
private const val LOYALTY_ENDPOINT = "$BASE_URL/loyalty"

fun main() {
    runBlocking {
        println("Started")
        launch {
            val flight = fetchFlight("Madrigal")
            println(flight)
        }
        println("Finished")
    }
}

suspend fun fetchFlight(passengerName: String): FlightStatus {
    val client = HttpClient(CIO)
    val flightResponse =  client.get<String>(FLIGHT_ENDPOINT)
    val loyaltyResponse = client.get<String>(LOYALTY_ENDPOINT)

    return FlightStatus.parse(
        passengerName = passengerName,
        flightResponse = flightResponse,
        loyaltyResponse = loyaltyResponse
    )
}

