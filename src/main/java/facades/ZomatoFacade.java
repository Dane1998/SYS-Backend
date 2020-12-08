/*
 * Gruppe 3 SYS
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CategoryDTO;
import dto.CollectionDTO;
import dto.CuisineDTO;
import dto.ZomatoCityDTO;
import dto.zCityDTO;
import entities.Category;
import entities.ZomatoCity;
import errorhandling.NotFoundException;
import fetch.CategoryFetcher;
import fetch.CuisineFetcher;
import fetch.ZomatoFetcher;
import fetch.zCityFetcher;
import java.io.IOException;
import java.net.MalformedURLException;
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

   

    public ZomatoCityDTO getCityData(int cityID) throws IOException, MalformedURLException, NotFoundException {

        //read citi Id from xityRequest json 
        ArrayList<CuisineDTO> cuisines = CuisineFetcher.getCuisines(GSON, threadPool, Integer.toString(cityID) );
        ArrayList<CollectionDTO> collections = new ArrayList();
       ZomatoCityDTO zomatoCity = new ZomatoCityDTO(cityID,cuisines, collections );
         // FETCHER.getListOfCollections(int cityID);

        return zomatoCity;
    }

    private static ArrayList<zCityDTO> scrapCities() {
        ArrayList<zCityDTO> cities = new ArrayList();
        ArrayList<Integer> unusedID = new ArrayList();
        ArrayList<Integer> commonID = new ArrayList();
        int count = 0;
        for (int i = 2690; i < 2700; i++) {
            count++;
            System.out.println("Counr: " + count);
            ArrayList<zCityDTO> singleResult;
            try {
                singleResult = FETCHER.getCityfromZomato(i);

                if (singleResult.size() < 1) {
                    unusedID.add(i);

                }
                if (singleResult.size() > 1) {
                    commonID.add(i);
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

    private void populateCategories() throws IOException {

        emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        ArrayList<CategoryDTO> categories = CategoryFetcher.getAllCategories();
        try {
            em.getTransaction().begin();
            for (CategoryDTO dto : categories) {
                em.persist(new Category(dto));
            }
            em.getTransaction().commit();

        } finally {
            em.close();

        }

    }

    public static void main(String[] args) throws NotFoundException, IOException {

        System.out.println("----------------------------------------------------");
        populateZomatoCities();

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

    public ArrayList<CategoryDTO> getCategories() {
        EntityManager em = emf.createEntityManager();

        ArrayList<CategoryDTO> list = new ArrayList();
        try {
            em.getTransaction().begin();
            TypedQuery<Category> query = em.createQuery("SELECT c from Category c ", Category.class);

            for (Category category : query.getResultList()) {
                list.add(new CategoryDTO(category));
            }
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return list;
    }
    
}
