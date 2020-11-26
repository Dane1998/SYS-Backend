/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.cities;

import dto.zCityDTO;



/**
 *
 * @author Bruger
 */
public class KeeperCityDTO {
    zCityDTO zCity;

    public KeeperCityDTO(zCityDTO city) {
        this.zCity = city;
    }

    public zCityDTO getCity() {
        return zCity;
    }
    
    
    
}
