package pwr.lcec.vendor.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;

import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendorportal.custom.entity.AlertNotification;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;
import pwr.lcec.vendorportal.interfaces.NotificationServiceRemote;

public class NotificationController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(NotificationController.class);
	
	@EJB
	private NotificationServiceRemote notificationService;

	private String title;
	private String description;
	private Date startDt;
	private Date endDt;
	private List<AlertNotification> alerts;
	private AlertNotification alert;
	
	ControllerUtil util = new ControllerUtil();
	
	public void findNotifications() {
		alerts = notificationService.getAllNotifications();
	}

	public void addNotification() {

		AlertNotification notification = new AlertNotification();
		notification.setTitle(title);
		notification.setBody(description);
		notification.setEffectiveStartDt(util.convertDtTm(startDt));
		notification.setEffectiveEndDt(util.convertDtTm(endDt));
		try {
			notificationService.createAlertNotification(notification);
			facesInfo("Notification has been updated succesfully.");
			clear();
		} catch (ProcessException | ValidationException e) {
			logger.error(e);
			facesError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void showNotification() {

		alert = notificationService.getCurrentNotification();

		if (alert != null) {
			PrimeFaces.current().executeScript("PF('bar').show()");
		} else {
			PrimeFaces.current().executeScript("PF('bar').hide()");
		}
	}

	public void clear() {
		title = null;
		description = null;
		startDt = null;
		endDt = null;
	}
	
	private void facesInfo(String message) {

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}

	private void facesError(String message) {

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDt() {
		return startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public Date getEndDt() {
		return endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public List<AlertNotification> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<AlertNotification> alerts) {
		this.alerts = alerts;
	}

	public AlertNotification getAlert() {
		return alert;
	}

	public void setAlert(AlertNotification alert) {
		this.alert = alert;
	}
}
