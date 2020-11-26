/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fetch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CategoryDTO;
import dto.categoryFetchResult.AllCategoriesDTO;
import dto.categoryFetchResult.CategoryKeeperDTO;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Artem and Magda
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
    private static final String URL = "https://developers.zomato.com/api/v2.1/categories";
    private static Gson GSON = new GsonBuilder().create();

    public static String getCategoriess() throws MalformedURLException, IOException {
       
      
        
       

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
    
//    public List<CategoriesListDTO> getList() throws IOException{
//        CategoryFetcher cf = new CategoryFetcher();
//        
//        
//        String categoryString = cf.getCategoriess();
//        System.out.println("hallo "+categoryString);
//        
//        CategoriesListDTO categories = GSON.fromJson(categoryString,CategoriesListDTO.class);
//        
//        ArrayList<CategoriesListDTO> a = new ArrayList();
//        
//        a.add(categories);
//        
//        AllCategoriesListDTO allCategories = new AllCategoriesListDTO(a);
//        
//        
//        
//        System.out.println("categories " + categories);
//        return allCategories.getAllCategories();
//    }
    
    public static ArrayList<CategoryDTO> getAllCategories () throws IOException{
        String categoriesString = getCategoriess();
        System.out.println("categoriesString:");
        System.out.println(categoriesString);
        
        AllCategoriesDTO fetchedCategories = GSON.fromJson(categoriesString, AllCategoriesDTO.class);
        
        ArrayList<CategoryDTO> allCategories = new ArrayList();
        for (CategoryKeeperDTO keeper : fetchedCategories.getCategories()) {
            allCategories.add(keeper.getCategory());
            
        }
        return allCategories;
    }
}
    