/*
 * Gruppe 3 SYS
 */
package fetch;

import com.google.gson.Gson;
import dto.CollectionDTO;
import dto.cities.LocationSugDTO;
import dto.zCityDTO;
import errorhandling.NotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
    private static final String ZOMATO_KEY_3 = "864497503a23e7b6ba474e5159324cc9";

    public static ZomatoFetcher getZomatoFetcher(Gson _gson, ExecutorService _threadPool) {
        if (instance == null) {
            GSON = _gson;
            threadPool = _threadPool;
            instance = new ZomatoFetcher();

        }
        return instance;
    }

    //based on Artem's methode
    private static String fetchCity(int cityID) throws MalformedURLException {
        URL url = new URL(URL + "/cities?city_ids=" + cityID);
        String jsonStr = "";
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("User-Agent", "server");
            con.setRequestProperty("user-key", ZOMATO_KEY_3);

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

                    return cityList.getCities();
                }
            }
        };

        Future<ArrayList<zCityDTO>> futureCities = threadPool.submit(getCities);

        ArrayList<zCityDTO> cities = new ArrayList();

        try {
            cities = futureCities.get(2, TimeUnit.SECONDS);

        } catch (Exception ex) {
            Logger.getLogger(FlightFetcher.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new NotFoundException(ex.getMessage());
        }
        return cities;
    }

    public ArrayList<CollectionDTO> getCollections(int cityID) throws NotFoundException {

        Callable< ArrayList<CollectionDTO>> getCollections = new Callable<ArrayList<CollectionDTO>>() {
            @Override
            public ArrayList<CollectionDTO> call() throws Exception {
                String fetchResult = fetchCollections(cityID);
                System.out.println("In getCollections(). call()");
                ArrayList<CollectionDTO> list = new ArrayList();
                if (fetchResult.equals("")) {
                    return new ArrayList<CollectionDTO>();
                } else {
                    CollectionResult result = GSON.fromJson(fetchResult, CollectionResult.class);
                    for (CollectionKeeper col : result.getCollections()) {
                        list.add(col.getCollection());
                        System.out.println("...................List size "+list.size());
                    }
                    return list;
                }
            }
        };
        ArrayList<CollectionDTO> list = new ArrayList();
        Future<ArrayList<CollectionDTO>> futureCollections = threadPool.submit(getCollections);

        try {
            list = futureCollections.get(5, TimeUnit.SECONDS);

        } catch (Exception ex) {
            Logger.getLogger(FlightFetcher.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new NotFoundException(ex.getMessage());
        }

        return list;
    }

    private  String fetchCollections(int cityID) {
        String jsonStr = "";
        try {
            URL url = new URL(URL + "/collections?city_id=" + cityID);
           
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("User-Agent", "server");
            con.setRequestProperty("user-key", ZOMATO_KEY_3);

            Scanner scan;

            scan = new Scanner(con.getInputStream());

            if (scan.hasNext()) {
                jsonStr = scan.nextLine();
            }
            scan.close();

        } catch (Exception ex) {
            Logger.getLogger(ZomatoFetcher.class.getName()).log(Level.SEVERE, null, ex);
            jsonStr = "";
            ex.printStackTrace();
        }
       
        return jsonStr;
    }
   
}

class CollectionKeeper {

    private CollectionDTO collection;

    public CollectionDTO getCollection() {
        return collection;
    }

    public CollectionKeeper(CollectionDTO collection) {
        this.collection = collection;
    }
}

class CollectionResult {

    ArrayList<CollectionKeeper> collections;

    public ArrayList<CollectionKeeper> getCollections() {
        return collections;
    }

    public CollectionResult(ArrayList<CollectionKeeper> collections) {
        this.collections = collections;
    }

}
