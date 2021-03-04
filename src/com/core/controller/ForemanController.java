package com.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.entity.Foreman;
import com.core.entity.Mechanics;
import com.core.entity.PriceFluctuation;
import com.core.entity.UnitTypeCombination;
import com.core.entity.WorkChargeInfo;
import com.core.service.ForemanService;
import com.core.util.PageBean;
import com.core.util.Utils;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;

@Controller
@Scope("prototype")
public class ForemanController {

	@Autowired
	private ForemanService foremanService;
	
	@RequestMapping(value = "/foremans")
	public @ResponseBody String list(HttpServletRequest request,int offset,int limit) {
		String code = (String) request.getSession().getAttribute("code");
		int project = Utils.getProject(request);
		return new Gson().toJson(foremanService.getForemanList(code, project,offset,limit));
	}
	
	@RequestMapping(value = "/foreman/delete/{id}")
	public @ResponseBody String deleteForeman(HttpServletRequest request,@PathVariable("id") int id) {
		String code = (String) request.getSession().getAttribute("code");
		return new Gson().toJson(foremanService.deleteForeman(code,Utils.getProject(request), id));
	}
	
	
	@RequestMapping(value = "/basetypes/{type}")
	public @ResponseBody String getBaseType(HttpServletRequest request,@PathVariable("type") int type) {
		String code = (String) request.getSession().getAttribute("code");
		return new Gson().toJson(foremanService.getBaseTypeByType(code, type));
	}
	
	@RequestMapping(value = "/priceTypes/{type}")
	public @ResponseBody String getPriceType(HttpServletRequest request,@PathVariable("type") int type) {
		String code = (String) request.getSession().getAttribute("code");
		return new Gson().toJson(foremanService.getBasePriceTypeByType(code, type));
	}
	
	@RequestMapping(value = "/{foreman}/add")
	public @ResponseBody String addForeman(HttpServletRequest request,@PathVariable("foreman") int id,Foreman foreman) {
		String code = (String) request.getSession().getAttribute("code");
		foreman.setCreateDate(Utils.getCurrentTime());
		return Utils.ajaxReturn(foremanService.addForeman(code, foreman,Utils.getProject(request),id));
	}
	@RequestMapping(value = "/foreman/edit/{id}")
	public @ResponseBody String editForeman(HttpServletRequest request,@PathVariable("id") int id,Foreman foreman) {
		String code = (String) request.getSession().getAttribute("code");
		foreman.setId(id);
		return Utils.ajaxReturn(foremanService.updateForeman(code, foreman));
	}
	
	@RequestMapping(value = "/{foreman}/workers")
	public @ResponseBody String getWorkers(HttpServletRequest request,@PathVariable("foreman") int foreman) {
		String code = (String) request.getSession().getAttribute("code");
		return new Gson().toJson(foremanService.getworkChargeInfoList(code, Utils.getProject(request), foreman));
	}
	@RequestMapping(value = "/{foreman}/showWorkers")
	public @ResponseBody String showWorkers(HttpServletRequest request,@PathVariable("foreman") int foreman) {
		String code = (String) request.getSession().getAttribute("code");
		return new Gson().toJson(foremanService.getShowWorkChargeInfoList(code, Utils.getProject(request), foreman));
	}
	
	@RequestMapping(value = "/{foreman}/addWorkInfo")
	public @ResponseBody String addWorkerInfo(HttpServletRequest request,@PathVariable("foreman") int foreman,
			UnitTypeCombination unitTypeCombination,PriceFluctuation priceFluctuation, int count,int workerType) {
		String code = (String) request.getSession().getAttribute("code");
		priceFluctuation.setType(0);
		priceFluctuation.setCreateDate(Utils.getCurrentTime());
		priceFluctuation.setStartDate(Utils.getCurrentDate());
		WorkChargeInfo workChargeInfo=new WorkChargeInfo();
		workChargeInfo.setWorkerType(workerType);
		workChargeInfo.setCount(count);
		workChargeInfo.setForeman(foreman);
		workChargeInfo.setCreateDate(Utils.getCurrentTime());
		workChargeInfo.setProject(Utils.getProject(request));
		int result = foremanService.addWorkCharge(code, workChargeInfo,unitTypeCombination,priceFluctuation);
		if(-1==result) {
			return Utils.ajaxReturn4ajax(false,"已经有相应记录，不能重复添加");
		}else if(1==result) {
			return Utils.ajaxReturn(true,workChargeInfo.getId()+"");
		}
		return Utils.ajaxReturn4ajax(false,"添加出错");
	}
	
	
	
