package datagen

import java.lang.StringBuilder
import java.math.BigDecimal
import java.time.Duration
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream

object DataGenerator {
    private val random = Random(System.currentTimeMillis())
    private val users = Stream
        .generate { generateString(5) + "@email.com" }
        .limit(100)
        .collect(Collectors.toList())

    private fun generateAirportCode(): String {
        val airports = arrayOf(
            "ATL", "DFW", "DEN", "ORD", "LAX", "CLT", "MCO", "LAS", "PHX", "MIA",
            "SEA", "IAH", "JFK", "EWR", "FLL", "MSP", "SFO", "DTW", "BOS", "SLC",
            "PHL", "BWI", "TPA", "SAN", "LGA", "MDW", "BNA", "IAD", "DCA", "AUS"
        )
        return airports[random.nextInt(airports.size)]
    }

    private fun generateString(size: Int): String {
        val alphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val sb = StringBuilder(size)
        for (i in 0 until size) {
            val index = random.nextInt(alphaString.length)
            sb.append(alphaString[index])
        }
        return sb.toString()
    }

    private fun generateEmail(): String {
        return users[random.nextInt(users.size)]
    }

    private fun generateDepartureTime(): ZonedDateTime {
        return LocalDate.now()
            .plusDays(random.nextInt(365).toLong())
            .atTime(random.nextInt(24), random.nextInt(60))
            .atZone(ZoneId.of("UTC"))
    }

    private fun generateArrivalTime(departure: ZonedDateTime?): ZonedDateTime? {
        return departure
            ?.plusHours(random.nextInt(15).toLong())
            ?.plusMinutes(random.nextInt(60).toLong())
    }

    @JvmStatic
    fun generateSkyOneAirlinesFlightData(): SkyOneAirlinesFlightData {
        val flightData = SkyOneAirlinesFlightData()
        flightData.emailAddress = generateEmail()
        flightData.flightDepartureTime = generateDepartureTime()
        flightData.iataDepartureCode = generateAirportCode()
        flightData.flightArrivalTime = generateArrivalTime(flightData.flightDepartureTime)
        flightData.iataArrivalCode = generateAirportCode()
        flightData.flightNumber = "SKY1" + random.nextInt(1000)
        flightData.confirmation = "SKY1" + generateString(6)
        flightData.ticketPrice = (500 + random.nextInt(1000)).toFloat()
        flightData.aircraft = "Aircraft" + generateString(3)
        flightData.bookingAgencyEmail = generateEmail()
        return flightData
    }

    @JvmStatic
    fun generateSunsetAirFlightData(): SunsetAirFlightData {
        val flightData = SunsetAirFlightData()
        flightData.customerEmailAddress = generateEmail()
        flightData.departureTime = generateDepartureTime()
        flightData.departureAirport = generateAirportCode()
        flightData.arrivalTime = generateArrivalTime(flightData.departureTime)
        flightData.arrivalAirport = generateAirportCode()
        flightData.flightDuration = Duration.between(flightData.departureTime, flightData.arrivalTime)
        flightData.flightId = "SUN" + random.nextInt(1000)
        flightData.referenceNumber = "SUN" + generateString(8)
        flightData.totalPrice = BigDecimal(300 + random.nextInt(1500))
        flightData.aircraftDetails = "Aircraft" + generateString(4)
        return flightData
    }
}