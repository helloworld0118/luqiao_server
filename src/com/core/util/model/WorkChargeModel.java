package com.core.util.model;


public class WorkChargeModel {

	private int id;
	private int workerType;
	private String workerTypeName;
	
	private int baseType;
	private String baseTypeName;
	private int count;
	private int basePriceType;
	private String basePriceTypeName;
	private int price;
	
	
	
	

	public WorkChargeModel(int id, int count,int unit_type, int unit_price_type, int price) {
		super();
		this.id = id;
		this.count = count;
		this.baseType = unit_type;
		this.basePriceType = unit_price_type;
		this.price = price;
	}
	
	
	
	public WorkChargeModel(int id, int baseType, String baseTypeName, int count, int basePriceType,
			String basePriceTypeName, int price) {
		super();
		this.id = id;
		this.baseType = baseType;
		this.baseTypeName = baseTypeName;
		this.count = count;
		this.basePriceType = basePriceType;
		this.basePriceTypeName = basePriceTypeName;
		this.price = price;
	}
	
	public WorkChargeModel(int id, int workType,String workTypeName,int baseType, String baseTypeName, int count, int basePriceType,
			String basePriceTypeName, int price) {
		super();
		this.id = id;
		this.workerType = workType;
		this.workerTypeName = workTypeName;
		this.baseType = baseType;
		this.baseTypeName = baseTypeName;
		this.count = count;
		this.basePriceType = basePriceType;
		this.basePriceTypeName = basePriceTypeName;
		this.price = price;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public int getBaseType() {
		return baseType;
	}

	public void setBaseType(int baseType) {
		this.baseType = baseType;
	}

	public int getBasePriceType() {
		return basePriceType;
	}

	public void setBasePriceType(int basePriceType) {
		this.basePriceType = basePriceType;
	}
	
	
	public String getBaseTypeName() {
		return baseTypeName;
	}

	public void setBaseTypeName(String baseTypeName) {
		this.baseTypeName = baseTypeName;
	}

	public String getBasePriceTypeName() {
		return basePriceTypeName;
	}

	public void setBasePriceTypeName(String basePriceTypeName) {
		this.basePriceTypeName = basePriceTypeName;
	}



	public int getWorkerType() {
		return workerType;
	}



	public void setWorkerType(int workerType) {
		this.workerType = workerType;
	}



	public String getWorkerTypeName() {
		return workerTypeName;
	}



	public void setWorkerTypeName(String workerTypeName) {
		this.workerTypeName = workerTypeName;
	}

	

}
