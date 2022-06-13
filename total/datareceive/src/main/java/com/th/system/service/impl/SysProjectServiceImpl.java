package com.th.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.th.system.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.th.system.dao.SysProjecAndModelMapper;
import com.th.system.dao.SysProjectMapper;
import com.th.system.service.SysModelService;
import com.th.system.service.SysProjectService;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.JsonUtil;
import com.th.system.vo.SysModelVo;
import com.th.system.vo.SysProjectVo;
import com.th.system.vo.UserVo;
import org.springframework.transaction.annotation.Transactional;

@Service("SysProjectServiceImpl")
@Transactional
public class SysProjectServiceImpl implements SysProjectService {

    @Autowired
    private SysProjectMapper sysProjectMapper;

    @Autowired
    private SysProjecAndModelMapper sysProjecAndModelMapper;

    @Autowired
    private SysModelService sysModelService;

    EncapsulationUtil en = new EncapsulationUtil();

    @Override
    public Integer insertProject(SysProject sysProject) {
        // TODO Auto-generated method stub
        return sysProjectMapper.insertProject(sysProject);
    }

    @Override
    public List<SysProjectVo> findByUserid(Integer userId, int limit, int size, String projectName, String val) {
        // TODO Auto-generated method stub
        return sysProjectMapper.findByUserid(userId, limit, size, projectName, val);
    }

    @Override
    public Integer updateProject(SysProject sysProject) {
        // TODO Auto-generated method stub
        return sysProjectMapper.updateProject(sysProject);
    }

    @Override
    public String delProject(Integer projectId) {
        List<SysProjecAndModel> list = sysProjecAndModelMapper.findBySysProjecId(projectId);
        Integer result = sysProjectMapper.delProject(projectId);
        if (result != 0) {
            for (int i = 0; i < list.size(); i++) {
                sysProjecAndModelMapper.delect(projectId, list.get(i).getModelId());
//				List<SysUser> userList = sysProjectMapper.findByProjectIdAir(projectId);
//				for (int j = 0; j < userList.size(); j++) {
//					SysUserMapper.d
//				}
//				SysModel model = new SysModel();
//				model.setModelId(list.get(i).getModelId());
//				model.setIsBinding("1");
//				sysModelMapper.updateMoele(model);
            }
        }
        JSONObject json = en.encapsulationStrJson(result, result);
        return JsonUtil.writeAsString(json);
    }

    @Override
    public List<SysProjectVo> findByName(Integer userId, String projectName, int size, int limit, String val) {
        // TODO Auto-generated method stub
        return sysProjectMapper.findByName(userId, projectName, size, limit, val);
    }

    @Override
    public Integer findCount() {
        // TODO Auto-generated method stub
        return sysProjectMapper.findCount();
    }

    @Override
    public List<UserVo> findByProjectId(Integer projectId) {
        // TODO Auto-generated method stub
        return sysProjectMapper.findByProjectId(projectId);
    }

    @Override
    public List<SysProject> findByUseridPro(Integer userId, int limit, int size, String projectName, String val, Integer type) {
        // TODO Auto-generated method stub
        return sysProjectMapper.findByUseridPro(userId, limit, size, projectName, val, type);
    }

    @Override
    public UserVo findByProjectIdPro(Integer projectId) {
        // TODO Auto-generated method stub
        return sysProjectMapper.findByProjectIdPro(projectId);
    }

    @Override
    public List<SysUser> findByProjectIdAir(Integer projectId) {
        // TODO Auto-generated method stub
        return sysProjectMapper.findByProjectIdAir(projectId);
    }

    @Override
    public Integer findYearTime(String time) {
        // TODO Auto-generated method stub
        return sysProjectMapper.findYearTime(time);
    }

    @Override
    public Integer findWeekTime(String time1, String time2) {
        // TODO Auto-generated method stub
        return sysProjectMapper.findWeekTime(time1, time2);
    }

