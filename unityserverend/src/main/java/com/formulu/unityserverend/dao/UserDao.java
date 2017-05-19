package com.formulu.unityserverend.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.formulu.unityserverend.conn.MySqlDAO;
import com.formulu.unityserverend.entity.User;

public class UserDao extends MySqlDAO {
    public UserDao() {
        super();
    }

    public User selectByName(String name) throws SQLException {
        User user = new User();
        String sql = "select * from users where name = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(sql);
        preparedStmt.setString(1, name);
        ResultSet rs = preparedStmt.executeQuery();
        while (rs.next()) {
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setContact(rs.getString(3));
        }
        return user;
    }
}
