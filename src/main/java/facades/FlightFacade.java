package facades;

import dto.AirportDTO;
import dto.CityDTO;
import dto.FrontAirportDTO;
import entities.Airport;
import entities.City;
import fetch.FlightFetcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author magda and daniel
 */
public class FlightFacade {

    private static EntityManagerFactory emf;
    private static FlightFacade instance;

    private FlightFacade() {

    }

    public static FlightFacade getFlightFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FlightFacade();
        }
        return instance;
    }

    /*
    returns list of FrontAirportDTO
     */
    public ArrayList<FrontAirportDTO> allAirports() {
        ArrayList<FrontAirportDTO> all = new ArrayList();
        emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            List<Object[]> results = em.createQuery("SELECT a.id, a.name, a.iata_code, a.country_name, c.city_name, a.latitude, a.longitude   from Airport a JOIN City c ON a.city_iata_code = c.iata_code").getResultList();
            int count=0;
           
            for (Object[] result : results) {
                count++;
                int id = (int) result[0];
                String name = (String) result[1];
                String code = (String) result[2];
                String country = (String) result[3];
                String city = (String) result[4];
                double latitude = (double) result[5];
                double longitude = (double) result[6];
                FrontAirportDTO airport = new FrontAirportDTO(id, name, code, country, city, latitude, longitude);
                all.add(airport);
                System.out.println("count: " + count);
            }
        } finally {
            em.close();
        }

        return all;
    }

    

}
