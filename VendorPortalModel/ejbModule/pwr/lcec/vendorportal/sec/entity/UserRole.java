package pwr.lcec.vendorportal.sec.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the UserRole database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "UserRole.findAll", query = "SELECT u FROM UserRole u"),
		@NamedQuery(name = "UserRole.findByUserId", query = "SELECT u FROM UserRole u WHERE u.userID = :userId") })
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UserRoleID")
	private Integer userRoleID;

	@Column(name="RoleID")
	private Integer roleID;

	@Column(name="UserID")
	private Integer userID;
	
	@ManyToOne
	@JoinColumn(name="RoleID", insertable=false, updatable=false)
	private Role role;

	public UserRole() {
	}
	
	public UserRole(Integer userRoleID, Integer roleID, Integer userID) {
		this.userRoleID = userRoleID;
		this.roleID = roleID;
		this.userID = userID;
	}
	public Integer getUserRoleID() {
		return this.userRoleID;
	}

	public void setUserRoleID(Integer userRoleID) {
		this.userRoleID = userRoleID;
	}

	public Integer getRoleID() {
		return this.roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public Integer getUserID() {
		return this.userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}