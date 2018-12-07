package pwr.lcec.vendor.web.model;

import java.io.Serializable;

public class MyRateGroup implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String myRtGrpId;
	private String assemblyUnit;
	private String desc;
	private String laborPrice;
	private String rateGroup;
	
	public MyRateGroup() {
		
	}
	
	public MyRateGroup(String myRtGrpId, String assemblyUnit, String desc, String laborPrice, String rateGroup) {
		this.myRtGrpId = myRtGrpId;
		this.assemblyUnit = assemblyUnit;
		this.desc = desc;
		this.laborPrice = laborPrice;
		this.rateGroup = rateGroup;
	}
	public String getMyRtGrpId() {
		return myRtGrpId;
	}
	public void setMyRtGrpId(String myRtGrpId) {
		this.myRtGrpId = myRtGrpId;
	}
	public String getAssemblyUnit() {
		return assemblyUnit;
	}
	public void setAssemblyUnit(String assemblyUnit) {
		this.assemblyUnit = assemblyUnit;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getLaborPrice() {
		return laborPrice;
	}
	public void setLaborPrice(String laborPrice) {
		this.laborPrice = laborPrice;
	}
	public String getRateGroup() {
		return rateGroup;
	}
	public void setRateGroup(String rateGroup) {
		this.rateGroup = rateGroup;
	}

}
