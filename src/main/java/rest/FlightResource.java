/*
 * Gruppe 3 SYS
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.AirportDTO;
import dto.FrontAirportDTO;
import facades.FlightFacade;
import fetch.FlightFetcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
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
    private static FlightFetcher fetcher = new FlightFetcher();
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
        // return gson.toJson( fetcher.allAirports());

    }

}
