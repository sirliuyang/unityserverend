package com.formulu.unityserverend.conn;

import com.formulu.unityserverend.util.PropertyLoader;

public class ConnPoolSingleton {

    private static String dbIP = PropertyLoader.dbConf("dbIP");
    private static String dbPort = PropertyLoader.dbConf("dbPort");
    private static String dbName = PropertyLoader.dbConf("dbName");
    private static String dbUser = PropertyLoader.dbConf("dbUser");
    private static String dbPwd = PropertyLoader.dbConf("dbPwd");

    private ConnPoolSingleton() {}

    private static ConnectionPool connPool = null;

    public static ConnectionPool getConnPool() {
        if (connPool == null) {
            connPool = new MySqlConnPool(dbIP, dbPort, dbName, dbUser, dbPwd);
        }
        return connPool;
    }
}
