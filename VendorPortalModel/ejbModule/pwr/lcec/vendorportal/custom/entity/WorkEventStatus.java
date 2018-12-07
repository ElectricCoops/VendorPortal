package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the WorkEventStatus database table.
 * 
 */
@Entity
@NamedQuery(name="WorkEventStatus.findAll", query="SELECT w FROM WorkEventStatus w")
public class WorkEventStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="WorkEventStatusId")
	private String workEventStatusId;

	@Column(name="Active")
	private boolean active;

	@Column(name="ApplicationCode")
	private String applicationCode;

	@Column(name="Description")
	private String description;

	public WorkEventStatus() {
	}

	public String getWorkEventStatusId() {
		return this.workEventStatusId;
	}

	public void setWorkEventStatusId(String workEventStatusId) {
		this.workEventStatusId = workEventStatusId;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getApplicationCode() {
		return this.applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}