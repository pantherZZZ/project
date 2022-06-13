package com.th.system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.th.system.po.ModelNameVo;
import com.th.system.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.th.system.po.SysProject;
import com.th.system.service.SysModelService;
import com.th.system.service.SysProjectService;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.HttpClient;
import com.th.system.utils.JsonUtil;
import com.th.system.vo.SysModelVo;
import com.th.system.vo.SysProjectVo;

@CrossOrigin
@RestController
@RequestMapping("/project")
public class SysProjectController extends HttpClient {

    @Autowired
    private SysProjectService sysProjectService;

    @Autowired
    private SysModelService sysModelService;

    @Autowired
    private ProjectService projectService;

    EncapsulationUtil en = new EncapsulationUtil();

    //根据项目id 查询projectId
    @PostMapping(value = "/findByProjectId", produces = "application/json;charset=UTF-8")
    public String findByProjectId(HttpServletRequest request, Integer projectId) {
        JSONObject message = sysProjectService.findByProjectIdAir2(projectId);
        return JsonUtil.writeAsString(message);
    }

    //项目分配用户
    @PostMapping(value = "/allotUser", produces = "application/json;charset=UTF-8")
    public String allotUser(Integer userId, Integer projectId) {
//		JSONObject json = sysProjecAndModelService.insertPro(modelIdList, projectId);
        SysProject project = new SysProject();
        project.setProjectId(projectId);
        project.setUserId(userId);
        Integer result = sysProjectService.updateProject(project);
        JSONObject json = en.encapsulationStrJson(result, result);
        return JsonUtil.writeAsString(json);
    }


    //根据项目id 查询
    @PostMapping(value = "/findssProjsectCount", produces = "application/json;charset=UTF-8")
    public String findPssrojectCsount(HttpServletRequest request) {
        Integer result = sysProjectService.findCount();
        JSONObject json = en.encapsulationStrJson(result, result);
        return JsonUtil.writeAsString(json);
    }


    //根据当前登录人管理的项目(展示项目)
    @PostMapping(value = "/showProject", produces = "application/json;charset=UTF-8")
    public String showProject(HttpServletRequest request, String page, String limit, String projectName, Integer type) {
        JSONObject json = null;
        json = sysProjectService.showProject(request, page, limit, projectName, type);
        return JsonUtil.writeAsString(json);
    }

    /**
     * 根据项目查找结构物
     *
     * @param projectId
     * @return
     */
    @GetMapping(value = "/findModelByProjectId", produces = "application/json;charset=UTF-8")
    public String findModelByProjectId(Integer projectId) {
        List<ModelNameVo> modelNameVoList = sysProjectService.findModelByProjectId(projectId);
        JSONObject json = en.encapsulationListJson(modelNameVoList);
        return JsonUtil.writeAsString(json);
    }

    //根据当前登录人管理的项目(展示模型)展示所有模型坐标
    @PostMapping(value = "/showModleByUser", produces = "application/json;charset=UTF-8")
    public String showModleByUser(HttpServletRequest request, String modelName) {
        JSONObject json = sysProjectService.showModleByUser(request, modelName);
        return JsonUtil.writeAsString(json);
    }

    //根据当前项目id(展示模型)
    @PostMapping(value = "/showModleByProject", produces = "application/json;charset=UTF-8")
    public String showModleByProject(HttpServletRequest request, Integer projectId, String page, String limit) {
        int pages = Integer.parseInt(page) - 1;
        List<SysModelVo> modelList = sysModelService.findByProjectId(projectId, pages, Integer.parseInt(limit), null, "11");
        List<SysModelVo> modelList2 = sysModelService.findByProjectId(projectId, pages, Integer.parseInt(limit), null, null);
        JSONObject json = en.encapsulationlimitJson(modelList, page, limit, modelList2.size());
        return JsonUtil.writeAsString(json);
    }

    //删除当前项目
    @PostMapping(value = "/delProject", produces = "application/json;charset=UTF-8")
    public String delProject(HttpServletRequest request, String projectId) {
        SysProject sysProject = projectService.selectProject(Integer.parseInt(projectId));
        if (sysProject.getType()==2) {
            JSONObject json = en.encapsulationStrJsonThree(1, 1);
            return JsonUtil.writeAsString(json);
        }
        String result = sysProjectService.delProject(Integer.valueOf(projectId));
        return result;
    }

    //新增项目
    @RequestMapping(value = "/insertProject", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String insertProject(HttpServletRequest request, String projectName) {

        HttpSession session = request.getSession();
        Integer userid = (Integer) session.getAttribute("userid");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataTime = format.format(date);
        SysProject project = new SysProject();
        project.setProjectName(projectName);
        project.setTime(dataTime);
        project.setUserId(userid);
        Integer result = sysProjectService.insertProject(project);
        JSONObject json = en.encapsulationStrJson(result, result);
        return JsonUtil.writeAsString(json);
    }

    //查询全部项目 按照项目名称 登录人
    @PostMapping(value = "/findByProjectName", produces = "application/json;charset=UTF-8")
    public String findByProjectName(HttpServletRequest request, String projectName, String limit, String page) {
        HttpSession session = request.getSession();
        Integer userid = (Integer) session.getAttribute("userid");
        String role = "1";//(String) session.getAttribute("role");
        Integer useridPro = userid;
        if (role.equals("1")) {
            useridPro = null;
        }
        int pages = Integer.parseInt(page) - 1;
        List<SysProjectVo> list = sysProjectService.findByName(useridPro, projectName, pages, Integer.parseInt(limit), projectName);
        List<SysProjectVo> list2 = sysProjectService.findByName(useridPro, projectName, pages, Integer.parseInt(limit), null);
        JSONObject json = en.encapsulationlimitJson(list, page, limit, list2.size());
        return JsonUtil.writeAsString(json);
    }

    //查询全部项目 按照项目名称 查询
    @PostMapping(value = "/findByProjectNamePro", produces = "application/json;charset=UTF-8")
    public String findByProjectNamePro(HttpServletRequest request, String projectName, String limit, String page) {
        HttpSession session = request.getSession();
        Integer userid = (Integer) session.getAttribute("userid");
        String role = "1";//(String) session.getAttribute("role");
        Integer useridPro = userid;
        if (role.equals("1")) {
            useridPro = null;
        }
        int pages = Integer.parseInt(page) - 1;
        List<SysProjectVo> list = sysProjectService.findByName(useridPro, projectName, pages, Integer.parseInt(limit), projectName);
        List<SysProjectVo> list2 = sysProjectService.findByName(useridPro, projectName, pages, Integer.parseInt(limit), null);
        JSONObject json = en.encapsulationlimitJson(list, page, limit, list2.size());
        return JsonUtil.writeAsString(json);
    }


    //查询所有项目数量
    @GetMapping(value = "/findProjectCount", produces = "application/json;charset=UTF-8")
    public String findProjectCount(HttpServletRequest request) {
        Integer result = sysProjectService.findCount();
        JSONObject json1 = new JSONObject();
        JSONObject json2 = new JSONObject();
        json2.put("code", 200);
        json2.put("count", result);
        json1.put("data", json2);
        json1.put("status", 200);
        json1.put("message", "成功");
        return JsonUtil.writeAsString(json1);
    }
}
