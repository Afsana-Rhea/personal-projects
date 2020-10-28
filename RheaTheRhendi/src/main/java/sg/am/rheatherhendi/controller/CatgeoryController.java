/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.rheatherhendi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sg.am.rheatherhendi.model.Category;
import sg.am.rheatherhendi.dao.BlogDao;
import sg.am.rheatherhendi.dao.CategoryDao;

/**
 *
 * @author afsanamiji
 */

@Controller
public class CatgeoryController {
    
    @Autowired
    BlogDao blogDao;
    
    @Autowired
    CategoryDao catDao;
    
    
    @GetMapping("categories")
    public String displayCategories(Model model){
        List<Category> cats = catDao.getAllCategories();
        Category cat = new Category();
        
        model.addAttribute("categoies", cats);
        model.addAttribute("category", cat);
        return "categories";
    }
    
}
