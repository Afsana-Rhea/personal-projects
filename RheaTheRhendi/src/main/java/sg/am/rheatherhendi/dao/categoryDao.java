/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.rheatherhendi.dao;

import java.util.List;
import sg.am.rheatherhendi.model.Category;

/**
 *
 * @author afsanamiji
 */
public interface categoryDao {
    
   public void addCategory(Category category);
   public void deleteCategory(int catId);
   public void editcategory(Category category);
   public Category getCategoryById(int catId);
   public List<Category> getAllCategories();
    
}
