package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Vendor database table.
 * 
 */
@Entity
@NamedQuery(name="Vendor.findAll", query="SELECT v FROM Vendor v")
public class Vendor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="VendorId")
	private int vendorId;

	private String AP_FullName;

	private String AP_PaymentType;

	@Column(name="AP_VEND_ID")
	private String apVendId;

	private String GL_Account;

	@Column(name="Name")
	private String name;

	public Vendor() {
	}

	public int getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getAP_FullName() {
		return this.AP_FullName;
	}

	public void setAP_FullName(String AP_FullName) {
		this.AP_FullName = AP_FullName;
	}

	public String getAP_PaymentType() {
		return this.AP_PaymentType;
	}

	public void setAP_PaymentType(String AP_PaymentType) {
		this.AP_PaymentType = AP_PaymentType;
	}

	public String getApVendId() {
		return this.apVendId;
	}

	public void setApVendId(String apVendId) {
		this.apVendId = apVendId;
	}

	public String getGL_Account() {
		return this.GL_Account;
	}

	public void setGL_Account(String GL_Account) {
		this.GL_Account = GL_Account;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}