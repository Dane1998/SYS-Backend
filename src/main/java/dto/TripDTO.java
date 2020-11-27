/*
 * Gruppe 3 SYS
 */
package dto;

import java.util.ArrayList;
import java.util.List;
import dto.RestaurantDTO;

/**
 *
 * @author magda
 */
public class TripDTO {
    private List<RestaurantDTO> restaurants = new ArrayList();
    private List<FlightDTO> flights = new ArrayList();
    private String username;

    public List<RestaurantDTO> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantDTO> restaurants) {
        this.restaurants = restaurants;
    }

    public List<FlightDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightDTO> flights) {
        this.flights = flights;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
