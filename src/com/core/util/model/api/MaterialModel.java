package com.core.util.model.api;

import java.util.ArrayList;
import java.util.List;

public class MaterialModel {
	private int id;
	private int supplier;
	private String materialName;
	
	private List<MaterialPriceModel> list = new ArrayList<>();
	
	
	
	public MaterialModel(int id, String materialName,int supplier) {
		super();
		this.id = id;
		this.materialName = materialName;
		this.supplier = supplier;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSupplier() {
		return supplier;
	}
	public void setSupplier(int supplier) {
		this.supplier = supplier;
	}
	public List<MaterialPriceModel> getList() {
		return list;
	}
	public void setList(List<MaterialPriceModel> list) {
		this.list = list;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
	
	
}

