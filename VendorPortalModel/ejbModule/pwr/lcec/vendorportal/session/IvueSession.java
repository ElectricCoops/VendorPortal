package pwr.lcec.vendorportal.session;

import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pwr.lcec.vendorportal.interfaces.IvueLocal;

@Stateless(name = "VPIvueSession", mappedName = "ejb/lcec/VPIvueSession", description = "VP Ivue Business Proxy")
@Local({ IvueLocal.class })
@Interceptors({ pwr.lcec.vendorportal.helper.LoggingInterceptor.class })
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class IvueSession implements IvueLocal {
	
	private static Logger logger = LogManager.getLogger(IvueSession.class);
	
	@PersistenceContext(unitName = "IVUEPU")
	private EntityManager em;

	@Override
	public void updateWorkflowTask(String workEventCd, Integer wfTaskKey) {

		try {
			Query query = em.createNativeQuery("UPDATE CIS11013.BI_WRKFLW_TASKS SET BI_WORK_EVENT_CD = ? WHERE BI_WRKFLW_TASKS_KEY = ?");
			query.setParameter(1, workEventCd);
			query.setParameter(2, wfTaskKey);
			query.executeUpdate();
		} catch (EJBException e) {
			logger.error(e.getMessage());
			//throw new ProcessException(e.getMessage());
		}
		
	}
	
	

}
