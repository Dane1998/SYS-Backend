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
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author magda
 */
public class FlightFacade {

    private static EntityManagerFactory emf;
    private static FlightFacade instance;
    private static final double DEGRE_TO_KM=111.0;

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
    
    public double calculateRadius(FrontAirportDTO airport,RestaurantDTO restaurant ){
        double r=0;
        
        double rLat=Double.parseDouble(restaurant.getLocation().getLatitude());
        double rLong=Double.parseDouble(restaurant.getLocation().getLongitude());
       
        double aLat =airport.getLatitude();
        double aLong = airport.getLongitude();
        
        double a=Math.pow(aLat-rLat, 2);
        double b=Math.pow(aLong-rLong, 2);
        double d=Math.sqrt(a+b);
        r=d*DEGRE_TO_KM;
        
        return r;
    }
    
    public HashMap<Double, FrontAirportDTO> getAirportsByRestaurant(RestaurantDTO restaurant, double radius){
        ArrayList<FrontAirportDTO> results= new ArrayList();
        ArrayList<FrontAirportDTO> list = instance.allAirports();
        for (FrontAirportDTO frontAirportDTO : list) {
            if (instance.calculateRadius(frontAirportDTO, restaurant)<=radius){
                results.add(frontAirportDTO);
            }
        }        
        
        HashMap<Double, FrontAirportDTO> resultmap=new HashMap();
        for (FrontAirportDTO result : results) {
            double r=calculateRadius(result, restaurant);
            resultmap.put(r, result);
        }
        
        return resultmap;
    }
    
    
    public HashMap<Double, FrontAirportDTO> getNearestAirorts(RestaurantDTO restaurant){
        ArrayList<FrontAirportDTO> duplicats= new ArrayList();
         HashMap<Double, FrontAirportDTO> mapResults=new HashMap();
        ArrayList<FrontAirportDTO> list = instance.allAirports();
        for (FrontAirportDTO frontAirportDTO : list) {
            double r = calculateRadius(frontAirportDTO, restaurant);
           FrontAirportDTO present= mapResults.putIfAbsent(r, frontAirportDTO);
           if(present!=null){
               FrontAirportDTO sameRadius = mapResults.putIfAbsent(-1*r, frontAirportDTO);
               if(sameRadius!=null){
                   duplicats.add(sameRadius);
               }
           }
        }        
      /*  
        HashMap<Double, FrontAirportDTO> resultmap=new HashMap();
        for (FrontAirportDTO result : results) {
            double r=calculateRadius(result, restaurant);
            resultmap.put(r, result);
        }
        */
        return resultmap;
    }
    
    

}
