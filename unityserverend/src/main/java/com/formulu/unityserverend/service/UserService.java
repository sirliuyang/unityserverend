package com.formulu.unityserverend.service;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONObject;

import com.formulu.unityserverend.dao.UserDao;
import com.formulu.unityserverend.entity.User;

public class UserService implements Service<User> {
    private UserDao dao = new UserDao();

    public void add(JSONObject json) {
        String name = json.getString("name");
        String contact = json.getString("contact");
        try {
            dao.insert(name, contact);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public User query(JSONObject name) {
        User user = null;
        /*
         * UserDao dao = new UserDao(); try { user = (User) dao.selectByName(name); } catch
         * (SQLException e) { e.printStackTrace(); }
         */
        return user;
    }

    @Override
    public void remove(JSONObject t) {
        // TODO Auto-generated method stub

    }

    @Override
    public User queryByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> queryAll() {
        // TODO Auto-generated method stub
        return null;
    }
}
