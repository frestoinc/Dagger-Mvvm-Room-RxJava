package com.frestoinc.gojekassignment.data.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by frestoinc on 03,February,2020 for GoJekAssignment.
 */
public class MockGitHubData {

    public static JSONArray generateFakeData() {
        JSONArray array = new JSONArray();
        JSONObject object1 = new JSONObject();
        JSONObject object2 = new JSONObject();
        JSONObject object3 = new JSONObject();
        try {

            //data 1
            object1.put("author", "xingshaocheng");
            object1.put("name", "architect-awesome");
            object1.put("avatar", "https://github.com/xingshaocheng.png");
            object1.put("url", "https://github.com/xingshaocheng/architect-awesome");
            object1.put("description", "后端架构师技术图谱");
            object1.put("language", "Go");
            object1.put("languageColor", "#3572A5");
            object1.put("stars", 7333);
            object1.put("forks", 1546);
            object1.put("currentPeriodStars", 1528);

            JSONObject innerObject1 = new JSONObject();
            object1.put("href", "https://github.com/viatsko");
            object1.put("avatar", "https://avatars0.githubusercontent.com/u/376065");
            object1.put("username", "viatsko");

            JSONArray array1 = new JSONArray();
            array1.put(innerObject1);
            object1.put("builtBy", array1);

            //data 2
            object2.put("author", "google");
            object2.put("name", "gvisor");
            object2.put("avatar", "https://github.com/google.png");
            object2.put("url", "https://github.com/google/gvisor");
            object2.put("description", "Container Runtime Sandbox");
            object2.put("language", "Go");
            object2.put("languageColor", "#3572A5");
            object2.put("stars", 3346);
            object2.put("forks", 118);
            object2.put("currentPeriodStars", 1624);

            JSONObject innerObject2 = new JSONObject();
            object2.put("href", "https://github.com/viatsko");
            object2.put("avatar", "https://avatars0.githubusercontent.com/u/376065");
            object2.put("username", "viatsko");

            JSONArray array2 = new JSONArray();
            array2.put(innerObject2);
            object2.put("builtBy", array2);

            //data 3
            object3.put("author", "davideuler");
            object3.put("name", "architecture.of.internet-product");
            object3.put("avatar", "https://github.com/davideuler.png");
            object3.put("url", "https://github.com/davideuler/architecture.of.internet-product");
            object3.put("description", "互联网公司技术架构，微信/淘宝/腾讯/阿里/美团点评/百度/微博/Google/Facebook/Amazon/eBay的架构，欢迎PR补充");
            object3.put("language", "Go");
            object3.put("languageColor", "#3572A5");
            object3.put("stars", 2763);
            object3.put("forks", 416);
            object3.put("currentPeriodStars", 1427);

            JSONObject innerObject3 = new JSONObject();
            object3.put("href", "https://github.com/viatsko");
            object3.put("avatar", "https://avatars0.githubusercontent.com/u/376065");
            object3.put("username", "viatsko");

            JSONArray array3 = new JSONArray();
            array3.put(innerObject3);
            object1.put("builtBy", array3);

            array.put(object1);
            array.put(object2);
            array.put(object3);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
        return array;
    }
}