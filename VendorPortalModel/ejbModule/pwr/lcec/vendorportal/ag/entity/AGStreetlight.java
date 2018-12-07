package pwr.lcec.vendorportal.ag.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the AGStreetlight database table.
 * 
 */
@Entity
@NamedQuery(name="AGStreetlight.findAll", query="SELECT a FROM AGStreetlight a")
public class AGStreetlight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer AGStreetlightID;

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

	private Integer SOCompleted30Days;

	@Column(name="Total30Days")
	private Integer total30Days;

	@Column(name="VendorType")
	private String vendorType;

	public AGStreetlight() {
	}

	public Integer getAGStreetlightID() {
		return this.AGStreetlightID;
	}

	public void setAGStreetlightID(Integer AGStreetlightID) {
		this.AGStreetlightID = AGStreetlightID;
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

	public Integer getSOCompleted30Days() {
		return this.SOCompleted30Days;
	}

	public void setSOCompleted30Days(Integer SOCompleted30Days) {
		this.SOCompleted30Days = SOCompleted30Days;
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

}