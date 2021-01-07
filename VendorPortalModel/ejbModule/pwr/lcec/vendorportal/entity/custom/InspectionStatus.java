package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the InspectionStatus database table.
 * 
 */
@Entity
@NamedQuery(name="InspectionStatus.findAll", query="SELECT i FROM InspectionStatus i")
public class InspectionStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="InspectionStatusId")
	private Integer inspectionStatusId;

	@Column(name="Active")
	private boolean active;

	@Column(name="Description")
	private String description;

	@Column(name="HeaderDescription")
	private String headerDescription;

	@Column(name="HeaderFlg")
	private boolean headerFlg;

	@Column(name="Status")
	private String status;

	@Column(name="WorkEventStatusId")
	private String workEventStatusId;

	public InspectionStatus() {
	}

	public Integer getInspectionStatusId() {
		return this.inspectionStatusId;
	}

	public void setInspectionStatusId(Integer inspectionStatusId) {
		this.inspectionStatusId = inspectionStatusId;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHeaderDescription() {
		return this.headerDescription;
	}

	public void setHeaderDescription(String headerDescription) {
		this.headerDescription = headerDescription;
	}

	public boolean getHeaderFlg() {
		return this.headerFlg;
	}

	public void setHeaderFlg(boolean headerFlg) {
		this.headerFlg = headerFlg;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWorkEventStatusId() {
		return this.workEventStatusId;
	}

	public void setWorkEventStatusId(String workEventStatusId) {
		this.workEventStatusId = workEventStatusId;
	}
}