	@RequestMapping(value = "/{foreman}/editWorkInfo/{worker}")
	public @ResponseBody String editWorkInfo(HttpServletRequest request,@PathVariable("worker") int worker,@PathVariable("foreman") int foreman,
			UnitTypeCombination unitTypeCombination,PriceFluctuation priceFluctuation, int count,int workerType) {
		String code = (String) request.getSession().getAttribute("code");
		
		WorkChargeInfo workChargeInfo=new WorkChargeInfo();
		workChargeInfo.setId(worker);
		workChargeInfo.setWorkerType(workerType);
		workChargeInfo.setCount(count);
		workChargeInfo.setForeman(foreman);
		workChargeInfo.setProject(Utils.getProject(request));
		
		priceFluctuation.setType(0);
		priceFluctuation.setCreateDate(Utils.getCurrentTime());
		priceFluctuation.setStartDate(Utils.getCurrentDate());
		int result = foremanService.updateWorkCharge(code, workChargeInfo,unitTypeCombination,priceFluctuation);
		if(-1==result) {
			return Utils.ajaxReturn4ajax(false,"已经有相应记录，不能重复添加");
		}else if(1==result) {
			return Utils.ajaxReturn(true,workChargeInfo.getId()+"");
		}
		return Utils.ajaxReturn4ajax(false,"编辑出错");
	}
	@RequestMapping(value = "/{foreman}/deleteWorkInfo/{worker}")
	public @ResponseBody String deleteWorkerInfo(HttpServletRequest request,@PathVariable("worker") int worker) {
		String code = (String) request.getSession().getAttribute("code");
		boolean success = foremanService.deleteWorkCharge(code, worker);
		return Utils.ajaxReturn(success);
	}
	@RequestMapping(value = "/foreman/search", method=RequestMethod.GET)
	public @ResponseBody String searchUsers(HttpServletRequest request,String param,int type) {
		String code = (String)request.getSession().getAttribute("code");
		return new Gson().toJson(foremanService.getForemanList(code, param,type, 0, 10));
	}
	@RequestMapping(value = "/foreman/addRelationship/{id}")
	public @ResponseBody String addRelationship(HttpServletRequest request,@PathVariable("id") int id) {
		String code = (String)request.getSession().getAttribute("code");
		return Utils.ajaxReturn(foremanService.addRelationship(code, id, Utils.getProject(request)));
	}
	
