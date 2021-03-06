package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;


/**
 * The persistent class for the InvoiceGLSummaryVw database table.
 * 
 */
@Entity
@NamedQuery(name = "InvoiceGLSummaryVw.findAll", query = "SELECT i FROM InvoiceGLSummaryVw i WHERE i.invoiceId = :invoiceId")
public class InvoiceGLSummaryVw implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AsBuiltQuantity")
	private int asBuiltQuantity;

	@Column(name="AssemblyActionCode")
	private String assemblyActionCode;

	@Column(name="AssemblyDescription")
	private String assemblyDescription;

	@Column(name="AssemblyGuid")
	private String assemblyGuid;

	@Column(name="AssemblyQuantity")
	private int assemblyQuantity;

	@Column(name="AssemblyRateGroupId")
	private int assemblyRateGroupId;

	@Column(name="ConstCost")
	private BigDecimal constCost;

	@Column(name="ConstGLAccount")
	private BigDecimal constGLAccount;

	@Column(name="InvoiceApprovedComment")
	private String invoiceApprovedComment;

	@Column(name="InvoiceId")
	private int invoiceId;

	@Column(name="InvoiceStatus")
	private String invoiceStatus;

	@Column(name="InvoiceStatusId")
	private int invoiceStatusId;

	@Column(name="RetireCost")
	private BigDecimal retireCost;

	@Column(name="RetireGlAccount")
	private BigDecimal retireGlAccount;

	@Id
	@Column(name="StakingSheetDetailId")
	private String stakingSheetDetailId;

	@Column(name="StakingSheetId")
	private int stakingSheetId;

	@Column(name="StationDescription")
	private String stationDescription;
	
	private transient BigDecimal extCost;
	
	private transient String energize;
	  
	private transient String transfer;

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

	public String getInvoiceApprovedComment() {
		return this.invoiceApprovedComment;
	}

	public void setInvoiceApprovedComment(String invoiceApprovedComment) {
		this.invoiceApprovedComment = invoiceApprovedComment;
	}

	public int getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceStatus() {
		return this.invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public int getInvoiceStatusId() {
		return this.invoiceStatusId;
	}

	public void setInvoiceStatusId(int invoiceStatusId) {
		this.invoiceStatusId = invoiceStatusId;
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
	    this.extCost = BigDecimal.ZERO;
	    if (this.assemblyActionCode.equals("C")) {
	      if (this.constCost != null) {
	        this.extCost = this.constCost.multiply(new BigDecimal(this.asBuiltQuantity));
	      } else {
	        this.extCost = BigDecimal.ZERO;
	      } 
	    } else if (this.assemblyActionCode.equals("R")) {
	      if (this.retireCost != null) {
	        this.extCost = this.retireCost.multiply(new BigDecimal(this.asBuiltQuantity));
	      } else {
	        this.extCost = BigDecimal.ZERO;
	      } 
	    } 
	    return this.extCost;
	  }
	  
	public void setExtCost(BigDecimal extCost) {
		this.extCost = extCost;
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