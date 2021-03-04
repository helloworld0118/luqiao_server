package com.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.entity.Gaibanhan;
import com.core.entity.LaborCost;
import com.core.entity.MaterialReceived;
import com.core.entity.MechanicsPrice;
import com.core.entity.Xianghan;
import com.core.entity.Yuanguanhan;
import com.core.service.ProgramService;
import com.core.util.PageBean;
import com.core.util.Utils;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;

@Controller
@Scope("prototype")
public class ProgramController {
	@Autowired
	private ProgramService programService;
	
	@RequestMapping(value ="/getNodes", method=RequestMethod.GET)
	public @ResponseBody String getNodes(HttpServletRequest request) {
		String date = request.getParameter("date");
		String code = (String) request.getSession().getAttribute("code");
		int type = 0;
		String typeParam = request.getParameter("type");
		if(null!=typeParam&&!"".equals(typeParam)) {
			type = Integer.parseInt(typeParam);
		}
		return new Gson().toJson(programService.getNodes(code, date,type));
	}
	
	@RequestMapping(value = "/yuanguanhan")
	public @ResponseBody String yuanguanhan(HttpServletRequest request) {
		String code = (String) request.getSession().getAttribute("code");
		return new Gson().toJson(programService.yuanguanhanList(code, Utils.getProject(request)));
	}
	
	@RequestMapping(value = "/gaibanhan")
	public @ResponseBody String gaibanhan(HttpServletRequest request) {
		String code = (String) request.getSession().getAttribute("code");
		return new Gson().toJson(programService.gaibanhanList(code, Utils.getProject(request)));
	}
	
	@RequestMapping(value = "/xianghan")
	public @ResponseBody String xianghan(HttpServletRequest request) {
		String code = (String) request.getSession().getAttribute("code");
		return new Gson().toJson(programService.xianghanList(code, Utils.getProject(request)));
	}
	
	@RequestMapping(value = "/yuanguanhan/add")
	public @ResponseBody String addYuanguanhan(HttpServletRequest request,Yuanguanhan yuanguanhan) {
		String node = request.getParameter("col1[node]");
		String childNode = request.getParameter("col1[childNode]");
		if("".equals(childNode)) {
			childNode="0";
		}
		yuanguanhan.setNode(Integer.parseInt(node));
		yuanguanhan.setChildnode(Integer.parseInt(childNode));
		yuanguanhan.setProject(Utils.getProject(request));
		String code = (String) request.getSession().getAttribute("code");
		boolean success = programService.addYuanguanhan(code, yuanguanhan);
		return Utils.ajaxReturn(success,yuanguanhan.getId()+"");
	}
	
	@RequestMapping(value = "/gaibanhan/add")
	public @ResponseBody String addGaibanhan(HttpServletRequest request,Gaibanhan gaibanhan) {
		String node = request.getParameter("col1[node]");
		String childNode = request.getParameter("col1[childNode]");
		if("".equals(childNode)) {
			childNode="0";
		}
		gaibanhan.setNode(Integer.parseInt(node));
		gaibanhan.setChildnode(Integer.parseInt(childNode));
		gaibanhan.setProject(Utils.getProject(request));
		String code = (String) request.getSession().getAttribute("code");
		boolean success = programService.addGaibanhan(code, gaibanhan);
		return Utils.ajaxReturn(success,gaibanhan.getId()+"");
	}
	
	@RequestMapping(value = "/xianghan/add")
	public @ResponseBody String addXianghan(HttpServletRequest request,Xianghan xianghan) {
		String node = request.getParameter("col1[node]");
		String childNode = request.getParameter("col1[childNode]");
		if("".equals(childNode)) {
			childNode="0";
		}
		xianghan.setNode(Integer.parseInt(node));
		xianghan.setChildnode(Integer.parseInt(childNode));
		xianghan.setProject(Utils.getProject(request));
		String code = (String) request.getSession().getAttribute("code");
		boolean success = programService.addXianghan(code, xianghan);
		return Utils.ajaxReturn(success,xianghan.getId()+"");
	}
	
	
	@RequestMapping(value = "/yuanguanhan/edit/{id}")
	public @ResponseBody String addYuanguanhan(HttpServletRequest request,Yuanguanhan yuanguanhan,@PathVariable("id")int id) {
		String node = request.getParameter("col1[node]");
		String childNode = request.getParameter("col1[childNode]");
		
		if(null==node) {
			node = request.getParameter("node");
			childNode = request.getParameter("childnode");
		}
		if(null==childNode||"".equals(childNode)) {
			childNode="0";
		}
		yuanguanhan.setId(id);
		yuanguanhan.setNode(Integer.parseInt(node));
		yuanguanhan.setChildnode(Integer.parseInt(childNode));
		yuanguanhan.setProject(Utils.getProject(request));
		String code = (String) request.getSession().getAttribute("code");
		boolean success = programService.updateYuanguanhan(code, yuanguanhan);
		return Utils.ajaxReturn(success,yuanguanhan.getId()+"");
	}
	
