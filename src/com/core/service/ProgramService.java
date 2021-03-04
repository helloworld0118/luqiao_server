package com.core.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.entity.Gaibanhan;
import com.core.entity.GaibanhanExample;
import com.core.entity.LaborCost;
import com.core.entity.LaborCostExample;
import com.core.entity.MaterialReceived;
import com.core.entity.MaterialReceivedExample;
import com.core.entity.MechanicsPrice;
import com.core.entity.MechanicsPriceExample;
import com.core.entity.Oil;
import com.core.entity.OilExample;
import com.core.entity.Xianghan;
import com.core.entity.XianghanExample;
import com.core.entity.Yuanguanhan;
import com.core.entity.YuanguanhanExample;
import com.core.mapper.GaibanhanMapper;
import com.core.mapper.LaborCostMapper;
import com.core.mapper.MaterialReceivedMapper;
import com.core.mapper.MechanicsPriceMapper;
import com.core.mapper.OilMapper;
import com.core.mapper.XianghanMapper;
import com.core.mapper.YuanguanhanMapper;
import com.core.util.BarDataModelComparator;
import com.core.util.BarNameModelComparator;
import com.core.util.PageBean;
import com.core.util.Utils;
import com.core.util.model.IndexPriceModel;
import com.core.util.model.api.NodeModel;
import com.core.util.model.chart.BarDataModel;
import com.core.util.model.chart.BarModel;
import com.core.util.model.chart.MaterialChartModel;
import com.core.util.model.chart.PieDataModel;
import com.core.util.model.chart.Tooltip;
import com.google.gson.Gson;

@Service
public class ProgramService {

	@Autowired
	private YuanguanhanMapper yuanguanhanMapper;

	@Autowired
	private GaibanhanMapper gaibanhanMapper;
	
	@Autowired
	private XianghanMapper xianghanMapper;
	
	@Autowired
	private LaborCostMapper laborCostMapper;
	
	@Autowired
	private MaterialReceivedMapper materialReceivedMapper;
	
	@Autowired
	private MechanicsPriceMapper mechanicsPriceMapper;
	
	@Autowired
	private OilMapper oilMapper;
	
	public boolean addYuanguanhan(String database, Yuanguanhan yuanguanhan) {
		Utils.setDatabase(database);
		int result = yuanguanhanMapper.insertSelective(yuanguanhan);
		return result == 1 ? true : false;
	}
	public List<NodeModel> getNodes(String database,String date,int type){
		Utils.setDatabase(database);
		List<NodeModel> list = new ArrayList<NodeModel>();
		if(type==1||type==0) {
			YuanguanhanExample yghExample = new YuanguanhanExample();
			if(null!=date&&!"".equals(date)) {
				yghExample.createCriteria().andCreateDateEqualTo(date);
			}
			for(Yuanguanhan entity:yuanguanhanMapper.selectByExample(yghExample)) {
				NodeModel node = new NodeModel("K"+entity.getNode()+"+"+entity.getChildnode(), "圆管涵", entity.getCreateDate());
				list.add(node);
			}
		}
		if(type==2||type==0) {
			GaibanhanExample gbhExample = new GaibanhanExample();
			if(null!=date&&!"".equals(date)) {
				gbhExample.createCriteria().andCreateDateEqualTo(date);
			}
			for(Gaibanhan entity:gaibanhanMapper.selectByExample(gbhExample)) {
				NodeModel node = new NodeModel("K"+entity.getNode()+"+"+entity.getChildnode(), "盖板涵", entity.getCreateDate());
				list.add(node);
			}
		}
		if(type==3||type==0) {
			XianghanExample xhExample = new XianghanExample();
			if(null!=date&&!"".equals(date)) {
				xhExample.createCriteria().andCreateDateEqualTo(date);
			}
			for(Xianghan entity:xianghanMapper.selectByExample(xhExample)) {
				NodeModel node = new NodeModel("K"+entity.getNode()+"+"+entity.getChildnode(), "箱涵", entity.getCreateDate());
				list.add(node);
			}
		}
		
		return list;
	}
	
