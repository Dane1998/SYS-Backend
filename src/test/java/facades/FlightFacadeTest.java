/*
 * Gruppe 3 SYS
 */
package facades;

import dto.FrontAirportDTO;
import dto.RestaurantDTO;
import dto.RestaurantLocationDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
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
    private static final double DEGRE_TO_KM=111.0;
 @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactory();
       facade = FlightFacade.getFlightFacade(emf);
    }
   // @Disabled
    @Test
    public void testSomeMethod(){
        ArrayList<FrontAirportDTO> list = facade.allAirports();
        
        assertEquals(6471, list.size());

    }
    @Test
    public void getAirportsByRestaurantTest(){
        System.out.println("getAirportsByRestaurant");
        double rLat=50.83629;
        double rLong=16.30990;
        double radius = 180.0;
        
        RestaurantDTO restaurant= new RestaurantDTO("", "", new RestaurantLocationDTO("","","50.83629", "16.30990",""));
        ArrayList<FrontAirportDTO> list = facade.allAirports();
        System.out.println("Airports on the list: "+list.size());
        for (FrontAirportDTO frontAirportDTO : list) {
            if (facade.calculateRadius(frontAirportDTO, restaurant)<=radius){
             //   System.out.println(frontAirportDTO.toString());
            }
        }
        System.out.println(facade.getAirportsByRestaurant(restaurant, radius));
    }
    @Test
    public void calculateRadius(){      
        System.out.println(".......................");
        System.out.println("......................."); 
        System.out.println("calculateRadius");
        //Palmiarnia Walbrzych, Poland
         double rLat=50.83629;
        double rLong=16.30990;
        double r;
        //Borowa Gora, Poland
        double aLat =50.73038;
        double aLong = 16.30359;
        
        double a=Math.pow(aLat-rLat, 2);
        double b=Math.pow(aLong-rLong, 2);
        double d=Math.sqrt(a+b);
        r=d*DEGRE_TO_KM;
        
        System.out.println("Radiusin km: "+r);
        assertEquals(r, 11.78,1.5);
    }
    
    @Test
    void getNearestAirortsTest(){
        RestaurantDTO restaurant= new RestaurantDTO("", "", new RestaurantLocationDTO("","","50.83629", "16.30990",""));
         Map<FrontAirportDTO, Double> nearest= new LinkedHashMap();
      /*  Map<FrontAirportDTO, Double> sorted=facade.getNearestAirorts(restaurant, 0);
       for (int i = 0; i < 10; i++) {
            FrontAirportDTO radius = (FrontAirportDTO) sorted.keySet().toArray()[i];
            Double valuse =sorted.get(radius);
            nearest.put(radius, valuse);
            System.out.println(nearest.get(radius));
        }*/
        System.out.println(facade.getNearestAirorts(restaurant, 22));
    }
}
