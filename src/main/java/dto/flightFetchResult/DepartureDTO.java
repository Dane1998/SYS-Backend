/*
 * Gruppe 3 SYS
 */
package dto.flightFetchResult;

import entities.Flight;

/**
 *
 * @author magda
 */
public class DepartureDTO {

    private String airport;
    private String terminal;
    private String gate;
    private String iata;
    private String scheduled;

    public DepartureDTO() {
    }

    public DepartureDTO(Flight flight) {
        this.airport = "No data";
        this.terminal = flight.getDep_terminal();
        try {
            this.gate = flight.getDep_gate();
        } catch (Exception e) {
            this.gate = "No data";
        }
        this.iata = flight.getDep_code();
        this.scheduled = flight.getDep_time();
    }

    public String getAqirport() {
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