	@RequestMapping(value = "/cars")
	public @ResponseBody String getCarsList(HttpServletRequest request,int offset, int limit) {
		String code = (String)request.getSession().getAttribute("code");
		PageHelper.startPage(offset, limit);
		return new Gson().toJson(foremanService.getMechanicsList(code, Utils.getProject(request),0));
	}
	@RequestMapping(value = "/mechanics")
	public @ResponseBody String getMechanics(HttpServletRequest request,int offset, int limit) {
		String code = (String)request.getSession().getAttribute("code");
		PageHelper.startPage(offset, limit);
		return new Gson().toJson(foremanService.getMechanics(code, Utils.getProject(request)));
	}
	@RequestMapping(value = "/skips")
	public @ResponseBody String getSkips(HttpServletRequest request,int offset, int limit) {
		String code = (String)request.getSession().getAttribute("code");
		PageHelper.startPage(offset, limit);
		return new Gson().toJson(foremanService.getSkipList(code, Utils.getProject(request)));
	}
	
//	@RequestMapping(value = "/addCar")
//	public @ResponseBody String addCar(HttpServletRequest request,Foreman foreman, Mechanics mechanics) {
//		String code = (String)request.getSession().getAttribute("code");
//		//return new Gson().toJson(foremanService.getMechanicsList(code, Utils.getProject(request),0));
//		foreman.setType(1);
//		foreman.setCreateDate(Utils.getCurrentTime());
//		mechanics.setCreateDate(Utils.getCurrentTime());
//		boolean success = foremanService.addMechanic(code, foreman, Utils.getProject(request), mechanics);
//		return Utils.ajaxReturn(success);
//	}
	@RequestMapping(value = {"/addCar"})
	public @ResponseBody String addCars(HttpServletRequest request,Foreman foreman, Mechanics mechanics,int basePriceType) {
		String code = (String)request.getSession().getAttribute("code");
		//return new Gson().toJson(foremanService.getMechanicsList(code, Utils.getProject(request),0));
		//foreman.setType(2);
		foreman.setCreateDate(Utils.getCurrentTime());
		mechanics.setCreateDate(Utils.getCurrentTime());
		String priceParam = request.getParameter("price");
		int price = 0;
		if(null!=priceParam&&!"".equals(priceParam)) {
			price=Integer.parseInt(priceParam);
		}
		boolean success = foremanService.addMechanic(code, foreman, Utils.getProject(request), mechanics,basePriceType,price,0);
		return Utils.ajaxReturn(success);
	}
	@RequestMapping(value = {"{mid}/addMechanics"})
	public @ResponseBody String addMechanics(HttpServletRequest request,Foreman foreman, Mechanics mechanics,@PathVariable("mid") int mid) {
		String code = (String)request.getSession().getAttribute("code");
		foreman.setCreateDate(Utils.getCurrentTime());
		mechanics.setCreateDate(Utils.getCurrentTime());
		boolean success = foremanService.addMechanic(code, Utils.getProject(request), foreman, mechanics,mid,1);
		return Utils.ajaxReturn(success);
	}
//	@RequestMapping(value = "/car/edit/{id}")
//	public @ResponseBody String editCar(HttpServletRequest request,@PathVariable("id") int carId,int foremanId,Foreman foreman, Mechanics mechanics) {
//		String code = (String)request.getSession().getAttribute("code");
//		//return new Gson().toJson(foremanService.getMechanicsList(code, Utils.getProject(request),0));
//		foreman.setId(foremanId);
//		mechanics.setId(carId);
//		boolean success = foremanService.updateMechanic(code, foreman, mechanics);
//		return Utils.ajaxReturn(success);
//	}
	
	@RequestMapping(value = {"/car/edit/{id}"})
	public @ResponseBody String editCars(HttpServletRequest request,@PathVariable("id") int carId,int foremanId,Foreman foreman, Mechanics mechanics,int basePriceType) {
		String code = (String)request.getSession().getAttribute("code");
		//return new Gson().toJson(foremanService.getMechanicsList(code, Utils.getProject(request),0));
		foreman.setId(foremanId);
		mechanics.setId(carId);
		mechanics.setmType(Integer.parseInt(request.getParameter("mType")));
		String priceParam = request.getParameter("price");
		int price = 0;
		if(null!=priceParam&&!"".equals(priceParam)) {
			price=Integer.parseInt(priceParam);
		}
		boolean success = foremanService.updateMechanic(code, foreman, mechanics,Utils.getProject(request),basePriceType,price);
		return Utils.ajaxReturn(success);
	}
	
	@RequestMapping(value = {"/mechanics/edit/{id}"})
	public @ResponseBody String editMechanics(HttpServletRequest request,@PathVariable("id") int mechanic,int foremanId,Foreman foreman, Mechanics mechanics) {
		String code = (String)request.getSession().getAttribute("code");
		//return new Gson().toJson(foremanService.getMechanicsList(code, Utils.getProject(request),0));
		foreman.setId(foremanId);
		mechanics.setId(mechanic);
		mechanics.setmType(Integer.parseInt(request.getParameter("mType")));
		boolean success = foremanService.updateMechanic(code, foreman, mechanics, Utils.getProject(request));
		return Utils.ajaxReturn(success);
	}
	
