package com.core.util.model.api;

import java.util.ArrayList;
import java.util.List;

import com.core.util.model.MechanicPriceModel;
import com.core.util.model.SkipMaterialPriceModel;

public class MechanicsModel {
	private int  id;
	private int  foremanId;
	private String plateNumber;
	private String name;
	private String mName;
	private String mobile;
	private String idcard;
	private String driverName;
	private String driverMobile;
	private String driverIdcard;
	private String createDate;
	private String model;
	private String capacity;
	private List<MechanicPriceModel> mlist = new ArrayList<>();
	private List<SkipMaterialPriceModel> slist = new ArrayList<>();
	
	public MechanicsModel(int id, int foremanId,String plateNumber, String name, String mobile, String idcard, String driverName,
			String driverMobile, String driverIdcard, String createDate,String capacity) {
		super();
		this.id = id;
		this.foremanId = foremanId;
		this.plateNumber = plateNumber;
		this.name = name;
		this.mobile = mobile;
		this.idcard = idcard;
		this.driverName = driverName;
		this.driverMobile = driverMobile;
		this.driverIdcard = driverIdcard;
		this.createDate = createDate;
		this.capacity = capacity;
	}
	
	
	public MechanicsModel(int id, int foremanId,String plateNumber, String name, String mName, String mobile, String idcard, String driverName,
			String driverMobile, String driverIdcard, String createDate, String model) {
		super();
		this.id = id;
		this.foremanId = foremanId;
		this.plateNumber = plateNumber;
		this.name = name;
		this.mName = mName;
		this.mobile = mobile;
		this.idcard = idcard;
		this.driverName = driverName;
		this.driverMobile = driverMobile;
		this.driverIdcard = driverIdcard;
		this.createDate = createDate;
		this.model = model;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getForemanId() {
		return foremanId;
	}

	public void setForemanId(int foremanId) {
		this.foremanId = foremanId;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverMobile() {
		return driverMobile;
	}

	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}

	public String getDriverIdcard() {
		return driverIdcard;
	}

	public void setDriverIdcard(String driverIdcard) {
		this.driverIdcard = driverIdcard;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}


	public String getCapacity() {
		return capacity;
	}


	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}


	public List<MechanicPriceModel> getMlist() {
		return mlist;
	}


	public void setMlist(List<MechanicPriceModel> mlist) {
		this.mlist = mlist;
	}


	public List<SkipMaterialPriceModel> getSlist() {
		return slist;
	}


	public void setSlist(List<SkipMaterialPriceModel> slist) {
		this.slist = slist;
	}
	
	
}
