package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the WorkFlowSearch_VW database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "WorkFlowSearch_VW.findAllLCEC", query = "SELECT w FROM WorkFlowSearch_VW w"),
	@NamedQuery(name = "WorkFlowSearch_VW.findLCECWorkflowByWoId", query = "SELECT w FROM WorkFlowSearch_VW w WHERE w.workOrderId = :woId"),
	@NamedQuery(name = "WorkFlowSearch_VW.findLCECWorkflowBySoId", query = "SELECT w FROM WorkFlowSearch_VW w WHERE w.serviceOrderId = :soId"),
	@NamedQuery(name = "WorkFlowSearch_VW.findLCECWorkflowBystatusId", query = "SELECT w FROM WorkFlowSearch_VW w WHERE w.workEventStatusId = :statusId"),
	@NamedQuery(name = "WorkFlowSearch_VW.findLCECWorkflowByWoAndSoId", query = "SELECT w FROM WorkFlowSearch_VW w WHERE w.workOrderId = :woId AND w.serviceOrderId = :soId"),
	@NamedQuery(name = "WorkFlowSearch_VW.findLCECWorkflowByWoSoIdAndStatusId", query = "SELECT w FROM WorkFlowSearch_VW w WHERE w.workOrderId = :woId AND w.serviceOrderId = :soId AND w.workEventStatusId = :statusId"),
	@NamedQuery(name = "WorkFlowSearch_VW.findAll", query = "SELECT w FROM WorkFlowSearch_VW w WHERE w.workGroup = :workgrp"),
	@NamedQuery(name = "WorkFlowSearch_VW.findWorkflowByWoId", query = "SELECT w FROM WorkFlowSearch_VW w WHERE w.workOrderId = :woId AND w.workGroup = :workgrp"),
	@NamedQuery(name = "WorkFlowSearch_VW.findWorkflowBySoId", query = "SELECT w FROM WorkFlowSearch_VW w WHERE w.serviceOrderId = :soId AND w.workGroup = :workgrp"),
	@NamedQuery(name = "WorkFlowSearch_VW.findWorkflowByStakingSheetId", query = "SELECT w FROM WorkFlowSearch_VW w WHERE w.stakingSheetId = :stakingSheetId"),
	@NamedQuery(name = "WorkFlowSearch_VW.findWorkflowBystatusId", query = "SELECT w FROM WorkFlowSearch_VW w WHERE w.workEventStatusId = :statusId AND w.workGroup = :workgrp"),
	@NamedQuery(name = "WorkFlowSearch_VW.findWorkflowByWoAndSoId", query = "SELECT w FROM WorkFlowSearch_VW w WHERE w.workOrderId = :woId AND w.serviceOrderId = :soId AND w.workGroup = :workgrp"),
	@NamedQuery(name = "WorkFlowSearch_VW.findWorkflowByWoSoIdAndStatusId", query = "SELECT w FROM WorkFlowSearch_VW w WHERE w.workOrderId = :woId AND w.serviceOrderId = :soId AND w.workEventStatusId = :statusId AND w.workGroup = :workgrp"),
	@NamedQuery(name = "WorkFlowSearch_VW.findAllWorkFlowWoId", query = "SELECT w.workOrderId FROM WorkFlowSearch_VW w WHERE w.workGroup = :wrkgrp AND w.workOrderId <> :woId") 
})
public class WorkFlowSearch_VW implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AccountId")
	private String accountId;

	@Column(name="AssignedVendorId")
	private int assignedVendorId;

	@Column(name="EnterTypeCode")
	private String enterTypeCode;

	private String LCECComments;

	@Column(name="NeededDt")
	private Timestamp neededDt;

	@Column(name="OpenDt")
	private Timestamp openDt;

	@Column(name="OverallAsBuiltStatus")
	private String overallAsBuiltStatus;

	@Column(name="OverallAsBuiltStatusId")
	private int overallAsBuiltStatusId;

	@Column(name="OverallInspectionStatus")
	private String overallInspectionStatus;

	@Column(name="OverallInspectionStatusId")
	private Integer overallInspectionStatusId;

	@Column(name="OverallInvoiceStatus")
	private String overallInvoiceStatus;

	@Column(name="OverallInvoiceStatusId")
	private Integer overallInvoiceStatusId;

	@Column(name="ResourceId")
	private int resourceId;

	@Column(name="ResourceName")
	private String resourceName;

	@Column(name="ServiceLocationId")
	private int serviceLocationId;

	@Column(name="ServiceOrderId")
	private String serviceOrderId;

	@Column(name="ServiceOrderPriority")
	private String serviceOrderPriority;

	@Column(name="ServiceOrderType")
	private String serviceOrderType;

	@Column(name="SoFullName")
	private String soFullName;

	@Column(name="SoFunction")
	private String soFunction;

	@Column(name="SoStatCode")
	private String soStatCode;

	@Column(name="SoTypeCode")
	private String soTypeCode;

	@Column(name="SoTypeCodeDescription")
	private String soTypeCodeDescription;

	@Column(name="StakingSheetId")
	private int stakingSheetId;

	@Column(name="UserName")
	private String userName;

	@Column(name="WorkEventDt")
	private Timestamp workEventDt;

	@Column(name="WorkEventStatus")
	private String workEventStatus;

	@Column(name="WorkEventStatusId")
	private String workEventStatusId;

	@Id
	@Column(name="WorkFlowId")
	private int workFlowId;

	@Column(name="WorkGroup")
	private String workGroup;

	@Column(name="WorkOrderId")
	private String workOrderId;

	@Column(name="WorkOrderName")
	private String workOrderName;

	public WorkFlowSearch_VW() {
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public int getAssignedVendorId() {
		return this.assignedVendorId;
	}

	public void setAssignedVendorId(int assignedVendorId) {
		this.assignedVendorId = assignedVendorId;
	}

	public String getEnterTypeCode() {
		return this.enterTypeCode;
	}

	public void setEnterTypeCode(String enterTypeCode) {
		this.enterTypeCode = enterTypeCode;
	}

	public String getLCECComments() {
		return this.LCECComments;
	}

	public void setLCECComments(String LCECComments) {
		this.LCECComments = LCECComments;
	}

	public Timestamp getNeededDt() {
		return this.neededDt;
	}

	public void setNeededDt(Timestamp neededDt) {
		this.neededDt = neededDt;
	}

	public Timestamp getOpenDt() {
		return this.openDt;
	}

	public void setOpenDt(Timestamp openDt) {
		this.openDt = openDt;
	}

	public String getOverallAsBuiltStatus() {
		return this.overallAsBuiltStatus;
	}

	public void setOverallAsBuiltStatus(String overallAsBuiltStatus) {
		this.overallAsBuiltStatus = overallAsBuiltStatus;
	}

	public int getOverallAsBuiltStatusId() {
		return this.overallAsBuiltStatusId;
	}

	public void setOverallAsBuiltStatusId(int overallAsBuiltStatusId) {
		this.overallAsBuiltStatusId = overallAsBuiltStatusId;
	}

	public String getOverallInspectionStatus() {
		return this.overallInspectionStatus;
	}

	public void setOverallInspectionStatus(String overallInspectionStatus) {
		this.overallInspectionStatus = overallInspectionStatus;
	}

	public Integer getOverallInspectionStatusId() {
		return this.overallInspectionStatusId;
	}

	public void setOverallInspectionStatusId(Integer overallInspectionStatusId) {
		this.overallInspectionStatusId = overallInspectionStatusId;
	}

	public String getOverallInvoiceStatus() {
		return this.overallInvoiceStatus;
	}

	public void setOverallInvoiceStatus(String overallInvoiceStatus) {
		this.overallInvoiceStatus = overallInvoiceStatus;
	}

	public Integer getOverallInvoiceStatusId() {
		return this.overallInvoiceStatusId;
	}

	public void setOverallInvoiceStatusId(Integer overallInvoiceStatusId) {
		this.overallInvoiceStatusId = overallInvoiceStatusId;
	}

	public int getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public int getServiceLocationId() {
		return this.serviceLocationId;
	}

	public void setServiceLocationId(int serviceLocationId) {
		this.serviceLocationId = serviceLocationId;
	}

	public String getServiceOrderId() {
		return this.serviceOrderId;
	}

	public void setServiceOrderId(String serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}

	public String getServiceOrderPriority() {
		return this.serviceOrderPriority;
	}

	public void setServiceOrderPriority(String serviceOrderPriority) {
		this.serviceOrderPriority = serviceOrderPriority;
	}

	public String getServiceOrderType() {
		return this.serviceOrderType;
	}

	public void setServiceOrderType(String serviceOrderType) {
		this.serviceOrderType = serviceOrderType;
	}

	public String getSoFullName() {
		return this.soFullName;
	}

	public void setSoFullName(String soFullName) {
		this.soFullName = soFullName;
	}

	public String getSoFunction() {
		return this.soFunction;
	}

	public void setSoFunction(String soFunction) {
		this.soFunction = soFunction;
	}

	public String getSoStatCode() {
		return this.soStatCode;
	}

	public void setSoStatCode(String soStatCode) {
		this.soStatCode = soStatCode;
	}

	public String getSoTypeCode() {
		return this.soTypeCode;
	}

	public void setSoTypeCode(String soTypeCode) {
		this.soTypeCode = soTypeCode;
	}

	public String getSoTypeCodeDescription() {
		return this.soTypeCodeDescription;
	}

	public void setSoTypeCodeDescription(String soTypeCodeDescription) {
		this.soTypeCodeDescription = soTypeCodeDescription;
	}

	public int getStakingSheetId() {
		return this.stakingSheetId;
	}

	public void setStakingSheetId(int stakingSheetId) {
		this.stakingSheetId = stakingSheetId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getWorkEventDt() {
		return this.workEventDt;
	}

	public void setWorkEventDt(Timestamp workEventDt) {
		this.workEventDt = workEventDt;
	}

	public String getWorkEventStatus() {
		return this.workEventStatus;
	}

	public void setWorkEventStatus(String workEventStatus) {
		this.workEventStatus = workEventStatus;
	}

	public String getWorkEventStatusId() {
		return this.workEventStatusId;
	}

	public void setWorkEventStatusId(String workEventStatusId) {
		this.workEventStatusId = workEventStatusId;
	}

	public int getWorkFlowId() {
		return this.workFlowId;
	}

	public void setWorkFlowId(int workFlowId) {
		this.workFlowId = workFlowId;
	}

	public String getWorkGroup() {
		return this.workGroup;
	}

	public void setWorkGroup(String workGroup) {
		this.workGroup = workGroup;
	}

	public String getWorkOrderId() {
		return this.workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

	public String getWorkOrderName() {
		return this.workOrderName;
	}

	public void setWorkOrderName(String workOrderName) {
		this.workOrderName = workOrderName;
	}

}