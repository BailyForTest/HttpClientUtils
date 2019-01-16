package com.dingtone.testcase.zf_web_dn;

import com.alibaba.fastjson.JSONObject;
import com.dingtone.common.HttpClientReponse;
import com.dingtone.common.HttpClientRequest;
import com.dingtone.parsing.Model;
import com.dingtone.utils.HttpClientUtil;
import com.dingtone.utils.ToJson;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class InsIdisDummy {

    @Test
    public void insidisdummy(long insid){

        HttpClientRequest request = new HttpClientRequest();
        request.setUrl("http://54.241.20.16:8080/dummy/v1/isDummy?trackCode=0");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);

        //set request body
        ToJson requestbody = new ToJson();
        requestbody.setInsId(insid);
        String Requestbody = JSONObject.toJSONString(requestbody);

        request.setBody(Requestbody);

        HttpClientReponse reponse = HttpClientUtil.doPost(request);
        Assert.assertEquals("200", reponse.getStatusCode());

        org.json.JSONObject jsonObject = new org.json.JSONObject(reponse.getBody());
        if(jsonObject.getString("Message").equals("This account is not dummy")){
            System.out.println("this is a original account");
        }
        else{
            System.out.println("this is a flick account");
            InsIdInfo insIdInfo = new InsIdInfo();
            insIdInfo.insidinfo(insid);
        }
    }

    public static void main(String[] args) {
        InsIdisDummy insIdisDummy = new InsIdisDummy();
        insIdisDummy.insidisdummy(9467099411L);
    }
}
