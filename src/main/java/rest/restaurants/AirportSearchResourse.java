package rest.restaurants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.FrontAirportDTO;
import dto.airportFetchSearch.AirportSearchDTO;
import errorhandling.API_Exception;
import errorhandling.NotFoundException;
import fetch.AirportSearchFetch;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author Dane
 */
@Path("airport")
public class AirportSearchResourse {

    @Context
    private UriInfo context;

    private static ExecutorService threadPool = Executors.newCachedThreadPool();
    private static AirportSearchFetch FETCHER = new AirportSearchFetch();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public AirportSearchResourse() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String demo() {
        return "{\"msg\":\"Hello in airports\"}";
    }

    /* @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("findAirport")
    public String findAirport(String jsonString) throws NotFoundException, API_Exception {
    String search;
    try {
    JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
    search = json.get("search").getAsString();
    } catch (Exception e) {
    throw new API_Exception("Malformed JSON Suplied", 400, e);
    }
    return GSON.toJson(FETCHER.findAirport(GSON, threadPool, search));
    }*/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("findAirport/{search}")
    public String allAirports(@PathParam("search") String search) throws IOException, NotFoundException {
        ArrayList<AirportSearchDTO> list = FETCHER.findAirport(GSON, threadPool, search);
        return GSON.toJson(list);
    }
}
