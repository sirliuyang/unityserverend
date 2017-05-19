package com.formula.unityserverend.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.formulu.unityserverend.util.PropertyLoader;

public class PropertyLoaderTest {

    @Test
    public void getProperty(){
        assertEquals(PropertyLoader.dbConf("dbName"), "test");
    }
}
