package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AsBuiltSummaryVw database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="AsBuiltSummaryVw.findAll", query="SELECT a FROM AsBuiltSummaryVw a"),
@NamedQuery(name="AsBuiltSummaryVw.findByStakingSheetId", query="SELECT a FROM AsBuiltSummaryVw a WHERE a.stakingSheetId = :sheetId")})
public class AsBuiltSummaryVw implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AsBuiltQuantity")
	private int asBuiltQuantity;

	@Column(name="AssemblyActionCode")
	private String assemblyActionCode;

	@Column(name="AssemblyDescription")
	private String assemblyDescription;

	@Id
	@Column(name="AssemblyGuid")
	private String assemblyGuid;

	@Column(name="DesignQuantity")
	private int designQuantity;

	@Column(name="InvoiceStatus")
	private String invoiceStatus;

	@Column(name="StakingSheetId")
	private int stakingSheetId;

	public AsBuiltSummaryVw() {
	}

	public int getAsBuiltQuantity() {
		return this.asBuiltQuantity;
	}

	public void setAsBuiltQuantity(int asBuiltQuantity) {
		this.asBuiltQuantity = asBuiltQuantity;
	}

	public String getAssemblyActionCode() {
		return this.assemblyActionCode;
	}

	public void setAssemblyActionCode(String assemblyActionCode) {
		this.assemblyActionCode = assemblyActionCode;
	}

	public String getAssemblyDescription() {
		return this.assemblyDescription;
	}

	public void setAssemblyDescription(String assemblyDescription) {
		this.assemblyDescription = assemblyDescription;
	}

	public String getAssemblyGuid() {
		return this.assemblyGuid;
	}

	public void setAssemblyGuid(String assemblyGuid) {
		this.assemblyGuid = assemblyGuid;
	}

	public int getDesignQuantity() {
		return this.designQuantity;
	}

	public void setDesignQuantity(int designQuantity) {
		this.designQuantity = designQuantity;
	}

	public String getInvoiceStatus() {
		return this.invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public int getStakingSheetId() {
		return this.stakingSheetId;
	}

	public void setStakingSheetId(int stakingSheetId) {
		this.stakingSheetId = stakingSheetId;
	}

}