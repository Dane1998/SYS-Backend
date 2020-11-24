/*
 * Gruppe 3 SYS
 */
package dto;

/**
 *
 * @author magda and daniel
 */
public class CityDTO {
     private String city_name;
    private String iata_code;

    public CityDTO(String city_name, String iata_code) {
        this.city_name = city_name;
        this.iata_code = iata_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getIata_code() {
        return iata_code;
    }

    
}
