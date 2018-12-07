package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The persistent class for the Voucher database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Voucher.findAll", query = "SELECT v FROM Voucher v WHERE v.workOrderId = :woId ORDER BY v.createdDt DESC"),
		@NamedQuery(name = "Voucher.findByInspectionId", query = "SELECT v FROM Voucher v WHERE v.inspectionId = :inspectionId"),
		@NamedQuery(name = "Voucher.findByInvoiceId", query = "SELECT v FROM Voucher v WHERE v.invoiceId = :invoiceId")})
public class Voucher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VoucherId")
	private Integer voucherId;

	@Column(name = "Amount")
	private BigDecimal amount;

	@Column(name = "ApprovedBy")
	private String approvedBy;

	@Column(name = "ApprovedComment")
	private String approvedComment;

	@Column(name = "InspectionComment")
	private String inspectionComment;

	@Column(name = "ApprovedDt")
	private Timestamp approvedDt;

	@Column(name = "CreatedDt")
	private Timestamp createdDt;

	@Column(name = "Crew")
	private String crew;

	@Column(name = "Description")
	private String description;

	private Integer GLAccountId;
	
	private Integer GLAccountIdSplit;

	@Column(name = "InspectionId")
	private int inspectionId;

	@Column(name = "InspectionStatusId")
	private Integer inspectionStatusId;

	@Column(name = "InvoiceDetailId")
	private Integer invoiceDetailId;

	@Column(name = "InvoiceId")
	private Integer invoiceId;

	@Column(name = "InvoiceStatusId")
	private Integer invoiceStatusId;

	@Column(name = "Requestor")
	private String requestor;

	@Column(name = "ServiceOrderId")
	private String serviceOrderId;

	@Column(name = "StakingSheetId")
	private Integer stakingSheetId;

	@Column(name = "StationDescription")
	private String stationDescription;

	@Column(name = "SubmitGuid")
	private String submitGuid;

	@Column(name = "WorkOrderId")
	private String workOrderId;
	
	@OneToOne
	@JoinColumn(name = "InspectionStatusId", insertable = false, updatable = false)
	private InspectionStatus inspectionStatus;
	
	@OneToOne
	@JoinColumn(name = "InvoiceStatusId", insertable = false, updatable = false)
	private InvoiceStatus invoiceStatus;

	public Voucher() { }

	public Integer getVoucherId() {
		return this.voucherId;
	}

	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getApprovedBy() {
		return this.approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getApprovedComment() {
		return this.approvedComment;
	}

	public void setApprovedComment(String approvedComment) {
		this.approvedComment = approvedComment;
	}

	public Timestamp getApprovedDt() {
		return this.approvedDt;
	}

	public void setApprovedDt(Timestamp approvedDt) {
		this.approvedDt = approvedDt;
	}

	public Timestamp getCreatedDt() {
		return this.createdDt;
	}

	public void setCreatedDt(Timestamp createdDt) {
		this.createdDt = createdDt;
	}

	public String getCrew() {
		return this.crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getGLAccountId() {
		return this.GLAccountId;
	}

	public void setGLAccountId(Integer GLAccountId) {
		this.GLAccountId = GLAccountId;
	}

	public Integer getInspectionId() {
		return this.inspectionId;
	}

	public void setInspectionId(Integer inspectionId) {
		this.inspectionId = inspectionId;
	}

	public Integer getInspectionStatusId() {
		return this.inspectionStatusId;
	}

	public void setInspectionStatusId(Integer inspectionStatusId) {
		this.inspectionStatusId = inspectionStatusId;
	}

	public Integer getInvoiceDetailId() {
		return this.invoiceDetailId;
	}

	public void setInvoiceDetailId(Integer invoiceDetailId) {
		this.invoiceDetailId = invoiceDetailId;
	}

	public Integer getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Integer getInvoiceStatusId() {
		return this.invoiceStatusId;
	}

	public void setInvoiceStatusId(Integer invoiceStatusId) {
		this.invoiceStatusId = invoiceStatusId;
	}

	public String getRequestor() {
		return this.requestor;
	}

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	public String getServiceOrderId() {
		return this.serviceOrderId;
	}

	public void setServiceOrderId(String serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}

	public Integer getStakingSheetId() {
		return this.stakingSheetId;
	}

	public void setStakingSheetId(Integer stakingSheetId) {
		this.stakingSheetId = stakingSheetId;
	}

	public String getStationDescription() {
		return this.stationDescription;
	}

	public void setStationDescription(String stationDescription) {
		this.stationDescription = stationDescription;
	}

	public String getSubmitGuid() {
		return this.submitGuid;
	}

	public void setSubmitGuid(String submitGuid) {
		this.submitGuid = submitGuid;
	}

	public String getWorkOrderId() {
		return this.workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

	public String getInspectionComment() {
		return inspectionComment;
	}

	public void setInspectionComment(String inspectionComment) {
		this.inspectionComment = inspectionComment;
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

	public Integer getGLAccountIdSplit() {
		return GLAccountIdSplit;
	}

	public void setGLAccountIdSplit(Integer gLAccountIdSplit) {
		GLAccountIdSplit = gLAccountIdSplit;
	}

}