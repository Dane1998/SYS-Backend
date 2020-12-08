/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.restaurants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import dto.FrontAirportDTO;
import errorhandling.API_Exception;
import errorhandling.NotFoundException;
import facades.FlightFacade;
import fetch.FlightFetcher;
import fetch.RestaurantFetcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author Dane
 */
@Path("restaurant")
public class RestaurantResource {

    @Context
    private UriInfo context;

    private static ExecutorService threadPool = Executors.newCachedThreadPool();
    private static RestaurantFetcher fetcher = new RestaurantFetcher();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of GenericResource
     */
    public RestaurantResource() {
    }

    /**
     * Retrieves representation of an instance of rest.RestaurantResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String demo() {
        return "{\"msg\":\"Hello in restaurants\"}";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("findRestaurants")
    public String findRestaurants(String jsonString) throws NotFoundException, API_Exception {
        String count;
        String cuisines;
        String category;
        String latitude;
        String longitude;
        String radius;

        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            count = json.get("count").getAsString();
            cuisines = json.get("cuisines").getAsString();
            category = json.get("category").getAsString();
            latitude = json.get("lat").getAsString();
            longitude = json.get("lon").getAsString();
            radius = json.get("radius").getAsString();
            
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied", 400, e);
        }
        return GSON.toJson(fetcher.getRestaurant(GSON, threadPool, count, cuisines, category, latitude, longitude, radius));
    }

}
