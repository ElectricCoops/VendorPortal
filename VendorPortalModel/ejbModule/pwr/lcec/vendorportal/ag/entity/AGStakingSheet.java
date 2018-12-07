package pwr.lcec.vendorportal.ag.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the AGStakingSheet database table.
 * 
 */
@Entity
@NamedQuery(name="AGStakingSheet.findAll", query="SELECT a FROM AGStakingSheet a")
@NamedStoredProcedureQuery(name = "RunAggregateSync", procedureName = "RunAggregateSync")
public class AGStakingSheet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer AGStakingSheetID;

	@Column(name="AsBuiltAppealed")
	private Integer asBuiltAppealed;

	@Column(name="AsBuiltCompleted30Days")
	private Integer asBuiltCompleted30Days;

	@Column(name="AsBuiltCorrected")
	private Integer asBuiltCorrected;

	@Column(name="AsbuiltInProgress")
	private Integer asbuiltInProgress;

	@Column(name="AsBuiltNotStarted")
	private Integer asBuiltNotStarted;

	@Column(name="AsBuiltRejected")
	private Integer asBuiltRejected;

	@Column(name="InspectionApproved30Days")
	private Integer inspectionApproved30Days;

	@Column(name="InspectionInProgress")
	private Integer inspectionInProgress;

	@Column(name="InspectionReady")
	private Integer inspectionReady;

	@Column(name="InspectionRejected")
	private Integer inspectionRejected;

	@Column(name="InvoiceApproved")
	private Integer invoiceApproved;

	@Column(name="InvoiceRejected")
	private Integer invoiceRejected;

	@Column(name="InvoiceSubmitted")
	private Integer invoiceSubmitted;

	@Column(name="LastSynced")
	private Timestamp lastSynced;

	@Column(name="NotInspected")
	private Integer notInspected;

	@Column(name="NotInvoiced")
	private Integer notInvoiced;

	@Column(name="Total30Days")
	private Integer total30Days;

	@Column(name="VendorType")
	private String vendorType;

	private Integer WOCompleted30Days;

	public AGStakingSheet() {
	}

	public Integer getAGStakingSheetID() {
		return this.AGStakingSheetID;
	}

	public void setAGStakingSheetID(Integer AGStakingSheetID) {
		this.AGStakingSheetID = AGStakingSheetID;
	}

	public Integer getAsBuiltAppealed() {
		return this.asBuiltAppealed;
	}

	public void setAsBuiltAppealed(Integer asBuiltAppealed) {
		this.asBuiltAppealed = asBuiltAppealed;
	}

	public Integer getAsBuiltCompleted30Days() {
		return this.asBuiltCompleted30Days;
	}

	public void setAsBuiltCompleted30Days(Integer asBuiltCompleted30Days) {
		this.asBuiltCompleted30Days = asBuiltCompleted30Days;
	}

	public Integer getAsBuiltCorrected() {
		return this.asBuiltCorrected;
	}

	public void setAsBuiltCorrected(Integer asBuiltCorrected) {
		this.asBuiltCorrected = asBuiltCorrected;
	}

	public Integer getAsbuiltInProgress() {
		return this.asbuiltInProgress;
	}

	public void setAsbuiltInProgress(Integer asbuiltInProgress) {
		this.asbuiltInProgress = asbuiltInProgress;
	}

	public Integer getAsBuiltNotStarted() {
		return this.asBuiltNotStarted;
	}

	public void setAsBuiltNotStarted(Integer asBuiltNotStarted) {
		this.asBuiltNotStarted = asBuiltNotStarted;
	}

	public Integer getAsBuiltRejected() {
		return this.asBuiltRejected;
	}

	public void setAsBuiltRejected(Integer asBuiltRejected) {
		this.asBuiltRejected = asBuiltRejected;
	}

	public Integer getInspectionApproved30Days() {
		return this.inspectionApproved30Days;
	}

	public void setInspectionApproved30Days(Integer inspectionApproved30Days) {
		this.inspectionApproved30Days = inspectionApproved30Days;
	}

	public Integer getInspectionInProgress() {
		return this.inspectionInProgress;
	}

	public void setInspectionInProgress(Integer inspectionInProgress) {
		this.inspectionInProgress = inspectionInProgress;
	}

	public Integer getInspectionReady() {
		return this.inspectionReady;
	}

	public void setInspectionReady(Integer inspectionReady) {
		this.inspectionReady = inspectionReady;
	}

	public Integer getInspectionRejected() {
		return this.inspectionRejected;
	}

	public void setInspectionRejected(Integer inspectionRejected) {
		this.inspectionRejected = inspectionRejected;
	}

	public Integer getInvoiceApproved() {
		return this.invoiceApproved;
	}

	public void setInvoiceApproved(Integer invoiceApproved) {
		this.invoiceApproved = invoiceApproved;
	}

	public Integer getInvoiceRejected() {
		return this.invoiceRejected;
	}

	public void setInvoiceRejected(Integer invoiceRejected) {
		this.invoiceRejected = invoiceRejected;
	}

	public Integer getInvoiceSubmitted() {
		return this.invoiceSubmitted;
	}

	public void setInvoiceSubmitted(Integer invoiceSubmitted) {
		this.invoiceSubmitted = invoiceSubmitted;
	}

	public Timestamp getLastSynced() {
		return this.lastSynced;
	}

	public void setLastSynced(Timestamp lastSynced) {
		this.lastSynced = lastSynced;
	}

	public Integer getNotInspected() {
		return this.notInspected;
	}

	public void setNotInspected(Integer notInspected) {
		this.notInspected = notInspected;
	}

	public Integer getNotInvoiced() {
		return this.notInvoiced;
	}

	public void setNotInvoiced(Integer notInvoiced) {
		this.notInvoiced = notInvoiced;
	}

	public Integer getTotal30Days() {
		return this.total30Days;
	}

	public void setTotal30Days(Integer total30Days) {
		this.total30Days = total30Days;
	}

	public String getVendorType() {
		return this.vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

	public Integer getWOCompleted30Days() {
		return this.WOCompleted30Days;
	}

	public void setWOCompleted30Days(Integer WOCompleted30Days) {
		this.WOCompleted30Days = WOCompleted30Days;
	}

}