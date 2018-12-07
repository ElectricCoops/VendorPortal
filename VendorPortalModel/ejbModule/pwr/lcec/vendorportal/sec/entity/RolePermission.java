package pwr.lcec.vendorportal.sec.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the RolePermission database table.
 * 
 */
@Entity
@NamedQuery(name="RolePermission.findAll", query="SELECT r FROM RolePermission r")
public class RolePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RolePermissionID")
	private int rolePermissionID;

	@Column(name="EffectiveEndDate")
	private Timestamp effectiveEndDate;

	@Column(name="EffectiveStartDate")
	private Timestamp effectiveStartDate;

	@Column(name="PermissionID")
	private int permissionID;

	@Column(name="RoleID")
	private int roleID;

	public RolePermission() {
	}

	public int getRolePermissionID() {
		return this.rolePermissionID;
	}

	public void setRolePermissionID(int rolePermissionID) {
		this.rolePermissionID = rolePermissionID;
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

	public int getPermissionID() {
		return this.permissionID;
	}

	public void setPermissionID(int permissionID) {
		this.permissionID = permissionID;
	}

	public int getRoleID() {
		return this.roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

}