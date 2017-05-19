package com.formula.unityserverend.conn;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.formulu.unityserverend.conn.MySqlConnPool;
import com.formulu.unityserverend.conn.StatusConnection;

public class MySQLConnPoolTest {

    private MySqlConnPool connPool = null;
    
    @Before
    public void setup(){
        connPool = new MySqlConnPool("127.0.0.1","3306","test", "root", "root");
    }
    
    @Test
    public void dbConnect1() {
        StatusConnection statusconn = connPool.getConnection();
        while (statusconn != null) {
            statusconn = connPool.getConnection();
        }
        assertNull(statusconn);
    }

    @Test
    public void dbConnect2() {
        StatusConnection statusconn = connPool.getConnection();
        while (statusconn != null) {
            assertNotNull(statusconn);
            statusconn = connPool.getConnection();
        }
    }
}
