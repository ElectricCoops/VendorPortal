package pwr.lcec.vendor.web.model;

import java.io.Serializable;

public class StakeSheet implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	private String station;
	private String ra;
	private int qty;
	private String unit;
	private String description;
	private int asBuilt;
	private String note;
	private String completedDt;
	private String completedByCrew;
	private String rateGrp;
	private String assyComment;
	private String installDt;
	private String lbrCost;
	
	public StakeSheet() {}
	
	public StakeSheet(String id, String station, String ra, int qty, String unit, String description, int asBuilt,
			String note, String completedDt, String completedByCrew, String rateGrp, String assyComment,
			String installDt, String lbrCost) {
		this.id = id;
		this.station = station;
		this.ra = ra;
		this.qty = qty;
		this.unit = unit;
		this.description = description;
		this.asBuilt = asBuilt;
		this.note = note;
		this.completedDt = completedDt;
		this.completedByCrew = completedByCrew;
		this.rateGrp = rateGrp;
		this.assyComment = assyComment;
		this.installDt = installDt;
		this.lbrCost = lbrCost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getRa() {
		return ra;
	}
	public void setRa(String ra) {
		this.ra = ra;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAsBuilt() {
		return asBuilt;
	}
	public void setAsBuilt(int asBuilt) {
		this.asBuilt = asBuilt;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public String getCompletedDt() {
		return completedDt;
	}

	public void setCompletedDt(String completedDt) {
		this.completedDt = completedDt;
	}

	public String getCompletedByCrew() {
		return completedByCrew;
	}

	public void setCompletedByCrew(String completedByCrew) {
		this.completedByCrew = completedByCrew;
	}

	public String getRateGrp() {
		return rateGrp;
	}

	public void setRateGrp(String rateGrp) {
		this.rateGrp = rateGrp;
	}

	public String getAssyComment() {
		return assyComment;
	}

	public void setAssyComment(String assyComment) {
		this.assyComment = assyComment;
	}

	public String getInstallDt() {
		return installDt;
	}

	public void setInstallDt(String installDt) {
		this.installDt = installDt;
	}

	public String getLbrCost() {
		return lbrCost;
	}

	public void setLbrCost(String lbrCost) {
		this.lbrCost = lbrCost;
	}

	
}
