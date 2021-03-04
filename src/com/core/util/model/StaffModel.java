package com.core.util.model;

import com.core.entity.Staff;

public class StaffModel extends Staff {
	private String roles;
	private int unit_price;
	private String unit_price_type;
	private String roleIds;
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public int getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(int unit_price) {
		this.unit_price = unit_price;
	}

	public String getUnit_price_type() {
		return unit_price_type;
	}

	public void setUnit_price_type(String unit_price_type) {
		this.unit_price_type = unit_price_type;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

}
