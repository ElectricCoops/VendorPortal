package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the InvoiceDetail database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="InvoiceDetail.findAll", query="SELECT i FROM InvoiceDetail i"),
@NamedQuery(name="InvoiceDetail.findByInvoiceId", query="SELECT i FROM InvoiceDetail i WHERE i.invoiceId = :invoiceId")
})
public class InvoiceDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="InvoiceDetailId")
	private int invoiceDetailId;

	@Column(name="Amount")
	private BigDecimal amount;

	@Column(name="AP_INV_DTL_ID")
	private String apInvDtlId;

	@Column(name="CategoryCode")
	private String categoryCode;

	@Column(name="Description")
	private String description;

	private String GL_Code;

	@Column(name="InvoiceId")
	private int invoiceId;

	@Column(name="Quantity")
	private int quantity;
	
	@Column(name="GLDepartment")
	private BigDecimal gLDepartment;
	
	@Column(name="GLActivity")
	private BigDecimal gLActivity;

	public InvoiceDetail() {
	}

	public int getInvoiceDetailId() {
		return this.invoiceDetailId;
	}

	public void setInvoiceDetailId(int invoiceDetailId) {
		this.invoiceDetailId = invoiceDetailId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getApInvDtlId() {
		return this.apInvDtlId;
	}

	public void setApInvDtlId(String apInvDtlId) {
		this.apInvDtlId = apInvDtlId;
	}

	public String getCategoryCode() {
		return this.categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGL_Code() {
		return this.GL_Code;
	}

	public void setGL_Code(String GL_Code) {
		this.GL_Code = GL_Code;
	}

	public int getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getgLDepartment() {
		return gLDepartment;
	}

	public void setgLDepartment(BigDecimal gLDepartment) {
		this.gLDepartment = gLDepartment;
	}

	public BigDecimal getgLActivity() {
		return gLActivity;
	}

	public void setgLActivity(BigDecimal gLActivity) {
		this.gLActivity = gLActivity;
	}

}