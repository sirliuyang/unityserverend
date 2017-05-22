package com.formula.unityserverend.client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.json.JSONObject;

import com.formulu.unityserverend.entity.User;

public class Client {
    public static void main(String[] args) {
        BufferedInputStream bis;
        BufferedReader br;
        OutputStreamWriter osw;
        BufferedWriter bw;;

        try {
            Socket socket = new Socket("localhost", 8888);
            osw = new OutputStreamWriter(socket.getOutputStream());
            bw = new BufferedWriter(osw);
            User user = new User();
            user.setId(0);
            user.setName("Leon");
            user.setContact("88978827");
            JSONObject jsonObject = new JSONObject(user);
            bw.write(jsonObject.toString() + "\n");
            bw.flush();
            //Receive the msg from server
            bis= new BufferedInputStream(socket.getInputStream());
            byte[] buf = new byte[128];
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
        } catch (Exception ex) {
        }
    }
}
