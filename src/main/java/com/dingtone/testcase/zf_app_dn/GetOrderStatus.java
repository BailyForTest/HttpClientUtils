package com.dingtone.testcase.zf_app_dn;

import com.alibaba.fastjson.JSONObject;
import com.dingtone.common.FileWriterTest;
import com.dingtone.common.HttpClientReponse;
import com.dingtone.common.HttpClientRequest;
import com.dingtone.parsing.Model;
import com.dingtone.utils.HttpClientUtil;
import com.dingtone.utils.ToJson;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GetOrderStatus {

    private static Logger logger = Logger.getLogger(HttpClientUtil.class);

    @Test(groups = "GetOrderStatus")
    public void getorderstatus(){
        HttpClientRequest request = new HttpClientRequest();
        FileWriterTest fileWriterTest = new FileWriterTest();

        request.setUrl("http://54.241.20.16:8080/trade/v1/getOrderStatus?trackCode=0");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);

      //set request body
        ToJson biz_content = new ToJson();
        biz_content.setAppId("us.superlikers.iapp");
        biz_content.setUid(8799314256331L);
        String getorderid = fileWriterTest.rdFile("F:\\File\\app_dn\\getorderlist\\orderid.txt");
        String[] orderid = getorderid.split(";");

        List<Long> list = new ArrayList<>();
        for(int i=0;i<orderid.length;i++){
            Long[] id =new Long[orderid.length];
            id[i] = Long.valueOf(orderid[i]);
            list.add(id[i]);
            biz_content.setOrders(list);
        }
        String Biz_content = JSONObject.toJSONString(biz_content);
       // System.out.println(Biz_content);

        ToJson getordeestatus = new ToJson();
        getordeestatus.setAppId("us.superlikers.iapp");
        getordeestatus.setTimestamp(12345L);
        getordeestatus.setSign_type("RSA");
        getordeestatus.setSign("");
        getordeestatus.setBiz_content(Biz_content);
        String Getordeestatus = JSONObject.toJSONString(getordeestatus);
        //System.out.println(Getordeestatus);

        request.setBody(Getordeestatus);

        HttpClientReponse reponse = HttpClientUtil.doPost(request);
        Assert.assertEquals("200", reponse.getStatusCode());

        //清除F:\File\OrderId.txt中的数据,重新写入任务进度信息
        //fileWriterTest.clearFile("F:\\File\\TaskInfo.txt");
        fileWriterTest.rwFile("F:\\File\\app_dn\\orderstatus\\reponsebody.txt",reponse.getBody());

        Model model = com.alibaba.fastjson.JSONObject.parseObject(reponse.getBody(), Model.class);




        /*for(int i=0;i<list.size();i++){
            String id = String.valueOf(model.getData().getUserOrders().get(i).getOrderId());
            fileWriterTest.rwFile("F:\\File\\TaskInfo.txt",id);
            fileWriterTest.rwFile("F:\\File\\TaskInfo.txt",model.getData().getUserOrders().get(i).getTaskInfo());
        }*/

    }

    /*public void test2(){
        HttpClientRequest request = new HttpClientRequest();
        FileWriterTest fileWriterTest = new FileWriterTest();

        request.setUrl("http://54.241.20.16:8080/trade/v1/getOrderStatus?trackCode=0");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);

        //set request body
        ToJson biz_content = new ToJson();
        biz_content.setAppId("us.superlikers.iapp");
        biz_content.setUid(8799314256310L);
        String getorderid = fileWriterTest.rdFile("F:\\File\\OrderId.txt");
        String[] orderid = getorderid.split(";");

        List<Long> list = new ArrayList<>();

      *//*  for(int i=0;i<orderid.length;i++){
            Long[] id =new Long[orderid.length];
            id[i] = Long.valueOf(orderid[i]);
            list.add(id[i]);
           // biz_content.setOrders(list);
        }*//*

        biz_content.setOrders(list);
        String Biz_content = JSONObject.toJSONString(biz_content);
        // System.out.println(Biz_content);

        ToJson getordeestatus = new ToJson();
        getordeestatus.setAppId("us.superlikers.iapp");
        getordeestatus.setTimestamp(12345L);
        getordeestatus.setSign_type("RSA");
        getordeestatus.setSign("");
        getordeestatus.setBiz_content(Biz_content);
        String Getordeestatus = JSONObject.toJSONString(getordeestatus);
        //System.out.println(Getordeestatus);

        request.setBody(Getordeestatus);

        HttpClientReponse reponse = HttpClientUtil.doPost(request);
        Assert.assertEquals("200", reponse.getStatusCode());

        Model model = com.alibaba.fastjson.JSONObject.parseObject(reponse.getBody(), Model.class);
        if(model.getData().getUserOrders().isEmpty()==true){
            logger.info("not find the orderid");
        }
        else{logger.info("find the orderid");}
    }*/

}
