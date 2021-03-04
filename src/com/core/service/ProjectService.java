package com.core.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.entity.Project;
import com.core.entity.ProjectExample;
import com.core.entity.ProjectStaffRole;
import com.core.entity.ProjectStaffRoleExample;
import com.core.mapper.ProjectMapper;
import com.core.mapper.ProjectStaffRoleMapper;
import com.core.util.Utils;


@Service
public class ProjectService {
	@Autowired
	private ProjectMapper projectMapper;
	
	@Autowired
	private ProjectStaffRoleMapper staffRoleMapper;
	
	public boolean updateProject(String database, Project project) {
		Utils.setDatabase(database);
		ProjectExample example = new ProjectExample();
		example.createCriteria().andIdEqualTo(project.getId());
		int result =projectMapper.updateByExample(project, example);
		return result==1? true:false;
	}
	
	public boolean hasProject(String database,int id,String name) {
		Utils.setDatabase(database);
		ProjectExample example = new ProjectExample();
		example.createCriteria().andNameEqualTo(name);
		List<Project> list= projectMapper.selectByExample(example);
		if(list.size()>0) {
			for (Project project : list) {
				if(project.getId()==id) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public Project getProject(String database, int id) {
		return projectMapper.selectByPrimaryKey(database,id);
	}
	public List<Project> getProjectList(String database, int staff) {
		Utils.setDatabase(database);
		ProjectExample example = new ProjectExample();
		if(staff==0) {
			return projectMapper.selectByExample(example);
			//example.createCriteria().andManagerEqualTo(manager);
		}
		ProjectStaffRoleExample psrExample = new ProjectStaffRoleExample();
		psrExample.createCriteria().andStaffEqualTo(staff);
		List<ProjectStaffRole> psrList = staffRoleMapper.selectByExample(psrExample);
		List<Integer> pids = new ArrayList<Integer>();
		for (ProjectStaffRole projectStaffRole : psrList) {
			pids.add(projectStaffRole.getProject());
		}
		
		example.createCriteria().andIdIn(pids);
		example.setDistinct(true);
		return projectMapper.selectByExample(example);
	}
	
	
	public boolean deleteProject(String database, int id) {
		int result =projectMapper.deleteByPrimaryKey(database,id);
		return result==1? true:false;
	}
	
    public boolean addProject(String database, Project project) {
    	Utils.setDatabase(database);
    	int result =projectMapper.insertSelective(project);
    	return result==1? true:false;
    }
}