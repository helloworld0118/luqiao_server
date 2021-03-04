package com.core.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.entity.BaseType;
import com.core.entity.Foreman;
import com.core.entity.Material;
import com.core.entity.MaterialExample;
import com.core.entity.MaterialSpec;
import com.core.entity.MaterialSpecExample;
import com.core.entity.MaterialType;
import com.core.entity.PriceFluctuation;
import com.core.entity.ProjectSupplier;
import com.core.entity.ProjectSupplierExample;
import com.core.entity.SkipMaterialPrice;
import com.core.entity.SkipMaterialPriceExample;
import com.core.entity.Supplier;
import com.core.entity.SupplierExample;
import com.core.entity.UnitTypeCombination;
import com.core.entity.UnitTypeCombinationExample;
import com.core.mapper.MaterialMapper;
import com.core.mapper.MaterialSpecMapper;
import com.core.mapper.MaterialTypeMapper;
import com.core.mapper.ProjectSupplierMapper;
import com.core.mapper.SkipMaterialPriceMapper;
import com.core.mapper.SupplierMapper;
import com.core.util.PageBean;
import com.core.util.PageBeanModel;
import com.core.util.Utils;
import com.core.util.model.MaterialModel;
import com.core.util.model.MechanicModel;
import com.core.util.model.api.MaterialPriceModel;
import com.github.pagehelper.PageHelper;

@Service
public class SupplierService extends UnitService {

	@Autowired
	private SupplierMapper supplierMapper;
	
	@Autowired
	private ProjectSupplierMapper projectSupplierMapper;

	@Autowired
	private MaterialMapper materialMapper;
	
	@Autowired
	private MaterialTypeMapper materialTypeMapper;
	
	@Autowired
	private MaterialSpecMapper materialSpecMapper;

	@Autowired
	private SkipMaterialPriceMapper skipMaterialPriceMapper;
	
//	public boolean addRelationship(String database, Supplier supplier,int project) {
//		Utils.setDatabase(database);
//		int result =supplierMapper.updateByPrimaryKeySelective(supplier);
//		ProjectSupplierExample example = new ProjectSupplierExample();
//		example.createCriteria().andProjectEqualTo(project).andSupplierEqualTo(supplier.getId());
//		projectSupplierMapper.selectByExample(example);
//		ProjectSupplier ps = new ProjectSupplier(); 
//		ps.setProject(project);
//		ps.setSupplier(supplier.getId());
//		projectSupplierMapper.insertSelective(ps);
//		return result==1?true:false;
//	}
//	
	public boolean addSupplier(String database, Supplier supplier,int project) {
		Utils.setDatabase(database);
		int result =supplierMapper.insertSelective(supplier);
		ProjectSupplier ps = new ProjectSupplier(); 
//		ps.setProject(project);
//		ps.setSupplier(supplier.getId());
//		projectSupplierMapper.insertSelective(ps);
		return result==1?true:false;
	}
	
	public boolean updateSupplier(String database, Supplier supplier) {
		Utils.setDatabase(database);
		int result =supplierMapper.updateByPrimaryKeySelective(supplier);
		return result==1?true:false;
	}
	
	public boolean deleteSupplier(String database, int supplier) {
		//Utils.setDatabase(database);
		int result =supplierMapper.deleteByPrimaryKey(database,supplier);
		return result==1?true:false;
	}
	public Supplier getSupplierByKey(String database, int id) {
		return supplierMapper.selectByPrimaryKey(database,id);
	}
	public List<Supplier> getSupplierList(String database,String name) {
		Utils.setDatabase(database);
		SupplierExample sExample = new  SupplierExample();
		sExample.createCriteria().andNameLike("%"+name+"%");
		return supplierMapper.selectByExample(sExample);
	}
	
	
	public List<Supplier> getOilSupplierList(String database,int project) {
		Utils.setDatabase(database);
		UnitTypeCombinationExample utcExample = new UnitTypeCombinationExample();
		
		List<Integer> oilIds = new ArrayList<>();
		oilIds.add(13);
		oilIds.add(14);
		utcExample.createCriteria().andMaterialTypeIn(oilIds);
		List<UnitTypeCombination> utcList = unitTypeCombinationMapper.selectByExample(utcExample);
		List<Integer> utcIds = new ArrayList<>();
		for (UnitTypeCombination unitTypeCombination : utcList) {
			utcIds.add(unitTypeCombination.getId());
		}
		if(utcIds.size()==0) {
			return new ArrayList<Supplier>();
		}
		MaterialExample example = new MaterialExample();
		example.createCriteria().andProjectEqualTo(project).andUnitPriceTypeIn(utcIds);
		List<Material> mlist = materialMapper.selectByExample(example);
		
		List<Integer> sid =new ArrayList<Integer>();
		for (Material entity : mlist) {
			sid.add(entity.getSupplier());
		}
		SupplierExample sExample = new  SupplierExample();
		sExample.createCriteria().andIdIn(sid);
		sExample.setDistinct(true);
		return supplierMapper.selectByExample(sExample);
	}
	
