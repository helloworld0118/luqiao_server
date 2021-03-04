package com.core.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.entity.DayBook;
import com.core.entity.Expense;
import com.core.entity.Project;
import com.core.entity.ProjectStaffRoleExample;
import com.core.entity.Role;
import com.core.entity.Staff;
import com.core.entity.TokenInfo;
import com.core.entity.main.Enterprise;
import com.core.mapper.ProjectStaffRoleMapper;
import com.core.service.EnterpriseService;
import com.core.service.FinanceService;
import com.core.service.ProjectService;
import com.core.service.RoleMenuService;
import com.core.service.StaffService;
import com.core.service.TokenService;
import com.core.util.MD5Util;
import com.core.util.SignHelper;
import com.core.util.Utils;
import com.core.util.model.api.ProjectRoleModel;
import com.core.util.model.api.UserModel;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/api")
public class UserApi {

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private RoleMenuService roleMenuService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@RequestMapping(value ="/login", method=RequestMethod.POST)
	public @ResponseBody String login(HttpServletRequest request,String code,String mobile,String password) {
		
		Enterprise enterprise  = enterpriseService.getByCode(code);
		if(null==enterprise) {
			return Utils.ajaxReturn(false, "无此企业编码");
		}
		int userId=0;
		if(enterprise.getMobile().equals(mobile)) {
			return Utils.ajaxReturn(false, "此账号无APP相关权限");
		}
		Staff staff = staffService.getUserByMobile(code, mobile);
		if (null != staff) {
			if(staff.getPassword().equals(MD5Util.encrypt(password))) {
				List<Project> temp = projectService.getProjectList(code, staff.getId());
				UserModel userModel = new UserModel();
				userModel.setStaff(staff);
				boolean haveRight=false;
				for (Project project : temp) {
					List<Role> rlist = roleMenuService.getRoleList(code, project.getId(), staff.getId());
					ProjectRoleModel model = new ProjectRoleModel();
					model.setProject(project);
					for (Role role : rlist) {
						if(role.getId()!=1&&role.getId()!=2) {
							haveRight=true;
							model.getRoles().add(role);
						}
					}
					userModel.getProjectRoles().add(model);
				}
				if(haveRight) {
					boolean success = false;
					TokenInfo tokeninfo = tokenService.getToken(code, staff.getId());
					String token = Utils.createToken(code, mobile);
					if(null!=tokeninfo) {
						tokeninfo.setToken(token);
						tokeninfo.setCode(code);
						tokeninfo.setDate(Utils.getCurrentTime());
						success = tokenService.updateToken(code,tokeninfo);
					}else {
						tokeninfo = new TokenInfo();
						tokeninfo.setStaff(staff.getId());
						tokeninfo.setToken(token);
						tokeninfo.setCode(code);
						tokeninfo.setDate(Utils.getCurrentTime());
						success=tokenService.addToken(code, tokeninfo);
					}
					userModel.setToken(token);
					return Utils.ajaxReturn(success,new Gson().toJson(userModel));
				}
				return Utils.ajaxReturn(false, "此账号无APP相关权限");
			}
			return Utils.ajaxReturn(false, "账号与密码不匹配");
		}
		return Utils.ajaxReturn(false, "此企业下无此账号");
	}
	
	@RequestMapping(value ="/update/{id}", method=RequestMethod.POST)
	public @ResponseBody String updatePassword(HttpServletRequest request,String code,@PathVariable("id") int id,String oldPassword,String newPassword) {
		Staff staff = staffService.getStaffById(code, id);
		if(!staff.getPassword().equals(MD5Util.encrypt(oldPassword))) {
			return Utils.ajaxReturn(false, "原密码输入错误");
		}
		staff.setPassword(MD5Util.encrypt(newPassword));
		boolean result = staffService.updateStaff(code, staff);
		return Utils.ajaxReturn(result);
	}
	
	
	
	public void add222(HttpServletRequest request,String code) {
		String token = request.getHeader("token");
		
//		String code = request.getParameter("code");
		String SALT = MD5Util.encrypt(code+token);
		try {
			SignHelper.getSignature(request.getParameterMap(), token, SALT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
