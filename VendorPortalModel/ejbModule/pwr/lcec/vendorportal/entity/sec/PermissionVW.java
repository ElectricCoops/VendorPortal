package pwr.lcec.vendorportal.entity.sec;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PermissionVW database table.
 * 
 */
@Entity
@NamedQuery(name="PermissionVW.findAll", query="SELECT p FROM PermissionVW p")
@Table(schema="sec")
public class PermissionVW implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Permission")
	private String permission;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RoleID")
	private int roleID;

	@Column(name="UserName")
	private String userName;

	public PermissionVW() {
	}

	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public int getRoleID() {
		return this.roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}