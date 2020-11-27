/*
 * Gruppe 3 SYS
 */
package rest;

import errorhandling.API_Exception;
import errorhandling.NotFoundException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

/**
 *
 * @author magda
 */
public interface IFlightResources {

    @POST
    String findFlights(String jsonString) throws NotFoundException, API_Exception;

}
