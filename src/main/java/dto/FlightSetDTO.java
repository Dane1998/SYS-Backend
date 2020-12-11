/*
 * Gruppe 3 SYS
 */
package dto;

import java.util.ArrayList;

/**
 *
 * @author magda
 */
public class FlightSetDTO {
    private String id;
    private ArrayList<FlightDTO> flights;

    public FlightSetDTO() {
       
        this.flights = new ArrayList();
    }
    
    public void setID(){
        if(flights.size()<1){
            id="No flights";
        }else{
            id="";
            for (FlightDTO flight : flights) {
                id+=flight.getId();
            }
        }
  
    }
    public void addFlight(FlightDTO flight){
        flights.add(flight);
    }
    
    
    
    
}
