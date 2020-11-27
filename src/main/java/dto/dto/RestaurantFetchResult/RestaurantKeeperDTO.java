
package dto.dto.RestaurantFetchResult;

import dto.RestaurantDTO;

/**
 *
 * @author Dane
 */
public class RestaurantKeeperDTO {
    
    RestaurantDTO restaurant;

    public RestaurantKeeperDTO(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }
    
    
}
