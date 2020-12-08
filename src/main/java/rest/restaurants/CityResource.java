/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.restaurants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CityDTO;

import fetch.zCityFetcher;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.zCityDTO;
import java.io.IOException;
import javax.ws.rs.Produces;



/**
 *
 * @author Bruger
 */
@Path("city")
public class CityResource {
    
    @Context
    private UriInfo context;
    private static final ExecutorService es = Executors.newCachedThreadPool();
    private static zCityFetcher fetcher = new zCityFetcher();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @Path("result")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCity() throws IOException{
        ArrayList<zCityDTO> list= zCityFetcher.getCityResult();
        return GSON.toJson(list);
        
    }
            
}
