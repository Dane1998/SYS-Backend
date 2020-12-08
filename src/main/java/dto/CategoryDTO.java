/*
 * Gruppe 3 SYS
 */
package dto;

import entities.Category;

/**
 *
 * @author magda
 */
public class CategoryDTO {
    private int id;
    private String name;

    public CategoryDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    
    
    
}
