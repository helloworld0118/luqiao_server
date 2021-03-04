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
import com.core.entity.Supplier;
import com.core.service.SupplierService;
import com.core.util.PageBean;
import com.core.util.PageBeanModel;
import com.core.util.Utils;
import com.core.util.model.MaterialModel;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;

@Controller
@Scope("prototype")
public class SupplierController {
	
	
	@Autowired
	private SupplierService supplierService;
	
	@RequestMapping(value = {"/material/list","materials"})
	public @ResponseBody String materialList(HttpServletRequest request,int offset,int limit) {
		String code = (String) request.getSession().getAttribute("code");
		PageHelper.startPage(offset,limit);
		PageBeanModel<MaterialModel> page =supplierService.getMaterialList(code, Utils.getProject(request));
		return new Gson().toJson(page);
	}
	
	
	@RequestMapping(value = "/supplier/edit")
	public @ResponseBody String editSupplierDetail(HttpServletRequest request,Supplier supplier,
			int sid,int mid,String name,int materialType,int baseType,int price,int materialSpec,String mAddress){
		String code = (String) request.getSession().getAttribute("code");
		supplier.setId(sid);;
		boolean success = supplierService.updateSupplier(code, supplier);
		int project =Utils.getProject(request);
		String startDate=Utils.getCurrentDate();
		int result = supplierService.updateMaterial(code, project, supplier.getId(), mid, materialType, price, startDate, baseType, materialSpec,mAddress);
		if(-1==result) {
			return Utils.ajaxReturn4ajax(false,"已经有相应记录，不能重复添加");
		}else if(1==result||success) {
			return Utils.ajaxReturn(true);
		}
		return Utils.ajaxReturn4ajax(false,"编辑出错");
	}
	
	
	@RequestMapping(value = "/supplier/add")
	public @ResponseBody String addSupplierDetail(HttpServletRequest request,int temp_id,Supplier supplier,
			String mAddress) {
		Object type=request.getParameter("type");
		String code = (String) request.getSession().getAttribute("code");
		 
		boolean success = supplierService.addSupplier(code, supplier,Utils.getProject(request));
		if(null!=type) {
			success = supplierService.updateMaterial(code,temp_id,mAddress,supplier.getId());
		}else {
			int price = Integer.parseInt(request.getParameter("price"));
			String startDate = request.getParameter("startDate");
			int baseType = Integer.parseInt(request.getParameter("baseType"));
			int materialType = Integer.parseInt(request.getParameter("materialType"));
			supplierService.addMaterial(code, Utils.getProject(request), supplier.getId(),materialType, 0, price, startDate, baseType, 0,mAddress);
		}
		return Utils.ajaxReturn(success);
	}
	
	@RequestMapping(value = "/materialtype", method=RequestMethod.GET)
	public @ResponseBody String getMaterialTypeList(HttpServletRequest request) {
		String code = (String) request.getSession().getAttribute("code");
		return new Gson().toJson(supplierService.getMaterialTypeList(code));
	}
	@RequestMapping(value = "/materialSpec", method=RequestMethod.GET)
	public @ResponseBody String getMaterialSpecList(HttpServletRequest request,int material) {
		String code = (String) request.getSession().getAttribute("code");
		return new Gson().toJson(supplierService.getMaterialSpecList(code, material));
	}
	
	
	@RequestMapping(value = "{supplier}/editMeterialSpec/{mid}", method=RequestMethod.POST)
	public @ResponseBody String editMeterialSpec(HttpServletRequest request,
			@PathVariable("supplier") int supplier,@PathVariable("mid") int mid,
			int type,int materialType,int baseType,
			int materialSpec,int price,String mAddress) {
		String code = (String) request.getSession().getAttribute("code");
		//return new Gson().toJson(suppplierService.getMaterialSpecList(code, material));
		int project = Utils.getProject(request);
		String startDate =Utils.getCurrentDate();
		int result = supplierService.updateMaterial(code, project, supplier,mid, materialType,price, startDate, baseType, materialSpec,mAddress);
		if(-1==result) {
			return Utils.ajaxReturn4ajax(false,"已经有相应记录，不能重复添加");
		}else if(1==result) {
			return Utils.ajaxReturn(true);
		}
		return Utils.ajaxReturn4ajax(false,"编辑出错");
	}
	
	@RequestMapping(value = {"{supplier}/deleteMeterialSpec/{mid}","material/delete/{mid}"}, method=RequestMethod.POST)
	public @ResponseBody String deleteMeterialSpec(HttpServletRequest request,@PathVariable("mid") int mid,@PathVariable("supplier") int supplier) {
		String code = (String) request.getSession().getAttribute("code");
		boolean success = supplierService.deleteMaterial(code,Utils.getProject(request),supplier,mid);
		return Utils.ajaxReturn(success);
	}
	
	@RequestMapping(value = "{supplier}/addMeterialSpec", method=RequestMethod.POST)
	public @ResponseBody String addMeterialSpec(HttpServletRequest request,
			@PathVariable("supplier") int supplier,
			int type,int materialType,int baseType,
			int materialSpec,int price,String mAddress) {
		String code = (String) request.getSession().getAttribute("code");
		int project = Utils.getProject(request);
		
		String startDate =Utils.getCurrentDate();
		int mid = supplierService.addMaterial(code, project, supplier,materialType,type, price, startDate, baseType, materialSpec,mAddress);
		if(-1==mid) {
			return Utils.ajaxReturn4ajax(false,"已经有相应记录，不能重复添加");
		}else if(0!=mid) {
			return Utils.ajaxReturn(true,mid+"");
		} 
		return Utils.ajaxReturn4ajax(false,"添加出错");
	}
	
	
	
	@RequestMapping(value ="supplier/search")
	public @ResponseBody String searchSupplier(HttpServletRequest request,String param) {
		String code = (String)request.getSession().getAttribute("code");
		PageHelper.startPage(0, 10);
		return new Gson().toJson(supplierService.getSupplierList(code, param));
	}
	
	
	@RequestMapping(value = "/supplier/addRelationship/{supplierId}")
	public @ResponseBody String addRelationship(HttpServletRequest request,int temp_id,Supplier supplier,
			String mAddress,@PathVariable("supplierId") int supplierId) {
		Object type=request.getParameter("type");
		String code = (String) request.getSession().getAttribute("code");
		supplier.setId(supplierId);
		boolean success = supplierService.updateSupplier(code, supplier);
		if(null!=type) {
			success = supplierService.updateMaterial(code,temp_id,mAddress,supplierId);
		}else {
			int price = Integer.parseInt(request.getParameter("price"));
			String startDate = request.getParameter("startDate");
			int baseType = Integer.parseInt(request.getParameter("baseType"));
			int materialType = Integer.parseInt(request.getParameter("materialType"));
			supplierService.addMaterial(code, Utils.getProject(request), supplierId,materialType, 0, price, startDate, baseType, 0,mAddress);
		}
		return Utils.ajaxReturn(success);
	}
}
