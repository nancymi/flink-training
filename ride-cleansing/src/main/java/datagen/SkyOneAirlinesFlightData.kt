package datagen

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonFormat
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonFormat.Shape
import java.time.ZonedDateTime
import java.util.*

data class SkyOneAirlinesFlightData(
    var emailAddress: String? = null,
    @org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonFormat(
        shape = Shape.STRING,
        pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    )
    var flightDepartureTime: ZonedDateTime? = null,
    var iataDepartureCode: String? = null,
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    var flightArrivalTime: ZonedDateTime? = null,
    var iataArrivalCode: String? = null,
    var flightNumber: String? = null,
    var confirmation: String? = null,
    var ticketPrice: Float? = null,
    var aircraft: String? = null,
    var bookingAgencyEmail: String? = null
) {
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as SkyOneAirlinesFlightData
        return that.ticketPrice?.compareTo(ticketPrice!!) == 0 &&
                emailAddress == that.emailAddress &&
                flightDepartureTime == that.flightDepartureTime &&
                iataDepartureCode == that.iataDepartureCode &&
                flightArrivalTime == that.flightArrivalTime &&
                iataArrivalCode == that.iataArrivalCode &&
                flightNumber == that.flightNumber &&
                confirmation == that.confirmation &&
                aircraft == that.aircraft &&
                bookingAgencyEmail == that.bookingAgencyEmail
    }

    override fun hashCode(): Int {
        return Objects.hash(
            emailAddress,
            flightDepartureTime,
            iataDepartureCode,
            flightArrivalTime,
            iataArrivalCode,
            flightNumber,
            confirmation,
            ticketPrice,
            aircraft,
            bookingAgencyEmail
        )
    }
}