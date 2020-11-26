
package dto.cities;


import java.util.ArrayList;

/**
 *
 * @author Bruger
 */
public class LocationSugDTO {
    ArrayList<dto.zCityDTO> location_suggestions;

    public LocationSugDTO(ArrayList<dto.zCityDTO> location_suggestions) {
        this.location_suggestions = location_suggestions;
    }

    public ArrayList<dto.zCityDTO> getCities() {
        return location_suggestions;
    }
    
    
    
}
