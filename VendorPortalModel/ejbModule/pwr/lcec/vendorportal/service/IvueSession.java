package pwr.lcec.vendorportal.service;

import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.interfaces.IvueSessionRemote;

@Stateless(name = "IvueSession", mappedName = "ejb/lcec/IvueSession", description = "Ivue Business Proxy")
@Remote({ IvueSessionRemote.class })
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class IvueSession implements IvueSessionRemote {

	private static Logger logger = Logger.getLogger(IvueSession.class);

	@PersistenceContext(unitName = "IVUEPU")
	private EntityManager em;

	public IvueSession() {
	}

	public void updateWorkflowTask(String workEventCd, Integer wfTaskKey) throws ProcessException {
		try {
			Query query = em.createNativeQuery(
					"UPDATE CIS11013.BI_WRKFLW_TASKS SET BI_WORK_EVENT_CD = ? WHERE BI_WRKFLW_TASKS_KEY = ?");
			query.setParameter(1, workEventCd);
			query.setParameter(2, wfTaskKey);
			query.executeUpdate();
		} catch (EJBException e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}

	}

}
