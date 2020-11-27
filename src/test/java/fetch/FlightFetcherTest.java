package fetch;

import java.text.ParseException;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;


/**
 *
 * @author magda  and daniel
 */
public class FlightFetcherTest {

    private static FlightFetcher ff = new FlightFetcher();
    


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
