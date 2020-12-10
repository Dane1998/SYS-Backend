package dto;

import entities.Restaurant;

/**
 *
 * @author Dane
 */
public class RestaurantDTO {

    private int id;
    private String name;
    private RestaurantLocationDTO location;

    public RestaurantDTO(int id, String name, RestaurantLocationDTO location) {
        this.id = id;
        this.name = name;
        this.location = location;

    }

    RestaurantDTO(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
    }

    public int getId() {
        return id;
    }

    public RestaurantLocationDTO getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

}
