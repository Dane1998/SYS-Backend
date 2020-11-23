package fetch;

import dto.AirportDTO;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author magda daniel
 */
public class FlightFetcherTest {
    
    @Test
    public void airportsFetchTest() throws IOException {
        FlightFetcher ff = new FlightFetcher();
      //  System.out.println(ff.getAirports("http://api.aviationstack.com/v1", 10, 20, "f0db19fb49cae5b7349fd4cc157b47e3&limit=10&offset=28"));
    }
    
    @Test public void getListOfAirports() throws IOException {
                FlightFetcher ff = new FlightFetcher();
                System.out.println(ff.getAirportList100(100).size());
                for (AirportDTO a :ff.getAirportList100(100)) {
                    System.out.println(a.toString());
        }
        
    }
    
    @Disabled
    @Test 
    public void getAllAirports() throws IOException{
                        FlightFetcher ff = new FlightFetcher();
                        ArrayList<AirportDTO> list = ff.allAirports();
                        System.out.println(list.get(0).toString());
                        System.out.println(list.get(6470).toString());
        System.out.println(list.size());
    }
    
    
    
    @Test
    public void printTheFakeList() {
                                FlightFetcher ff = new FlightFetcher();
        for (AirportDTO a : ff.fakeAirports()) {
            System.out.println(a.toString());
        }

    }
}
