package com.th.screen.until;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author zhang bao
 * @Date 2022/3/16 14:36
 * @Description
 * @Version 1.0
 */
public class ReturnUtil {

    public static JSONObject resultOk() {
        JSONObject jsonOk = new JSONObject();
        jsonOk.put("message", "投屏成功");
        jsonOk.put("code", 200);
        return jsonOk;
    }

    public static JSONObject closeOk() {
        JSONObject jsonOk = new JSONObject();
        jsonOk.put("message", "关屏成功");
        jsonOk.put("code", 200);
        return jsonOk;
    }

    public static JSONObject resultXXError() {
        JSONObject jsonError = new JSONObject();
        jsonError.put("message", "熙讯投屏失败");
        jsonError.put("code", 201);
        return jsonError;
    }

    public static JSONObject resultYBError() {
        JSONObject jsonError = new JSONObject();
        jsonError.put("message", "仰帮投屏失败");
        jsonError.put("code", 202);
        return jsonError;
    }

    public static JSONObject CloseYBError() {
        JSONObject jsonError = new JSONObject();
        jsonError.put("message", "仰帮关屏失败");
        jsonError.put("code", 206);
        return jsonError;
    }

    //仰帮内网登录失败
    public static JSONObject resultLoginError() {
        JSONObject jsonError = new JSONObject();
        jsonError.put("message", "屏幕登录失败");
        jsonError.put("code", 203);
        return jsonError;
    }

    //空指针
    public static JSONObject resultNullPointError() {
        JSONObject jsonError = new JSONObject();
        jsonError.put("message", "无屏幕数据");
        jsonError.put("code", 204);
        return jsonError;
    }

    //屏幕不在线
    public static JSONObject resultScreenOffLineError() {
        JSONObject jsonError = new JSONObject();
        jsonError.put("message", "屏幕不在线");
        jsonError.put("code", 205);
        return jsonError;
    }

}
