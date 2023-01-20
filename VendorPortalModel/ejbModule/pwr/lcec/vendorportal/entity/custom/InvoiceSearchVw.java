package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the InvoiceSearchVw database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "InvoiceSearchVw.findAllLCEC", query = "SELECT i FROM InvoiceSearchVw i"),
	@NamedQuery(name = "InvoiceSearchVw.findAll", query = "SELECT i FROM InvoiceSearchVw i WHERE i.workGroup = :wrkgrp"),
	@NamedQuery(name = "InvoiceSearchVw.findByIdLCEC", query = "SELECT i FROM InvoiceSearchVw i WHERE i.invoiceId = :invId"),
	@NamedQuery(name = "InvoiceSearchVw.findById", query = "SELECT i FROM InvoiceSearchVw i WHERE i.invoiceId = :invId AND i.workGroup = :wrkgrp"),
	@NamedQuery(name = "InvoiceSearchVw.findByWoIdLCEC", query = "SELECT i FROM InvoiceSearchVw i WHERE i.workOrderId = :woId"),
	@NamedQuery(name = "InvoiceSearchVw.findByWoId", query = "SELECT i FROM InvoiceSearchVw i WHERE i.workOrderId = :woId AND i.workGroup = :wrkgrp"),
	@NamedQuery(name = "InvoiceSearchVw.findByVenRefNo", query = "SELECT i FROM InvoiceSearchVw i WHERE i.vendorReference = :venRefNo AND i.workGroup = :wrkgrp"),
	@NamedQuery(name = "InvoiceSearchVw.findByVenRefNoLCEC", query = "SELECT i FROM InvoiceSearchVw i WHERE i.vendorReference = :venRefNo"),
	@NamedQuery(name = "InvoiceSearchVw.findByInvoiceStatus", query = "SELECT i FROM InvoiceSearchVw i WHERE i.invoiceStatus.description = :invStatus AND i.workGroup = :wrkgrp") 
})
public class InvoiceSearchVw implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AP_INV_ID")
	private String apInvId;

	@Column(name="ApprovedBy")
	private String approvedBy;

	@Column(name="ApprovedDt")
	private Timestamp approvedDt;

	@Column(name="BusinessRuleFlg")
	private String businessRuleFlg;

	@Column(name="InvoiceAmount")
	private BigDecimal invoiceAmount;

	@Column(name="InvoicedBy")
	private String invoicedBy;

	@Column(name="InvoicedDt")
	private Timestamp invoicedDt;

	@Id
	@Column(name="InvoiceId")
	private int invoiceId;

	@Column(name="InvoiceStatusId")
	private int invoiceStatusId;
	
	@OneToOne
	@JoinColumn(name = "InvoiceStatusId", insertable = false, updatable = false)
	private InvoiceStatus invoiceStatus;

	@Column(name="InvoiceType")
	private String invoiceType;

	@Column(name="PaymentDt")
	private Timestamp paymentDt;

	@Column(name="PaymentStatus")
	private String paymentStatus;

	@Column(name="ServiceOrderId")
	private String serviceOrderId;

	@Column(name="VendorId")
	private int vendorId;

	@Column(name="VendorName")
	private String vendorName;

	@Column(name="VendorReference")
	private String vendorReference;

	@Column(name="WorkFlowId")
	private String workFlowId;

	@Column(name="WorkGroup")
	private String workGroup;

	@Column(name="WorkOrderId")
	private String workOrderId;

	public InvoiceSearchVw() {
	}

	public String getApInvId() {
		return this.apInvId;
	}

	public void setApInvId(String apInvId) {
		this.apInvId = apInvId;
	}

	public String getApprovedBy() {
		return this.approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Timestamp getApprovedDt() {
		return this.approvedDt;
	}

	public void setApprovedDt(Timestamp approvedDt) {
		this.approvedDt = approvedDt;
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

	public int getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
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

	public String getVendorName() {
		return this.vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
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

	public InvoiceStatus getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

}