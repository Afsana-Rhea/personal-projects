/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.am.rheatherhendi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import sg.am.rheatherhendi.model.Category;

/**
 *
 * @author afsanamiji
 */
public class categoryMapper implements RowMapper<Category>{

    @Override
    public Category mapRow(ResultSet rs, int i) throws SQLException {
        Category category = new Category();
        category.setiD(rs.getInt("categoryId"));
        category.setName(rs.getString("categoryName"));
        return category;
    }
    
}
