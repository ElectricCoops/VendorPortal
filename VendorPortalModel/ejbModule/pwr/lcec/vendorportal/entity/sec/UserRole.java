package pwr.lcec.vendorportal.entity.sec;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the UserRole database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "UserRole.findAll", query = "SELECT u FROM UserRole u"), 
	@NamedQuery(name = "UserRole.findByUserId", query = "SELECT u FROM UserRole u WHERE u.userID = :userId")
})
@Table(schema="sec")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="UserRoleID")
	private int userRoleID;

	@Column(name="RoleID")
	private int roleID;

	@Column(name="UserID")
	private int userID;
	
	@ManyToOne
	@JoinColumn(name = "RoleID", insertable = false, updatable = false)
	private Role role;

	public UserRole() {
	}
	
	public UserRole(Integer userRoleID, Integer roleID, Integer userID) {
	    this.userRoleID = userRoleID;
	    this.roleID = roleID;
	    this.userID = userID;
	  }

	public int getUserRoleID() {
		return this.userRoleID;
	}

	public void setUserRoleID(int userRoleID) {
		this.userRoleID = userRoleID;
	}

	public int getRoleID() {
		return this.roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}