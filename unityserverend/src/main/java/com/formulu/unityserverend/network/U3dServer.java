package com.formulu.unityserverend.network;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.formulu.unityserverend.handler.MsgHandler;
// http://blog.csdn.net/u014735301/article/details/42145131
import com.formulu.unityserverend.handler.SocketMsgHandler;

public class U3dServer implements Runnable {
    public void run() {
        ServerSocket u3dServerSocket = null;
        while (true) {
            try {
                u3dServerSocket = new ServerSocket(8888);
                System.out.println("U3d server started, listening to 8888");
                while (true) {
                    Socket socket = u3dServerSocket.accept();
                    System.out.println("Client connected");
                    new SocketReceiver(socket).start();
                    new SocketSender(socket).start();
                }
            } catch (IOException e) {
                System.err.println("Failed:" + e.getMessage());
                if (u3dServerSocket != null) {
                    try {
                        u3dServerSocket.close();
                    } catch (IOException ioe) {
                    }
                    u3dServerSocket = null;
                }
            }
            //
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {

            }
        }
    }

    class SocketSender extends Thread {
        private Socket socket;
        private OutputStreamWriter writer;

        public SocketSender(Socket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
            try {
                writer = new OutputStreamWriter(socket.getOutputStream());
                writer.write("Server, Server, Server");
                writer.flush();
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
}
