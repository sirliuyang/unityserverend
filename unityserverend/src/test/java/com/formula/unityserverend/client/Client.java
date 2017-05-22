package com.formula.unityserverend.client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONObject;

import com.formulu.unityserverend.entity.User;
import com.formulu.unityserverend.handler.MsgHandler;
import com.formulu.unityserverend.handler.SocketMsgHandler;

public class Client {
    public static void main(String[] args) {
        try {
            new SocketSender().start();
        } catch (Exception ex) {
        }
    }
}


class SocketSender extends Thread {
    private Socket socket;
    private OutputStreamWriter writer;

    public SocketSender() throws UnknownHostException, IOException {
        socket = new Socket("localhost", 8888);
    }

    @Override
    public void run() {
        try {
            writer = new OutputStreamWriter(socket.getOutputStream());
            BufferedWriter bw = new BufferedWriter(writer);
            User user = new User();
            user.setId(0);
            user.setName("Leon");
            user.setContact("88978827");
            JSONObject jsonObject = new JSONObject(user);
            jsonObject.append("datatype", "user");
            bw.write(jsonObject.toString() + "\n");
            bw.flush();
            bw.close();
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class SocketReceiver extends Thread {
    /** 报文长度字节数 */
    private int messageLengthBytes = 1024;
    private Socket socket;
    private MsgHandler<BufferedInputStream> msgHanlder;
    /** socket输入处理流 */
    private BufferedInputStream bis = null;

    public SocketReceiver(Socket socket) {
        this.socket = socket;
        msgHanlder = new SocketMsgHandler();
    }

    @Override
    public void run() {
        try {

            // 获取socket中的数据
            bis = new BufferedInputStream(socket.getInputStream());
            msgHanlder.handleMsg(bis);
            byte[] buf = new byte[messageLengthBytes];
            /**
             * 在Socket报文传输过程中,应该明确报文的域
             */
            while (true) {
                /*
                 * 这种业务处理方式是根据不同的报文域,开启线程,采用不同的业务逻辑进行处理 依据业务需求而定
                 */
                // 读取字节数组中的内容
                bis.read(buf);
                // 输出
                System.out.println(new String(buf, "utf-8"));

            }
        } catch (IOException e) {
            System.err.println("读取报文出错" + e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                socket = null;
            }
        }
    }
}
