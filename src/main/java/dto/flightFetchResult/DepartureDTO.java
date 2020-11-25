/*
 * Gruppe 3 SYS
 */
package dto.flightFetchResult;

/**
 *
 * @author magda
 */
public class DepartureDTO{
    private String aqirport;
    private String terminal;
    private String gate;
    private String iata;

    public DepartureDTO(String aqirport, String terminal, String gate, String iata) {
        this.aqirport = aqirport;
        this.terminal = terminal;
        this.gate = gate;
        this.iata = iata;
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
    
    
    
}
