/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fetch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CuisineDTO;
import dto.CuisineListDTO;
import dto.FlightDTO;
import dto.cities.LocationSugDTO;
import errorhandling.NotFoundException;
import static fetch.zCityFetcher.getCities;
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
import utils.HttpUtils;

/**
 *
 * @author Bruger
 */
public class CuisineFetcher {

    private static final String URL = "https://developers.zomato.com/api/v2.1/cuisines";
    private static Gson GSON = new GsonBuilder().create();

    public ArrayList<CuisineDTO> getCuisines(Gson gson, ExecutorService threadPool, String city_id) throws MalformedURLException, IOException, NotFoundException {
        Callable<ArrayList<CuisineDTO>> cuisineTask = new Callable<ArrayList<CuisineDTO>>() {
            @Override
            public ArrayList<CuisineDTO> call() throws IOException {
                String results;
                CuisineListDTO cl;

                ArrayList<CuisineDTO> all = new ArrayList();
                results = callForFetch(city_id);
                cl = GSON.fromJson(results, CuisineListDTO.class);
                all.addAll(cl.getCuisines());
                return all ; 
            }
         
        
                
    };
        Future<ArrayList<CuisineDTO>> futureCuisines = threadPool.submit(cuisineTask);
        
        ArrayList<CuisineDTO> result;
        try {
            result = futureCuisines.get(20, TimeUnit.SECONDS);
        } catch (Exception ex) {
            Logger.getLogger(FlightFetcher.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new NotFoundException(ex.getMessage());
        }
        return result;
    }

        

    public static String callForFetch(String city_id) throws IOException {
       // String key = "54348244fce52e40658f0500d58769b0";
       // String keyString = "?user-key=" + key;
        String city_idString = city_id;
        String _url = URL + city_idString;

        return HttpUtils.fetchData(_url);
    }

  
}