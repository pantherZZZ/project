package com.th.screen.until;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.th.screen.entity.RequestData;
import okhttp3.*;
import org.springframework.stereotype.Component;


@Component
public class Test {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    public static void managementScreen(boolean result) {
        //如果任务是开启的状态先操作是开屏状态
        Map<String, Object> openScreenMap = new LinkedHashMap<>();
        openScreenMap.put("type", "callCardService");
        openScreenMap.put("fn", "setScreenOpen");
        openScreenMap.put("arg1", result); //true开屏  false关屏
        Gson gson = new Gson();
        String jsonObject = gson.toJson(openScreenMap);

        String url = "http://101.132.245.105:9000/command/l20-721-02111";
        Test test = new Test();
        try {
            String y = test.post(url, jsonObject);
            System.out.println("开屏================" + y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void closeScreen() {
//        //如果任务是开启的状态先操作是开屏状态
//        Map<String, Object> closeScreenMap = new LinkedHashMap<>();
//        closeScreenMap.put("type", "callCardService");
//        closeScreenMap.put("fn", "setScreenOpen");
//        closeScreenMap.put("arg1", false);
//        Gson gson = new Gson();
//        String jsonObject = gson.toJson(closeScreenMap);
//
//        String url = "http://101.132.245.105:9000/command/l20-721-02111";
//        Test test = new Test();
//        try {
//            String y = test.post(url, jsonObject);
//            System.out.println("关屏================" + y);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void get() {
//		TaskToSetBrightness taskToSetBrightness = new TaskToSetBrightness();
        Gson gson = new Gson();
        RequestData data = new RequestData();
        String jsonData = gson.toJson(data);
        Test test = new Test();
        String url = "http://101.132.245.105:9000/command/l20-721-02111"; //check this
        String result;
        try {
            result = test.post(url, jsonData);
            System.out.print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
