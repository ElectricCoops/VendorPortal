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
	@NamedQuery(name = "UserPrefVoucher.findAll", query = "SELECT u FROM UserPrefVoucher u"),
	@NamedQuery(name = "UserPrefVoucher.findVoucherPrefByUserId", query = "SELECT u FROM UserPrefVoucher u WHERE u.userId = :userId AND u.viewName = :viewName") 
})
@Table(schema="sec")
public class UserPrefVoucher implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserPrefId")
	private int userPrefId;
	
	@Column(name = "Amount")
	private boolean amount;
	
	@Column(name = "ApprovedBy")
	private boolean approvedBy;
	
	@Column(name = "ApprovedComment")
	private boolean approvedComment;
	
	@Column(name = "ApprovedDt")
	private boolean approvedDt;
	
	@Column(name = "CreatedDt")
	private boolean createdDt;
	
	@Column(name = "Crew")
	private boolean crew;
	
	@Column(name = "Description")
	private boolean description;
	
	private boolean GLAccountId;
	
	private boolean GLAccountIdSplit;
	
	@Column(name = "InspectionComment")
	private boolean inspectionComment;
	
	@Column(name = "InspectionId")
	private boolean inspectionId;
	
	@Column(name = "InspectionStatusId")
	private boolean inspectionStatusId;
	
	@Column(name = "InvoiceDetailId")
	private boolean invoiceDetailId;
	
	@Column(name = "InvoiceId")
	private boolean invoiceId;
	
	@Column(name = "InvoiceStatusId")
	private boolean invoiceStatusId;
	
	@Column(name = "Requestor")
	private boolean requestor;
	
	@Column(name = "ServiceOrderId")
	private boolean serviceOrderId;
	
	@Column(name = "StakingSheetId")
	private boolean stakingSheetId;
	
	@Column(name = "StationDescription")
	private boolean stationDescription;
	
	@Column(name = "SubmitGuid")
	private boolean submitGuid;
	
	@Column(name = "UserId")
	private int userId;
	
	@Column(name = "ViewDefault")
	private boolean viewDefault;
	
	@Column(name = "ViewName")
	private String viewName;
	
	@Column(name = "VoucherId")
	private boolean voucherId;
	
	@Column(name = "WorkOrderId")
	private boolean workOrderId;

	public UserPrefVoucher() {
	}

	public UserPrefVoucher(boolean amount, boolean approvedBy, boolean approvedComment, boolean approvedDt,
			boolean createdDt, boolean crew, boolean description, boolean gLAccountId, boolean gLAccountIdSplit,
			boolean inspectionComment, boolean inspectionId, boolean inspectionStatusId, boolean invoiceDetailId,
			boolean invoiceId, boolean invoiceStatusId, boolean requestor, boolean serviceOrderId,
			boolean stakingSheetId, boolean stationDescription, boolean submitGuid, int userId, boolean viewDefault,
			String viewName, boolean voucherId, boolean workOrderId) {
		this.amount = amount;
		this.approvedBy = approvedBy;
		this.approvedComment = approvedComment;
		this.approvedDt = approvedDt;
		this.createdDt = createdDt;
		this.crew = crew;
		this.description = description;
		this.GLAccountId = gLAccountId;
		this.GLAccountIdSplit = gLAccountIdSplit;
		this.inspectionComment = inspectionComment;
		this.inspectionId = inspectionId;
		this.inspectionStatusId = inspectionStatusId;
		this.invoiceDetailId = invoiceDetailId;
		this.invoiceId = invoiceId;
		this.invoiceStatusId = invoiceStatusId;
		this.requestor = requestor;
		this.serviceOrderId = serviceOrderId;
		this.stakingSheetId = stakingSheetId;
		this.stationDescription = stationDescription;
		this.submitGuid = submitGuid;
		this.userId = userId;
		this.viewDefault = viewDefault;
		this.viewName = viewName;
		this.voucherId = voucherId;
		this.workOrderId = workOrderId;
	}

	public int getUserPrefId() {
		return this.userPrefId;
	}

	public void setUserPrefId(int userPrefId) {
		this.userPrefId = userPrefId;
	}

	public boolean getAmount() {
		return this.amount;
	}

	public void setAmount(boolean amount) {
		this.amount = amount;
	}

	public boolean getApprovedBy() {
		return this.approvedBy;
	}

	public void setApprovedBy(boolean approvedBy) {
		this.approvedBy = approvedBy;
	}

	public boolean getApprovedComment() {
		return this.approvedComment;
	}

	public void setApprovedComment(boolean approvedComment) {
		this.approvedComment = approvedComment;
	}

	public boolean getApprovedDt() {
		return this.approvedDt;
	}

	public void setApprovedDt(boolean approvedDt) {
		this.approvedDt = approvedDt;
	}

	public boolean getCreatedDt() {
		return this.createdDt;
	}

	public void setCreatedDt(boolean createdDt) {
		this.createdDt = createdDt;
	}

	public boolean getCrew() {
		return this.crew;
	}

	public void setCrew(boolean crew) {
		this.crew = crew;
	}

	public boolean getDescription() {
		return this.description;
	}

	public void setDescription(boolean description) {
		this.description = description;
	}

	public boolean getGLAccountId() {
		return this.GLAccountId;
	}

	public void setGLAccountId(boolean GLAccountId) {
		this.GLAccountId = GLAccountId;
	}

	public boolean getGLAccountIdSplit() {
		return this.GLAccountIdSplit;
	}

	public void setGLAccountIdSplit(boolean GLAccountIdSplit) {
		this.GLAccountIdSplit = GLAccountIdSplit;
	}

	public boolean getInspectionComment() {
		return this.inspectionComment;
	}

	public void setInspectionComment(boolean inspectionComment) {
		this.inspectionComment = inspectionComment;
	}

	public boolean getInspectionId() {
		return this.inspectionId;
	}

	public void setInspectionId(boolean inspectionId) {
		this.inspectionId = inspectionId;
	}

	public boolean getInspectionStatusId() {
		return this.inspectionStatusId;
	}

	public void setInspectionStatusId(boolean inspectionStatusId) {
		this.inspectionStatusId = inspectionStatusId;
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

	public boolean getRequestor() {
		return this.requestor;
	}

	public void setRequestor(boolean requestor) {
		this.requestor = requestor;
	}

	public boolean getServiceOrderId() {
		return this.serviceOrderId;
	}

	public void setServiceOrderId(boolean serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
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

	public boolean getSubmitGuid() {
		return this.submitGuid;
	}

	public void setSubmitGuid(boolean submitGuid) {
		this.submitGuid = submitGuid;
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

	public boolean getVoucherId() {
		return this.voucherId;
	}

	public void setVoucherId(boolean voucherId) {
		this.voucherId = voucherId;
	}

	public boolean getWorkOrderId() {
		return this.workOrderId;
	}

	public void setWorkOrderId(boolean workOrderId) {
		this.workOrderId = workOrderId;
	}
}
