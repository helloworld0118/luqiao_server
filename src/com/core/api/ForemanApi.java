package com.core.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.service.ForemanService;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/api")
public class ForemanApi {
	@Autowired
	private ForemanService foremanService;
	
	
	@RequestMapping(value = "/foreman/list")
	public @ResponseBody String searchMechanics(HttpServletRequest request,String code,int project) {
		String limit = request.getParameter("limit");
		if(null!=limit&&!"".equals(limit)) {
			PageHelper.startPage(0, Integer.parseInt(limit));
		}
		String param = request.getParameter("param");
		return new Gson().toJson(foremanService.getForemanList(code,project,param,0));
	}
	@RequestMapping(value = "/foreman/{id}")
	public @ResponseBody String getWorkerDetailList(HttpServletRequest request,String code,int project,@PathVariable("id") int id) {
		//PageHelper.startPage(0, 10);
		//String param = request.getParameter("param");
		return new Gson().toJson(foremanService.getWorkerDetailList(code,project,id));
	}
	@RequestMapping(value = "/basetype/{type}")
	public @ResponseBody String getBaseType(HttpServletRequest request,String code,@PathVariable("type") int type) {
		return new Gson().toJson(foremanService.getBaseTypeByType(code, type));
	}
	@RequestMapping(value = "/priceType/{type}")
	public @ResponseBody String getPriceType(HttpServletRequest request,String code,@PathVariable("type") int type) {
		return new Gson().toJson(foremanService.getBasePriceTypeByType(code, type));
	}
	
}
