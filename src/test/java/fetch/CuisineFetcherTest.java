/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fetch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CuisineDTO;
import errorhandling.NotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bruger
 */
public class CuisineFetcherTest {
    
    
    private static final ExecutorService es = Executors.newCachedThreadPool();
    private static CuisineFetcher fetcher = new CuisineFetcher();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @Test
    public void getAllCuisinetest() throws IOException, MalformedURLException, MalformedURLException, NotFoundException{
       String city_id="280";
        ArrayList<CuisineDTO>  list = CuisineFetcher.getCuisines(GSON, es, city_id);
        
        String name="";
        for (CuisineDTO cat : list) {
            if(cat.getCuisine_id()==84){
                name = cat.getCuisine_name();
                System.out.println(name);
            }
        }
        assertEquals(list.size(), 141);
        assertTrue(name.equals("Russian"));
    }

}
