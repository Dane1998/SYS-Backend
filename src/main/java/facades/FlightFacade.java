package facades;

import dto.FlightDTO;
import dto.FrontAirportDTO;
import dto.RestaurantDTO;
import dto.TripDTO;
import entities.Flight;
import entities.Restaurant;
import entities.Trip;
import entities.User;
import errorhandling.NotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

/**
 *
 * @author magda
 */
public class FlightFacade {

    private static EntityManagerFactory emf;
    private static FlightFacade instance;
    private static final double DEGRE_TO_KM = 111.0;
    private static UserFacade USER_FACADE = UserFacade.getUserFacade(emf);

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
    coded with Daniel
    returns list of FrontAirportDTO
     */
    public ArrayList<FrontAirportDTO> allAirports() {
        System.out.println("In allAirports()");
        ArrayList<FrontAirportDTO> all = new ArrayList();
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            List<Object[]> results = em.createQuery("SELECT a.id, a.name, a.iata_code, a.country_name, c.city_name, a.latitude, a.longitude   from Airport a JOIN City c ON a.city_iata_code = c.iata_code").getResultList();
            int count = 0;

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
            }
        } finally {
            em.close();
        }
        System.out.println("Items on the list: " + all.size());

        return all;
    }

    public String saveTrip(TripDTO dto) throws NotFoundException {
        Trip trip = new Trip();

        for (RestaurantDTO rDTO : dto.getRestaurants()) {
            Restaurant restaurant = new Restaurant(rDTO);

            trip.addRestaurant(restaurant);
        }
        for (FlightDTO fDTO : dto.getFlights()) {
            trip.addFlight(new Flight(fDTO));

        }

        EntityManager em = emf.createEntityManager();
        try {
            User user = em.find(User.class, dto.getUsername());
            if (user == null) {
                throw new NotFoundException("User does not exist in the system");
            } else {

                em.getTransaction().begin();
                trip.setUser(user);
                em.persist(trip);
                em.getTransaction().commit();
                return "The trip is saved on the trip list";
            }
        } finally {
            em.close();
        }

    }

    public ArrayList<TripDTO> getTripsByUser(String userName) throws AuthenticationException, NotFoundException {
        ArrayList<TripDTO> trips = new ArrayList();
        EntityManager em = emf.createEntityManager();

        // User user = USER_FACADE.getVeryfiedUser(username, password);
        try {
            User user = em.find(User.class, userName);
            if (user == null) {
                throw new NotFoundException("User does not exist in the system");
            } else {
                em.getTransaction().begin();

                List<Trip> results = em.createQuery("SELECT t from Trip t where t.user=:user", Trip.class)
                        .setParameter("user", user)
                        .getResultList();
                em.getTransaction().commit();

                for (Trip result : results) {
                    trips.add(new TripDTO(result));

                }

                return trips;
            }
            }finally {
            em.close();
        }

        }
    

    

    public double calculateRadius(FrontAirportDTO airport, RestaurantDTO restaurant) {
        double r = 0;

        double rLat = Double.parseDouble(restaurant.getLocation().getLatitude());
        double rLong = Double.parseDouble(restaurant.getLocation().getLongitude());

        double aLat = airport.getLatitude();
        double aLong = airport.getLongitude();

        double a = Math.pow(aLat - rLat, 2);
        double b = Math.pow(aLong - rLong, 2);
        double d = Math.sqrt(a + b);
        r = d * DEGRE_TO_KM;

        return r;
    }

    public Map<FrontAirportDTO, Double> getAirportsByRestaurant(RestaurantDTO restaurant, double radius) {
        ArrayList<FrontAirportDTO> results = new ArrayList();
        ArrayList<FrontAirportDTO> list = instance.allAirports();
        for (FrontAirportDTO frontAirportDTO : list) {
            if (instance.calculateRadius(frontAirportDTO, restaurant) <= radius) {
                results.add(frontAirportDTO);
            }
        }

        HashMap<FrontAirportDTO, Double> resultmap = new HashMap();
        for (FrontAirportDTO result : results) {
            double r = calculateRadius(result, restaurant);
            resultmap.put(result, r);
        }

        return resultmap;
    }

    public Map<FrontAirportDTO, Double> getNearestAirorts(RestaurantDTO restaurant, int quantity) {
        Map<FrontAirportDTO, Double> mapResults = new HashMap();
        ArrayList<FrontAirportDTO> list = instance.allAirports();
        for (FrontAirportDTO frontAirportDTO : list) {
            double r = calculateRadius(frontAirportDTO, restaurant);
            mapResults.put(frontAirportDTO, r);
        }
        Map<FrontAirportDTO, Double> nearest = new LinkedHashMap();
        Map<FrontAirportDTO, Double> sorted = sortByComparator(mapResults, true);
        for (int i = 0; i < quantity; i++) {
            FrontAirportDTO radius = (FrontAirportDTO) sorted.keySet().toArray()[i];
            Double valuse = sorted.get(radius);
            nearest.put(radius, valuse);
        }

        return nearest;
    }

    static Map<FrontAirportDTO, Double> sortByComparator(Map<FrontAirportDTO, Double> unsortMap, final boolean order) {

        List<Entry<FrontAirportDTO, Double>> list = new LinkedList<Entry<FrontAirportDTO, Double>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<FrontAirportDTO, Double>>() {
            public int compare(Entry<FrontAirportDTO, Double> o1,
                    Entry<FrontAirportDTO, Double> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<FrontAirportDTO, Double> sortedMap = new LinkedHashMap<FrontAirportDTO, Double>();
        for (Entry<FrontAirportDTO, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
