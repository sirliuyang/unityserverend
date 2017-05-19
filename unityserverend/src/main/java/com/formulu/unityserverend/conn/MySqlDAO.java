package com.formulu.unityserverend.conn;

import java.sql.Connection;

public class MySqlDAO {
    
    protected static ConnectionPool connPool;
    protected StatusConnection statusConn;
    protected Connection conn;
    
    public MySqlDAO(){
        connPool = ConnPoolSingleton.getConnPool();
        statusConn = connPool.getConnection();
        conn = statusConn.toConnection();
    }
}
