package com.formulu.unityserverend.service;

import java.util.List;

public interface Service<T> {
    public void add(T t);
    public void remove(T t);
    public T queryByName(String name);
    public List<T> queryAll();
}
