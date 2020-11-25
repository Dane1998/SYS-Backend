/*
 * Gruppe 3 SYS
 */
package dto.categoryFetchResult;

import dto.CategoryDTO;

/**
 *
 * @author magda
 */
public class CategoryKeeperDTO {
    CategoryDTO categories;

    public CategoryKeeperDTO(CategoryDTO categories) {
        this.categories = categories;
    }

    public CategoryDTO getCategory() {
        return categories;
    }
    
    
    
}
