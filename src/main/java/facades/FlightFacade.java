package facades;

import dto.AirportDTO;
import dto.CityDTO;
import entities.Airport;
import entities.City;
import fetch.FlightFetcher;
import java.io.IOException;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author magda and daniel
 */
public class FlightFacade {

    private  EntityManagerFactory emf;
    private  FlightFacade instance;
    private  FlightFetcher fetcher = new FlightFetcher();

    private FlightFacade() {

    }

    public  FlightFacade getFlightFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FlightFacade();
        }
        return instance;
    }

    public  String populateAirports() throws IOException {
        String msg = "Not persisted maaaaan";
        ArrayList<AirportDTO> allAirportsDTO = fetcher.allAirports();
        emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        int count = 0;
        try {
            for (AirportDTO apDTO : allAirportsDTO) {
                Airport ap = new Airport(apDTO);
                em.getTransaction().begin();
                em.persist(ap);
                count++;
                em.getTransaction().commit();
            }
            msg = "Persisted " + count + " airports";
        } finally {
            em.close();
        }

        return msg;
    }

    public  void main(String[] args) throws IOException {
        System.out.println(populateCities());
        System.out.println(populateAirports());
    }
    
    public  String populateCities() throws IOException {
        String msg ="Not populated";
        ArrayList<CityDTO> allCities = fetcher.allCities();
        System.out.println("in populatecieties");
        
        emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        int count = 0;
        try {
            for (CityDTO ctDTO : allCities) {
                City ct  = new City(ctDTO);
                em.getTransaction().begin();
                em.persist(ct);
                count++;
                em.getTransaction().commit();
            }
            msg = "Persisted " + count + " cities";
        } finally {
            em.close();
        }
        
        
        return msg;
    }

}
