/*
 * Gruppe 3 SYS
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.FrontAirportDTO;
import dto.TripDTO;
import errorhandling.API_Exception;
import errorhandling.NotFoundException;
import facades.FlightFacade;
import fetch.FlightFetcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author magda
 */
@Path("flight")
public class FlightResource {

    @Context
    private UriInfo context;

    // private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static ExecutorService threadPool = Executors.newCachedThreadPool();
    private static FlightFetcher FETCHER = new FlightFetcher();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final FlightFacade FACADE = FlightFacade.getFlightFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of FlightResource
     */
    public FlightResource() {
    }

    /**
     * Retrieves representation of an instance of rest.FlightResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String demo() {
        return "{\"msg\":\"Hello in flights\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("allairports")
    public String allAirports() throws IOException {
        System.out.println("In allAirports point");
        ArrayList<FrontAirportDTO> list = FACADE.allAirports();
        return GSON.toJson(list);
        // return gson.toJson( FETCHER.allAirports());

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Path("findflights")
    public String findFlights(String jsonString) throws NotFoundException, API_Exception {
        String depCode;
        String arrCode;
        String date;

        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            depCode = json.get("dep_code").getAsString();
            arrCode = json.get("arr_code").getAsString();
            date = json.get("date").getAsString();
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied", 400, e);
        }
        System.out.println("1: dep: " + depCode + ", arr: " + arrCode + " date: " + date);
        return GSON.toJson(FETCHER.findFlights(GSON, threadPool, depCode, arrCode, date));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("savetrip")
    public String saveTrip(String jsonString) throws API_Exception, NotFoundException {
        System.out.println("In endpoint save trip");
        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
        TripDTO trip;

        try {
            trip = GSON.fromJson(json, TripDTO.class);
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied", 400, e);
        }
        String msg = FACADE.saveTrip(trip);

        return "{\"msg\":\""+msg+"\"}";
    }

}
