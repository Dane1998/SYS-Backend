/*
 * Gruppe 3 SYS
 */
package entities;

import dto.AirportDTO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author magda and daniel
 */
@Entity
public class Airport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String iata_code;
    private double latitude;
    private double longitude;
    private String country_name;
    private String city_iata_code;

    public Airport() {
    }

    public Airport(AirportDTO arp) {
        
        this.name = arp.getAirport_name();
        this.iata_code = arp.getIata_code();
        this.latitude = arp.getLatitude();
        this.longitude = arp.getLongitude();
        this.country_name = arp.getCountry_name();
        this.city_iata_code = arp.getCity_iata_code();
    }
    
    
 

    public int getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public String getIata_code() {
        return iata_code;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCountry_name() {
        return country_name;
    }

    public String getCity_iata_code() {
        return city_iata_code;
    }



   

    

    
}