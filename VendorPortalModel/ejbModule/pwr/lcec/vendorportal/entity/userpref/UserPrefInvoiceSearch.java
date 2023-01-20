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
	@NamedQuery(name = "UserPrefInvoiceSearch.findAll", query = "SELECT u FROM UserPrefInvoiceSearch u"),
	@NamedQuery(name = "UserPrefInvoiceSearch.findUserPrefInvSearchByUserId", query = "SELECT u FROM UserPrefInvoiceSearch u WHERE u.userId = :userId") 
})
@Table(schema="sec")
public class UserPrefInvoiceSearch implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserPrefId")
	private int userPrefId;
	
	@Column(name = "Action")
	private boolean action;
	
	@Column(name = "AP_INV_ID")
	private boolean apInvId;
	
	@Column(name = "ApprovedBy")
	private boolean approvedBy;
	
	@Column(name = "ApprovedDt")
	private boolean approvedDt;
	
	@Column(name = "BusinessRuleFlg")
	private boolean businessRuleFlg;
	
	@Column(name = "InvoiceAmount")
	private boolean invoiceAmount;
	
	@Column(name = "InvoiceBy")
	private boolean invoiceBy;
	
	@Column(name = "InvoiceDt")
	private boolean invoiceDt;
	
	@Column(name = "InvoiceId")
	private boolean invoiceId;
	
	@Column(name = "InvoiceStatusId")
	private boolean invoiceStatusId;
	
	@Column(name = "InvoiceType")
	private boolean invoiceType;
	
	private boolean LCECReference;
	
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
	
	@Column(name = "VendorName")
	private boolean vendorName;
	
	@Column(name = "VendorReference")
	private boolean vendorReference;
	
	@Column(name = "ViewDefault")
	private boolean viewDefault;
	
	@Column(name = "ViewName")
	private String viewName;
	
	@Column(name = "WorkFlowId")
	private boolean workFlowId;
	
	@Column(name = "WorkGroup")
	private boolean workGroup;
	
	@Column(name = "WorkOrderId")
	private boolean workOrderId;

	public UserPrefInvoiceSearch() {
	}

	public UserPrefInvoiceSearch(boolean action, boolean apInvId, boolean approvedBy, boolean approvedDt,
			boolean businessRuleFlg, boolean invoiceAmount, boolean invoiceBy, boolean invoiceDt, boolean invoiceId,
			boolean invoiceStatusId, boolean invoiceType, boolean lCECReference, boolean paymentDt,
			boolean paymentStatus, boolean serviceOrderId, int userId, boolean vendorId, boolean vendorName,
			boolean vendorReference, boolean viewDefault, String viewName, boolean workFlowId, boolean workGroup,
			boolean workOrderId) {
		this.action = action;
		this.apInvId = apInvId;
		this.approvedBy = approvedBy;
		this.approvedDt = approvedDt;
		this.businessRuleFlg = businessRuleFlg;
		this.invoiceAmount = invoiceAmount;
		this.invoiceBy = invoiceBy;
		this.invoiceDt = invoiceDt;
		this.invoiceId = invoiceId;
		this.invoiceStatusId = invoiceStatusId;
		this.invoiceType = invoiceType;
		this.LCECReference = lCECReference;
		this.paymentDt = paymentDt;
		this.paymentStatus = paymentStatus;
		this.serviceOrderId = serviceOrderId;
		this.userId = userId;
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorReference = vendorReference;
		this.viewDefault = viewDefault;
		this.viewName = viewName;
		this.workFlowId = workFlowId;
		this.workGroup = workGroup;
		this.workOrderId = workOrderId;
	}

	public int getUserPrefId() {
		return this.userPrefId;
	}

	public void setUserPrefId(int userPrefId) {
		this.userPrefId = userPrefId;
	}

	public boolean getAction() {
		return this.action;
	}

	public void setAction(boolean action) {
		this.action = action;
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

	public boolean getInvoiceAmount() {
		return this.invoiceAmount;
	}

	public void setInvoiceAmount(boolean invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public boolean getInvoiceBy() {
		return this.invoiceBy;
	}

	public void setInvoiceBy(boolean invoiceBy) {
		this.invoiceBy = invoiceBy;
	}

	public boolean getInvoiceDt() {
		return this.invoiceDt;
	}

	public void setInvoiceDt(boolean invoiceDt) {
		this.invoiceDt = invoiceDt;
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

	public boolean getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(boolean invoiceType) {
		this.invoiceType = invoiceType;
	}

	public boolean getLCECReference() {
		return this.LCECReference;
	}

	public void setLCECReference(boolean LCECReference) {
		this.LCECReference = LCECReference;
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

	public boolean getVendorName() {
		return this.vendorName;
	}

	public void setVendorName(boolean vendorName) {
		this.vendorName = vendorName;
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
}
