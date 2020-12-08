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
    private static Gson GSON;
    private static ZomatoFetcher instance;
    private static ExecutorService threadPool;
    private static final String ZOMATO_KEY = "b56b72e22de9832e0798ab27cafe6a1a";
    private static final String ZOMATO_KEY_1 = "6f8518d08cbe8cf8158a2680714232b0";
    private static final String ZOMATO_KEY_2 = "54348244fce52e40658f0500d58769b0";

    public static ZomatoFetcher getZomatoFetcher(Gson _gson, ExecutorService _threadPool) {
        if (instance == null) {
            GSON = _gson;
            threadPool = _threadPool;
            instance = new ZomatoFetcher();

        }
        return instance;
    }

    //based on Artem's methode
    public static String fetchCity(int cityID) throws MalformedURLException {
        URL url = new URL(URL + "/cities?city_ids=" + cityID);
        String jsonStr = "";
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("User-Agent", "server");
            con.setRequestProperty("user-key", "b56b72e22de9832e0798ab27cafe6a1a");

            Scanner scan;

            scan = new Scanner(con.getInputStream());

            if (scan.hasNext()) {
                jsonStr = scan.nextLine();
            }
            scan.close();

            System.out.println("Fetched data: \n " + jsonStr);
        } catch (IOException ex) {
            Logger.getLogger(ZomatoFetcher.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Ommited ID: " + cityID);
            jsonStr = "";
        }
        return jsonStr;
    }

    public ArrayList<zCityDTO> getCityfromZomato(int cityID) throws NotFoundException {

        Callable<ArrayList<zCityDTO>> getCities = new Callable<ArrayList<zCityDTO>>() {
            @Override
            public ArrayList<zCityDTO> call() throws Exception {
                String fetchresult = fetchCity(cityID);
                if (fetchresult.equals("")) {
                    return new ArrayList<zCityDTO>();
                } else {
                    LocationSugDTO cityList = GSON.fromJson(fetchresult, LocationSugDTO.class);
                    System.out.println("cityList:");
                    System.out.println(cityList.toString());
                    return cityList.getCities();
                }
            }
        };
        
        Future<ArrayList<zCityDTO>> futureCities = threadPool.submit(getCities);

        ArrayList<zCityDTO> cities;

        try {
            cities = futureCities.get(2, TimeUnit.SECONDS);
            
        } catch (Exception ex) {
            Logger.getLogger(FlightFetcher.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new NotFoundException(ex.getMessage());
        }
        return cities;
    } //fetch cuisines and return list of them
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
