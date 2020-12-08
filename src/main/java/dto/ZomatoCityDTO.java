/*
 * Gruppe 3 SYS
 */
package dto;

import java.util.ArrayList;

/**
 *
 * @author magda
 */
public class ZomatoCityDTO {
    private int city_id;
    private ArrayList<CuisineDTO> cuisines;
    private ArrayList<CollectionDTO> collections;

    public ZomatoCityDTO(int city_id, ArrayList<CuisineDTO> cuisines, ArrayList<CollectionDTO> collections) {
        this.city_id = city_id;
        this.cuisines = cuisines;
        this.collections = collections;
    }
    
    
}
