package pwr.lcec.vendorportal.service;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.apache.log4j.Logger;

import pwr.lcec.vendorportal.ag.entity.AGInspection;
import pwr.lcec.vendorportal.ag.entity.AGInspector;
import pwr.lcec.vendorportal.ag.entity.AGInvoice;
import pwr.lcec.vendorportal.ag.entity.AGStakingSheet;
import pwr.lcec.vendorportal.ag.entity.AGStreetlight;
import pwr.lcec.vendorportal.interfaces.DashboardServiceRemote;

@Stateless(name = "DashboardService", mappedName = "ejb/lcec/DashboardService", description = "Dashboard Business Proxy")
@Remote({ DashboardServiceRemote.class })
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class DashboardService implements DashboardServiceRemote{
	
	private static Logger logger = Logger.getLogger(DashboardService.class);
	
	@PersistenceContext(unitName = "vendorPortalPU")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<AGInspection> findAllAGInspection() {
		Query query = em.createNamedQuery("AGInspection.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<AGInspector> findAllAGInspector() {
		Query query = em.createNamedQuery("AGInspector.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<AGInvoice> findAllAGInvoice() {
		Query query = em.createNamedQuery("AGInvoice.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<AGStakingSheet> findAllAGStakingSheet() {
		Query query = em.createNamedQuery("AGStakingSheet.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<AGStreetlight> findAllAGStreetlight() {
		Query query = em.createNamedQuery("AGStreetlight.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	public void runAggregateSync() {

		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("RunAggregateSync");
		try {
			query.execute();
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

}
