/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.rheatherhendi.dao;

import java.util.List;
import sg.am.rheatherhendi.model.Blog;

/**
 *
 * @author afsanamiji
 */
public interface blogDao {
    public void addBlog(Blog blog);
    public void deleteBlog(int blogId);
    public void editBlog(Blog blog);
    public Blog getById(int blogId);
    public List<Blog> getAllBlogs();
    public Blog getBlogByName(String blogName);
}
