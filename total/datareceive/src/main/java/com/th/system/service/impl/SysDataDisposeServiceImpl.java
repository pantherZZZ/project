package com.th.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.th.system.dao.AlarmInsertionMapper;
import com.th.system.po.SysAlarmInsertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.th.system.dao.SysDataMapper;
import com.th.system.po.SysCaution;
import com.th.system.po.SysData;
import com.th.system.po.SysMeasure;
import com.th.system.service.SysCautionService;
import com.th.system.service.SysDataDisposeService;
import com.th.system.service.SysDtuSetvice;
import com.th.system.service.SysEquipmentService;
import com.th.system.service.SysFactorService;
import com.th.system.service.SysMeasureService;
import com.th.system.service.SysModelService;
import com.th.system.service.SysProjectService;
import com.th.system.utils.AnalysisUtil;
import com.th.system.vo.DistributionVo;
import com.th.system.vo.StatementVo;
import com.th.system.vo.SysCantionVo;
import com.th.system.vo.SysDataProVo;
import com.th.system.vo.SysDataVo;
import org.springframework.transaction.annotation.Transactional;

@Service("SysDataDisposeServiceImpl")
@Transactional
public class SysDataDisposeServiceImpl implements SysDataDisposeService{

	@Autowired
	private SysDataMapper sysDataMapper;
	
	@Autowired
	private SysModelService sysModelService;
	
	@Autowired
	private SysMeasureService sysMeasureService;
	
	@Autowired
	private  SysEquipmentService sysEquipmentService;
	
	@Autowired
	private SysFactorService sysFactorService;
	
	@Autowired
	private SysDtuSetvice sysDtuSetvice;
	
	@Autowired
	private SysCautionService cautionService;
	
	@Autowired
	private SysProjectService sysProjectService;

	@Autowired
	private AlarmInsertionMapper alarmInsertionMapper;

	AnalysisUtil analysisUtil = new AnalysisUtil();
	
	@Override
	public JSONArray realTimeSupervise(Integer equipmentId, String time1, String time2) {
		JSONArray arr1 = new JSONArray();
		for (int i = 1; i < 3; i++) {
			JSONObject json3 = new JSONObject();
			JSONArray arr2 = new JSONArray();
			for (int j = 0; j < 24; j++) {
				SysData data = sysDataMapper.findHourData(equipmentId, time1, j+1+"");
				if(data != null) {
					arr2.add(data.getData());
				}else {
					arr2.add("0");
				}
			}
			if(i == 1) {
				json3.put("name", time1);
			}else {
				json3.put("name", time2);
			}
			json3.put("type","line");
			json3.put("stack", i+"");
			json3.put("data", arr2);
			arr1.add(json3);
		}
		return arr1;
	}

	@Override
	public JSONArray measureData(String[] measureIdArr, String time) {
		JSONArray arr1 = new JSONArray();
		for (int i = 1; i < 3; i++) {
			JSONObject json3 = new JSONObject();
			JSONArray arr2 = new JSONArray();
			for (int j = 0; j < 24; j++) {
				String data = sysMeasureService.findByMeasurePro(Integer.parseInt(measureIdArr[i-1]), time, j+1+"");
				if(data != null) {
					arr2.add(data);
				}else {
					arr2.add("0");
				}
			}
			SysMeasure measure = sysMeasureService.findByMeasure(Integer.parseInt(measureIdArr[i-1]));
			json3.put("name", measure.getMeasureName());//传测点名称
			json3.put("type","line");
			json3.put("stack", i+"");
			json3.put("data", arr2);
			arr1.add(json3);
		}
		return arr1;
	}

