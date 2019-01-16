package com.dingtone.utils;

import com.dingtone.common.HttpClientReponse;
import com.dingtone.common.HttpClientRequest;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


public class HttpClientUtil {
    private CloseableHttpClient httpClient;
    private static Logger logger =  Logger.getLogger(HttpClientUtil.class);

    //GET请求体
    public static  HttpClientReponse doGet(HttpClientRequest httpClientReques){
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        httpClientUtil.init();
        HttpGet httpGet = new HttpGet(httpClientReques.getUrl());
        return  httpClientUtil.sendRequest(httpGet,httpClientReques);
    }

    //POST请求体
    public static  HttpClientReponse doPost(HttpClientRequest httpClientReques){
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        httpClientUtil.init();
        HttpPost httpPost = new HttpPost(httpClientReques.getUrl());
        return  httpClientUtil.sendRequest(httpPost,httpClientReques);
    }

    //Delete请求体
    public static  HttpClientReponse doDelete(HttpClientRequest httpClientReques){
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        httpClientUtil.init();
        HttpDelete httpDelete = new HttpDelete(httpClientReques.getUrl());
        return  httpClientUtil.sendRequest(httpDelete,httpClientReques);
    }

    //Delete请求体
    public static  HttpClientReponse doPut(HttpClientRequest httpClientReques){
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        httpClientUtil.init();
        HttpPut httpPut = new HttpPut(httpClientReques.getUrl());
        return  httpClientUtil.sendRequest(httpPut,httpClientReques);
    }

    //HTTP连接
    private void init(){
         httpClient = HttpClientBuilder.create().build();
         logger.info("Start init http connection.");
    }

    //发送request请求
    private HttpClientReponse sendRequest(HttpRequestBase httpRequestBase, HttpClientRequest httpClientRequest){
        HttpClientReponse httpClientReponse = new HttpClientReponse();
        String url = httpClientRequest.getUrl();
        String encodingOfBody = "ISO-8859-1";

        //请求Headers
        Map<String, String> requestHeaders = httpClientRequest.getHeaders();
        for (String key : requestHeaders.keySet()) {
            httpRequestBase.setHeader(key, requestHeaders.get(key));
            if (key.toLowerCase().equals("Content-Type")){
                String contentType = requestHeaders.get(key);
                if (contentType.split(";").length>=2){
                    encodingOfBody = contentType.split(":")[1].split("=")[1];
                }
            }
        }


        //编码格式
        try {
            if(httpRequestBase instanceof  HttpEntityEnclosingRequestBase){
                ((HttpEntityEnclosingRequestBase)httpRequestBase).setEntity(new StringEntity(httpClientRequest.getBody()));
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("This encoding is not supported");
            logger.error(e.getMessage());
        }


        //Response返回值处理
        try {
            CloseableHttpResponse response = httpClient.execute(httpRequestBase);
            String statusCode = response.getStatusLine().toString().split(" ")[1];
            logger.info(response.getStatusLine().toString().split(" ")[1]);
            httpClientReponse.setStatusCode(statusCode);

            Header[] headers = response.getAllHeaders();
            Map<String, String> responseHeaders = new HashMap<String, String>();
            for (Header header : headers) {
                //logger.info(header.getName() + ": " + header.getValue());
                responseHeaders.put(header.getName(), header.getValue());
            }
            httpClientReponse.setHeaders(requestHeaders);


            HttpEntity entity = response.getEntity();
            String body = IOUtils.toString(entity.getContent());
            logger.info(body);

            httpClientReponse.setBody(body);

            this.close();
        } catch (ClientProtocolException e) {
            logger.error("This http protocol is not supported");
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return httpClientReponse;
    }

    //关闭HTTP连接
    private void close(){
        try {
            httpClient.close();
            logger.info("Close http connection successfully");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Close http connection failed");
            logger.error(e.getMessage());
        }
    }
}