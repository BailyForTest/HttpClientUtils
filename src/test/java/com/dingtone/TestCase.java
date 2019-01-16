package com.dingtone;

import com.dingtone.common.HttpClientReponse;
import com.dingtone.common.HttpClientRequest;
import com.dingtone.parsing.Model;
import com.dingtone.utils.HttpClientUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestCase {

    //获取应用商品信息
    @Test
    public void getListProducts(){
            HttpClientRequest request = new HttpClientRequest();

            request.setUrl("http://54.241.20.16:8080/product/v2/app/listProducts?uid=8799314256310&appId=us.superlikers.iapp&isoCountryCode=CN&channel=apple&trackCode=0");

            Map<String, String> requestHeader = new HashMap<String, String>();
            requestHeader.put("X-G-TOKEN", "0");
            request.setHeaders(requestHeader);

            HttpClientReponse reponse = HttpClientUtil.doGet(request);
            Assert.assertEquals("200", reponse.getStatusCode());
    }

    //获取订单信息
    @Test()
    public void getOrderList(){
        HttpClientRequest request = new HttpClientRequest();

        request.setUrl("http://54.241.20.16:8080/trade/v1/getOrderList?trackCode=0");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);

        request.setBody("{\n" +
                "    \"appId\":\"us.superlikers.iapp\", \n" +
                "    \"timestamp\":12345,  \n" +
                "    \"sign_type\":\"RSA\", \n" +
                "    \"sign\":\"\", \n" +
                "    \"biz_content\":\"{\\\"appId\\\":\\\"us.superlikers.iapp\\\",\\\"uid\\\":\\\"8799314256328\\\",\\\"pageIndex\\\":0,\\\"pageSize\\\":10}\"\n" +
                "    }");

        HttpClientReponse reponse = HttpClientUtil.doPost(request);
        Assert.assertEquals("200", reponse.getStatusCode());

        //获取当前账号下的订单信息
        Model model = com.alibaba.fastjson.JSONObject.parseObject(reponse.getBody(), Model.class);
        for(int i=0 ;i<model.getData().getTotal();i++){
            long[] orderid = new long[model.getData().getTotal()];
            String[] gender = new String[model.getData().getTotal()];
            orderid[i] = model.getData().getUserOrders().get(i).getOrderId();
            System.out.println(orderid[i]);
        }
    }

    //订单任务完成回调接口
    @Test(dependsOnMethods = "getOrderList")
    public void getOrderFinished(){
        HttpClientRequest request = new HttpClientRequest();

        request.setUrl("http://54.241.20.16:8080/trade/private/v1/OrderFinished?trackCode=0");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);

        request.setBody("{{\n" +
                "    \"appId\":\"us.superlikers.iapp\", \n" +
                "    \"timestamp\":12345,  \n" +
                "    \"sign_type\":\"RSA\", \n" +
                "    \"sign\":\"\", \n" +
                "    \"biz_content\":\"{\\\"appId\\\":\\\"us.superlikers.iapp\\\",\\\"orderId\\\":\\\"201812210019045\\\"}\"\n" +
                "    }");

        HttpClientReponse reponse = HttpClientUtil.doPost(request);
        Assert.assertEquals("200", reponse.getStatusCode());
    }

    //根据订单id获取订单状态
    @Test
    public void getOrderStatus(){
        HttpClientRequest request = new HttpClientRequest();

        request.setUrl("http://54.241.20.16:8080/trade/v1/getOrderStatus?trackCode=0&appId=us.superlikers.iapp&orderId=201812210019045");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);

        request.setBody("{\n" +
                "    \"appId\":\"us.superlikers.iapp\", \n" +
                "    \"timestamp\":12345,  \n" +
                "    \"sign_type\":\"RSA\", \n" +
                "    \"sign\":\"\", \n" +
                "    \"biz_content\":\"{\\\"appId\\\":\\\"us.superlikers.iapp\\\",\\\"uid\\\":\\\"8799314256310\\\",\\\"orders\\\":\\\"201812250007925\\\",}\"\n" +
                "    }");

        HttpClientReponse reponse = HttpClientUtil.doPost(request);
        Assert.assertEquals("200", reponse.getStatusCode());
    }

    //修改订单tag内容接口
    @Test
    public void updateOrder(){
        HttpClientRequest request = new HttpClientRequest();

        request.setUrl("http://54.241.20.16:8080/trade/v1/updateOrdere?trackCode=0");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);

        request.setBody("{\"appId\":\"me.getinsta\", \n" +
                "   \"timestamp\":12345,  \n" +
                "   \"sign_type\":\"RSA\", \n" +
                "    \"sign\":\"\", \n" +
                "    \"biz_content\":\"{\\\"orderId\\\":\\\"2017101800405-1\\\",\\\"deliverInfo\\\":\\\"deliverInfo\\\"}\"}");

        HttpClientReponse reponse = HttpClientUtil.doGet(request);
        Assert.assertEquals("200", reponse.getStatusCode());
    }

    //SDK拉取任务列表接口
    @Test
    public void getpull(){
        HttpClientRequest request = new HttpClientRequest();

        request.setUrl("http://54.241.20.16:8080/task/v2/pull?trackCode=0");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);

        request.setBody("{\n" +
                "  \"appId\":\"me.dingtone\", \n" +
                "  \"timestamp\":12345,  \n" +
                "  \"sign_type\":\"RSA\", \n" +
                "  \"sign\":\"\", \n" +
                "  \"biz_content\":\"{\\\"customId\\\":\\\"7182012637346203\\\",\\\"deviceId\\\":\\\"And.694299dae58bae08a728db64ddd26bdf.dttalk\\\",\\\"cc\\\":\\\"1\\\",\\\"insId\\\":\\\"9467099411\\\"}\"\n" +
                "}");

        HttpClientReponse reponse = HttpClientUtil.doPost(request);
        Assert.assertEquals("200", reponse.getStatusCode());

        Model model = com.alibaba.fastjson.JSONObject.parseObject(reponse.getBody(), Model.class);
        for(int i=0;i<=6;i++){
            if(model.getData().getTasks().get(i).getPurchaseAppId().equals("us.superlikers.iapp")){
                System.out.println(model.getData().getTasks().get(i).getOrderId());
                model.getData().getTasks().get(i).getOrderId();
                if(model.getData().getTasks().get(i).getOrderId()==201812250007925L){
                    System.out.println("find the OrderId");
                }

                //System.out.println(model.getData().getTasks().get(i).getTaskType());
            }
            }
    }

    //SDK任务完成汇报接口
    @Test
    public void getcomplete(){
        HttpClientRequest request = new HttpClientRequest();

        request.setUrl("http://54.241.20.16:8080/task/v1/complete?trackCode=0");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);

        request.setBody(" {\n" +
                "    \"appId\":\"me.dingtone\", \n" +
                "    \"timestamp\":12345, \n" +
                "    \"sign_type\":\"RSA\", \n" +
                "    \"sign\":\"\", \n" +
                "    \"biz_content\":\n" +
                "\"{\\\"cc\\\":\\\"1\\\",\\\"batchId\\\":\\\"22\\\",\\\"deviceId\\\":\\\"And.694299dae58bae08a728db64ddd26bdf.dttalk\\\",\\\"customId\\\":\\\"13424354\\\",\\\"completeType\\\":1,\\\"tasks\\\":[{ \\\"taskId\\\": 1, \\\"owner\\\": \\\"1234@1234.com\\\", \\\"receiveTime\\\": 0, \\\"expireTime\\\": 0, \\\"appType\\\": \\\"instagram\\\",  \\\"receiver\\\": null, \\\"taskType\\\": \\\"FOLLOW\\\",\\\"extra\\\": \\\"{\\\\\\\"channel\\\\\\\":\\\\\\\"instagram\\\\\\\",\\\\\\\"count\\\\\\\":1,\\\\\\\"type\\\\\\\":\\\\\\\"FOLLOW\\\\\\\",\\\\\\\"mediaInfo\\\\\\\":{\\\\\\\"profilePicUrl\\\\\\\":\\\\\\\"https://www.amazon-west-1.djadndsdi\\\\\\\",\\\\\\\"userName\\\\\\\":\\\\\\\"newName\\\\\\\",\\\\\\\"userId\\\\\\\":\\\\\\\"562155214556\\\\\\\"}}\\\", \\\"price\\\": null,  \\\"orderId\\\": \\\"-6397176842693822448\\\"}]}\" \n" +
                " }");

        HttpClientReponse reponse = HttpClientUtil.doPost(request);
        Assert.assertEquals("200", reponse.getStatusCode());

        Model model = com.alibaba.fastjson.JSONObject.parseObject(reponse.getBody(), Model.class);
        for(int i=0;i<=6;i++){
            if(model.getData().getTasks().get(i).getPurchaseAppId().equals("us.superlikers.iapp")){
                System.out.println(model.getData().getTasks().get(i).getOrderId());
            }
        }

    }






}
