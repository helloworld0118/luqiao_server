package com.core.util.model.chart;

public class Tooltip {
	private String trigger="item";
	private String formatter="{a} <br/>{b} : {c}";
	public String getTrigger() {
		return trigger;
	}
	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}
	public String getFormatter() {
		return formatter;
	}
	public void setFormatter(String formatter) {
		this.formatter += formatter;
	}
	
}
