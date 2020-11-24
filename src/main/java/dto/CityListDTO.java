/*
 * Gruppe 3 SYS
 */
package dto;

import java.util.ArrayList;

/**
 *
 * @author magda and daniel
 */
public class CityListDTO {
    private     ArrayList<CityDTO> data= new ArrayList<>();

    public ArrayList<CityDTO> getData() {
        return data;
    }

    public CityListDTO( ArrayList<CityDTO> data) {
        this.data=data;
    }

  
    
    
}
