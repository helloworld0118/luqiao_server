package com.core.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.entity.LaborCost;
import com.core.entity.MaterialReceived;
import com.core.entity.MechanicsPrice;
import com.core.entity.Oil;
import com.core.service.ProgramService;
import com.core.util.Utils;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/api")
public class ProgramApi {

	@Autowired
	private ProgramService programService;
	
	
	@RequestMapping(value ="/getNodes", method=RequestMethod.GET)
	public @ResponseBody String getNodes(HttpServletRequest request,String code) {
		String date = request.getParameter("date");
		int type = 0;
		String typeParam = request.getParameter("type");
		if(null!=typeParam&&!"".equals(typeParam)) {
			type = Integer.parseInt(typeParam);
		}
		return new Gson().toJson(programService.getNodes(code, date,type));
	}
	
	@RequestMapping(value ="/addLabor", method=RequestMethod.POST)
	public @ResponseBody String addLabor(HttpServletRequest request,String code,LaborCost entity) {
		boolean success = programService.addLabor(code, entity);
		return Utils.ajaxReturn(success);
	}
	
	@RequestMapping(value ="/addMaterialReceived", method=RequestMethod.POST)
	public @ResponseBody String addMaterialReceived(HttpServletRequest request,String code,MaterialReceived entity) {
		boolean success = programService.addMaterialReceived(code, entity);
		return Utils.ajaxReturn(success);
	}
	@RequestMapping(value ="/addMechanicsPrice", method=RequestMethod.POST)
	public @ResponseBody String addMechanicsPrice(HttpServletRequest request,String code,MechanicsPrice entity) {
		boolean success = programService.addMechanicsPrice(code, entity);
		return Utils.ajaxReturn(success);
	}
	
	@RequestMapping(value ="/materialReceived/list", method=RequestMethod.GET)
	public @ResponseBody String getMaterialReceivedList(HttpServletRequest request,String code,int project,int staff) {
		String date = request.getParameter("date");
		return new Gson().toJson(programService.materialRecevicelist(code, project, staff, date));
	}
	
	@RequestMapping(value ="/labor/list", method=RequestMethod.GET)
	public @ResponseBody String getLaborList(HttpServletRequest request,String code,int project,int staff) {
		String date = request.getParameter("date");
		return new Gson().toJson(programService.laborlist(code, project, staff, date));
	}
	
	@RequestMapping(value ="/mechanicPrices/list", method=RequestMethod.GET)
	public @ResponseBody String getMechanicsList(HttpServletRequest request,String code,int project,int staff) {
		String date = request.getParameter("date");
		return new Gson().toJson(programService.mechanicsPriceList(code, project, staff, date));
	}
	
	@RequestMapping(value = "/oil/list", method = RequestMethod.GET)
	public @ResponseBody String getOilds(HttpServletRequest request, String code,int project,int staff) {
		String date = request.getParameter("date");
		return  new Gson().toJson(programService.oilList(code, project,staff,date));
	}
	
	@RequestMapping(value = "/oil/add", method = RequestMethod.POST)
	public @ResponseBody String addOil(HttpServletRequest request, String code, Oil oil) {
		return Utils.ajaxReturn(programService.addOil(code, oil));
	}
	
}
