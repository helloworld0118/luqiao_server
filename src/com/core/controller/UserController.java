package com.core.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.entity.Menu;
import com.core.entity.Project;
import com.core.entity.Role;
import com.core.entity.Staff;
import com.core.entity.UnitTypeCombination;
import com.core.entity.main.Enterprise;
import com.core.service.EnterpriseService;
import com.core.service.ProjectService;
import com.core.service.RoleMenuService;
import com.core.service.StaffService;
import com.core.util.MD5Util;
import com.core.util.PageBean;
import com.core.util.PageBeanModel;
import com.core.util.Utils;
import com.core.util.model.MenuModel;
import com.core.util.model.StaffModel;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;

@Controller
@Scope("prototype")
public class UserController {

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private RoleMenuService roleMenuService;
	
	@Autowired 
	private ProjectService projectService;
	
	
	
	@RequestMapping(value = { "/index*","/login*"}, method=RequestMethod.GET)
	public String index(HttpServletRequest request){
		HttpSession session = request.getSession();
		
		Object obj = session.getAttribute("currentUser");
		if(null==obj) {
			return "login";
		}
		Object isSuperAdmin = session.getAttribute("isSuperAdmin");
		if(null != isSuperAdmin && isSuperAdmin.equals("true")) {
			return "redirect:/project/list";
		}
		String code = (String) request.getSession().getAttribute("code");
		List<Project> list = mutiProject(request,code,((Staff)obj).getId());
		if(list.size()>1) {
			return "redirect:/project/list";
		}
		return "redirect:/project/"+list.get(0).getId();
	}
	
	@RequestMapping(value = "/login*",method=RequestMethod.POST)
	public String login(HttpServletRequest request,HttpServletResponse response,String code,String mobile,String password) {
		
		if(code!=null&&!Utils.isMatch(code)) {
			request.setAttribute("msg", "企业编码格式错误");
			return "login";
		}
		Enterprise temp  = enterpriseService.getByCode(code);
		if(null==temp) {
			request.setAttribute("msg", "无此企业编码");
			return "login";
		}
		
		Enterprise enterprise = enterpriseService.getByMobileAndCode(mobile.trim(),code.trim());
		if (null != enterprise) {
			if(enterprise.getPassword().equals(MD5Util.encrypt(password))) {
				request.getSession().setAttribute("code", code);
				List<Role> roles = new ArrayList<Role>();
				Role role =new Role();
				role.setName("公司管理员");
				roles.add(role);
				request.getSession().setAttribute("roles", roles);
				request.getSession().setAttribute("menus", roleMenuService.getMenusPc(code, 1));
				request.getSession().setAttribute("currentUser", enterprise);
				request.getSession().setAttribute("isSuperAdmin", "true");
				return "redirect:/project/list";
			}
			request.setAttribute("msg", "账号与密码不匹配");
			return "login";
		}
		Staff staff = staffService.getUserByMobile(code, mobile);
		if (null != staff) {
			if(staff.getPassword().equals(MD5Util.encrypt(password))) {
				request.getSession().setAttribute("code", code);
				request.getSession().setAttribute("currentUser", staff);
				List<Project> list =mutiProject(request,code,staff.getId());
				if(list.size()>1) {
					return "redirect:/project/list";
				}
				return "redirect:/project/"+list.get(0).getId();
			}
			request.setAttribute("msg", "账号与密码不匹配");
			return "login";
		}
		request.setAttribute("msg", "无此账号信息，请确认输入内容正确");
		return "login";
	}
	
	
	private List<Project> mutiProject(HttpServletRequest request,String code,int staff) {
		return projectService.getProjectList(code, staff);
	}
	@RequestMapping(value = "users", method=RequestMethod.GET)
	public @ResponseBody String list(HttpServletRequest request,int offset,int limit) {
		String code = (String) request.getSession().getAttribute("code");
		int project = Utils.getProject(request);
		PageBeanModel<StaffModel> page  = staffService.getStaffList(code,project,offset,limit);
		return new Gson().toJson(page);
	}
	
//	@RequestMapping(value = "user/{id}", method=RequestMethod.GET)
//	public @ResponseBody String getInfo(HttpServletRequest request,@PathVariable("id") int id) {
//		String code = (String) request.getSession().getAttribute("code");
//		int project = Utils.getProject(request);
//		return new Gson().toJson(staffService.getStaffById(code, id, project));
//	}
	
