package com.core.util.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private int id;
	private String name;
	private String menu_id;
	private String url;
	private String icon;
	private List<MenuModel> childs = new ArrayList<MenuModel>();

	public MenuModel(int id, String name, String url, String icon) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.icon = icon;
		if (null != url && !"".equals(url)) {
			this.menu_id = url.replace(".html", "");
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<MenuModel> getChilds() {
		return childs;
	}

	public void setChilds(List<MenuModel> childs) {
		this.childs = childs;
	}

	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

}
