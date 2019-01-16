package com.dingtone;

import com.dingtone.common.HttpClientReponse;
import com.dingtone.common.HttpClientRequest;
import com.dingtone.utils.HttpClientUtil;

import com.dingtone.utils.TestData;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.HashMap;
import java.util.Map;


public class HttpClientUtilTest extends TestData {
    private static Logger logger = Logger.getLogger(HttpClientUtilTest.class);

    @Test
    public void test1() {
        if (TestData.initTestData(2)) {
            HttpClientRequest request = new HttpClientRequest();

            request.setUrl(TestData.TestIP + TestData.V1_isDummy +"trackCode=0");
            //request.setUrl("http://54.241.20.16:8080/dummy/v1/isDummy?trackCode=0");


            Map<String, String> requestHeader = new HashMap<String, String>();
            requestHeader.put("X-G-TOKEN", "0");
            requestHeader.put("Content-Type", "application/json");
            request.setHeaders(requestHeader);

            request.setBody("{\"insId\": \"9504864908\"}");

            HttpClientReponse reponse = HttpClientUtil.doPost(request);

            JSONObject jsonObject = new JSONObject(reponse.getBody());

            System.out.println(jsonObject.getInt("Result"));
            System.out.println(jsonObject.getString("Message"));
            System.out.println(jsonObject.getJSONObject("data"));
            JSONObject data1 = jsonObject.getJSONObject("data");
            System.out.println(data1.getInt("isBind"));

            System.out.println(jsonObject.getLong("ServerTime"));

            Assert.assertEquals("200", reponse.getStatusCode());

        }
    }

    @Test
    public void test2(){
        if(TestData.initTestData(2)){
            HttpClientRequest request = new HttpClientRequest();
            request.setUrl("http://54.241.20.16:8080/dummy/v1/isDummy");

            Map<String, String> requestHeader = new HashMap<String, String>();
            requestHeader.put("X-G-TOKEN", "0");
            requestHeader.put("Content-Type", "application/json");
            request.setHeaders(requestHeader);

            request.setBody("");

            HttpClientReponse reponse = HttpClientUtil.doPost(request);
            Assert.assertEquals("200", reponse.getStatusCode());
        }
    }
    }

