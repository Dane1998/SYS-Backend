/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Bruger
 */
public class zCityDTO {
    private int id;
    private String name;
    private int country_id;
    private String country_name;
    //private boolean is_state;
    private int state_id;
    private String state_name;
    private String state_code;

    public zCityDTO(int id, String name, int country_id, String country_name, boolean is_state, int state_id, String state_name, String state_code) {
        this.id = id;
        this.name = name;
        this.country_id = country_id;
        this.country_name = country_name;
       // this.is_state = is_state;
        this.state_id = state_id;
        this.state_name = state_name;
        this.state_code = state_code;
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

   /* public boolean isIs_state() {
        return is_state;
    }

    public void setIs_state(boolean is_state) {
        this.is_state = is_state;
    }*/

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

    
    
    
    
    
}
