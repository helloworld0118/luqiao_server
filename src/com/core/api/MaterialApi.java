package com.core.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.service.SupplierService;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/api")
public class MaterialApi {
	@Autowired
	private SupplierService supplierService;

	@RequestMapping(value = "/supplier/list", method = RequestMethod.GET)
	public @ResponseBody String getSupplierList(HttpServletRequest request, String code, int project) {
		return new Gson().toJson(supplierService.getSupplierList(code, project));
	}
	
	@RequestMapping(value = "/oil/supplier", method = RequestMethod.GET)
	public @ResponseBody String getOilSupplierList(HttpServletRequest request, String code, int project) {
		return new Gson().toJson(supplierService.getOilSupplierList(code, project));
	}
	
	@RequestMapping(value = "/{id}/oils", method = RequestMethod.GET)
	public @ResponseBody String getSupplierOil(HttpServletRequest request, String code, int project,
			@PathVariable("id") int id) {
		return new Gson().toJson(supplierService.getOil4Api(code, project, id));
	}

	@RequestMapping(value = "/{id}/materials", method = RequestMethod.GET)
	public @ResponseBody String getSupplierDetail(HttpServletRequest request, String code, int project,
			@PathVariable("id") int id) {
		return new Gson().toJson(supplierService.getMaterial4Api(code, project, id));
	}
	
	
}
