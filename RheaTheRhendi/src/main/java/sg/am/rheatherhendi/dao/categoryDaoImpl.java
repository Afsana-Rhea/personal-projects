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
public class categoryDaoImpl implements categoryDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Transactional
    @Override
    public void addCategory(Category category) {
        final String ADD_CAT = "INSERT INTO category(categoryName) VALUES (?)";
        jdbc.update(ADD_CAT, category.getName());
        category.setiD(jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class));
    
    }

    @Override
    public void deleteCategory(int catId) {
        final String DELETE_BLOG_CAT = "DELETE FROM blog WHERE categoryId = ?";
        jdbc.update(DELETE_BLOG_CAT,catId );
        
        final String DELETE_CAT = "DELETE FROM category WHERE categoryId = ?";
        jdbc.update(DELETE_CAT, catId);
    }

    @Override
    public void editcategory(Category category) {
        final String EDIT_CAT = "UPDATE category SET categoryName = ? WHERE categoryId = ?";
        jdbc.update(EDIT_CAT, category.getName(), category.getiD());
    }

    @Override
    public Category getCategoryById(int catId) {
        try{
        final String GET_CAT = "SELECT * FROM category WHERE categoryId = ?";
        Category category = jdbc.queryForObject(GET_CAT, new categoryMapper(), catId);
        category.setBlogs(getBlogsForCategory(category));
        return category;
        }catch(DataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Category> getAllCategories() {
        try{
            final String GET_ALL_CATS = "SELECT * FROM category";
            List<Category> categories = jdbc.query(GET_ALL_CATS, new categoryMapper());
            for(Category category : categories){
                category.setBlogs(getBlogsForCategory(category));
            }
            return categories;
        }catch(DataAccessException ex){
            return null;
        }

    }

    private List<Blog> getBlogsForCategory(Category category) {
        final String GET_BLOGS_FOR_CAT = "SELECT * FROM blog WHERE categoryId = ?";
        return jdbc.query(GET_BLOGS_FOR_CAT, new blogMapper(), category.getiD());
    }
    
}
