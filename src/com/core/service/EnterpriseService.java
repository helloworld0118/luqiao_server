package com.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.entity.main.Enterprise;
import com.core.entity.main.EnterpriseMeal;
import com.core.entity.main.Module;
import com.core.entity.main.ModuleMeal;
import com.core.mapper.main.EnterpriseMapper;
import com.core.mapper.main.EnterpriseMealMapper;
import com.core.mapper.main.ModuleMapper;
import com.core.mapper.main.ModuleMealMapper;

@Service
public class EnterpriseService {
	@Autowired
	private EnterpriseMapper enterpriseMapper;

	@Autowired
	private EnterpriseMealMapper enterpriseMealMapper;

	@Autowired
	private ModuleMealMapper moduleMealMapper;

	@Autowired
	private ModuleMapper moduleMapper;
	
	
	public List<Enterprise> getlist() {
		return enterpriseMapper.selectAll();
	}

	public Enterprise getByMobile(String mobile) {
		return enterpriseMapper.selectByMobile(mobile);
	}
	public Enterprise getByMobileAndCode(String mobile,String code) {
		return enterpriseMapper.selectByMobileAndCode(mobile,code);
	}
	
	public Enterprise getByCode(String code) {
		return enterpriseMapper.selectByCode(code);
	}

	public Module getModuleById(int id) {
		return moduleMapper.selectByPrimaryKey(id);
	}
	public boolean updateEnterprise(Enterprise enterprise) {
		int result = enterpriseMapper.updateByPrimaryKey(enterprise);
		return result==1?true:false;
	}
	
	
	public List<Module> getModuelsByEnterprise(Enterprise enterprise) {
		List<EnterpriseMeal> emlist_0= enterpriseMealMapper.selectByEnterprise(enterprise.getId(),0);
		List<EnterpriseMeal> emlist_1= enterpriseMealMapper.selectByEnterprise(enterprise.getId(),1);
		
		StringBuffer sb = new StringBuffer();
		for (EnterpriseMeal enterpriseMeal : emlist_0) {
			sb.append(enterpriseMeal.getMeal());
			sb.append(",");
		}
		for (EnterpriseMeal enterpriseMeal : emlist_1) {
			sb.append(enterpriseMeal.getMeal());
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		List<ModuleMeal> mmlist = moduleMealMapper.selectByIds(sb.toString());
		sb = new StringBuffer();
		for (ModuleMeal moduleMeal : mmlist) {
			sb.append(moduleMeal.getModule());
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		return moduleMapper.selectByIds(sb.toString());
	}
}