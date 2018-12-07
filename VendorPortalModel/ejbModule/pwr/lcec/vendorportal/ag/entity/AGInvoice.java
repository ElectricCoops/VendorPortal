package pwr.lcec.vendorportal.ag.entity;

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
	private Integer AGInvoiceID;

	@Column(name="InvoiceApproved")
	private Integer invoiceApproved;

	@Column(name="InvoiceRejected")
	private Integer invoiceRejected;

	@Column(name="InvoiceSubmitted")
	private Integer invoiceSubmitted;

	@Column(name="LastSynced")
	private Timestamp lastSynced;

	@Column(name="NotInvoiced")
	private Integer notInvoiced;

	@Column(name="Total30Days")
	private Integer total30Days;

	@Column(name="VendorType")
	private String vendorType;

	public AGInvoice() {
	}

	public Integer getAGInvoiceID() {
		return AGInvoiceID;
	}

	public void setAGInvoiceID(Integer aGInvoiceID) {
		AGInvoiceID = aGInvoiceID;
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

}