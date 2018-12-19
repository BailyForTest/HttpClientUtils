package com.dingdong.common;

import org.json.*;

public class JsonObject {

    public void GetInt(String respone,String Int) {
        JSONObject jsonObject = new JSONObject(respone);
        jsonObject.getInt(Int);
    }

    public void getString(String respone,String str) {
        JSONObject jsonObject = new JSONObject(respone);
        jsonObject.getString(str);
    }

    public void getJSONObject(String respone,String jsonobject) {
        JSONObject jsonObject = new JSONObject(respone);
        jsonObject.getJSONObject(jsonobject);
    }

}
