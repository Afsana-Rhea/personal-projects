/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.rheatherhendi.dao;

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
public class blogDaoImplTest {
    
    @Autowired
    CategoryDao categoryDao;
    
    @Autowired
    BlogDao blogDao;
    
    public blogDaoImplTest() {
    }
    


    /**
     * Test of addBlog method, of class blogDaoImpl.
     */
    @Test
    public void testAddBlog() {
        Blog blog = new Blog();
        blog.setiD(1);
        blog.setTitle("1st one");
        blog.setPost("body");
        blog.setIsPublished(true);
        
        
        Category category = new Category();
        category.setiD(1);
        category.setName("info");
        categoryDao.addCategory(category);
        
        blog.setCategory(category);
        blogDao.addBlog(blog);
        int id = blog.getiD();
        Blog actBlog = blogDao.getById(id);
        
        assertEquals(blog.getTitle(), actBlog.getTitle());
        //assertTrue(blog.equals(actBlog));
    }

    /**
     * Test of deleteBlog method, of class blogDaoImpl.
     */
    @Test
    public void testDeleteBlog() {
       Blog blog = new Blog();
        blog.setTitle("1st one");
        blog.setPost("body");
        blog.setIsPublished(true);
        
        
        Category category = new Category();
        category.setName("info");
        categoryDao.addCategory(category);
        
        blog.setCategory(category);
        blogDao.addBlog(blog);
        blogDao.deleteBlog(blog.getiD());
        
        assertNull(blogDao.getById(blog.getiD()));
    }

    /**
     * Test of editBlog method, of class blogDaoImpl.
     */
    @Test
    public void testEditBlog() {
         Blog blog = new Blog();
        blog.setTitle("1st one");
        blog.setPost("body");
        blog.setIsPublished(true);
        
        
        Category category = new Category();
        category.setName("info");
        categoryDao.addCategory(category);
        
        blog.setCategory(category);
        blogDao.addBlog(blog);
        blog.setTitle("2nd one");
        
        blogDao.editBlog(blog);
        
        assertTrue(blogDao.getById(blog.getiD()).equals(blog));
        
        
    }

  

    /**
     * Test of getAllBlogs method, of class blogDaoImpl.
     */
    @Test
    public void testGetAllBlogs() {
        Blog blog = new Blog();
        blog.setTitle("1st one");
        blog.setPost("body");
        blog.setIsPublished(true);
        
        
        Category category = new Category();
        category.setName("info");
        categoryDao.addCategory(category);
        
        blog.setCategory(category);
        blogDao.addBlog(blog);
        
         Blog blogTwo = new Blog();
        blogTwo.setTitle("2st one");
        blogTwo.setPost("body");
        blogTwo.setIsPublished(true);
        
        blogTwo.setCategory(category);
       blogDao.addBlog(blogTwo);
        
        assertEquals(2, blogDao.getAllBlogs().size());
        assertTrue(blogDao.getAllBlogs().contains(blog));
        assertTrue(blogDao.getAllBlogs().contains(blogTwo));
        
    }

    /**
     * Test of getBlogByName method, of class blogDaoImpl.
     */
    @Test
    public void testGetBlogByName() {
       Blog blog = new Blog();
        blog.setTitle("1st one");
        blog.setPost("body");
        blog.setIsPublished(true);
        
        
        Category category = new Category();
        category.setName("info");
        categoryDao.addCategory(category);
        
        blog.setCategory(category);
        blogDao.addBlog(blog);
        
        assertTrue(blogDao.getBlogByName(blog.getTitle()).equals(blog));
        
        
    }
    
}
