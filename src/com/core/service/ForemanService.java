package com.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.entity.BasePriceType;
import com.core.entity.BasePriceTypeExample;
import com.core.entity.BaseType;
import com.core.entity.BaseTypeExample;
import com.core.entity.Foreman;
import com.core.entity.ForemanExample;
import com.core.entity.MaterialExample;
import com.core.entity.MaterialType;
import com.core.entity.Mechanics;
import com.core.entity.MechanicsExample;
import com.core.entity.MechanicsProjectOwner;
import com.core.entity.MechanicsProjectOwnerExample;
import com.core.entity.PriceFluctuation;
import com.core.entity.ProjectForeman;
import com.core.entity.ProjectForemanExample;
import com.core.entity.SkipMaterialPrice;
import com.core.entity.SkipMaterialPriceExample;
import com.core.entity.UnitTypeCombination;
import com.core.entity.UnitTypeCombinationExample;
import com.core.entity.WorkChargeInfo;
import com.core.entity.WorkChargeInfoExample;
import com.core.mapper.BasePriceTypeMapper;
import com.core.mapper.BaseTypeMapper;
import com.core.mapper.ForemanMapper;
import com.core.mapper.MaterialTypeMapper;
import com.core.mapper.MechanicsMapper;
import com.core.mapper.MechanicsProjectOwnerMapper;
import com.core.mapper.ProjectForemanMapper;
import com.core.mapper.SkipMaterialPriceMapper;
import com.core.mapper.WorkChargeInfoMapper;
import com.core.util.PageBean;
import com.core.util.PageBeanModel;
import com.core.util.Utils;
import com.core.util.model.MaterialModel;
import com.core.util.model.MechanicModel;
import com.core.util.model.MechanicPriceModel;
import com.core.util.model.ShowWorkChargeModel;
import com.core.util.model.SkipMaterialPriceModel;
import com.core.util.model.WorkChargeModel;
import com.core.util.model.api.MechanicsModel;
import com.github.pagehelper.PageHelper;

@Service
public class ForemanService extends UnitService {

	@Autowired
	private WorkChargeInfoMapper workChargeInfoMapper;

	@Autowired
	private MechanicsProjectOwnerMapper mechanicsProjectOwnerMapper;

	@Autowired
	private MechanicsMapper mechanicsMapper;

	@Autowired
	private ForemanMapper foremanMapper;

	@Autowired
	private ProjectForemanMapper projectForemanMapper;

	@Autowired
	private BaseTypeMapper baseTypeMapper;
	
	@Autowired
	private MaterialTypeMapper materialTypeMapper;

	@Autowired
	private BasePriceTypeMapper basePriceTypeMapper;

	@Autowired
	private SkipMaterialPriceMapper skipMaterialPriceMapper;
	
	public List<BaseType> getBaseTypeByType(String database, int type) {
		Utils.setDatabase(database);
		BaseTypeExample example = new BaseTypeExample();
		example.createCriteria().andTypeEqualTo(type);
		return baseTypeMapper.selectByExample(example);
	}

	public List<BasePriceType> getBasePriceTypeByType(String database, int type) {
		Utils.setDatabase(database);
		BasePriceTypeExample example = new BasePriceTypeExample();
		example.createCriteria().andTypeEqualTo(type);
		return basePriceTypeMapper.selectByExample(example);
	}
	
	public List<Foreman> getForemanList(String database,int project, String name, int type) {
		Utils.setDatabase(database);
		ProjectForemanExample pfExample = new ProjectForemanExample();
		pfExample.createCriteria().andProjectEqualTo(project);
		List<ProjectForeman> pflist = projectForemanMapper.selectByExample(pfExample);
		List<Integer> fids = new ArrayList<Integer>();
		for (ProjectForeman projectForeman : pflist) {
			fids.add(projectForeman.getForeman());
		}
		if (fids.size() == 0) {
			return new ArrayList<Foreman>();
		}
		
		ForemanExample fExample = new ForemanExample();
		ForemanExample.Criteria criteria = fExample.createCriteria();
		criteria.andTypeEqualTo(type);
		criteria.andIdIn(fids);
		if(null!=name &&!"".equals(name)) {
			criteria.andNameLike("%" + name + "%");
		}
		return foremanMapper.selectByExample(fExample);
	}

	public List<Foreman> getForemanList(String database, String name, int type, int offset, int limit) {
		Utils.setDatabase(database);
		PageHelper.startPage(offset, limit);
		ForemanExample fExample = new ForemanExample();
		if(null!=name &&!"".equals(name)) {
			fExample.createCriteria().andTypeEqualTo(type).andNameLike("%" + name + "%");
		}
		return foremanMapper.selectByExample(fExample);
	}

	public PageBean<Foreman> getForemanList(String database, int project, int offset, int limit) {
		Utils.setDatabase(database);
		PageHelper.startPage(offset, limit);
		ProjectForemanExample pfExample = new ProjectForemanExample();
		pfExample.createCriteria().andProjectEqualTo(project);
		List<ProjectForeman> pflist = projectForemanMapper.selectByExample(pfExample);
		List<Integer> fids = new ArrayList<Integer>();
		for (ProjectForeman projectForeman : pflist) {
			fids.add(projectForeman.getForeman());
		}
		if (fids.size() == 0) {
			PageBean<Foreman> page = new PageBean<Foreman>(null);
			return page;
		}
		PageBean<ProjectForeman> temp = new PageBean<ProjectForeman>(pflist);
		PageHelper.startPage(offset, limit);
		PageHelper.orderBy("id DESC");
		ForemanExample fExample = new ForemanExample();
		fExample.createCriteria().andIdIn(fids);
		List<Foreman> list = foremanMapper.selectByExample(fExample);
		PageBean<Foreman> page = new PageBean<Foreman>(list);
		page.setTotal(temp.getTotal());
		return page;
	}

	public Foreman getForemanById(String database, int id) {
		return foremanMapper.selectByPrimaryKey(database, id);
	}

