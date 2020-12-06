/*
 * Gruppe 3 SYS
 */
package dto;

import dto.flightFetchResult.AirlineDTO;
import dto.flightFetchResult.ArrivalDTO;
import dto.flightFetchResult.DepartureDTO;
import dto.flightFetchResult.LiveDTO;
import java.util.Objects;

/**
 *
 * @author magda
 */
public class FlightDTO {

    private String flight_date;
    private String flight_status;
    private DepartureDTO departure;
    private ArrivalDTO arrival;
    private AirlineDTO airline;
    private LiveDTO live;

    public FlightDTO(String flight_date, String flight_status, DepartureDTO departure, ArrivalDTO arrival, AirlineDTO airline, LiveDTO live) {

        this.flight_date = flight_date;
        this.flight_status = flight_status;
        this.departure = departure;
        this.arrival = arrival;
        this.airline = airline;
        this.live = live;
    }

    public String getFlight_date() {
        return flight_date;
    }

    public String getFlight_status() {
        return flight_status;
    }

    public DepartureDTO getDeparture() {
        return departure;
    }

    public ArrivalDTO getArrival() {
        return arrival;
    }

    public AirlineDTO getAirline() {
        return airline;
    }

    public LiveDTO getLive() {
        return live;
    }

    public void setFlight_date(String flight_date) {
        this.flight_date = flight_date;
    }

    @Override
    public String toString() {
        return "FlightDTO{" + "flight_date=" + flight_date + ", flight_status=" + flight_status + ", departure=" + departure.getAqirport() + ", arrival=" + arrival.getAirport() + ", airline=" + airline.getName() + ", live=" + live.getUpdated() + '}';
    }

    @Override
    public int hashCode() {

        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.flight_date);
        hash = 29 * hash + Objects.hashCode(this.departure);
        hash = 29 * hash + Objects.hashCode(this.arrival);
        hash = 29 * hash + Objects.hashCode(this.airline);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FlightDTO other = (FlightDTO) obj;
        if (!Objects.equals(this.flight_date, other.flight_date)) {
            return false;
        }
        if (!Objects.equals(this.departure.getScheduled(), other.departure.getScheduled())) {
            return false;
        }
        if (!Objects.equals(this.arrival.getScheduled(), other.arrival.getScheduled())) {
            return false;
        }

        return true;
    }

}
