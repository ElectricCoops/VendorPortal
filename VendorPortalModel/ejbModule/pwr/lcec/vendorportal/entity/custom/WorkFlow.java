package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the WorkFlow database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "WorkFlow.findAll", query = "SELECT w FROM WorkFlow w"),
	@NamedQuery(name = "WorkFlow.findWorkflowById", query = "SELECT w FROM WorkFlow w WHERE w.workOrderId = :woId"),
	@NamedQuery(name = "WorkFlow.findWorkflowByIdWorkGroup", query = "SELECT w FROM WorkFlow w WHERE w.workOrderId = :woId AND w.workGroup = :workgroup"),
	@NamedQuery(name = "WorkFlow.findWorkflowByWfId", query = "SELECT w FROM WorkFlow w WHERE w.workFlowId = :wfId") 
})
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "WorkFlow.updateOverallWorkflowStatus", procedureName = "UPDATE_OVERALL_WORKFLOW_STATUS", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_WorkOrderId"),
			@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "IN_AsBuiltStatusId"),
			@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "IN_InspectionStatusId"),
			@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "IN_InvoiceId"),
			@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "IN_PaymentStatusId") }) 
})
public class WorkFlow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="WorkFlowId")
	private int workFlowId;

	@Column(name="AccountId")
	private String accountId;

	@Column(name="AssignedVendorId")
	private int assignedVendorId;

	@Column(name="DesignCost")
	private BigDecimal designCost;

	@Column(name="DesignTotalAssembly")
	private int designTotalAssembly;

	@Column(name="DesignTotalStation")
	private int designTotalStation;

	@Column(name="DesignUniqueAssembly")
	private int designUniqueAssembly;

	@Column(name="InvoiceCost")
	private BigDecimal invoiceCost;

	@Column(name="InvoiceTotalAssembly")
	private int invoiceTotalAssembly;

	@Column(name="InvoiceTotalStation")
	private int invoiceTotalStation;

	@Column(name="InvoiceUniqueAssembly")
	private int invoiceUniqueAssembly;

	@Column(name="InvoiceVoucherCost")
	private BigDecimal invoiceVoucherCost;

	@Column(name="InvoiceVoucherQty")
	private Integer invoiceVoucherQty;

	@Column(name="InvoiceVoucherStation")
	private Integer invoiceVoucherStation;

	@Column(name="NeededDt")
	private Timestamp neededDt;

	@Column(name="OpenDt")
	private Timestamp openDt;

	@Column(name="OverallAsBuiltStatusId")
	private int overallAsBuiltStatusId;
	
	@OneToOne
	@JoinColumn(name = "OverallAsBuiltStatusId", insertable = false, updatable = false)
	private AsBuiltStatus asBuiltStatus;

	@Column(name="OverallInspectionStatusId")
	private int overallInspectionStatusId;
	
	@OneToOne
	@JoinColumn(name = "OverallInspectionStatusId", insertable = false, updatable = false)
	private InspectionStatus inspectionStatus;

	@Column(name="OverallInvoiceStatusId")
	private int overallInvoiceStatusId;
	
	@OneToOne
	@JoinColumn(name = "OverallInvoiceStatusId", insertable = false, updatable = false)
	private InvoiceStatus invoiceStatus;

	@Column(name="OverallPaymentStatusId")
	private String overallPaymentStatusId;

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

	private String WONotFound;

	@Column(name="WorkEventDt")
	private Timestamp workEventDt;

	@Column(name="WorkEventStatusId")
	private String workEventStatusId;
	
	@OneToOne
	@JoinColumn(name = "WorkEventStatusId", insertable = false, updatable = false)
	private WorkEventStatus workEventStatus;

	@Column(name="WorkGroup")
	private String workGroup;

	@Column(name="WorkOrderId")
	private String workOrderId;

	@Column(name="WorkOrderName")
	private String workOrderName;

	public WorkFlow() {
	}

	public int getWorkFlowId() {
		return this.workFlowId;
	}

	public void setWorkFlowId(int workFlowId) {
		this.workFlowId = workFlowId;
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

	public BigDecimal getDesignCost() {
		return this.designCost;
	}

	public void setDesignCost(BigDecimal designCost) {
		this.designCost = designCost;
	}

	public int getDesignTotalAssembly() {
		return this.designTotalAssembly;
	}

	public void setDesignTotalAssembly(int designTotalAssembly) {
		this.designTotalAssembly = designTotalAssembly;
	}

	public int getDesignTotalStation() {
		return this.designTotalStation;
	}

	public void setDesignTotalStation(int designTotalStation) {
		this.designTotalStation = designTotalStation;
	}

	public int getDesignUniqueAssembly() {
		return this.designUniqueAssembly;
	}

	public void setDesignUniqueAssembly(int designUniqueAssembly) {
		this.designUniqueAssembly = designUniqueAssembly;
	}

	public BigDecimal getInvoiceCost() {
		return this.invoiceCost;
	}

	public void setInvoiceCost(BigDecimal invoiceCost) {
		this.invoiceCost = invoiceCost;
	}

	public int getInvoiceTotalAssembly() {
		return this.invoiceTotalAssembly;
	}

	public void setInvoiceTotalAssembly(int invoiceTotalAssembly) {
		this.invoiceTotalAssembly = invoiceTotalAssembly;
	}

	public int getInvoiceTotalStation() {
		return this.invoiceTotalStation;
	}

	public void setInvoiceTotalStation(int invoiceTotalStation) {
		this.invoiceTotalStation = invoiceTotalStation;
	}

	public int getInvoiceUniqueAssembly() {
		return this.invoiceUniqueAssembly;
	}

	public void setInvoiceUniqueAssembly(int invoiceUniqueAssembly) {
		this.invoiceUniqueAssembly = invoiceUniqueAssembly;
	}

	public BigDecimal getInvoiceVoucherCost() {
		return this.invoiceVoucherCost;
	}

	public void setInvoiceVoucherCost(BigDecimal invoiceVoucherCost) {
		this.invoiceVoucherCost = invoiceVoucherCost;
	}

	public Integer getInvoiceVoucherQty() {
		return this.invoiceVoucherQty;
	}

	public void setInvoiceVoucherQty(Integer invoiceVoucherQty) {
		this.invoiceVoucherQty = invoiceVoucherQty;
	}

	public Integer getInvoiceVoucherStation() {
		return this.invoiceVoucherStation;
	}

	public void setInvoiceVoucherStation(Integer invoiceVoucherStation) {
		this.invoiceVoucherStation = invoiceVoucherStation;
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

	public Integer getOverallAsBuiltStatusId() {
		return this.overallAsBuiltStatusId;
	}

	public void setOverallAsBuiltStatusId(Integer overallAsBuiltStatusId) {
		this.overallAsBuiltStatusId = overallAsBuiltStatusId;
	}

	public int getOverallInspectionStatusId() {
		return this.overallInspectionStatusId;
	}

	public void setOverallInspectionStatusId(int overallInspectionStatusId) {
		this.overallInspectionStatusId = overallInspectionStatusId;
	}

	public int getOverallInvoiceStatusId() {
		return this.overallInvoiceStatusId;
	}

	public void setOverallInvoiceStatusId(int overallInvoiceStatusId) {
		this.overallInvoiceStatusId = overallInvoiceStatusId;
	}

	public String getOverallPaymentStatusId() {
		return this.overallPaymentStatusId;
	}

	public void setOverallPaymentStatusId(String overallPaymentStatusId) {
		this.overallPaymentStatusId = overallPaymentStatusId;
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

	public String getWONotFound() {
		return this.WONotFound;
	}

	public void setWONotFound(String WONotFound) {
		this.WONotFound = WONotFound;
	}

	public Timestamp getWorkEventDt() {
		return this.workEventDt;
	}

	public void setWorkEventDt(Timestamp workEventDt) {
		this.workEventDt = workEventDt;
	}

	public String getWorkEventStatusId() {
		return this.workEventStatusId;
	}

	public void setWorkEventStatusId(String workEventStatusId) {
		this.workEventStatusId = workEventStatusId;
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

	public AsBuiltStatus getAsBuiltStatus() {
		return asBuiltStatus;
	}

	public void setAsBuiltStatus(AsBuiltStatus asBuiltStatus) {
		this.asBuiltStatus = asBuiltStatus;
	}

	public InspectionStatus getInspectionStatus() {
		return inspectionStatus;
	}

	public void setInspectionStatus(InspectionStatus inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}

	public InvoiceStatus getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public WorkEventStatus getWorkEventStatus() {
		return workEventStatus;
	}

	public void setWorkEventStatus(WorkEventStatus workEventStatus) {
		this.workEventStatus = workEventStatus;
	}

}