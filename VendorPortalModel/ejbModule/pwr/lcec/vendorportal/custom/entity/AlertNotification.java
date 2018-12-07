package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the AlertNotification database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "AlertNotification.findAll", query = "SELECT a FROM AlertNotification a"),
		@NamedQuery(name = "AlertNotification.findCurrent", query = "SELECT a FROM AlertNotification a WHERE CURRENT_TIMESTAMP BETWEEN a.effectiveStartDt AND a.effectiveEndDt") })
public class AlertNotification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AlertNotificationId")
	private Integer alertNotificationId;

	@Column(name = "Body")
	private String body;

	@Column(name = "EffectiveEndDt")
	private Timestamp effectiveEndDt;

	@Column(name = "EffectiveStartDt")
	private Timestamp effectiveStartDt;

	@Column(name = "Title")
	private String title;

	public AlertNotification() {
	}

	public Integer getAlertNotificationId() {
		return this.alertNotificationId;
	}

	public void setAlertNotificationId(Integer alertNotificationId) {
		this.alertNotificationId = alertNotificationId;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Timestamp getEffectiveEndDt() {
		return this.effectiveEndDt;
	}

	public void setEffectiveEndDt(Timestamp effectiveEndDt) {
		this.effectiveEndDt = effectiveEndDt;
	}

	public Timestamp getEffectiveStartDt() {
		return this.effectiveStartDt;
	}

	public void setEffectiveStartDt(Timestamp effectiveStartDt) {
		this.effectiveStartDt = effectiveStartDt;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}