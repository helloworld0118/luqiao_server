package com.core.util;

import java.util.Comparator;

import com.core.util.model.chart.BarDataModel;

public class BarDataModelComparator implements Comparator {

	public int compare(Object obj0, Object obj1) {
		BarDataModel model1 = (BarDataModel) obj0;
		BarDataModel model2 = (BarDataModel) obj1;
		return model1.getName().compareTo(model2.getName());
	}

}