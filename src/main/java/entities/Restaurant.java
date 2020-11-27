/*
 * Gruppe 3 SYS
 */
package entities;

import dto.RestaurantDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author magda
 */
@Entity
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    
    private int id;
    private String name;

    @ManyToMany(mappedBy = "restaurants"  , cascade = CascadeType.PERSIST)
    private List<Trip> trips = new ArrayList();

    public Restaurant() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public void setID(int id) {
        this.id=id;
    }

    public Restaurant(RestaurantDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
    }
    
    
    
}
