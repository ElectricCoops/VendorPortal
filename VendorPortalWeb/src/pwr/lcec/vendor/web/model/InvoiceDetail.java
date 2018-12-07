package pwr.lcec.vendor.web.model;

public class InvoiceDetail {

	private String invDetId;
	private String cr;
	private String station;
	private String assmUnit;
	private String rtGrp;
	private String asBuiltQty;
	private String asBuiltCost;
	private double subTot;

	public InvoiceDetail() {

	}

	public InvoiceDetail(String invDetId, String cr, String station, String assmUnit, String rtGrp, String asBuiltQty,
			String asBuiltCost, double subTot) {
		this.invDetId = invDetId;
		this.cr = cr;
		this.station = station;
		this.assmUnit = assmUnit;
		this.rtGrp = rtGrp;
		this.asBuiltQty = asBuiltQty;
		this.asBuiltCost = asBuiltCost;
		this.subTot = subTot;
	}

	public String getInvDetId() {
		return invDetId;
	}

	public void setInvDetId(String invDetId) {
		this.invDetId = invDetId;
	}

	public String getCr() {
		return cr;
	}

	public void setCr(String cr) {
		this.cr = cr;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getAssmUnit() {
		return assmUnit;
	}

	public void setAssmUnit(String assmUnit) {
		this.assmUnit = assmUnit;
	}

	public String getRtGrp() {
		return rtGrp;
	}

	public void setRtGrp(String rtGrp) {
		this.rtGrp = rtGrp;
	}

	public String getAsBuiltQty() {
		return asBuiltQty;
	}

	public void setAsBuiltQty(String asBuiltQty) {
		this.asBuiltQty = asBuiltQty;
	}

	public String getAsBuiltCost() {
		return asBuiltCost;
	}

	public void setAsBuiltCost(String asBuiltCost) {
		this.asBuiltCost = asBuiltCost;
	}

	public double getSubTot() {
		return subTot;
	}

	public void setSubTot(double subTot) {
		this.subTot = subTot;
	}

}
