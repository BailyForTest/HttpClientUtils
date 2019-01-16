package com.dingtone.testcase.zf_app_dn;

import com.alibaba.fastjson.JSONObject;
import com.dingtone.common.FileWriterTest;
import com.dingtone.common.HttpClientReponse;
import com.dingtone.common.HttpClientRequest;
import com.dingtone.parsing.Model;
import com.dingtone.utils.HttpClientUtil;
import com.dingtone.utils.ToJson;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TaskPull {

    //原生账号
    @Test
    public void test1(){

        HttpClientRequest request = new HttpClientRequest();
        request.setUrl("http://54.241.20.16:8080/task/v2/pull?trackCode=0");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);

        //set request body
        ToJson biz_content = new ToJson();
        biz_content.setCustomId(7182012637346203L);
        biz_content.setDeviceId("And.694299dae58bae08a728db64ddd26bdf.dttalk");
        biz_content.setCc(1);
        biz_content.setInsId(9467099411L);
        String Biz_content = JSONObject.toJSONString(biz_content);
        //System.out.println(Biz_content);


        ToJson gettaskpullbody = new ToJson();
        gettaskpullbody.setAppId("me.dingtone");
        gettaskpullbody.setTimestamp(12345L);
        gettaskpullbody.setSign_type("RSA");
        gettaskpullbody.setSign("");
        gettaskpullbody.setBiz_content(Biz_content);
        String Gettaskpullbody = JSONObject.toJSONString(gettaskpullbody);
        //System.out.println(Gettaskpullbody);

        request.setBody(Gettaskpullbody);

        HttpClientReponse reponse = HttpClientUtil.doPost(request);
        Assert.assertEquals("200", reponse.getStatusCode());

        //读取存在本地的订单单号
        FileWriterTest fileWriterTest = new FileWriterTest();
        String str1 = fileWriterTest.rdFile("F:\\File\\OrderId.txt");
        String[] str = str1.split(";");
        for(String s :str){
            Long id =Long.valueOf(s);
            //System.out.println(id);

            //将获取到的orderid 存入到数组中
            Model model = com.alibaba.fastjson.JSONObject.parseObject(reponse.getBody(), Model.class);
            for(int i=0;i<7;i++){
                long[] arr = new long[7] ;
                arr[i] = model.getData().getTasks().get(i).getOrderId();
                //System.out.println(arr[i]);
                if(arr[i]==id){
                    System.out.println("find the orderid  "+arr[i]);
                    continue;
                }
            }
        }
    }

    //flicker账号
    @Test
    public void test2(){
        HttpClientRequest request = new HttpClientRequest();
        request.setUrl("http://54.241.20.16:8080/task/v2/pull?trackCode=0");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);

        //set request body
        ToJson biz_content = new ToJson();
        biz_content.setCustomId(7182012637342603L);
        biz_content.setDeviceId("And.59cd2f1ec95f4aef25f8b6c8ed74c3ac.dttalk");
        biz_content.setCc(1);
        biz_content.setInsId(9610360797L);
        String Biz_content = JSONObject.toJSONString(biz_content);
        //System.out.println(Biz_content);

        ToJson gettaskpullbody = new ToJson();
        gettaskpullbody.setAppId("me.dingtone");
        gettaskpullbody.setTimestamp(12345L);
        gettaskpullbody.setSign_type("RSA");
        gettaskpullbody.setSign("");
        gettaskpullbody.setBiz_content(Biz_content);
        String Gettaskpullbody = JSONObject.toJSONString(gettaskpullbody);
        //System.out.println(Gettaskpullbody);

        request.setBody(Gettaskpullbody);

        HttpClientReponse reponse = HttpClientUtil.doPost(request);
        Assert.assertEquals("200", reponse.getStatusCode());

        Model model = com.alibaba.fastjson.JSONObject.parseObject(reponse.getBody(), Model.class);
        FileWriterTest fileWriterTest = new FileWriterTest();

        fileWriterTest.clearFile("F:\\File\\app_dn\\getpull\\responsebody.txt");
        fileWriterTest.rwFile("F:\\File\\app_dn\\getpull\\responsebody.txt",reponse.getBody());


        //读取存在本地的订单单号
        String str1 = fileWriterTest.rdFile("F:\\File\\app_dn\\getorderlist\\orderid.txt");
        String[] str = str1.split(";");

        for(String s :str) {
            Long id = Long.valueOf(s);
            //System.out.println(id);
             for (int i = 0; i < 7; i++) {
                long[] arr = new long[7];
                arr[i] = model.getData().getTasks().get(i).getOrderId();
                System.out.println(arr[i]);
                if (arr[i] == id) {
                    System.out.println("find the orderid  " + arr[i]);
                    continue;
                }
            }

        }
    }


}