    @Override
    public JSONObject findByProjectIdAir2(Integer projectId) {
        UserVo projectVo = new UserVo();
        List<SysModel> listVo = new ArrayList<SysModel>();
        UserVo list = sysProjectMapper.findByProjectIdPro(projectId);
        List<SysModel> modelList = sysProjecAndModelMapper.findBySysProjecIdPro(projectId);
        for (int j = 0; j < modelList.size(); j++) {
            SysModel model = new SysModel();
            model.setModelId(modelList.get(j).getModelId());
            model.setModelName(modelList.get(j).getModelName());
            listVo.add(model);
        }
        projectVo = list;
        JSONObject message = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("result", projectVo);
        data.put("code", 200);
        data.put("modelList", listVo);
        message.put("data", data);
        message.put("status", 200);
        message.put("message", "成功");
        return message;
    }

    @Override
    public JSONObject showProject(HttpServletRequest request, String page, String limit, String projectName, Integer type) {
        HttpSession session = request.getSession();
        Integer userid = (Integer) session.getAttribute("userid");
        String role = "1";//(String) session.getAttribute("role");
        Integer useridPro = userid;
        if (role.equals("1")) {
            useridPro = null;
        }
        List<SysProject> list = sysProjectMapper.findByUseridPro(useridPro, 0, 0, projectName, null, type);
        List<SysProjectVo> data = new ArrayList<>();
        if (list != null && list.size() > 0) {
            int pages = Integer.parseInt(page);
            Integer size = 0;
            for (int i = 0; i < list.size(); i++) {
                List<SysModelVo> modelList = sysModelService.findByProjectId(list.get(i).getProjectId(), pages, Integer.parseInt(limit), null, "111");
                List<SysModelVo> modelList2 = sysModelService.findByProjectId(list.get(i).getProjectId(), pages, Integer.parseInt(limit), null, null);
                String val = "";
                for (int j = 0; j < modelList.size(); j++) {
                    if (j == 0) {
                        val += modelList.get(j).getModelName();
                    } else {
                        val += "," + modelList.get(j).getModelName();
                    }
                }
                size += modelList2.size();
                SysProjectVo project = new SysProjectVo();
                project.setProjectId(list.get(i).getProjectId());
                project.setProjectName(list.get(i).getProjectName());
                project.setModelName(val);
                project.setCreationTime(list.get(i).getCreationTime());
                project.setTime(list.get(i).getTime());
                project.setUserId(userid);
                project.setUserName(list.get(i).getUserName());
                project.setType(list.get(i).getType());
                data.add(project);
            }
            JSONObject json = en.encapsulationlimitJson(data, page, limit, size);
            return json;
        }
        JSONObject json = en.encapsulationlimitJson(data, page, limit, 0);
        return json;
    }

    @Override
    public List<ModelNameVo> findModelByProjectId(Integer projectId) {
        return sysProjectMapper.findModelByProjectId(projectId);
    }

    @Override
    public JSONObject showModleByUser(HttpServletRequest request, String modelName) {
        HttpSession session = request.getSession();
        Integer userid = (Integer) session.getAttribute("userid");
        String role = (String) session.getAttribute("role");
        Integer useridPro = userid;
        if (role.equals("1")) {
            useridPro = null;
        }
        List<SysProjectVo> list = sysProjectMapper.findByUserid(useridPro, 0, 0, null, null);
        List<SysProjectVo> data = new ArrayList<SysProjectVo>();
        for (int i = 0; i < list.size(); i++) {
            List<SysModelVo> modelList = sysModelService.findByProjectId(list.get(i).getProjectId(), 0, 0, modelName, null);
            for (int j = 0; j < modelList.size(); j++) {
                SysProjectVo project = new SysProjectVo();
                project.setProjectId(list.get(i).getProjectId());
                project.setProjectName(list.get(i).getProjectName());
                project.setModelName(modelList.get(j).getModelName());
                project.setModelId(modelList.get(j).getModelId());
                project.setCoordinateX(modelList.get(j).getCoordinateX());
                project.setCoordinateY(modelList.get(j).getCoordinateY());
                project.setPicturePath(modelList.get(j).getPicturePath());
                data.add(project);
            }
        }
        JSONObject json = en.encapsulationListJson(data);
        return json;
    }

}
