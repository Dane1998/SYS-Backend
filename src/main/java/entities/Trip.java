/*
 * Gruppe 3 SYS
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author magda
 */
@Entity
public class Trip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.PERSIST)
    private List<Flight> flights = new ArrayList();

    @JoinColumn (name="user_name")
    private User user;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Restaurant> restaurants = new ArrayList();

    public Trip() {
    }

    public int getId() {
        return id;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (!user.getTrips().contains(this)) {
            user.addTrip(this);
        }
        this.user = user;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);

        if (flight.getTrip() == null) {
            flight.setTrip(this);
        }
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

}
