package com.th.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.th.system.po.SysDtuEquipment;
import com.th.system.service.SysDtuEquipmentService;
import com.th.system.service.SysDtuSetvice;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.HttpClient;
import com.th.system.utils.JsonUtil;
import com.th.system.vo.SysDtuVo;

@CrossOrigin
@RestController
@RequestMapping("/dtu")
public class SysDtuController extends HttpClient {

	@Autowired
	private SysDtuSetvice sysDtuSetvice;
	
	@Autowired
	private SysDtuEquipmentService sysDtuEquipmentService;
	
	EncapsulationUtil en = new EncapsulationUtil();
	
	@PostMapping(value = "/findByPlantId", produces="application/json;charset=UTF-8")
	public String findByPlantId(HttpServletRequest request,Integer plantId) {
		List<SysDtuEquipment> list = sysDtuEquipmentService.findByPlantId(plantId);
		JSONObject json = en.encapsulationListJson(list);
		return JsonUtil.writeAsString(json);
	}
	
	//新增dtu
	@PostMapping(value = "/insertDTU", produces="application/json;charset=UTF-8")
	public String insertDTU(Integer plantId,Integer modelId,String postId,String dtuName,Integer typeId,Integer modelNumberId) {
		Integer number = sysDtuSetvice.insertDTU(plantId, modelId,postId,dtuName,typeId,modelNumberId);
		JSONObject json = en.encapsulationStrJson(number, number);
		return JsonUtil.writeAsString(json);
	}
	
//	//修改dtu
//	@PostMapping(value = "/updateDTU", produces="application/json;charset=UTF-8")
//	public String updateDTU(HttpServletRequest request,String dtuId,String plantId,String modelId,String modelNumberId,String equipmentId) {
//		Integer number = sysDtuSetvice.updateDTU(dtuId, plantId, modelId, modelNumberId, equipmentId);
//		JSONObject json = en.encapsulationStrJson(number, number);
//		return JsonUtil.writeAsString(json);
//	}
//	
	//删除dtu
	@PostMapping(value = "/delDTU", produces="application/json;charset=UTF-8")
	public String delDTU(HttpServletRequest request,Integer dtuId) {
		Integer number = sysDtuSetvice.delDTU(dtuId);
		JSONObject json = en.encapsulationStrJson(number, number);
		return JsonUtil.writeAsString(json);
	}
	
	//dtu 根据modelId查询
	@PostMapping(value = "/findByDtuModelId", produces="application/json;charset=UTF-8")
	public String findByDtuModelId(HttpServletRequest request,Integer modelId,String page,String limit) {
		int pages = Integer.parseInt(page)-1;
		List<SysDtuVo> list = sysDtuSetvice.findByModelId(modelId,pages,Integer.parseInt(limit));
		List<SysDtuVo> list2 = sysDtuSetvice.findByModelId(modelId,0,1000000);
		JSONObject json = en.encapsulationlimitJson(list, page, limit, list2.size());
		return JsonUtil.writeAsString(json);
	}
		
	//dtu 根据id查询
	@PostMapping(value = "/findByDtuId", produces="application/json;charset=UTF-8")
	public String findByDtuId(HttpServletRequest request,Integer dtuId) {
		SysDtuVo dtu = sysDtuSetvice.findById(dtuId);
		int code = httpClientPost(request);
		JSONObject message =new JSONObject();
		JSONObject data =new JSONObject();
		data.put("result",dtu);
		data.put("code",code);
		message.put("data", data);
		message.put("status", 200);
		message.put("message", "成功");
		return JsonUtil.writeAsString(message);
	}
}
