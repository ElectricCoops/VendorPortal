package pwr.lcec.vendorportal.ag.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the AGInspector database table.
 * 
 */
@Entity
@NamedQuery(name="AGInspector.findAll", query="SELECT a FROM AGInspector a")
public class AGInspector implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer AGInspectorID;

	@Column(name="InspectionApproved30Days")
	private Integer inspectionApproved30Days;

	@Column(name="InspectionInProgress")
	private Integer inspectionInProgress;

	@Column(name="InspectionReady")
	private Integer inspectionReady;

	@Column(name="InspectionRejected")
	private Integer inspectionRejected;

	@Column(name="InspectorID")
	private Integer inspectorID;

	@Column(name="InspectorName")
	private String inspectorName;

	@Column(name="LastSynced")
	private Timestamp lastSynced;

	@Column(name="NotInspected")
	private Integer notInspected;

	@Column(name="Total30Days")
	private Integer total30Days;

	public AGInspector() {
	}

	public Integer getAGInspectorID() {
		return this.AGInspectorID;
	}

	public void setAGInspectorID(Integer AGInspectorID) {
		this.AGInspectorID = AGInspectorID;
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

	public Integer getInspectorID() {
		return this.inspectorID;
	}

	public void setInspectorID(Integer inspectorID) {
		this.inspectorID = inspectorID;
	}

	public String getInspectorName() {
		return this.inspectorName;
	}

	public void setInspectorName(String inspectorName) {
		this.inspectorName = inspectorName;
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

}