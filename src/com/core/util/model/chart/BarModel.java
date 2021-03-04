package com.core.util.model.chart;

import java.util.ArrayList;
import java.util.List;

public class BarModel {
	private List<String> nameData=new ArrayList<String>();
	private List<BarDataModel> todayData=new ArrayList<BarDataModel>();
	private List<BarDataModel> alldayData=new ArrayList<BarDataModel>();
	
	
	
	public List<String> getNameData() {
		return nameData;
	}
	public void setNameData(List<String> nameData) {
		this.nameData = nameData;
	}
	public List<BarDataModel> getTodayData() {
		return todayData;
	}
	public void setTodayData(List<BarDataModel> todayData) {
		this.todayData = todayData;
	}
	public List<BarDataModel> getAlldayData() {
		return alldayData;
	}
	public void setAlldayData(List<BarDataModel> alldayData) {
		this.alldayData = alldayData;
	}
	
	
}
