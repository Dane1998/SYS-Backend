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
public class CuisineDTO {
   private int cuisine_id;
   private String cuisine_name;
   
   

    public CuisineDTO(int cuisine_id, String cuisine_name) {
        this.cuisine_id = cuisine_id;
        this.cuisine_name = cuisine_name;
    }
   
   
   

    public int getCuisine_id() {
        return cuisine_id;
    }

    public void setCuisine_id(int cuisine_id) {
        this.cuisine_id = cuisine_id;
    }

    public String getCuisine_name() {
        return cuisine_name;
    }

    public void setCuisine_name(String cuisine_name) {
        this.cuisine_name = cuisine_name;
    }
   
   
   
    
}
