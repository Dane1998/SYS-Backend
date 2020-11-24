/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author magda daniel
 */
public class AirportDTO {
    private String airport_name;
    private String iata_code;
    private String country_name;
    private String city_iata_code;
    private double latitude;
    private double longitude;
   
    
    
   

    public AirportDTO() {
    }

    public AirportDTO(String airport_name, String iata_code, String country_name, String city_iata_code, double latitude, double longitude) {
        this.airport_name = airport_name;
        this.iata_code = iata_code;
        this.country_name = country_name;
        this.city_iata_code = city_iata_code;
        this.latitude = latitude;
        this.longitude = longitude;
    }

   
    

    public String getAirport_name() {
        return airport_name;
    }

    public String getIata_code() {
        return iata_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public String getCity_iata_code() {
        return city_iata_code;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "AirportDTO{" + "airport_name=" + airport_name + ", iata_code=" + iata_code + ", country_name=" + country_name + ", city_iata_code=" + city_iata_code + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

   
    
    

    
}