	@RequestMapping(value = { "/car/delete/{id}",  "/mechanics/delete/{id}"})
	public @ResponseBody String deleteCar(HttpServletRequest request,@PathVariable("id") int carId,int foreman) {
		String code = (String)request.getSession().getAttribute("code");
		//return new Gson().toJson(foremanService.getMechanicsList(code, Utils.getProject(request),0));
		boolean success = foremanService.deleteMechanic(code, Utils.getProject(request), foreman, carId);
		return Utils.ajaxReturn(success);
	}
	
	@RequestMapping(value = "/mechanics/search")
	public @ResponseBody String searchMechanics(HttpServletRequest request,String param,int type) {
		String code = (String)request.getSession().getAttribute("code");
		PageHelper.startPage(0, 10);
		return new Gson().toJson(foremanService.getMechanicsByPlateName(code, param,type));
	}
	@RequestMapping(value = "/car/addRelationship")
	public @ResponseBody String addCarRelationship(HttpServletRequest request,Foreman foreman, Mechanics mechanics,int foremanId,int carId) {
		String code = (String)request.getSession().getAttribute("code");
		if(0!=foremanId) {
			foreman.setId(foremanId);
		}
		if(0!=carId) {
			mechanics.setId(carId);
		}
		//return  Utils.ajaxReturn(false);
		return Utils.ajaxReturn(foremanService.addCarRelationShip(code, foreman,mechanics,Utils.getProject(request)));
	}
	
	@RequestMapping(value = "/mechanics/addRelationship/{mid}")
	public @ResponseBody String addMechanicsRelationship(HttpServletRequest request,Foreman foreman, Mechanics mechanics,
			@PathVariable("mid") int mid) {
		String code = (String)request.getSession().getAttribute("code");
		String foremanId = request.getParameter("foremanId");
		if(null!=foremanId&&!"".equals(foremanId)) {
			foreman.setId(Integer.parseInt(foremanId));
		}
		String mechanicsId = request.getParameter("mechanicsId");
		if(null!=mechanicsId&&!"".equals(mechanicsId)) {
			mechanics.setId(Integer.parseInt(mechanicsId));
		}
		//return  Utils.ajaxReturn(false);
		return Utils.ajaxReturn(foremanService.addMechanicsRelationShip(code, foreman,mechanics,Utils.getProject(request),mid));
	}
	
	@RequestMapping(value = "/skip/addRelationship/{temp_skipId}")
	public @ResponseBody String addSkipRelationship(HttpServletRequest request,Foreman foreman, Mechanics mechanics,
			@PathVariable("temp_skipId") int temp_skipId) {
		String code = (String)request.getSession().getAttribute("code");
		String foremanId = request.getParameter("foremanId");
		if(null!=foremanId&&!"".equals(foremanId)) {
			foreman.setId(Integer.parseInt(foremanId));
		}
		String skipId = request.getParameter("skipId");
		if(null!=skipId&&!"".equals(skipId)) {
			mechanics.setId(Integer.parseInt(skipId));
		}
		return Utils.ajaxReturn(foremanService.addSkipRelationShip(code, foreman,mechanics,Utils.getProject(request), temp_skipId));
	}
	
	
	@RequestMapping(value = {"{temp_id}/addSkip"})
	public @ResponseBody String addSkip(HttpServletRequest request,Foreman foreman, Mechanics mechanics,@PathVariable("temp_id") int skip) {
		String code = (String)request.getSession().getAttribute("code");
		foreman.setCreateDate(Utils.getCurrentTime());
		mechanics.setCreateDate(Utils.getCurrentTime());
		boolean success = foremanService.addSkip(code, foreman, Utils.getProject(request), mechanics,skip);
		return Utils.ajaxReturn(success);
	}
	
	
	@RequestMapping(value = {"/skip/edit/{skipId}"})
	public @ResponseBody String editSkip(HttpServletRequest request,@PathVariable("skipId") int skipId,Foreman foreman, Mechanics mechanics,int foremanId) {
		String code = (String)request.getSession().getAttribute("code");
		foreman.setId(foremanId);
		mechanics.setId(skipId);
		boolean success = foremanService.updateSkip(code, foreman, Utils.getProject(request), mechanics);
		return Utils.ajaxReturn(success);
	}
	
	
	@RequestMapping(value = {"/skip/delete/{skipId}"})
	public @ResponseBody String deleteSkip(HttpServletRequest request,@PathVariable("skipId") int skipId,int foreman) {
		String code = (String)request.getSession().getAttribute("code");
		boolean success = foremanService.deleteSkip(code, Utils.getProject(request), foreman, skipId);
		return Utils.ajaxReturn(success);
	}
	@RequestMapping(value = {"/{foreman}/{skip}/showMaterialPrices"})
	public @ResponseBody String showMaterialPirces(HttpServletRequest request,@PathVariable("foreman") int foreman,@PathVariable("skip") int skip) {
		String code = (String)request.getSession().getAttribute("code");
		return new Gson().toJson(foremanService.showMaterialPrices(code, Utils.getProject(request), foreman, skip));
	}
	
