package pwr.lcec.vendorportal.entity.sec;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the Permission database table.
 * 
 */
@Entity
@NamedQuery(name="Permission.findAll", query="SELECT p FROM Permission p")
@Table(schema="sec")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PermissionID")
	private int permissionID;

	@Column(name="Domain")
	private String domain;

	@Column(name="EffectiveEndDate")
	private Timestamp effectiveEndDate;

	@Column(name="EffectiveStartDate")
	private Timestamp effectiveStartDate;

	@Column(name="Permission")
	private String permission;

	public Permission() {
	}

	public int getPermissionID() {
		return this.permissionID;
	}

	public void setPermissionID(int permissionID) {
		this.permissionID = permissionID;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Timestamp getEffectiveEndDate() {
		return this.effectiveEndDate;
	}

	public void setEffectiveEndDate(Timestamp effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	public Timestamp getEffectiveStartDate() {
		return this.effectiveStartDate;
	}

	public void setEffectiveStartDate(Timestamp effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

}