	public boolean addGaibanhan(String database, Gaibanhan gaibanhan) {
		Utils.setDatabase(database);
		int result = gaibanhanMapper.insertSelective(gaibanhan);
		return result == 1 ? true : false;
	}

	public boolean addXianghan(String database, Xianghan xianghan) {
		Utils.setDatabase(database);
		int result = xianghanMapper.insertSelective(xianghan);
		return result == 1 ? true : false;
	}
	
	public boolean updateYuanguanhan(String database, Yuanguanhan yuanguanhan) {
		Utils.setDatabase(database);
		int result = yuanguanhanMapper.updateByPrimaryKeySelective(yuanguanhan);
		return result == 1 ? true : false;
	}
	public boolean updateGaibanhan(String database, Gaibanhan gaibanhan) {
		Utils.setDatabase(database);
		int result = gaibanhanMapper.updateByPrimaryKeySelective(gaibanhan);
		return result == 1 ? true : false;
	}
	
	public boolean updateXianghan(String database, Xianghan xianghan) {
		Utils.setDatabase(database);
		int result = xianghanMapper.updateByPrimaryKeySelective(xianghan);
		return result == 1 ? true : false;
	}
	
	public List<Yuanguanhan> yuanguanhanList(String database, int project) {
		Utils.setDatabase(database);
		YuanguanhanExample example = new YuanguanhanExample();
		example.createCriteria().andProjectEqualTo(project);
		return yuanguanhanMapper.selectByExample(example);
	}
	public List<Gaibanhan> gaibanhanList(String database, int project) {
		Utils.setDatabase(database);
		GaibanhanExample example = new GaibanhanExample();
		example.createCriteria().andProjectEqualTo(project);
		return gaibanhanMapper.selectByExample(example);
	}
	public List<Xianghan> xianghanList(String database, int project) {
		Utils.setDatabase(database);
		XianghanExample example = new XianghanExample();
		example.createCriteria().andProjectEqualTo(project);
		return xianghanMapper.selectByExample(example);
	}
	
	
	public boolean addLabor(String database,LaborCost entity) {
		Utils.setDatabase(database);
		int result = laborCostMapper.insertSelective(entity);
		return result==0?false:true;
	}

	public boolean addMaterialReceived(String database,MaterialReceived entity) {
		Utils.setDatabase(database);
		int result = materialReceivedMapper.insertSelective(entity);
		return result==0?false:true;
	}
	
	public boolean addMechanicsPrice(String database,MechanicsPrice entity) {
		Utils.setDatabase(database);
		int result = mechanicsPriceMapper.insertSelective(entity);
		return result==0?false:true;
	}
	
	
	private IndexPriceModel<LaborCost> getTodayLaborInfo(String database,int project) {
		String date = Utils.getCurrentDate();
		List<LaborCost> list = getLaborInfo(database, project, date);
		int price = 0;
		int nums = 0;
		for (LaborCost entity : list) {
			price += entity.getPrice();
			nums += entity.getWorkerNums();
		}
		IndexPriceModel<LaborCost> model = new IndexPriceModel<LaborCost>(price, nums);
		model.setList(list);
		return model;
	}
	
