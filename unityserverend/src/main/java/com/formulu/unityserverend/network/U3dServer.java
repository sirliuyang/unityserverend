package com.formulu.unityserverend.network;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
// http://blog.csdn.net/u014735301/article/details/42145131

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

        /** socket输入处理流 */
        private BufferedInputStream bis = null;


        public SocketReceiver(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {

                // 获取socket中的数据
                bis = new BufferedInputStream(socket.getInputStream());
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
}
