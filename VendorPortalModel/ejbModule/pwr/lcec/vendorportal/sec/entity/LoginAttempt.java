package pwr.lcec.vendorportal.sec.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the LoginAttempt database table.
 * 
 */
@Entity
@NamedQuery(name="LoginAttempt.findAll", query="SELECT l FROM LoginAttempt l")
public class LoginAttempt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="LoginAttemptID")
	private int loginAttemptID;

	@Column(name="AttemptDT")
	private Timestamp attemptDT;

	@Column(name="BrowserType")
	private String browserType;

	private String IPAddress;

	@Column(name="Success")
	private boolean success;

	@Column(name="UserID")
	private int userID;
	
	@Column(name="NameAttempt")
	private String nameAttempt;

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

	public String getNameAttempt() {
		return nameAttempt;
	}

	public void setNameAttempt(String nameAttempt) {
		this.nameAttempt = nameAttempt;
	}

}