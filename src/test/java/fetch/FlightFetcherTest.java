package fetch;

import dto.AirportDTO;
import dto.CityDTO;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


/**
 *
 * @author magda  and daniel
 */
public class FlightFetcherTest {

    private static FlightFetcher ff = new FlightFetcher();
    @Test
    public void airportsFetchTest() throws IOException {
        //  FlightFetcher ff = new FlightFetcher();
        //  System.out.println(ff.getAirports("http://api.aviationstack.com/v1", 10, 20, "f0db19fb49cae5b7349fd4cc157b47e3&limit=10&offset=28"));
    }

    @Test
    public void getListOfAirports() throws IOException {
        
        System.out.println(ff.getAirportList100(100).size());
        for (AirportDTO a : ff.getAirportList100(100)) {
            System.out.println(a.toString());
        }

    }

    @Disabled
    @Test
    public void getAllAirports() throws IOException {

        ArrayList<AirportDTO> list = ff.allAirports();
        System.out.println(list.get(0).toString());
        System.out.println(list.get(6470).toString());
        System.out.println(list.size());
    }

   
    
    @Disabled
    @Test
    public void get100CitiesTest() throws IOException {
        ArrayList<CityDTO> list = ff.get100Cities(0);
        for (CityDTO ct : list) {
            System.out.println(ct.toString());
        }
    }

    
}