	public List<Supplier> getSupplierList(String database,int project) {
		Utils.setDatabase(database);
//		ProjectSupplierExample psExample=new ProjectSupplierExample();
//		psExample.createCriteria().andProjectEqualTo(project);
//		List<ProjectSupplier> pslist = projectSupplierMapper.selectByExample(psExample);
		MaterialExample example = new MaterialExample();
		example.createCriteria().andProjectEqualTo(project);
		List<Material> mlist = materialMapper.selectByExample(example);
		
		
		List<Integer> sid =new ArrayList<Integer>();
		for (Material entity : mlist) {
			sid.add(entity.getSupplier());
		}
		if(sid.size()==0) {
			return new ArrayList<Supplier>();
		}
		SupplierExample sExample = new  SupplierExample();
		sExample.createCriteria().andIdIn(sid);
		sExample.setDistinct(true);
		return supplierMapper.selectByExample(sExample);
	}
	public boolean deleteMaterial(String database,int project,int supplier,int material) {
		int result =materialMapper.deleteByPrimaryKey(database,material);
		MaterialExample example = new MaterialExample();
		example.createCriteria().andSupplierEqualTo(supplier).andProjectEqualTo(project);
//		if(materialMapper.selectByExample(example).size()==0) {
//			ProjectSupplierExample psExample = new ProjectSupplierExample();
//			psExample.createCriteria().andProjectEqualTo(project).andSupplierEqualTo(supplier);
//			projectSupplierMapper.deleteByExample(psExample);
//		}
		return result==1?true:false;
	}
	public List<com.core.util.model.api.MaterialModel> getMaterialList(String database,int project,int supplier){
		Utils.setDatabase(database);
		MaterialExample example = new MaterialExample();
		example.createCriteria().andProjectEqualTo(project).andSupplierEqualTo(supplier);
		List<Material> mlist = materialMapper.selectByExample(example);
		List<com.core.util.model.api.MaterialModel> models =new ArrayList<com.core.util.model.api.MaterialModel>();
		Map<String,List<MaterialPriceModel>> materialPriceMap = new HashMap<>();
		Map<String,com.core.util.model.api.MaterialModel> materialMap = new HashMap<>();
		for (Material material : mlist) {
			List<MaterialPriceModel> list = null;
			Supplier sp = supplierMapper.selectByPrimaryKey(database, material.getSupplier());
		    UnitTypeCombination utc=unitTypeCombinationMapper.selectByPrimaryKey(database, material.getUnitPriceType());
		    MaterialType materialType=materialTypeMapper.selectByPrimaryKey(database,utc.getMaterialType());
		    if(materialMap.containsKey(materialType.getMtName())) {
		    	list = materialPriceMap.get(materialType.getMtName());
			}else {
				list = new ArrayList<>();
				materialPriceMap.put(materialType.getMtName(), list);
			    com.core.util.model.api.MaterialModel  model = new com.core.util.model.api.MaterialModel(material.getId(),materialType.getMtName(),sp.getId());
			    materialMap.put(materialType.getMtName(), model);
			}
		    PriceFluctuation pf = priceFluctuationMapper.selectByPrimaryKey(database, material.getUnitPrice());
		    BaseType bt = baseTypeMapper.selectByPrimaryKey(database, utc.getBaseType());
		    
		    String materialSpecName="默认";
		    int materialSpec = 0;
		    if(null!=utc.getMaterialSpec()&&0!=utc.getMaterialSpec()) {
		    	MaterialSpec tempMs = materialSpecMapper.selectByPrimaryKey(database,utc.getMaterialSpec());
		    	materialSpecName=tempMs.getMsName();
		    	materialSpec=tempMs.getId();
		    }
		    list.add(new MaterialPriceModel(bt.getId(), bt.getName(), materialSpecName, materialSpec, pf.getPrice()));
		}
		
		for (String key : materialMap.keySet()) {
			com.core.util.model.api.MaterialModel model = materialMap.get(key);
			model.getList().addAll(materialPriceMap.get(key));
		}
		return models;
	}	
	public List<MaterialModel> getOil4Api(String database,int project,int supplier){
		Utils.setDatabase(database);
		MaterialExample example = new MaterialExample();
		example.createCriteria().andProjectEqualTo(project).andSupplierEqualTo(supplier);
		List<Material> mlist = materialMapper.selectByExample(example);
		List<MaterialModel> models =new ArrayList<MaterialModel>();
		for (Material material : mlist) {
			Supplier sp = supplierMapper.selectByPrimaryKey(database, material.getSupplier());
		    UnitTypeCombination utc=unitTypeCombinationMapper.selectByPrimaryKey(database, material.getUnitPriceType());
		    if(utc.getMaterialType()!=13&&utc.getMaterialType()!=14) continue;
		    PriceFluctuation pf = priceFluctuationMapper.selectByPrimaryKey(database, material.getUnitPrice());
		    MaterialType materialType=materialTypeMapper.selectByPrimaryKey(database,utc.getMaterialType());
		    BaseType bt = baseTypeMapper.selectByPrimaryKey(database, utc.getBaseType());
		    String materialSpecName="默认";
		    int materialSpec = 0;
		    if(null!=utc.getMaterialSpec()&&0!=utc.getMaterialSpec()) {
		    	MaterialSpec tempMs = materialSpecMapper.selectByPrimaryKey(database,utc.getMaterialSpec());
		    	materialSpecName=tempMs.getMsName();
		    	materialSpec=tempMs.getId();
		    }
			models.add(new MaterialModel(material.getId(),sp.getId(),materialType.getId(),sp.getName(), sp.getMobile(), materialType.getMtName(), 
					bt.getId(), bt.getName(), material.getCreateDate(),material.getmAddress(),
					pf.getPrice(),pf.getStartDate(),materialSpecName,materialSpec));
		}
		return models;
	}
	
