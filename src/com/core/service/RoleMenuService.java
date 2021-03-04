package com.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.entity.Menu;
import com.core.entity.MenuExample;
import com.core.entity.MenuExample.Criteria;
import com.core.entity.ProjectStaffRole;
import com.core.entity.ProjectStaffRoleExample;
import com.core.entity.Role;
import com.core.entity.RoleExample;
import com.core.entity.RoleMenu;
import com.core.entity.RoleMenuExample;
import com.core.entity.Staff;
import com.core.mapper.MenuMapper;
import com.core.mapper.ProjectStaffRoleMapper;
import com.core.mapper.RoleMapper;
import com.core.mapper.RoleMenuMapper;
import com.core.util.Utils;
import com.core.util.model.MenuModel;

@Service
public class RoleMenuService {
	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	private ProjectStaffRoleMapper staffRoleMapper;
	
	public List<MenuModel> getMenusPc(String database, int role) {
		Utils.setDatabase(database);
		RoleMenuExample roleMenuexample = new RoleMenuExample();
		roleMenuexample.createCriteria().andRoleEqualTo(role);
		roleMenuexample.setOrderByClause("order_num ASC");
		List<RoleMenu> roleMenulist = roleMenuMapper.selectByExample(roleMenuexample);
		MenuExample menuExample = new MenuExample();
		Criteria criteria = menuExample.createCriteria();
		List<Integer> ids = new ArrayList<Integer>();
		StringBuffer sb=new StringBuffer();
		for (RoleMenu roleMenu : roleMenulist) {
			ids.add(roleMenu.getMenu());
			sb.append(roleMenu.getMenu()+",");
		}
		sb.deleteCharAt(sb.length()-1);
		criteria.andIdIn(ids);
		criteria.andTypeEqualTo(0);
		menuExample.setDistinct(true);
		menuExample.setOrderByClause("ORDER BY FIELD(id,"+sb.toString()+")");
		return getMensList(menuMapper.selectByExample(menuExample));
	}
	private List<MenuModel> getMensList(List<Menu> list){
		List<MenuModel> mens=new ArrayList<MenuModel>();
		for (Menu menu : list) {
			if(menu.getParent()==0) {
				mens.add(new MenuModel(menu.getId(),menu.getName(),menu.getUrl(),menu.getIcon()));
			}
		}
		for (Menu menu : list) {
			if(menu.getParent()!=0) {
				for (MenuModel model : mens) {
					if(model.getId()==menu.getParent()) {
						model.getChilds().add(new MenuModel(menu.getId(), menu.getName(), menu.getUrl(),menu.getIcon()));
					}
				}
			}
		}
		return mens;
	}
	
	public List<MenuModel> getMenusPc(String database,int project, Staff staff) {
		Utils.setDatabase(database);
		List<Role> roles =getRoleList(database,project,staff.getId());
		RoleMenuExample roleMenuexample = new RoleMenuExample();
		List<Integer> rids =new ArrayList<Integer>();
		for (Role temp : roles) {
			rids.add(temp.getId());
		}
		roleMenuexample.createCriteria().andRoleIn(rids);
		roleMenuexample.setOrderByClause("order_num asc");
		List<RoleMenu> roleMenulist = roleMenuMapper.selectByExample(roleMenuexample);
		List<Integer> mids = new ArrayList<Integer>();
		StringBuffer sb=new StringBuffer();
		for (RoleMenu roleMenu : roleMenulist) {
			mids.add(roleMenu.getMenu());
			sb.append(roleMenu.getMenu()+",");
		}
		sb.deleteCharAt(sb.length()-1);
		MenuExample menuExample = new MenuExample();
		Criteria criteria = menuExample.createCriteria();
		criteria.andIdIn(mids);
		criteria.andTypeEqualTo(0);
		menuExample.setDistinct(true);
		menuExample.setOrderByClause("ORDER BY FIELD(id,"+sb.toString()+")");
		return getMensList(menuMapper.selectByExample(menuExample));
	}
	
	public List<RoleMenu> getlist(String database, int role) {
		Utils.setDatabase(database);
		RoleMenuExample example = new RoleMenuExample();
		example.createCriteria().andRoleEqualTo(role);
		return roleMenuMapper.selectByExample(example);
	}
	
	public List<Role> getRoleList(String database,int project, int staff) {
		Utils.setDatabase(database);
		ProjectStaffRoleExample example = new ProjectStaffRoleExample();
		example.createCriteria().andStaffEqualTo(staff).andProjectEqualTo(project);
		List<ProjectStaffRole> srlist =staffRoleMapper.selectByExample(example);
		RoleExample roleexample= new RoleExample();
		List<Integer> ids = new ArrayList<Integer>();
		for (ProjectStaffRole staffRole : srlist) {
			ids.add(staffRole.getRole());
		}
		roleexample.createCriteria().andIdIn(ids);
		return roleMapper.selectByExample(roleexample);
	}
	
	public List<Role> getRoleList(String database) {
		Utils.setDatabase(database);
		RoleExample example = new RoleExample();
		example.createCriteria().andIdNotEqualTo(1);
		return roleMapper.selectByExample(example);
	}
	
}