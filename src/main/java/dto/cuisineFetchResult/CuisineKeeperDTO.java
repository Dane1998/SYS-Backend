/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.cuisineFetchResult;

import dto.CuisineDTO;

/**
 *
 * @author Bruger
 */
public class CuisineKeeperDTO {
    
  CuisineDTO cuisine;  

    public CuisineKeeperDTO(CuisineDTO cuisine) {
        this.cuisine = cuisine;
    }

    public CuisineDTO getCuisine() {
        return cuisine;
    }
  
  
    
}
