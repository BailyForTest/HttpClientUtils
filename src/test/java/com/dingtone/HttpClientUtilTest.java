package com.dingtone;

import com.dingdong.common.HttpClientReponse;
import com.dingdong.common.HttpClientRequest;
import com.dingdong.utils.HttpClientUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.HashMap;
import java.util.Map;

public class HttpClientUtilTest {
    private static Logger logger = Logger.getLogger(HttpClientUtilTest.class);

    @Test(testName = "非一键完善信息用户")
    public  void test1(){
        HttpClientRequest request = new HttpClientRequest();
        request.setUrl("http://54.241.20.16:8080/dummy/v1/isDummy?trackCode=0");

        Map<String,String> requestHeader =new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN","0");
        requestHeader.put("Content-Type","application/json");
        //requestHeader.put("name2","value2");
        request.setHeaders(requestHeader);
        request.setBody("{\"insId\": \"9504864908\"}");

        HttpClientReponse reponse =  HttpClientUtil.doPost(request);
        Assert.assertEquals("200",reponse.getStatusCode());
    }
    @Test
    public  void test2(){

    }
}

