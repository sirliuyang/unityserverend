package com.formulu.unityserverend.conn;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class MySQLConnPool {
    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private String dbUrl = "";
    private String dbUser = "";
    private String dbPwd = "";

    private int initialConnSize = 10;
    private int incrementalConnections = 5;
    private int maxConnSize = 50;
    private Vector<StatusConnection> connections = null;

    public MySQLConnPool(String dbIP, String dbPort, String dbName, String dbUser, String dbPwd) {
        this.dbUrl = "jdbc:mysql://" + dbIP + ":" + dbPort + "/" + dbName;
        this.dbUser = dbUser;
        this.dbPwd = dbPwd;
    }

    public StatusConnection getConnection() {
        StatusConnection conn = null;
        try {
            if (connections != null) {
                conn = getFreeConnection();
                while (conn == null && connections.size() < maxConnSize) {
                    createConnections(incrementalConnections);
                    conn = getFreeConnection();
                }
            } else {
                initialConnPool();
                conn = getFreeConnection();
            }
        } catch (Exception ex) {
        }
        return conn;
    }

    public boolean closeConnections() {
        connections.clear();
        return true;
    }

    private synchronized void initialConnPool()
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        if (connections != null) {
            return;
        }
        Driver driver = (Driver) (Class.forName(this.jdbcDriver).newInstance());
        DriverManager.registerDriver(driver);
        connections = new Vector<StatusConnection>();
        createConnections(initialConnSize);
    }

    private void createConnections(int connNum) throws SQLException {
        for (int i = 1; i <= connNum; i++) {
            if (connections.size() >= maxConnSize) {
                break;
            }
            connections.addElement(new StatusConnection(createOneConnection()));
        }
    }

    private Connection createOneConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
        return conn;
    }

    private StatusConnection getFreeConnection() {
        StatusConnection conn = null;
        for (StatusConnection connection : connections) {
            if (!connection.isInUse()) {
                connection.inUse();
                conn = connection;
            }
        }
        return conn;
    }

}
