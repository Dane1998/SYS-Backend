/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fetch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.FlightDTO;
import dto.FlightSetDTO;
import dto.flightFetchResult.FlightsListDTO;
import errorhandling.API_Exception;
import errorhandling.NotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.HttpUtils;

/**
 *
 * @author magda og daniel
 */
public class FlightFetcher {

    private static final String URL = "http://api.aviationstack.com/v1";
    private static Gson GSON;// = new GsonBuilder().create();
    private static FlightFetcher instance;
    private static ExecutorService threadPool;

    private FlightFetcher() {
    }

    public static FlightFetcher getFlightFetcher(Gson _gson, ExecutorService _threadPool) {
        if (instance == null) {
            GSON = _gson;
            threadPool = _threadPool;
            instance = new FlightFetcher();

        }
        return instance;
    }

    public ArrayList<FlightSetDTO> findFlightSets(String dep_iata, String arr_iata, String date, int stops) throws NotFoundException, API_Exception {
        ArrayList<FlightSetDTO> setList = new ArrayList();
        ArrayList<FlightDTO> list = findFlights(dep_iata, arr_iata, date);
        for (FlightDTO flightDTO : list) {
            FlightSetDTO set = new FlightSetDTO();
            set.addFlight(flightDTO);
            set.setID();
            setList.add(set);
        }
        return setList;
    }

    public ArrayList<FlightDTO> findFlights(String dep_iata, String arr_iata, String date) throws NotFoundException, API_Exception {

        Callable<ArrayList<FlightDTO>> flightsTask = new Callable<ArrayList<FlightDTO>>() {
            @Override
            public ArrayList<FlightDTO> call() throws Exception {
                int offset = 0;
                int total = 0;
                String results;
                FlightsListDTO list;
                ArrayList<FlightDTO> all = new ArrayList();
                while (offset <= total) {
                    System.out.println("2:   date: " + date);
                    results = callForFetch(offset, dep_iata, arr_iata, date);
                    list = GSON.fromJson(results, FlightsListDTO.class);
                    Set<FlightDTO> set = new LinkedHashSet<>(list.getData());
                    all.addAll(set);
                    total = list.getPagination().getTotal();
                    offset += 100;
                    System.out.println("offset: " + offset + " results: " + all.size());

                }
                //set the real date back on flights
                for (FlightDTO flightDTO : all) {
                    flightDTO.setFlight_date(date);
                    flightDTO.setID();
                }
                return all;
            }

        };
        Future<ArrayList<FlightDTO>> futureFlights = threadPool.submit(flightsTask);

        ArrayList<FlightDTO> result;
        try {
            result = futureFlights.get(5, TimeUnit.SECONDS);
        } catch (Exception ex ) {
            Logger.getLogger(FlightFetcher.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new API_Exception(ex.getMessage(), 408);

        
        }
        return result;
    }

    public static String callForFetch(int offset, String dep_iata, String arr_iata, String date) throws IOException, API_Exception {
        String key = "7749dd3fccb2428159fdfd0710f97584";
        String keyString = "?access_key=" + key;
        String limitString = "&limit=100";
        String offsetString = "&offset=" + offset;
        String statusString = "&flight_status=scheduled";
        String dep_iataString = "&dep_iata=" + dep_iata;
        String arr_iataString = "&arr_iata=" + arr_iata;

        String dateString = "&flight_date=" + realDateToAvailableDate(date); // When we can use that option we need to add this string to the URL like the other strings

        String _url = URL + "/flights" + keyString + limitString + offsetString + statusString + dep_iataString + arr_iataString;
        String dataString = HttpUtils.fetchData(_url);

        return dataString;
    }

    public static String realDateToAvailableDate(String realDateString) throws API_Exception {

        LocalDate realDate = LocalDate.parse(realDateString);
        LocalDate availableDate;
        LocalDate now = LocalDate.now();
        if (realDate.isBefore(now)) {
            throw new API_Exception("No data available for flights from before today", 408);
        } else if (realDate.isBefore(now.plusMonths(3))) {
            availableDate = realDate.minusMonths(3);
        } else if (realDate.isBefore(now.plusMonths(6))) {
            availableDate = realDate.minusMonths(6);
        } else if (realDate.isBefore(now.plusMonths(9))) {
            availableDate = realDate.minusMonths(9);
        } else if (realDate.isBefore(now.plusMonths(12))) {
            availableDate = realDate.minusMonths(12);
        } else {
            throw new API_Exception("No flights available for given date. Search for flights within one year from today", 408);

        }
        return availableDate.toString();
    }

}
