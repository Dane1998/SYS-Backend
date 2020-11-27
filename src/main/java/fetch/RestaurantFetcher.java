package fetch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.RestaurantDTO;
import dto.RestaurantListDTO;
import dto.dto.RestaurantFetchResult.RestaurantKeeperDTO;
import errorhandling.NotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dane
 */
public class RestaurantFetcher {

    private static final String URL = "https://developers.zomato.com/api/v2.1/search";
    private static Gson GSON = new GsonBuilder().create();

    public ArrayList<RestaurantDTO> getRestaurant(Gson gson, ExecutorService threadPool, String count, String cuisines, String category, String latitude, String longitude, String radius) throws NotFoundException {
        Callable<ArrayList<RestaurantDTO>> restaurantTask = new Callable<ArrayList<RestaurantDTO>>() {
            @Override
            public ArrayList<RestaurantDTO> call() throws IOException {
                String result;
                RestaurantListDTO rl;
                ArrayList<RestaurantDTO> all = new ArrayList();
                result = callForFetch(count, cuisines, category, latitude, longitude, radius);
                rl = GSON.fromJson(result, RestaurantListDTO.class);
                for (RestaurantKeeperDTO keeper : rl.getRestaurants()) {
                    all.add(keeper.getRestaurant());
                }
                return all;
            }
        };

        Future<ArrayList<RestaurantDTO>> futureRestaurant = threadPool.submit(restaurantTask);
        ArrayList<RestaurantDTO> result;
        try {
            result = futureRestaurant.get(20, TimeUnit.SECONDS);
        } catch (Exception ex) {
            Logger.getLogger(RestaurantFetcher.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new NotFoundException(ex.getMessage());
        }
        return result;
    }

    public static String callForFetch(String count, String cuisine, String category, String latitude, String longitude, String radius) throws IOException {
        String countString = "?count=" + count;
        String cuisinesString = "&cuisines=" + cuisine;
        String categoryString = "&category=" + category;
        String latitudeString = "&lat=" + latitude;
        String longitudeString = "&lon=" + longitude;
        String radiusString = "&radius=" + radius; 
        URL url = new URL(URL + countString + latitudeString + longitudeString+ radiusString+ cuisinesString + categoryString );
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", "server");
        con.setRequestProperty("user-key", "864497503a23e7b6ba474e5159324cc9");
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        System.out.println(jsonStr);
        return jsonStr;

    }
}

