
package dto;

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
