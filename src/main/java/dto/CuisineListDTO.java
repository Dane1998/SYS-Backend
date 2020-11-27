/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import dto.cuisineFetchResult.CuisineKeeperDTO;
import java.util.ArrayList;

/**
 *
 * @author Bruger
 */
public class CuisineListDTO {
    
    ArrayList<CuisineKeeperDTO> cuisines;

    public CuisineListDTO(ArrayList<CuisineKeeperDTO> cuisines) {
        this.cuisines = cuisines;
    }

    public ArrayList<CuisineKeeperDTO> getCuisines() {
        return cuisines;
    }
    
    
    
}
