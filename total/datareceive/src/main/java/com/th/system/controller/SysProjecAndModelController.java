package com.th.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.th.system.po.SysProject;
import com.th.system.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.th.system.po.SysModel;
import com.th.system.po.SysProjecAndModel;
import com.th.system.service.SysModelService;
import com.th.system.service.SysProjecAndModelService;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.HttpClient;
import com.th.system.utils.JsonUtil;

@CrossOrigin
@RestController
@RequestMapping("/projecAndModel")
public class SysProjecAndModelController extends HttpClient {

    @Autowired
    private SysProjecAndModelService sysProjecAndModelService;

    @Autowired
    private SysModelService sysModelService;

    @Autowired
    private ProjectService projectService;

    EncapsulationUtil en = new EncapsulationUtil();

    //模型添加到项目中
    @PostMapping(value = "/insert", produces = "application/json;charset=UTF-8")
    public String insert(HttpServletRequest request, Integer modelId, Integer projectId) {
        SysProjecAndModel sysProjecAndModel = new SysProjecAndModel();
        sysProjecAndModel.setModelId(modelId);
        sysProjecAndModel.setProjectId(projectId);
        Integer result = sysProjecAndModelService.insert(sysProjecAndModel);
        if (result != 0) {
            SysModel model = new SysModel();
            model.setModelId(modelId);
            model.setIsBinding("2");
            sysModelService.updateMoele(model);
        }
        JSONObject json = en.encapsulationStrJson(result, result);
        return JsonUtil.writeAsString(json);
    }


    //模型添加到项目中
    @PostMapping(value = "/insertPro", produces = "application/json;charset=UTF-8")
    public String insertPro(String modelIdList, Integer projectId) {
        JSONObject json = sysProjecAndModelService.insertPro(modelIdList, projectId);
        return JsonUtil.writeAsString(json);
    }

    @PostMapping(value = "/updateProjec", produces = "application/json;charset=UTF-8")
    public String updateProjec(String modelIdList, String projectName
            , String userName, String password, String projectId, String userId) {
        JSONObject json = sysProjecAndModelService.updateProjec(modelIdList, projectName, userName, password, Integer.valueOf(projectId), Integer.valueOf(userId));
        return JsonUtil.writeAsString(json);
    }

    @PostMapping(value = "/updateProjecPro", produces = "application/json;charset=UTF-8")
    public String updateProjecPro(String projectName, String projectId, String userId, String type) {
        JSONObject json = sysProjecAndModelService.updateProjecPro(projectName, Integer.valueOf(projectId), userId, Integer.valueOf(type));
        return JsonUtil.writeAsString(json);
    }

    @PostMapping(value = "/insertProjec", produces = "application/json;charset=UTF-8")
    public String insertProjec(String projectName, String userId, String type) {
        if (Integer.parseInt(type)==2){
            JSONObject json = en.encapsulationStrJsonTwo(1, 1);
            return JsonUtil.writeAsString(json);
        }

        JSONObject json = sysProjecAndModelService.insertProjec(projectName, Integer.valueOf(userId), Integer.valueOf(type));
        return JsonUtil.writeAsString(json);
    }

}
