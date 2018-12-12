package com.dingdong.common;

import com.google.gson.JsonArray;
import org.json.*;

import javax.xml.transform.Result;

public class JsonObject {

    public void GetJsonObject(String Body) {
        //服务器返回的JSON数据
        JSONObject jsonObject = new JSONObject(Body);
        try {
            //从JSON中得到字符串
            Integer Result = jsonObject.getInt("Result");
            String Message = jsonObject.getString("Message");
            System.out.println(Result);
            System.out.println(Message);

            //从JSON中得到JSONArray,并且遍历
           // JSONArray jsonArray = jsonObject.getJSONArray("data");

     /*       for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject everyJsonObject = jsonArray.getJSONObject(i);
                String category_id = everyJsonObject.getString("id");
                String category_uid = everyJsonObject.getString("uid");
                String category_photoUrl = everyJsonObject.getString("photoUrl");
                String useCount = everyJsonObject.getString("useCount");
                System.out.println("=====================================================");
            }*/

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
