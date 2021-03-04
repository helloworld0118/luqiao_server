package com.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.entity.LaborCost;
import com.core.entity.MaterialReceived;
import com.core.entity.MechanicsPrice;
import com.core.entity.Project;
import com.core.entity.Staff;
import com.core.entity.main.Enterprise;
import com.core.entity.main.Module;
import com.core.service.EnterpriseService;
import com.core.service.ProgramService;
import com.core.service.ProjectService;
import com.core.service.RoleMenuService;
import com.core.service.StaffService;
import com.core.util.PageBean;
import com.core.util.PageBeanModel;
import com.core.util.Utils;
import com.core.util.model.IndexModel;
import com.core.util.model.IndexPriceModel;
import com.core.util.model.ProjectListModel;
import com.core.util.model.ProjectModel;
import com.core.util.model.StaffModel;
import com.core.util.model.chart.PieDataModel;
import com.core.util.model.chart.PieModel;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;

@Controller
@Scope("prototype")
@RequestMapping(value = { "/project", ""})
public class ProjectController {

	@Autowired
	private EnterpriseService enterpriseService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private RoleMenuService roleMenuService;
	
	@Autowired
	private ProgramService programService;
	
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request) {
		setProjects(request);
		return "project";
	}
	
	@RequestMapping(value = "/allmanager")
	public String allmanger(HttpServletRequest request) {
		Project project = (Project) request.getSession().getAttribute("current_project_obj");
		if(null==project) {
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
		ProjectModel model = new ProjectModel(project.getId(),project.getName(),project.getCode(),
				enterpriseService.getModuleById(project.getModule()).getName(),
				project.getStartDate(),project.getAddress(),project.getEndDate(),project.getCreateDate(),
				project.getStartNode(),project.getEndNode(),project.getDescription(),project.getRemark(),project.getModule());
		
		request.setAttribute("model",model);
		return "allmanager";
	}
	private List<Project> mutiProject(HttpServletRequest request,String code,int staff) {
		return projectService.getProjectList(code, staff);
	}
	@RequestMapping(value = "/todayInfo")
	public @ResponseBody String getTodayInfo(HttpServletRequest request) {
		int project = Utils.getProject(request);
		String database =(String) request.getSession().getAttribute("code");
		IndexPriceModel<LaborCost> labor = programService.todayLabor(database, project);
		List<LaborCost> lclist = labor.getList();
		IndexPriceModel<MaterialReceived> material = programService.todayMaterialReceived(database, project);
		List<MaterialReceived> mrlist = material.getList();
		IndexPriceModel<MechanicsPrice> mechanics = programService.todayMechanicsPrice(database, project);
		List<MechanicsPrice> mplist = mechanics.getList();
		PieModel model1 = initPieChart("圆管涵",lclist,mrlist,mplist);
		PieModel model2 = initPieChart("盖板涵",lclist,mrlist,mplist);
		PieModel model3 = initPieChart("箱涵",lclist,mrlist,mplist);
		List<PieModel> list = new ArrayList<PieModel>();
		list.add(model1);
		list.add(model2);
		list.add(model3);
		return new Gson().toJson(new IndexModel(labor, material, mechanics, list));
	}
	
	@RequestMapping(value = "/alldayInfo")
	public @ResponseBody String getAlldayInfo(HttpServletRequest request) {
		int project = Utils.getProject(request);
		String database =(String) request.getSession().getAttribute("code");
		IndexPriceModel<LaborCost> labor = programService.alldayLabor(database, project);
		List<LaborCost> lclist = labor.getList();
		IndexPriceModel<MaterialReceived> material = programService.alldayMaterial(database, project);
		List<MaterialReceived> mrlist = material.getList();
		IndexPriceModel<MechanicsPrice> mechanics = programService.alldayMechanics(database, project);
		List<MechanicsPrice> mplist = mechanics.getList();
		PieModel model1 = initPieChart("圆管涵",lclist,mrlist,mplist);
		PieModel model2 = initPieChart("盖板涵",lclist,mrlist,mplist);
		PieModel model3 = initPieChart("箱涵",lclist,mrlist,mplist);
		List<PieModel> list = new ArrayList<PieModel>();
		list.add(model1);
		list.add(model2);
		list.add(model3);
		return new Gson().toJson(new IndexModel(labor, material, mechanics, list));
	}
	
	@RequestMapping(value = "/materialInfo")
	public @ResponseBody String getMaterialInfo(HttpServletRequest request) {
		String database =(String) request.getSession().getAttribute("code");
		return new Gson().toJson(programService.getUsedMaterial(database, Utils.getProject(request)));
	}
	
	private PieModel initPieChart(String title,List<LaborCost> lclist,List<MaterialReceived> mrlist,List<MechanicsPrice> mplist) {
		PieModel model = new PieModel();
		model.setTitle(title);
		Map<String, String> nodes = new HashMap<String, String>();
		PieDataModel laborModel = new PieDataModel();
		laborModel.setName("人工");
		laborModel.setValue(0);
		PieDataModel materialModel = new PieDataModel();
		materialModel.setName("材料");
		materialModel.setValue(0);
		PieDataModel mechanicsModel = new PieDataModel();
		mechanicsModel.setName("机械");
		mechanicsModel.setValue(0);
		for (LaborCost temp : lclist) {
			if(temp.getProgram().equals(title)) {
				if(!nodes.containsKey(temp.getNode())) {
					nodes.put(temp.getNode(), temp.getNode());
				}
				laborModel.setValue(laborModel.getValue()+temp.getPrice());
			}
		}
		for (MaterialReceived temp : mrlist) {
			if(temp.getProgram().equals(title)) {
				if(!nodes.containsKey(temp.getNode())) {
					nodes.put(temp.getNode(), temp.getNode());
				}
				materialModel.setValue(materialModel.getValue()+temp.getFreightPrice()+temp.getMaterialPrice());
			}
		}
		for (MechanicsPrice temp : mplist) {
			if(temp.getProgram().equals(title)) {
				if(!nodes.containsKey(temp.getNode())) {
					nodes.put(temp.getNode(), temp.getNode());
				}
				mechanicsModel.setValue(mechanicsModel.getValue()+temp.getPrice());
			}
		}
		StringBuffer sb = new StringBuffer();
		for (String  key : nodes.keySet()) {
			sb.append(key +",");
		}
		if(sb.length()>1) {
			sb.deleteCharAt(sb.length()-1);
		}
		model.setSubTitle(sb.toString());
		
		List<PieDataModel> list = new ArrayList<PieDataModel>();
		list.add(laborModel);
		list.add(materialModel);
		list.add(mechanicsModel);
		model.setData(list);
		return model;
	}
	@RequestMapping(value = "/ajaxlist")
	public @ResponseBody String ajaxlist(HttpServletRequest request,int offset,int limit) {
		/**
		PageHelper.startPage(offset,limit);
		String code = (String) request.getSession().getAttribute("code");
		int manager = 0;
		Object isSuperAdmin = request.getSession().getAttribute("isSuperAdmin");
		if (null == isSuperAdmin || isSuperAdmin.equals("false")) {
			Staff staff = (Staff) request.getSession().getAttribute("currentUser");
			manager = staff.getId();
		}
		List<Project> projects = projectService.getProjectList(code, manager,false);
		List<ProjectModel> models = new ArrayList<ProjectModel>();
		for (Project project : projects) {
			String pmanagerName="";
			int pmanager=-1;
			if(null!=project.getPmanager()) {
				Staff staff = staffService.getStaffById(code, project.getPmanager());
				pmanagerName=staff.getName();
				pmanager=staff.getId();
			}
			models.add(new ProjectModel(project.getId(),project.getName(),project.getCode(),
					pmanagerName,
					enterpriseService.getModuleById(project.getModule()).getName(),
					project.getStartDate(),project.getAddress(),project.getEndDate(),project.getCreateDate(),
					project.getStartNode(),project.getEndNode(),project.getDescription(),project.getRemark(),project.getModule(),pmanager));
		}
		PageBean<Project> temp =new PageBean<Project>(projects);
		PageBeanModel<ProjectModel> page =new PageBeanModel<ProjectModel>(temp.getTotal(),models);
		return new Gson().toJson(page);
		**/
		Project project = (Project) request.getSession().getAttribute("current_project_obj");
		String code = (String) request.getSession().getAttribute("code");
		List<ProjectModel> models = new ArrayList<ProjectModel>();
		models.add(new ProjectModel(project.getId(),project.getName(),project.getCode(),
				enterpriseService.getModuleById(project.getModule()).getName(),
				project.getStartDate(),project.getAddress(),project.getEndDate(),project.getCreateDate(),
				project.getStartNode(),project.getEndNode(),project.getDescription(),project.getRemark(),project.getModule()));
		
		PageBeanModel<ProjectModel> page =new PageBeanModel<ProjectModel>(1,models);
		return new Gson().toJson(page);
	}
	private void setProjects(HttpServletRequest request) {
		String code = (String) request.getSession().getAttribute("code");
		int manager = 0;
		Object superAdmin = request.getSession().getAttribute("isSuperAdmin");
		if (null == superAdmin || superAdmin.equals("false")) {
			Staff staff = (Staff) request.getSession().getAttribute("currentUser");
			manager = staff.getId();
		}
		List<Project> projects = projectService.getProjectList(code, manager);
		List<ProjectListModel> models = new ArrayList<ProjectListModel>();
		if (projects.size() <= 4 && projects.size() > 0) {
			models.add(new ProjectListModel(projects));
		} else {
			List<Project> temps = new ArrayList<Project>();
			int i = 0;
			for (Project project : projects) {
				temps.add(project);
				i += 1;
				if (i == 4) {
					models.add(new ProjectListModel(temps));
					i = 0;
					temps = new ArrayList<Project>();
				}
			}
			if(i!=0) {
				models.add(new ProjectListModel(temps));
			}
		}
		request.setAttribute("models", models);
	}

	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request) {
		String code = (String) request.getSession().getAttribute("code");
		setModules(request, code);
		setMangers(request, 0, code);
		request.setAttribute("title", "添加项目");
		String tempId=Utils.getCurrentTime2();
		request.setAttribute("formURL", "project/addInfo/"+tempId);
		request.setAttribute("tempId", tempId);
		return "add_project";
	}

	private void setModules(HttpServletRequest request, String code) {
		Enterprise enterprise = null;
		try {
			enterprise = (Enterprise) request.getSession().getAttribute("currentUser");
		} catch (Exception e) {
			enterprise = enterpriseService.getByCode(code);
		}
		List<Module> list = enterpriseService.getModuelsByEnterprise(enterprise);
		request.setAttribute("modules", list);
	}

	private void setMangers(HttpServletRequest request, int project,String code) {
		
		Object isSuperAdmin = request.getSession().getAttribute("isSuperAdmin");
		request.setAttribute("managers", getManager(request, code, project,2,isSuperAdmin));
		request.setAttribute("pmanagers", getManager(request, code, project,3,isSuperAdmin));
	}
    private List<Staff> getManager(HttpServletRequest request,String code,int project,int role, Object isSuperAdmin) {
    	List<Staff> all = staffService.getStaffByRole(code,0,role);
    	if(null==all) {
    		all =new ArrayList<>();
    	}
    	Enterprise enterprise = (Enterprise) request.getSession().getAttribute("currentUser");
		Staff staff = new Staff();
		staff.setId(0);
		staff.setName(enterprise.getName());
		all.add(staff);
    	if(project==0) {
    		return all;
    	}
    	List<Integer> list = staffService.getStaffIdsByRole(code,project,role);
		for (Staff manager : all) {
			for (int id : list) {
				if(manager.getId()==id) {
					manager.setRemark("selected");
				}
			}
		}
		return all;
    }
    
	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, @PathVariable("id") int id) {
		String code = (String) request.getSession().getAttribute("code");
		Project project = projectService.getProject(code, id);
		setModules(request, code);
		setMangers(request, id, code);
		request.setAttribute("project", project);
		request.setAttribute("title", "编辑项目");
		request.setAttribute("formURL", "project/edit/" + id);
		request.setAttribute("tempId", id);
		request.getSession().setAttribute("current_project_obj", project);
		return "add_project";
	}

	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.POST)
	public String edit(HttpServletRequest request, @PathVariable("id") int id, @ModelAttribute Project project,
			String daterange,int [] manager, int[] pmanager) {
		String code = (String) request.getSession().getAttribute("code");
		try {
			String[] dates = daterange.split("to");
			if (dates.length==2) {
				project.setStartDate(dates[0].trim());
				project.setEndDate(dates[1].trim());
			}
		} catch (Exception e) {
		}
		project.setId(id);
		project.setCreateDate(Utils.getCurrentTime());
		boolean success = projectService.updateProject(code, project);
		if (success) {
			success = staffService.updateStaffProject(code, project.getId(), id, manager,2);
			success = staffService.updateStaffProject(code, project.getId(), id, pmanager,3);
		}
		if (!success) {
			request.setAttribute("msg", "修改失败，请重试");
		}
		return success == true ? "redirect:/project/list" : "redirect:/project/edit/" + id;
	}
	
	@RequestMapping(value = { "/ajaxEdit/{id}" }, method = RequestMethod.POST)
	public @ResponseBody String editAjax(HttpServletRequest request, @PathVariable("id") int id, @ModelAttribute Project project,
			String daterange) {
		String code = (String) request.getSession().getAttribute("code");
		try {
			String[] dates = daterange.split("to");
			if (dates.length==2) {
				project.setStartDate(dates[0].trim());
				project.setEndDate(dates[1].trim());
			}
			
		} catch (Exception e) {
		}
		project.setId(id);
		boolean success = projectService.updateProject(code, project);

		if (!success) {
			request.setAttribute("msg", "修改失败，请重试");
		}else {
//			if (success) {
//				Project oldProject =(Project) request.getSession().getAttribute("current_project_obj");
//				success = staffService.updateStaffPorject(code, project.getId(), id, project.getPmanager(),oldProject.getPmanager(), 3);
//			}
			request.getSession().removeAttribute("current_project_obj");
			request.getSession().setAttribute("current_project_obj", project);
		}
		
		return Utils.ajaxReturn(success);
	}



	@RequestMapping(value = { "/addInfo/{tempId}" }, method = RequestMethod.POST)
	public String add(HttpServletRequest request, @ModelAttribute Project project, String daterange,@PathVariable("tempId") int tempId
			,int [] manager, int[] pmanager) {
		String code = (String) request.getSession().getAttribute("code");
		try {
			String[] dates = daterange.split("to");
			project.setStartDate(dates[0].trim());
			project.setEndDate(dates[1].trim());
		} catch (Exception e) {
		}
		project.setCreateDate(Utils.getCurrentTime());
		boolean success = projectService.addProject(code, project);
		if (success) {
			success = staffService.updateStaffProject(code, project.getId(),tempId, manager, 2);
			success = staffService.updateStaffProject(code, project.getId(),tempId, pmanager, 3);
		}
		if (!success) {
			request.setAttribute("msg", "添加失败，请重试");
		}
		return success == true ? "redirect:/project/list" : "addproject";
	}

	@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
	public @ResponseBody String addAjax(HttpServletRequest request, @ModelAttribute Project project, String daterange) {
		String code = (String) request.getSession().getAttribute("code");
		try {
			String[] dates = daterange.split("to");
			project.setStartDate(dates[0].trim());
			project.setEndDate(dates[1].trim());
		} catch (Exception e) {
		}
		project.setCreateDate(Utils.getCurrentTime());
		boolean success = projectService.addProject(code, project);
		return Utils.ajaxReturn(success);
	}

	@RequestMapping(value = { "/delete/{id}" })
	public @ResponseBody String delete(HttpServletRequest request, @PathVariable("id") int id) {
		String code = (String) request.getSession().getAttribute("code");
		boolean success = projectService.deleteProject(code, id);
		return Utils.ajaxReturn(success);
	}
	@RequestMapping(value = { "/del/{id}" })
	public String del(HttpServletRequest request, @PathVariable("id") int id) {
		String code = (String) request.getSession().getAttribute("code");
		projectService.deleteProject(code, id);
		return "redirect:/project/list";
	}
	
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
	public String index(HttpServletRequest request, @PathVariable("id") int id) {
		String code = (String) request.getSession().getAttribute("code");
		// boolean success = projectService.updateProject(code, project);
		Project project = projectService.getProject(code, id);
		request.getSession().setAttribute("current_project", id);
		request.getSession().setAttribute("current_project_name", project.getName());
		request.getSession().setAttribute("current_project_obj", project);
		Object isSuperAdmin = request.getSession().getAttribute("isSuperAdmin");
		if (null == isSuperAdmin || isSuperAdmin.equals("false")) {
			Staff staff = (Staff) request.getSession().getAttribute("currentUser");
			request.getSession().setAttribute("menus", roleMenuService.getMenusPc(code, id, staff));
			request.getSession().setAttribute("roles", roleMenuService.getRoleList(code, id, staff.getId()));
		}
		setProjects(request);
		return "index";
	}
	@RequestMapping(value = { "/getModules" }, method = RequestMethod.GET)
	public @ResponseBody String ajaxGetModules(HttpServletRequest request) {
		String code = (String) request.getSession().getAttribute("code");
		Enterprise enterprise = null;
		try {
			enterprise = (Enterprise) request.getSession().getAttribute("currentUser");
		} catch (Exception e) {
			enterprise = enterpriseService.getByCode(code);
		}
		List<Module> list = enterpriseService.getModuelsByEnterprise(enterprise);
		return new Gson().toJson(list);
	}
