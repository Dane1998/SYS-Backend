/*
 * Gruppe 3 SYS
 */
package dto.flightFetchResult;

import entities.Flight;
import java.time.ZonedDateTime;

/**
 *
 * @author magda
 */
public class ArrivalDTO {

    private String airport;
    private String terminal;
    private String gate;
    private String iata;
    private String scheduled;

    public ArrivalDTO(){
    }

    public ArrivalDTO(Flight flight) {
        this.airport = "No Data";
        this.terminal = flight.getArr_terminal();
        try {
            this.gate = flight.getArr_gate();
        } catch (Exception e) {
            this.gate = "No data";
        }
        this.iata = flight.getArr_code();
        this.scheduled = flight.getArr_time();
    }

   

   

    public String getAirport() {
        return airport;
    }

    public String getTerminal() {
        return terminal;
    }

    public String getGate() {
        return gate;
    }

    public String getIata() {
        return iata;
    }

    public String getScheduled() {
        return scheduled;
    }
    

}
