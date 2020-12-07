package fetch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.ExecutorService;
import utils.HttpUtils;

/**
 *
 * @author magda and daniel
 */
public class FlightFetcherTest {

    private static final ExecutorService threadPool = HttpUtils.getThreadPool();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static FlightFetcher fetcher = FlightFetcher.getFlightFetcher(GSON, threadPool);
   

}
