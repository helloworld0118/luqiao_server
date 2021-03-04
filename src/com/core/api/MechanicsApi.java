package com.core.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.entity.Mechanics;
import com.core.service.ForemanService;
import com.core.util.Utils;
import com.core.util.model.MechanicModel;
import com.core.util.model.MechanicPriceModel;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/api")
public class MechanicsApi {

	
	@Autowired
	private ForemanService foremanService;
	
	
	@RequestMapping(value = "/mechanics/list")
	public @ResponseBody String searchMechanics(HttpServletRequest request,String code,int project) {
		//String code = (String)request.getSession().getAttribute("code");
		//String param = request.getParameter("param");
		return new Gson().toJson(foremanService.getMechanicsList(code, project));
	}
	
	
	@RequestMapping(value = "/skip/list")
	public @ResponseBody String searchSkips(HttpServletRequest request,String code,int project) {
		///String code = (String)request.getSession().getAttribute("code");
//		PageHelper.startPage(0, 100);
//		String param = request.getParameter("param");
		return new Gson().toJson(foremanService.getSkipsList4Api(code,project));
	}
	
	@RequestMapping(value = "/mechanics/all")
	public @ResponseBody String getAllMechanics(HttpServletRequest request,String code,int project) {
		return new Gson().toJson(foremanService.getMechanicsAll(code, project));
	}
	
	
	@RequestMapping(value ="/mechanics/{id}", method=RequestMethod.POST)
	public @ResponseBody String getMechanics(HttpServletRequest request,String code,int project,int foremanId,@PathVariable("id") int id) {
		//boolean success = programService.addMechanicsPrice(code, entity);
		//return Utils.ajaxReturn(success);
		return new Gson().toJson(foremanService.getMechanicsDetail(code, project,foremanId,id));
	}
	
	@RequestMapping(value ="/skip/{id}", method=RequestMethod.POST)
	public @ResponseBody String getSkips(HttpServletRequest request,String code,int project,int foremanId,@PathVariable("id") int id) {
		//boolean success = programService.addMechanicsPrice(code, entity);
		//return Utils.ajaxReturn(success);
		return new Gson().toJson(foremanService.getSkipDetail(code, project,foremanId,id));
	}

}
