package pwr.lcec.vendor.web.model;

import java.io.Serializable;

public class Invoice implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String invId;
	private String lcecRefNo;
	private String vendorRefNo;
	private String invDt;
	private String invAmt;
	private String invStat;
	
	public Invoice() {
		
	}
	
	public Invoice(String invId, String lcecRefNo, String vendorRefNo, String invDt, String invAmt, String invStat) {
		this.invId = invId;
		this.lcecRefNo = lcecRefNo;
		this.vendorRefNo = vendorRefNo;
		this.invDt = invDt;
		this.invAmt = invAmt;
		this.invStat = invStat;
	}
	
	public String getInvId() {
		return invId;
	}
	public void setInvId(String invId) {
		this.invId = invId;
	}
	public String getLcecRefNo() {
		return lcecRefNo;
	}
	public void setLcecRefNo(String lcecRefNo) {
		this.lcecRefNo = lcecRefNo;
	}
	public String getVendorRefNo() {
		return vendorRefNo;
	}
	public void setVendorRefNo(String vendorRefNo) {
		this.vendorRefNo = vendorRefNo;
	}
	public String getInvDt() {
		return invDt;
	}
	public void setInvDt(String invDt) {
		this.invDt = invDt;
	}
	public String getInvAmt() {
		return invAmt;
	}
	public void setInvAmt(String invAmt) {
		this.invAmt = invAmt;
	}
	public String getInvStat() {
		return invStat;
	}
	public void setInvStat(String invStat) {
		this.invStat = invStat;
	}
}
