/*
 * Gruppe 3 SYS
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.ZomatoCityDTO;
import errorhandling.API_Exception;
import facades.ZomatoFacade;
import fetch.ZomatoFetcher;
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
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/citydata")
    public String city(String cityRequest) {
//        ZomatoCityDTO zomatoCity=new ZomatoCityDTO();
//        zomatoCity=FACADE.getCityData(cityRequest);
//        return GSON.toJson(zomatoCity);
        return "{\"msg\":\"Not supportd yet\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/countries")
    public String countries() {
        
        return GSON.toJson(FACADE.getAllCountries());

    }
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/citiylist")
    public String countries(String countryJson) throws API_Exception {
       String country="";
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
    
    
}
