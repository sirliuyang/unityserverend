package com.formula.unityserverend.dao;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.formulu.unityserverend.dao.UserDao;
import com.formulu.unityserverend.entity.User;

public class UserDaoTest {
    private UserDao userDao;

    @Before
    public void setup() {
        userDao = new UserDao();
    }

    @After
    public void down() {

    }

    @Test
    public void test() {
        try {
            User user = userDao.selectByName("leon");
            System.out.println(user.toString());
        } catch (SQLException e) {
            fail("");
        }
    }
}
