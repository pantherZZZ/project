package com.th.system.utils;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class EncapsulationUtil{
	
	public JSONObject json(JSONArray json) {
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		json2.put("data",json);
		json2.put("code",200);
		json1.put("data",json2);
		json1.put("status", 200);
		json1.put("message", "成功");
		return json1;
	}
	
	/**
	 * list
	 * @param list
	 * @return
	 */
	public JSONObject encapsulationListJson(List list) {
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		json2.put("message", "成功");
		json2.put("list",list);
		json2.put("code",200);
		json1.put("data",json2);
		json1.put("status", 200);
		json1.put("message", "成功");
		return json1;
	}
	/**
	 * Object
	 * @param obj
	 * @return
	 */
	public JSONObject encapsulationObjJson(Object obj) {
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		json2.put("message", "成功");
		json2.put("list",obj);
		json2.put("code",200);
		json1.put("data",json2);
		json1.put("status", 200);
		json1.put("message", "成功");
		return json1;
	}
	/**
	 * result
	 * @param result
	 * @param num
	 * @return
	 */
	public JSONObject encapsulationStrJson(Object result,int num) {
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		if(num != 0) {
			json2.put("code",200);
		}else {
			json2.put("code",201);
		}
		json2.put("result",result);
		json1.put("data",json2);
		json1.put("status", 200);
		json1.put("message", "成功");
		return json1;
	}

	public JSONObject encapsulationStrJsonTwo(Object result,int num) {
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		if(num != 0) {
			json2.put("code",200);
		}else {
			json2.put("code",201);
		}
		json2.put("result",result);
		json1.put("data",json2);
		json1.put("status", 201);
		json1.put("message", "岩体项目只保留一个");
		return json1;
	}
	public JSONObject encapsulationStrJsonThree(Object result,int num) {
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		if(num != 0) {
			json2.put("code",200);
		}else {
			json2.put("code",201);
		}
		json2.put("result",result);
		json1.put("data",json2);
		json1.put("status", 201);
		json1.put("message", "岩体项目不允许删除");
		return json1;
	}
	/**
	 * limit
	 * @param list
	 * @param page
	 * @param limit
	 * @param total
	 * @return
	 */
	public JSONObject encapsulationlimitJson(List list,String page,String limit,int total) {
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		json2.put("code",200);
		json2.put("page",page);
		json2.put("limit",limit);
		json2.put("total",total);
		json2.put("list",list);
		json1.put("data",json2);
		json1.put("status", 200);
		json1.put("message", "成功");
		return json1;
	}
	/**
	 * count
	 * @param count
	 * @return
	 */
	public JSONObject encapsulationCountJson(Object count) {
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		json2.put("code",200);
		json2.put("count",count);
		json1.put("data",json2);
		json1.put("status", 200);
		json1.put("message", "成功");
		return json1;
	}
	
	/**
	 * count
	 * @param
	 * @return
	 */
	public JSONObject encapsulationJson(List arr) {
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		json2.put("code",200);
		json2.put("series",arr);
		json1.put("data",json2);
		json1.put("status", 200);
		json1.put("message", "成功");
		return json1;
	}
	
}