	@RequestMapping(value = "user/{id}", method=RequestMethod.POST)
	public @ResponseBody String edit(HttpServletRequest request,@PathVariable("id") int id,@ModelAttribute Staff staff,int[] role) {
		if(staff.getPassword().length()!=32){
			staff.setPassword(MD5Util.encrypt(staff.getPassword()));
		}
		int project = Utils.getProject(request);
		staff.setId(id);
		String unit_price= request.getParameter("unit_price");
		if(null==unit_price||"".equals(unit_price)) {
			unit_price="0";
		}
		String code = (String)request.getSession().getAttribute("code");
		boolean success = staffService.updateStaff(code, staff,project,role,Integer.parseInt(unit_price));
		return Utils.ajaxReturn(success);
	}
	
	@RequestMapping(value = "user/delete/{id}")
	public @ResponseBody String delete(HttpServletRequest request,@PathVariable("id") int id) {
		String code = (String)request.getSession().getAttribute("code");
		Staff staff = new Staff();
		staff.setId(id);
		int project = Utils.getProject(request);
		boolean success = staffService.deleteStaff(code, staff,project);
		return Utils.ajaxReturn(success);
	}
	
	@RequestMapping(value = "/user/addManager/{tempId}", method=RequestMethod.POST)
	public @ResponseBody String addManager(HttpServletRequest request, Staff staff,int[] role, UnitTypeCombination utc,@PathVariable("tempId") int tempId) {
		staff.setPassword(MD5Util.encrypt(staff.getPassword()));
		String code = (String)request.getSession().getAttribute("code");
		String unit_price= request.getParameter("unit_price");
		if(null==unit_price||"".equals(unit_price)) {
			unit_price="0";
		}
		staff.setCreateDate(Utils.getCurrentTime());
		boolean success = staffService.addStaff(code,tempId,staff,role,utc,Integer.parseInt(unit_price));
		return Utils.ajaxReturn(success, "{\"id\":"+staff.getId()+",\"name\":\""+staff.getName()+"\"}");
	}
	
	@RequestMapping(value = "/{tempId}/user/addManagerRelationship/{userId}", method=RequestMethod.POST)
	public @ResponseBody String addManagerRelationship(HttpServletRequest request, Staff staff,int[] role, UnitTypeCombination utc,@PathVariable("tempId") int tempId,@PathVariable("userId") int userId) {
		staff.setPassword(MD5Util.encrypt(staff.getPassword()));
		String code = (String)request.getSession().getAttribute("code");
		String unit_price= request.getParameter("unit_price");
		if(null==unit_price||"".equals(unit_price)) {
			unit_price="0";
		}
		staff.setCreateDate(Utils.getCurrentTime());
		boolean success = staffService.addRelationShip(code, tempId, staff, role, utc, Integer.parseInt(unit_price));
		//boolean success = staffService.addStaff(code,tempId,staff,role,utc,Integer.parseInt(unit_price));
		return Utils.ajaxReturn(success, "{\"id\":"+staff.getId()+",\"name\":\""+staff.getName()+"\"}");
	}
	
