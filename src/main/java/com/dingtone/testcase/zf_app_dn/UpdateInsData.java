package com.dingtone.testcase.zf_app_dn;

import com.dingtone.common.HttpClientRequest;
import com.dingtone.utils.HttpClientUtil;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class UpdateInsData {

    private static Logger logger = Logger.getLogger(HttpClientUtil.class);

    public void test1(){
        HttpClientRequest request = new HttpClientRequest();

        request.setUrl("http://54.241.20.16:8080/trade/v1/getOrderList?trackCode=0");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);



    }



}