	private IndexPriceModel<LaborCost> getAlldayLaborInfo(String database,int project) {
		List<LaborCost> list = getLaborInfo(database, project,null);
		int price = 0;
		int nums = 0;
		for (LaborCost entity : list) {
			price += entity.getPrice();
			nums += entity.getWorkerNums();
		}
		IndexPriceModel<LaborCost> model = new IndexPriceModel<LaborCost>(price, nums);
		model.setList(list);
		return model;
	}
	private IndexPriceModel<MaterialReceived> getAlldayMaterialInfo(String database,int project) {
		List<MaterialReceived> list = getMaterialInfo(database, project,null);
		int price = 0;
		int nums = 0;
		for (MaterialReceived entity : list) {
			price += entity.getMaterialPrice()+entity.getFreightPrice();
			nums += 1;
		}
		IndexPriceModel<MaterialReceived> model = new IndexPriceModel<MaterialReceived>(price, nums);
		model.setList(list);
		return model;
	}
	private IndexPriceModel<MechanicsPrice> getAlldayMechanicsInfo(String database,int project) {
		List<MechanicsPrice> list = getMechanicsInfo(database, project,null);
		int price = 0;
		int nums = 0;
		for (MechanicsPrice entity : list) {
			price += entity.getPrice();
			nums += 1;
		}
		IndexPriceModel<MechanicsPrice> model = new IndexPriceModel<MechanicsPrice>(price, nums);
		model.setList(list);
		return model;
	}
	
	private IndexPriceModel<LaborCost> getYesterdayLaborInfo(String database,int project) {
		String date = Utils.getYesterday();
		List<LaborCost> list = getLaborInfo(database, project, date);
		int price = 0;
		int nums = 0;
		for (LaborCost entity : list) {
			price += entity.getPrice();
			nums += entity.getWorkerNums();
		}
		IndexPriceModel<LaborCost> model = new IndexPriceModel<LaborCost>(price, nums);
		return model;
	}
	private List<LaborCost> getLaborInfo(String database,int project,String date) {
		Utils.setDatabase(database);
		LaborCostExample example = new LaborCostExample();
		if(date!=null&&!"".equals(date)) {
			example.createCriteria().andProjectEqualTo(project).andDateEqualTo(date);
		}else {
			example.createCriteria().andProjectEqualTo(project);
		}
		return laborCostMapper.selectByExample(example);
	}
	
	
	
