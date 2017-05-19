package com.formulu.unityserverend.conn;

public interface ConnectionPool {
    public StatusConnection getConnection();
    public boolean closeConnections();
}
