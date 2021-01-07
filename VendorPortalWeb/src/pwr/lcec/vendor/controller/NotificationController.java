package pwr.lcec.vendor.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import pwr.lcec.vendor.controller.NotificationController;
import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendorportal.entity.custom.AlertNotification;
import pwr.lcec.vendorportal.interfaces.NotificationLocal;

public class NotificationController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(NotificationController.class);

	@EJB
	private NotificationLocal notificationService;

	private Date startDt = new Date();
	private Date endDt;
	private AlertNotification currentAlert = new AlertNotification();

	private List<AlertNotification> alerts;
	private AlertNotification alert;
	private boolean activeFlg;
	private boolean showReactivate = false;
	private boolean reactivateFlg = false;
	private boolean showNotification = true;
	ControllerUtil util = new ControllerUtil();

	public void findNotifications() {
		alerts = notificationService.getAllNotifications();

		Date now = new Date();

		if (activeFlg) {
			alerts = alerts.stream().filter(a -> (now.after(a.getEffectiveStartDt()) && now.before(a.getEffectiveEndDt()))).collect(Collectors.toList());
		}
	}

	public void loadNewDialog() {
		this.startDt = new Date();
		this.endDt = null;
		this.currentAlert = new AlertNotification();
		PrimeFaces.current().ajax().update(new String[] { "newDialogFrm" });
		PrimeFaces.current().executeScript("PF('newDialog').show();");
	}

	public void removeAlert() {
		this.currentAlert.setEffectiveEndDt(new Timestamp(System.currentTimeMillis()));

		try {
			this.currentAlert = this.notificationService.updateAlertNotification(this.currentAlert);
			sendMessenge("Notification has been removed succesfully.", FacesMessage.SEVERITY_INFO);
			logger.info("Alert Notification " + this.currentAlert.getAlertNotificationId()
					+ " has been removed succesfully.");
		} catch (Exception e) {
			sendMessenge("Alert Notification could not be removed at this time.", FacesMessage.SEVERITY_ERROR);
			logger.error(e);
		}

		findNotifications();
	}

	public void checkUpdateStatus() {
		if (this.currentAlert.getEffectiveEndDt() != null) {
			this.showReactivate = true;
		} else {
			this.showReactivate = false;
		}

		this.startDt = new Date();
		this.endDt = new Date();

		PrimeFaces.current().ajax().update(new String[] { "updateDialogFrm" });
		PrimeFaces.current().executeScript("PF('updateDialog').show();");
	}

	public void updateAlert() {
		if (this.notificationService.getCurrentNotification() == null) {

			AlertNotification an = this.notificationService
					.getAlertById(this.currentAlert.getAlertNotificationId().intValue());

			an.setTitle(this.currentAlert.getTitle());
			an.setBody(this.currentAlert.getBody());

			an.setEffectiveStartDt(this.util.convertDtTm(this.startDt));
			an.setEffectiveEndDt(this.util.convertDtTm(this.endDt));

			try {
				an = this.notificationService.updateAlertNotification(an);
				sendMessenge("Notification has been updateed succesfully.", FacesMessage.SEVERITY_INFO);
				logger.info("Alert Notification " + this.currentAlert.getAlertNotificationId()
						+ " has been updated succesfully.");
			} catch (Exception e) {
				sendMessenge("Alert Notification could not be updated at this time.", FacesMessage.SEVERITY_ERROR);
				logger.error(e);
			}
		} else {

			sendMessenge("There is already an active Alert.", FacesMessage.SEVERITY_ERROR);
			logger.error("There is already an active Alert.");
		}

		findNotifications();
	}

	public void addNotification() {
		if (this.notificationService.getCurrentNotification() == null) {
			this.currentAlert.setEffectiveStartDt(this.util.convertDtTm(this.startDt));
			this.currentAlert.setEffectiveEndDt(this.util.convertDtTm(this.endDt));

			try {
				this.currentAlert = this.notificationService.createAlertNotification(this.currentAlert);
				sendMessenge("Notification has been added succesfully.", FacesMessage.SEVERITY_INFO);
				logger.info("New Alert Notification " + this.currentAlert.getAlertNotificationId()
						+ " has been added succesfully.");
			} catch (Exception e) {
				sendMessenge("Alert Notification could not be added at this time.", FacesMessage.SEVERITY_ERROR);
				logger.error(e);
			}

		} else {

			sendMessenge("There is already an active Alert.", FacesMessage.SEVERITY_ERROR);
			logger.error("There is already an active Alert.");
		}

		findNotifications();
	}

	public void showNotification() {
		alert = notificationService.getCurrentNotification();

		if (alert != null) {
			PrimeFaces.current().executeScript("PF('bar').show()");
		} else {
			PrimeFaces.current().executeScript("PF('bar').hide()");
		}
		logger.debug("Show notification...." + showNotification);
	}
	
	public void onNotificationBarClose() {
		
		logger.debug("Notification Bar Closed by User....");
		showNotification = false;
		
	}

	public void onDateSelect(AjaxBehaviorEvent event) {
	}

	public void sendMessenge(String msg, FacesMessage.Severity level) {
		FacesMessage message = new FacesMessage();
		message.setSummary(msg);
		message.setSeverity(level);

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public Date getEndDt() {
		return this.endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public List<AlertNotification> getAlerts() {
		return this.alerts;
	}

	public void setAlerts(List<AlertNotification> alerts) {
		this.alerts = alerts;
	}

	public AlertNotification getAlert() {
		return this.alert;
	}

	public void setAlert(AlertNotification alert) {
		this.alert = alert;
	}

	public boolean isActiveFlg() {
		return this.activeFlg;
	}

	public void setActiveFlg(boolean activeFlg) {
		this.activeFlg = activeFlg;
	}

	public AlertNotification getCurrentAlert() {
		return this.currentAlert;
	}

	public void setCurrentAlert(AlertNotification currentAlert) {
		this.currentAlert = currentAlert;
	}

	public boolean isShowReactivate() {
		return this.showReactivate;
	}

	public void setShowReactivate(boolean showReactivate) {
		this.showReactivate = showReactivate;
	}

	public boolean isReactivateFlg() {
		return this.reactivateFlg;
	}

	public void setReactivateFlg(boolean reactivateFlg) {
		this.reactivateFlg = reactivateFlg;
	}

	public boolean isShowNotification() {
		return showNotification;
	}

	public void setShowNotification(boolean showNotification) {
		this.showNotification = showNotification;
	}
	
}
