package test;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.core.api.ProgramApi;
import com.core.controller.FinanceController;
import com.core.controller.ForemanController;
import com.core.controller.ProjectController;
import com.core.controller.UserController;
import com.core.entity.Foreman;
import com.core.entity.LaborCost;
import com.core.entity.MaterialReceived;
import com.core.entity.Mechanics;
import com.core.entity.MechanicsPrice;
import com.core.entity.Staff;
import com.core.util.Utils;

/**
 * @RunWith:使用pringJUnit4ClassRunner.class进行测试
 * @ContextConfiguration:加载配置文件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:/springmvc.xml", "classpath*:/web.xml" })
public class HelloControllerTest {
	// 模拟request,response
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@Autowired
	private UserController staffController;
	@Autowired
	private ProjectController projectController;
	@Autowired
	private ForemanController foremanController;
	
	@Autowired
	private FinanceController financeController;
	
	
	@Autowired
	private ProgramApi programApi;
	/*
	 * 测试开始之前进行初始化
	 */
	@Before
	public void setUp() throws Exception {
		request = new MockHttpServletRequest();
		request.setCharacterEncoding("UTF-8");
		request.getSession().setAttribute("currentUser", "aaa");
		request.getSession().setAttribute("isSuperAdmin", "true");
		request.getSession().setAttribute("current_project", 35);
		request.getSession().setAttribute("code", "NH01");
		
		response = new MockHttpServletResponse();
	}

	public void testUser() {
		request.setParameter("username", "admin");
		request.setParameter("password", "123456");
		try {
			// 判断控制器执行后是否返回字符串"/index"用于渲染
			//System.out.println(staffController.list());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testgetMenlist() {
		request.setParameter("username", "admin");
		request.setParameter("password", "123456");
		try {
			System.out.println(staffController.login(request, response, "NH01", "123123", "123456"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	public void testGetModule() {
		Foreman foreman=new Foreman();
		foreman.setId(10);
		Mechanics ms = new Mechanics();
		ms.setmName("bbb");
		System.out.println(projectController.getMaterialInfo(request));
		//System.out.println(projectController.getInfo(request));
		//System.out.println(foremanController.getCarsList(request, 0,2));
//		System.out.println(projectController.list(request));;
		//System.out.println(staffController.searchUsers(request));;
		//projectController.ajaxlist(request,0,1);
		//request.setParameter("daterange", "2018-01-09 to 2018-01-11");
		//System.out.println(financeController.loanInfoList(request, 0, 10));
		//System.out.println(foremanController.showMaterialPirces(request,8,5));
		//UUID.randomUUID().toString().replaceAll("-", "")
		//System.out.println(Utils.getVoucherNo());
		//17122814420001
		//1712281443147240001
		//1712281443227620001
	}

	
    public void addUser() {
		Staff staff =new Staff();
		staff.setEducation("1");
		staff.setHiredate("2012-11-11");
		staff.setMobile("12132");
		staff.setName("12312");
		int [] roles = new int[]{1};
    		//staffController.addUser(request, staff,12,roles,1);
    }
    
    public void addLabor() {
    	LaborCost entity = new LaborCost();
    	//entity.setBaseType(1);
    	entity.setWorker(20);
    	entity.setForeman(16);
    	entity.setDate(Utils.getYesterday());
    	entity.setIcount(10);
    	entity.setNode("K100+100");
    	entity.setProgram("圆管涵");
    	entity.setIprocedure("基坑");
    	entity.setProject(35);
    	entity.setWorkerNums(10);
    	entity.setPrice(100);
    	programApi.addLabor(request, "NH01", entity);
    }
    
    @Test
    public void addMetarRec() {
    	System.out.println("车".split("/")[0]);
//    	MaterialReceived entity = new MaterialReceived();
//    	//entity.setBaseType(3);
//    	entity.setDate(Utils.getYesterday());
//    	entity.setDistance(10);
//    	entity.setFreightPrice(100);
//    	entity.setIcount(10);
//    	entity.setProgram("圆管涵");
//    	entity.setIprocedure("基坑");
//    	entity.setMaterial(26);
//    	entity.setMaterialPrice(500);
//    	entity.setNode("K100+100");
//    	entity.setProject(35);
//    	entity.setSkip(21);
//    	entity.setSupplier(8);
//    	programApi.addMaterialReceived(request, "NH01", entity);
    }
   
    public void addMenchi() {
    	MechanicsPrice entity = new MechanicsPrice();
    	entity.setProgram("圆管涵");
    	entity.setIprocedure("基坑");
    	entity.setNode("K100+100");
    	entity.setProject(35);
    	entity.setIcount(20);
    	entity.setUnitPrice(100);
    	entity.setUnitPriceType("计天");
    	entity.setMechanic(15);
    	entity.setDate(Utils.getYesterday());
    	entity.setCreateDate(Utils.getCurrentTime());;
    	programApi.addMechanicsPrice(request, "NH01", entity);
    }
    
}