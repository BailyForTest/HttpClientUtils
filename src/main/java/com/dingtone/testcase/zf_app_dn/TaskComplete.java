package com.dingtone.testcase.zf_app_dn;

import com.alibaba.fastjson.JSONObject;
import com.dingtone.common.HttpClientReponse;
import com.dingtone.common.HttpClientRequest;
import com.dingtone.utils.HttpClientUtil;
import com.dingtone.utils.ToJson;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Test(groups = "TaskComplete")
public class TaskComplete {
    //SDK任务拉取接口
    public void test1() {

        HttpClientRequest request = new HttpClientRequest();
        request.setUrl("http://54.241.20.16:8080/task/v2/complete?trackCode=0");

        Map<String, String> requestHeader = new HashMap<String, String>();
        requestHeader.put("X-G-TOKEN", "0");
        requestHeader.put("Content-Type", "application/json");
        request.setHeaders(requestHeader);

        ToJson mediaInfo = new ToJson();
        mediaInfo.setProfilePicUrl("https://scontent-sjc3-1.cdninstagram.com/vp/c15a84997c58ee1ef85f2121e34a618b/5BE52AB9/t51.2885-19/11906333_1637934709787635_1429991507_a.jpg");
        mediaInfo.setUserName("education");
        mediaInfo.setUserId(7228229718L);
        String MediaInfo = JSONObject.toJSONString(mediaInfo);
        //System.out.println("mediaInfo json -->：" + MediaInfo);

        ToJson extra = new ToJson();
        extra.setChannel("instagram");
        extra.setCount(15);
        extra.setType("COMMENT");
        extra.setMediaInfo(MediaInfo);
        String Extra = JSONObject.toJSONString(extra);
        //System.out.println("Extra："+Extra);

        ToJson tasks = new ToJson();
        tasks.setTaskId(859490116937044556l);
        tasks.setOwner("");
        tasks.setReceiveTime(0L);
        tasks.setExpireTime(0L);
        tasks.setAppType("instagram");
        tasks.setReceiver("8799314413958");
        tasks.setTaskType("COMMENT");
        tasks.setExtra(Extra);
        tasks.setPrice(2D);
        tasks.setOrderId(201807130071863L);
        String Tasks = JSONObject.toJSONString(tasks);
        System.out.println("Tasks："+Tasks);

        ToJson biz_content = new ToJson();
        List<String> list = new ArrayList<>();
        list.add(Tasks);
        biz_content.setCc(1);
        biz_content.setBatchId("7182012637346203");
        biz_content.setDeviceId("And.694299dae58bae08a728db64ddd26bdf.dttalk");
        biz_content.setCustomId(7182012637346203L);
        biz_content.setCompleteType("1");
        biz_content.setInsId(7182012637346203L);
        String Biz_content = JSONObject.toJSONString(biz_content);
        System.out.println("Biz_content：" + Biz_content);

        ToJson toJson = new ToJson();
        toJson.setAppId("me.dingtone");
        toJson.setTimestamp(12345l);
        toJson.setSign_type("RSA");
        toJson.setSign("");
        toJson.setBiz_content(Biz_content);
        String getcompletejsonbody = JSONObject.toJSONString(toJson);
        System.out.println("getcompletejsonbody：" + getcompletejsonbody);


        //System.out.println(jsonString4);
        request.setBody(getcompletejsonbody);

        HttpClientReponse reponse = HttpClientUtil.doPost(request);
        Assert.assertEquals("200", reponse.getStatusCode());

    }


}
