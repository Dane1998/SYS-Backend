/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import dto.CuisineDTO;
import errorhandling.API_Exception;
import errorhandling.NotFoundException;

import fetch.CuisineFetcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import sun.nio.ch.ThreadPool;

/**
 *
 * @author Bruger
 */
    @Path("cuisine")
public class CuisineResource {


    
    @Context
    private UriInfo context;
    private static final ExecutorService es = Executors.newCachedThreadPool();
    private static CuisineFetcher fetcher = new CuisineFetcher();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @Path("result{city_id}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
   public String findFlights(String jsonString) throws NotFoundException, API_Exception, IOException {
    
        String city_id;

        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            city_id = json.get("city_id").getAsString();
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied", 400, e);
        }

        return GSON.toJson(fetcher.getCuisines(GSON, es, city_id));
    }

            
}
