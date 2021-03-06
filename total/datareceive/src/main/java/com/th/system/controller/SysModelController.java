package com.th.system.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.th.system.service.*;
import com.th.system.vo.SysEquipmentNameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.th.system.po.SysDetectionType;
import com.th.system.po.SysFactor;
import com.th.system.po.SysMeasure;
import com.th.system.po.SysModel;
import com.th.system.po.SysModelAndType;
import com.th.system.po.SysPathlist;
import com.th.system.po.SysProjecAndModel;
import com.th.system.po.SysProject;
import com.th.system.po.SysStrategy;
import com.th.system.po.SysType;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.HttpClient;
import com.th.system.utils.JsonUtil;
import com.th.system.vo.SysModelVo;

@CrossOrigin
@RestController
@RequestMapping("/model")
public class SysModelController extends HttpClient {

    @Autowired
    private SysModelService sysModelService;

    @Autowired
    private SysProjecAndModelService sysProjecAndModelService;

    @Autowired
    private SysPathlistService sysPathlistService;

    @Autowired
    private SysModelAndTypeService sysModelAndTypeService;

    @Autowired
    private SysFactorService sysFactorService;

    @Autowired
    private SysDetectionTypeService sysDetectionTypeService;

    @Autowired
    private SysStrategyService sysStrategyService;

    @Autowired
    private SysMeasureService sysMeasureService;

    @Autowired
    private SysModelTypeService sysModelTypeService;

    @Autowired
    private SysProjectService sysProjectService;

    @Autowired
    private SysDtuOneService sysDtuOneService;

    @Autowired
    private SysMeasuringPointService sysMeasuringPointService;

    EncapsulationUtil en = new EncapsulationUtil();

    //????????????
    @PostMapping(value = "/dalMeasure", produces = "application/json;charset=UTF-8")
    public String dalMeasure(HttpServletRequest request, Integer measureId) {
        Integer result = sysMeasureService.dalMeasure(measureId);
        JSONObject json = en.encapsulationStrJson(result, result);
        return JsonUtil.writeAsString(json);
    }

    //????????????
    @PostMapping(value = "/updateMeasure", produces = "application/json;charset=UTF-8")
    public String updateMeasure(HttpServletRequest request, String measureName, Integer factorId, String dataSource, String label
            , Integer equipmentId, Integer modelId, Integer measureId, String pictureSite) {
        Integer result = sysMeasureService.updateMeasure(measureId, measureName, factorId, dataSource, equipmentId, pictureSite, modelId, label);
        JSONObject json = en.encapsulationStrJson(result, result);
        return JsonUtil.writeAsString(json);
    }

    //????????????id??????
    @PostMapping(value = "/findByMeasureId", produces = "application/json;charset=UTF-8")
    public String findByMeasureId(HttpServletRequest request, Integer measureId) {
        SysMeasure measure = sysMeasureService.findByMeasure(measureId);
        int code = httpClientPost(request);
        JSONObject message = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("code", code);
        data.put("list", measure);
        message.put("data", data);
        message.put("status", 200);
        message.put("message", "??????");
        return JsonUtil.writeAsString(message);
    }

    //???????????????id??????
    @PostMapping(value = "/findByMeasureModelId", produces = "application/json;charset=UTF-8")
    public String findByMeasureModelId(HttpServletRequest request, Integer modelId, String measureName, String page, String limit) {
        int pages = Integer.parseInt(page) - 1;
        List<SysMeasure> list = sysMeasureService.findByModelId(modelId, measureName, pages, Integer.parseInt(limit));
        List<SysMeasure> list2 = sysMeasureService.findByModelId(modelId, measureName, 0, 1000000);
        JSONObject json = en.encapsulationlimitJson(list, page, limit, list2.size());
        return JsonUtil.writeAsString(json);
    }

