package com.core.util.model;

import com.core.entity.Project;

public class ProjectModel {
	private Integer id;
	private String name;
	private String code;
	private int module;
	private String moduleName;
	private String startDate;
	private String address;
	private String endDate;
	private String createDate;
	private Integer startNode;
	private Integer endNode;
	private String description;
	private String remark;

	
	
	public ProjectModel(Integer id, String name, String code, String module, String startDate,
			String address, String endDate, String createDate, Integer startNode, Integer endNode, String description,
			String remark,int moduleId) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.startDate = startDate;
		this.address = address;
		this.moduleName = module;
		this.endDate = endDate;
		this.createDate = createDate;
		this.startNode = startNode;
		this.endNode = endNode;
		this.description = description;
		this.remark = remark;
		this.module = moduleId;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}






	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	public String getCreateDate() {
		return createDate;
	}



	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}



	public Integer getStartNode() {
		return startNode;
	}



	public void setStartNode(Integer startNode) {
		this.startNode = startNode;
	}



	public Integer getEndNode() {
		return endNode;
	}



	public void setEndNode(Integer endNode) {
		this.endNode = endNode;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public int getModule() {
		return module;
	}



	public void setModule(int module) {
		this.module = module;
	}
	public String getModuleName() {
		return moduleName;
	}



	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
   

}
