package com.core.util;

import java.util.Comparator;

import com.core.util.model.chart.BarDataModel;

public class BarNameModelComparator implements Comparator {

	public int compare(Object obj0, Object obj1) {
		String model1 = (String) obj0;
		String  model2 =(String) obj1;
		return model1.compareTo(model2);
	}

}