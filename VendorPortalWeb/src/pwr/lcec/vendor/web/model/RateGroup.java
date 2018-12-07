package pwr.lcec.vendor.web.model;

import java.io.Serializable;

public class RateGroup implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String vendor;
	private String rate;
	private String  effStartDt;
	private String  effEndDt;
	
	public RateGroup() {
		
	}
	
	public RateGroup(String id, String vendor, String rate, String effStartDt, String effEndDt) {

		this.id = id;
		this.vendor = vendor;
		this.rate = rate;
		this.effStartDt = effStartDt;
		this.effEndDt = effEndDt;
	}
	
	public String getVendor() {
		return vendor;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getEffStartDt() {
		return effStartDt;
	}
	public void setEffStartDt(String effStartDt) {
		this.effStartDt = effStartDt;
	}
	public String getEffEndDt() {
		return effEndDt;
	}
	public void setEffEndDt(String effEndDt) {
		this.effEndDt = effEndDt;
	}

}
