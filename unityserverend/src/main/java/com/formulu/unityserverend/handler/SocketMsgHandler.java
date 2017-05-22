package com.formulu.unityserverend.handler;

import java.io.BufferedInputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import com.formulu.unityserverend.entity.Game;
import com.formulu.unityserverend.entity.User;
import com.formulu.unityserverend.service.GameService;
import com.formulu.unityserverend.service.Service;
import com.formulu.unityserverend.service.UserService;
import com.formulu.unityserverend.util.ContentTransfer;

public class SocketMsgHandler implements MsgHandler<BufferedInputStream> {
    private static final String TYPE = "datatype";

    @Override
    public void handleMsg(BufferedInputStream msgFromClient) {
        String msg = ContentTransfer.InputStream2String(msgFromClient);
        JSONObject json = new JSONObject(msg);
        JSONArray type = (JSONArray) json.get(TYPE);
        if (type.get(0).equals("user")) {
            Service<User> service = new UserService();
            User user = new User();
            service.add(user);
        }
        if (type.get(0).equals("game")) {
            Service<Game> service = new GameService();
            Game game = new Game();
            service.add(game);
        }
    }
}
