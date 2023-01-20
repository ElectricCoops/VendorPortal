package pwr.lcec.vendorportal.entity.sec;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the LoginAttempt database table.
 * 
 */
@Entity
@NamedQuery(name="LoginAttempt.findAll", query="SELECT l FROM LoginAttempt l")
@Table(schema="sec")
public class LoginAttempt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="LoginAttemptID")
	private int loginAttemptID;

	@Column(name="AttemptDT")
	private Timestamp attemptDT;

	@Column(name="BrowserType")
	private String browserType;

	private String IPAddress;

	@Column(name="NameAttempt")
	private String nameAttempt;

	@Column(name="Success")
	private boolean success;

	@Column(name="UserID")
	private int userID;

	public LoginAttempt() {
	}

	public int getLoginAttemptID() {
		return this.loginAttemptID;
	}

	public void setLoginAttemptID(int loginAttemptID) {
		this.loginAttemptID = loginAttemptID;
	}

	public Timestamp getAttemptDT() {
		return this.attemptDT;
	}

	public void setAttemptDT(Timestamp attemptDT) {
		this.attemptDT = attemptDT;
	}

	public String getBrowserType() {
		return this.browserType;
	}

	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}

	public String getIPAddress() {
		return this.IPAddress;
	}

	public void setIPAddress(String IPAddress) {
		this.IPAddress = IPAddress;
	}

	public String getNameAttempt() {
		return this.nameAttempt;
	}

	public void setNameAttempt(String nameAttempt) {
		this.nameAttempt = nameAttempt;
	}

	public boolean getSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}