	private IndexPriceModel<MaterialReceived> getTodayMaterialInfo(String database,int project) {
		String date = Utils.getCurrentDate();
		List<MaterialReceived> list = getMaterialInfo(database, project, date);
		int price = 0;
		int nums = 0;
		for (MaterialReceived entity : list) {
			price += entity.getMaterialPrice()+entity.getFreightPrice();
			nums += 1;
		}
		IndexPriceModel<MaterialReceived> model = new IndexPriceModel<MaterialReceived>(price, nums);
		model.setList(list);
		return model;
	}
	private IndexPriceModel<MaterialReceived> getYesterdayMetarialInfo(String database,int project) {
		String date = Utils.getYesterday();
		List<MaterialReceived> list = getMaterialInfo(database, project, date);
		int price = 0;
		int nums = 0;
		for (MaterialReceived entity : list) {
			price += entity.getMaterialPrice()+entity.getFreightPrice();
			nums += 1;
		}
		IndexPriceModel<MaterialReceived> model = new IndexPriceModel<MaterialReceived>(price, nums);
		return model;
	}
	private List<MaterialReceived> getMaterialInfo(String database,int project,String date) {
		Utils.setDatabase(database);
		MaterialReceivedExample example = new MaterialReceivedExample();
		if(date!=null&&!"".equals(date)) {
			example.createCriteria().andProjectEqualTo(project).andDateEqualTo(date);
		}else {
			example.createCriteria().andProjectEqualTo(project);
		}
		return materialReceivedMapper.selectByExample(example);
	}
	
	
	private IndexPriceModel<MechanicsPrice> getTodayMechanicsInfo(String database,int project) {
		String date = Utils.getCurrentDate();
		List<MechanicsPrice> list = getMechanicsInfo(database, project, date);
		int price = 0;
		int nums = 0;
		for (MechanicsPrice entity : list) {
			price += entity.getPrice();
			nums += 1;
		}
		IndexPriceModel<MechanicsPrice> model = new IndexPriceModel<MechanicsPrice>(price, nums);
		model.setList(list);
		return model;
	}
	private IndexPriceModel<MechanicsPrice> getYesterdayMechanicsInfo(String database,int project) {
		String date = Utils.getYesterday();
		List<MechanicsPrice> list = getMechanicsInfo(database, project, date);
		int price = 0;
		int nums = 0;
		for (MechanicsPrice entity : list) {
			price += entity.getPrice();
			nums += 1;
		}
		IndexPriceModel<MechanicsPrice> model = new IndexPriceModel<MechanicsPrice>(price, nums);
		return model;
	}
	private List<MechanicsPrice> getMechanicsInfo(String database,int project,String date) {
		Utils.setDatabase(database);
		MechanicsPriceExample example = new MechanicsPriceExample();
		if(date!=null&&!"".equals(date)) {
			example.createCriteria().andProjectEqualTo(project).andDateEqualTo(date);
		}else {
			example.createCriteria().andProjectEqualTo(project);
		}
		return mechanicsPriceMapper.selectByExample(example);
	}
	public IndexPriceModel<MaterialReceived> alldayMaterial(String database,int project) {
		IndexPriceModel<MaterialReceived> all = getAlldayMaterialInfo(database, project);
		return all;
	}
	public IndexPriceModel<MechanicsPrice> alldayMechanics(String database,int project) {
		IndexPriceModel<MechanicsPrice> all = getAlldayMechanicsInfo(database, project);
		return all;
	}
	public IndexPriceModel<LaborCost> alldayLabor(String database,int project) {
		IndexPriceModel<LaborCost> all = getAlldayLaborInfo(database, project);
		return all;
	}
	public IndexPriceModel<LaborCost> todayLabor(String database,int project) {
		IndexPriceModel<LaborCost> today = getTodayLaborInfo(database, project);
		IndexPriceModel<LaborCost> yesterday = getYesterdayLaborInfo(database, project);
		int x = today.getPrice()-yesterday.getPrice();
		int yesterdayPrice = yesterday.getPrice();
		if(yesterdayPrice==0) {
			yesterdayPrice=1;
		}
		float pricePercent = ( Math.abs(x)) / (float)yesterdayPrice;
		pricePercent=(float)(Math.round(pricePercent*10000))/100;
		IndexPriceModel<LaborCost> model = new IndexPriceModel<LaborCost>(today.getPrice(), today.getNums());
		if(x>0) {
			model.setPriceColor("red");
			model.setPriceIcon("icon-arrow-up");
		}else if(x<0) {
			model.setPriceColor("green");
			model.setPriceIcon("icon-arrow-down");
		}else {
			model.setNumsColor("black");
			model.setNumsIcon("");
		}
		model.setPriceChange(Utils.formatPercent(pricePercent));
		int y = today.getNums() - yesterday.getNums();
		int yesterdayNums = yesterday.getNums();
		if(yesterdayNums==0) {
			yesterdayNums=1;
		}
		float workerPercent = ( Math.abs(y)) / (float) yesterdayNums ;
		workerPercent=(float)(Math.round(workerPercent*10000))/100;
		if(y>0) {
			model.setNumsColor("red");
			model.setNumsIcon("icon-arrow-up");
		}else if(x<0) {
			model.setNumsColor("green");
			model.setPriceIcon("icon-arrow-down");
		}else {
			model.setNumsColor("black");
			model.setNumsIcon("");
		}
		model.setNumsChange(Utils.formatPercent(workerPercent));
		model.setList(today.getList());
		return model;
	}
	
	
	
