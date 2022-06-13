package com.th.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.th.system.po.ModelNameVo;
import com.th.system.po.SysProject;
import com.th.system.po.SysUser;
import com.th.system.vo.SysProjectVo;
import com.th.system.vo.UserVo;

public interface SysProjectService {

    public JSONObject showModleByUser(HttpServletRequest request, String modelName);

    //新增项目
    public Integer insertProject(SysProject sysProject);

    //根据用户id 权限查询对应项目
    public List<SysProjectVo> findByUserid(Integer userId, int limit, int size, String projectName, String val);

    //修改
    public Integer updateProject(SysProject sysProject);

    //删除
    public String delProject(Integer projectId);

    //根据用户id 项目名称 权限查询对应项目
    public List<SysProjectVo> findByName(Integer userId, String projectName, int size, int limit, String val);

    //查询项目个数
    public Integer findCount();

    //根据id查询
    public UserVo findByProjectIdPro(Integer projectId);

    //根据id查询
    public List<UserVo> findByProjectId(Integer projectId);

    //根据用户id 权限查询对应项目
    public List<SysProject> findByUseridPro(Integer userId
            , int limit, int size, String projectName, String val, Integer type);

    //根据id查询
    public List<SysUser> findByProjectIdAir(Integer projectId);

    //查询项目个数年
    public Integer findYearTime(String time);

    //查询项目个数星期
    public Integer findWeekTime(String time1, String time2);

    public JSONObject findByProjectIdAir2(Integer projectId);

    public JSONObject showProject(HttpServletRequest request, String page, String limit, String projectName, Integer type);

    List<ModelNameVo> findModelByProjectId(Integer projectId);
}
