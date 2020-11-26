/*
 * Gruppe 3 SYS
 */
package dto.flightFetchResult;

import dto.FlightDTO;
import dto.flightFetchResult.PaginationDTO;
import java.util.ArrayList;

/**
 *
 * @author magda
 */
public class FlightsListDTO {
    private ArrayList<FlightDTO> data ;
        private PaginationDTO pagination;

    public FlightsListDTO(ArrayList<FlightDTO> data, PaginationDTO pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public PaginationDTO getPagination() {
        return pagination;
    }


   

    public ArrayList<FlightDTO> getData() {
        return data;
    }
    
    

    
    
    
}




