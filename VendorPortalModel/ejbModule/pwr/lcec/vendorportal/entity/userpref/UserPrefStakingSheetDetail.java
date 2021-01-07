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
	@NamedQuery(name = "UserPrefStakingSheetDetail.findAll", query = "SELECT u FROM UserPrefStakingSheetDetail u"),
	@NamedQuery(name = "UserPrefStakingSheetDetail.findUserPrefStakingDetByUserId", query = "SELECT u FROM UserPrefStakingSheetDetail u WHERE u.userId = :userId AND u.viewName = :viewName") 
})
@Table(schema="sec")
public class UserPrefStakingSheetDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserPrefId")
	private int userPrefId;
	
	@Column(name = "AsBuiltAmount")
	private boolean asBuiltAmount;
	
	@Column(name = "AsBuiltBy")
	private boolean asBuiltBy;
	
	@Column(name = "AsBuiltComments")
	private boolean asBuiltComments;
	
	@Column(name = "AsBuiltDt")
	private boolean asBuiltDt;
	
	@Column(name = "AsBuiltQuantity")
	private boolean asBuiltQuantity;
	
	@Column(name = "AsBuiltStatusId")
	private boolean asBuiltStatusId;
	
	@Column(name = "AssemblyActionCode")
	private boolean assemblyActionCode;
	
	@Column(name = "AssemblyAmount")
	private boolean assemblyAmount;
	
	@Column(name = "AssemblyCreatedDt")
	private boolean assemblyCreatedDt;
	
	@Column(name = "AssemblyDescription")
	private boolean assemblyDescription;
	
	@Column(name = "AssemblyGuid")
	private boolean assemblyGuid;
	
	@Column(name = "AssemblyModifiedDt")
	private boolean assemblyModifiedDt;
	
	@Column(name = "AssemblyQuantity")
	private boolean assemblyQuantity;
	
	@Column(name = "AssemblyRateGroupId")
	private boolean assemblyRateGroupId;
	
	@Column(name = "CurrentInspectedDetailBy")
	private boolean currentInspectedDetailBy;
	
	@Column(name = "CurrentInspectionDetailDt")
	private boolean currentInspectionDetailDt;
	
	@Column(name = "CurrentInspectionDetailId")
	private boolean currentInspectionDetailId;
	
	@Column(name = "CurrentInspectionDetailStatusId")
	private boolean currentInspectionDetailStatusId;
	
	@Column(name = "CurrentInspectorDetailComments")
	private boolean currentInspectorDetailComments;
	
	@Column(name = "Energize")
	private boolean energize;
	
	private boolean GL_AccountId;
	
	@Column(name = "InvoiceApprovedBy")
	private boolean invoiceApprovedBy;
	
	@Column(name = "InvoiceApprovedComment")
	private boolean invoiceApprovedComment;
	
	@Column(name = "InvoiceApprovedDt")
	private boolean invoiceApprovedDt;
	
	@Column(name = "InvoiceDetailId")
	private boolean invoiceDetailId;
	
	@Column(name = "InvoiceId")
	private boolean invoiceId;
	
	@Column(name = "InvoiceStatusId")
	private boolean invoiceStatusId;
	
	@Column(name = "InvoiceSubmitGuid")
	private boolean invoiceSubmitGuid;
	
	@Column(name = "LcecNotes")
	private boolean lcecNotes;
	
	@Column(name = "StakingSheetDetailId")
	private boolean stakingSheetDetailId;
	
	@Column(name = "StakingSheetId")
	private boolean stakingSheetId;
	
	@Column(name = "StakingSource")
	private boolean stakingSource;
	
	@Column(name = "StationDescription")
	private boolean stationDescription;
	
	@Column(name = "StatusDescription")
	private boolean statusDescription;
	
	@Column(name = "StStatusRefGuid")
	private boolean stStatusRefGuid;
	
	@Column(name = "Transfer")
	private boolean transfer;
	
	@Column(name = "UserId")
	private int userId;
	
	@Column(name = "ViewDefault")
	private boolean viewDefault;
	
	@Column(name = "ViewName")
	private String viewName;
	
	@Column(name = "InvoicedBy")
	private boolean invoicedBy;
	
	@Column(name = "InvoicedDt")
	private boolean invoicedDt;

	public UserPrefStakingSheetDetail() {
	}

	public UserPrefStakingSheetDetail(boolean asBuiltAmount, boolean asBuiltBy, boolean asBuiltComments,
			boolean asBuiltDt, boolean asBuiltQuantity, boolean asBuiltStatusId, boolean assemblyActionCode,
			boolean assemblyAmount, boolean assemblyCreatedDt, boolean assemblyDescription, boolean assemblyGuid,
			boolean assemblyModifiedDt, boolean assemblyQuantity, boolean assemblyRateGroupId,
			boolean currentInspectedDetailBy, boolean currentInspectionDetailDt, boolean currentInspectionDetailId,
			boolean currentInspectionDetailStatusId, boolean currentInspectorDetailComments, boolean energize,
			boolean gL_AccountId, boolean invoiceApprovedBy, boolean invoiceApprovedComment, boolean invoiceApprovedDt,
			boolean invoiceDetailId, boolean invoiceId, boolean invoiceStatusId, boolean invoiceSubmitGuid,
			boolean lcecNotes, boolean stakingSheetDetailId, boolean stakingSheetId, boolean stakingSource,
			boolean stationDescription, boolean statusDescription, boolean stStatusRefGuid, boolean transfer,
			int userId, boolean viewDefault, String viewName, boolean invoicedBy, boolean invoicedDt) {
		this.asBuiltAmount = asBuiltAmount;
		this.asBuiltBy = asBuiltBy;
		this.asBuiltComments = asBuiltComments;
		this.asBuiltDt = asBuiltDt;
		this.asBuiltQuantity = asBuiltQuantity;
		this.asBuiltStatusId = asBuiltStatusId;
		this.assemblyActionCode = assemblyActionCode;
		this.assemblyAmount = assemblyAmount;
		this.assemblyCreatedDt = assemblyCreatedDt;
		this.assemblyDescription = assemblyDescription;
		this.assemblyGuid = assemblyGuid;
		this.assemblyModifiedDt = assemblyModifiedDt;
		this.assemblyQuantity = assemblyQuantity;
		this.assemblyRateGroupId = assemblyRateGroupId;
		this.currentInspectedDetailBy = currentInspectedDetailBy;
		this.currentInspectionDetailDt = currentInspectionDetailDt;
		this.currentInspectionDetailId = currentInspectionDetailId;
		this.currentInspectionDetailStatusId = currentInspectionDetailStatusId;
		this.currentInspectorDetailComments = currentInspectorDetailComments;
		this.energize = energize;
		this.GL_AccountId = gL_AccountId;
		this.invoiceApprovedBy = invoiceApprovedBy;
		this.invoiceApprovedComment = invoiceApprovedComment;
		this.invoiceApprovedDt = invoiceApprovedDt;
		this.invoiceDetailId = invoiceDetailId;
		this.invoiceId = invoiceId;
		this.invoiceStatusId = invoiceStatusId;
		this.invoiceSubmitGuid = invoiceSubmitGuid;
		this.lcecNotes = lcecNotes;
		this.stakingSheetDetailId = stakingSheetDetailId;
		this.stakingSheetId = stakingSheetId;
		this.stakingSource = stakingSource;
		this.stationDescription = stationDescription;
		this.statusDescription = statusDescription;
		this.stStatusRefGuid = stStatusRefGuid;
		this.transfer = transfer;
		this.userId = userId;
		this.viewDefault = viewDefault;
		this.viewName = viewName;
		this.invoicedBy = invoicedBy;
		this.invoicedDt = invoicedDt;
	}

	public int getUserPrefId() {
		return this.userPrefId;
	}

	public void setUserPrefId(int userPrefId) {
		this.userPrefId = userPrefId;
	}

	public boolean getAsBuiltAmount() {
		return this.asBuiltAmount;
	}

	public void setAsBuiltAmount(boolean asBuiltAmount) {
		this.asBuiltAmount = asBuiltAmount;
	}

	public boolean getAsBuiltBy() {
		return this.asBuiltBy;
	}

	public void setAsBuiltBy(boolean asBuiltBy) {
		this.asBuiltBy = asBuiltBy;
	}

	public boolean getAsBuiltComments() {
		return this.asBuiltComments;
	}

	public void setAsBuiltComments(boolean asBuiltComments) {
		this.asBuiltComments = asBuiltComments;
	}

	public boolean getAsBuiltDt() {
		return this.asBuiltDt;
	}

	public void setAsBuiltDt(boolean asBuiltDt) {
		this.asBuiltDt = asBuiltDt;
	}

	public boolean getAsBuiltQuantity() {
		return this.asBuiltQuantity;
	}

	public void setAsBuiltQuantity(boolean asBuiltQuantity) {
		this.asBuiltQuantity = asBuiltQuantity;
	}

	public boolean getAsBuiltStatusId() {
		return this.asBuiltStatusId;
	}

	public void setAsBuiltStatusId(boolean asBuiltStatusId) {
		this.asBuiltStatusId = asBuiltStatusId;
	}

	public boolean getAssemblyActionCode() {
		return this.assemblyActionCode;
	}

	public void setAssemblyActionCode(boolean assemblyActionCode) {
		this.assemblyActionCode = assemblyActionCode;
	}

	public boolean getAssemblyAmount() {
		return this.assemblyAmount;
	}

	public void setAssemblyAmount(boolean assemblyAmount) {
		this.assemblyAmount = assemblyAmount;
	}

	public boolean getAssemblyCreatedDt() {
		return this.assemblyCreatedDt;
	}

	public void setAssemblyCreatedDt(boolean assemblyCreatedDt) {
		this.assemblyCreatedDt = assemblyCreatedDt;
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

	public boolean getAssemblyModifiedDt() {
		return this.assemblyModifiedDt;
	}

	public void setAssemblyModifiedDt(boolean assemblyModifiedDt) {
		this.assemblyModifiedDt = assemblyModifiedDt;
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

	public boolean getCurrentInspectedDetailBy() {
		return this.currentInspectedDetailBy;
	}

	public void setCurrentInspectedDetailBy(boolean currentInspectedDetailBy) {
		this.currentInspectedDetailBy = currentInspectedDetailBy;
	}

	public boolean getCurrentInspectionDetailDt() {
		return this.currentInspectionDetailDt;
	}

	public void setCurrentInspectionDetailDt(boolean currentInspectionDetailDt) {
		this.currentInspectionDetailDt = currentInspectionDetailDt;
	}

	public boolean getCurrentInspectionDetailId() {
		return this.currentInspectionDetailId;
	}

	public void setCurrentInspectionDetailId(boolean currentInspectionDetailId) {
		this.currentInspectionDetailId = currentInspectionDetailId;
	}

	public boolean getCurrentInspectionDetailStatusId() {
		return this.currentInspectionDetailStatusId;
	}

	public void setCurrentInspectionDetailStatusId(boolean currentInspectionDetailStatusId) {
		this.currentInspectionDetailStatusId = currentInspectionDetailStatusId;
	}

	public boolean getCurrentInspectorDetailComments() {
		return this.currentInspectorDetailComments;
	}

	public void setCurrentInspectorDetailComments(boolean currentInspectorDetailComments) {
		this.currentInspectorDetailComments = currentInspectorDetailComments;
	}

	public boolean getEnergize() {
		return this.energize;
	}

	public void setEnergize(boolean energize) {
		this.energize = energize;
	}

	public boolean getGL_AccountId() {
		return this.GL_AccountId;
	}

	public void setGL_AccountId(boolean GL_AccountId) {
		this.GL_AccountId = GL_AccountId;
	}

	public boolean getInvoiceApprovedBy() {
		return this.invoiceApprovedBy;
	}

	public void setInvoiceApprovedBy(boolean invoiceApprovedBy) {
		this.invoiceApprovedBy = invoiceApprovedBy;
	}

	public boolean getInvoiceApprovedComment() {
		return this.invoiceApprovedComment;
	}

	public void setInvoiceApprovedComment(boolean invoiceApprovedComment) {
		this.invoiceApprovedComment = invoiceApprovedComment;
	}

	public boolean getInvoiceApprovedDt() {
		return this.invoiceApprovedDt;
	}

	public void setInvoiceApprovedDt(boolean invoiceApprovedDt) {
		this.invoiceApprovedDt = invoiceApprovedDt;
	}

	public boolean getInvoiceDetailId() {
		return this.invoiceDetailId;
	}

	public void setInvoiceDetailId(boolean invoiceDetailId) {
		this.invoiceDetailId = invoiceDetailId;
	}

	public boolean getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(boolean invoiceId) {
		this.invoiceId = invoiceId;
	}

	public boolean getInvoiceStatusId() {
		return this.invoiceStatusId;
	}

	public void setInvoiceStatusId(boolean invoiceStatusId) {
		this.invoiceStatusId = invoiceStatusId;
	}

	public boolean getInvoiceSubmitGuid() {
		return this.invoiceSubmitGuid;
	}

	public void setInvoiceSubmitGuid(boolean invoiceSubmitGuid) {
		this.invoiceSubmitGuid = invoiceSubmitGuid;
	}

	public boolean getLcecNotes() {
		return this.lcecNotes;
	}

	public void setLcecNotes(boolean lcecNotes) {
		this.lcecNotes = lcecNotes;
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

	public boolean getStakingSource() {
		return this.stakingSource;
	}

	public void setStakingSource(boolean stakingSource) {
		this.stakingSource = stakingSource;
	}

	public boolean getStationDescription() {
		return this.stationDescription;
	}

	public void setStationDescription(boolean stationDescription) {
		this.stationDescription = stationDescription;
	}

	public boolean getStatusDescription() {
		return this.statusDescription;
	}

	public void setStatusDescription(boolean statusDescription) {
		this.statusDescription = statusDescription;
	}

	public boolean getStStatusRefGuid() {
		return this.stStatusRefGuid;
	}

	public void setStStatusRefGuid(boolean stStatusRefGuid) {
		this.stStatusRefGuid = stStatusRefGuid;
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

	public boolean isInvoicedDt() {
		return this.invoicedDt;
	}

	public void setInvoicedDt(boolean invoicedDt) {
		this.invoicedDt = invoicedDt;
	}

	public void setInvoicedBy(boolean invoicedBy) {
		this.invoicedBy = invoicedBy;
	}

	public boolean isInvoicedBy() {
		return this.invoicedBy;
	}
}
