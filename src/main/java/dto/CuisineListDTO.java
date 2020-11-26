/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;

/**
 *
 * @author Bruger
 */
public class CuisineListDTO {
    
    ArrayList<CuisineDTO> cuisines;

    public CuisineListDTO(ArrayList<CuisineDTO> cuisines) {
        this.cuisines = cuisines;
    }

    public ArrayList<CuisineDTO> getCuisines() {
        return cuisines;
    }
    
    
    
}
