package pwr.lcec.vendorportal.sec.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the User database table.
 * 
 */
@Entity
@Table(name = "UserTbl")
@NamedQueries({
@NamedQuery(name="User.findAll", query="SELECT u FROM User u WHERE u.effectiveEndDate IS NULL"),
@NamedQuery(name="User.findByPrincipal", query="SELECT u FROM User u WHERE u.userName = :user AND u.effectiveEndDate IS NULL")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UserID")
	private Integer userID;

	@Column(name="EffectiveEndDate")
	private Timestamp effectiveEndDate;

	@Column(name="EffectiveStartDate")
	private Timestamp effectiveStartDate;

	@Column(name="Email")
	private String email;

	@Column(name="FirstName")
	private String firstName;

	@Column(name="IsLocked")
	private boolean isLocked;

	@Column(name="LastLogin")
	private Timestamp lastLogin;

	@Column(name="LastName")
	private String lastName;

	@Column(name="Password")
	private String password;

	@Column(name="PasswordSalt")
	private String passwordSalt;

	@Column(name="Phone")
	private String phone;

	@Column(name="PhoneCarrier")
	private String phoneCarrier;

	@Column(name="UserName")
	private String userName;
	
	@Column(name="WorkGroup")
	private String workGroup;
	
	@Column(name="CreatedBy")
	private String createdBy;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name="UserID")
	private List<UserRole> userRole;

	public User() {
	}

	public Integer getUserID() {
		return this.userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean getIsLocked() {
		return this.isLocked;
	}

	public void setIsLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Timestamp getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordSalt() {
		return this.passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneCarrier() {
		return this.phoneCarrier;
	}

	public void setPhoneCarrier(String phoneCarrier) {
		this.phoneCarrier = phoneCarrier;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWorkGroup() {
		return workGroup;
	}
	public void setWorkGroup(String workGroup) {
		this.workGroup = workGroup;
	}

	public List<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}