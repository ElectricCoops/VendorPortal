package pwr.lcec.vendorportal.ag.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the AGInspection database table.
 * 
 */
@Entity
@NamedQuery(name="AGInspection.findAll", query="SELECT a FROM AGInspection a")
public class AGInspection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer AGInspectionID;

	@Column(name="InspectionApproved30Days")
	private Integer inspectionApproved30Days;

	@Column(name="InspectionInProgress")
	private Integer inspectionInProgress;

	@Column(name="InspectionReady")
	private Integer inspectionReady;

	@Column(name="InspectionRejected")
	private Integer inspectionRejected;

	@Column(name="LastSynced")
	private Timestamp lastSynced;

	@Column(name="NotInspected")
	private Integer notInspected;

	@Column(name="Total30Days")
	private Integer total30Days;

	@Column(name="VendorType")
	private String vendorType;

	public AGInspection() {
	}

	public Integer getAGInspectionID() {
		return this.AGInspectionID;
	}

	public void setAGInspectionID(Integer AGInspectionID) {
		this.AGInspectionID = AGInspectionID;
	}

	public Integer getInspectionApproved30Days() {
		return this.inspectionApproved30Days;
	}

	public void setInspectionApproved30Days(Integer inspectionApproved30Days) {
		this.inspectionApproved30Days = inspectionApproved30Days;
	}

	public Integer getInspectionInProgress() {
		return this.inspectionInProgress;
	}

	public void setInspectionInProgress(Integer inspectionInProgress) {
		this.inspectionInProgress = inspectionInProgress;
	}

	public Integer getInspectionReady() {
		return this.inspectionReady;
	}

	public void setInspectionReady(Integer inspectionReady) {
		this.inspectionReady = inspectionReady;
	}

	public Integer getInspectionRejected() {
		return this.inspectionRejected;
	}

	public void setInspectionRejected(Integer inspectionRejected) {
		this.inspectionRejected = inspectionRejected;
	}

	public Timestamp getLastSynced() {
		return this.lastSynced;
	}

	public void setLastSynced(Timestamp lastSynced) {
		this.lastSynced = lastSynced;
	}

	public Integer getNotInspected() {
		return this.notInspected;
	}

	public void setNotInspected(Integer notInspected) {
		this.notInspected = notInspected;
	}

	public Integer getTotal30Days() {
		return this.total30Days;
	}

	public void setTotal30Days(Integer total30Days) {
		this.total30Days = total30Days;
	}

	public String getVendorType() {
		return this.vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

}