	@RequestMapping(value = "/gaibanhan/edit/{id}")
	public @ResponseBody String addGaibanhan(HttpServletRequest request,Gaibanhan gaibanhan,@PathVariable("id")int id) {
		String node = request.getParameter("col1[node]");
		String childNode = request.getParameter("col1[childNode]");
		if(null==node) {
			node = request.getParameter("node");
			childNode = request.getParameter("childnode");
		}
		if(null==childNode||"".equals(childNode)) {
			childNode="0";
		}
		gaibanhan.setId(id);
		gaibanhan.setNode(Integer.parseInt(node));
		gaibanhan.setChildnode(Integer.parseInt(childNode));
		gaibanhan.setProject(Utils.getProject(request));
		String code = (String) request.getSession().getAttribute("code");
		boolean success = programService.updateGaibanhan(code, gaibanhan);
		return Utils.ajaxReturn(success,gaibanhan.getId()+"");
	}
	
	
	@RequestMapping(value = "/xianghan/edit/{id}")
	public @ResponseBody String addXianghan(HttpServletRequest request,Xianghan xianghan,@PathVariable("id")int id) {
		String node = request.getParameter("col1[node]");
		String childNode = request.getParameter("col1[childNode]");
		if(null==node) {
			node = request.getParameter("node");
			childNode = request.getParameter("childnode");
		}
		if(null==childNode||"".equals(childNode)) {
			childNode="0";
		}
		xianghan.setId(id);
		xianghan.setNode(Integer.parseInt(node));
		xianghan.setChildnode(Integer.parseInt(childNode));
		xianghan.setProject(Utils.getProject(request));
		String code = (String) request.getSession().getAttribute("code");
		boolean success = programService.updateXianghan(code, xianghan);
		return Utils.ajaxReturn(success,xianghan.getId()+"");
	}
	@RequestMapping(value = "/labor/list")
	public @ResponseBody String getLaborList(HttpServletRequest request,int offset,int limit) {
		String code = (String) request.getSession().getAttribute("code");
		PageHelper.offsetPage(offset, limit);
		PageHelper.orderBy("id desc");
		String dataRange = request.getParameter("daterange");
		String startDate=null;
		String endDate=null;
		if (null!=dataRange&&!"".equals(dataRange)) {
			startDate=dataRange.split("to")[0].trim();
			endDate=dataRange.split("to")[1].trim();
		}
		String program = request.getParameter("program");
		String procedure = request.getParameter("iprocedure");
		String node = request.getParameter("node");
		
		PageBean<LaborCost> page =programService.laborlist(code,Utils.getProject(request),program,procedure,node,startDate,endDate);
		return new Gson().toJson(page);
	}
	@RequestMapping(value = "/materialPrice/list")
	public @ResponseBody String getMaterialPriceList(HttpServletRequest request,int offset,int limit) {
		String code = (String) request.getSession().getAttribute("code");
		PageHelper.offsetPage(offset, limit);
		PageHelper.orderBy("id desc");
		String dataRange = request.getParameter("daterange");
		String startDate=null;
		String endDate=null;
		if (null!=dataRange&&!"".equals(dataRange)) {
			startDate=dataRange.split("to")[0].trim();
			endDate=dataRange.split("to")[1].trim();
		}
		String program = request.getParameter("program");
		String procedure = request.getParameter("iprocedure");
		String node = request.getParameter("node");
		PageBean<MaterialReceived> page =programService.materialRecevicelist(code,Utils.getProject(request),program,procedure,node,startDate,endDate);
		return new Gson().toJson(page);
	}
	@RequestMapping(value = "/mechanicsPrice/list")
	public @ResponseBody String getMechanicsPriceList(HttpServletRequest request,int offset,int limit) {
		String code = (String) request.getSession().getAttribute("code");
		PageHelper.offsetPage(offset, limit);
		PageHelper.orderBy("id desc");
		String dataRange = request.getParameter("daterange");
		String startDate=null;
		String endDate=null;
		if (null!=dataRange&&!"".equals(dataRange)) {
			startDate=dataRange.split("to")[0].trim();
			endDate=dataRange.split("to")[1].trim();
		}
		String program = request.getParameter("program");
		String procedure = request.getParameter("iprocedure");
		String node = request.getParameter("node");
		PageBean<MechanicsPrice> page =programService.mechanicsPriceList(code,Utils.getProject(request),program,procedure,node,startDate,endDate);
		return new Gson().toJson(page);
	}
	
	@RequestMapping(value = "/oil/list")
	public @ResponseBody String getOilList(HttpServletRequest request,int offset,int limit) {
		String code = (String) request.getSession().getAttribute("code");
		PageHelper.orderBy("id desc");
		PageHelper.offsetPage(offset, limit);
		String dataRange = request.getParameter("daterange");
		String startDate=null;
		String endDate=null;
		if (null!=dataRange&&!"".equals(dataRange)) {
			startDate=dataRange.split("to")[0].trim();
			endDate=dataRange.split("to")[1].trim();
		}
		return new Gson().toJson(programService.oilList(code,Utils.getProject(request),startDate,endDate));
	}
}
