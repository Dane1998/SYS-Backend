/*
 * Gruppe 3 SYS
 */
package fetch;

import dto.AirportDTO;
import dto.CityDTO;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.PopulateDB;
import utils.SetupUsers;

/**
 *
 * @author magda
 */
public class PopulateDBTest {
    
    @Disabled
    @Test
    public void getListOfAirports() throws IOException {
        
        System.out.println(PopulateDB.getAirportList100(100).size());
        for (AirportDTO a : PopulateDB.getAirportList100(100)) {
            System.out.println(a.toString());
        }

    }

    @Disabled
    @Test
    public void getAllAirports() throws IOException {

        ArrayList<AirportDTO> list = PopulateDB.allAirports();
        System.out.println(list.get(0).toString());
        System.out.println(list.get(6470).toString());
        System.out.println(list.size());
    }

   
    
    @Disabled
    @Test
    public void get100CitiesTest() throws IOException {
        ArrayList<CityDTO> list = PopulateDB.get100Cities(0);
        for (CityDTO ct : list) {
            System.out.println(ct.toString());
        }
    }
}
