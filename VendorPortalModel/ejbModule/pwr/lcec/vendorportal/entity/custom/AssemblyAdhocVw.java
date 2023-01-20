package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the AssemblyAdhocVw database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "AssemblyAdhocVw.findAll", query = "SELECT a FROM AssemblyAdhocVw a WHERE a.rateGroupId = :rateGrpId ORDER BY a.assemblyGuid ASC"),
	@NamedQuery(name = "AssemblyAdhocVw.findByRates", query = "SELECT a FROM AssemblyAdhocVw a WHERE a.rateGroupId = :rateGrpId AND a.energized = :energized AND a.transfer = :transfer AND :workEventDt BETWEEN a.effectiveStartDt AND a.effectiveEndDt") 
})
public class AssemblyAdhocVw implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AssemblyGuid")
	private String assemblyGuid;

	@Column(name="EffectiveEndDt")
	private Timestamp effectiveEndDt;

	@Column(name="EffectiveStartDt")
	private Timestamp effectiveStartDt;

	@Column(name="Energized")
	private String energized;

	@Column(name="RateGroupId")
	private int rateGroupId;

	@Id
	@Column(name="RateGroupPriceId")
	private int rateGroupPriceId;

	@Column(name="Transfer")
	private String transfer;

	public AssemblyAdhocVw() {
	}

	public String getAssemblyGuid() {
		return this.assemblyGuid;
	}

	public void setAssemblyGuid(String assemblyGuid) {
		this.assemblyGuid = assemblyGuid;
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

	public String getEnergized() {
		return this.energized;
	}

	public void setEnergized(String energized) {
		this.energized = energized;
	}

	public int getRateGroupId() {
		return this.rateGroupId;
	}

	public void setRateGroupId(int rateGroupId) {
		this.rateGroupId = rateGroupId;
	}

	public int getRateGroupPriceId() {
		return this.rateGroupPriceId;
	}

	public void setRateGroupPriceId(int rateGroupPriceId) {
		this.rateGroupPriceId = rateGroupPriceId;
	}

	public String getTransfer() {
		return this.transfer;
	}

	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}

}