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
import java.time.LocalTime;
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
                    manipulateDate(flightDTO, date);
                    
                    flightDTO.setID();
                }
                return all;
            }

        };
        Future<ArrayList<FlightDTO>> futureFlights = threadPool.submit(flightsTask);

        ArrayList<FlightDTO> result;
        try {
            result = futureFlights.get(5, TimeUnit.SECONDS);
        } catch (Exception ex) {
            Logger.getLogger(FlightFetcher.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new API_Exception(ex.getMessage(), 408);

        }
        return result;
    }
    private void manipulateDate(FlightDTO flight, String date){
        String arrSheduled=date+"T"+flight.getArrival().getScheduled().split("T")[1];
        String depSheduled=date+"T"+flight.getDeparture().getScheduled().split("T")[1];
        flight.getDeparture().setScheduled(depSheduled);
        flight.getArrival().setScheduled(arrSheduled);
        
    }
    
    

    public  String fetchFlights(int offset, String dep_iata, String arr_iata, String date) throws IOException, API_Exception {
        String key = "f0db19fb49cae5b7349fd4cc157b47e3";
        String keyString = "?access_key=" + key;
        String limitString = "&limit=100";
        String offsetString = "&offset=" + offset;
        String statusString = "&flight_status=scheduled";

        String dep_iataString = "";
        if (dep_iata != null) {
            dep_iataString = "&dep_iata=" + dep_iata;
        }
        String arr_iataString = "";
        if (arr_iata != null) {
            arr_iataString = "&arr_iata=" + arr_iata;
        }

        String dateString = "&flight_date=" + realDateToAvailableDate(date); // When we can use that option we need to add this string to the URL like the other strings
//  http://api.aviationstack.com/v1/flights?access_key=f0db19fb49cae5b7349fd4cc157b47e3&flight_date=2020-11-30&flight_status=scheduled&arr_iata=SVG

        String _url = URL + "/flights" + keyString + dateString + limitString + offsetString + statusString + dep_iataString + arr_iataString;
        String dataString = HttpUtils.fetchData(_url);
        System.out.println(_url);
        System.out.println(dataString);
        return dataString;
    }

    public static String callForFetch(int offset, String dep_iata, String arr_iata, String date) throws IOException, API_Exception {
        String key = "f0db19fb49cae5b7349fd4cc157b47e3";
        String keyString = "?access_key=" + key;
        String limitString = "&limit=100";
        String offsetString = "&offset=" + offset;
        String statusString = "&flight_status=scheduled";
        String dep_iataString = "&dep_iata=" + dep_iata;
        String arr_iataString = "&arr_iata=" + arr_iata;

        String dateString = "&flight_date=" + realDateToAvailableDate(date);
//  http://api.aviationstack.com/v1/flights?access_key=f0db19fb49cae5b7349fd4cc157b47e3&flight_date=2020-11-30&flight_status=scheduled&arr_iata=SVG
        String _url = URL + "/flights" + keyString + dateString + limitString + offsetString + statusString + dep_iataString + arr_iataString;
        String dataString = HttpUtils.fetchData(_url);

        return dataString;
    }

    public ArrayList<FlightDTO> findAllFlightsFromTO(String dep_iata, String arr_iata, String date) throws NotFoundException, API_Exception {

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
                    results = fetchFlights(offset, dep_iata, arr_iata, date);
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
                    manipulateDate(flightDTO, date);
                    flightDTO.setID();
                }
                return all;
            }

        };
        Future<ArrayList<FlightDTO>> futureFlights = threadPool.submit(flightsTask);

        ArrayList<FlightDTO> result;
        try {
            result = futureFlights.get(5, TimeUnit.SECONDS);
        } catch (Exception ex) {
            Logger.getLogger(FlightFetcher.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new API_Exception(ex.getMessage(), 408);

        }
        return result;
    }

    public ArrayList<FlightSetDTO> setFLights(String dep_iata, String arr_iata, String date) throws NotFoundException, API_Exception {
        ArrayList<FlightSetDTO> list = new ArrayList();
        ArrayList<FlightDTO> froms = findAllFlightsFromTO(dep_iata, null, date);
        LocalDate realDate = LocalDate.parse(date);
        LocalDate nextDay = realDate.plusDays(1);
        String nextDayString = nextDay.toString();
        ArrayList<FlightDTO> tos = findAllFlightsFromTO(null, arr_iata, date);
        ArrayList<FlightDTO> tosNextDay = findAllFlightsFromTO(null, arr_iata, nextDayString);
        //tos.addAll(tosNextDay);

        
         
        for (FlightDTO from : froms) {
            if (from.getArrival().getIata().equals(arr_iata)) {
                FlightSetDTO set = new FlightSetDTO();
                set.addFlight(from);
                set.setID();
                list.add(set);
            }

            for (FlightDTO to : tos) {

                if (from.getArrival().getIata().equals(to.getDeparture().getIata())
                        && isDepAfterArr(to.getDeparture().getScheduled(), from.getArrival().getScheduled())) {
                    FlightSetDTO set = new FlightSetDTO();

                    set.addFlight(from);
                    set.addFlight(to);
                    set.setID();
                    list.add(set);
                }
            }
        }

        for (FlightDTO from : froms) {

            for (FlightDTO to : tosNextDay) {

                if (from.getArrival().getIata().equals(to.getDeparture().getIata())) {
                    FlightSetDTO set = new FlightSetDTO();

                    set.addFlight(from);
                    set.addFlight(to);
                    set.setID();
                    list.add(set);
                }
            }
        }
        
//        for (FlightDTO flightDTO : tosNextDay) {
//            if(flightDTO.getDeparture().getIata().equals(dep_iata)){
//            FlightSetDTO set = new FlightSetDTO();
//                set.addFlight(flightDTO);
//                set.setID();
//                list.add(set);}
//        }

        return list;
    }

    public boolean isDepAfterArr(String dep, String arr) {
        System.out.println("Departure: " + dep + "    Arrival: " + arr);
        String depStr = dep.split("T")[1].split("\\+")[0];
        String arrStr = arr.split("T")[1].split("\\+")[0];
        LocalTime depTime = LocalTime.parse(depStr);
        LocalTime arrTime = LocalTime.parse(arrStr);
        System.out.println("depTime.isAfter(arrTime)??: " + depTime.isAfter(arrTime));
        return depTime.isAfter(arrTime);

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
