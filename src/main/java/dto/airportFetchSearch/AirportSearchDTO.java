
package dto.airportFetchSearch;

/**
 *
 * @author Dane
 */
public class AirportSearchDTO {
    
    private String airport_name;
    private String iata_code;
    private String country_name;

    public AirportSearchDTO(String airport_name, String iata_code) {
        this.airport_name = airport_name;
        this.iata_code = iata_code;
    }

    public String getAirport_name() {
        return airport_name;
    }

    public String getIata_code() {
        return iata_code;
    }
    
    
}
