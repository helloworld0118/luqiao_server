package com.core.util.model.chart;

import java.util.ArrayList;
import java.util.List;

public class PieModel {
	private String title;
	private String subTitle;
	private List<PieDataModel> data=new ArrayList<PieDataModel>();
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public List<PieDataModel> getData() {
		return data;
	}
	public void setData(List<PieDataModel> data) {
		this.data = data;
	}
	
	
}
