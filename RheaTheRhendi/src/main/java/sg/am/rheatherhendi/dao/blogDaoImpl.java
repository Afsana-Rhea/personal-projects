/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.rheatherhendi.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sg.am.rheatherhendi.model.Blog;
import sg.am.rheatherhendi.model.Category;

@Repository
public class BlogDaoImpl implements BlogDao {
    
    @Autowired
    JdbcTemplate jdbc;
   

    @Override
    @Transactional
    public void addBlog(Blog blog) {
        final String ADD_BLOG = "INSERT INTO blog(blogTitle, blogPost, isPublished, categoryId) VALUES (?, ?, ?, ?)";
        jdbc.update(ADD_BLOG, blog.getTitle(), blog.getPost(), blog.isIsPublished(), blog.getCategory().getiD());
        blog.setiD(jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class));
    }

    @Override
    public void deleteBlog(int blogId) {
    final String DELETE_BLOG = "DELETE FROM blog WHERE blogId = ?";
    jdbc.update(DELETE_BLOG, blogId);
    }

    @Override
    public void editBlog(Blog blog) {
        final String UPDATE_BLOG = "UPDATE blog SET blogTitle = ?, blogPost = ?, isPublished = ?, categoryId = ? WHERE blogId = ?";
        jdbc.update(UPDATE_BLOG, blog.getTitle(), blog.getPost(), blog.isIsPublished(), blog.getCategory().getiD(), blog.getiD());
 
    }

    @Override
    public Blog getById(int blogId) {
        try{
            final String GET_BLOG_BY_ID = "SELECT * from blog WHERE blogId = ?";
            Blog blog = jdbc.queryForObject(GET_BLOG_BY_ID, new BlogMapper(), blogId);
            blog.setCategory(getCategoryforBlog(blog));
            return blog;
        }catch (DataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Blog> getAllBlogs() {
    final String GET_BLOGS = "SELECT * FROM blog";
    List<Blog> blogs = jdbc.query(GET_BLOGS, new BlogMapper());
    return associateCategoryForBlogs(blogs);
    }

    @Override
    public Blog getBlogByName(String blogName) {
        try{
                final String GET_BLOG = "SELECT * from blog WHERE blogTitle = ?"; 
                Blog blog = jdbc.queryForObject(GET_BLOG, new BlogMapper(), blogName);
                blog.setCategory(getCategoryforBlog(blog));
                return blog;
        }catch(DataAccessException ex){
            return null;
        }
   
        }

    private Category getCategoryforBlog(Blog blog) {
    final String GET_CATEGORY_FOR_BLOG = "SELECT * FROM category JOIN blog ON blog.categoryId = category.categoryId WHERE blog.blogId = ?";
    return jdbc.queryForObject(GET_CATEGORY_FOR_BLOG, new CategoryMapper(), blog.getiD());
    }

    private List<Blog> associateCategoryForBlogs(List<Blog> blogs) {
    for(Blog blog: blogs){
    blog.setCategory(getCategoryforBlog(blog));
}
        return blogs;
    }
    
}
