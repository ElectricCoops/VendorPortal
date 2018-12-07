package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AsBuiltStatus database table.
 * 
 */
@Entity
@NamedQuery(name="AsBuiltStatus.findAll", query="SELECT a FROM AsBuiltStatus a")
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "UPDATE_OVERALL_ASBUILT_STATUS", procedureName = "UPDATE_OVERALL_ASBUILT_STATUS", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_WorkOrderId"),
			@StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "OUT_Response") }) })
public class AsBuiltStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="AsBuiltStatusId")
	private Integer asBuiltStatusId;

	@Column(name="Active")
	private boolean active;

	@Column(name="Description")
	private String description;

	public AsBuiltStatus() {
	}
	
	public Integer getAsBuiltStatusId() {
		return asBuiltStatusId;
	}

	public void setAsBuiltStatusId(Integer asBuiltStatusId) {
		this.asBuiltStatusId = asBuiltStatusId;
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
}