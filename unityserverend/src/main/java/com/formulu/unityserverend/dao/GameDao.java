package com.formulu.unityserverend.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import com.formulu.unityserverend.conn.MySqlDAO;
import com.formulu.unityserverend.entity.Game;

public class GameDao extends MySqlDAO {
    public GameDao() {
        super();
    }

    public void insert(String name, JSONObject step){
        
    }
    
    public Game selectByName(String name) throws SQLException {
        Game game = new Game();
        String sql = "select * from games where name = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(sql);
        preparedStmt.setString(1, name);
        ResultSet rs = preparedStmt.executeQuery();
        while (rs.next()) {

        }
        return game;
    }
}
