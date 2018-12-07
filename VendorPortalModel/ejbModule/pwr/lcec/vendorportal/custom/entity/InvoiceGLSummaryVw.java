package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the InvoiceGLSummaryVw database table.
 * 
 */
@Entity
@NamedQuery(name = "InvoiceGLSummaryVw.findAll", query = "SELECT i FROM InvoiceGLSummaryVw i WHERE i.invoiceId = :invoiceId")
public class InvoiceGLSummaryVw implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "AsBuiltQuantity")
	private int asBuiltQuantity;

	@Column(name = "AssemblyActionCode")
	private String assemblyActionCode;

	@Column(name = "AssemblyDescription")
	private String assemblyDescription;

	@Column(name = "AssemblyGuid")
	private String assemblyGuid;

	@Column(name = "AssemblyQuantity")
	private int assemblyQuantity;

	@Column(name = "AssemblyRateGroupId")
	private int assemblyRateGroupId;

	@Column(name = "ConstCost")
	private BigDecimal constCost;

	@Column(name = "ConstGLAccount")
	private BigDecimal constGLAccount;

	@Column(name = "InvoiceId")
	private int invoiceId;

	@Column(name = "RetireCost")
	private BigDecimal retireCost;

	@Column(name = "RetireGlAccount")
	private BigDecimal retireGlAccount;

	@Id
	@Column(name = "StakingSheetDetailId")
	private String stakingSheetDetailId;

	@Column(name = "StakingSheetId")
	private int stakingSheetId;

	@Column(name = "StationDescription")
	private String stationDescription;
	
	@Column(name = "InvoiceStatusId")
	private String invoiceStatusId;
	
	@Column(name = "InvoiceStatus")
	private String invoiceStatus;
	
	@Column(name = "InvoiceApprovedComment")
	private String invoiceApprovedComment;

	private transient BigDecimal extCost;

	public InvoiceGLSummaryVw() {
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

	public int getAssemblyQuantity() {
		return this.assemblyQuantity;
	}

	public void setAssemblyQuantity(int assemblyQuantity) {
		this.assemblyQuantity = assemblyQuantity;
	}

	public int getAssemblyRateGroupId() {
		return this.assemblyRateGroupId;
	}

	public void setAssemblyRateGroupId(int assemblyRateGroupId) {
		this.assemblyRateGroupId = assemblyRateGroupId;
	}

	public BigDecimal getConstCost() {
		return this.constCost;
	}

	public void setConstCost(BigDecimal constCost) {
		this.constCost = constCost;
	}

	public BigDecimal getConstGLAccount() {
		return this.constGLAccount;
	}

	public void setConstGLAccount(BigDecimal constGLAccount) {
		this.constGLAccount = constGLAccount;
	}

	public int getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public BigDecimal getRetireCost() {
		return this.retireCost;
	}

	public void setRetireCost(BigDecimal retireCost) {
		this.retireCost = retireCost;
	}

	public BigDecimal getRetireGlAccount() {
		return this.retireGlAccount;
	}

	public void setRetireGlAccount(BigDecimal retireGlAccount) {
		this.retireGlAccount = retireGlAccount;
	}

	public String getStakingSheetDetailId() {
		return this.stakingSheetDetailId;
	}

	public void setStakingSheetDetailId(String stakingSheetDetailId) {
		this.stakingSheetDetailId = stakingSheetDetailId;
	}

	public int getStakingSheetId() {
		return this.stakingSheetId;
	}

	public void setStakingSheetId(int stakingSheetId) {
		this.stakingSheetId = stakingSheetId;
	}

	public String getStationDescription() {
		return this.stationDescription;
	}

	public void setStationDescription(String stationDescription) {
		this.stationDescription = stationDescription;
	}

	public BigDecimal getExtCost() {

		extCost = BigDecimal.ZERO;
		if (assemblyActionCode.equals("C")) {
			if (constCost != null) {
				extCost = constCost.multiply(new BigDecimal(asBuiltQuantity));
			} else {
				extCost = BigDecimal.ZERO;
			}
		} else if (assemblyActionCode.equals("R")) {
			if (retireCost != null) {
				extCost = retireCost.multiply(new BigDecimal(asBuiltQuantity));
			} else {
				extCost = BigDecimal.ZERO;
			}
		}
		return extCost;
	}

	public void setExtCost(BigDecimal extCost) {
		this.extCost = extCost;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getInvoiceApprovedComment() {
		return invoiceApprovedComment;
	}

	public void setInvoiceApprovedComment(String invoiceApprovedComment) {
		this.invoiceApprovedComment = invoiceApprovedComment;
	}

	public String getInvoiceStatusId() {
		return invoiceStatusId;
	}

	public void setInvoiceStatusId(String invoiceStatusId) {
		this.invoiceStatusId = invoiceStatusId;
	}

}