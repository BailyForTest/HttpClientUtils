package com.dingdong.common;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpUriRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientRequest {
    private String url;

    private HttpUriRequest httpMethod;

    private ArrayList<NameValuePair> paris;

    private Map<String,String> headers;

    private String body;

    public String getUrl() {
        return url;
    }

    public HttpUriRequest getHttpMethod() {
        return httpMethod;
    }

    public  ArrayList<NameValuePair> getParis() {
        return paris;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHttpMethod(HttpUriRequest httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setParis( ArrayList<NameValuePair> paris) {
        this.paris = paris;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
