
package dto.airportFetchSearch;

import java.util.ArrayList;

/**
 *
 * @author Dane
 */
public class AirportSearchListDTO {
    
    private ArrayList<AirportSearchDTO> data;

    public AirportSearchListDTO(ArrayList<AirportSearchDTO> data) {
        this.data = data;
    }

    public ArrayList<AirportSearchDTO> getData() {
        return data;
    }
    
    
}
