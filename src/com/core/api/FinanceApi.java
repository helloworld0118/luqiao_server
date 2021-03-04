package com.core.api;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.entity.DayBook;
import com.core.entity.Expense;
import com.core.entity.LoanInfo;
import com.core.service.FinanceService;
import com.core.service.RoleMenuService;
import com.core.util.Utils;
import com.google.gson.Gson;

@Controller
@Scope("prototype")

@RequestMapping(value = "/api")
public class FinanceApi {

	@Autowired
	private FinanceService financeService;
	
	@Autowired
	private RoleMenuService roleMenuService;
	
	
	@RequestMapping(value = "/department/list")
	public @ResponseBody String departmentList(HttpServletRequest request,String code) {
		return new Gson().toJson(financeService.getDepartList(code));
	}
	
	
	@RequestMapping(value = "/expense/add", method=RequestMethod.POST)
	public @ResponseBody String addExpense(HttpServletRequest request,String code,Expense expense) {
		boolean success = financeService.addExpense(code, expense);
		return Utils.ajaxReturn(success);
	}
	
	
	@RequestMapping(value = "/expense/list")
	public @ResponseBody String expenseList(HttpServletRequest request,int project,String code,int staff) {
		String date =  request.getParameter("date");
		return new Gson().toJson(financeService.expenseList(code, project,date,staff));
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
	
	@RequestMapping(value = "/billType/list")
	public @ResponseBody String getBasePriceTypeList(HttpServletRequest request) {
		String code = (String) request.getSession().getAttribute("code");
		return new Gson().toJson(financeService.getbasePriceTypeList(code));
	}
	
	
}
