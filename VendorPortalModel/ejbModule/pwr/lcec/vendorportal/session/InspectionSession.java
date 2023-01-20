package pwr.lcec.vendorportal.session;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pwr.lcec.vendorportal.entity.custom.Inspection;
import pwr.lcec.vendorportal.entity.custom.InspectionDetail;
import pwr.lcec.vendorportal.entity.custom.InspectionDetailVw;
import pwr.lcec.vendorportal.entity.custom.InspectionStatus;
import pwr.lcec.vendorportal.entity.custom.Inspection_;
import pwr.lcec.vendorportal.entity.custom.WorkFlow_;
import pwr.lcec.vendorportal.interfaces.InspectionLocal;

@Stateless(name = "VPInspectionSession", mappedName = "ejb/lcec/VPInspectionSession", description = "VP Inspection Business Proxy")
@Local({ InspectionLocal.class })
@Interceptors({ pwr.lcec.vendorportal.helper.LoggingInterceptor.class })
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class InspectionSession implements InspectionLocal {
	
	private static Logger logger = LogManager.getLogger(InspectionSession.class);

	private static final String LCEC = "LCEC";

	@PersistenceContext(unitName = "vendorPortalPU")
	private EntityManager em;
	
	private CriteriaBuilder cb;

	@SuppressWarnings("unchecked")
	@Override
	public List<Inspection> getAllInspections() {
		
		List<Inspection> found = null;

		Query query = em.createNamedQuery("Inspection.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			found = new ArrayList<Inspection>(query.getResultList());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return  found;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Inspection> getInspectionByWoId(String workOrderId) {
		
		if (StringUtils.isEmpty(workOrderId)) {
			logger.warn("ValidationException: Work Order ID is required.");
			//logger.error("Work Order ID is required.");
		}

		List<Inspection> result = null;

		Query query = this.em.createNamedQuery("Inspection.findInspectionByWoId");
		query.setParameter("woId", workOrderId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (NoResultException nre) {
			logger.warn("NoResultException: Work Order ID " + workOrderId + " is not found.");
			throw new NoResultException("Work Order ID " + workOrderId + " is not found.");
		} catch (Exception e) {
			logger.error("Exception occurred: " + e.getMessage());
			//throw new ProcessException(e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Inspection> getInspectionByStatus(String inspectionStatusId) {
		
		List<Inspection> result = null;

		Query query = this.em.createNamedQuery("Inspection.findInspectionByStatus");
		query.setParameter("woStat", inspectionStatusId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (NoResultException nre) {
			logger.warn("NoResultException: No result found for status " + inspectionStatusId);
			throw new NoResultException("No result found for status " + inspectionStatusId);
		} catch (Exception e) {
			logger.error("Exception occurred: " + e.getMessage());
			//throw new ProcessException(e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Inspection> getInspectionByWoIdAndStatus(String workOrderId, String inspectionStatusId) {
		
		if (StringUtils.isEmpty(workOrderId) || StringUtils.isEmpty(inspectionStatusId)) {
			logger.warn("ValidationException: Work Order No. and inspection status is required.");
			//throw new ValidationException("Work Order No. and inspection status is required.");
		}
		
		List<Inspection> result = null;

		Query query = this.em.createNamedQuery("Inspection.findInspectionByWoIdAndStatus");
		query.setParameter("woId", workOrderId);
		query.setParameter("woStat", inspectionStatusId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (NoResultException nre) {
			logger.warn("NoResultException: No result found for Work Order No. " + workOrderId + " and status " + inspectionStatusId);
			throw new NoResultException("No result found for Work Order No. " + workOrderId + " and status " + inspectionStatusId);
		} catch (Exception e) {
			logger.error("Exception occurred: " + e.getMessage());
			//throw new ProcessException(e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InspectionStatus> getInspectionStatus() {
		
		List<InspectionStatus> found = null;

		Query query = em.createNamedQuery("InspectionStatus.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			found = query.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return  found;
	}

	@Override
	public Integer insertInspection(Inspection inspection) {

		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("Inspection.NEW_INSPECTION");
		query.setParameter("IN_InspectionDT", inspection.getInspectionDt());
		query.setParameter("IN_InspectedBy", Integer.valueOf(inspection.getInspectedBy()));
		query.setParameter("IN_InspectionStatusID", Integer.valueOf(inspection.getInspectionStatusId()));
		query.setParameter("IN_COMMENTS", inspection.getComments());
		query.setParameter("IN_WORKORDER_ID", inspection.getWorkOrderId());
		query.setParameter("IN_InspectionType", inspection.getInspectionType());
		try {
			query.execute();
		} catch (EJBException e) {
			logger.error(e);
			//throw new ProcessException(e.getMessage());
		}
		return (Integer) query.getOutputParameterValue("OUT_Inspection_ID");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InspectionDetailVw> getInspectionDetailByInspId(Integer inspectionId) {
		
		if (inspectionId.intValue() == 0) {
			logger.warn("ValidationException: Inspection ID is required.");
			//throw new ValidationException("Inspection ID is required.");
		}

		List<InspectionDetailVw> result = null;

		Query query = this.em.createNamedQuery("InspectionDetailVw.findByInspId");
		query.setParameter("inspId", inspectionId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (NoResultException nre) {
			logger.warn("NoResultException: Inspection ID " + inspectionId + " is not found.");
			throw new NoResultException("Inspection ID " + inspectionId + " is not found.");
		} catch (Exception e) {
			logger.error("Exception occurred: " + e.getMessage());
			//throw new ProcessException(e.getMessage());
		}
		return result;
	}

	@Override
	public int getResourceId(String resourceName) {
		
		if (StringUtils.isEmpty(resourceName)) {
			logger.warn("ValidationException: Resource name is required.");
			//throw new ValidationException("Resource name is required.");
		}

		int result = 0;

		Query query = this.em.createNamedQuery("Resource.findByResourceName");
		query.setParameter("name", resourceName);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = ((Integer) query.getSingleResult()).intValue();
		} catch (NoResultException | EJBException ex) {
			logger.error(ex);
		}
		return result;
	}

	@Override
	public void updateInspection(Integer inspectionId, Integer inspectionStatusId) {
		
		try {
			Inspection inspection = (Inspection) em.find(Inspection.class, inspectionId);
			inspection.setInspectionStatusId(inspectionStatusId.intValue());
			inspection.setInspectionDt(new Timestamp(System.currentTimeMillis()));

			em.merge(inspection);
		} catch (EJBException e) {
			logger.error(e.getMessage());
			//throw new ProcessException(e.getMessage());
		}
		
	}

	@Override
	public List<Inspection> getInspections(String workOrderId, Integer inspectionStatusId, String workgroup) {
		
		cb = em.getCriteriaBuilder();
		CriteriaQuery<Inspection> cq = cb.createQuery(Inspection.class);

		Root<Inspection> inspection = cq.from(Inspection.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (StringUtils.isNotEmpty(workOrderId)) {
			predicates.add(this.cb.equal(inspection.get(Inspection_.workOrderId), workOrderId));
		}
		if (inspectionStatusId != null && inspectionStatusId.intValue() != 0) {
			predicates.add(this.cb.equal(inspection.get(Inspection_.inspectionStatusId), inspectionStatusId));
		}

		if (StringUtils.isNotEmpty(workgroup) && !workgroup.equals(LCEC)) {
			predicates.add(cb.equal(inspection.get(Inspection_.workFlow).get(WorkFlow_.workGroup), workgroup));
		}
		predicates.add(cb.equal(inspection.get(Inspection_.inspectionType), "SS"));

		cq.where(cb.and((Predicate[]) predicates.toArray(new Predicate[predicates.size()])));

		cq.select(inspection);

		TypedQuery<Inspection> typedQuery = em.createQuery(cq);

		List<Inspection> result = null;

		try {
			result = typedQuery.getResultList();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			//throw new ProcessException(ex.getMessage());
		}

		return result;
		
	}

	@Override
	public Inspection getInspectionStatusId(Integer inspectionId) {
		
		Inspection result = null;
		Query query = this.em.createNamedQuery("Inspection.findInspectionStatus");
		query.setParameter("inspectionId", inspectionId);

		try {
			result = (Inspection) query.getSingleResult();
		} catch (EJBException ex) {
			logger.error("ProcessException: " + ex.getMessage());
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@Override
	public int removeNullInspections(String workOrderId) {
		
		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("Inspection.DELETE_NULL_INSPECTIONS");

		query.setParameter("IN_WorkOrderId", workOrderId);

		try {
			query.execute();
		} catch (EJBException e) {
			logger.error(e);
		}
		Integer result = (Integer) query.getOutputParameterValue("OUT_Result");

		return result.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Inspection getInspectionByInspectionId(int inspectionId) {
		
		List<Inspection> result = null;

		Query query = this.em.createNamedQuery("Inspection.findInspectionById");
		query.setParameter("inspectionId", Integer.valueOf(inspectionId));
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (NoResultException nre) {
			logger.warn("NoResultException: Work Order ID " + inspectionId + " is not found.");
		} catch (Exception e) {
			logger.error("Exception occurred: " + e.getMessage());
		}

		if (result.size() == 1)
			return (Inspection) result.get(0);
		if (result == null || result.isEmpty()) {
			return null;
		}
		throw new NonUniqueResultException();
	}

	@Override
	public void removeInspectionDetail(Integer inspectionId) {
		
		Query query = this.em.createNamedQuery("InspectionDetail.deleteInspectionDetailByInspectionId");
		query.setParameter("inspectionId", inspectionId);
		try {
			query.executeUpdate();
		} catch (Exception e) {
			logger.error("Error Occured: " + e.getMessage());
		}
		
	}

	@Override
	public void removeInspection(Integer inspectionId) {
		
		try {
			Inspection inspection = (Inspection) em.find(Inspection.class, inspectionId);

			em.remove(inspection);
		} catch (EJBException e) {
			logger.error(e.getMessage());
		}
		
	}

	@Override
	public boolean removeInspectionDetailById(int inspectionDetailId) {

		try {
			InspectionDetail insDetail = em.find(InspectionDetail.class, inspectionDetailId);
			
			em.remove(insDetail);
			return true;
		} catch (Exception e) {
			logger.error("Error occured: " + e.getMessage());
			return false;
		}
	
	}

}
