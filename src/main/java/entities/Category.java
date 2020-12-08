/*
 * Gruppe 3 SYS
 */
package entities;

import dto.CategoryDTO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author magda
 */
@Entity
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    private int id;
    private String name;

    public Category() {
    }

    public Category(CategoryDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
    }
        

    public int getId() {
        return id;
    }

   

    public String getName() {
        return name;
    }
    

}