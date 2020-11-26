/*
 * Gruppe 3 SYS
 */
package dto;

import dto.flightFetchResult.AirlineDTO;
import dto.flightFetchResult.ArrivalDTO;
import dto.flightFetchResult.DepartureDTO;
import dto.flightFetchResult.LiveDTO;


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

    public FlightDTO( String flight_date, String flight_status, DepartureDTO departure, ArrivalDTO arrival, AirlineDTO airline, LiveDTO live) {
        
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
    
    
    
    
    
    
}


