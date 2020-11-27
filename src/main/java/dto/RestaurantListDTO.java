
package dto;

import dto.dto.RestaurantFetchResult.RestaurantKeeperDTO;
import java.util.ArrayList;

/**
 *
 * @author Dane
 */
public class RestaurantListDTO {
    
    ArrayList<RestaurantKeeperDTO> restaurants;

    public RestaurantListDTO(ArrayList<RestaurantKeeperDTO> restaurants) {
        this.restaurants = restaurants;
    }

    public ArrayList<RestaurantKeeperDTO> getRestaurants() {
        return restaurants;
    }
    
    
}
