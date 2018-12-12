package com.dingdong.common;

import org.apache.http.client.methods.HttpUriRequest;

import java.util.Map;

public class HttpClientRequest {
    private String url;

    private HttpUriRequest httpMethod;

    private Map<String,String> Paramter;

    private Map<String,String> headers;

    private String body;

    public String getUrl() {
        return url;
    }

    public HttpUriRequest getHttpMethod() {
        return httpMethod;
    }

    public Map<String, String> getParamter() {
        return Paramter;
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

    public void setParamter(Map<String, String> paramter) {
        this.Paramter = paramter;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
