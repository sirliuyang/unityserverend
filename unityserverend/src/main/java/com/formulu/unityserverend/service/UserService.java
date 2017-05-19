package com.formulu.unityserverend.service;

import java.sql.SQLException;

import com.formulu.unityserverend.dao.UserDao;
import com.formulu.unityserverend.entity.User;

public class UserService {

    public void add(User user) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into users(name,contact) values(\"");
    }

    public User query(String name) {
        User user = null;
        UserDao dao = new UserDao();
        try {
            user = dao.selectByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
