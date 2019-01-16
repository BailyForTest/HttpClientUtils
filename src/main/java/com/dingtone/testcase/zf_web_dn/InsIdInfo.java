package com.dingtone.testcase.zf_web_dn;

import com.alibaba.fastjson.JSONObject;
import com.dingtone.common.HttpClientReponse;
import com.dingtone.common.HttpClientRequest;
import com.dingtone.parsing.Model;
import com.dingtone.utils.HttpClientUtil;
import com.dingtone.utils.ToJson;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class InsIdInfo {
    @Test
    public void insidinfo(long insid){

        HttpClientRequest request = new HttpClientRequest();
        request.setUrl("http://54.241.20.16:8080/dummy/v1/bind?trackCode=0");

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

        Model model = com.alibaba.fastjson.JSONObject.parseObject(reponse.getBody(), Model.class);

    }

}
