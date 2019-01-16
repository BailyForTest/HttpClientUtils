package com.dingtone.testcase.zf_web_dn;

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

public class TaskPullTest {

    FileWriterTest fileWriterTest = new FileWriterTest();
    private long GanfollowerOrderid = 201901090016421L;
    //private long HahtagOrderid=201901070003871L;
      private long HahtagOrderid=201901140001922L;


    @Test(testName = "original account")
    public void test1(){
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

        Model model = com.alibaba.fastjson.JSONObject.parseObject(reponse.getBody(), Model.class);

        //判断拉取到的任务个数
        String str = reponse.getBody().toString();
        int fromIndex = 0;
        int count = 0;
        while(true){
            int index = str.indexOf("orderId", fromIndex);
            if(-1 != index){
                fromIndex = index + 1;
                count++;
            }else{
                break;
            }
        }

        //循环判断拉取到的任务orderid是否和定制单一致
        if(count>1){
            for (int i = 0; i < count; i++) {
                long[] arr = new long[count];
                arr[i] = model.getData().getTasks().get(i).getOrderId();
                //System.out.println(arr[i]);
                if (arr[i] == GanfollowerOrderid) {
                    System.out.println("find the orderid  " + arr[i]);
                    fileWriterTest.rwFile("F:\\File\\test.txt","find the orderid  " + arr[i]);
                    continue;
                }
            }
            //test1();
        }
        else{
            if(fileWriterTest.rdFile("F:\\File\\test.txt").equals("")){
                System.out.println("no tasks to get");
            }
            else {
                System.out.println("find the orderid"+fileWriterTest.rdFile("F:\\File\\test.txt"));
            }
        }

    }

    @Test(testName = "flick account")//性别不符合
    public void test2(){
        HttpClientRequest request = new HttpClientRequest();
        request.setUrl("http://54.241.20.16:8080/task/v2/pull?trackCode=0");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);

        //set request body
        ToJson biz_content = new ToJson();
        biz_content.setCustomId(145138272809025L);
        biz_content.setDeviceId("And.1c164d016c2f5c4d1ae51f1512d4007b.dttalk");
        biz_content.setCc(1);
        biz_content.setInsId(9702403042L);
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

        for (int i = 0; i < 7; i++) {
            long[] arr = new long[7];
            arr[i] = model.getData().getTasks().get(i).getOrderId();
            System.out.println(arr[i]);
            if (arr[i] == HahtagOrderid) {
                System.out.println("find the orderid  " + arr[i]);
                continue;
            }
        }
    }

    @Test(testName = "flick account")//性别符合，国家不符合
    public void test3(){
        HttpClientRequest request = new HttpClientRequest();
        request.setUrl("http://54.241.20.16:8080/task/v2/pull?trackCode=0");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);

        //set request body
        ToJson biz_content = new ToJson();
        biz_content.setCustomId(145138275500640L);
        biz_content.setDeviceId("And.694299dae58bae08a728db64ddd26bdf.dttalk");
        biz_content.setCc(1);
        biz_content.setInsId(9656717471L);
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

        for (int i = 0; i < 7; i++) {
            long[] arr = new long[7];
            arr[i] = model.getData().getTasks().get(i).getOrderId();
            System.out.println(arr[i]);
            if (arr[i] == GanfollowerOrderid) {
                System.out.println("find the orderid  " + arr[i]);
                continue;
            }
        }
    }

    @Test(testName = "flick account")//性别符合，国家符合
    public void test4() {
        HttpClientRequest request = new HttpClientRequest();
        request.setUrl("http://54.241.20.16:8080/task/v2/pull?trackCode=0");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);

        //set request body
        ToJson biz_content = new ToJson();
        biz_content.setCustomId(7182012637347116L);
        biz_content.setDeviceId("And.3153768f0b0b11e987b02c56dc483296.dttalk");
        biz_content.setCc(1);
        biz_content.setInsId(9383663986L);
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

        String str = reponse.getBody().toString();
        int fromIndex = 0;
        int count = 0;
        while(true){
            int index = str.indexOf("orderId", fromIndex);
            if(-1 != index){
                fromIndex = index + 1;
                count++;
            }else{
                break;
            }
        }
        //System.out.println(count);
        if(count>1){
            for (int i = 0; i < count; i++) {
                long[] arr = new long[count];
                arr[i] = model.getData().getTasks().get(i).getOrderId();
                //System.out.println(arr[i]);
                if (arr[i] == GanfollowerOrderid) {
                    System.out.println("find the GanfollowerOrderid  " + arr[i]);
                    fileWriterTest.rwFile("F:\\File\\test1.txt","");
                    continue;
                }
                else if (arr[i] == HahtagOrderid){
                    System.out.println("find the HahtagOrderid  " + arr[i]);
                    fileWriterTest.rwFile("F:\\File\\test1.txt","");
                    continue;
                }
            }
            test4();
        }
        else{
            if(fileWriterTest.rdFile("F:\\File\\test1.txt").equals("")){
                System.out.println("no tasks to get");
            }
            else {
                System.out.println(fileWriterTest.rdFile("F:\\File\\test1.txt"));
            }
        }

    }

}
