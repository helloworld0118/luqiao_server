package com.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.entity.BasePriceType;
import com.core.entity.PriceFluctuation;
import com.core.entity.UnitTypeCombination;
import com.core.mapper.BasePriceTypeMapper;
import com.core.mapper.BaseTypeMapper;
import com.core.mapper.PriceFluctuationMapper;
import com.core.mapper.UnitTypeCombinationMapper;
import com.core.util.Utils;

@Service
public class UnitService {
	@Autowired
	
	protected PriceFluctuationMapper priceFluctuationMapper;
	
	@Autowired
	protected UnitTypeCombinationMapper unitTypeCombinationMapper;
	
	@Autowired
	protected BasePriceTypeMapper basePriceTypeMapper;
	
	
	@Autowired
	protected BaseTypeMapper baseTypeMapper;
	
	PriceFluctuation getPriceFluctuation(String database,int id) {
		return priceFluctuationMapper.selectByPrimaryKey(database,id);
	}
	UnitTypeCombination getUnitTypeCombination(String database, int id) {
		return unitTypeCombinationMapper.selectByPrimaryKey(database,id);
	}
	BasePriceType getBasePriceType(String database, int id) {
		return basePriceTypeMapper.selectByPrimaryKey(database,id);
	}
	
	int insertUnitPrice(String database,PriceFluctuation pf){
		Utils.setDatabase(database);
		return priceFluctuationMapper.insertSelective(pf);
	}
	int insertUnitTypeCombination(String database,UnitTypeCombination utc){
		Utils.setDatabase(database);
		return unitTypeCombinationMapper.insertSelective(utc);
	}
}
