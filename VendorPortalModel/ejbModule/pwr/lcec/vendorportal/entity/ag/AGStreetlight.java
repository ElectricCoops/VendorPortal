package pwr.lcec.vendorportal.entity.ag;

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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int AGStreetlightID;

	@Column(name="InspectionApproved30Days")
	private int inspectionApproved30Days;

	@Column(name="InspectionInProgress")
	private int inspectionInProgress;

	@Column(name="InspectionReady")
	private int inspectionReady;

	@Column(name="InspectionRejected")
	private int inspectionRejected;

	@Column(name="InvoiceApproved")
	private int invoiceApproved;

	@Column(name="InvoiceRejected")
	private int invoiceRejected;

	@Column(name="InvoiceSubmitted")
	private int invoiceSubmitted;

	@Column(name="LastSynced")
	private Timestamp lastSynced;

	@Column(name="NotInspected")
	private int notInspected;

	@Column(name="NotInvoiced")
	private int notInvoiced;

	private int SOCompleted30Days;

	@Column(name="Total30Days")
	private int total30Days;

	@Column(name="VendorType")
	private String vendorType;

	public AGStreetlight() {
	}

	public int getAGStreetlightID() {
		return this.AGStreetlightID;
	}

	public void setAGStreetlightID(int AGStreetlightID) {
		this.AGStreetlightID = AGStreetlightID;
	}

	public int getInspectionApproved30Days() {
		return this.inspectionApproved30Days;
	}

	public void setInspectionApproved30Days(int inspectionApproved30Days) {
		this.inspectionApproved30Days = inspectionApproved30Days;
	}

	public int getInspectionInProgress() {
		return this.inspectionInProgress;
	}

	public void setInspectionInProgress(int inspectionInProgress) {
		this.inspectionInProgress = inspectionInProgress;
	}

	public int getInspectionReady() {
		return this.inspectionReady;
	}

	public void setInspectionReady(int inspectionReady) {
		this.inspectionReady = inspectionReady;
	}

	public int getInspectionRejected() {
		return this.inspectionRejected;
	}

	public void setInspectionRejected(int inspectionRejected) {
		this.inspectionRejected = inspectionRejected;
	}

	public int getInvoiceApproved() {
		return this.invoiceApproved;
	}

	public void setInvoiceApproved(int invoiceApproved) {
		this.invoiceApproved = invoiceApproved;
	}

	public int getInvoiceRejected() {
		return this.invoiceRejected;
	}

	public void setInvoiceRejected(int invoiceRejected) {
		this.invoiceRejected = invoiceRejected;
	}

	public int getInvoiceSubmitted() {
		return this.invoiceSubmitted;
	}

	public void setInvoiceSubmitted(int invoiceSubmitted) {
		this.invoiceSubmitted = invoiceSubmitted;
	}

	public Timestamp getLastSynced() {
		return this.lastSynced;
	}

	public void setLastSynced(Timestamp lastSynced) {
		this.lastSynced = lastSynced;
	}

	public int getNotInspected() {
		return this.notInspected;
	}

	public void setNotInspected(int notInspected) {
		this.notInspected = notInspected;
	}

	public int getNotInvoiced() {
		return this.notInvoiced;
	}

	public void setNotInvoiced(int notInvoiced) {
		this.notInvoiced = notInvoiced;
	}

	public int getSOCompleted30Days() {
		return this.SOCompleted30Days;
	}

	public void setSOCompleted30Days(int SOCompleted30Days) {
		this.SOCompleted30Days = SOCompleted30Days;
	}

	public int getTotal30Days() {
		return this.total30Days;
	}

	public void setTotal30Days(int total30Days) {
		this.total30Days = total30Days;
	}

	public String getVendorType() {
		return this.vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

}