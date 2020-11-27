
package dto;

/**
 *
 * @author Dane
 */
public class RestaurantDTO {
    private String id;
    private String name;
    private RestaurantLocationDTO location;
    

    public RestaurantDTO(String id, String name, RestaurantLocationDTO location) {
        this.id = id;
        this.name = name;
        this.location = location;
 
    }

    public String getId() {
        return id;
    }
    
    public RestaurantLocationDTO getLocation() {
        return location;
    }
    
    public String getName() {
        return name;
    }

  
}
