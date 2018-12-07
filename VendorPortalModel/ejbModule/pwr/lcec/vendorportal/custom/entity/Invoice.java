package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The persistent class for the Invoice database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i ORDER BY i.invoicedDt DESC"),
		@NamedQuery(name = "Invoice.findByWoId", query = "SELECT i FROM Invoice i WHERE i.workOrderId = :woId ORDER BY i.invoicedDt DESC"),
		@NamedQuery(name = "Invoice.findByInvId", query = "SELECT i FROM Invoice i WHERE i.invoiceId = :invId ORDER BY i.invoicedDt DESC") })
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "UPDATE_WORKFLOW_CALC", procedureName = "UPDATE_WORKFLOW_CALC", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "IN_StakingSheetId"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Timestamp.class, name = "IN_RateDate"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_TypeFlag"),
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "OUT_Response") }),
		@NamedStoredProcedureQuery(name = "UPDATE_INVOICE_STATUS", procedureName = "UPDATE_INVOICE_STATUS", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "IN_InvoiceId"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "IN_InvoiceStatus"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_InvoiceType"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_ApprovedBy"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Timestamp.class, name = "IN_ApprovedDt"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_ApprovedComment"),
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "OUT_Response") })
		})
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "InvoiceId")
	private int invoiceId;

	@Column(name = "AP_INV_ID")
	private String apInvId;

	@Column(name = "BusinessRuleFlg")
	private String businessRuleFlg;

	@Column(name = "InvoiceAmount")
	private BigDecimal invoiceAmount;

	@Column(name = "InvoicedBy")
	private String invoicedBy;

	@Column(name = "InvoicedDt")
	private Timestamp invoicedDt;

	@Column(name = "InvoiceStatusId")
	private int invoiceStatusId;

	@OneToOne
	@JoinColumn(name = "InvoiceStatusId", insertable = false, updatable = false)
	private InvoiceStatus invoiceStatus;

	@Column(name = "InvoiceType")
	private String invoiceType;

	@Column(name = "PaymentDt")
	private Timestamp paymentDt;

	@Column(name = "PaymentStatus")
	private String paymentStatus;

	@Column(name = "ServiceOrderId")
	private String serviceOrderId;

	@Column(name = "VendorId")
	private int vendorId;

	@OneToOne
	@JoinColumn(name = "VendorId", insertable = false, updatable = false)
	private Vendor vendor;

	@Column(name = "VendorReference")
	private String vendorReference;

	@Column(name = "WorkFlowId")
	private String workFlowId;

	@Column(name = "WorkOrderId")
	private String workOrderId;

	@Column(name = "ApprovedBy")
	private String approvedBy;

	@Column(name = "ApprovedDt")
	private Timestamp approvedDt;

	@Column(name = "ApprovedComment")
	private String approvedComment;

	@Column(name = "DesignCost")
	private BigDecimal designCost;

	@Column(name = "DesignTotalAssembly")
	private Integer designTotalAssembly;

	@Column(name = "DesignUniqueAssembly")
	private Integer designUniqueAssembly;

	@Column(name = "DesignTotalStation")
	private Integer designTotalStation;

	@Column(name = "InvoiceUniqueAssembly")
	private Integer invoiceUniqueAssembly;

	@Column(name = "InvoiceTotalAssembly")
	private Integer invoiceTotalAssembly;

	@Column(name = "InvoiceTotalStation")
	private Integer invoiceTotalStation;
	
	@Column(name = "InvoiceVoucherCost")
	private BigDecimal invoiceVoucherCost;
	
	@Column(name = "InvoiceVoucherQty")
	private Integer invoiceVoucherQty;

	public Invoice() {
	}

	public int getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getApInvId() {
		return this.apInvId;
	}

	public void setApInvId(String apInvId) {
		this.apInvId = apInvId;
	}

	public String getBusinessRuleFlg() {
		return this.businessRuleFlg;
	}

	public void setBusinessRuleFlg(String businessRuleFlg) {
		this.businessRuleFlg = businessRuleFlg;
	}

	public BigDecimal getInvoiceAmount() {
		return this.invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getInvoicedBy() {
		return this.invoicedBy;
	}

	public void setInvoicedBy(String invoicedBy) {
		this.invoicedBy = invoicedBy;
	}

	public Timestamp getInvoicedDt() {
		return this.invoicedDt;
	}

	public void setInvoicedDt(Timestamp invoicedDt) {
		this.invoicedDt = invoicedDt;
	}

	public int getInvoiceStatusId() {
		return this.invoiceStatusId;
	}

	public void setInvoiceStatusId(int invoiceStatusId) {
		this.invoiceStatusId = invoiceStatusId;
	}

	public String getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public Timestamp getPaymentDt() {
		return this.paymentDt;
	}

	public void setPaymentDt(Timestamp paymentDt) {
		this.paymentDt = paymentDt;
	}

	public String getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getServiceOrderId() {
		return this.serviceOrderId;
	}

	public void setServiceOrderId(String serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}

	public int getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorReference() {
		return this.vendorReference;
	}

	public void setVendorReference(String vendorReference) {
		this.vendorReference = vendorReference;
	}

	public String getWorkFlowId() {
		return this.workFlowId;
	}

	public void setWorkFlowId(String workFlowId) {
		this.workFlowId = workFlowId;
	}

	public String getWorkOrderId() {
		return this.workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

	public InvoiceStatus getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Timestamp getApprovedDt() {
		return approvedDt;
	}

	public void setApprovedDt(Timestamp approvedDt) {
		this.approvedDt = approvedDt;
	}

	public String getApprovedComment() {
		return approvedComment;
	}

	public void setApprovedComment(String approvedComment) {
		this.approvedComment = approvedComment;
	}

	public BigDecimal getDesignCost() {
		return designCost;
	}

	public void setDesignCost(BigDecimal designCost) {
		this.designCost = designCost;
	}

	public Integer getDesignTotalAssembly() {
		return designTotalAssembly;
	}

	public void setDesignTotalAssembly(Integer designTotalAssembly) {
		this.designTotalAssembly = designTotalAssembly;
	}

	public Integer getDesignUniqueAssembly() {
		return designUniqueAssembly;
	}

	public void setDesignUniqueAssembly(Integer designUniqueAssembly) {
		this.designUniqueAssembly = designUniqueAssembly;
	}

	public Integer getDesignTotalStation() {
		return designTotalStation;
	}

	public void setDesignTotalStation(Integer designTotalStation) {
		this.designTotalStation = designTotalStation;
	}

	public Integer getInvoiceUniqueAssembly() {
		return invoiceUniqueAssembly;
	}

	public void setInvoiceUniqueAssembly(Integer invoiceUniqueAssembly) {
		this.invoiceUniqueAssembly = invoiceUniqueAssembly;
	}

	public Integer getInvoiceTotalAssembly() {
		return invoiceTotalAssembly;
	}

	public void setInvoiceTotalAssembly(Integer invoiceTotalAssembly) {
		this.invoiceTotalAssembly = invoiceTotalAssembly;
	}

	public Integer getInvoiceTotalStation() {
		return invoiceTotalStation;
	}

	public void setInvoiceTotalStation(Integer invoiceTotalStation) {
		this.invoiceTotalStation = invoiceTotalStation;
	}

	public BigDecimal getInvoiceVoucherCost() {
		return invoiceVoucherCost;
	}

	public void setInvoiceVoucherCost(BigDecimal invoiceVoucherCost) {
		this.invoiceVoucherCost = invoiceVoucherCost;
	}

	public Integer getInvoiceVoucherQty() {
		return invoiceVoucherQty;
	}

	public void setInvoiceVoucherQty(Integer invoiceVoucherQty) {
		this.invoiceVoucherQty = invoiceVoucherQty;
	}

}