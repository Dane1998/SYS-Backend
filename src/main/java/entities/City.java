/*
 * Gruppe 3 SYS
 */
package entities;

import dto.CityDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author magda and daniel
 */
@Entity
public class City implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    private String city_name;
    private String iata_code;

    public City(CityDTO ctDTO) {

        this.city_name = ctDTO.getCity_name();
        this.iata_code = ctDTO.getIata_code();
    }

    public City() {
    }

}
