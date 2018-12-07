package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the InvoiceStatus database table.
 * 
 */
@Entity
@NamedQuery(name="InvoiceStatus.findAll", query="SELECT i FROM InvoiceStatus i")
public class InvoiceStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="InvoiceStatusId")
	private Integer invoiceStatusId;

	@Column(name="Active")
	private boolean active;

	@Column(name="Description")
	private String description;
	
	@Column(name="TransactionStatus")
	private String transactionStatus;

	public InvoiceStatus() {
	}

	public Integer getInvoiceStatusId() {
		return this.invoiceStatusId;
	}

	public void setInvoiceStatusId(Integer invoiceStatusId) {
		this.invoiceStatusId = invoiceStatusId;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

}