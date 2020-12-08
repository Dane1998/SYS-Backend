/*
 * Gruppe 3 SYS
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.ZomatoCityDTO;
import errorhandling.API_Exception;
import errorhandling.NotFoundException;
import facades.ZomatoFacade;
import fetch.ZomatoFetcher;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.ExecutorService;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;
import utils.HttpUtils;

/**
 * REST Web Service
 *
 * @author magda
 */
@Path("zomato")
public class ZomatoResource {

    private static final ExecutorService threadPool = HttpUtils.getThreadPool();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static ZomatoFetcher FETCHER = ZomatoFetcher.getZomatoFetcher(GSON, threadPool);
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final ZomatoFacade FACADE = ZomatoFacade.getZomatoFacade(EMF);

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ZomatoResource
     */
    public ZomatoResource() {
    }

    /**
     * Retrieves representation of an instance of rest.FlightResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String demo() {
        return "{\"msg\":\"Hello in zomato\"}";
    }

    /**
     * PUT method for updating or creating an instance of ZomatoResource
     *
     * @param content representation for the resource
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/countries")
    public String countries() {

        return GSON.toJson(FACADE.getAllCountries());

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/citylist")
    public String countries(String countryJson) throws API_Exception {
        String country = "";
        try {
            JsonObject json = JsonParser.parseString(countryJson).getAsJsonObject();
            country = json.get("country").getAsString();
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied", 400, e);
        }
        return GSON.toJson(FACADE.getCitiesByCountry(country));

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/categories")
    public String catgories() {

        return GSON.toJson(FACADE.getCategories());

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/citydata")
    public String city(String cityRequest) throws NotFoundException {
        int cityID = 0;
        try {
            JsonObject json = JsonParser.parseString(cityRequest).getAsJsonObject();
            cityID = json.get("city_id").getAsInt();

            ZomatoCityDTO zomatoCity = FACADE.getCityData(cityID);
            return GSON.toJson(zomatoCity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException("We could not get data for given city");
        }

        //return "{\"msg\":\"Not supportd yet\"}";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search")
    public String search(String request) throws NotFoundException, API_Exception {

        int cityID;
        Integer[] collections;
        Integer[] cuisines;
        Integer[] categories;
        double latitude;
        double longitude;
        int radius;

        JsonObject json = JsonParser.parseString(request).getAsJsonObject();

        try {
            cityID = json.get("city_id").getAsInt();
        } catch (Exception e) {
            throw new API_Exception("city_id required", 400, e);
        }

        try {
            JsonArray collJson = json.getAsJsonArray("collections");
            collections = new Integer[collJson.size()];
            for (int i = 0; i < collJson.size(); i++) {
                collections[i] = collJson.get(i).getAsInt();

            }
        } catch (Exception e) {
            collections = new Integer[0];
        }

        try {
            JsonArray cuisJson = json.getAsJsonArray("cuisines");
            cuisines = new Integer[cuisJson.size()];
            for (int i = 0; i < cuisJson.size(); i++) {
                cuisines[i] = cuisJson.get(i).getAsInt();

            }
        } catch (Exception e) {
            cuisines = new Integer[0];
        }

        try {
            JsonArray catJson = json.getAsJsonArray("categories");
            categories = new Integer[catJson.size()];
            for (int i = 0; i < catJson.size(); i++) {
                categories[i] = catJson.get(i).getAsInt();

            }
        } catch (Exception e) {
            categories = new Integer[0];
        }

        try {
            latitude = json.get("latitude").getAsDouble();

        } catch (Exception e) {
            latitude = 999999;

        }

        try {
            longitude = json.get("longitude").getAsDouble();

        } catch (Exception e) {
            longitude = 999999;
        }

        try {
            radius = json.get("radius").getAsInt();
        } catch (Exception e) {
            radius = -1;
        }

        try {
            return GSON.toJson(FETCHER.getRestaurants(cityID, collections, cuisines, categories, latitude, longitude, radius));
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException(e.getMessage());
        }

        // return "{\"msg\":\"Not supportd yet\"}";
    }

}
