/*
 * Gruppe 3 SYS
 */
package dto;

/**
 *
 * @author magda
 */
public class FrontAirportDTO {
    
    private String name;
    private String code;
    private String country;
    private String city;

    public FrontAirportDTO(String name, String code, String country, String city) {
        this.name = name;
        this.code = code;
        this.country = country;
        this.city = city;
    }

    public FrontAirportDTO() {
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    
   
}
