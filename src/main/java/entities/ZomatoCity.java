/*
 * Gruppe 3 SYS
 */
package entities;

import dto.zCityDTO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author magda
 */
@Entity
public class ZomatoCity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    private int id;
    private String name;
    private int country_id;
    private String country_name;
    private int state_id;
    private String state_name;
    private String state_code;

    public ZomatoCity() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public ZomatoCity(zCityDTO dto){
        this.id = dto.getId();
        this.name = dto.getName();
        this.country_id = dto.getCountry_id();
        this.country_name = dto.getCountry_name();
        this.state_id = dto.getState_id();
        this.state_name = dto.getState_name();
        this.state_code = dto.getState_code();
    }
    
    
    
    
}
