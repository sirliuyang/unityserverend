package com.formulu.unityserverend.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.formulu.unityserverend.conn.MySqlDAO;
import com.formulu.unityserverend.entity.User;

public class UserDao extends MySqlDAO {
    public UserDao() {
        super();
    }

    public void insert(String name, String contact) throws SQLException {
        String sql = "insert into users(name, contact) values(?,?)";
        PreparedStatement preparedStmt = conn.prepareStatement(sql);
        preparedStmt.setString(1, name);
        preparedStmt.setString(2, contact);
        preparedStmt.executeUpdate();
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

    public List<User> selectAll() throws SQLException {
        List<User> userList = new ArrayList<User>();
        String sql = "select * from users";
        Statement Stmt = conn.createStatement();
        ResultSet rs = Stmt.executeQuery(sql);
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setContact(rs.getString(3));
            userList.add(user);
        }
        return userList;
    }
}
