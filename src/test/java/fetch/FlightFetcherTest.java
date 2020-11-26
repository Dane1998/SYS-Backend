package fetch;

import dto.AirportDTO;
import dto.CityDTO;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.SetupUsers;


/**
 *
 * @author magda  and daniel
 */
public class FlightFetcherTest {

    private static FlightFetcher ff = new FlightFetcher();
    

    @Disabled
    @Test
    public void getListOfAirports() throws IOException {
        
        System.out.println(SetupUsers.getAirportList100(100).size());
        for (AirportDTO a : SetupUsers.getAirportList100(100)) {
            System.out.println(a.toString());
        }

    }

    @Disabled
    @Test
    public void getAllAirports() throws IOException {

        ArrayList<AirportDTO> list = SetupUsers.allAirports();
        System.out.println(list.get(0).toString());
        System.out.println(list.get(6470).toString());
        System.out.println(list.size());
    }

   
    
    @Disabled
    @Test
    public void get100CitiesTest() throws IOException {
        ArrayList<CityDTO> list = SetupUsers.get100Cities(0);
        for (CityDTO ct : list) {
            System.out.println(ct.toString());
        }
    }

    @Test
    public void dateFormatTest() throws ParseException{
    /*    String dateString="2020-11-08";
            String[] dateArr = dateString.split("-");
            for (int i = 0; i < dateArr.length; i++) {
            String string = dateArr[i];
                System.out.println(string);
        }
            String dateString2=dateArr[0]+"/"+dateArr[1]+"/"+dateArr[2];
            System.out.println(dateString2);
        Date parsedDate = new SimpleDateFormat("yyyy/MM/dd").parse(dateString2); //    Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        
System.out.println("parsed:" + parsedDate.toString());
   String date = "2016-08-16";

        //default, ISO_LOCAL_DATE
        LocalDate localDate = LocalDate.parse(dateString);
        LocalDate localDate1 = LocalDate.parse(date);
         System.out.println("Date to string: "+ localDate1.toString());
         System.out.println("Date : "+ localDate1);
         */
    String schStr = "2019-12-12T04:20:13+00:00";
        // LocalDate scheduled = LocalDate.parse("2019-12-12T04:20:13+00:00");
ZonedDateTime zonedDateTime = ZonedDateTime.parse(schStr);
         System.out.println("TO STRING: "+zonedDateTime.toString());
         System.out.println("hour: "+zonedDateTime.getHour());
         System.out.println("minut: "+zonedDateTime.getMinute());

    }
    
}
