package pwr.lcec.vendorportal.entity.sec;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the UserTbl database table.
 * 
 */
@Entity
@Table(name="UserTbl",schema="sec")
@NamedQueries({ 
	@NamedQuery(name="UserTbl.findAll", query="SELECT u FROM UserTbl u ORDER BY u.userName"),
	@NamedQuery(name="UserTbl.findActive", query="SELECT u FROM UserTbl u WHERE u.effectiveEndDate IS NULL ORDER BY u.userName"),
	@NamedQuery(name="UserTbl.findByPrincipal", query="SELECT u FROM UserTbl u WHERE u.userName = :user AND u.effectiveEndDate IS NULL")
})
public class UserTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="UserID")
	private Integer userID;

	@Column(name="CreatedBy")
	private String createdBy;

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
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "UserID", updatable = false )
	private List<UserRole> userRole;

	public UserTbl() {
	}

	public Integer getUserID() {
		return this.userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
		return this.workGroup;
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

}