//	@RequestMapping(value = { "/getManagers" }, method = RequestMethod.GET)
//	public @ResponseBody String getManagers(HttpServletRequest request) {
//		String code = (String) request.getSession().getAttribute("code");
//		Object isSuperAdmin = request.getSession().getAttribute("isSuperAdmin");
//		return new Gson().toJson(getManager(request, code,Utils.getProject(request), 2, isSuperAdmin));
//	}
//	@RequestMapping(value = { "/getPManagers" }, method = RequestMethod.GET)
//	public @ResponseBody String getPManagers(HttpServletRequest request) {
//		String code = (String) request.getSession().getAttribute("code");
//		Object isSuperAdmin = request.getSession().getAttribute("isSuperAdmin");
//		return new Gson().toJson(getManager(request, code,Utils.getProject(request), 3, isSuperAdmin));
//	}
//	@RequestMapping(value = { "/hasAddbtn" }, method = RequestMethod.GET)
//	public @ResponseBody String hasAddbtn(HttpServletRequest request) {
//		Object isSuperAdmin = request.getSession().getAttribute("isSuperAdmin");
//		if(null==isSuperAdmin||"".equals(isSuperAdmin)) {
//			return Utils.ajaxReturn(false);
//		}
//		return Utils.ajaxReturn(true);
//	}
	@RequestMapping(value = { "/hasName" })
	public @ResponseBody String hasName(HttpServletRequest request,String name) {
		String code = (String) request.getSession().getAttribute("code");
		String param=request.getParameter("id");
		int id = 0;
		if(null!=param&&!"".equals(param)) {
			id = Integer.parseInt(param);
		}
		return Utils.ajaxReturn(projectService.hasProject(code, id, name));
	}
	
	
}