    //??????????????????
    @PostMapping(value = "/insertMeasurePicture", produces = "application/json;charset=UTF-8")
    public String insertMeasurePicture(HttpServletRequest request, @RequestParam(value = "file") MultipartFile file) {
        String size = "????????????";
        if (file != null) {
            // ???????????????
            String fileName = file.getOriginalFilename();
            // ????????????????????????
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-", "").substring(6) + suffixName;
            // ????????????????????????
            String filePath = "/www/wwwroot/local/xm/upload/";
            size = "http://119.3.156.168:8083/" + newFileName;
            File dest = new File(filePath + newFileName);
            // ????????????????????????
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        int code = httpClientPost(request);
        JSONObject message = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("code", code);
        data.put("pictureSite", size);
        message.put("data", data);
        message.put("status", 200);
        message.put("message", "??????");
        return JsonUtil.writeAsString(message);
    }

    /**
     * ????????????????????????????????????????????????
     *
     * @param modelId
     * @return
     */
    @GetMapping(value = "/findAllEquipment", produces = "application/json;charset=UTF-8")
    public String findAllEquipment(Integer modelId) {
        List<SysEquipmentNameVo> equipmentNameList = sysDtuOneService.findEquipmentName(modelId);
        JSONObject json = en.encapsulationListJson(equipmentNameList);
        return JsonUtil.writeAsString(json);
    }

    //????????????
    @PostMapping(value = "/insertMeasure", produces = "application/json;charset=UTF-8")
    public String insertMeasure(String measureName, Integer factorId, String dataSource, String pictureSite
            , Integer equipmentId, Integer modelId) {
        Integer integer = sysMeasuringPointService.insertMeasure(measureName, factorId, dataSource, pictureSite, equipmentId, modelId);
        JSONObject json = en.encapsulationStrJson(integer, integer);
        return JsonUtil.writeAsString(json);
    }

    //????????????id??????
    @PostMapping(value = "/findByStrategyId", produces = "application/json;charset=UTF-8")
    public String findByStrategyId(HttpServletRequest request, Integer strategyId) {
        SysStrategy strategy = sysStrategyService.findByStrategyId(strategyId);
        int code = httpClientPost(request);
        JSONObject message = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("code", code);
        data.put("result", strategy);
        message.put("data", data);
        message.put("status", 200);
        message.put("message", "??????");
        return JsonUtil.writeAsString(message);
    }

    //????????????
    @PostMapping(value = "/delStrategy", produces = "application/json;charset=UTF-8")
    public String delStrategy(String strategyId) {
        Integer count = sysStrategyService.delStrategy(Integer.parseInt(strategyId));
        JSONObject json = en.encapsulationStrJson(count, count);
        return JsonUtil.writeAsString(json);
    }

    //????????????
    @PostMapping(value = "/updateStrategy", produces = "application/json;charset=UTF-8")
    public String updateStrategy( Integer strategyId, String strategyName, String way, String cycle, String startTime, String endTime, String describe, String modelId) {
        Integer count = sysStrategyService.updateStrategy(strategyId, strategyName, way, cycle, startTime, endTime, describe);
        JSONObject json = en.encapsulationStrJson(count, count);
        return JsonUtil.writeAsString(json);
    }

    //???????????? ?????????
    @PostMapping(value = "/findByStrategyModelId", produces = "application/json;charset=UTF-8")
    public String findByStrategyModelId(Integer modelId, String page, String limit) {
        int pages = Integer.parseInt(page) - 1;
        List<SysStrategy> list = sysStrategyService.findByModelId(modelId, pages, Integer.parseInt(limit), "1");
        List<SysStrategy> list2 = sysStrategyService.findByModelId(modelId, pages, Integer.parseInt(limit), null);
        JSONObject json = en.encapsulationlimitJson(list, page, limit, list2.size());
        return JsonUtil.writeAsString(json);
    }

    //???????????? ?????????
    @PostMapping(value = "/insertStrategy", produces = "application/json;charset=UTF-8")
    public String insertStrategy(String strategyName, String way, String cycle, String startTime, String endTime, String describe, Integer modelId) {
        Integer count = sysStrategyService.insertStrategy(strategyName, modelId, way, cycle, startTime, endTime, describe);
        JSONObject json = en.encapsulationStrJson(count, count);
        return JsonUtil.writeAsString(json);
    }

    //??????????????????
    @PostMapping(value = "/insertFactor", produces = "application/json;charset=UTF-8")
    public String insertFactor(String factorName, String isPrivate) {
//		String factorId = UUID.randomUUID().toString().replace("-", "");
        Integer count = sysFactorService.insertFactor(null, factorName, isPrivate);
        JSONObject json = en.encapsulationStrJson(count, count);
        return JsonUtil.writeAsString(json);
    }

    //????????????????????????
    @GetMapping(value = "/findFctorAll", produces = "application/json;charset=UTF-8")
    public String findFctorAll(HttpServletRequest request) {
        List<SysFactor> list = sysFactorService.findAll();
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

    //??????????????????
    @PostMapping(value = "/updateMoele", produces = "application/json;charset=UTF-8")
    public String updateMoele(HttpServletRequest request, String factorName, String isPrivate) {
//		String factorId = UUID.randomUUID().toString().replace("-", "");
        Integer count = sysFactorService.insertFactor(null, factorName, isPrivate);
        JSONObject json = en.encapsulationStrJson(count, count);
        return JsonUtil.writeAsString(json);
    }

    //??????????????????
    @PostMapping(value = "/insertDetection", produces = "application/json;charset=UTF-8")
    public String insertDetection(HttpServletRequest request, String detectionTypeName, String isUsable) {
//		String detectionTypeId = UUID.randomUUID().toString().replace("-", "");
        Integer count = sysDetectionTypeService.insertDetection(null, detectionTypeName, isUsable);
        JSONObject json = en.encapsulationStrJson(count, count);
        return JsonUtil.writeAsString(json);
    }

    //????????????????????????
    @GetMapping(value = "/findDetectionTypeAll", produces = "application/json;charset=UTF-8")
    public String findDetectionTypeAll(HttpServletRequest request) {
        List<SysDetectionType> list = sysDetectionTypeService.findAll();
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

    //????????????????????????
    @GetMapping(value = "/findModelCount", produces = "application/json;charset=UTF-8")
    public String findModelCount(HttpServletRequest request) {
        Integer count = sysModelService.findCount();
        int code = httpClientPost(request);
        JSONObject message = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("count", count);
        data.put("code", code);
        message.put("data", data);
        message.put("status", 200);
        message.put("message", "??????");
        return JsonUtil.writeAsString(message);
    }

    //?????????????????????????????????
    @PostMapping(value = "/inserModelAndTypet", produces = "application/json;charset=UTF-8")
    public String inserModelAndTypet(HttpServletRequest request, Integer typeId, Integer modelId) {
        SysModelAndType sysModelAndType = new SysModelAndType();
        sysModelAndType.setModelId(modelId);
        sysModelAndType.setTypeId(typeId);
        Integer count = sysModelAndTypeService.insert(sysModelAndType);
        JSONObject json = en.encapsulationStrJson(count, count);
        return JsonUtil.writeAsString(json);
    }

    //??????modelid ??????model?????? ?????????????????????
    @PostMapping(value = "/findByTypeName", produces = "application/json;charset=UTF-8")
    public String findByTypeName(HttpServletRequest request, Integer modelId) {
        List<SysType> list = sysModelAndTypeService.fineByModleId(modelId);
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

    //??????modelId????????????
    @PostMapping(value = "/findByModelId", produces = "application/json;charset=UTF-8")
    public String findByModelId(HttpServletRequest request, Integer modelId) {
        SysModel model = sysModelService.findByModelIdPro(modelId);
        int code = httpClientPost(request);
        JSONObject message = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("result", model);
        data.put("code", code);
        message.put("data", data);
        message.put("status", 200);
        message.put("message", "??????");
        return JsonUtil.writeAsString(message);
    }

    //??????modelId????????????
    @PostMapping(value = "/findByFactorModelId", produces = "application/json;charset=UTF-8")
    public String findByFactorModelId(HttpServletRequest request, Integer modelId) {
        List<SysFactor> list = sysFactorService.findByModelId(modelId);
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

    //??????????????????????????????????????????????????????
    @PostMapping(value = "/findByBindingIsNot", produces = "application/json;charset=UTF-8")
    public String findByBindingIsNot(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userid = (Integer) session.getAttribute("userid");
        List<SysModel> list = sysModelService.findByBindingIsNot(userid);
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

    //?????????????????????????????????
    @PostMapping(value = "/findByBindingIsNotPro", produces = "application/json;charset=UTF-8")
    public String findByBindingIsNotPro(HttpServletRequest request) {
        List<SysModel> list = sysModelService.findByBindingIsNot(null);
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }


    @PostMapping(value = "/updateModel", produces = "application/json;charset=UTF-8")
    public String updateModel(HttpServletRequest request, String modelName, String coordinateX, String coordinateY,
                              String purpose, String gist, String describe, Integer modelId, String address, Integer pictureId
                                ,String sketchMap,String longitude,String latitude) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userid");
        SysModel model = new SysModel();
        model.setCoordinateX(coordinateX);
        model.setCoordinateY(coordinateY);
        model.setDescribe(describe);
        model.setGist(gist);
        model.setModelId(modelId);
        model.setModelName(modelName);
        model.setPurpose(purpose);
        model.setSketchMap(pictureId + "");
        model.setAddress(address);
        model.setSketchMap(sketchMap);
        model.setUserId(userId);
        model.setLongitude(longitude);
        model.setLatitude(latitude);
        Integer count = sysModelService.updateMoele(model);
        JSONObject json = en.encapsulationStrJson(count, count);
        return JsonUtil.writeAsString(json);
    }

    //?????????????????????
    @GetMapping(value = "/findAll", produces = "application/json;charset=UTF-8")
    public String findAll(HttpServletRequest request) {
        List<SysModel> list = sysModelService.findAll();
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

    //??????modelId??????????????????
    @PostMapping(value = "/delModel", produces = "application/json;charset=UTF-8")
    public String delModel(HttpServletRequest request, Integer modelId) {
        Integer count = sysModelService.delModel(modelId);
        if (count != 0) {
            sysProjecAndModelService.delect(null, modelId);
        }
        JSONObject json = en.encapsulationStrJson(count, count);
        return JsonUtil.writeAsString(json);
    }

    //??????userid??????
    @PostMapping(value = "/findByUserModel", produces = "application/json;charset=UTF-8")
    public String findByUserModel(HttpServletRequest request, String modelName, String page, String limit) {
        HttpSession session = request.getSession();
        Integer userid = (Integer) session.getAttribute("userid");
        String role = "1";//(String) session.getAttribute("role");
        Integer user = null;
        if (!role.equals("1")) {
            user = userid;
        }
        int pages = Integer.parseInt(page) - 1;
        List<SysModelVo> list = sysModelService.findByProjectIdAir(user, pages, Integer.parseInt(limit), modelName, "9");
        List<SysModelVo> list2 = sysModelService.findByProjectIdAir(user, pages, Integer.parseInt(limit), modelName, null);
        JSONObject json = en.encapsulationlimitJson(list, page, limit, list2.size());
        return JsonUtil.writeAsString(json);
    }

    //??????userid??????
    @PostMapping(value = "/findByUserModelPro", produces = "application/json;charset=UTF-8")
    public String findByUserModelPro(HttpServletRequest request, String modelName) {
        HttpSession session = request.getSession();
        Integer userid = (Integer) session.getAttribute("userid");
        String role = "1";//(String) session.getAttribute("role");
        Integer user = null;
        if (!role.equals("1")) {
            user = userid;
        }
        List<SysModelVo> list = sysModelService.findByProjectIdAir(user, 0, 0, modelName, null);
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

    //??????userid??????
    @PostMapping(value = "/findByUserModelPRO", produces = "application/json;charset=UTF-8")
    public String findByUserModelPRO(HttpServletRequest request) {
        List<SysModelVo> list = sysModelService.findByProjectIdAir(null, 0, 0, null, null);
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

    //?????????????????????????????????
    @PostMapping(value = "/insertModel", produces = "application/json;charset=UTF-8")
    public String insertModel(HttpServletRequest request, String modelName, String coordinateX, String coordinateY,
                              String purpose, String gist, String describe) {
        SysModel model = new SysModel();
        HttpSession session = request.getSession();
        Integer userid = (Integer) session.getAttribute("userid");
        model.setCoordinateX(coordinateX);
        model.setCoordinateY(coordinateY);
        model.setDescribe(describe);
        model.setGist(gist);
        model.setModelName(modelName);
        model.setPurpose(purpose);
        model.setIsBinding("1");
        model.setUserId(userid);

        Integer count = sysModelService.insertModel(model);
        JSONObject json = en.encapsulationStrJson(count, count);
        return JsonUtil.writeAsString(json);
    }

    public void show(Integer projectId, Integer modelId) {
        SysProjecAndModel sysProjecAndModel = new SysProjecAndModel();
        sysProjecAndModel.setModelId(modelId);
        sysProjecAndModel.setProjectId(projectId);
        sysProjecAndModelService.insert(sysProjecAndModel);
    }

    @PostMapping(value = "/gopicture", produces = "application/json;charset=UTF-8")
    public String gopicture(HttpServletRequest request, @RequestParam(value = "file") MultipartFile file) throws IllegalStateException, IOException {
        Integer count = 0;
        String pictureId = "";
        if (file != null) {
            // ???????????????
            String fileName = file.getOriginalFilename();
            // ????????????????????????
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            if (!suffixName.equals(".jpg") || !suffixName.equals(".png")) {
                String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-", "").substring(6) + suffixName;
                // ????????????????????????
                String filePath = "/www/wwwroot/jgw.taiott.com/dist/static/jgwImg/";
                //		    String filePath = "D:/path/";
                String size = "http://jgw.taiott.com/static/jgwImg/" + newFileName;
                File dest = new File(filePath + newFileName);
                // ????????????????????????
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                file.transferTo(dest);
                SysPathlist pathlist = new SysPathlist();
                pathlist.setPictureName(fileName);
                pathlist.setPicturePath(size);
                count = sysPathlistService.insertPath(pathlist);
                pictureId = sysPathlistService.showPictureId(size);
            }
        }
        JSONObject message = new JSONObject();
        JSONObject data = new JSONObject();
        if (count != 0) {
            data.put("code", 200);
        } else {
            data.put("code", 201);
        }
        data.put("result", count);
        data.put("pictureId", pictureId);
        message.put("data", data);
        message.put("status", 200);
        message.put("message", "??????");
        return JsonUtil.writeAsString(message);
    }

    //????????????
    @PostMapping(value = "/insertPicture", produces = "application/json;charset=UTF-8")
    public String insertPicture(String modelName, String coordinateX, String coordinateY,
                                String purpose, String gist, String describe, String factorId, String address,
                                String detectionTypeId, String projectId, String pictureId, String userId,
                                String longitude,String latitude,String lnglat) {
        boolean bool = factorId.contains("@");
//		HttpSession session = request.getSession();
//		int userid = (int)session.getAttribute("userid");
        Integer count = 0;
        SysModel model = new SysModel();
        model.setCoordinateX(coordinateX);
        model.setCoordinateY(coordinateY);
        model.setDescribe(describe);
        model.setGist(gist);
        model.setModelName(modelName);
        model.setPurpose(purpose);
        model.setSketchMap(pictureId);
        model.setIsBinding("1");
        model.setUserId(Integer.valueOf(userId));
        model.setAddress(address);
        model.setLongitude(longitude);
        model.setLatitude(latitude);
        model.setDetectionTypeId(Integer.valueOf(detectionTypeId));
        count = sysModelService.insertModel(model);
        String modelId = sysModelService.showModelId(modelName, Integer.valueOf(userId), address, Integer.valueOf(detectionTypeId), coordinateX, coordinateY);
        if (bool) {
            String[] split = factorId.split("@");
            for (int i = 0; i < split.length; i++) {
                if (split[i] != "" || !"".equals(split[i])) {
                    sysModelTypeService.insertModelType(Integer.parseInt(modelId), Integer.parseInt(split[i]));
                }
            }

        } else {
            sysModelTypeService.insertModelType(Integer.parseInt(modelId), Integer.parseInt(factorId));
        }
        this.show(Integer.parseInt(projectId), Integer.parseInt(modelId));
        JSONObject json = en.encapsulationStrJson(count, count);
        return JsonUtil.writeAsString(json);
    }

}
