package fetch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.airportFetchSearch.AirportSearchDTO;
import dto.airportFetchSearch.AirportSearchListDTO;
import errorhandling.NotFoundException;
import static fetch.FlightFetcher.realDateToAvailableDate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.HttpUtils;

/**
 *
 * @author Dane
 */
public class AirportSearchFetch {

    private static final String URL = "http://api.aviationstack.com/v1";
    private static Gson GSON = new GsonBuilder().create();

    public ArrayList<AirportSearchDTO> findAirport(Gson gson, ExecutorService threadpool, String search) throws NotFoundException {
        Callable<ArrayList<AirportSearchDTO>> airportSearchTask = new Callable<ArrayList<AirportSearchDTO>>() {
            @Override
            public ArrayList<AirportSearchDTO> call() throws IOException, NotFoundException {
                String results;
                AirportSearchListDTO list;
                ArrayList<AirportSearchDTO> all = new ArrayList();
                results = callForFetch(search);
                list = GSON.fromJson(results, AirportSearchListDTO.class);
                all.addAll(list.getData());
                return all;
            }
        };

        Future<ArrayList<AirportSearchDTO>> futureAirportSearch = threadpool.submit(airportSearchTask);
        ArrayList<AirportSearchDTO> result;

        try {
            result = futureAirportSearch.get(20, TimeUnit.SECONDS);
        } catch (Exception ex) {
            Logger.getLogger(AirportSearchFetch.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new NotFoundException(ex.getMessage());
        }
        return result;
    }

    public static String callForFetch(String search) throws IOException, NotFoundException {
        String key = "f0db19fb49cae5b7349fd4cc157b47e3";
        String keyString = "?access_key=" + key;
        String searchString = "&search=" + search;
        String _url = URL + "/airports" + keyString + searchString;
        String dataString = HttpUtils.fetchData(_url);

        return dataString;
    }
}
