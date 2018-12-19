package com.dingtone;

import com.dingdong.common.HttpClientReponse;
import com.dingdong.common.HttpClientRequest;
import com.dingdong.utils.HttpClientUtil;
import com.dingdong.utils.TestData;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

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
    @Test(groups = "test2")
    public void test2(){
        if (TestData.initTestData(2)) {
            HttpClientRequest request = new HttpClientRequest();

            //request.setUrl(TestData.TestIP + TestData.V1_isDummy +"trackCode=0");
            request.setUrl("http://54.241.20.16:8080/dummy/v1/isDummy");


            Map<String, String> requestHeader = new HashMap<String, String>();
            requestHeader.put("X-G-TOKEN", "0");
            requestHeader.put("Content-Type", "application/json");
            request.setHeaders(requestHeader);

            request.setBody("{\"insId\": \"9504864908\"}");

            HttpClientReponse reponse = HttpClientUtil.doPost(request);
            Assert.assertEquals("200", reponse.getStatusCode());

        }
    }
}
