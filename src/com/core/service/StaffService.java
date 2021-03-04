package com.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.entity.Foreman;
import com.core.entity.ForemanExample;
import com.core.entity.PriceFluctuation;
import com.core.entity.Project;
import com.core.entity.ProjectExample;
import com.core.entity.ProjectStaffPrice;
import com.core.entity.ProjectStaffPriceExample;
import com.core.entity.ProjectStaffRole;
import com.core.entity.ProjectStaffRoleExample;
import com.core.entity.Role;
import com.core.entity.RoleExample;
import com.core.entity.Staff;
import com.core.entity.StaffExample;
import com.core.entity.UnitTypeCombination;
import com.core.entity.WorkChargeInfo;
import com.core.entity.WorkChargeInfoExample;
import com.core.entity.main.Enterprise;
import com.core.mapper.ForemanMapper;
import com.core.mapper.PriceFluctuationMapper;
import com.core.mapper.ProjectStaffPriceMapper;
import com.core.mapper.ProjectStaffRoleMapper;
import com.core.mapper.RoleMapper;
import com.core.mapper.StaffMapper;
import com.core.mapper.UnitTypeCombinationMapper;
import com.core.mapper.WorkChargeInfoMapper;
import com.core.mapper.main.EnterpriseMapper;
import com.core.util.BeanUtil;
import com.core.util.MD5Util;
import com.core.util.PageBean;
import com.core.util.PageBeanModel;
import com.core.util.Utils;
import com.core.util.model.ProjectModel;
import com.core.util.model.StaffModel;
import com.core.util.model.WorkChargeModel;
import com.github.pagehelper.PageHelper;

@Service
public class StaffService extends UnitService {
	@Autowired
	private StaffMapper staffMapper;

	@Autowired
	private ProjectStaffRoleMapper staffRoleMapper;
	
	@Autowired
	private ProjectStaffPriceMapper staffPriceMapper;
	
	@Autowired
	private EnterpriseMapper enterpriseMapper;

	@Autowired
	private RoleMapper roleMapper;
	
	public Staff getStaffById(String database,int id) {
		return staffMapper.selectByPrimaryKey(database,id);
	}
//	public StaffModel getStaffById(String database,int id,int project) {
//		Staff staff = staffMapper.selectByPrimaryKey(database,id);
//		ProjectStaffPrice temp = getUnitPrice(database,project,staff.getId());
//		return new StaffModel(staff, 
//				getProjectStaffRole(database, project, staff.getId()),
//				getPriceFluctuation(database, temp.getUnitPrice()), 
//				getUnitTypeCombination(database, temp.getUnitPriceType()));
//	}
	
	public List<Role> getProjectStaffRole(String database,int project,int staff) {
		Utils.setDatabase(database);
		ProjectStaffRoleExample psrExample = new ProjectStaffRoleExample();
		psrExample.createCriteria().andProjectEqualTo(project).andStaffEqualTo(staff);
		List<ProjectStaffRole> psrList = staffRoleMapper.selectByExample(psrExample);
		List<Integer> rids =new ArrayList<Integer>();
		for (ProjectStaffRole psr : psrList) {
			rids.add(psr.getRole());
		}
		if(rids.size()==0) {
			return new ArrayList<Role>();
		}
		RoleExample rExample = new RoleExample();
		rExample.createCriteria().andIdIn(rids);
		return roleMapper.selectByExample(rExample);
	}
	
