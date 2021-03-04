package com.core.util.model;

public class LoanInfoModel {

	private String person;
	private int price;
	private String date;
	private int department;
	private String departmentName;
	private String staffName;
	private String reason;

	public LoanInfoModel(String person, int price, String date, int department,String departmentName, String staffName,
			String reason) {
		super();
		this.person = person;
		this.price = price;
		this.date = date;
		this.department = department;
		this.departmentName = departmentName;
		this.staffName = staffName;
		this.reason = reason;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
