/*
 * Gruppe 3 SYS
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @OneToMany(mappedBy = "trip")
    private List<Flight> flights = new ArrayList();

    private User user;
    private List<Integer> restaurantID = new ArrayList();

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

    public List<Integer> getRestaurantID() {
        return restaurantID;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);

        if (flight.getTrip() == null) {
            flight.setTrip(this);
        }
    }

}
