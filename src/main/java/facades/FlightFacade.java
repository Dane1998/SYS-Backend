
package facades;

import dto.AirportDTO;
import fetch.FlightFetcher;
import java.io.IOException;
import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author magda
 */
public class FlightFacade {
        
    private static EntityManagerFactory emf;
    private static FlightFacade instance;
    private static FlightFetcher fetcher;
    private FlightFacade() {
        
    }
    
    public static FlightFacade getFlightFacade(EntityManagerFactory _emf) {
         if (instance == null) {
            emf = _emf;
            instance = new FlightFacade();
        }
        return instance;
    }
    
    
    public ArrayList<AirportDTO> allAirports() throws IOException{
       //return fetcher.allAirports();
        return fetcher.fakeAirports();
    }
    
    
    
        
    
}
