package pwr.lcec.vendorportal.entity.ag;

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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int AGInspectionID;

	@Column(name="InspectionApproved30Days")
	private int inspectionApproved30Days;

	@Column(name="InspectionInProgress")
	private int inspectionInProgress;

	@Column(name="InspectionReady")
	private int inspectionReady;

	@Column(name="InspectionRejected")
	private int inspectionRejected;

	@Column(name="LastSynced")
	private Timestamp lastSynced;

	@Column(name="NotInspected")
	private int notInspected;

	@Column(name="Total30Days")
	private int total30Days;

	@Column(name="VendorType")
	private String vendorType;

	public AGInspection() {
	}

	public int getAGInspectionID() {
		return this.AGInspectionID;
	}

	public void setAGInspectionID(int AGInspectionID) {
		this.AGInspectionID = AGInspectionID;
	}

	public int getInspectionApproved30Days() {
		return this.inspectionApproved30Days;
	}

	public void setInspectionApproved30Days(int inspectionApproved30Days) {
		this.inspectionApproved30Days = inspectionApproved30Days;
	}

	public int getInspectionInProgress() {
		return this.inspectionInProgress;
	}

	public void setInspectionInProgress(int inspectionInProgress) {
		this.inspectionInProgress = inspectionInProgress;
	}

	public int getInspectionReady() {
		return this.inspectionReady;
	}

	public void setInspectionReady(int inspectionReady) {
		this.inspectionReady = inspectionReady;
	}

	public int getInspectionRejected() {
		return this.inspectionRejected;
	}

	public void setInspectionRejected(int inspectionRejected) {
		this.inspectionRejected = inspectionRejected;
	}

	public Timestamp getLastSynced() {
		return this.lastSynced;
	}

	public void setLastSynced(Timestamp lastSynced) {
		this.lastSynced = lastSynced;
	}

	public int getNotInspected() {
		return this.notInspected;
	}

	public void setNotInspected(int notInspected) {
		this.notInspected = notInspected;
	}

	public int getTotal30Days() {
		return this.total30Days;
	}

	public void setTotal30Days(int total30Days) {
		this.total30Days = total30Days;
	}

	public String getVendorType() {
		return this.vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

}