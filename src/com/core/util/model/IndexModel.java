package com.core.util.model;

import java.util.List;

import com.core.entity.LaborCost;
import com.core.entity.MaterialReceived;
import com.core.entity.MechanicsPrice;
import com.core.util.model.chart.PieModel;

public class IndexModel {
	private IndexPriceModel<LaborCost> labor;
	private IndexPriceModel<MaterialReceived> material;
	private IndexPriceModel<MechanicsPrice> mechanics;
	private List<PieModel> list ;
	
	
	
	public IndexModel(IndexPriceModel<LaborCost> labor, IndexPriceModel<MaterialReceived> material,
			IndexPriceModel<MechanicsPrice> mechanics, List<PieModel> list) {
		super();
		this.labor = labor;
		this.material = material;
		this.mechanics = mechanics;
		this.list = list;
	}
	public IndexPriceModel<LaborCost> getLabor() {
		return labor;
	}
	public void setLabor(IndexPriceModel<LaborCost> labor) {
		this.labor = labor;
	}
	public IndexPriceModel<MaterialReceived> getMaterial() {
		return material;
	}
	public void setMaterial(IndexPriceModel<MaterialReceived> material) {
		this.material = material;
	}
	public IndexPriceModel<MechanicsPrice> getMechanics() {
		return mechanics;
	}
	public void setMechanics(IndexPriceModel<MechanicsPrice> mechanics) {
		this.mechanics = mechanics;
	}
	public List<PieModel> getList() {
		return list;
	}
	public void setList(List<PieModel> list) {
		this.list = list;
	}
	
}
