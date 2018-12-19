package com.dingdong.utils;

import com.dingdong.common.HttpClientReponse;
import com.dingdong.common.HttpClientRequest;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

public class HttpClientUtilInt {

    private static Logger logger = Logger.getLogger(HttpClientUtil.class);

    public HttpClientReponse doPost(HttpClientRequest request) {
        HttpClientReponse httpClientReponse = new HttpClientReponse();

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String url = request.getUrl();
        HttpUriRequest post = new HttpPost(url);

        List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
        valuePairs.add(new BasicNameValuePair("trackCode","0"));

        try {
            ((HttpPost) post).setEntity(new UrlEncodedFormEntity(valuePairs,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        Map<String, String> requestHeaders = request.getHeaders();
        for (String key : requestHeaders.keySet()) {
            post.setHeader(key, requestHeaders.get(key));
        }

        try {
            ((HttpPost) post).setEntity(new StringEntity(request.getBody()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            CloseableHttpResponse response = httpClient.execute(post);
            String statusCode = response.getStatusLine().toString().split(" ")[1];
            logger.info(response.getStatusLine().toString().split(" ")[1]);
            httpClientReponse.setStatusCode(statusCode);

            Header[] headers = response.getAllHeaders();
            Map<String, String> responseHeaders = new HashMap<String, String>();
            for (Header header : headers) {
                logger.info(header.getName() + ": " + header.getValue());
                responseHeaders.put(header.getName(), header.getValue());
            }
            httpClientReponse.setHeaders(requestHeaders);


            HttpEntity entity = response.getEntity();
            String body = IOUtils.toString(entity.getContent());
            //String body = EntityUtils.toString(response.getEntity());
            logger.info(body);
            httpClientReponse.setBody(body);

            httpClient.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpClientReponse;
    }

}