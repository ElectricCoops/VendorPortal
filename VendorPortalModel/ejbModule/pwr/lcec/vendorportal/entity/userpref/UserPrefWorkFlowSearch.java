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
	@NamedQuery(name = "UserPrefWorkFlowSearch.findAll", query = "SELECT u FROM UserPrefWorkFlowSearch u"),
	@NamedQuery(name = "UserPrefWorkFlowSearch.findAllWFSearchPrefByUserId", query = "SELECT u FROM UserPrefWorkFlowSearch u WHERE u.userId = :userId") 
})
@Table(schema="sec")
public class UserPrefWorkFlowSearch implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserPrefId")
	private Integer userPrefId;
	
	@Column(name = "AccountId")
	private boolean accountId;
	
	@Column(name = "AssignedVendorId")
	private boolean assignedVendorId;
	
	@Column(name = "EnterTypeCode")
	private boolean enterTypeCode;
	
	private boolean LCECComments;
	
	@Column(name = "NeededDt")
	private boolean neededDt;
	
	@Column(name = "OpenDt")
	private boolean openDt;
	
	@Column(name = "OverallAsBuiltStatus")
	private boolean overallAsBuiltStatus;
	
	@Column(name = "OverallAsBuiltStatusId")
	private boolean overallAsBuiltStatusId;
	
	@Column(name = "OverallInspectionStatus")
	private boolean overallInspectionStatus;
	
	@Column(name = "OverallInspectionStatusId")
	private boolean overallInspectionStatusId;
	
	@Column(name = "OverallInvoiceStatus")
	private boolean overallInvoiceStatus;
	
	@Column(name = "OverallInvoiceStatusId")
	private boolean overallInvoiceStatusId;
	
	@Column(name = "ResourceId")
	private boolean resourceId;
	
	@Column(name = "ResourceName")
	private boolean resourceName;
	
	@Column(name = "ServiceLocationId")
	private boolean serviceLocationId;
	
	@Column(name = "ServiceOrderId")
	private boolean serviceOrderId;
	
	@Column(name = "ServiceOrderPriority")
	private boolean serviceOrderPriority;
	
	@Column(name = "ServiceOrderType")
	private boolean serviceOrderType;
	
	@Column(name = "SoFullName")
	private boolean soFullName;
	
	@Column(name = "SoFunction")
	private boolean soFunction;
	
	@Column(name = "SoStatCode")
	private boolean soStatCode;
	
	@Column(name = "SoTypeCode")
	private boolean soTypeCode;
	
	@Column(name = "SoTypeCodeDescription")
	private boolean soTypeCodeDescription;
	
	@Column(name = "StakingSheetId")
	private boolean stakingSheetId;
	
	@Column(name = "UserId")
	private int userId;
	
	@Column(name = "UserName")
	private boolean userName;
	
	@Column(name = "ViewDefault")
	private boolean viewDefault;
	
	@Column(name = "ViewName")
	private String viewName;
	
	@Column(name = "WorkEventDt")
	private boolean workEventDt;
	
	@Column(name = "WorkEventStatus")
	private boolean workEventStatus;
	
	@Column(name = "WorkEventStatusId")
	private boolean workEventStatusId;
	
	@Column(name = "WorkFlowId")
	private boolean workFlowId;
	
	@Column(name = "WorkGroup")
	private boolean workGroup;
	
	@Column(name = "WorkOrderId")
	private boolean workOrderId;
	
	@Column(name = "WorkOrderName")
	private boolean workOrderName;

	public UserPrefWorkFlowSearch() {
	}

	public UserPrefWorkFlowSearch(boolean accountId, boolean assignedVendorId, boolean enterTypeCode,
			boolean lCECComments, boolean neededDt, boolean openDt, boolean overallAsBuiltStatus,
			boolean overallAsBuiltStatusId, boolean overallInspectionStatus, boolean overallInspectionStatusId,
			boolean overallInvoiceStatus, boolean overallInvoiceStatusId, boolean resourceId, boolean resourceName,
			boolean serviceLocationId, boolean serviceOrderId, boolean serviceOrderPriority, boolean serviceOrderType,
			boolean soFullName, boolean soFunction, boolean soStatCode, boolean soTypeCode,
			boolean soTypeCodeDescription, boolean stakingSheetId, int userId, boolean userName, boolean viewDefault,
			String viewName, boolean workEventDt, boolean workEventStatus, boolean workEventStatusId,
			boolean workFlowId, boolean workGroup, boolean workOrderId, boolean workOrderName) {
		this.accountId = accountId;
		this.assignedVendorId = assignedVendorId;
		this.enterTypeCode = enterTypeCode;
		this.LCECComments = lCECComments;
		this.neededDt = neededDt;
		this.openDt = openDt;
		this.overallAsBuiltStatus = overallAsBuiltStatus;
		this.overallAsBuiltStatusId = overallAsBuiltStatusId;
		this.overallInspectionStatus = overallInspectionStatus;
		this.overallInspectionStatusId = overallInspectionStatusId;
		this.overallInvoiceStatus = overallInvoiceStatus;
		this.overallInvoiceStatusId = overallInvoiceStatusId;
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.serviceLocationId = serviceLocationId;
		this.serviceOrderId = serviceOrderId;
		this.serviceOrderPriority = serviceOrderPriority;
		this.serviceOrderType = serviceOrderType;
		this.soFullName = soFullName;
		this.soFunction = soFunction;
		this.soStatCode = soStatCode;
		this.soTypeCode = soTypeCode;
		this.soTypeCodeDescription = soTypeCodeDescription;
		this.stakingSheetId = stakingSheetId;
		this.userId = userId;
		this.userName = userName;
		this.viewDefault = viewDefault;
		this.viewName = viewName;
		this.workEventDt = workEventDt;
		this.workEventStatus = workEventStatus;
		this.workEventStatusId = workEventStatusId;
		this.workFlowId = workFlowId;
		this.workGroup = workGroup;
		this.workOrderId = workOrderId;
		this.workOrderName = workOrderName;
	}

	public Integer getUserPrefId() {
		return this.userPrefId;
	}

	public void setUserPrefId(Integer userPrefId) {
		this.userPrefId = userPrefId;
	}

	public boolean getAccountId() {
		return this.accountId;
	}

	public void setAccountId(boolean accountId) {
		this.accountId = accountId;
	}

	public boolean getAssignedVendorId() {
		return this.assignedVendorId;
	}

	public void setAssignedVendorId(boolean assignedVendorId) {
		this.assignedVendorId = assignedVendorId;
	}

	public boolean getEnterTypeCode() {
		return this.enterTypeCode;
	}

	public void setEnterTypeCode(boolean enterTypeCode) {
		this.enterTypeCode = enterTypeCode;
	}

	public boolean getLCECComments() {
		return this.LCECComments;
	}

	public void setLCECComments(boolean LCECComments) {
		this.LCECComments = LCECComments;
	}

	public boolean getNeededDt() {
		return this.neededDt;
	}

	public void setNeededDt(boolean neededDt) {
		this.neededDt = neededDt;
	}

	public boolean getOpenDt() {
		return this.openDt;
	}

	public void setOpenDt(boolean openDt) {
		this.openDt = openDt;
	}

	public boolean getOverallAsBuiltStatus() {
		return this.overallAsBuiltStatus;
	}

	public void setOverallAsBuiltStatus(boolean overallAsBuiltStatus) {
		this.overallAsBuiltStatus = overallAsBuiltStatus;
	}

	public boolean getOverallAsBuiltStatusId() {
		return this.overallAsBuiltStatusId;
	}

	public void setOverallAsBuiltStatusId(boolean overallAsBuiltStatusId) {
		this.overallAsBuiltStatusId = overallAsBuiltStatusId;
	}

	public boolean getOverallInspectionStatus() {
		return this.overallInspectionStatus;
	}

	public void setOverallInspectionStatus(boolean overallInspectionStatus) {
		this.overallInspectionStatus = overallInspectionStatus;
	}

	public boolean getOverallInspectionStatusId() {
		return this.overallInspectionStatusId;
	}

	public void setOverallInspectionStatusId(boolean overallInspectionStatusId) {
		this.overallInspectionStatusId = overallInspectionStatusId;
	}

	public boolean getOverallInvoiceStatus() {
		return this.overallInvoiceStatus;
	}

	public void setOverallInvoiceStatus(boolean overallInvoiceStatus) {
		this.overallInvoiceStatus = overallInvoiceStatus;
	}

	public boolean getOverallInvoiceStatusId() {
		return this.overallInvoiceStatusId;
	}

	public void setOverallInvoiceStatusId(boolean overallInvoiceStatusId) {
		this.overallInvoiceStatusId = overallInvoiceStatusId;
	}

	public boolean getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(boolean resourceId) {
		this.resourceId = resourceId;
	}

	public boolean getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(boolean resourceName) {
		this.resourceName = resourceName;
	}

	public boolean getServiceLocationId() {
		return this.serviceLocationId;
	}

	public void setServiceLocationId(boolean serviceLocationId) {
		this.serviceLocationId = serviceLocationId;
	}

	public boolean getServiceOrderId() {
		return this.serviceOrderId;
	}

	public void setServiceOrderId(boolean serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}

	public boolean getServiceOrderPriority() {
		return this.serviceOrderPriority;
	}

	public void setServiceOrderPriority(boolean serviceOrderPriority) {
		this.serviceOrderPriority = serviceOrderPriority;
	}

	public boolean getServiceOrderType() {
		return this.serviceOrderType;
	}

	public void setServiceOrderType(boolean serviceOrderType) {
		this.serviceOrderType = serviceOrderType;
	}

	public boolean getSoFullName() {
		return this.soFullName;
	}

	public void setSoFullName(boolean soFullName) {
		this.soFullName = soFullName;
	}

	public boolean getSoFunction() {
		return this.soFunction;
	}

	public void setSoFunction(boolean soFunction) {
		this.soFunction = soFunction;
	}

	public boolean getSoStatCode() {
		return this.soStatCode;
	}

	public void setSoStatCode(boolean soStatCode) {
		this.soStatCode = soStatCode;
	}

	public boolean getSoTypeCode() {
		return this.soTypeCode;
	}

	public void setSoTypeCode(boolean soTypeCode) {
		this.soTypeCode = soTypeCode;
	}

	public boolean getSoTypeCodeDescription() {
		return this.soTypeCodeDescription;
	}

	public void setSoTypeCodeDescription(boolean soTypeCodeDescription) {
		this.soTypeCodeDescription = soTypeCodeDescription;
	}

	public boolean getStakingSheetId() {
		return this.stakingSheetId;
	}

	public void setStakingSheetId(boolean stakingSheetId) {
		this.stakingSheetId = stakingSheetId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean getUserName() {
		return this.userName;
	}

	public void setUserName(boolean userName) {
		this.userName = userName;
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

	public boolean getWorkEventDt() {
		return this.workEventDt;
	}

	public void setWorkEventDt(boolean workEventDt) {
		this.workEventDt = workEventDt;
	}

	public boolean getWorkEventStatus() {
		return this.workEventStatus;
	}

	public void setWorkEventStatus(boolean workEventStatus) {
		this.workEventStatus = workEventStatus;
	}

	public boolean getWorkEventStatusId() {
		return this.workEventStatusId;
	}

	public void setWorkEventStatusId(boolean workEventStatusId) {
		this.workEventStatusId = workEventStatusId;
	}

	public boolean getWorkFlowId() {
		return this.workFlowId;
	}

	public void setWorkFlowId(boolean workFlowId) {
		this.workFlowId = workFlowId;
	}

	public boolean getWorkGroup() {
		return this.workGroup;
	}

	public void setWorkGroup(boolean workGroup) {
		this.workGroup = workGroup;
	}

	public boolean getWorkOrderId() {
		return this.workOrderId;
	}

	public void setWorkOrderId(boolean workOrderId) {
		this.workOrderId = workOrderId;
	}

	public boolean getWorkOrderName() {
		return this.workOrderName;
	}

	public void setWorkOrderName(boolean workOrderName) {
		this.workOrderName = workOrderName;
	}

	public String toString() {
		return "UserPrefWorkFlowSearch [userPrefId=" + this.userPrefId + ", accountId=" + this.accountId
				+ ", assignedVendorId=" + this.assignedVendorId + ", enterTypeCode=" + this.enterTypeCode
				+ ", LCECComments=" + this.LCECComments + ", neededDt=" + this.neededDt + ", openDt=" + this.openDt
				+ ", overallAsBuiltStatus=" + this.overallAsBuiltStatus + ", overallAsBuiltStatusId="
				+ this.overallAsBuiltStatusId + ", overallInspectionStatus=" + this.overallInspectionStatus
				+ ", overallInspectionStatusId=" + this.overallInspectionStatusId + ", overallInvoiceStatus="
				+ this.overallInvoiceStatus + ", overallInvoiceStatusId=" + this.overallInvoiceStatusId
				+ ", resourceId=" + this.resourceId + ", resourceName=" + this.resourceName + ", serviceLocationId="
				+ this.serviceLocationId + ", serviceOrderId=" + this.serviceOrderId + ", serviceOrderPriority="
				+ this.serviceOrderPriority + ", serviceOrderType=" + this.serviceOrderType + ", soFullName="
				+ this.soFullName + ", soFunction=" + this.soFunction + ", soStatCode=" + this.soStatCode
				+ ", soTypeCode=" + this.soTypeCode + ", soTypeCodeDescription=" + this.soTypeCodeDescription
				+ ", stakingSheetId=" + this.stakingSheetId + ", userId=" + this.userId + ", userName=" + this.userName
				+ ", viewDefault=" + this.viewDefault + ", viewName=" + this.viewName + ", workEventDt="
				+ this.workEventDt + ", workEventStatus=" + this.workEventStatus + ", workEventStatusId="
				+ this.workEventStatusId + ", workFlowId=" + this.workFlowId + ", workGroup=" + this.workGroup
				+ ", workOrderId=" + this.workOrderId + ", workOrderName=" + this.workOrderName + "]";
	}
}
