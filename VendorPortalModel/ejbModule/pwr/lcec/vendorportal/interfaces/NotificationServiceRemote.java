package pwr.lcec.vendorportal.interfaces;

import java.util.List;

import javax.ejb.Remote;

import pwr.lcec.vendorportal.custom.entity.AlertNotification;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;

@Remote
public interface NotificationServiceRemote {
	
	public List<AlertNotification> getAllNotifications();
	
	public AlertNotification createAlertNotification(AlertNotification alertNotification) throws ProcessException, ValidationException;
	
	public AlertNotification getCurrentNotification();
}
