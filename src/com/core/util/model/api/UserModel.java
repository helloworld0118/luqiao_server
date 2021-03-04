package com.core.util.model.api;

import java.util.ArrayList;
import java.util.List;

import com.core.entity.Staff;

public class UserModel {
	private Staff staff;
	private String token;
	private List<ProjectRoleModel> projectRoles = new ArrayList<>();

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public List<ProjectRoleModel> getProjectRoles() {
		return projectRoles;
	}

	public void setProjectRoles(List<ProjectRoleModel> projectRoles) {
		this.projectRoles = projectRoles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
