package com.core.util.model;


public class ShowWorkChargeModel {
	
	private String workerType;
	private String baseType;
	
	private int count;
	private String basePriceType;
	private int price;
	
	
	
	public ShowWorkChargeModel(String workerType,String baseType, int count, String basePriceType, int price) {
		super();
		this.workerType = workerType;
		this.baseType = baseType;
		this.count = count;
		this.basePriceType = basePriceType;
		this.price = price;
	}
	
	
	
	public String getWorkerType() {
		return workerType;
	}



	public void setWorkerType(String workerType) {
		this.workerType = workerType;
	}



	public String getBaseType() {
		return baseType;
	}
	public void setBaseType(String baseType) {
		this.baseType = baseType;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getBasePriceType() {
		return basePriceType;
	}
	public void setBasePriceType(String basePriceType) {
		this.basePriceType = basePriceType;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
