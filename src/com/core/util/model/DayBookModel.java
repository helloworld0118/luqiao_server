package com.core.util.model;

public class DayBookModel {

	private String voucherno;
	private int income;
	private int incomeType;
	private String incomeTypeName;
	private int pay;
	private String payFor;
	private String date;
	private String staffName;
	private String summary;
	private int department;
	private String departmentName;
	private String account;
	private String billTypeName;
	
	public DayBookModel(String voucherno, int income, int incomeType, String incomeTypeName, int pay, String payFor,
			String date, String staffName, String summary,String account,int department,String departmentName,String billTypeName) {
		super();
		this.voucherno = voucherno;
		this.income = income;
		this.incomeType = incomeType;
		this.incomeTypeName = incomeTypeName;
		this.pay = pay;
		this.payFor = payFor;
		this.date = date;
		this.staffName = staffName;
		this.summary = summary;
		this.account = account;
		this.department = department;
		this.departmentName = departmentName;
		this.billTypeName = billTypeName;
	}
	public String getVoucherno() {
		return voucherno;
	}
	public void setVoucherno(String voucherno) {
		this.voucherno = voucherno;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public int getIncomeType() {
		return incomeType;
	}
	public void setIncomeType(int incomeType) {
		this.incomeType = incomeType;
	}
	public String getIncomeTypeName() {
		return incomeTypeName;
	}
	public void setIncomeTypeName(String incomeTypeName) {
		this.incomeTypeName = incomeTypeName;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	public String getPayFor() {
		return payFor;
	}
	public void setPayFor(String payFor) {
		this.payFor = payFor;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getDepartment() {
		return department;
	}
	public void setDepartment(int department) {
		this.department = department;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getBillTypeName() {
		return billTypeName;
	}
	public void setBillTypeName(String billTypeName) {
		this.billTypeName = billTypeName;
	}
	
	
}
