/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fetch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.cities.LocationSugDTO;

import dto.zCityDTO;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Bruger
 */
public class zCityFetcher {
    
    private static final String URL = "https://developers.zomato.com/api/v2.1/cities";
    private static Gson GSON = new GsonBuilder().create();
    
    public static String getCities() throws MalformedURLException, IOException {
        
        String cityID = "?q=NewYork";
        
        
        URL url = new URL(URL+cityID);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", "server");
        con.setRequestProperty("user-key", "54348244fce52e40658f0500d58769b0");
        
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        System.out.println(con);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX"+jsonStr);
        return jsonStr;
    }
    
     public static ArrayList<zCityDTO> getCityResult () throws IOException{
        String cityString = getCities();
        LocationSugDTO fetchedCities = GSON.fromJson(cityString, LocationSugDTO.class);
        
        return fetchedCities.getCities();
        
        
        
        /*System.out.println("cityString:");
        System.out.println(cityString);
        
        LocationSugDTO fetchedCities = GSON.fromJson(cityString, LocationSugDTO.class);
        
        ArrayList<zCityDTO> allRes = new ArrayList();
        allRes.add(fetchedCities);
       
                
        
        return allRes;*/
    }
    
}
