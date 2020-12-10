/*
 * Gruppe 3 SYS
 */
package dto;

import java.util.ArrayList;
import java.util.List;
import dto.RestaurantDTO;
import entities.Flight;
import entities.Restaurant;
import entities.Trip;

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

    public TripDTO(Trip trip) {
        this.username = trip.getUser().getUserName();
        for (Restaurant restaurant : trip.getRestaurants()) {
            restaurants.add(new RestaurantDTO(restaurant));
        }
        for (Flight flight : trip.getFlights()) {
            flights.add(new FlightDTO(flight));
        }
    }
    
    
}
