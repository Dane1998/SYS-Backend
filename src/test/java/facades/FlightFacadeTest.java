/*
 * Gruppe 3 SYS
 */
package facades;

import dto.FrontAirportDTO;
import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author magda
 */

@Disabled

public class FlightFacadeTest {
    
    private static EntityManagerFactory emf;
    private static FlightFacade facade;
 @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = FlightFacade.getFlightFacade(emf);
    }
    @Test
    public void testSomeMethod(){
        ArrayList<FrontAirportDTO> list = facade.allAirports();
        
        assertEquals(6471, list.size());

    }
}
