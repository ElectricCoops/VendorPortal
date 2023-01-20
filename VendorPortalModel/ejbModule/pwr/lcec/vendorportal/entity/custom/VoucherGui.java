package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the VoucherGui database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "VoucherGui.findByWorkOrderId", query = "SELECT v FROM VoucherGui v WHERE v.workOrderId = :woId ORDER BY v.createdDt DESC"),
	@NamedQuery(name = "VoucherGui.findByInspectionId", query = "SELECT v FROM VoucherGui v WHERE v.inspectionId = :inspectionId"),
	@NamedQuery(name = "VoucherGui.findByInvoiceId", query = "SELECT v FROM VoucherGui v WHERE v.invoiceId = :invoiceId"),
	@NamedQuery(name = "VoucherGui.findById", query = "SELECT v FROM VoucherGui v WHERE v.voucherId = :voucherId")
})
public class VoucherGui implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Amount")
	private BigDecimal amount;

	@Column(name="ApprovedBy")
	private String approvedBy;

	@Column(name="ApprovedComment")
	private String approvedComment;

	@Column(name="ApprovedDt")
	private Timestamp approvedDt;

	@Column(name="CreatedDt")
	private Timestamp createdDt;

	@Column(name="Crew")
	private String crew;

	@Column(name="Description")
	private String description;

	private int GLAccountId;

	private int GLAccountIdSplit;

	@Column(name="InspectionComment")
	private String inspectionComment;

	@Column(name="InspectionId")
	private Integer inspectionId;

	@Column(name="InspectionStatus")
	private String inspectionStatus;

	@Column(name="InspectionStatusId")
	private Integer inspectionStatusId;

	@Column(name="InvoiceDetailId")
	private Integer invoiceDetailId;

	@Column(name="InvoiceId")
	private Integer invoiceId;

	@Column(name="InvoiceStatus")
	private String invoiceStatus;

	@Column(name="InvoiceStatusId")
	private Integer invoiceStatusId;

	@Column(name="Requestor")
	private String requestor;

	@Column(name="ServiceOrderId")
	private String serviceOrderId;

	@Column(name="StakingSheetId")
	private Integer stakingSheetId;

	@Column(name="StationDescription")
	private String stationDescription;

	@Column(name="SubmitGuid")
	private String submitGuid;

	@Id
	@Column(name="VoucherId")
	private int voucherId;

	@Column(name="WorkOrderId")
	private String workOrderId;

	public VoucherGui() {
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getApprovedComment() {
		return approvedComment;
	}

	public void setApprovedComment(String approvedComment) {
		this.approvedComment = approvedComment;
	}

	public Timestamp getApprovedDt() {
		return approvedDt;
	}

	public void setApprovedDt(Timestamp approvedDt) {
		this.approvedDt = approvedDt;
	}

	public Timestamp getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Timestamp createdDt) {
		this.createdDt = createdDt;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getGLAccountId() {
		return GLAccountId;
	}

	public void setGLAccountId(int gLAccountId) {
		GLAccountId = gLAccountId;
	}

	public int getGLAccountIdSplit() {
		return GLAccountIdSplit;
	}

	public void setGLAccountIdSplit(int gLAccountIdSplit) {
		GLAccountIdSplit = gLAccountIdSplit;
	}

	public String getInspectionComment() {
		return inspectionComment;
	}

	public void setInspectionComment(String inspectionComment) {
		this.inspectionComment = inspectionComment;
	}

	public Integer getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(Integer inspectionId) {
		this.inspectionId = inspectionId;
	}

	public String getInspectionStatus() {
		return inspectionStatus;
	}

	public void setInspectionStatus(String inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}

	public Integer getInspectionStatusId() {
		return inspectionStatusId;
	}

	public void setInspectionStatusId(Integer inspectionStatusId) {
		this.inspectionStatusId = inspectionStatusId;
	}

	public Integer getInvoiceDetailId() {
		return invoiceDetailId;
	}

	public void setInvoiceDetailId(Integer invoiceDetailId) {
		this.invoiceDetailId = invoiceDetailId;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public Integer getInvoiceStatusId() {
		return invoiceStatusId;
	}

	public void setInvoiceStatusId(Integer invoiceStatusId) {
		this.invoiceStatusId = invoiceStatusId;
	}

	public String getRequestor() {
		return requestor;
	}

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	public String getServiceOrderId() {
		return serviceOrderId;
	}

	public void setServiceOrderId(String serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}

	public Integer getStakingSheetId() {
		return stakingSheetId;
	}

	public void setStakingSheetId(Integer stakingSheetId) {
		this.stakingSheetId = stakingSheetId;
	}

	public String getStationDescription() {
		return stationDescription;
	}

	public void setStationDescription(String stationDescription) {
		this.stationDescription = stationDescription;
	}

	public String getSubmitGuid() {
		return submitGuid;
	}

	public void setSubmitGuid(String submitGuid) {
		this.submitGuid = submitGuid;
	}

	public int getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(int voucherId) {
		this.voucherId = voucherId;
	}

	public String getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

}