	@RequestMapping(value = "/user/add", method=RequestMethod.POST)
	public @ResponseBody String addUser(HttpServletRequest request,Staff staff, UnitTypeCombination utc,int[]role) {
		staff.setPassword(MD5Util.encrypt(staff.getPassword()));
		String code = (String)request.getSession().getAttribute("code");
		int project = Utils.getProject(request);
		String unit_price= request.getParameter("unit_price");
		if(null==unit_price||"".equals(unit_price)) {
			unit_price="0";
		}
		staff.setCreateDate(Utils.getCurrentTime());
		boolean success = staffService.addStaff(code,project,staff,role,utc,Integer.parseInt(unit_price));
		return Utils.ajaxReturn(success, "{\"id\":"+staff.getId()+",\"name\":\""+staff.getName()+"\"}");
	}
	@RequestMapping(value = "/user/addRelationship/{id}")
	public @ResponseBody String addRelationship(HttpServletRequest request,@PathVariable("id") int id,Staff staff,UnitTypeCombination utc,int[]role) {
		String code = (String)request.getSession().getAttribute("code");
		int project = Utils.getProject(request);
		staff.setId(id);
		String unit_price= request.getParameter("unit_price");
		if(null==unit_price||"".equals(unit_price)) {
			unit_price="0";
		}
		boolean success = staffService.addRelationShip(code,project,staff,role,utc,Integer.parseInt(unit_price));
		return Utils.ajaxReturn(success);

	}
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("menus");
		session.removeAttribute("roles");
		session.removeAttribute("currentUser");
		session.removeAttribute("isSuperAdmin");
		session.removeAttribute("current_project");
		session.removeAttribute("current_project_name");
		session.removeAttribute("modules");
		return "redirect:/login";
	}
	
	
	
	@RequestMapping(value = "/roles", method=RequestMethod.GET)
    public @ResponseBody String getRoleList(HttpServletRequest request) {
		String code = (String)request.getSession().getAttribute("code");
		return new Gson().toJson(roleMenuService.getRoleList(code));
    }
	
	@RequestMapping(value = "user/search", method=RequestMethod.GET)
	public @ResponseBody String searchUsers(HttpServletRequest request,String param) {
		String code = (String)request.getSession().getAttribute("code");
		return new Gson().toJson(staffService.getStaff(code, 0, param));
	}
	
	
	@RequestMapping(value = "user/searchMananger", method=RequestMethod.GET)
	public @ResponseBody String searchManangers(HttpServletRequest request,String param) {
		String code = (String)request.getSession().getAttribute("code");
		int managerRole= 3;
		return new Gson().toJson(staffService.getStaff(code, managerRole,param));
	}
	@RequestMapping(value = "user/password", method=RequestMethod.POST)
	public @ResponseBody String updatePassword(HttpServletRequest request,String oldpassworld,String newpassword,String confpassword) {
		HttpSession session = request.getSession();
		String code = (String)session.getAttribute("code");
		Object isSuperAdmin = session.getAttribute("isSuperAdmin");
		if(null != isSuperAdmin && isSuperAdmin.equals("true")) {
			Enterprise enterprise = (Enterprise) session.getAttribute("currentUser");
			if(!enterprise.getPassword().equals(MD5Util.encrypt(oldpassworld))) {
				return Utils.ajaxReturn(false, "原密码输入错误");
			}
			enterprise.setPassword(MD5Util.encrypt(newpassword));
			boolean result = enterpriseService.updateEnterprise(enterprise);
			return Utils.ajaxReturn(result);
		}else {
			Staff staff = (Staff) session.getAttribute("currentUser");
			if(!staff.getPassword().equals(MD5Util.encrypt(oldpassworld))) {
				return Utils.ajaxReturn(false, "原密码输入错误");
			}
			staff.setPassword(MD5Util.encrypt(newpassword));
			boolean result = staffService.updateStaff(code, staff);
			return Utils.ajaxReturn(result);
		}
	}
	@RequestMapping(value = { "user/hasName" })
	public @ResponseBody String hasName(HttpServletRequest request,String name) {
		String code = (String) request.getSession().getAttribute("code");
		String param=request.getParameter("id");
		int id = 0;
		if(null!=param&&!"".equals(param)) {
			id = Integer.parseInt(param);
		}
		return Utils.ajaxReturn(staffService.hasName(code,id,name));
	}
	
	
	@RequestMapping(value = { "user/hasMobile" })
	public @ResponseBody String hasMobile(HttpServletRequest request,String name) {
		String code = (String) request.getSession().getAttribute("code");
		String param=request.getParameter("id");
		int id = 0;
		if(null!=param&&!"".equals(param)) {
			id = Integer.parseInt(param);
		}
		return Utils.ajaxReturn(staffService.hasMobile(code,id,name));
	}
}