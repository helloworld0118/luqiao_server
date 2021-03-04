package com.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.entity.DayBook;
import com.core.entity.LaborCost;
import com.core.entity.LoanInfo;
import com.core.entity.MaterialReceived;
import com.core.entity.MechanicsPrice;
import com.core.entity.Oil;
import com.core.entity.Role;
import com.core.service.FinanceService;
import com.core.service.ProgramService;
import com.core.service.RoleMenuService;
import com.core.util.PageBean;
import com.core.util.Utils;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;

@Controller
@Scope("prototype")


public class FinanceController {

	@Autowired
	private FinanceService financeService;
	
	@Autowired
	private RoleMenuService roleMenuService;
	
	
	@RequestMapping(value = "/loanInfo/list")
	public @ResponseBody String loanInfoList(HttpServletRequest request,int offset, int limit) {
		String code = (String) request.getSession().getAttribute("code");
		PageHelper.startPage(offset, limit);
		PageHelper.orderBy("id desc");
		String dataRange = request.getParameter("daterange");
		String person = request.getParameter("person");
		String startDate=null;
		String endDate=null;
		if (null!=dataRange&&!"".equals(dataRange)) {
			startDate=dataRange.split("to")[0].trim();
			endDate=dataRange.split("to")[1].trim();
		}
		return new Gson().toJson(financeService.loanList(code, Utils.getProject(request),startDate,endDate,person));
	}
	@RequestMapping(value = "/loanInfo/hasBtn")
	public @ResponseBody String loanInfoHasBtn(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object isSuperAdmin = session.getAttribute("isSuperAdmin");
		String code = (String) session.getAttribute("code");
		if(null != isSuperAdmin && isSuperAdmin.equals("true")) {
			return Utils.ajaxReturn(true);
		}
		int userId = Utils.getCurrentUserId(request);
		List<Role> list =roleMenuService.getRoleList(code, Utils.getProject(request), userId);
		for (Role role : list) {
			if(role.getId()==6) {
				return Utils.ajaxReturn(true);
			}
		}
		return Utils.ajaxReturn(false);
	}
	
	@RequestMapping(value = "/daybook/hasBtn")
	public @ResponseBody String dayBookHasBtn(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object isSuperAdmin = session.getAttribute("isSuperAdmin");
		String code = (String) session.getAttribute("code");
		if(null != isSuperAdmin && isSuperAdmin.equals("true")) {
			return Utils.ajaxReturn(true);
		}
		int userId = Utils.getCurrentUserId(request);
		List<Role> list =roleMenuService.getRoleList(code, Utils.getProject(request), userId);
		for (Role role : list) {
			if(role.getId()==6) {
				return Utils.ajaxReturn(true);
			}
		}
		return Utils.ajaxReturn(false);
	}
	
	@RequestMapping(value = "/daybook/list")
	public @ResponseBody String dayBookList(HttpServletRequest request,int offset, int limit) {
		String code = (String) request.getSession().getAttribute("code");
		PageHelper.startPage(offset, limit);
		PageHelper.orderBy("id desc");
		String dataRange = request.getParameter("daterange");
		String account = request.getParameter("account");
		String startDate=null;
		String endDate=null;
		if (null!=dataRange&&!"".equals(dataRange)) {
			startDate=dataRange.split("to")[0].trim();
			endDate=dataRange.split("to")[1].trim();
		}
		return new Gson().toJson(financeService.dayBookList(code, Utils.getProject(request),startDate,endDate,account));
	}
	
	@RequestMapping(value = "/expense/list")
	public @ResponseBody String expenseList(HttpServletRequest request,int offset, int limit) {
		String code = (String) request.getSession().getAttribute("code");
		PageHelper.startPage(offset, limit);
		PageHelper.orderBy("id desc");
		String dataRange = request.getParameter("daterange");
		String person = request.getParameter("person");
		String startDate=null;
		String endDate=null;
		if (null!=dataRange&&!"".equals(dataRange)) {
			startDate=dataRange.split("to")[0].trim();
			endDate=dataRange.split("to")[1].trim();
		}
		return new Gson().toJson(financeService.expenseList(code, Utils.getProject(request),startDate,endDate,person));
	}
	@RequestMapping(value = "/loanInfo/add")
	public @ResponseBody String addLoanInfo(HttpServletRequest request,LoanInfo loanInfo) {
		String code = (String) request.getSession().getAttribute("code");
		loanInfo.setCreateDate(Utils.getCurrentTime());
		loanInfo.setPresenter(Utils.getCurrentUserName(request));
		loanInfo.setProject(Utils.getProject(request));
		boolean success = financeService.addLoanInfo(code, loanInfo);
		return Utils.ajaxReturn(success);
	}
	
	@RequestMapping(value = "/daybook/add")
	public @ResponseBody String addDayBook(HttpServletRequest request,DayBook dayBook) {
		String code = (String) request.getSession().getAttribute("code");
		dayBook.setVoucherno(Utils.getVoucherNo());
		dayBook.setCreateDate(Utils.getCurrentTime());
		dayBook.setDate(Utils.getCurrentDate());
		dayBook.setProject(Utils.getProject(request));
		dayBook.setPresenter(Utils.getCurrentUserName(request));
		boolean success = financeService.addDayBook(code, dayBook);
		return Utils.ajaxReturn(success);
	}
	@RequestMapping(value = "/department/list")
	public @ResponseBody String departmentList(HttpServletRequest request) {
		String code = (String) request.getSession().getAttribute("code");
		return new Gson().toJson(financeService.getDepartList(code));
	}
	@RequestMapping(value = "/billType/list")
	public @ResponseBody String getBasePriceTypeList(HttpServletRequest request) {
		String code = (String) request.getSession().getAttribute("code");
		return new Gson().toJson(financeService.getbasePriceTypeList(code));
	}
	
	
}
