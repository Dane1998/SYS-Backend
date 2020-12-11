package fetch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.FlightSetDTO;
import errorhandling.API_Exception;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import utils.HttpUtils;
import java.time.*;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.junit.jupiter.api.Disabled;

/**
 *
 * @author magda
 */
public class FlightFetcherTest {

    private static final ExecutorService threadPool = HttpUtils.getThreadPool();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static FlightFetcher fetcher = FlightFetcher.getFlightFetcher(GSON, threadPool);

    @Disabled
    @Test
    void TestListOfSets() {
        try {
            ArrayList<FlightSetDTO> list = fetcher.setFLights("XMN", "KWE", "2020-12-12");
            for (FlightSetDTO flightSetDTO : list) {
                System.out.println(flightSetDTO.toString());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

    }

    @Disabled
    @Test
    void testDates() {
        String dateString = "2020-09-12T06:55:00+00:00";
        String dateA = "2020-10-12T05:55:00+00:00";

        String formatter = "ISO_OFFSET_DATE_TIME";
        LocalDate realDate = LocalDate.parse(dateString, ISO_OFFSET_DATE_TIME);
        LocalDate datA = LocalDate.parse(dateA, ISO_OFFSET_DATE_TIME);
        System.out.println(realDate);

        Time t = Time.valueOf(dateA);
        System.out.println(t);
        System.out.println(t);
        System.out.println(t);
        System.out.println(t);
        LocalTime realTime = LocalTime.parse(dateString, ISO_OFFSET_DATE_TIME);
        System.out.println(realTime);
        System.out.println("Is ralTime after datA?: " + realTime.isAfter(LocalTime.parse(dateA, ISO_OFFSET_DATE_TIME)));
        long time = Time.parse(dateA);
        Timestamp a = Timestamp.valueOf(dateString);
        Timestamp b = Timestamp.valueOf(dateA);
        System.out.println("   ........   ");
        System.out.println(time);
        System.out.println("   ........   ");
        System.out.println("is dateString after dateA?: " + a.after(b));
        System.out.println("is dateString before dateA?: " + a.before(b));
        //"ISO_OFFSET_DATE_TIME"
    }

    @Disabled
    @Test
    void time() {
        System.out.println("xdcfgvhbnj");
//        String today = "2020-09-12T06:55:00+00:00";
//        String tomorow = "2020-10-12T05:55:00+00:00";
//        String dateA = "2020-09-12";
//        String dateB = "2020-09-11";
//        String timeA = "06:55:00";
//        String timeB = "07:55:00";
//        LocalTime tA = LocalTime.parse(timeA);
//        LocalTime tB = LocalTime.parse(timeB);
//
//        System.out.println(tA.isAfter(tB));
//        System.out.println(tA.isBefore(tB));
//
//        LocalDate dA = LocalDate.parse(dateA);
//        LocalDate dB = LocalDate.parse(dateB);
//        System.out.println("(A after B?: " + dA.isAfter(dB));
//        System.out.println("(A before B?: " + dA.isBefore(dB));
//
//        LocalTime f1 = LocalTime.parse(today.split("T")[1].split("\\+")[0]);
//        System.out.println(f1);
        String date = "2020-12-14";
        String actual = "2020-09-12T06:55:00+00:00";
        String actual2 = "2020-09-14T09:55:00+00:00";

        String arrSheduled = date + "T" + actual.split("T")[1];
        String depSheduled = date + "T" + actual2.split("T")[1];
        System.out.println(actual);
        System.out.println(arrSheduled);
        System.out.println(actual2);
        System.out.println(depSheduled);

    }

}