	public PageBeanModel<StaffModel> getStaffList(String database,int project,int offset,int limit) {
		Utils.setDatabase(database);
		ProjectStaffRoleExample psrExample = new ProjectStaffRoleExample();
		psrExample.createCriteria().andProjectEqualTo(project);
		List<ProjectStaffRole> pslist= staffRoleMapper.selectByExample(psrExample);
		List<Integer> sids = new ArrayList<Integer>();
		for (ProjectStaffRole projectStaffRole : pslist) {
			sids.add(projectStaffRole.getStaff());
		}
		PageHelper.startPage(offset,limit);
		PageHelper.orderBy("id desc");
		StaffExample example = new StaffExample();
		example.createCriteria().andIdIn(sids);
		List<Staff> list = staffMapper.selectByExample(example);
		if(null==list) {
			list=new ArrayList<>();
		}
		List<Role> superadminRoles =getProjectStaffRole(database, project, 0);
		if(superadminRoles.size()>0) {
			Staff superadmin =new Staff();
			Enterprise tempAdmin = enterpriseMapper.selectByCode(database);
			superadmin.setId(0);
			superadmin.setName(tempAdmin.getName());
			superadmin.setIdcard(tempAdmin.getLegalPersonIdcard());
			superadmin.setMobile(tempAdmin.getMobile());
			list.add(0,superadmin);
		}	
		PageBean<Staff> page = new PageBean<Staff>(list);
		List<StaffModel> models =new ArrayList<StaffModel>();
		for (Staff staff : list) {
			StaffModel model = new StaffModel();
			ProjectStaffPrice temp = getUnitPrice(database,project,staff.getId());
			BeanUtil.copy(staff, model);
			if(null!=temp&&staff.getId()!=0) {
				model.setUnit_price(getPriceFluctuation(database, temp.getUnitPrice()).getPrice());
			}
			StringBuffer sb =new StringBuffer();
			StringBuffer sbIds =new StringBuffer();
			List<Role> roles =getProjectStaffRole(database, project, staff.getId());
			for (Role role : roles) {
				sb.append(role.getName());
				sb.append(",");
				sbIds.append(role.getId());
				sbIds.append(",");
			}
			sb.deleteCharAt(sb.length()-1);
			model.setRoles(sb.toString());
			sbIds.deleteCharAt(sbIds.length()-1);
			model.setRoleIds(sbIds.toString());
			if(null!=temp) {
				UnitTypeCombination utc = getUnitTypeCombination(database, temp.getUnitPriceType());
				try {
					model.setUnit_price_type(getBasePriceType(database,utc.getBasePriceType()).getName());
				} catch (Exception e) {
					System.out.println("====staff price Type miss==========="+staff.getId());
				}
			}
			models.add(model);
		}
		return new PageBeanModel<StaffModel>(page.getTotal(),models);
	}
	
	private ProjectStaffPrice getUnitPrice(String database,int project,int staff) {
		Utils.setDatabase(database);
		ProjectStaffPriceExample example = new ProjectStaffPriceExample();
		example.createCriteria().andProjectEqualTo(project).andStaffEqualTo(staff);
		try {
			return staffPriceMapper.selectByExample(example).get(0);
		} catch (Exception e) {
			return null;
		}
	}
	public List<Staff> getStaff(String database,int managerRole,String query){
		Utils.setDatabase(database);
		PageHelper.startPage(0, 10);
		StaffExample sExample = new StaffExample();
		if(managerRole!=0) {
			ProjectStaffRoleExample srExample = new ProjectStaffRoleExample();
			srExample.createCriteria().andRoleEqualTo(managerRole);
			List<ProjectStaffRole> srlist =staffRoleMapper.selectByExample(srExample);
			List<Integer> sids = new ArrayList<Integer>();
			for (ProjectStaffRole staffRole : srlist) {
				sids.add(staffRole.getStaff());
			}
			sExample.createCriteria().andIdIn(sids);
		}
		sExample.createCriteria().andNameLike("%"+query+"%");
		return staffMapper.selectByExample(sExample);
	}
	