	public boolean addForeman(String database, Foreman foreman, int project, int updateId) {
		Utils.setDatabase(database);
		int result = foremanMapper.insertSelective(foreman);

		ProjectForeman pf = new ProjectForeman();
		pf.setForeman(foreman.getId());
		pf.setProject(project);
		projectForemanMapper.insertSelective(pf);
		WorkChargeInfo workChargeInfo = new WorkChargeInfo();
		workChargeInfo.setForeman(foreman.getId());
		WorkChargeInfoExample example = new WorkChargeInfoExample();
		example.createCriteria().andForemanEqualTo(updateId).andProjectEqualTo(project);
		workChargeInfoMapper.updateByExampleSelective(workChargeInfo, example);
		return result == 1 ? true : false;
	}

	public boolean addRelationship(String database, int foreman, int project) {
		ProjectForeman pf = new ProjectForeman();
		pf.setForeman(foreman);
		pf.setProject(project);
		int result = projectForemanMapper.insertSelective(pf);
		return result == 1 ? true : false;
	}

	public boolean updateForeman(String database, Foreman foreman) {
		Utils.setDatabase(database);
		ForemanExample example = new ForemanExample();
		example.createCriteria().andIdEqualTo(foreman.getId());
		int result = foremanMapper.updateByExampleSelective(foreman,example);

		return result == 1 ? true : false;
	}

	public boolean deleteForeman(String database, int project, int foreman) {
		Utils.setDatabase(database);
		ProjectForemanExample example = new ProjectForemanExample();
		example.createCriteria().andForemanEqualTo(foreman).andProjectEqualTo(project);
		int result = projectForemanMapper.deleteByExample(example);

		WorkChargeInfoExample wciExample = new WorkChargeInfoExample();
		wciExample.createCriteria().andProjectEqualTo(project).andForemanEqualTo(foreman);
		workChargeInfoMapper.deleteByExample(wciExample);
		return result == 1 ? true : false;
	}

	public int addWorkCharge(String database, WorkChargeInfo workChargeInfo,
			UnitTypeCombination unitTypeCombination, PriceFluctuation priceFluctuation) {
		Utils.setDatabase(database);
		UnitTypeCombinationExample utcExample  = new UnitTypeCombinationExample();
		UnitTypeCombinationExample.Criteria criteria = utcExample.createCriteria();
		criteria.andBasePriceTypeEqualTo(unitTypeCombination.getBasePriceType());
		criteria.andBaseTypeEqualTo(unitTypeCombination.getBaseType());
		List<UnitTypeCombination> utclist = unitTypeCombinationMapper.selectByExample(utcExample);
		if(utclist.size()>0) {
			
			List<Integer> tempIds = new ArrayList<>();
			for (UnitTypeCombination temp : utclist) {
				tempIds.add(temp.getId());
			}	
			WorkChargeInfoExample mExample = new WorkChargeInfoExample();
			mExample.createCriteria().andProjectEqualTo(workChargeInfo.getProject()).andWorkerTypeEqualTo(workChargeInfo.getWorkerType()).andForemanEqualTo(workChargeInfo.getForeman()).andUnitPriceTypeIn(tempIds);
			int size = workChargeInfoMapper.selectByExample(mExample).size();
			if(size>0) {
				return -1;
			}
		}
		
		unitTypeCombinationMapper.insertSelective(unitTypeCombination);
		priceFluctuationMapper.insertSelective(priceFluctuation);
		workChargeInfo.setUnitPrice(priceFluctuation.getId());
		workChargeInfo.setUnitPriceType(unitTypeCombination.getId());

		int result = workChargeInfoMapper.insertSelective(workChargeInfo);
		return result;
	}

	public int updateWorkCharge(String database, WorkChargeInfo workChargeInfo,
			UnitTypeCombination unitTypeCombination, PriceFluctuation priceFluctuation) {
		Utils.setDatabase(database);
		UnitTypeCombinationExample utcExample  = new UnitTypeCombinationExample();
		UnitTypeCombinationExample.Criteria criteria = utcExample.createCriteria();
		criteria.andBasePriceTypeEqualTo(unitTypeCombination.getBasePriceType());
		criteria.andBaseTypeEqualTo(unitTypeCombination.getBaseType());
		List<UnitTypeCombination> utclist = unitTypeCombinationMapper.selectByExample(utcExample);
		if(utclist.size()>0) {
			
			List<Integer> tempIds = new ArrayList<>();
			for (UnitTypeCombination temp : utclist) {
				tempIds.add(temp.getId());
			}	
			WorkChargeInfoExample mExample = new WorkChargeInfoExample();
			mExample.createCriteria().andProjectEqualTo(workChargeInfo.getProject()).andForemanEqualTo(workChargeInfo.getForeman()).andUnitPriceTypeIn(tempIds);
			List<WorkChargeInfo> list = workChargeInfoMapper.selectByExample(mExample);
			if(list.size()>0&&list.get(0).getId()!=workChargeInfo.getId()) {
				return -1;
			}
		}
		
		unitTypeCombinationMapper.insertSelective(unitTypeCombination);
		priceFluctuationMapper.insertSelective(priceFluctuation);
		workChargeInfo.setUnitPrice(priceFluctuation.getId());
		workChargeInfo.setUnitPriceType(unitTypeCombination.getId());
		int result = workChargeInfoMapper.updateByPrimaryKeySelective(workChargeInfo);
		return result;
	}

	public boolean deleteWorkCharge(String database, int workChargeInfo) {
		// Utils.setDatabase(database);
		int result = workChargeInfoMapper.deleteByPrimaryKey(database, workChargeInfo);
		return result == 1 ? true : false;
	}

	public List<WorkChargeModel> getworkChargeInfoList(String database, int project, int foreman) {
		Utils.setDatabase(database);
		WorkChargeInfoExample example = new WorkChargeInfoExample();
		example.createCriteria().andProjectEqualTo(project).andForemanEqualTo(foreman);
		List<WorkChargeInfo> list = workChargeInfoMapper.selectByExample(example);
		List<WorkChargeModel> models = new ArrayList<WorkChargeModel>();
		for (WorkChargeInfo workChargeInfo : list) {
			UnitTypeCombination utc = getUnitTypeCombination(database, workChargeInfo.getUnitPriceType());
			String workTypeName=baseTypeMapper.selectByPrimaryKey(database, workChargeInfo.getWorkerType()).getName();	
			String baseTypeName=baseTypeMapper.selectByPrimaryKey(database, utc.getBaseType()).getName();
			models.add(new WorkChargeModel(workChargeInfo.getId(),workChargeInfo.getWorkerType(),workTypeName,utc.getBaseType(),baseTypeName,
					workChargeInfo.getCount(),utc.getBasePriceType(),
					basePriceTypeMapper.selectByPrimaryKey(database, utc.getBasePriceType()).getName(),
					getPriceFluctuation(database, workChargeInfo.getUnitPrice()).getPrice()));
			
		}
		return models;
	}

