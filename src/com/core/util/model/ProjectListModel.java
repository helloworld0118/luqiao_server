package com.core.util.model;

import java.util.ArrayList;
import java.util.List;

import com.core.entity.Project;

public class ProjectListModel {
	private List<Project> projects=new ArrayList<Project>(4);

	public ProjectListModel(List<Project> projects) {
		this.projects = projects;
	}
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	
}
