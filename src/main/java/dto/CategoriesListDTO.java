/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruger
 */
public class CategoriesListDTO {
   List<CategoriesDTO> categories = new ArrayList();
    
     public CategoriesListDTO(ArrayList<CategoriesDTO> categories) {
         this.categories = categories;
         
    }
    
    public List<CategoriesDTO> getCategories() {
        return categories;
    }
  
  
    
    
    
    
    
    
}
