package com.core.util.model;

import java.util.List;

public class IndexPriceModel<T> {
	private int price;
	private int nums;
	private String priceChange;
	private String numsChange;
	private String priceColor;
	private String priceIcon;
	private String numsColor;
	private String numsIcon;
	private List<T> list;
	
	
	public IndexPriceModel(int price, int nums) {
		super();
		this.price = price;
		this.nums = nums;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public String getPriceChange() {
		return priceChange;
	}
	public void setPriceChange(String priceChange) {
			this.priceChange = priceChange;
	}
	public String getNumsChange() {
		return numsChange;
	}
	public void setNumsChange(String numsChange) {
			this.numsChange = numsChange;
	}
	public String getPriceColor() {
		return priceColor;
	}
	public void setPriceColor(String priceColor) {
		this.priceColor = priceColor;
	}
	public String getPriceIcon() {
		return priceIcon;
	}
	public void setPriceIcon(String priceIcon) {
		this.priceIcon = priceIcon;
	}
	public String getNumsColor() {
		return numsColor;
	}
	public void setNumsColor(String numsColor) {
		this.numsColor = numsColor;
	}
	public String getNumsIcon() {
		return numsIcon;
	}
	public void setNumsIcon(String numsIcon) {
		this.numsIcon = numsIcon;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
	

	
}
