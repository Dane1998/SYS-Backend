/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CategoriesListDTO;
import dto.CategoriesDTO;
import dto.DadDTO;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import utils.HttpUtils;

/**
 *
 * @author Bruger
 */
public class CategoryFetcher {
   // private static final String chuckNorrisAPI = "https://developers.zomato.com/api/v2.1/categories";
    //private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

   /* public static String fetchJokes(ExecutorService es) throws TimeoutException, InterruptedException, ExecutionException {
        long start = System.nanoTime();

        Callable<CategoryDTO> catCall = new Callable<CategoryDTO>() {
            @Override
            public CategoryDTO call() throws Exception {
                String chuck = HttpUtils.fetchData(chuckNorrisAPI);
                CategoryDTO categoryDTO = GSON.fromJson(chuck, CategoryDTO.class);
                return categoryDTO;
            }
        };

        
        
         Future<CategoryDTO> chuckFuture = es.submit(catCall);
         
         
         CategoryDTO chuck = chuckFuture.get(2,TimeUnit.SECONDS);
         
         
         
         CategoryDTO category = new CategoryDTO();
         String json = GSON.toJson(category);
                    
        return json;
    }*/
    private final String URL = "https://developers.zomato.com/api/v2.1/categories";
    private Gson GSON = new GsonBuilder().create();

    public String getCategoriess() throws MalformedURLException, IOException {
       
      
        
       

        URL url = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", "server");
        con.setRequestProperty("user-key", "54348244fce52e40658f0500d58769b0");
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        System.out.println(jsonStr);
        return jsonStr;
    }
    public List<CategoriesDTO> getList() throws IOException{
        CategoryFetcher cf = new CategoryFetcher();
        
        
        
        String categoryString = cf.getCategoriess();
        System.out.println("hallo "+categoryString);
        CategoriesListDTO categories = GSON.fromJson(categoryString,CategoriesListDTO.class);
        System.out.println("categories " + categories);
        return categories.getCategories();
    }
}
    

