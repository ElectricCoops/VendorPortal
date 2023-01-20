package pwr.lcec.vendorportal.interfaces;

import java.util.List;

import javax.ejb.Local;

import pwr.lcec.vendorportal.entity.custom.AlertNotification;

@Local
public interface NotificationLocal {
	
	public List<AlertNotification> getAllNotifications();

	public AlertNotification createAlertNotification(AlertNotification paramAlertNotification);

	public AlertNotification updateAlertNotification(AlertNotification paramAlertNotification);

	public AlertNotification getCurrentNotification();

	public AlertNotification getAlertById(int paramInt);

}
