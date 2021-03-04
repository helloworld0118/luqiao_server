package com.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.entity.SkipMaterialPrice;
import com.core.service.SupplierService;
import com.core.util.Utils;
import com.google.gson.Gson;

@Controller
@Scope("prototype")
public class SkipPriceController {
	@Autowired
	private SupplierService supplierService;
	
	@RequestMapping(value = "{skip}/addSkipMaterialPrice")
	public @ResponseBody String addSkipMaterialPrice(HttpServletRequest request,@PathVariable("skip") int skipId,
			int materialType, int basePriceType, int unitPrice) {
		String code = (String) request.getSession().getAttribute("code");
		SkipMaterialPrice smpinfo = new SkipMaterialPrice();
		smpinfo.setMechainc(skipId);
		smpinfo.setMaterialType(materialType);
		smpinfo.setBasePriceType(basePriceType);
		smpinfo.setProject(Utils.getProject(request));
		smpinfo.setCreateDate(Utils.getCurrentTime());
		int result=supplierService.addSkipPriceInfo(code,smpinfo,unitPrice);
		
		if(-1==result) {
			return Utils.ajaxReturn4ajax(false,"已经有相应记录，不能重复添加");
		}else if(1==result) {
			return Utils.ajaxReturn(true,smpinfo.getId()+"");
		}
		return Utils.ajaxReturn4ajax(false,"添加出错");
	}
	
	@RequestMapping(value = "{skip}/editSkipMaterialPrice/{smpid}")
	public @ResponseBody String editSkipMaterialPrice(HttpServletRequest request,@PathVariable("skip") int skipId,
			@PathVariable("smpid") int smpid,
			int materialType, int basePriceType, int unitPrice) {
		String code = (String) request.getSession().getAttribute("code");
		SkipMaterialPrice smpinfo = new SkipMaterialPrice();
		smpinfo.setId(smpid);
		smpinfo.setMechainc(skipId);
		smpinfo.setMaterialType(materialType);
		smpinfo.setBasePriceType(basePriceType);
		smpinfo.setProject(Utils.getProject(request));
		int result=supplierService.updateSkipPriceInfo(code,smpinfo,unitPrice);
		if(-1==result) {
			return Utils.ajaxReturn4ajax(false,"已经有相应记录，不能重复添加");
		}else if(1==result) {
			return Utils.ajaxReturn(true,smpinfo.getId()+"");
		}
		return Utils.ajaxReturn4ajax(false,"编辑出错");
	}
	
	@RequestMapping(value = "{skip}/deleteSkipMaterialPrice/{smpid}")
	public @ResponseBody String deleteSkipMaterialPrice(HttpServletRequest request,@PathVariable("smpid") int smpid) {
		String code = (String) request.getSession().getAttribute("code");
		boolean success=supplierService.deleteSkipPriceInfo(code,smpid);
		return Utils.ajaxReturn(success);
	}
	@RequestMapping(value = "{skip}/materialPrices")
	public @ResponseBody String materialPrices(HttpServletRequest request,@PathVariable("skip") int skip) {
		String code = (String) request.getSession().getAttribute("code");
		return new Gson().toJson(supplierService.getSkipPriceList(code,Utils.getProject(request),skip));
	}
	
}
