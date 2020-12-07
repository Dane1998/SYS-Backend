/*
 * Gruppe 3 SYS
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CuisineDTO;
import dto.ZomatoCityDTO;
import fetch.ZomatoFetcher;
import fetch.zCityFetcher;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import javax.persistence.EntityManagerFactory;
import utils.HttpUtils;

/**
 *
 * @author magda
 */
public class ZomatoFacade {

    private static EntityManagerFactory emf;
    private static ZomatoFacade instance;
        private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final ExecutorService threadPool = HttpUtils.getThreadPool();

    private static final ZomatoFetcher FETCHER= ZomatoFetcher.getZomatoFetcher(GSON, threadPool);

    private ZomatoFacade() {
    }

    public static ZomatoFacade getZomatoFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ZomatoFacade();

        }
        return instance;

    }
    
    public String saveZomatoCitiesinDB(){
        String status ="Not saved";
        
        zCityFetcher fetcher = new zCityFetcher();
        return status;
        
    }

    public ZomatoCityDTO getCityData(String cityRequest) {
        
        //read citi Id from xityRequest json 
        int cityID=0;
        ZomatoCityDTO zomatoCity = new ZomatoCityDTO();
     //   ArrayList<CuisineDTO> cuisines = FETCHER.getListOfCuisines(cityID);
      //  FETCHER.getListOfCollections(int cityID);
        
        
        return zomatoCity;
    }
}
