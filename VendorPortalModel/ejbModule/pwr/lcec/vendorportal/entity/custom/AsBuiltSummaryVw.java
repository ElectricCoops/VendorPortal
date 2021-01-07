package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.StringUtils;


/**
 * The persistent class for the AsBuiltSummaryVw database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "AsBuiltSummaryVw.findAll", query = "SELECT a FROM AsBuiltSummaryVw a"),
	@NamedQuery(name = "AsBuiltSummaryVw.findByStakingSheetId", query = "SELECT a FROM AsBuiltSummaryVw a WHERE a.stakingSheetId = :sheetId") })
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
	
	private transient String energize;
	
	private transient String transfer;

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

	public String getEnergize() {
		int startPos = this.assemblyGuid.lastIndexOf("E");

		if (StringUtils.substring(assemblyGuid, assemblyGuid.length() - 1).equals("E")) {
			energize = "Energized";
		}else if (StringUtils.substring(this.assemblyGuid, startPos, startPos + 1).equals("E")) {
			energize = "Energized";
		}else {
			energize = "De-Energized";
		} 
		return this.energize;
	}

	public void setEnergize(String energize) {
		this.energize = energize; 
	}

	public String getTransfer() {
		if (StringUtils.substring(assemblyGuid, assemblyGuid.length() - 1).equals("T")) {
			transfer = "Transfer";
		} else {
			transfer = "Non-Transfer";
		} 
		return 	transfer;
	}

	public void setTransfer(String transfer) { 
		this.transfer = transfer; 
	}

}