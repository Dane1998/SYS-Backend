/*
 * Gruppe 3 SYS
 */
package fetch;

import dto.CategoryDTO;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author magda
 */
public class CategoryFetcherTest {

    @Test
    public void getAllCategoriestest() throws IOException {
        ArrayList<CategoryDTO>  list = CategoryFetcher.getAllCategories();
        String name="";
        for (CategoryDTO cat : list) {
            if(cat.getId()==6){
                name = cat.getName();
                System.out.println(name);
            }
        }
        assertEquals(list.size(), 13);
        assertTrue(name.equals("Cafes"));
    }

}
