package pwr.lcec.vendorportal.session;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pwr.lcec.vendorportal.entity.custom.AlertNotification;
import pwr.lcec.vendorportal.interfaces.NotificationLocal;

@Stateless(name = "VpNotificationService", mappedName = "ejb/lcec/VPNotificationService", description = "VP Alert Notification Business Proxy")
@Local({ NotificationLocal.class })
@Interceptors({ pwr.lcec.vendorportal.helper.LoggingInterceptor.class })
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class NotificationSession implements NotificationLocal {

	private static Logger logger = LogManager.getLogger(NotificationSession.class);

	@PersistenceContext(unitName = "vendorPortalPU")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<AlertNotification> getAllNotifications() {
		Query query = em.createNamedQuery("AlertNotification.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	public AlertNotification createAlertNotification(AlertNotification alertNotification) {
		try {
			this.em.persist(alertNotification);
		} catch (Exception e) {
			logger.error(e);
		}

		return alertNotification;
	}

	@SuppressWarnings("unchecked")
	public AlertNotification getCurrentNotification() {
		List<AlertNotification> result = null;

		Query query = this.em.createNamedQuery("AlertNotification.findCurrent");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (EJBException ex) {
			logger.error("ProcessException: " + ex.getMessage());
		} catch (NoResultException nre) {
			logger.warn("NoResultException: " + nre.getMessage());
		}

		if (result.size() == 1)
			return (AlertNotification) result.get(0);
		if (result == null || result.isEmpty()) {
			return null;
		}
		throw new NonUniqueResultException();
	}

	public AlertNotification updateAlertNotification(AlertNotification alertNotification) {
		return (AlertNotification) this.em.merge(alertNotification);
	}

	@SuppressWarnings("unchecked")
	public AlertNotification getAlertById(int alertNotificationId) {
		List<AlertNotification> result = null;

		Query query = this.em.createNamedQuery("AlertNotification.findAlertById");
		query.setParameter("alertNotificationId", Integer.valueOf(alertNotificationId));
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (EJBException ex) {
			logger.error("ProcessException: " + ex.getMessage());
		} catch (NoResultException nre) {
			logger.warn("NoResultException: " + nre.getMessage());
		}

		if (result.size() == 1)
			return (AlertNotification) result.get(0);
		if (result == null || result.isEmpty()) {
			return null;
		}
		throw new NonUniqueResultException();
	}

}