	public IndexPriceModel<MaterialReceived> todayMaterialReceived(String database,int project) {
		IndexPriceModel<MaterialReceived> today = getTodayMaterialInfo(database, project);
		IndexPriceModel<MaterialReceived> yesterday = getYesterdayMetarialInfo(database, project);
		int x = today.getPrice()-yesterday.getPrice();
		int yesterdayPrice = yesterday.getPrice();
		if(yesterdayPrice==0) {
			yesterdayPrice=1;
		}
		float pricePercent = ( Math.abs(x)) / (float)yesterdayPrice;
		pricePercent=(float)(Math.round(pricePercent*10000))/100;
		IndexPriceModel<MaterialReceived> model = new IndexPriceModel<MaterialReceived>(today.getPrice(), today.getNums());
		if(x>0) {
			model.setPriceColor("red");
			model.setPriceIcon("icon-arrow-up");
		}else if(x<0) {
			model.setPriceColor("green");
			model.setPriceIcon("icon-arrow-down");
		}else {
			model.setNumsColor("black");
			model.setNumsIcon("");
		}
		model.setPriceChange(Utils.formatPercent(pricePercent));
		int y = today.getNums() - yesterday.getNums();
		int yesterdayNums = yesterday.getNums();
		if(yesterdayNums==0) {
			yesterdayNums=1;
		}
		float workerPercent = ( Math.abs(y)) / (float) yesterdayNums ;
		workerPercent=(float)(Math.round(workerPercent*10000))/100;
		if(y>0) {
			model.setNumsColor("red");
			model.setNumsIcon("icon-arrow-up");
		}else if(x<0) {
			model.setNumsColor("green");
			model.setNumsIcon("icon-arrow-down");
		}else {
			model.setNumsColor("black");
			model.setNumsIcon("");
		}
		model.setNumsChange(Utils.formatPercent(workerPercent));
		model.setList(today.getList());
		return model;
	}
	
	
	public IndexPriceModel<MechanicsPrice> todayMechanicsPrice(String database,int project) {
		IndexPriceModel<MechanicsPrice> today = getTodayMechanicsInfo(database, project);
		IndexPriceModel<MechanicsPrice> yesterday = getYesterdayMechanicsInfo(database, project);
		int x = today.getPrice()-yesterday.getPrice();
		int yesterdayPrice = yesterday.getPrice();
		if(yesterdayPrice==0) {
			yesterdayPrice=1;
		}
		float pricePercent = ( Math.abs(x)) / (float)yesterdayPrice;
		pricePercent=(float)(Math.round(pricePercent*10000))/100;
		IndexPriceModel<MechanicsPrice> model = new IndexPriceModel<MechanicsPrice>(today.getPrice(), today.getNums());
		if(x>0) {
			model.setPriceColor("red");
			model.setPriceIcon("icon-arrow-up");
		}else if(x<0) {
			model.setPriceColor("green");
			model.setPriceIcon("icon-arrow-down");
		}else {
			model.setNumsColor("black");
			model.setNumsIcon("");
		}
		model.setPriceChange(Utils.formatPercent(pricePercent));
		int y = today.getNums() - yesterday.getNums();
		int yesterdayNums = yesterday.getNums();
		if(yesterdayNums==0) {
			yesterdayNums=1;
		}else {
			model.setNumsColor("black");
			model.setNumsIcon("");
		}
		float workerPercent = ( Math.abs(y)) / (float) yesterdayNums ;
		workerPercent=(float)(Math.round(workerPercent*10000))/100;
		if(y>0) {
			model.setNumsColor("red");
			model.setNumsIcon("icon-arrow-up");
		}else if(x<0) {
			model.setNumsColor("green");
			model.setNumsIcon("icon-arrow-down");
		}else {
			model.setNumsColor("black");
			model.setNumsIcon("");
		}
		model.setNumsChange(Utils.formatPercent(workerPercent));
		model.setList(today.getList());
		return model;
	}
	
