/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.rheatherhendi.dao;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import sg.am.rheatherhendi.model.Blog;
import sg.am.rheatherhendi.model.Category;

/**
 *
 * @author afsanamiji
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class categoryDaoImplTest {
    
    @Autowired
    categoryDao categoryDao;
    
    public categoryDaoImplTest() {
    }
   

    /**
     * Test of addCategory method, of class categoryDaoImpl.
     */
    @Test
    public void testGetAndAddCategory() {
        Category cat = new Category();
        cat.setiD(1);
        cat.setName("info");
        
        List<Blog> blogs = new ArrayList<>();
        cat.setBlogs(blogs);
        categoryDao.addCategory(cat);
        
        Category actCat = categoryDao.getCategoryById(cat.getiD());
        
        assertEquals(cat.getName(), actCat.getName());
        assertEquals(cat.getiD(), actCat.getiD());
        
        assertTrue(cat.equals(actCat));
    }

    /**
     * Test of deleteCategory method, of class categoryDaoImpl.
     */
    @Test
    public void testDeleteCategory() {
      Category cat = new Category();
        cat.setiD(1);
        cat.setName("info");
         List<Blog> blogs = new ArrayList<>();
        cat.setBlogs(blogs);
        
        categoryDao.addCategory(cat);
        categoryDao.deleteCategory(cat.getiD());
        
        assertNull(categoryDao.getCategoryById(cat.getiD()));
    }

    /**
     * Test of editcategory method, of class categoryDaoImpl.
     */
    @Test
    public void testEditcategory() {
     Category cat = new Category();
        cat.setiD(1);
        cat.setName("info");
         List<Blog> blogs = new ArrayList<>();
        cat.setBlogs(blogs);
        categoryDao.addCategory(cat);
        
        cat.setName("lessons");
        
        categoryDao.editcategory(cat);
        
        assertTrue(cat.equals(categoryDao.getCategoryById(cat.getiD())));
    }

 
    /**
     * Test of getAllCategories method, of class categoryDaoImpl.
     */
    @Test
    public void testGetAllCategories() {
         Category cat = new Category();
        cat.setiD(1);
        cat.setName("info");
         List<Blog> blogs = new ArrayList<>();
        cat.setBlogs(blogs);
        
        categoryDao.addCategory(cat);
        
         Category catTwo = new Category();
        catTwo.setiD(1);
        catTwo.setName("lessons");
       
        catTwo.setBlogs(blogs);
        
        categoryDao.addCategory(catTwo);
        
        assertEquals(2, categoryDao.getAllCategories().size());
        assertTrue(categoryDao.getAllCategories().contains(cat));
        assertTrue(categoryDao.getAllCategories().contains(catTwo));
    }
    
}
