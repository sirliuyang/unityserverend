package com.formulu.unityserverend.network;

public class U3dInstance {
    private static U3dInstance instance = null;

    private boolean stop;

    private U3dInstance() {}

    public static synchronized U3dInstance getApplication() {
        if (instance == null) {
            instance = new U3dInstance();
        }
        return instance;
    }

    public void start() {
        init();
        new Thread(new U3dServer(), "U3d Server").start();
        while (!stop) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("");
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                getApplication().stopMe();
            }
        });
        getApplication().start();
    }

    public void stopMe() {
        System.out.println("系统即将关闭...");
    }

    /**
     * 初始化系统
     */
    private void init() {

    }

}
