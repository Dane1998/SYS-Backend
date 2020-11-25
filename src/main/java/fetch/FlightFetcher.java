/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fetch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.AirportDTO;
import dto.AirportListDTO;
import dto.CityDTO;
import dto.CityListDTO;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author magda og daniel
 */
public class FlightFetcher {

    private final String URL = "http://api.aviationstack.com/v1";
    private Gson GSON = new GsonBuilder().create();
    
    private static FlightFetcher ff = new FlightFetcher();
    
    
    
    public  static void findFlights(Gson gson, ExecutorService threadPool ){
        
    }
    
    
    
    
    
    
    
    
    
    
    

    public String getAirports(int offset) throws MalformedURLException, IOException {
       
        String key = "7749dd3fccb2428159fdfd0710f97584";
        String keyString = "?access_key=" + key;
        String limitString = "&limit=100";
        String offsetString = "&offset=" + offset;

        URL url = new URL(URL + "/airports" + keyString + limitString + offsetString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", "server");

        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        return jsonStr;
    }

    public ArrayList<AirportDTO> getAirportList100(int offset) throws IOException {

       // FlightFetcher ff = new FlightFetcher();
        String response = ff.getAirports(offset);
        AirportListDTO list = GSON.fromJson(response, AirportListDTO.class);
        return list.getData();

    }

    public ArrayList<AirportDTO> allAirports() throws IOException {
      //  FlightFetcher ff = new FlightFetcher();
        ArrayList<AirportDTO> allAirportsList = new ArrayList<>();
        int total = 6471;
        int offset = 0;
        boolean run = true;
        int count = 0;

        while (run) {
            ArrayList<AirportDTO> list = ff.getAirportList100(offset);
            allAirportsList.addAll(list);
            offset += 100;
         
            if (offset >= total) {
                run = false;
            }
            count += 1;
        }

        return allAirportsList;

    }
    
    
    
    
    
    
    public String getCities(int offset) throws ProtocolException, MalformedURLException, IOException{
        String key = "7749dd3fccb2428159fdfd0710f97584";
        String keyString = "?access_key=" + key;
        String limitString = "&limit=100";
        String offsetString = "&offset=" + offset;
        
        URL url = new URL(URL + "/cities" + keyString + limitString + offsetString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", "server");

        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        return jsonStr;
    }
    
    

    public ArrayList<CityDTO> get100Cities(int offset) throws IOException {

        String response = ff.getCities(offset);
        CityListDTO list = GSON.fromJson(response, CityListDTO.class);
        return list.getData();

    }
    
    
    public ArrayList<CityDTO> allCities() throws IOException{
        ArrayList<CityDTO> all = new ArrayList<>();
        int total = 9368;
        int offset=0;
       
        while (offset <= total){
            ArrayList<CityDTO> part = ff.get100Cities(offset);
            all.addAll(part);
            offset+=100;
        }
        return all;
    }
    
    
    

//    public ArrayList<AirportDTO> fakeAirports() {
//        ArrayList<AirportDTO> fake = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            String countryName = "";
//            String airportName = "";
//            String airportCode = "";
//            String citiCode = "";
//
//            Random r = new Random();
//
//            String alphabet = "abcdefghijklmnopqrstuvwxyz";
//            for (int c = 0; c < 5; c++) {
//                char x = alphabet.charAt(r.nextInt(alphabet.length()));
//                countryName += x;
//            }
//            for (int c = 0; c < 5; c++) {
//                char x = alphabet.charAt(r.nextInt(alphabet.length()));
//                airportName += x;
//            }
//            for (int c = 0; c < 3; c++) {
//                char x = alphabet.charAt(r.nextInt(alphabet.length()));
//                airportCode += x;
//            }
//            for (int c = 0; c < 3; c++) {
//                char x = alphabet.charAt(r.nextInt(alphabet.length()));
//                citiCode += x;
//            }
//          ///  AirportDTO airport = new AirportDTO(airportName, airportCode, countryName, citiCode);
//           // fake.add(airport);
//        }
//        System.out.println("Just before return");
//        return fake;
//    }

}
