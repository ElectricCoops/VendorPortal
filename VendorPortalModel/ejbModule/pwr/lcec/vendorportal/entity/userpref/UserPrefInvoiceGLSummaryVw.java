package pwr.lcec.vendorportal.entity.userpref;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
		@NamedQuery(name = "UserPrefInvoiceGLSummaryVw.findAll", query = "SELECT u FROM UserPrefInvoiceGLSummaryVw u"),
		@NamedQuery(name = "UserPrefInvoiceGLSummaryVw.findInvDetPrefByUserId", query = "SELECT u FROM UserPrefInvoiceGLSummaryVw u WHERE u.userId = :userId") 
	})
@Table(schema="sec")
public class UserPrefInvoiceGLSummaryVw implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserPrefId")
	private int userPrefId;
	
	@Column(name = "AsBuiltQuantity")
	private boolean asBuiltQuantity;
	
	@Column(name = "AssemblyActionCode")
	private boolean assemblyActionCode;
	
	@Column(name = "AssemblyDescription")
	private boolean assemblyDescription;
	
	@Column(name = "AssemblyGuid")
	private boolean assemblyGuid;
	
	@Column(name = "AssemblyQuantity")
	private boolean assemblyQuantity;
	
	@Column(name = "AssemblyRateGroupId")
	private boolean assemblyRateGroupId;
	
	@Column(name = "ConstCost")
	private boolean constCost;
	
	@Column(name = "ConstGLAccount")
	private boolean constGLAccount;
	
	@Column(name = "Energize")
	private boolean energize;
	
	@Column(name = "ExtCost")
	private boolean extCost;
	
	@Column(name = "InvoiceApprovedComment")
	private boolean invoiceApprovedComment;
	
	@Column(name = "InvoiceId")
	private boolean invoiceId;
	
	@Column(name = "InvoiceStatus")
	private boolean invoiceStatus;
	
	@Column(name = "InvoiceStatusId")
	private boolean invoiceStatusId;
	
	@Column(name = "RetireCost")
	private boolean retireCost;
	
	@Column(name = "RetireGlAccount")
	private boolean retireGlAccount;
	
	@Column(name = "StakingSheetDetailId")
	private boolean stakingSheetDetailId;
	
	@Column(name = "StakingSheetId")
	private boolean stakingSheetId;
	
	@Column(name = "StationDescription")
	private boolean stationDescription;
	
	@Column(name = "Transfer")
	private boolean transfer;
	
	@Column(name = "UserId")
	private int userId;
	
	@Column(name = "ViewDefault")
	private boolean viewDefault;
	
	@Column(name = "ViewName")
	private String viewName;

	public UserPrefInvoiceGLSummaryVw() {
	}

	public UserPrefInvoiceGLSummaryVw(boolean asBuiltQuantity, boolean assemblyActionCode, boolean assemblyDescription,
			boolean assemblyGuid, boolean assemblyQuantity, boolean assemblyRateGroupId, boolean constCost,
			boolean constGLAccount, boolean energize, boolean extCost, boolean invoiceApprovedComment,
			boolean invoiceId, boolean invoiceStatus, boolean invoiceStatusId, boolean retireCost,
			boolean retireGlAccount, boolean stakingSheetDetailId, boolean stakingSheetId, boolean stationDescription,
			boolean transfer, int userId, boolean viewDefault, String viewName) {
		this.asBuiltQuantity = asBuiltQuantity;
		this.assemblyActionCode = assemblyActionCode;
		this.assemblyDescription = assemblyDescription;
		this.assemblyGuid = assemblyGuid;
		this.assemblyQuantity = assemblyQuantity;
		this.assemblyRateGroupId = assemblyRateGroupId;
		this.constCost = constCost;
		this.constGLAccount = constGLAccount;
		this.energize = energize;
		this.extCost = extCost;
		this.invoiceApprovedComment = invoiceApprovedComment;
		this.invoiceId = invoiceId;
		this.invoiceStatus = invoiceStatus;
		this.invoiceStatusId = invoiceStatusId;
		this.retireCost = retireCost;
		this.retireGlAccount = retireGlAccount;
		this.stakingSheetDetailId = stakingSheetDetailId;
		this.stakingSheetId = stakingSheetId;
		this.stationDescription = stationDescription;
		this.transfer = transfer;
		this.userId = userId;
		this.viewDefault = viewDefault;
		this.viewName = viewName;
	}

	public int getUserPrefId() {
		return this.userPrefId;
	}

	public void setUserPrefId(int userPrefId) {
		this.userPrefId = userPrefId;
	}

	public boolean getAsBuiltQuantity() {
		return this.asBuiltQuantity;
	}

	public void setAsBuiltQuantity(boolean asBuiltQuantity) {
		this.asBuiltQuantity = asBuiltQuantity;
	}

	public boolean getAssemblyActionCode() {
		return this.assemblyActionCode;
	}

	public void setAssemblyActionCode(boolean assemblyActionCode) {
		this.assemblyActionCode = assemblyActionCode;
	}

	public boolean getAssemblyDescription() {
		return this.assemblyDescription;
	}

	public void setAssemblyDescription(boolean assemblyDescription) {
		this.assemblyDescription = assemblyDescription;
	}

	public boolean getAssemblyGuid() {
		return this.assemblyGuid;
	}

	public void setAssemblyGuid(boolean assemblyGuid) {
		this.assemblyGuid = assemblyGuid;
	}

	public boolean getAssemblyQuantity() {
		return this.assemblyQuantity;
	}

	public void setAssemblyQuantity(boolean assemblyQuantity) {
		this.assemblyQuantity = assemblyQuantity;
	}

	public boolean getAssemblyRateGroupId() {
		return this.assemblyRateGroupId;
	}

	public void setAssemblyRateGroupId(boolean assemblyRateGroupId) {
		this.assemblyRateGroupId = assemblyRateGroupId;
	}

	public boolean getConstCost() {
		return this.constCost;
	}

	public void setConstCost(boolean constCost) {
		this.constCost = constCost;
	}

	public boolean getConstGLAccount() {
		return this.constGLAccount;
	}

	public void setConstGLAccount(boolean constGLAccount) {
		this.constGLAccount = constGLAccount;
	}

	public boolean getEnergize() {
		return this.energize;
	}

	public void setEnergize(boolean energize) {
		this.energize = energize;
	}

	public boolean getExtCost() {
		return this.extCost;
	}

	public void setExtCost(boolean extCost) {
		this.extCost = extCost;
	}

	public boolean getInvoiceApprovedComment() {
		return this.invoiceApprovedComment;
	}

	public void setInvoiceApprovedComment(boolean invoiceApprovedComment) {
		this.invoiceApprovedComment = invoiceApprovedComment;
	}

	public boolean getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(boolean invoiceId) {
		this.invoiceId = invoiceId;
	}

	public boolean getInvoiceStatus() {
		return this.invoiceStatus;
	}

	public void setInvoiceStatus(boolean invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public boolean getInvoiceStatusId() {
		return this.invoiceStatusId;
	}

	public void setInvoiceStatusId(boolean invoiceStatusId) {
		this.invoiceStatusId = invoiceStatusId;
	}

	public boolean getRetireCost() {
		return this.retireCost;
	}

	public void setRetireCost(boolean retireCost) {
		this.retireCost = retireCost;
	}

	public boolean getRetireGlAccount() {
		return this.retireGlAccount;
	}

	public void setRetireGlAccount(boolean retireGlAccount) {
		this.retireGlAccount = retireGlAccount;
	}

	public boolean getStakingSheetDetailId() {
		return this.stakingSheetDetailId;
	}

	public void setStakingSheetDetailId(boolean stakingSheetDetailId) {
		this.stakingSheetDetailId = stakingSheetDetailId;
	}

	public boolean getStakingSheetId() {
		return this.stakingSheetId;
	}

	public void setStakingSheetId(boolean stakingSheetId) {
		this.stakingSheetId = stakingSheetId;
	}

	public boolean getStationDescription() {
		return this.stationDescription;
	}

	public void setStationDescription(boolean stationDescription) {
		this.stationDescription = stationDescription;
	}

	public boolean getTransfer() {
		return this.transfer;
	}

	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean getViewDefault() {
		return this.viewDefault;
	}

	public void setViewDefault(boolean viewDefault) {
		this.viewDefault = viewDefault;
	}

	public String getViewName() {
		return this.viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
}
