package pwr.lcec.vendorportal.entity.ag;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the AGInvoice database table.
 * 
 */
@Entity
@NamedQuery(name="AGInvoice.findAll", query="SELECT a FROM AGInvoice a")
public class AGInvoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int AGInvoiceID;

	@Column(name="InvoiceApproved")
	private int invoiceApproved;

	@Column(name="InvoiceRejected")
	private int invoiceRejected;

	@Column(name="InvoiceSubmitted")
	private int invoiceSubmitted;

	@Column(name="LastSynced")
	private Timestamp lastSynced;

	@Column(name="NotInvoiced")
	private int notInvoiced;

	@Column(name="Total30Days")
	private int total30Days;

	@Column(name="VendorType")
	private String vendorType;

	public AGInvoice() {
	}

	public int getAGInvoiceID() {
		return this.AGInvoiceID;
	}

	public void setAGInvoiceID(int AGInvoiceID) {
		this.AGInvoiceID = AGInvoiceID;
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

	public int getNotInvoiced() {
		return this.notInvoiced;
	}

	public void setNotInvoiced(int notInvoiced) {
		this.notInvoiced = notInvoiced;
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