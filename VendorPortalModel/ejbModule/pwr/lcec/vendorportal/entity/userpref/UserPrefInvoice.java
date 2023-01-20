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
	@NamedQuery(name = "UserPrefInvoice.findAll", query = "SELECT u FROM UserPrefInvoice u"),
	@NamedQuery(name = "UserPrefInvoice.findInvPrefByUserId", query = "SELECT u FROM UserPrefInvoice u WHERE u.userId = :userId AND u.viewName = :viewName") 
})
@Table(schema="sec")
public class UserPrefInvoice implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserPrefId")
	private int userPrefId;
	
	@Column(name = "AP_INV_ID")
	private boolean apInvId;
	
	@Column(name = "ApprovedBy")
	private boolean approvedBy;
	
	@Column(name = "ApprovedComment")
	private boolean approvedComment;
	
	@Column(name = "ApprovedDt")
	private boolean approvedDt;
	
	@Column(name = "BusinessRuleFlg")
	private boolean businessRuleFlg;
	
	@Column(name = "DesignCost")
	private boolean designCost;
	
	@Column(name = "DesignTotalAssembly")
	private boolean designTotalAssembly;
	
	@Column(name = "DesignTotalStation")
	private boolean designTotalStation;
	
	@Column(name = "DesignUniqueAssembly")
	private boolean designUniqueAssembly;
	
	@Column(name = "InvoiceAmount")
	private boolean invoiceAmount;
	
	@Column(name = "InvoicedBy")
	private boolean invoicedBy;
	
	@Column(name = "InvoicedDt")
	private boolean invoicedDt;
	
	@Column(name = "InvoiceId")
	private boolean invoiceId;
	
	@Column(name = "InvoiceStatusId")
	private boolean invoiceStatusId;
	
	@Column(name = "InvoiceTotalAssembly")
	private boolean invoiceTotalAssembly;
	
	@Column(name = "InvoiceTotalStation")
	private boolean invoiceTotalStation;
	
	@Column(name = "InvoiceType")
	private boolean invoiceType;
	
	@Column(name = "InvoiceUniqueAssembly")
	private boolean invoiceUniqueAssembly;
	
	@Column(name = "InvoiceVoucherCost")
	private boolean invoiceVoucherCost;
	
	@Column(name = "InvoiceVoucherQty")
	private boolean invoiceVoucherQty;
	
	@Column(name = "PaymentDt")
	private boolean paymentDt;
	
	@Column(name = "PaymentStatus")
	private boolean paymentStatus;
	
	@Column(name = "ServiceOrderId")
	private boolean serviceOrderId;
	
	@Column(name = "UserId")
	private int userId;
	
	@Column(name = "VendorId")
	private boolean vendorId;
	
	@Column(name = "VendorReference")
	private boolean vendorReference;
	
	@Column(name = "ViewDefault")
	private boolean viewDefault;
	
	@Column(name = "ViewName")
	private String viewName;
	
	@Column(name = "WorkFlowId")
	private boolean workFlowId;
	
	@Column(name = "WorkOrderId")
	private boolean workOrderId;
	
	private boolean LCECReference;

	public UserPrefInvoice() {
	}

	public UserPrefInvoice(boolean apInvId, boolean approvedBy, boolean approvedComment, boolean approvedDt,
			boolean businessRuleFlg, boolean designCost, boolean designTotalAssembly, boolean designTotalStation,
			boolean designUniqueAssembly, boolean invoiceAmount, boolean invoicedBy, boolean invoicedDt,
			boolean invoiceId, boolean invoiceStatusId, boolean invoiceTotalAssembly, boolean invoiceTotalStation,
			boolean invoiceType, boolean invoiceUniqueAssembly, boolean invoiceVoucherCost, boolean invoiceVoucherQty,
			boolean paymentDt, boolean paymentStatus, boolean serviceOrderId, int userId, boolean vendorId,
			boolean vendorReference, boolean viewDefault, String viewName, boolean workFlowId, boolean workOrderId,
			boolean LCECReference) {
		this.apInvId = apInvId;
		this.approvedBy = approvedBy;
		this.approvedComment = approvedComment;
		this.approvedDt = approvedDt;
		this.businessRuleFlg = businessRuleFlg;
		this.designCost = designCost;
		this.designTotalAssembly = designTotalAssembly;
		this.designTotalStation = designTotalStation;
		this.designUniqueAssembly = designUniqueAssembly;
		this.invoiceAmount = invoiceAmount;
		this.invoicedBy = invoicedBy;
		this.invoicedDt = invoicedDt;
		this.invoiceId = invoiceId;
		this.invoiceStatusId = invoiceStatusId;
		this.invoiceTotalAssembly = invoiceTotalAssembly;
		this.invoiceTotalStation = invoiceTotalStation;
		this.invoiceType = invoiceType;
		this.invoiceUniqueAssembly = invoiceUniqueAssembly;
		this.invoiceVoucherCost = invoiceVoucherCost;
		this.invoiceVoucherQty = invoiceVoucherQty;
		this.paymentDt = paymentDt;
		this.paymentStatus = paymentStatus;
		this.serviceOrderId = serviceOrderId;
		this.userId = userId;
		this.vendorId = vendorId;
		this.vendorReference = vendorReference;
		this.viewDefault = viewDefault;
		this.viewName = viewName;
		this.workFlowId = workFlowId;
		this.workOrderId = workOrderId;
		this.LCECReference = LCECReference;
	}

	public int getUserPrefId() {
		return this.userPrefId;
	}

	public void setUserPrefId(int userPrefId) {
		this.userPrefId = userPrefId;
	}

	public boolean getApInvId() {
		return this.apInvId;
	}

	public void setApInvId(boolean apInvId) {
		this.apInvId = apInvId;
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

	public boolean getBusinessRuleFlg() {
		return this.businessRuleFlg;
	}

	public void setBusinessRuleFlg(boolean businessRuleFlg) {
		this.businessRuleFlg = businessRuleFlg;
	}

	public boolean getDesignCost() {
		return this.designCost;
	}

	public void setDesignCost(boolean designCost) {
		this.designCost = designCost;
	}

	public boolean getDesignTotalAssembly() {
		return this.designTotalAssembly;
	}

	public void setDesignTotalAssembly(boolean designTotalAssembly) {
		this.designTotalAssembly = designTotalAssembly;
	}

	public boolean getDesignTotalStation() {
		return this.designTotalStation;
	}

	public void setDesignTotalStation(boolean designTotalStation) {
		this.designTotalStation = designTotalStation;
	}

	public boolean getDesignUniqueAssembly() {
		return this.designUniqueAssembly;
	}

	public void setDesignUniqueAssembly(boolean designUniqueAssembly) {
		this.designUniqueAssembly = designUniqueAssembly;
	}

	public boolean getInvoiceAmount() {
		return this.invoiceAmount;
	}

	public void setInvoiceAmount(boolean invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public boolean getInvoicedBy() {
		return this.invoicedBy;
	}

	public void setInvoicedBy(boolean invoicedBy) {
		this.invoicedBy = invoicedBy;
	}

	public boolean getInvoicedDt() {
		return this.invoicedDt;
	}

	public void setInvoicedDt(boolean invoicedDt) {
		this.invoicedDt = invoicedDt;
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

	public boolean getInvoiceTotalAssembly() {
		return this.invoiceTotalAssembly;
	}

	public void setInvoiceTotalAssembly(boolean invoiceTotalAssembly) {
		this.invoiceTotalAssembly = invoiceTotalAssembly;
	}

	public boolean getInvoiceTotalStation() {
		return this.invoiceTotalStation;
	}

	public void setInvoiceTotalStation(boolean invoiceTotalStation) {
		this.invoiceTotalStation = invoiceTotalStation;
	}

	public boolean getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(boolean invoiceType) {
		this.invoiceType = invoiceType;
	}

	public boolean getInvoiceUniqueAssembly() {
		return this.invoiceUniqueAssembly;
	}

	public void setInvoiceUniqueAssembly(boolean invoiceUniqueAssembly) {
		this.invoiceUniqueAssembly = invoiceUniqueAssembly;
	}

	public boolean getInvoiceVoucherCost() {
		return this.invoiceVoucherCost;
	}

	public void setInvoiceVoucherCost(boolean invoiceVoucherCost) {
		this.invoiceVoucherCost = invoiceVoucherCost;
	}

	public boolean getInvoiceVoucherQty() {
		return this.invoiceVoucherQty;
	}

	public void setInvoiceVoucherQty(boolean invoiceVoucherQty) {
		this.invoiceVoucherQty = invoiceVoucherQty;
	}

	public boolean getPaymentDt() {
		return this.paymentDt;
	}

	public void setPaymentDt(boolean paymentDt) {
		this.paymentDt = paymentDt;
	}

	public boolean getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public boolean getServiceOrderId() {
		return this.serviceOrderId;
	}

	public void setServiceOrderId(boolean serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(boolean vendorId) {
		this.vendorId = vendorId;
	}

	public boolean getVendorReference() {
		return this.vendorReference;
	}

	public void setVendorReference(boolean vendorReference) {
		this.vendorReference = vendorReference;
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

	public boolean getWorkFlowId() {
		return this.workFlowId;
	}

	public void setWorkFlowId(boolean workFlowId) {
		this.workFlowId = workFlowId;
	}

	public boolean getWorkOrderId() {
		return this.workOrderId;
	}

	public void setWorkOrderId(boolean workOrderId) {
		this.workOrderId = workOrderId;
	}

	public boolean isLCECReference() {
		return this.LCECReference;
	}

	public void setLCECReference(boolean lCECReference) {
		this.LCECReference = lCECReference;
	}
}
