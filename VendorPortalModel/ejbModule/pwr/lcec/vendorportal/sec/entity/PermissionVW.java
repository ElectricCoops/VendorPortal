package pwr.lcec.vendorportal.sec.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PermissionVW database table.
 * 
 */
@Entity
@NamedQuery(name="PermissionVW.findAll", query="SELECT p FROM PermissionVW p")
public class PermissionVW implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PermissionVwPK id;
	
	@Column(name="Domain")
	private String domain;

	@Column(name="Permission")
	private String permission;

	@Column(name="Username")
	private String username;

	public PermissionVW() {
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public PermissionVwPK getId() {
		return id;
	}
}