/*
 * Gruppe 3 SYS
 */
package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.AirportDTO;
import dto.AirportListDTO;
import dto.CityDTO;
import dto.CityListDTO;
import entities.Airport;
import entities.City;
import fetch.FlightFetcher;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author magda
 */
public class PopulateDB {
     private static final String URL = "http://api.aviationstack.com/v1";
    private static Gson GSON = new GsonBuilder().create();
    private static EntityManagerFactory emf;

    private static FlightFetcher ff = new FlightFetcher();

    public static String getAirports(int offset) throws MalformedURLException, IOException {

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

    public static ArrayList<AirportDTO> getAirportList100(int offset) throws IOException {

        // FlightFetcher ff = new FlightFetcher();
        String response = getAirports(offset);
        AirportListDTO list = GSON.fromJson(response, AirportListDTO.class);
        return list.getData();

    }

    public static ArrayList<AirportDTO> allAirports() throws IOException {
        //  FlightFetcher ff = new FlightFetcher();
        ArrayList<AirportDTO> allAirportsList = new ArrayList<>();
        int total = 6471;
        int offset = 0;
        boolean run = true;
        int count = 0;

        while (run) {
            ArrayList<AirportDTO> list = getAirportList100(offset);
            allAirportsList.addAll(list);
            offset += 100;

            if (offset >= total) {
                run = false;
            }
            count += 1;
        }

        return allAirportsList;

    }

    public static String getCities(int offset) throws ProtocolException, MalformedURLException, IOException {
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

    public static ArrayList<CityDTO> get100Cities(int offset) throws IOException {

        String response = getCities(offset);
        CityListDTO list = GSON.fromJson(response, CityListDTO.class);
        return list.getData();

    }

    public static ArrayList<CityDTO> allCities() throws IOException {
        ArrayList<CityDTO> all = new ArrayList<>();
        int total = 9368;
        int offset = 0;

        while (offset <= total) {
            ArrayList<CityDTO> part = get100Cities(offset);
            all.addAll(part);
            offset += 100;
        }
        return all;
    }
    
    public static String populateAirports() throws IOException {
        String msg = "Not persisted maaaaan";
        ArrayList<AirportDTO> allAirportsDTO = allAirports();
        emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        int count = 0;
        try {
            for (AirportDTO apDTO : allAirportsDTO) {
                Airport ap = new Airport(apDTO);
                em.getTransaction().begin();
                em.persist(ap);
                count++;
                em.getTransaction().commit();
            }
            msg = "Persisted " + count + " airports";
        } finally {
            em.close();
        }

        return msg;
    }

    public static String populateCities() throws IOException {
        String msg = "Not populated";
        ArrayList<CityDTO> allCities = allCities();

        emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        int count = 0;
        try {
            for (CityDTO ctDTO : allCities) {
                City ct = new City(ctDTO);
                em.getTransaction().begin();
                em.persist(ct);
                count++;
                em.getTransaction().commit();
            }
            msg = "Persisted " + count + " cities";
        } finally {
            em.close();
        }

        return msg;
    }

}