	@Override
	public JSONArray projectStatistics() {
		JSONArray arr = new JSONArray();
		int[] num1 = {27,1532};
		int[] num2 = {45,1594};
		Map<String, String> timeInterval = analysisUtil.getTimeInterval();
		Map<String, String> weekDate = analysisUtil.getWeekDate();
		String time1 = analysisUtil.getLastYear();
		String time2 = analysisUtil.getYear();
		Integer year1 = sysProjectService.findYearTime(time2);//今年数量
		Integer year2 = sysProjectService.findYearTime(time1);//去年数量
		Integer weekTime1 = sysProjectService.findWeekTime(timeInterval.get("lastBeginDate"), timeInterval.get("lastEndDate"));//上一周数量
		Integer weekTime2 = sysProjectService.findWeekTime(weekDate.get("mondayDate"), weekDate.get("sundayDate"));//下一周数量
		num1[1] = year1;
		num1[0] = weekTime1;
		num2[1] = year2;
		num2[0] = weekTime2;
		arr.add(num1);
		arr.add(num2);
		return arr;
	}

	@Override
	public List<DistributionVo> modelDistribution() {
		List<DistributionVo> list = new ArrayList<>();
		String[] province = {"北京","天津","上海","重庆","河北","山西","辽宁","吉林","黑龙江","江苏","浙江","安徽","福建","江西"
				,"山东","河南","湖北","湖南","广东","海南","四川","贵州","云南","陕西","甘肃","青海","广西","西藏",
				"宁夏","新疆","内蒙古","香港","澳门","台湾","南海诸岛"};
		String[] num = {"25","36","52","48","87","66","41","2","32","67","86","2","1","32"
				,"1254","65","12","45","123","90","21","34","56","76","54","23","43","23",
				"157","321","54","45","41","87","0"};
		for (int i = 0; i < province.length; i++) {
			Integer count = sysModelService.findAddress(province[i]);
			if (i==9){
				count++;
			}
			num[i] = count+"";
		}
		for (int i = 0; i < num.length; i++) {
			DistributionVo distribution = new DistributionVo();
			distribution.setName(province[i]);
			distribution.setValue(Integer.parseInt(num[i]));
			list.add(distribution);
		}
		return list;
	}

	@Override
	public JSONArray facilityStatistics() {
		JSONArray arr = new JSONArray();
		JSONArray num1 = new JSONArray();
		JSONArray num2 = new JSONArray();
		String year = analysisUtil.getYear();
		for (int i = 1; i < 13; i++) {
			String date = "";
			if(i >= 10) {
				date = year+"-"+i;
			}else {
				date = year+"-0"+i;
			}	
			Integer count1 = sysEquipmentService.findTimeCount(date);
			Integer count2 = sysDtuSetvice.findTimeCount(date);
			num1.add(count1);
			num2.add(count2);
		}
		arr.add(num1);
		arr.add(num2);
		return arr;
	}

	@Override
	public JSONArray facilityStatisticsPro() {
		JSONArray arr = new JSONArray();
		JSONArray num1 = new JSONArray();
		JSONArray num2 = new JSONArray();
		String year = analysisUtil.getYear();
		double val1 = 0;
		double val2 = 0;
		for (int i = 1; i < 13; i++) {
			String date = "";
			if(i != 1) {
				if(i >= 10) {
					date = year+"-"+i;
				}else {
					date = year+"-0"+i;
				}
				Integer count1 = sysEquipmentService.findTimeCount(date);
				Integer count2 = sysDtuSetvice.findTimeCount(date);
				double data1 = 0;
				double data2 = 0;
				if(val1 != 0 && count1 != 0) {	
					data1 = (count1-val1)/val1;
				}else {
					data1 = count1;
				}
				if(val2 != 0 && count2 != 0) {
					data2 = (count2-val2)/val2;
				}else {
					data2 = count2;	
				}	
				num1.add(data1);
				num2.add(data2);
				val1 = count1;
				val2 = count2;
			}else {
				num1.add(val1);
				num2.add(val2);
			}
		}
		arr.add(num1);
		arr.add(num2);
		return arr;
	}

	@Override
	public List<StatementVo> findStatement() {
		List<SysCantionVo> cantionList = cautionService.findCautionDate();
		List<StatementVo> list = new ArrayList<StatementVo>();
		for (int i = 0; i < cantionList.size() ; i++) {
			SysCantionVo cantion = cantionList.get(i);
			StatementVo statement = new StatementVo();
			statement.setDistrict(cantion.getMeasureName());
			statement.setSpecification(cantion.getNumber());
			statement.setStatementMessage(cantion.getCautionMessage());
			statement.setStatementTime(cantion.getProduceTime());
			list.add(statement);
		}
		return list;
	}