	public List<WorkChargeModel> getWorkerDetailList(String database, int project, int foreman) {
		Utils.setDatabase(database);
		WorkChargeInfoExample example = new WorkChargeInfoExample();
		example.createCriteria().andProjectEqualTo(project).andForemanEqualTo(foreman);
		List<WorkChargeInfo> list = workChargeInfoMapper.selectByExample(example);
		List<WorkChargeModel> models = new ArrayList<WorkChargeModel>();
		for (WorkChargeInfo workChargeInfo : list) {
			UnitTypeCombination utc = getUnitTypeCombination(database, workChargeInfo.getUnitPriceType());
			BaseType bt =baseTypeMapper.selectByPrimaryKey(database, utc.getBaseType());
			BasePriceType bpt = basePriceTypeMapper.selectByPrimaryKey(database, utc.getBasePriceType());
			BaseType workerType = baseTypeMapper.selectByPrimaryKey(database, workChargeInfo.getWorkerType());
			models.add(new WorkChargeModel(workChargeInfo.getId(),workerType.getId(),workerType.getName(), utc.getBaseType(), bt.getName(),workChargeInfo.getCount(), 
					utc.getBasePriceType(),bpt.getName(), getPriceFluctuation(database, workChargeInfo.getUnitPrice()).getPrice()));
		}
		return models;
	}
	
	public List<ShowWorkChargeModel> getShowWorkChargeInfoList(String database, int project, int foreman) {
		Utils.setDatabase(database);
		WorkChargeInfoExample example = new WorkChargeInfoExample();
		example.createCriteria().andProjectEqualTo(project).andForemanEqualTo(foreman);
		List<WorkChargeInfo> list = workChargeInfoMapper.selectByExample(example);
		List<ShowWorkChargeModel> models = new ArrayList<ShowWorkChargeModel>();
		for (WorkChargeInfo workChargeInfo : list) {
			UnitTypeCombination utc = getUnitTypeCombination(database, workChargeInfo.getUnitPriceType());
			models.add(new ShowWorkChargeModel(
					baseTypeMapper.selectByPrimaryKey(database, workChargeInfo.getWorkerType()).getName(),
					baseTypeMapper.selectByPrimaryKey(database, utc.getBaseType()).getName(),
					workChargeInfo.getCount(),
					basePriceTypeMapper.selectByPrimaryKey(database, utc.getBasePriceType()).getName(),
					getPriceFluctuation(database, workChargeInfo.getUnitPrice()).getPrice()));
		}
		return models;
	}

	public boolean addMechanic(String database, Foreman foreman, int project, Mechanics mechanics,int type) {
		Utils.setDatabase(database);
		if (null == foreman.getId() || foreman.getId() == 0) {
			foreman.setCreateDate(Utils.getCurrentTime());
			foremanMapper.insertSelective(foreman);
		}
		mechanics.setCreateDate(Utils.getCurrentTime());
		int result = mechanicsMapper.insertSelective(mechanics);
		MechanicsProjectOwner mpo = new MechanicsProjectOwner();
		mpo.setMechanics(mechanics.getId());
		mpo.setOwner(foreman.getId());
		mpo.setProject(project);
		mpo.setType(type);
		mechanicsProjectOwnerMapper.insertSelective(mpo);
		return result == 1 ? true : false;
	}
	
	
	
	public boolean addMechanic(String database, int project, Foreman foreman, Mechanics mechanics, int mid,int type) {
		Utils.setDatabase(database);
		if (null == foreman.getId() || foreman.getId() == 0) {
			foreman.setCreateDate(Utils.getCurrentTime());
			foremanMapper.insertSelective(foreman);
		}
		mechanics.setCreateDate(Utils.getCurrentTime());
		int result = mechanicsMapper.insertSelective(mechanics);
		MechanicsProjectOwner mpo = new MechanicsProjectOwner();
		mpo.setMechanics(mechanics.getId());
		mpo.setOwner(foreman.getId());
		mpo.setProject(project);
		mpo.setType(type); //0小车 1机械
		MechanicsProjectOwnerExample example=new MechanicsProjectOwnerExample();
		example.createCriteria().andMechanicsEqualTo(mid).andProjectEqualTo(project);
		mechanicsProjectOwnerMapper.updateByExampleSelective(mpo, example);
		return result == 1 ? true : false;
	}
	
	
	public boolean addSkip(String database, Foreman foreman, int project, Mechanics mechanics, int skip) {
		Utils.setDatabase(database);
		if (null == foreman.getId() || foreman.getId() == 0) {
			foremanMapper.insertSelective(foreman);
		}
		int result = mechanicsMapper.insertSelective(mechanics);
		
		SkipMaterialPrice smpinfo = new SkipMaterialPrice();
		smpinfo.setForeman(foreman.getId());
		smpinfo.setMechainc(mechanics.getId());
		smpinfo.setProject(project);
		SkipMaterialPriceExample example =new SkipMaterialPriceExample();
		example.createCriteria().andMechaincEqualTo(skip).andProjectEqualTo(project);
		skipMaterialPriceMapper.updateByExampleSelective(smpinfo, example);
		
		return result == 1 ? true : false;
	}
	
