package pwr.lcec.vendorportal.session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pwr.lcec.vendorportal.entity.ag.AGInspection;
import pwr.lcec.vendorportal.entity.ag.AGInvoice;
import pwr.lcec.vendorportal.entity.ag.AGStakingSheet;
import pwr.lcec.vendorportal.entity.ag.AGStreetlight;
import pwr.lcec.vendorportal.interfaces.DashboardLocal;

@Stateless(name = "VPDashboardService", mappedName = "ejb/lcec/VPDashboardService", description = "Vp Dashboard Business Proxy")
@Local({ DashboardLocal.class })
@Interceptors({ pwr.lcec.vendorportal.helper.LoggingInterceptor.class })
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class DashboardSession implements DashboardLocal {
	
	private static Logger logger = LogManager.getLogger(DashboardSession.class);

	@PersistenceContext(unitName = "vendorPortalPU")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<AGInspection> findAllAGInspection() {
		
		Query query = this.em.createNamedQuery("AGInspection.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AGInvoice> findAllAGInvoice() {
		
		Query query = em.createNamedQuery("AGInvoice.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AGStakingSheet> findAllAGStakingSheet() {

		Query query = em.createNamedQuery("AGStakingSheet.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AGStreetlight> findAllAGStreetlight() {

		Query query = em.createNamedQuery("AGStreetlight.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	@Override
	public void runAggregateSync() {

		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("RunAggregateSync");
		try {
			query.execute();
		} catch (Exception ex) {
			logger.error(ex);
		}
		
	}

}
