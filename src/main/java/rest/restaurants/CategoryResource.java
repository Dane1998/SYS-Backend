package rest.restaurants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CategoryDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import fetch.CategoryFetcher;


@Path("category")
public class CategoryResource {
    
    @Context
    private UriInfo context;
    private static final ExecutorService es = Executors.newCachedThreadPool();
    private static CategoryFetcher fetcher = new CategoryFetcher();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @Path("virk")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJCategory() throws IOException, TimeoutException, InterruptedException, ExecutionException{
        System.out.println("virker det?");
        ArrayList<CategoryDTO>  list = CategoryFetcher.getAllCategories();
        return GSON.toJson(list);
    }

}