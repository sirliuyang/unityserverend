package com.formulu.unityserverend.conn;

import java.sql.Connection;

public class StatusConnection {
    private Connection conneciton = null;
    private boolean inUse = false;

    public StatusConnection(Connection connection) {
        this.conneciton = connection;
    }

    public Connection toConnection() {
        return conneciton;
    }

    public boolean isInUse() {
        return this.inUse;
    }

    public void inUse() {
        this.inUse = true;
    }

    public void free() {
        this.inUse = false;
    }
}