	public List<MaterialModel> getMaterial4Api(String database,int project,int supplier){
		Utils.setDatabase(database);
		MaterialExample example = new MaterialExample();
		example.createCriteria().andProjectEqualTo(project).andSupplierEqualTo(supplier);
		List<Material> mlist = materialMapper.selectByExample(example);
		List<MaterialModel> models =new ArrayList<MaterialModel>();
		for (Material material : mlist) {
			Supplier sp = supplierMapper.selectByPrimaryKey(database, material.getSupplier());
		    UnitTypeCombination utc=unitTypeCombinationMapper.selectByPrimaryKey(database, material.getUnitPriceType());
		    PriceFluctuation pf = priceFluctuationMapper.selectByPrimaryKey(database, material.getUnitPrice());
		    MaterialType materialType=materialTypeMapper.selectByPrimaryKey(database,utc.getMaterialType());
		    BaseType bt = baseTypeMapper.selectByPrimaryKey(database, utc.getBaseType());
		    String materialSpecName="默认";
		    int materialSpec = 0;
		    if(null!=utc.getMaterialSpec()&&0!=utc.getMaterialSpec()) {
		    	MaterialSpec tempMs = materialSpecMapper.selectByPrimaryKey(database,utc.getMaterialSpec());
		    	materialSpecName=tempMs.getMsName();
		    	materialSpec=tempMs.getId();
		    }
			models.add(new MaterialModel(material.getId(),sp.getId(),materialType.getId(),sp.getName(), sp.getMobile(), materialType.getMtName(), 
					bt.getId(), bt.getName(), material.getCreateDate(),material.getmAddress(),
					pf.getPrice(),pf.getStartDate(),materialSpecName,materialSpec));
		}
		return models;
	}
	
	public PageBeanModel<MaterialModel> getMaterialList(String database,int project){
		Utils.setDatabase(database);
		PageHelper.orderBy("id DESC");
		List<Material> mlist = materialMapper.selectBySql(project);
		PageBean<Material> temp =new PageBean<Material>(mlist);
		List<MaterialModel> models =new ArrayList<MaterialModel>();
		for (Material material : mlist) {
			Supplier sp = supplierMapper.selectByPrimaryKey(database, material.getSupplier());
		    UnitTypeCombination utc=unitTypeCombinationMapper.selectByPrimaryKey(database, material.getUnitPriceType());
		    PriceFluctuation pf = priceFluctuationMapper.selectByPrimaryKey(database, material.getUnitPrice());
		    MaterialType materialType=materialTypeMapper.selectByPrimaryKey(database,utc.getMaterialType());
		    BaseType bt = baseTypeMapper.selectByPrimaryKey(database, utc.getBaseType());
		    String materialSpecName="默认";
		    int materialSpec = 0;
		    if(null!=utc.getMaterialSpec()&&0!=utc.getMaterialSpec()) {
		    	MaterialSpec tempMs = materialSpecMapper.selectByPrimaryKey(database,utc.getMaterialSpec());
		    	materialSpecName=tempMs.getMsName();
		    	materialSpec=tempMs.getId();
		    }
			models.add(new MaterialModel(material.getId(),sp.getId(),materialType.getId(),sp.getName(), sp.getMobile(), materialType.getMtName(), 
					bt.getId(), bt.getName(), material.getCreateDate(),material.getmAddress(),
					pf.getPrice(),pf.getStartDate(),materialSpecName,materialSpec));
		}
		PageBeanModel<MaterialModel> page =new PageBeanModel<>(temp.getTotal(), models);
		return page;
	}
	public List<MaterialType> getMaterialTypeList(String database){
		Utils.setDatabase(database);
		return materialTypeMapper.selectByExample(null);
	}
	
