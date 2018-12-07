package pwr.lcec.vendor.web.model;

import java.io.Serializable;

public class InspectionView implements Serializable {

	private static final long serialVersionUID = 1L;

	private String stationId;
	private String auId;
	private String cr;
	private int quantity;
	private String unit;
	private String unitDesc;
	private String rate;
	private String lcecNotes;
	private String asBuilt;
	private String asBuiltCompleted;
	private String crew;
	private String lcecComment;
	private String installDt;
	private String labor;
	private String vendorComment;
	private String inspectionStatus;
	private String inspectionComment;

	public InspectionView(String stationId, String auId, String cr, int quantity, String unit, String unitDesc,
			String rate, String lcecNotes, String asBuilt, String asBuiltCompleted, String crew, String lcecComment,
			String installDt, String labor, String vendorComment, String inspectionStatus, String inspectionComment) {
		super();
		this.stationId = stationId;
		this.auId = auId;
		this.cr = cr;
		this.quantity = quantity;
		this.unit = unit;
		this.unitDesc = unitDesc;
		this.rate = rate;
		this.lcecNotes = lcecNotes;
		this.asBuilt = asBuilt;
		this.asBuiltCompleted = asBuiltCompleted;
		this.crew = crew;
		this.lcecComment = lcecComment;
		this.installDt = installDt;
		this.labor = labor;
		this.vendorComment = vendorComment;
		this.inspectionStatus = inspectionStatus;
		this.inspectionComment = inspectionComment;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getAuId() {
		return auId;
	}

	public void setAuId(String auId) {
		this.auId = auId;
	}

	public String getCr() {
		return cr;
	}

	public void setCr(String cr) {
		this.cr = cr;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnitDesc() {
		return unitDesc;
	}

	public void setUnitDesc(String unitDesc) {
		this.unitDesc = unitDesc;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getLcecNotes() {
		return lcecNotes;
	}

	public void setLcecNotes(String lcecNotes) {
		this.lcecNotes = lcecNotes;
	}

	public String getAsBuilt() {
		return asBuilt;
	}

	public void setAsBuilt(String asBuilt) {
		this.asBuilt = asBuilt;
	}

	public String getAsBuiltCompleted() {
		return asBuiltCompleted;
	}

	public void setAsBuiltCompleted(String asBuiltCompleted) {
		this.asBuiltCompleted = asBuiltCompleted;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public String getLcecComment() {
		return lcecComment;
	}

	public void setLcecComment(String lcecComment) {
		this.lcecComment = lcecComment;
	}

	public String getInstallDt() {
		return installDt;
	}

	public void setInstallDt(String installDt) {
		this.installDt = installDt;
	}

	public String getLabor() {
		return labor;
	}

	public void setLabor(String labor) {
		this.labor = labor;
	}

	public String getVendorComment() {
		return vendorComment;
	}

	public void setVendorComment(String vendorComment) {
		this.vendorComment = vendorComment;
	}

	public String getInspectionStatus() {
		return inspectionStatus;
	}

	public void setInspectionStatus(String inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}

	public String getInspectionComment() {
		return inspectionComment;
	}

	public void setInspectionComment(String inspectionComment) {
		this.inspectionComment = inspectionComment;
	}
}