	public List<Staff> getStaffByRole(String database,int project,int role){
		Utils.setDatabase(database);
		ProjectStaffRoleExample srExample = new ProjectStaffRoleExample();
		ProjectStaffRoleExample.Criteria criteria = srExample.createCriteria();
		criteria.andRoleEqualTo(role);
		if(project!=0) {
			criteria.andProjectEqualTo(project);
		}
		List<ProjectStaffRole> srlist =staffRoleMapper.selectByExample(srExample);
		List<Integer> sids = new ArrayList<Integer>();
		for (ProjectStaffRole staffRole : srlist) {
			sids.add(staffRole.getStaff());
		}
		if(sids.size()==0) {
			return null;
		}
		StaffExample sExample = new StaffExample();
		sExample.createCriteria().andIdIn(sids);
		return staffMapper.selectByExample(sExample);
		
	}
	
	
	public List<Integer> getStaffIdsByRole(String database,int project,int role){
		Utils.setDatabase(database);
		ProjectStaffRoleExample srExample = new ProjectStaffRoleExample();
		ProjectStaffRoleExample.Criteria criteria = srExample.createCriteria();
		criteria.andRoleEqualTo(role);
		if(project!=0) {
			criteria.andProjectEqualTo(project);
		}
		List<ProjectStaffRole> srlist =staffRoleMapper.selectByExample(srExample);
		List<Integer> sids = new ArrayList<Integer>();
		for (ProjectStaffRole staffRole : srlist) {
			sids.add(staffRole.getStaff());
		}
		return sids;
		
	}
	public boolean addRelationShip(String database,int project,Staff staff,int[] role,UnitTypeCombination utc,int unit_price) {
		int result = 0;
		for (int i : role) {
			if(i==0) {
				continue;
			}
			ProjectStaffRole psr = new ProjectStaffRole();
			psr.setProject(project);
			psr.setRole(i);
			psr.setStaff(staff.getId());
			result = staffRoleMapper.insertSelective(psr);
		}
		Staff oldStaff = staffMapper.selectByPrimaryKey(database, staff.getId());
		if(!oldStaff.getPassword().equals(staff.getPassword())) {
			staff.setPassword(MD5Util.encrypt(staff.getPassword()));
		}
		result=staffMapper.updateByPrimaryKeySelective(staff);
		if(result!=0 && null!=utc) {
			PriceFluctuation pf = new PriceFluctuation();
			pf.setPrice(unit_price);
			pf.setType(0);
			pf.setStartDate(Utils.getCurrentDate());
			pf.setCreateDate(Utils.getCurrentTime());
			result=insertUnitPrice(database, pf);
			ProjectStaffPrice psp =new ProjectStaffPrice();
			psp.setProject(project);
			psp.setUnitPrice(pf.getId());
			psp.setUnitPriceType(1);
			psp.setStaff(staff.getId());
			result=staffPriceMapper.insertSelective(psp);
		}
		
		return result==1? true:false;

	}
	public boolean addStaff(String database,int project,Staff staff,int[] role,UnitTypeCombination utc,int unit_price) {
		Utils.setDatabase(database);
		staffMapper.insertSelective(staff);
		int result=0;
		for (int i : role) {
			if(i==0) {
				continue;
			}
			ProjectStaffRole psr = new ProjectStaffRole();
			psr.setProject(project);
			psr.setRole(i);
			psr.setStaff(staff.getId());
			result = staffRoleMapper.insertSelective(psr);
		}
		if(result!=0&&null!=utc) {
			result=insertUnitTypeCombination(database,utc);
			PriceFluctuation pf = new PriceFluctuation();
			pf.setPrice(unit_price);
			pf.setType(0);
			pf.setStartDate(Utils.getCurrentDate());
			pf.setCreateDate(Utils.getCurrentTime());
			result=insertUnitPrice(database, pf);
			ProjectStaffPrice psp =new ProjectStaffPrice();
			psp.setProject(project);
			psp.setUnitPrice(pf.getId());
			psp.setUnitPriceType(utc.getId());
			psp.setStaff(staff.getId());
			result=staffPriceMapper.insertSelective(psp);
		}
		return result==1? true:false;
	}
	
	public boolean updateStaffProject(String database,int project,int tempId,int[] staff,int role) {
		Utils.setDatabase(database);
		ProjectStaffRoleExample example =new ProjectStaffRoleExample();
		example.createCriteria().andRoleEqualTo(role).andProjectEqualTo(tempId);
		staffRoleMapper.deleteByExample(example);
		int result=0;
		for (int i : staff) {
			ProjectStaffRole psr = new ProjectStaffRole();
			psr.setProject(project);
			psr.setRole(role);
			psr.setStaff(i);
			result=staffRoleMapper.insertSelective(psr);
		}
		return result==0? false:true;
	}
	