	public List<MaterialSpec> getMaterialSpecList(String database,int mt){
		Utils.setDatabase(database);
		MaterialSpecExample example = new MaterialSpecExample();
		example.createCriteria().andMaterialTypeEqualTo(mt);
		return materialSpecMapper.selectByExample(example);
	}
	
	public boolean updateMaterial(String database,int temp_id,String address,int supplier) {
		MaterialExample example =new MaterialExample();
		example.createCriteria().andSupplierEqualTo(temp_id);
		Material material =new Material();
		material.setmAddress(address);
		material.setSupplier(supplier);
		materialMapper.updateByExampleSelective(material, example);
		return true;
	}
	
	public int updateMaterial(String database,int project,int supplier,int mid,int materialType,int price,String startDate,
			int baseType,int materialSpec,String mAddress) {
		Utils.setDatabase(database);
		
		UnitTypeCombinationExample utcExample  = new UnitTypeCombinationExample();
		UnitTypeCombinationExample.Criteria criteria = utcExample.createCriteria();
		criteria.andMaterialTypeEqualTo(materialType);
		criteria.andBaseTypeEqualTo(baseType);
		if(0!=materialSpec) {
			criteria.andMaterialSpecEqualTo(materialSpec);
		}
		List<UnitTypeCombination> utclist = unitTypeCombinationMapper.selectByExample(utcExample);
		if(utclist.size()>0) {
			
			List<Integer> tempIds = new ArrayList<>();
			for (UnitTypeCombination temp : utclist) {
				tempIds.add(temp.getId());
			}	
			MaterialExample mExample = new MaterialExample();
			mExample.createCriteria().andProjectEqualTo(project).andSupplierEqualTo(supplier).andUnitPriceTypeIn(tempIds);
			List<Material>  list= materialMapper.selectByExample(mExample);
			if(list.size()>0&&list.get(0).getId()!=mid) {
				return -1;
			}
		}
		PriceFluctuation pf =new PriceFluctuation();
		pf.setCreateDate(Utils.getCurrentTime());
		pf.setPrice(price);
		pf.setType(1);
		pf.setStartDate(startDate);
		priceFluctuationMapper.insertSelective(pf);
		
		UnitTypeCombination utc =new UnitTypeCombination();
		utc.setBaseType(baseType);
		if(0!=materialSpec) {
			utc.setMaterialSpec(materialSpec);
		}
		utc.setMaterialType(materialType);
		unitTypeCombinationMapper.insertSelective(utc);
		
		Material material =new Material();
		material.setId(mid);
		material.setProject(project);
		material.setSupplier(supplier);
		material.setmAddress(mAddress);
		//material.setType(type);
		//material.setCreateDate(Utils.getCurrentTime());
		material.setUpdateDate(Utils.getCurrentTime());
		material.setUnitPrice(pf.getId());
		material.setUnitPriceType(utc.getId());
		int result = materialMapper.updateByPrimaryKeySelective(material);
		
		
		return result;
	}
	
