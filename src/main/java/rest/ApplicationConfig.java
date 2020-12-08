package rest;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(cors.CorsFilter.class);
        resources.add(errorhandling.API_ExceptionMapper.class);
        resources.add(errorhandling.GenericExceptionMapper.class);
        resources.add(org.glassfish.jersey.server.wadl.internal.WadlResource.class);
        resources.add(rest.FlightResource.class);
        resources.add(rest.UserResource.class);
        resources.add(rest.ZomatoResource.class);
        resources.add(rest.restaurants.AirportSearchResourse.class);
        resources.add(rest.restaurants.CategoryResource.class);
        resources.add(rest.restaurants.CityResource.class);
        resources.add(rest.restaurants.CuisineResource.class);
        resources.add(rest.restaurants.RestaurantResource.class);
        resources.add(security.JWTAuthenticationFilter.class);
        resources.add(security.LoginEndpoint.class);
        resources.add(security.RolesAllowedFilter.class);
        resources.add(security.errorhandling.AuthenticationExceptionMapper.class);
        resources.add(security.errorhandling.NotAuthorizedExceptionMapper.class);
    }
    
}
