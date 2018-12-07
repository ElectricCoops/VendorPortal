package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AssemblyAdhocVw database table.
 * 
 */
@Entity
@NamedQuery(name="AssemblyAdhocVw.findAll", query="SELECT a FROM AssemblyAdhocVw a WHERE a.rateGroupId = :rateGrpId ORDER BY a.assemblyGuid ASC")
public class AssemblyAdhocVw implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AssemblyGuid")
	private String assemblyGuid;

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