	public int addMaterial(String database,int project,int supplier,int materialType, int type,int price,String startDate,
			int baseType,int materialSpec,String mAddress) {
		Utils.setDatabase(database);
		UnitTypeCombinationExample utcExample  = new UnitTypeCombinationExample();
		UnitTypeCombinationExample.Criteria criteria = utcExample.createCriteria();
		criteria.andMaterialTypeEqualTo(materialType);
		criteria.andBaseTypeEqualTo(baseType);
		if(0!=materialSpec) {
			criteria.andMaterialSpecEqualTo(materialSpec);
		}
		List<UnitTypeCombination> utclist = unitTypeCombinationMapper.selectByExample(utcExample);
		if(utclist.size()>0) {
			
			List<Integer> tempIds = new ArrayList<>();
			for (UnitTypeCombination temp : utclist) {
				tempIds.add(temp.getId());
			}	
			MaterialExample mExample = new MaterialExample();
			mExample.createCriteria().andProjectEqualTo(project).andSupplierEqualTo(supplier).andUnitPriceTypeIn(tempIds);
			int size = materialMapper.selectByExample(mExample).size();
			if(size>0) {
				return -1;
			}
		}
		
		PriceFluctuation pf =new PriceFluctuation();
		pf.setCreateDate(Utils.getCurrentTime());
		pf.setPrice(price);
		pf.setType(1);
		pf.setStartDate(startDate);
		priceFluctuationMapper.insertSelective(pf);
		
		UnitTypeCombination utc =new UnitTypeCombination();
		utc.setBaseType(baseType);
		if(0!=materialSpec) {
			utc.setMaterialSpec(materialSpec);
		}
		utc.setMaterialType(materialType);
		unitTypeCombinationMapper.insertSelective(utc);
		
		Material material =new Material();
		material.setProject(project);
		material.setSupplier(supplier);
		material.setType(type);
		material.setmAddress(mAddress);
		material.setCreateDate(Utils.getCurrentTime());
		material.setUnitPrice(pf.getId());
		material.setUnitPriceType(utc.getId());
		materialMapper.insertSelective(material);
		
		return material.getId();
	}
	
	
	public int addSkipPriceInfo(String database,SkipMaterialPrice smpinfo, int price) {
		Utils.setDatabase(database);
		
		SkipMaterialPriceExample smpExample = new SkipMaterialPriceExample();
		smpExample.createCriteria().andMechaincEqualTo(smpinfo.getMechainc()).
		andMaterialTypeEqualTo(smpinfo.getMaterialType()).
		andProjectEqualTo(smpinfo.getProject()).
		andBasePriceTypeEqualTo(smpinfo.getBasePriceType());
		int size = skipMaterialPriceMapper.selectByExample(smpExample).size();
		if(size>0) {
			return -1;
		}
		
		
		PriceFluctuation pf = new PriceFluctuation();
		pf.setPrice(price);
		pf.setCreateDate(Utils.getCurrentTime());
		pf.setStartDate(Utils.getCurrentTime());
		pf.setType(3);
		priceFluctuationMapper.insertSelective(pf);
		
		smpinfo.setUnitPrice(pf.getId());
		
		int result = skipMaterialPriceMapper.insertSelective(smpinfo);
		return result;
	}
	
	public int updateSkipPriceInfo(String database,SkipMaterialPrice smpinfo, int price) {
		Utils.setDatabase(database);
		
		SkipMaterialPriceExample smpExample = new SkipMaterialPriceExample();
		smpExample.createCriteria().andMechaincEqualTo(smpinfo.getMechainc()).
		andMaterialTypeEqualTo(smpinfo.getMaterialType()).
		andProjectEqualTo(smpinfo.getProject()).
		andBasePriceTypeEqualTo(smpinfo.getBasePriceType());
		List<SkipMaterialPrice> list= skipMaterialPriceMapper.selectByExample(smpExample);
		if(list.size()>0&&list.get(0).getId()!=smpinfo.getId()) {
			return -1;
		}
		
		PriceFluctuation pf = new PriceFluctuation();
		pf.setPrice(price);
		pf.setCreateDate(Utils.getCurrentTime());
		pf.setStartDate(Utils.getCurrentTime());
		pf.setType(3);
		priceFluctuationMapper.insertSelective(pf);
		
		smpinfo.setUnitPrice(pf.getId());
		
		int result = skipMaterialPriceMapper.updateByPrimaryKeySelective(smpinfo);
		return result;
	}
	
	public boolean deleteSkipPriceInfo(String database,int smpid) {
		Utils.setDatabase(database);
		int result = skipMaterialPriceMapper.deleteByPrimaryKey(database,smpid);
		return result ==1?true:false;
	}
	public List<SkipMaterialPrice> getSkipPriceList(String database,int project,int skip) {
		Utils.setDatabase(database);
		SkipMaterialPriceExample example = new SkipMaterialPriceExample();
		example.createCriteria().andProjectEqualTo(project).andMechaincEqualTo(skip);
		List<SkipMaterialPrice> list = skipMaterialPriceMapper.selectByExample(example);
		for (SkipMaterialPrice skipMaterialPrice : list) {
			PriceFluctuation pf = getPriceFluctuation(database, skipMaterialPrice.getUnitPrice());
			skipMaterialPrice.setUnitPrice(pf.getPrice());
		}
		return list;
	}
}