	public boolean updateSkip(String database, Foreman foreman, int project, Mechanics mechanics) {
		
		foremanMapper.updateByPrimaryKeySelective(foreman);
		int result = mechanicsMapper.updateByPrimaryKeySelective(mechanics);
		
		
		SkipMaterialPrice smpinfo = new SkipMaterialPrice();
		smpinfo.setForeman(foreman.getId());
		smpinfo.setMechainc(mechanics.getId());
		smpinfo.setProject(project);
		SkipMaterialPriceExample example =new SkipMaterialPriceExample();
		example.createCriteria().andMechaincEqualTo(mechanics.getId()).andProjectEqualTo(project);
		skipMaterialPriceMapper.updateByExampleSelective(smpinfo, example);
		
		return result == 1 ? true : false;
	}
	public boolean addMechanic(String database, Foreman foreman, int project, Mechanics mechanics, int basePriceType,
			int price,int type) {
		Utils.setDatabase(database);
		if (null == foreman.getId() || foreman.getId() == 0) {
			foremanMapper.insertSelective(foreman);
		}
		int result = mechanicsMapper.insertSelective(mechanics);
		if (price == -1) {
			MechanicsProjectOwner mpo = new MechanicsProjectOwner();
			mpo.setMechanics(mechanics.getId());
			mpo.setOwner(foreman.getId());
			mpo.setProject(project);
			mpo.setType(1);
			mechanicsProjectOwnerMapper.insertSelective(mpo);
		} else {
			UnitTypeCombination utc = new UnitTypeCombination();
			utc.setBasePriceType(basePriceType);
			unitTypeCombinationMapper.insertSelective(utc);

			PriceFluctuation pf = new PriceFluctuation();
			pf.setCreateDate(Utils.getCurrentTime());
			pf.setStartDate(Utils.getCurrentDate());
			pf.setType(2);
			pf.setPrice(price);
			priceFluctuationMapper.insertSelective(pf);

			MechanicsProjectOwner mpo = new MechanicsProjectOwner();
			mpo.setMechanics(mechanics.getId());
			mpo.setOwner(foreman.getId());
			mpo.setProject(project);
			mpo.setUnitPriceType(utc.getId());
			mpo.setUnitPrice(pf.getId());
			mpo.setType(type);
			mechanicsProjectOwnerMapper.insertSelective(mpo);
		}

		return result == 1 ? true : false;
	}

