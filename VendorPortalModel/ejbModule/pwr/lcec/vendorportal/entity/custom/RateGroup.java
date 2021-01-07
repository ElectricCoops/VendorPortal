package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the RateGroup database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "RateGroup.findAll", query = "SELECT r FROM RateGroup r"),
	@NamedQuery(name = "RateGroup.findByRateGrpName", query = "SELECT r.rateGroupId FROM RateGroup r WHERE r.rateGroupName = :rateName") 
})
@NamedStoredProcedureQuery(name = "GET_ADHOC_ASSEMBLYS", procedureName = "GET_ADHOC_ASSEMBLYS", parameters = {
	@StoredProcedureParameter(mode = ParameterMode.IN, type = Timestamp.class, name = "IN_WorkEventDt"),
	@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "IN_RATEGROUP"),
	@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_ENERGIZE"),
	@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_TRANSFER") 
})
public class RateGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RateGroupId")
	private int rateGroupId;

	@Column(name="Description")
	private String description;

	@Column(name="EffectiveEndDt")
	private Timestamp effectiveEndDt;

	@Column(name="EffectiveStartDt")
	private Timestamp effectiveStartDt;

	@Column(name="FixedRateSW")
	private String fixedRateSW;

	@Column(name="RateGroupName")
	private String rateGroupName;

	@Column(name="StatusCode")
	private String statusCode;

	@Column(name="VendorId")
	private int vendorId;

	public RateGroup() {
	}

	public int getRateGroupId() {
		return this.rateGroupId;
	}

	public void setRateGroupId(int rateGroupId) {
		this.rateGroupId = rateGroupId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getEffectiveEndDt() {
		return this.effectiveEndDt;
	}

	public void setEffectiveEndDt(Timestamp effectiveEndDt) {
		this.effectiveEndDt = effectiveEndDt;
	}

	public Timestamp getEffectiveStartDt() {
		return this.effectiveStartDt;
	}

	public void setEffectiveStartDt(Timestamp effectiveStartDt) {
		this.effectiveStartDt = effectiveStartDt;
	}

	public String getFixedRateSW() {
		return this.fixedRateSW;
	}

	public void setFixedRateSW(String fixedRateSW) {
		this.fixedRateSW = fixedRateSW;
	}

	public String getRateGroupName() {
		return this.rateGroupName;
	}

	public void setRateGroupName(String rateGroupName) {
		this.rateGroupName = rateGroupName;
	}

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public int getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

}