package com.formula.unityserverend.conn;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.formulu.unityserverend.conn.MySqlConnPool;
import com.formulu.unityserverend.conn.StatusConnection;

public class MySQLConnPoolTest {

    private MySqlConnPool connPool = null;
    private StatusConnection statusconn = null;
    private Connection connection = null;

    @Before
    public void setup() {
        connPool = new MySqlConnPool("127.0.0.1", "3306", "test", "root", "root");
        statusconn = connPool.getConnection();
        connection = statusconn.toConnection();
    }

    @After
    public void down() {
        connPool.closeConnections();
    }

    @Test
    public void testQuery() {
        try {
            String sql = "select * from users where name = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1, "leon");
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
            }
        } catch (SQLException e) {
            fail("SQL Exception:" + e.getMessage());
        }
    }

    @Test
    public void dbConnect1() {
        while (statusconn != null) {
            statusconn = connPool.getConnection();
        }
        assertNull(statusconn);
    }

    @Test
    public void dbConnect2() {
        while (statusconn != null) {
            assertNotNull(statusconn);
            statusconn = connPool.getConnection();
        }
    }
}
