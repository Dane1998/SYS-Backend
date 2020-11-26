/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dto.RestaurantDTO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Dane
 */
@Entity
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String city;
    private double latitude;
    private double longitude;
    private int zipcode;

    public Restaurant() {
    }

    public Restaurant(RestaurantDTO restaurantDTO) {
        this.name = restaurantDTO.getName();
        this.address = restaurantDTO.getLocation().getAddress();
        this.city = restaurantDTO.getLocation().getCity();
        this.latitude = restaurantDTO.getLocation().getLatitude();
        this.longitude = restaurantDTO.getLocation().getLongitude();
        this.zipcode = restaurantDTO.getLocation().getZipcode();
   
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getZipcode() {
        return zipcode;
    }
    
    

    
    
}
