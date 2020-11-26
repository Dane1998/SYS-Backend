/*
 * Gruppe 3 SYS
 */
package dto.flightFetchResult;

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
