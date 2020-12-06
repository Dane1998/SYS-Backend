/*
 * Gruppe 3 SYS
 */
package dto;

/**
 *
 * @author magda
 */
public class FrontAirportDTO {

    private int id;
    private String name;
    private String code;
    private String country;
    private String city;
    private double latitude;
    private double longitude;

    public FrontAirportDTO(int id, String name, String code, String country, String city, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "FrontAirportDTO{" + "id=" + id + ", name=" + name + ", code=" + code + ", country=" + country + ", city=" + city + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCity() {
        return city;
    }
    

    
    
}