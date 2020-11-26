/*
 * Gruppe 3 SYS
 */
package dto.flightFetchResult;

import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 *
 * @author magda
 */
public class DepartureDTO{
    private String aqirport;
    private String terminal;
    private String gate;
    private String iata;
    private String scheduled;

    public DepartureDTO() {
    }

  

    public String getAqirport() {
        return aqirport;
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