	private Mechanics getMechanicById(String database, int id, int type) {
		MechanicsExample example = new MechanicsExample();
		example.createCriteria().andIdEqualTo(id).andMTypeEqualTo(type);
		List<Mechanics> list = mechanicsMapper.selectByExample(example);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List<Mechanics> getMechanicsByPlateName(String database,int project,String plateName, int type) {
		MechanicsExample example = new MechanicsExample();
		if(null!=plateName&&!"".equals(plateName)) {
			example.createCriteria().andPlateNumberLike("%" + plateName + "%").andMTypeEqualTo(type);
		}
		List<Mechanics> tempList = mechanicsMapper.selectByExample(example);
		List<Mechanics> list = new ArrayList<>();
		for (Mechanics mechanic : tempList) {
			MechanicsProjectOwnerExample mpoExample = new MechanicsProjectOwnerExample();
			mpoExample.createCriteria().andProjectEqualTo(project).andMechanicsEqualTo(mechanic.getId());
			if(mechanicsProjectOwnerMapper.selectByExample(mpoExample).size()>0) {
				list.add(mechanic);
			}
			if(list.size()>=10) {
				break;
			}
		}	
				
		return list;
	}
	
	public List<Mechanics> getMechanicsByPlateName(String database, String plateName, int type) {
		MechanicsExample example = new MechanicsExample();
		if(null!=plateName&&!"".equals(plateName)) {
			example.createCriteria().andPlateNumberLike("%" + plateName + "%").andMTypeEqualTo(type);
		}
		List<Mechanics> list = mechanicsMapper.selectByExample(example);
		return list;
	}

	
	public boolean updateMechanic(String database, Foreman foreman, Mechanics mechanics, int project) {
		Utils.setDatabase(database);

		int result =foremanMapper.updateByPrimaryKeySelective(foreman);
		
		result = mechanicsMapper.updateByPrimaryKeySelective(mechanics);
		MechanicsProjectOwner mpo = new MechanicsProjectOwner();
		mpo.setMechanics(mechanics.getId());
		mpo.setOwner(foreman.getId());
		mpo.setProject(project);
		mpo.setType(1); //0小车 1机械
		MechanicsProjectOwnerExample example=new MechanicsProjectOwnerExample();
		example.createCriteria().andMechanicsEqualTo(mechanics.getId()).andProjectEqualTo(project);
		mechanicsProjectOwnerMapper.updateByExampleSelective(mpo, example);
		return result == 1 ? true : false;
	}

	public boolean updateMechanic(String database, Foreman foreman, Mechanics mechanics, int project, int basePriceType,
			int price) {
		Utils.setDatabase(database);

		MechanicsExample meExample =new MechanicsExample();
		meExample.createCriteria().andIdEqualTo(mechanics.getId());
		int result = mechanicsMapper.updateByExample(mechanics,meExample);

		foremanMapper.updateByPrimaryKeySelective(foreman);

		if (price != -1) {
			UnitTypeCombination utc = new UnitTypeCombination();
			utc.setBasePriceType(basePriceType);
			unitTypeCombinationMapper.insertSelective(utc);

			PriceFluctuation pf = new PriceFluctuation();
			pf.setCreateDate(Utils.getCurrentTime());
			pf.setStartDate(Utils.getCurrentDate());
			pf.setType(2);
			pf.setPrice(price);
			priceFluctuationMapper.insertSelective(pf);

			MechanicsProjectOwnerExample example = new MechanicsProjectOwnerExample();
			example.createCriteria().andProjectEqualTo(project).andOwnerEqualTo(foreman.getId())
					.andMechanicsEqualTo(mechanics.getId());

			MechanicsProjectOwner mpo = new MechanicsProjectOwner();
			mpo.setMechanics(mechanics.getId());
			mpo.setOwner(foreman.getId());
			mpo.setProject(project);
			mpo.setUnitPriceType(utc.getId());
			mpo.setUnitPrice(pf.getId());
			mpo.setType(mechanics.getmType());
			Utils.setDatabase(database);
			
			mechanicsProjectOwnerMapper.updateByExampleSelective(mpo, example);
		}

		return result == 1 ? true : false;
	}

	public boolean deleteMechanic(String database, int project, int foreman, int mechanics) {
		Utils.setDatabase(database);
		// int result =mechanicsMapper.deleteByPrimaryKey(database,mechanics);
		MechanicsProjectOwnerExample example = new MechanicsProjectOwnerExample();
		example.createCriteria().andProjectEqualTo(project).andOwnerEqualTo(foreman).andMechanicsEqualTo(mechanics);
		int result = mechanicsProjectOwnerMapper.deleteByExample(example);
		return result == 1 ? true : false;
	}
	
	public boolean deleteSkip(String database, int project, int foreman, int mechanics) {
		Utils.setDatabase(database);
		// int result =mechanicsMapper.deleteByPrimaryKey(database,mechanics);
		SkipMaterialPriceExample example = new SkipMaterialPriceExample();
		example.createCriteria().andProjectEqualTo(project).andForemanEqualTo(foreman).andMechaincEqualTo(mechanics);
		int result = skipMaterialPriceMapper.deleteByExample(example);
		return result == 1 ? true : false;
	}

	public boolean addCarRelationShip(String database, Foreman foreman, Mechanics mechanics, int project) {
		Utils.setDatabase(database);
		if (null!=foreman && null!=foreman.getId() && 0 != foreman.getId()) {
			foremanMapper.updateByPrimaryKeySelective(foreman);
		} else {
			foremanMapper.insertSelective(foreman);
		}
		if (null!=mechanics && null!=mechanics.getId() && 0 != mechanics.getId()) {
			mechanicsMapper.updateByPrimaryKeySelective(mechanics);
		} else {
			mechanicsMapper.insertSelective(mechanics);
		}

		MechanicsProjectOwner mpo = new MechanicsProjectOwner();
		mpo.setMechanics(mechanics.getId());
		mpo.setOwner(foreman.getId());
		mpo.setProject(project);
		mpo.setType(0);
		int result = mechanicsProjectOwnerMapper.insertSelective(mpo);
		return result == 1 ? true : false;
	}

	public boolean addMechanicsRelationShip(String database, Foreman foreman, Mechanics mechanics, int project,
			int mid) {
		Utils.setDatabase(database);
		if (null != foreman.getId()&&0 != foreman.getId()) {
			foremanMapper.updateByPrimaryKeySelective(foreman);
		} else {
			foremanMapper.insertSelective(foreman);
		}
		if (null != mechanics.getId()&& 0 != mechanics.getId()) {
			mechanicsMapper.updateByPrimaryKeySelective(mechanics);
		} else {
			mechanics.setCreateDate(Utils.getCurrentTime());
			mechanicsMapper.insertSelective(mechanics);
		}

		MechanicsProjectOwner mpo = new MechanicsProjectOwner();
		mpo.setMechanics(mechanics.getId());
		
		mpo.setOwner(foreman.getId());
		mpo.setProject(project);
		mpo.setType(1); //0小车 1机械
		MechanicsProjectOwnerExample example=new MechanicsProjectOwnerExample();
		example.createCriteria().andMechanicsEqualTo(mid).andProjectEqualTo(project);
		int result = mechanicsProjectOwnerMapper.updateByExampleSelective(mpo, example);
		return result == 0 ? false : true;
	}
	
	
	public boolean addSkipRelationShip(String database, Foreman foreman, Mechanics mechanics, int project,int temp_skipId) {
		Utils.setDatabase(database);
		if (null != foreman.getId()&&0 != foreman.getId()) {
			foremanMapper.updateByPrimaryKeySelective(foreman);
		} else {
			foremanMapper.insertSelective(foreman);
		}
		if (null != mechanics.getId()&& 0 != mechanics.getId()) {
			mechanicsMapper.updateByPrimaryKeySelective(mechanics);
		} else {
			mechanics.setCreateDate(Utils.getCurrentTime());
			mechanicsMapper.insertSelective(mechanics);
		}

		SkipMaterialPrice smpinfo = new SkipMaterialPrice();
		smpinfo.setForeman(foreman.getId());
		smpinfo.setMechainc(mechanics.getId());
		smpinfo.setProject(project);
		SkipMaterialPriceExample example =new SkipMaterialPriceExample();
		example.createCriteria().andMechaincEqualTo(temp_skipId).andProjectEqualTo(project);
		skipMaterialPriceMapper.updateByExampleSelective(smpinfo, example);
		
		return true;
	}
	
	public List<MechanicsModel> getMechanicsDetail(String database, int project,int foremanId,int id) {
		Utils.setDatabase(database);
		MechanicsProjectOwnerExample mpoExample = new MechanicsProjectOwnerExample();
		mpoExample.createCriteria().andProjectEqualTo(project).andTypeEqualTo(1).andMechanicsEqualTo(id).andOwnerEqualTo(foremanId);
		mpoExample.setDistinct(true);
		List<MechanicsProjectOwner> mpolist = mechanicsProjectOwnerMapper.selectByExample(mpoExample);
		List<MechanicsModel> models = new ArrayList<MechanicsModel>();
		for (MechanicsProjectOwner mechanicsProjectOwner : mpolist) {
			Mechanics mechanics = getMechanicById(database, mechanicsProjectOwner.getMechanics(), 1);
			if (null != mechanics) {
			    Foreman foreman = getForemanById(database, mechanicsProjectOwner.getOwner());
			    MechanicsModel model = new MechanicsModel(mechanics.getId(), foreman.getId(), mechanics.getPlateNumber(),
						foreman.getName(), mechanics.getmName(), foreman.getMobile(), foreman.getIdcard(),
						mechanics.getDriverName(), mechanics.getDriverMobile(), mechanics.getDriverIdcard(),
						mechanics.getCreateDate(), mechanics.getModel());
			    model.getMlist().addAll(getMechanicPrice(database, project, mechanics.getId(), true));
			    models.add(model);
			    
			}
		}
		return models;
	}
	public List<Mechanics> getMechanicsAll(String database,int project){
		Utils.setDatabase(database);
		Utils.setDatabase(database);
		MechanicsProjectOwnerExample mpoExample = new MechanicsProjectOwnerExample();
		mpoExample.createCriteria().andProjectEqualTo(project);
		mpoExample.setDistinct(true);
		List<MechanicsProjectOwner> mpolist = mechanicsProjectOwnerMapper.selectByExample(mpoExample);
		List<Integer> mIds = new ArrayList<>();
		for (MechanicsProjectOwner entity : mpolist) {
			mIds.add(entity.getMechanics());
		}
		MechanicsExample example = new MechanicsExample();
		example.createCriteria().andIdIn(mIds);
		example.setDistinct(true);
		return mechanicsMapper.selectByExample(example);
	}
	
	public List<MechanicModel> getMechanicsList(String database, int project) {
		Utils.setDatabase(database);
		MechanicsProjectOwnerExample mpoExample = new MechanicsProjectOwnerExample();
		mpoExample.createCriteria().andProjectEqualTo(project).andTypeEqualTo(1);
		mpoExample.setDistinct(true);
		List<MechanicsProjectOwner> mpolist = mechanicsProjectOwnerMapper.selectByExample(mpoExample);
		PageBean<MechanicsProjectOwner> temp = new PageBean<MechanicsProjectOwner>(mpolist);
		List<MechanicModel> models = new ArrayList<MechanicModel>();
		for (MechanicsProjectOwner mechanicsProjectOwner : mpolist) {
			Mechanics mechanics = getMechanicById(database, mechanicsProjectOwner.getMechanics(), 1);
			if (null != mechanics) {
				try {
					Foreman foreman = getForemanById(database, mechanicsProjectOwner.getOwner());
				    List<MechanicPriceModel> prices = getMechanicPrice(database,project,mechanics.getId(),true);
				    for (MechanicPriceModel mechanicPriceModel : prices) {
				    	 models.add(new MechanicModel(mechanics.getId(), foreman.getId(), mechanics.getPlateNumber(),
									foreman.getName(), mechanics.getmName(), foreman.getMobile(), foreman.getIdcard(),
									mechanics.getDriverName(), mechanics.getDriverMobile(), mechanics.getDriverIdcard(),
									mechanics.getCreateDate(), mechanics.getModel(),mechanicPriceModel.getBaseType(),mechanicPriceModel.getBaseTypeName(),
									mechanicPriceModel.getBasePriceType(),mechanicPriceModel.getBasePriceTypeName(),mechanicPriceModel.getUnitPrice()));
					}
				    
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			
		}
		return models;
	}
	
	public PageBeanModel<MechanicModel> getMechanics(String database, int project){
		Utils.setDatabase(database);
		PageHelper.orderBy("id DESC");
		List<Mechanics> list = mechanicsMapper.selectMechaincs(project);
		PageBean<Mechanics> temp = new PageBean<Mechanics>(list);
		List<MechanicModel> models = new ArrayList<MechanicModel>();
		for (Mechanics mechanics : list) {
			MechanicsProjectOwnerExample mpoExample = new MechanicsProjectOwnerExample();
			mpoExample.createCriteria().andProjectEqualTo(project).andMechanicsEqualTo(mechanics.getId());
			Foreman foreman = getForemanById(database,  mechanicsProjectOwnerMapper.selectByExample(mpoExample).get(0).getOwner());
			models.add(new MechanicModel(mechanics.getId(), foreman.getId(), mechanics.getPlateNumber(),
						foreman.getName(), mechanics.getmName(), foreman.getMobile(), foreman.getIdcard(),
						mechanics.getDriverName(), mechanics.getDriverMobile(), mechanics.getDriverIdcard(),
						mechanics.getCreateDate(), mechanics.getModel()));
		}
		PageBeanModel<MechanicModel> page = new PageBeanModel<MechanicModel>(temp.getTotal(), models);
		return page;
	}
	
	public PageBeanModel<MechanicModel> getMechanicsList(String database, int project, int type) {
		Utils.setDatabase(database);
		MechanicsProjectOwnerExample mpoExample = new MechanicsProjectOwnerExample();
		mpoExample.createCriteria().andProjectEqualTo(project).andTypeEqualTo(type);
		if(type==1) {
			mpoExample.setDistinct(true);
		}
		PageHelper.orderBy("id DESC");
		List<MechanicsProjectOwner> mpolist = mechanicsProjectOwnerMapper.selectByExample(mpoExample);
		PageBean<MechanicsProjectOwner> temp = new PageBean<MechanicsProjectOwner>(mpolist);
		List<MechanicModel> models = new ArrayList<MechanicModel>();
		for (MechanicsProjectOwner mechanicsProjectOwner : mpolist) {
			Mechanics mechanics = getMechanicById(database, mechanicsProjectOwner.getMechanics(), type);
			if (null != mechanics) {
				   Foreman foreman = getForemanById(database, mechanicsProjectOwner.getOwner());
				   if(type==1) {
					   models.add(new MechanicModel(mechanics.getId(), foreman.getId(), mechanics.getPlateNumber(),
								foreman.getName(), mechanics.getmName(), foreman.getMobile(), foreman.getIdcard(),
								mechanics.getDriverName(), mechanics.getDriverMobile(), mechanics.getDriverIdcard(),
								mechanics.getCreateDate(), mechanics.getModel()));
				   }else if (null != mechanicsProjectOwner.getUnitPriceType()) {
						UnitTypeCombination utc = getUnitTypeCombination(database,
								mechanicsProjectOwner.getUnitPriceType());
						BasePriceType bpt= basePriceTypeMapper.selectByPrimaryKey(database, utc.getBasePriceType());
						models.add(new MechanicModel(mechanics.getId(), foreman.getId(), mechanics.getPlateNumber(),
								foreman.getName(), mechanics.getmName(), foreman.getMobile(), foreman.getIdcard(),
								mechanics.getDriverName(), mechanics.getDriverMobile(), mechanics.getDriverIdcard(),
								mechanics.getCreateDate(), mechanics.getModel(),
								bpt.getId(),bpt.getName(),
								getPriceFluctuation(database, mechanicsProjectOwner.getUnitPrice()).getPrice()));

					}else {
						models.add(new MechanicModel(mechanics.getId(), foreman.getId(), mechanics.getPlateNumber(),
								foreman.getName(), foreman.getMobile(), foreman.getIdcard(), mechanics.getDriverName(),
								mechanics.getDriverMobile(), mechanics.getDriverIdcard(), mechanics.getCreateDate()));
					}
			}
		}
		PageBeanModel<MechanicModel> page = new PageBeanModel<MechanicModel>(temp.getTotal(), models);
		return page;
	}
	
	public List<MechanicsModel> getSkipDetail(String database, int project,int foremanId,int id) {
		Utils.setDatabase(database);
		SkipMaterialPriceExample example = new SkipMaterialPriceExample();
		example.createCriteria().andProjectEqualTo(project).andForemanEqualTo(foremanId).andMechaincEqualTo(id);
		List<SkipMaterialPrice> list = skipMaterialPriceMapper.selectByExampleGroupBySkip(example);
		SkipMaterialPrice sp = new SkipMaterialPrice();
		sp.setProject(project);
		long total = skipMaterialPriceMapper.countByExample(sp,null);
		List<MechanicsModel> models = new ArrayList<MechanicsModel>();
		for (SkipMaterialPrice skipMaterialPrice : list) {
			Foreman foreman = getForemanById(database, skipMaterialPrice.getForeman());
			Mechanics mechanics = getMechanicById(database, skipMaterialPrice.getMechainc(), 2);
			MechanicsModel model = new MechanicsModel(mechanics.getId(), foreman.getId(), mechanics.getPlateNumber(),
					foreman.getName(), foreman.getMobile(), foreman.getIdcard(), mechanics.getDriverName(),
					mechanics.getDriverMobile(), mechanics.getDriverIdcard(), mechanics.getCreateDate(),mechanics.getCapacity());
			model.getSlist().addAll(showMaterialPrices(database, project, foreman.getId(), mechanics.getId()));
			models.add(model);
		}
		return models;
	}
	
	
	
	public List<MechanicModel> getSkipsList4Api(String database, int project) {
		Utils.setDatabase(database);
		SkipMaterialPriceExample example = new SkipMaterialPriceExample();
		example.createCriteria().andProjectEqualTo(project);
		List<SkipMaterialPrice> list = skipMaterialPriceMapper.selectByExampleGroupBySkip(example);
		SkipMaterialPrice sp = new SkipMaterialPrice();
		sp.setProject(project);
		List<MechanicModel> models = new ArrayList<MechanicModel>();
		for (SkipMaterialPrice skipMaterialPrice : list) {
			try {
				Foreman foreman = getForemanById(database, skipMaterialPrice.getForeman());
				Mechanics mechanics = getMechanicById(database, skipMaterialPrice.getMechainc(), 2);
				List<SkipMaterialPriceModel> prices=showMaterialPrices(database, project, foreman.getId(), mechanics.getId());
				for (SkipMaterialPriceModel model : prices) {
					models.add(new MechanicModel(mechanics.getId(), foreman.getId(), mechanics.getPlateNumber(),
							foreman.getName(), foreman.getMobile(), foreman.getIdcard(), mechanics.getDriverName(),
							mechanics.getDriverMobile(), mechanics.getDriverIdcard(), mechanics.getCreateDate(),mechanics.getCapacity(),model.getMaterialType(),model.getBasePriceType(),model.getUnitPrice()));
			
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return models;
	}
	public PageBeanModel<MechanicModel> getSkipList(String database, int project){
		Utils.setDatabase(database);
		PageHelper.orderBy("id DESC");
		List<Mechanics> list = mechanicsMapper.selectSkips(project);
		PageBean<Mechanics> temp = new PageBean<Mechanics>(list);
		List<MechanicModel> models = new ArrayList<MechanicModel>();
		for (Mechanics mechanics : list) {
			SkipMaterialPriceExample example = new SkipMaterialPriceExample();
			example.createCriteria().andProjectEqualTo(project).andMechaincEqualTo(mechanics.getId());
			Foreman foreman = getForemanById(database, skipMaterialPriceMapper.selectByExample(example).get(0).getForeman());
			models.add(new MechanicModel(mechanics.getId(), foreman.getId(), mechanics.getPlateNumber(),
					foreman.getName(), foreman.getMobile(), foreman.getIdcard(), mechanics.getDriverName(),
					mechanics.getDriverMobile(), mechanics.getDriverIdcard(), mechanics.getCreateDate(),mechanics.getCapacity()));
		}
		PageBeanModel<MechanicModel> page = new PageBeanModel<MechanicModel>(temp.getTotal(), models);
		return page;
		
	}
//	public PageBeanModel<MechanicModel> getSkipList(String database, int project) {
//		Utils.setDatabase(database);
//		SkipMaterialPriceExample example = new SkipMaterialPriceExample();
//		example.createCriteria().andProjectEqualTo(project);
//		PageHelper.orderBy("id DESC");
//		List<SkipMaterialPrice> list = skipMaterialPriceMapper.selectByExampleGroupBySkip(example);
//		SkipMaterialPrice sp = new SkipMaterialPrice();
//		sp.setProject(project);
//		long total = skipMaterialPriceMapper.countByExample(sp,null);
//		List<MechanicModel> models = new ArrayList<MechanicModel>();
//		for (SkipMaterialPrice skipMaterialPrice : list) {
//			try {
//				Foreman foreman = getForemanById(database, skipMaterialPrice.getForeman());
//				Mechanics mechanics = getMechanicById(database, skipMaterialPrice.getMechainc(), 2);
//				models.add(new MechanicModel(mechanics.getId(), foreman.getId(), mechanics.getPlateNumber(),
//						foreman.getName(), foreman.getMobile(), foreman.getIdcard(), mechanics.getDriverName(),
//						mechanics.getDriverMobile(), mechanics.getDriverIdcard(), mechanics.getCreateDate(),mechanics.getCapacity()));
//			} catch (Exception e) {
//			}
//		}
//		PageBeanModel<MechanicModel> page = new PageBeanModel<MechanicModel>(total, models);
//		return page;
//	}
	
	public List<SkipMaterialPriceModel>  showMaterialPrices(String database, int project, int foreman, int skip) {
		Utils.setDatabase(database);
		SkipMaterialPriceExample example = new SkipMaterialPriceExample();
		example.createCriteria().andProjectEqualTo(project).andForemanEqualTo(foreman).andMechaincEqualTo(skip);
		List<SkipMaterialPrice> list = skipMaterialPriceMapper.selectByExample(example);
		List<SkipMaterialPriceModel> models =new ArrayList<SkipMaterialPriceModel>();
		for (SkipMaterialPrice skipMaterialPrice : list) {
			
			models.add(new SkipMaterialPriceModel(materialTypeMapper.selectByPrimaryKey(database, skipMaterialPrice.getMaterialType()).getMtName(),
					basePriceTypeMapper.selectByPrimaryKey(database, skipMaterialPrice.getBasePriceType()).getName(), 
					priceFluctuationMapper.selectByPrimaryKey(database, skipMaterialPrice.getUnitPrice()).getPrice()));
		}
		return models;
	}
	public int addMechanicPrice(String database,int project,int mechanic,int basePriceType,int baseType,int price) {
		Utils.setDatabase(database);
		
		
		UnitTypeCombinationExample utcExample  = new UnitTypeCombinationExample();
		UnitTypeCombinationExample.Criteria criteria = utcExample.createCriteria();
		criteria.andBasePriceTypeEqualTo(basePriceType);
		criteria.andBaseTypeEqualTo(baseType);
		List<UnitTypeCombination> utclist = unitTypeCombinationMapper.selectByExample(utcExample);
		if(utclist.size()>0) {
			List<Integer> tempIds = new ArrayList<>();
			for (UnitTypeCombination temp : utclist) {
				tempIds.add(temp.getId());
			}	
			MechanicsProjectOwnerExample mExample = new MechanicsProjectOwnerExample();
			mExample.createCriteria().andProjectEqualTo(project).andMechanicsEqualTo(mechanic).andUnitPriceTypeIn(tempIds);
			int size = mechanicsProjectOwnerMapper.selectByExample(mExample).size();
			if(size>0) {
				return -1;
			}
		}
		
		UnitTypeCombination utc = new UnitTypeCombination();
		utc.setBasePriceType(basePriceType);
		utc.setBaseType(baseType);
		unitTypeCombinationMapper.insertSelective(utc);

		PriceFluctuation pf = new PriceFluctuation();
		pf.setCreateDate(Utils.getCurrentTime());
		pf.setStartDate(Utils.getCurrentDate());
		pf.setType(2);
		pf.setPrice(price);
		priceFluctuationMapper.insertSelective(pf);

		MechanicsProjectOwner mpo = new MechanicsProjectOwner();
		mpo.setMechanics(mechanic);
		//mpo.setOwner(foreman.getId());
		mpo.setProject(project);
		mpo.setUnitPriceType(utc.getId());
		mpo.setUnitPrice(pf.getId());
		mpo.setType(1);
		int result = mechanicsProjectOwnerMapper.insertSelective(mpo);

		if (result==1) {
			return mpo.getId();
		}
		return 0;
	}
	
public int updateMechanicPrice(String database,int project,int mechanic,int baseType,int basePriceType,int price,int id) {
		Utils.setDatabase(database);
		
		UnitTypeCombinationExample utcExample  = new UnitTypeCombinationExample();
		UnitTypeCombinationExample.Criteria criteria = utcExample.createCriteria();
		criteria.andBasePriceTypeEqualTo(basePriceType);
		criteria.andBaseTypeEqualTo(baseType);
		List<UnitTypeCombination> utclist = unitTypeCombinationMapper.selectByExample(utcExample);
		if(utclist.size()>0) {
			List<Integer> tempIds = new ArrayList<>();
			for (UnitTypeCombination temp : utclist) {
				tempIds.add(temp.getId());
			}	
			MechanicsProjectOwnerExample mExample = new MechanicsProjectOwnerExample();
			mExample.createCriteria().andProjectEqualTo(project).andMechanicsEqualTo(mechanic).andUnitPriceTypeIn(tempIds);
			List<MechanicsProjectOwner> list = mechanicsProjectOwnerMapper.selectByExample(mExample);
			if(list.size()>0&&list.get(0).getId()!=id) {
				return -1;
			}
		}
		
		
		UnitTypeCombination utc = new UnitTypeCombination();
		utc.setBasePriceType(basePriceType);
		utc.setBaseType(baseType);
		unitTypeCombinationMapper.insertSelective(utc);

		PriceFluctuation pf = new PriceFluctuation();
		pf.setCreateDate(Utils.getCurrentTime());
		pf.setStartDate(Utils.getCurrentDate());
		pf.setType(2);
		pf.setPrice(price);
		priceFluctuationMapper.insertSelective(pf);

		MechanicsProjectOwner mpo = new MechanicsProjectOwner();
		mpo.setMechanics(mechanic);
		mpo.setProject(project);
		mpo.setUnitPriceType(utc.getId());
		mpo.setUnitPrice(pf.getId());
		mpo.setType(1);
		mpo.setId(id);
		Utils.setDatabase(database);
		
		MechanicsProjectOwnerExample example = new MechanicsProjectOwnerExample();
		example.createCriteria().andIdEqualTo(id);
		int result = mechanicsProjectOwnerMapper.updateByExampleSelective(mpo, example);
		return result;
	}

	public boolean deleteMechanicPrice(String database,int id) {
		int result =mechanicsProjectOwnerMapper.deleteByPrimaryKey(database,id);
		return result == 1 ? true : false;
	}
	public List<MechanicPriceModel> getMechanicPrice(String database,int project,int mechanic,boolean show){
		Utils.setDatabase(database);
		MechanicsProjectOwnerExample example = new MechanicsProjectOwnerExample();
		example.createCriteria().andProjectEqualTo(project).andMechanicsEqualTo(mechanic);
		
		List<MechanicsProjectOwner>  list = mechanicsProjectOwnerMapper.selectByExample(example);
		
		List<MechanicPriceModel> models = new ArrayList<>();
		if(show) {
			for (MechanicsProjectOwner mpo : list) {
				PriceFluctuation pf = getPriceFluctuation(database, mpo.getUnitPrice());
				UnitTypeCombination utc = getUnitTypeCombination(database, mpo.getUnitPriceType());
				String baseTypeName = baseTypeMapper.selectByPrimaryKey(database, utc.getBaseType()).getName();
				String basePriceName = basePriceTypeMapper.selectByPrimaryKey(database, utc.getBasePriceType()).getName();
				MechanicPriceModel model = new MechanicPriceModel(mpo.getId(),utc.getBaseType(), utc.getBasePriceType(), pf.getPrice(),baseTypeName,basePriceName);
				models.add(model);
			}
		}else {
			for (MechanicsProjectOwner mpo : list) {
				PriceFluctuation pf = getPriceFluctuation(database, mpo.getUnitPrice());
				UnitTypeCombination utc = getUnitTypeCombination(database, mpo.getUnitPriceType());
				MechanicPriceModel model = new MechanicPriceModel(mpo.getId(),utc.getBaseType(), utc.getBasePriceType(), pf.getPrice());
				models.add(model);
			}
		}
		return models;
	}
}
