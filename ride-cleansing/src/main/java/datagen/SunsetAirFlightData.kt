package datagen

import datagen.SunsetAirFlightData
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.time.Duration
import java.time.ZonedDateTime
import java.util.*

data class SunsetAirFlightData(
    var customerEmailAddress: String? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    var departureTime: ZonedDateTime? = null,
    var departureAirport: String? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    var arrivalTime: ZonedDateTime? = null,
    var arrivalAirport: String? = null,
    var flightDuration: Duration? = null,
    var flightId: String? = null,
    var referenceNumber: String? = null,
    var totalPrice: BigDecimal? = null,
    var aircraftDetails: String? = null
) {
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as SunsetAirFlightData
        return customerEmailAddress == that.customerEmailAddress && departureTime == that.departureTime && departureAirport == that.departureAirport && arrivalTime == that.arrivalTime && arrivalAirport == that.arrivalAirport && flightDuration == that.flightDuration && flightId == that.flightId && referenceNumber == that.referenceNumber && totalPrice == that.totalPrice && aircraftDetails == that.aircraftDetails
    }

    override fun hashCode(): Int {
        return Objects.hash(
            customerEmailAddress,
            departureTime,
            departureAirport,
            arrivalTime,
            arrivalAirport,
            flightDuration,
            flightId,
            referenceNumber,
            totalPrice,
            aircraftDetails
        )
    }

    override fun toString(): String {
        return "SunsetAirFlightData{" +
                "customerEmailAddress='" + customerEmailAddress + '\'' +
                ", departureTime=" + departureTime +
                ", departureAirport='" + departureAirport + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", flightDuration=" + flightDuration +
                ", flightId='" + flightId + '\'' +
                ", referenceNumber='" + referenceNumber + '\'' +
                ", totalPrice=" + totalPrice +
                ", aircraftDetails='" + aircraftDetails + '\'' +
                '}'
    }
}