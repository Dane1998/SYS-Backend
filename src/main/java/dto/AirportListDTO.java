/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;

/**
 *
 * @author magda og daniel
 */
public class AirportListDTO {

    private ArrayList<AirportDTO> data = new ArrayList<>();

    public AirportListDTO(ArrayList<AirportDTO> data) {
        this.data = data;
    }

    public ArrayList<AirportDTO> getData() {
        return data;
    }

}