	@RequestMapping(value = "/{mechanic}/addMechanicPrice")
	public @ResponseBody String addMechanicPrice(HttpServletRequest request,@PathVariable("mechanic") int mechanic,int basePriceType,int baseType,int unitPrice) {
		String code = (String)request.getSession().getAttribute("code");
		int id=foremanService.addMechanicPrice(code,Utils.getProject(request),mechanic,basePriceType,baseType,unitPrice);
		if (id==0) {
			return Utils.ajaxReturn4ajax(false,"添加出错");
		}else if(id==-1) {
			return Utils.ajaxReturn4ajax(false,"已经有相应记录，不能重复添加");
		}
		return Utils.ajaxReturn(true,id+"");
	}
	
	@RequestMapping(value = "/{mechanic}/editMechanicPrice/{id}")
	public @ResponseBody String editMechanicPrice(HttpServletRequest request,@PathVariable("mechanic") int mechanic,int basePriceType,int baseType,int unitPrice,@PathVariable("id") int id) {
		String code = (String)request.getSession().getAttribute("code");
		int result =foremanService.updateMechanicPrice(code,Utils.getProject(request),mechanic,basePriceType,baseType,unitPrice,id);
		if (result==0) {
			return Utils.ajaxReturn4ajax(false,"编辑出错");
		}else if(result==-1) {
			return Utils.ajaxReturn4ajax(false,"已经有相应记录，不能重复添加");
		}
		return Utils.ajaxReturn(true);
	}
	@RequestMapping(value = "/{mechanic}/deleteMechanicPrice/{id}")
	public @ResponseBody String deleteMechanicPrice(HttpServletRequest request,@PathVariable("mechanic") int mechanic,@PathVariable("id") int id) {
		String code = (String)request.getSession().getAttribute("code");
		boolean result =foremanService.deleteMechanicPrice(code,id);
		return Utils.ajaxReturn(result);
	}
	@RequestMapping(value = "/{mechanic}/mechanicPrices")
	public @ResponseBody String mechanicPrices(HttpServletRequest request,@PathVariable("mechanic") int mechanic) {
		String code = (String)request.getSession().getAttribute("code");
		return new Gson().toJson(foremanService.getMechanicPrice(code,Utils.getProject(request),mechanic,false));
	}
	@RequestMapping(value = "/{mechanic}/showMechanicPrices")
	public @ResponseBody String showMechanicPrices(HttpServletRequest request,@PathVariable("mechanic") int mechanic) {
		String code = (String)request.getSession().getAttribute("code");
		return new Gson().toJson(foremanService.getMechanicPrice(code,Utils.getProject(request),mechanic,true));
	}
	
}
