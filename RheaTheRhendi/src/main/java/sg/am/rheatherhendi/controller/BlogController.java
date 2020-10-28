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
import sg.am.rheatherhendi.model.Blog;
import sg.am.rheatherhendi.model.Category;
import sg.am.rheatherhendi.dao.BlogDao;
import sg.am.rheatherhendi.dao.CategoryDao;

/**
 *
 * @author afsanamiji
 */

@Controller
public class BlogController {
    
    @Autowired
    BlogDao blogDao;
    
    @Autowired
    CategoryDao catDao;
    
    @GetMapping("/")
    public String DisplayBlogs(Model model) {
        List<Category> cats = catDao.getAllCategories();
        Blog blog = new Blog();
        List<Blog> blogs = blogDao.getAllBlogs();
        
        model.addAttribute("categories", cats);
        model.addAttribute("blogs", blogs);
        model.addAttribute("blog", blog);
        return "index";
        
    }
}
