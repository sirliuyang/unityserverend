package com.formulu.unityserverend.service;

import java.util.List;

import org.json.JSONObject;

public interface Service<T> {
    public void add(JSONObject json);
    public void remove(JSONObject json);
    public T queryByName(String name);
    public List<T> queryAll();
}