	@SuppressWarnings("unchecked")
	public BarModel getUsedMaterial(String database,int project) {
		Utils.setDatabase(database);
		String date = Utils.getCurrentDate();
		List<MaterialChartModel> all = materialReceivedMapper.selectAllGroup(project);
		List<MaterialChartModel> today= materialReceivedMapper.selectAllGroupByDate(project, date);
		BarModel model = new BarModel();
		List<BarDataModel> todayData=new ArrayList<BarDataModel>();
		List<BarDataModel> alldayData=new ArrayList<BarDataModel>();
		//model.setData(data);
		Map<String, String> material = new HashMap<String ,String>();
		for (MaterialChartModel temp : all) {
			BarDataModel data = new BarDataModel();
			if(!material.containsKey(temp.getMaterialName())) {
				model.getNameData().add(temp.getMaterialName());
				material.put(temp.getMaterialName()+"-"+temp.getBaseType(), temp.getMaterialName()+"_"+temp.getBaseType());
			}
			data.setValue(temp.getIcount());
			data.setName(temp.getMaterialName());
			Tooltip tooltip = new Tooltip();
			tooltip.setFormatter(temp.getBaseType());
			data.setTooltip(tooltip);
			alldayData.add(data);
		}
		
		for (String key : material.keySet()) {
			BarDataModel data = new BarDataModel();
			for (MaterialChartModel temp : today) {
				if((temp.getMaterialName()+"-"+temp.getBaseType()).equals(key)) {
					data.setValue(temp.getIcount());
					data.setName(temp.getMaterialName()+"-"+temp.getBaseType());
					Tooltip tooltip = new Tooltip();
					tooltip.setFormatter(temp.getBaseType());
					data.setTooltip(tooltip);
				}
			}
			if(null==data.getTooltip()) {
				data.setValue(0);
				String[] values = material.get(key).split("_");
				data.setName(values[0]+"-"+values[1]);
				Tooltip tooltip = new Tooltip();
				tooltip.setFormatter(values[1]);
				data.setTooltip(tooltip);
			}
			todayData.add(data);
		}
		BarDataModelComparator comparator=new BarDataModelComparator();
		Collections.sort(todayData, comparator);
		Collections.sort(alldayData, comparator);
		BarNameModelComparator nameComparator = new BarNameModelComparator();
		Collections.sort(model.getNameData(), nameComparator);
		model.setTodayData(todayData);
		model.setAlldayData(alldayData);
		return model;
	}
	
	public List<LaborCost>  laborlist(String database,int project,int staff,String date){
		Utils.setDatabase(database);
		LaborCostExample example = new LaborCostExample();
		LaborCostExample.Criteria criteria=example.createCriteria();
		criteria.andProjectEqualTo(project);
		criteria.andStaffEqualTo(staff);
		example.setOrderByClause("id DESC");
		if(null!=date&&!"".equals(date)) {
			criteria.andDateEqualTo(date);
		}else {
			criteria.andDateNotEqualTo(Utils.getCurrentDate());
		}
		return laborCostMapper.selectByExample(example);
	}
	
	public PageBean<LaborCost>  laborlist(String database,int project,String program,String procedure,String node,String startDate,String endDate){
		Utils.setDatabase(database);
		LaborCostExample example = new LaborCostExample();
		LaborCostExample.Criteria criteria=example.createCriteria();
		criteria.andProjectEqualTo(project);
		if (null!=startDate&&!"".equals(startDate)) {
			criteria.andDateBetween(startDate, endDate);
		}
		if (null!=program&&!"".equals(program)) {
			criteria.andProgramEqualTo(program);
		}
		if (null!=procedure&&!"".equals(procedure)) {
			criteria.andIprocedureEqualTo(procedure);
		}
		if (null!=node&&!"".equals(node)) {
			criteria.andNodeEqualTo(node);
		}
		PageBean<LaborCost> page = new PageBean<LaborCost>(laborCostMapper.selectByExample(example));
		return page;
	}
	
	public List<MaterialReceived> materialRecevicelist(String database,int project,int staff,String date){
		Utils.setDatabase(database);
		MaterialReceivedExample example = new MaterialReceivedExample();
		MaterialReceivedExample.Criteria criteria=example.createCriteria();
		criteria.andProjectEqualTo(project);
		criteria.andStaffEqualTo(staff);
		example.setOrderByClause("id DESC");
		if(null!=date&&!"".equals(date)) {
			criteria.andDateEqualTo(date);
		}else {
			criteria.andDateNotEqualTo(Utils.getCurrentDate());
		}
		return materialReceivedMapper.selectByExample(example);
	}
	
