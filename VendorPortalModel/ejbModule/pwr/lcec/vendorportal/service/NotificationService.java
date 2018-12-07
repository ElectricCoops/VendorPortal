package pwr.lcec.vendorportal.service;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import pwr.lcec.vendorportal.custom.entity.AlertNotification;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;
import pwr.lcec.vendorportal.interfaces.NotificationServiceRemote;

@Stateless(name = "NotificationService", mappedName = "ejb/lcec/NotificationService", description = "Alert Notification Business Proxy")
@Remote({ NotificationServiceRemote.class })
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class NotificationService implements NotificationServiceRemote {

	private static Logger logger = Logger.getLogger(NotificationService.class);

	@PersistenceContext(unitName = "vendorPortalPU")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<AlertNotification> getAllNotifications() {

		Query query = em.createNamedQuery("AlertNotification.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	public AlertNotification createAlertNotification(AlertNotification alertNotification)
			throws ProcessException, ValidationException {
		if (alertNotification == null) {
			logger.warn("alertNotification is null");
			throw new ValidationException("AlertNotification cannot be empty.");
		}
		try {
			em.persist(alertNotification);
		} catch (Exception e) {
			logger.error(e);
			throw new ProcessException(e.getMessage());
		}
		return alertNotification;
	}

	public AlertNotification getCurrentNotification() {

		AlertNotification result = null;

		Query query = em.createNamedQuery("AlertNotification.findCurrent");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = (AlertNotification) query.getSingleResult();
		} catch (EJBException ex) {
			logger.error("ProcessException: " + ex.getMessage());
		} catch (NoResultException nre) {
			logger.warn("NoResultException: " + nre.getMessage());
		}
		return result;
	}

}
