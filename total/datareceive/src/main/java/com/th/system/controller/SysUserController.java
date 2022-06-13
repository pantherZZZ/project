package com.th.system.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.th.system.po.SysProject;
import com.th.system.po.SysUser;
import com.th.system.service.SysProjectService;
import com.th.system.service.SysUserService;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.HttpClient;
import com.th.system.utils.JsonUtil;
import com.th.system.utils.MD5;
import com.th.system.vo.SysUserVo;

/**
 * 系统设置
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class SysUserController extends HttpClient {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysProjectService sysProjectService;
	
	EncapsulationUtil en = new EncapsulationUtil();
	
	@PostMapping(value = "/findAll", produces="application/json;charset=UTF-8")
	public String findAll(HttpServletRequest request,String page,String limit,String userName,String mailbox,String phoneNumber,Integer type) {
		List<SysUserVo> userVoList = new ArrayList<SysUserVo>();
		int pages = Integer.parseInt(page);
		if(Integer.parseInt(page)!=0) {
			 pages = Integer.parseInt(page)-1;
		}
		List<SysUser> list = sysUserService.findAll(request, pages, Integer.parseInt(limit),userName,mailbox,phoneNumber,"1");
		List<SysUser> list2 = sysUserService.findAll(request, 0,0,userName,mailbox,phoneNumber,null);
		for (int i = 0; i < list.size(); i++) {
			SysUserVo user = new SysUserVo();
			List<SysProject> userList = sysProjectService.findByUseridPro(list.get(i).getUserId(), 0, 0, null, null,type);
			String projectName = "";
			for (int j = 0; j < userList.size(); j++) {
				if(userList.size() != 0) {
					if(j == 0) {
						projectName += userList.get(j).getProjectName();
				    }else {
						projectName += ","+userList.get(j).getProjectName();
					}
				}
			}
			SysUser usera =  list.get(i);
			user.setForbidden(usera.getForbidden());
			user.setIsMailbox(usera.getIsMailbox());
			user.setIsNote(usera.getIsNote());
			user.setLoginName(usera.getLoginName());
			user.setMailbox(usera.getMailbox());
			user.setNoDisturbing(usera.getNoDisturbing());
			user.setPhoneNumber(usera.getPhoneNumber());
			user.setProfilePicture(usera.getProfilePicture());
			user.setRole(usera.getRole());
			user.setUserId(usera.getUserId());
			user.setUserName(usera.getUserName());
			user.setProjectName(projectName);
			userVoList.add(user);
		}	
		JSONObject json = en.encapsulationlimitJson(userVoList, page, limit, list2.size());
		return JsonUtil.writeAsString(json);
	}
	
	//新增用户把用户设置为当前项目管理员(新增未有的用户)
	@PostMapping(value = "/insertUserAndProject1", produces="application/json;charset=UTF-8")
	public String insertUserAndProject1(HttpServletRequest request,String userName,String password,Integer projectId) {
		//新增用户
		SysUser user = new SysUser();
		String userId = UUID.randomUUID().toString().replace("-", "");
		user.setUserId(Integer.parseInt(userId));
		user.setLoginName(userName);
		user.setUserName(userName);
		user.setPassword(MD5.getMD5(password));
		user.setRole("2");
		user.setForbidden("1");
		user.setIsMailbox("1");
		user.setIsNote("1");
		user.setNoDisturbing("2");
		user.setForbidden("2");
		Integer result = sysUserService.insertUser(user);
		//添加到project表中
		SysProject project = new SysProject();
		project.setProjectId(projectId);
		project.setUserId(Integer.parseInt(userId));
		sysProjectService.updateProject(project);
		JSONObject json = en.encapsulationStrJson(result, result);
		return JsonUtil.writeAsString(json);
	}
	
	//新增用户把用户设置为当前项目管理员(新增已有的用户)
	@PostMapping(value = "/insertUserAndProject2", produces="application/json;charset=UTF-8")
	public String insertUserAndProject2(HttpServletRequest request,String userId,Integer projectId) {
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dataTime = format.format(date);
		//添加到project表中
		SysProject project = new SysProject();
		project.setProjectId(projectId);
		project.setUserId(Integer.parseInt(userId));
		project.setTime(dataTime);
		Integer result = sysProjectService.updateProject(project);
		int code = httpClientPost(request);
		JSONObject message =new JSONObject();
		JSONObject data =new JSONObject();
		data.put("result",result);
		data.put("code",code);
		message.put("data", data);
		message.put("status", 200);
		message.put("message", "成功");
		return JsonUtil.writeAsString(message);
	}
	
	
	//修改用户
	@PostMapping(value = "/updateUser", produces="application/json;charset=UTF-8")
	public String updateUser(HttpServletRequest request,String userId,String userName,String password,String projectId,String role,String phoneNumber,
			String mailbox,String isMailbox,String isNote,String noDisturbing,String forbidden) {
		SysUser user = new SysUser();
		user.setUserName(userName);
		user.setPassword(MD5.getMD5(password));
		user.setUserId(Integer.parseInt(userId));
		user.setRole(role);
		user.setPhoneNumber(phoneNumber);
		user.setMailbox(mailbox);
		user.setIsMailbox(isMailbox);
		user.setIsNote(isNote);
		user.setNoDisturbing(noDisturbing);
		user.setForbidden(forbidden);
		Integer count = sysUserService.updateUser(user);
		JSONObject json = en.encapsulationStrJson(count, count);
		return JsonUtil.writeAsString(json);
	}
	
	@PostMapping(value = "/verifyUserName", produces="application/json;charset=UTF-8")
	public String verifyUserName(String userName) {
		Integer count = sysUserService.verifyUserName(userName);
		JSONObject json = en.encapsulationStrJson(count, 1);
		return JsonUtil.writeAsString(json);
	}
	
	//修改当前登录用户
	@PostMapping(value = "/updateLoginUser", produces="application/json;charset=UTF-8")
	public String updateLoginUser(HttpServletRequest request,String userName,String password,String projectId,String role,String phoneNumber,
			String mailbox,String isMailbox,String isNote,String noDisturbing,String forbidden) {
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("userid");
		SysUser user = new SysUser();
		user.setLoginName(userName);
		user.setUserName(userName);
		user.setPassword(MD5.getMD5(password));
		user.setUserId(userid);
		user.setRole(role);
		user.setPhoneNumber(phoneNumber);
		user.setMailbox(isMailbox);
		user.setIsMailbox(isMailbox);
		user.setIsNote(isNote);
		user.setNoDisturbing(noDisturbing);
		user.setForbidden(forbidden);
		Integer result = sysUserService.updateUser(user);
		JSONObject json = en.encapsulationStrJson(result, result);
		return JsonUtil.writeAsString(json);
	}
	
	//按照userName或loginName查询非模糊查询
	@PostMapping(value = "/findByName", produces="application/json;charset=UTF-8")
	public String findByName(HttpServletRequest request,String userName,String loginName) {
		List<SysUser> list = new ArrayList<SysUser>();
		if(userName != null || loginName!= null) {
			list = sysUserService.findList(userName,loginName);
		}
		JSONObject json = en.encapsulationListJson(list);
		return JsonUtil.writeAsString(json);
	}
	
	//按照userName或loginName查询模糊查询
	@PostMapping(value = "/findByNamePro", produces="application/json;charset=UTF-8")
	public String findByNamePro(HttpServletRequest request,String userName,String loginName) {
		List<SysUser> list = new ArrayList<SysUser>();
		if(userName != null || loginName!= null) {
			list = sysUserService.findListPro(userName,loginName);
		}
		JSONObject json = en.encapsulationListJson(list);
		return JsonUtil.writeAsString(json);
	}
	
	//按照userid查询
	@PostMapping(value = "/findByUserId", produces="application/json;charset=UTF-8")
	public String findByUserId(HttpServletRequest request,Integer userId) {
		SysUser user = sysUserService.findByUserid(userId);
		int code = httpClientPost(request);
		JSONObject message =new JSONObject();
		JSONObject data =new JSONObject();
		data.put("result",user);
		data.put("code",code);
		message.put("data", data);
		message.put("status", 200);
		message.put("message", "成功");
		return JsonUtil.writeAsString(message);
	}
	
	//按照当前登录人userid查询
	@PostMapping(value = "/findByUserIdPro", produces="application/json;charset=UTF-8")
	public String findByUserIdPro(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("userid");
		SysUser user = sysUserService.findByUserid(userid);
		int code = httpClientPost(request);
		JSONObject message =new JSONObject();
		JSONObject data =new JSONObject();
		data.put("result",user);
		data.put("code",code);
		message.put("data", data);
		message.put("status", 200);
		message.put("message", "成功");
		return JsonUtil.writeAsString(message);
	}
	
	//登录获取基本信息
	@PostMapping(value = "/loginMessage", produces="application/json;charset=UTF-8")
	public String LoginMessage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("userid");
		SysUser user = sysUserService.findByUserid(userid);
		JSONObject message =new JSONObject();
		JSONObject data =new JSONObject();
		data.put("result",user);
		data.put("code",200);
		message.put("data", data);
		message.put("status", 200);
		message.put("message", "成功");
		return JsonUtil.writeAsString(message);
	}
	
	//登录获取基本信息
	@PostMapping(value = "/comparisonPasswordPro", produces="application/json;charset=UTF-8")
	public String comparisonPasswordPro(HttpServletRequest request,String newPassword,String oldPassword) {
		HttpSession session = request.getSession();
		JSONObject message =new JSONObject();
		JSONObject data =new JSONObject();
		Integer userid = (Integer) session.getAttribute("userid");
		String result = "";
		if((!newPassword.equals("") || newPassword != null) && (!oldPassword.equals("") || oldPassword != null)) {
			SysUser user = sysUserService.comparisonPassword(userid, MD5.getMD5(oldPassword));
			if(user != null) {
				SysUser sysUser = new SysUser();
				sysUser.setUserId(userid);
				sysUser.setPassword(MD5.getMD5(newPassword));
				sysUserService.updateUser(sysUser);
				result = "1";
				data.put("code",200);
			}else {
				result = "旧密码不正确";
				data.put("code",201);
			}
		}else {
			result = "新密码不可以和旧密码为空";
			data.put("code",202);
		}
		data.put("result",result);
		message.put("data", data);
		message.put("status", 200);
		message.put("message", "成功");
		return JsonUtil.writeAsString(message);
	}
	
	//登录获取基本信息
	@PostMapping(value = "/comparisonPassword", produces="application/json;charset=UTF-8")
	public String comparisonPassword(HttpServletRequest request,String password) {
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("userid");
		SysUser user = sysUserService.comparisonPassword(userid, MD5.getMD5(password));
		int code = httpClientPost(request);
		JSONObject message =new JSONObject();
		JSONObject data =new JSONObject();
		if(user != null) {
			data.put("result","OK");
		}else {
			data.put("result","ERROR");
		}
		data.put("code",code);
		message.put("data", data);
		message.put("status", 200);
		message.put("message", "成功");
		return JsonUtil.writeAsString(message);
	}
	
	//上传头像
	@PostMapping(value = "/uploading", produces="application/json;charset=UTF-8")
	public String uploading(HttpServletRequest request,@RequestParam(value="file")MultipartFile file,String userId) throws IllegalStateException, IOException {
		String fileName = file.getOriginalFilename();
	    // 获取文件的后缀名
	    String suffixName = fileName.substring(fileName.lastIndexOf("."));
	    String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-","").substring(6) + suffixName;
	    // 文件上传后的路径
	    String filePath = "/www/wwwroot/local/xm/upload/";
	    String size = "http://119.3.156.168:8083/"+newFileName;
	    File dest = new File(filePath + newFileName);
	    // 检测是否存在目录
	    if (!dest.getParentFile().exists()) {
	        dest.getParentFile().mkdirs();
	    }
	    file.transferTo(dest);
	    SysUser user = new SysUser();
	    user.setUserId(Integer.parseInt(userId));
	    user.setProfilePicture(size);
	    Integer result = sysUserService.updateUser(user);
	    JSONObject json = en.encapsulationStrJson(result, result);
		return JsonUtil.writeAsString(json);
	}
	
}
