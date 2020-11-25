/*
 * Gruppe 3 SYS
 */
package dto.categoryFetchResult;

import java.util.ArrayList;

/**
 *
 * @author magda
 */
public class AllCategoriesDTO {
    ArrayList<CategoryKeeperDTO> categories;

    public AllCategoriesDTO(ArrayList<CategoryKeeperDTO> categories) {
        this.categories = categories;
    }

    public ArrayList<CategoryKeeperDTO> getCategories() {
        return categories;
    }
    
    
    
}
