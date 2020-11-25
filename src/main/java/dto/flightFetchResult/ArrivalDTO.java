/*
 * Gruppe 3 SYS
 */
package dto.flightFetchResult;

/**
 *
 * @author magda
 */
public class ArrivalDTO {

    private String airport;
    private String terminal;
    private String gate;
    private String iata;

    public ArrivalDTO(String airport, String terminal, String gate, String iata) {
        this.airport = airport;
        this.terminal = terminal;
        this.gate = gate;
        this.iata = iata;
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
    

}
