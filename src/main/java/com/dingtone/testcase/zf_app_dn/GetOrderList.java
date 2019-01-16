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

import java.util.HashMap;
import java.util.Map;


public class GetOrderList {

    private static Logger logger = Logger.getLogger(HttpClientUtil.class);

    @Test
    public void getAccountOrderList() {
        HttpClientRequest request = new HttpClientRequest();

        request.setUrl("http://54.241.20.16:8080/trade/v1/getOrderList?trackCode=0");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);

        ToJson biz_content = new ToJson();
        biz_content.setAppId("us.superlikers.iapp");
        biz_content.setUid(8799314256331L);
        biz_content.setPageIndex(0);
        biz_content.setPageSize(10);
        String Biz_content = JSONObject.toJSONString(biz_content);
        //System.out.println(Biz_content);

        ToJson getorderlistbody = new ToJson();
        getorderlistbody.setAppId("us.superlikers.iapp");
        getorderlistbody.setSign_type("RSA");
        getorderlistbody.setTimestamp(12345L);
        getorderlistbody.setSign("");
        ;
        getorderlistbody.setBiz_content(Biz_content);
        String Getorderlistbody = JSONObject.toJSONString(getorderlistbody);
        System.out.println(Getorderlistbody);

        request.setBody(Getorderlistbody);


        HttpClientReponse reponse = HttpClientUtil.doPost(request);
        Assert.assertEquals("200", reponse.getStatusCode());

        //清除F:\File\OrderId.txt中的数据
        FileWriterTest fileWriterTest = new FileWriterTest();
        fileWriterTest.clearFile("F:\\File\\app_dn\\getorderlist\\orderid.txt");
        fileWriterTest.rwFile("F:\\File\\app_dn\\getorderlist\\rsponsebody.txt",reponse.getBody());

        //循环输出账号下的订单号
        Model model = com.alibaba.fastjson.JSONObject.parseObject(reponse.getBody(), Model.class);

        if (model.getData().getTotal() != 0) {
            logger.info("Account have orderid");
        } else {
            logger.info("Account have no orderid");
        }

        for (int i = 0; i < model.getData().getTotal(); i++) {
            long[] orderid = new long[model.getData().getTotal()];
            String[] gender = new String[model.getData().getTotal()];
            orderid[i] = model.getData().getUserOrders().get(i).getOrderId();
            System.out.println("第"+(i+1)+"个订单号："+orderid[i]);
            String id = String.valueOf(orderid[i]);
            fileWriterTest.rwFile("F:\\File\\app_dn\\getorderlist\\orderid.txt", id + ";");
        }
    }

    /*@Test
    public void sendFailUid() {
        HttpClientRequest request = new HttpClientRequest();

        request.setUrl("http://54.241.20.16:8080/trade/v1/getOrderList?trackCode=0");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);

        ToJson biz_content = new ToJson();
        biz_content.setAppId("us.superlikers.iapp");
        biz_content.setUid(7382719328L);
        biz_content.setPageIndex(0);
        biz_content.setPageSize(10);
        String Biz_content = JSONObject.toJSONString(biz_content);
        //System.out.println(Biz_content);

        ToJson getorderlistbody = new ToJson();
        getorderlistbody.setAppId("us.superlikers.iapp");
        getorderlistbody.setSign_type("RSA");
        getorderlistbody.setTimestamp(12345L);
        getorderlistbody.setSign("");

        getorderlistbody.setBiz_content(Biz_content);
        String Getorderlistbody = JSONObject.toJSONString(getorderlistbody);
        //System.out.println(Getorderlistbody);

        request.setBody(Getorderlistbody);


        HttpClientReponse reponse = HttpClientUtil.doPost(request);
        Assert.assertEquals("200", reponse.getStatusCode());

        //判断输出
        Model model = com.alibaba.fastjson.JSONObject.parseObject(reponse.getBody(), Model.class);
        String total =String.valueOf(model.getData().getTotal());
        try{
            Assert.assertEquals("0", total);
        }catch (Exception e){
            logger.info("Tes3 select orderlist Fail");
        }
        if (model.getData().getTotal() != 0) {
            logger.info("Test2 select orderlist success");
        } else {
            logger.info("Test2 select orderlist Fail");
        }
    }

    @Test
    public void sendFailAppid() {
        HttpClientRequest request = new HttpClientRequest();

        request.setUrl("http://54.241.20.16:8080/trade/v1/getOrderList?trackCode=0");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);

        ToJson biz_content = new ToJson();
        biz_content.setAppId("us.superliker.iapp");
        biz_content.setUid(7382719328L);
        biz_content.setPageIndex(0);
        biz_content.setPageSize(10);
        String Biz_content = JSONObject.toJSONString(biz_content);
        //System.out.println(Biz_content);

        ToJson getorderlistbody = new ToJson();
        getorderlistbody.setAppId("us.superliker.iapp");
        getorderlistbody.setSign_type("RSA");
        getorderlistbody.setTimestamp(12345L);
        getorderlistbody.setSign("");
        ;
        getorderlistbody.setBiz_content(Biz_content);
        String Getorderlistbody = JSONObject.toJSONString(getorderlistbody);
        //System.out.println(Getorderlistbody);

        request.setBody(Getorderlistbody);


        HttpClientReponse reponse = HttpClientUtil.doPost(request);
        Assert.assertEquals("200", reponse.getStatusCode());

        //判断输出
        Model model = com.alibaba.fastjson.JSONObject.parseObject(reponse.getBody(), Model.class);
        String total =String.valueOf(model.getData().getTotal());
        try{
            Assert.assertEquals("0", total);
        }catch (Exception e){
           logger.info("Tes3 select orderlist Fail");
        }
        if (model.getData().getTotal() != 0) {
            logger.info("Test3 select orderlist success");
        } else {
            logger.info("Tes3 select orderlist Fail");
        }
    }*/
}