/*
 * Gruppe 3 SYS
 */
package entities;

import dto.FlightDTO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author magda
 */
@Entity
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String dep_code;
    private String dep_terminal;
    private String dep_gate;
    private String dep_time;
    private String arr_code;
    private String arr_terminal;
    private String arr_gate;
    private String arr_time;
    private String airline;
    
    @ManyToOne
    @JoinColumn(name="TRIP")
    private Trip trip;

    public Flight(FlightDTO dto) {
        this.dep_code = dto.getDeparture().getIata();
        this.dep_terminal = dto.getArrival().getIata();
        this.dep_gate = dto.getDeparture().getGate();
        this.dep_time = dto.getDeparture().getScheduled();
        this.arr_code = dto.getArrival().getIata();
        this.arr_terminal = dto.getArrival().getTerminal();
        this.arr_gate = dto.getArrival().getGate();
        this.arr_time = dto.getArrival().getScheduled();
        this.airline = dto.getAirline().getName();
    }

    public Flight() {
    }
    
    

    
    
    public int getId() {
        return id;
    }

    public String getDep_code() {
        return dep_code;
    }

    public void setDep_code(String dep_code) {
        this.dep_code = dep_code;
    }

    public String getDep_terminal() {
        return dep_terminal;
    }

    public void setDep_terminal(String dep_terminal) {
        this.dep_terminal = dep_terminal;
    }

    public String getDep_gate() {
        return dep_gate;
    }

    public void setDep_gate(String dep_gate) {
        this.dep_gate = dep_gate;
    }

    public String getDep_time() {
        return dep_time;
    }

    public void setDep_time(String dep_time) {
        this.dep_time = dep_time;
    }

    public String getArr_code() {
        return arr_code;
    }

    public void setArr_code(String arr_code) {
        this.arr_code = arr_code;
    }

    public String getArr_terminal() {
        return arr_terminal;
    }

    public void setArr_terminal(String arr_terminal) {
        this.arr_terminal = arr_terminal;
    }

    public String getArr_gate() {
        return arr_gate;
    }

    public void setArr_gate(String arr_gate) {
        this.arr_gate = arr_gate;
    }

    public String getArr_time() {
        return arr_time;
    }

    public void setArr_time(String arr_time) {
        this.arr_time = arr_time;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Trip getTrip() {
        return trip;
    }
    
    public void setTrip(Trip trip){
        if(!trip.getFlights().contains(this)){
            trip.addFlight(this);
        }
        this.trip=trip;
    }
    

}
