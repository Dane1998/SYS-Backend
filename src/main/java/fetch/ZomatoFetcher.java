/*
 * Gruppe 3 SYS
 */
package fetch;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import dto.cities.LocationSugDTO;
import dto.zCityDTO;
import errorhandling.NotFoundException;
import static fetch.zCityFetcher.getCities;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author magda
 */
public class ZomatoFetcher {

    private static final String URL = "https://developers.zomato.com/api/v2.1"; ///cities?city_ids=1
    private static final int CITY_QUANTITY = 11903;
    private static Gson GSON;
    private static ZomatoFetcher instance;
    private static ExecutorService threadPool;
    private static final String ZOMATO_KEY = "b56b72e22de9832e0798ab27cafe6a1a";

    public static ZomatoFetcher getZomatoFetcher(Gson _gson, ExecutorService _threadPool) {
        if (instance == null) {
            GSON = _gson;
            threadPool = _threadPool;
            instance = new ZomatoFetcher();

        }
        return instance;
    }

    //based on Artem's methode
    public static String fetchCity(int cityID) throws MalformedURLException, ProtocolException, IOException {
        URL url = new URL(URL + "/cities?city_ids=" + cityID);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", "server");
        con.setRequestProperty("user-key", ZOMATO_KEY);

        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();

        return jsonStr;
    }

    public ArrayList<zCityDTO> getAllCitiesfromZomato(int cityID) throws NotFoundException {

        Callable<ArrayList<zCityDTO>> getCities = new Callable<ArrayList<zCityDTO>>() {
            @Override
            public ArrayList<zCityDTO> call() throws Exception {

                LocationSugDTO cityList = GSON.fromJson(fetchCity(cityID), LocationSugDTO.class);
                return cityList.getCities();
            }

        };

        Future<ArrayList<zCityDTO>> futureCities = threadPool.submit(getCities);
        
        ArrayList<zCityDTO> cities;
        try{
            cities = futureCities.get(2,TimeUnit.SECONDS);
       } catch (Exception ex) {
            Logger.getLogger(FlightFetcher.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new NotFoundException(ex.getMessage());
        }

        return cities;
    }

//fetch cuisines and return list of them
//    public ArrayList<CuisineDTO> getListOfCuisines(int cityID) {
//        ArrayList<CuisineDTO> cuisines = new ArrayList();
//        return cuisines;
//    }
    //fetch collections and return list of them  
    public ArrayList<Object> getListOfCollections(int cityID) {
        ArrayList<Object> collections = new ArrayList();
        return collections;
    }

}
/*
6774318
 */
