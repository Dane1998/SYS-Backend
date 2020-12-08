/*
 * Gruppe 3 SYS
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CuisineDTO;
import dto.ZomatoCityDTO;
import dto.zCityDTO;
import entities.ZomatoCity;
import errorhandling.NotFoundException;
import fetch.ZomatoFetcher;
import fetch.zCityFetcher;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;
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
    private static final int CITY_QUANTITY = 11903;

    private static final ZomatoFetcher FETCHER = ZomatoFetcher.getZomatoFetcher(GSON, threadPool);

    private ZomatoFacade() {
    }

    public static ZomatoFacade getZomatoFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ZomatoFacade();

        }
        return instance;

    }

    public String saveZomatoCitiesinDB() {
        String status = "Not saved";

        zCityFetcher fetcher = new zCityFetcher();
        return status;

    }

    public ZomatoCityDTO getCityData(String cityRequest) {

        //read citi Id from xityRequest json 
        int cityID = 0;
        ZomatoCityDTO zomatoCity = new ZomatoCityDTO();
        //   ArrayList<CuisineDTO> cuisines = FETCHER.getListOfCuisines(cityID);
        //  FETCHER.getListOfCollections(int cityID);

        return zomatoCity;
    }

    public static ArrayList<zCityDTO> scrapCities() {
        ArrayList<zCityDTO> cities = new ArrayList();
        ArrayList<Integer> unusedID = new ArrayList();
        ArrayList<Integer> commonID = new ArrayList();

        for (int i = 1758; i < 2700; i++) {

            ArrayList<zCityDTO> singleResult;
            try {
                singleResult = FETCHER.getCityfromZomato(i);

                if (singleResult.size() < 1) {

                }
                if (singleResult.size() > 1) {

                }
                System.out.println("CIty id: " + i);
                if (singleResult.size() > 0) {
                    cities.add(singleResult.get(0));
                }
            } catch (NotFoundException ex) {
                Logger.getLogger(ZomatoFacade.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Exception!");
                ex.printStackTrace();
                break;

            }

        }
        System.out.println("Unused ID:");
        System.out.println(unusedID);
        System.out.println("Common ID:");
        System.out.println(commonID);
        System.out.println("All cities scrapped: " + cities.size());

        return cities;
    }

    private static void populateZomatoCities() throws NotFoundException {
        emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        ArrayList<zCityDTO> cities = scrapCities();
        System.out.println("In populate: cities.size()=" + cities.size());
        try {
            em.getTransaction().begin();
            for (zCityDTO city : cities) {
                em.persist(new ZomatoCity(city));
            }
            em.getTransaction().commit();

        } finally {
            em.close();

        }

    }

    public static void main(String[] args) throws NotFoundException {

        System.out.println("----------------------------------------------------");
        // populateZomatoCities();
        // ArrayList<zCityDTO> list = getCitiesByCountry("Australia");
        // System.out.println("list.size: " + list.size());
        // getAllCountries();
    }

    public ArrayList<zCityDTO> getCitiesByCountry(String country) {
        EntityManager em = emf.createEntityManager();
        ArrayList<zCityDTO> cities = new ArrayList();

        try {
            em.getTransaction().begin();
            TypedQuery<ZomatoCity> query = em.createQuery("SELECT c from ZomatoCity c where c.country_name=:country", ZomatoCity.class)
                    .setParameter("country", country);
            for (ZomatoCity zomatoCity : query.getResultList()) {
                cities.add(new zCityDTO(zomatoCity));
            }
            em.getTransaction().commit();

        } finally {
            em.close();

        }
        return cities;
    }

    public ArrayList<String> getAllCountries() {
        EntityManager em = emf.createEntityManager();
        ArrayList<String> countries = new ArrayList();
        try {
            em.getTransaction().begin();
            TypedQuery<String> query = em.createQuery("SELECT c.country_name from ZomatoCity c group by c.country_name", String.class);

            for (String country : query.getResultList()) {
                countries.add(country);
            }
            em.getTransaction().commit();

        } finally {
            em.close();
        }

        return countries;
    }
    
    

}
