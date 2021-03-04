package com.core.util.model.api;

import java.util.ArrayList;
import java.util.List;

import com.core.entity.Project;
import com.core.entity.Role;

public class ProjectRoleModel {
	private Project project;
	private List<Role> roles = new ArrayList<>();

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