	@Override
	public JSONObject findEquipmentState() {
		Integer equipmentCount = sysEquipmentService.findCount();
		int i = equipmentCount + 3;

		//报警数量
		int alertorCount = 0;
		try {
			List<SysAlarmInsertion> list = alarmInsertionMapper.selectList(null);
			List<Integer> typeList = CollUtil.getFieldValues(list, "type", Integer.class);
			List<Integer> collect = typeList.stream().distinct().collect(Collectors.toList());
			alertorCount = collect.size();
		} catch (Exception e) {
			e.printStackTrace();
		}


		JSONObject message =new JSONObject();
		JSONObject data =new JSONObject();
		JSONObject json =new JSONObject();
		json.put("equipmentCount", i);//设备数量
		json.put("normalEquipment", equipmentCount);//正常设备
		json.put("alertorCount", alertorCount);//报警数量
		json.put("offlineEquipment", 0);//未联网
		data.put("code",200);
		data.put("data",json);
		message.put("list", data);
		message.put("status", 200);
		message.put("message", "成功");
		return message;
	}

	@Override
	public JSONObject findStatementCount() {
		JSONObject message =new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject data =new JSONObject();
		JSONArray num1 = new JSONArray();
		JSONArray num2 = new JSONArray();
		String year = analysisUtil.getYear();
		for (int i = 1; i < 13; i++) {
			String date = "";
			if(i >= 10) {
				date = year+"-"+i;
			}else {
				date = year+"-0"+i;
			}
			List<SysCaution> findCautionTime = cautionService.findCautionTime(date);
			num1.add(findCautionTime.size());
			num2.add(findCautionTime.size()+1);
		}
		data.put("code",200);
		arr.add(num1);
		arr.add(num2);
		data.put("data",arr);
		message.put("list", data);
		message.put("status", 200);
		message.put("message", "成功");
		return message;
	}

	@Override
	public JSONObject findModelData(Integer modelId) {
		Integer count1 = sysDtuSetvice.findDTUModleCount(modelId);
		Integer count2 = sysEquipmentService.findModelCount(modelId);
		SysDataProVo dataVo = new SysDataProVo();
		dataVo.setDTUCount(count1+"");
		dataVo.setFullRate("1");
		dataVo.setSensorCount(count2+"");
		dataVo.setValidRate("1");
		JSONObject message =new JSONObject();
		JSONObject data =new JSONObject();
		data.put("list",dataVo);
		data.put("code",200);
		message.put("data", data);
		message.put("status", 200);
		message.put("message", "成功");
		return message;
	}

	@Override
	public JSONObject findFullData(Integer modelId) {
		Integer count = cautionService.findModelCount(modelId);
		List<SysDataVo> dataVo = new ArrayList<SysDataVo>();
		SysDataVo date1 = new SysDataVo();
		date1.setValue(count);
		date1.setName("完整");
		dataVo.add(date1);
		SysDataVo date2 = new SysDataVo();
		date2.setValue(0);
		date2.setName("缺失");
		dataVo.add(date2);
		JSONObject message =new JSONObject();
		JSONObject data =new JSONObject();
		data.put("data",dataVo);
		data.put("code",200);
		message.put("data", data);
		message.put("status", 200);
		message.put("message", "成功");
		return message;
	}

	@Override
	public JSONObject findValidData(Integer modelId) {
		Integer count = sysEquipmentService.findModelCount(modelId);
		List<SysDataVo> dataVo = new ArrayList<SysDataVo>();
		SysDataVo date1 = new SysDataVo();
		date1.setValue(count);
		date1.setName("在线");
		dataVo.add(date1);
		SysDataVo date2 = new SysDataVo();
		date2.setValue(0);
		date2.setName("离线");
		dataVo.add(date2);
		JSONObject message =new JSONObject();
		JSONObject data =new JSONObject();
		data.put("data",dataVo);
		data.put("code",200);
		message.put("data", data);
		message.put("status", 200);
		message.put("message", "成功");
		return message;
	}
	
}