	public boolean updateStaff(String database,Staff staff) {
		int result = staffMapper.updateByPrimaryKeySelective(staff);
		return result==1?true:false;
	}
	public Staff getUserByMobile(String database, String mobile) {
		Utils.setDatabase(database);
		StaffExample example = new StaffExample();
		example.createCriteria().andMobileEqualTo(mobile);
		List <Staff> list =staffMapper.selectByExample(example);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	public boolean updateStaff(String database, Staff staff,int project,int[] role,int unit_price) {
		Utils.setDatabase(database);
		StaffExample example = new StaffExample();
		example.createCriteria().andIdEqualTo(staff.getId());
		int result = staffMapper.updateByExample(staff, example);
		ProjectStaffRoleExample psrExample =  new ProjectStaffRoleExample();
		psrExample.createCriteria().andStaffEqualTo(staff.getId()).andProjectEqualTo(project);
		staffRoleMapper.deleteByExample(psrExample);
		for (int i : role) {
			if(i==0) {
				continue;
			}
			ProjectStaffRole psr = new ProjectStaffRole();
			psr.setProject(project);
			psr.setRole(i);
			psr.setStaff(staff.getId());
			result = staffRoleMapper.insertSelective(psr);
		}
		
		
		PriceFluctuation pf = new PriceFluctuation();
		pf.setPrice(unit_price);
		pf.setType(0);
		pf.setStartDate(Utils.getCurrentDate());
		pf.setCreateDate(Utils.getCurrentTime());
		result=insertUnitPrice(database, pf);
		try {
			ProjectStaffPriceExample pspExample = new ProjectStaffPriceExample();
			pspExample.createCriteria().andProjectEqualTo(project).andStaffEqualTo(staff.getId());
			ProjectStaffPrice psp = staffPriceMapper.selectByExample(pspExample).get(0);
			psp.setUnitPrice(pf.getId());
			staffPriceMapper.updateByPrimaryKeySelective(psp);
		} catch (Exception e) {
			ProjectStaffPrice psp = new ProjectStaffPrice();
			psp.setProject(project);
			psp.setStaff(staff.getId());
			psp.setUnitPrice(pf.getId());
			UnitTypeCombination utc = new UnitTypeCombination();
			utc.setBasePriceType(1);
			unitTypeCombinationMapper.insertSelective(utc);
			psp.setUnitPriceType(utc.getId());
			staffPriceMapper.insertSelective(psp);
		}
		return result==1? true:false;
	}
	public boolean deleteStaff(String database, Staff staff,int project) {
		Utils.setDatabase(database);
		ProjectStaffRoleExample psrExample =  new ProjectStaffRoleExample();
		psrExample.createCriteria().andStaffEqualTo(staff.getId()).andProjectEqualTo(project);
		int result=staffRoleMapper.deleteByExample(psrExample);
		ProjectStaffPriceExample pspExample = new ProjectStaffPriceExample();
		psrExample.createCriteria().andStaffEqualTo(staff.getId()).andProjectEqualTo(project);
		result = staffPriceMapper.deleteByExample(pspExample);
		return result==0? false:true;
	}
	
	public boolean hasName(String database,int id,String name) {
		Utils.setDatabase(database);
		StaffExample example = new StaffExample();
		example.createCriteria().andNameEqualTo(name);
		List<Staff> list= staffMapper.selectByExample(example);
		if(list.size()>0) {
			for (Staff entity : list) {
				if(entity.getId()==id) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean hasMobile(String database,int id,String value) {
		Utils.setDatabase(database);
		StaffExample example = new StaffExample();
		example.createCriteria().andMobileEqualTo(value);
		List<Staff> list= staffMapper.selectByExample(example);
		if(list.size()>0) {
			for (Staff entity : list) {
				if(entity.getId()==id) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}