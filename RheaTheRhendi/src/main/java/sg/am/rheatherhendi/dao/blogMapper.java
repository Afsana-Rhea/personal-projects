/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.rheatherhendi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sg.am.rheatherhendi.model.Blog;

/**
 *
 * @author afsanamiji
 */

@Repository
public class BlogMapper implements RowMapper<Blog>{

    @Override
    public Blog mapRow(ResultSet rs, int i) throws SQLException {
        Blog blog = new Blog();
        blog.setiD(rs.getInt("blogId"));
        blog.setTitle(rs.getString("blogTitle"));
        blog.setPost(rs.getString("blogPost"));
        blog.setIsPublished(rs.getBoolean("isPublished"));
        return blog;
    }
    
}
