package pwr.lcec.vendorportal.session;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pwr.lcec.vendorportal.entity.custom.TempWMISWorkRequest;
import pwr.lcec.vendorportal.helper.LoggingInterceptor;
import pwr.lcec.vendorportal.interfaces.AddWorkOrderRemote;

@Stateless(name = "AddWOService", mappedName = "ejb/lcec/AddWOService", description = "AddWOService Business Proxy")
@Remote({ AddWorkOrderRemote.class })
@Interceptors(value=LoggingInterceptor.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AddWorkOrderSession implements AddWorkOrderRemote {
	
	private static Logger logger = LogManager.getLogger(AddWorkOrderSession.class);
	
	@PersistenceContext(unitName = "vendorPortalPU")
	private EntityManager em;

	@Override
	public void updateWMISWR(String workRequest) throws Exception {

		try {
			TempWMISWorkRequest wr = (TempWMISWorkRequest) em.find(TempWMISWorkRequest.class, workRequest);

			if (wr != null) {
				wr.setSyncDate(null);
				em.merge(wr);
			} else {
				wr = new TempWMISWorkRequest();
				wr.setWorkRequest(workRequest);
				wr.setSyncDate(null);
				em.persist(wr);
			}

		} catch (EJBException ex) {
			logger.error(ex);
			throw new Exception(ex.getMessage());
		}
	}
}
