package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the RateGroupVw database table.
 * 
 */
@Entity
@NamedQuery(name="RateGroupVw.findAll", query="SELECT r FROM RateGroupVw r")
public class RateGroupVw implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="AssemblyGuid")
	private String assemblyGuid;

	@Column(name="Description")
	private String description;

	@Column(name="EffectiveEndDt")
	private Timestamp effectiveEndDt;

	@Column(name="EffectiveStartDt")
	private Timestamp effectiveStartDt;

	@Column(name="FixedCost")
	private String fixedCost;

	@Column(name="LaborConstCost")
	private String laborConstCost;

	@Column(name="LaborConstHours")
	private BigDecimal laborConstHours;

	@Column(name="LaborRetireCost")
	private String laborRetireCost;

	@Column(name="LaborRetireHours")
	private BigDecimal laborRetireHours;

	@Column(name="RateGroupName")
	private String rateGroupName;

	@Column(name="RateGroupPriceId")
	private int rateGroupPriceId;

	public RateGroupVw() {
	}

	public String getAssemblyGuid() {
		return this.assemblyGuid;
	}

	public void setAssemblyGuid(String assemblyGuid) {
		this.assemblyGuid = assemblyGuid;
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

	public String getFixedCost() {
		return this.fixedCost;
	}

	public void setFixedCost(String fixedCost) {
		this.fixedCost = fixedCost;
	}

	public String getLaborConstCost() {
		return this.laborConstCost;
	}

	public void setLaborConstCost(String laborConstCost) {
		this.laborConstCost = laborConstCost;
	}

	public BigDecimal getLaborConstHours() {
		return this.laborConstHours;
	}

	public void setLaborConstHours(BigDecimal laborConstHours) {
		this.laborConstHours = laborConstHours;
	}

	public String getLaborRetireCost() {
		return this.laborRetireCost;
	}

	public void setLaborRetireCost(String laborRetireCost) {
		this.laborRetireCost = laborRetireCost;
	}

	public BigDecimal getLaborRetireHours() {
		return this.laborRetireHours;
	}

	public void setLaborRetireHours(BigDecimal laborRetireHours) {
		this.laborRetireHours = laborRetireHours;
	}

	public String getRateGroupName() {
		return this.rateGroupName;
	}

	public void setRateGroupName(String rateGroupName) {
		this.rateGroupName = rateGroupName;
	}

	public int getRateGroupPriceId() {
		return this.rateGroupPriceId;
	}

	public void setRateGroupPriceId(int rateGroupPriceId) {
		this.rateGroupPriceId = rateGroupPriceId;
	}

}