package com.dingtone;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCase {
    @DataProvider(name = "params")
    public Object [][] dataProvider(){
        return new Object[][]{
                {"A", 65},
                {"B", 66},
                {"C", 67}
        };
    }

    @Test(dataProvider = "params", groups = {"test2","test1"})
    public void printParam(String str, int i){
        System.out.println("strParam = " + str + " ,i = " + i);
    }

    @BeforeGroups(groups={"test1"})
    public void setUp(){
        System.out.println("Method---setup");
    }

    @AfterGroups(groups={"test1"})
    public void tearDown(){
        System.out.println("Method---tearDown");
    }

    @Test(groups = "test1")
    public void test1(){
        System.out.println("this test1");
    }

}