	public PageBean<MaterialReceived>  materialRecevicelist(String database,int project,String program,String procedure,String node,String startDate,String endDate){
		Utils.setDatabase(database);
		MaterialReceivedExample example = new MaterialReceivedExample();
		MaterialReceivedExample.Criteria criteria=example.createCriteria();
		criteria.andProjectEqualTo(project);
		if (null!=startDate&&!"".equals(startDate)) {
			criteria.andDateBetween(startDate, endDate);
		}
		if (null!=program&&!"".equals(program)) {
			criteria.andProgramEqualTo(program);
		}
		if (null!=procedure&&!"".equals(procedure)) {
			criteria.andIprocedureEqualTo(procedure);
		}
		if (null!=node&&!"".equals(node)) {
			criteria.andNodeEqualTo(node);
		}
		PageBean<MaterialReceived> page = new PageBean<MaterialReceived>(materialReceivedMapper.selectByExample(example));
		return page;
	}
	
	
	public List<MechanicsPrice> mechanicsPriceList(String database,int project,int staff,String date){
		Utils.setDatabase(database);
		MechanicsPriceExample example = new MechanicsPriceExample();
		MechanicsPriceExample.Criteria criteria=example.createCriteria();
		criteria.andProjectEqualTo(project);
		criteria.andStaffEqualTo(staff);
		example.setOrderByClause("id DESC");
		if(null!=date&&!"".equals(date)) {
			criteria.andDateEqualTo(date);
		}else {
			criteria.andDateNotEqualTo(Utils.getCurrentDate());
		}
		return mechanicsPriceMapper.selectByExample(example);
	}
	
	public PageBean<MechanicsPrice>  mechanicsPriceList(String database,int project,String program,String procedure,String node,String startDate,String endDate){
		Utils.setDatabase(database);
		MechanicsPriceExample example = new MechanicsPriceExample();
		MechanicsPriceExample.Criteria criteria=example.createCriteria();
		criteria.andProjectEqualTo(project);
		if (null!=startDate&&!"".equals(startDate)) {
			criteria.andDateBetween(startDate, endDate);
		}
		if (null!=program&&!"".equals(program)) {
			criteria.andProgramEqualTo(program);
		}
		if (null!=procedure&&!"".equals(procedure)) {
			criteria.andIprocedureEqualTo(procedure);
		}
		if (null!=node&&!"".equals(node)) {
			criteria.andNodeEqualTo(node);
		}
		PageBean<MechanicsPrice> page = new PageBean<MechanicsPrice>(mechanicsPriceMapper.selectByExample(example));
		return page;
	}
	
	public List<Oil> oilList(String database,int project,int staff,String date){
		Utils.setDatabase(database);
		OilExample example = new OilExample();
		OilExample.Criteria criteria=example.createCriteria();
		criteria.andProjectEqualTo(project);
		criteria.andStaffEqualTo(staff);
		example.setOrderByClause("id DESC");
		if(null!=date&&!"".equals(date)) {
			criteria.andDateEqualTo(date);
		}else {
			criteria.andDateNotEqualTo(Utils.getCurrentDate());
		}
		return oilMapper.selectByExample(example);
	}
	
	public boolean addOil(String database,Oil oil) {
		Utils.setDatabase(database);
		int result = oilMapper.insertSelective(oil);
		return result==0?false:true;
	}
	
	public PageBean<Oil> oilList(String database,int project,String startDate,String endDate){
		Utils.setDatabase(database);
		OilExample example = new OilExample();
		OilExample.Criteria criteria=example.createCriteria();
		criteria.andProjectEqualTo(project);
		if (null!=startDate&&!"".equals(startDate)) {
			criteria.andDateBetween(startDate, endDate);
		}
		PageBean<Oil> page = new PageBean<Oil>(oilMapper.selectByExample(example));
		return page;
	}
	
	
	
}
