package com.formulu.unityserverend.handler;

public interface MsgHandler<T> {
    public void handleMsg(T t);
}
