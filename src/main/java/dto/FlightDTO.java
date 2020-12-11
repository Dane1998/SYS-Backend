/*
 * Gruppe 3 SYS
 */
package dto;

import dto.flightFetchResult.AirlineDTO;
import dto.flightFetchResult.ArrivalDTO;
import dto.flightFetchResult.DepartureDTO;
import dto.flightFetchResult.LiveDTO;
import entities.Flight;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
    private FlightData flight;
    private String id;

    public FlightDTO(String flight_date, String flight_status, DepartureDTO departure, ArrivalDTO arrival, AirlineDTO airline, LiveDTO live) {

        this.flight_date = flight_date;
        this.flight_status = flight_status;
        this.departure = departure;
        this.arrival = arrival;
        this.airline = airline;
        this.live = live;
    }

    FlightDTO(Flight flight) {
        try {
            this.flight_date = flight.getArr_time().split("T")[0];
        } catch (Exception e) {
            this.flight_date = "No data";
        }
        this.flight_status = "No data";
        this.departure = new DepartureDTO(flight);
        this.arrival = new ArrivalDTO(flight);
        this.airline = new AirlineDTO(flight.getAirline());
        this.live = new LiveDTO(setLive());
    }

    private String setLive() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    public void setID() {
        id = flight.getIata();
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
        return "FlightDTO{" + "flight_date=" + flight_date + ", departure=" + departure + ", arrival=" + arrival + ", airline=" + airline + ", flight=" + flight.getIata() + ", id=" + id + '}';
    }

    public FlightData getFlight() {
        return flight;
    }

    public String getId() {
        return id;
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

class FlightData {

    private String iata;

    public String getIata() {
        return iata;
    }

}
