package pwr.lcec.vendorportal.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pwr.lcec.vendorportal.custom.entity.Inspection;
import pwr.lcec.vendorportal.custom.entity.InspectionDetailVw;
import pwr.lcec.vendorportal.custom.entity.InspectionStatus;
import pwr.lcec.vendorportal.custom.entity.Inspection_;
import pwr.lcec.vendorportal.custom.entity.WorkFlow_;
import pwr.lcec.vendorportal.exception.NoResultException;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;
import pwr.lcec.vendorportal.interfaces.InspectionSessionRemote;

@Stateless(name = "InspectionSession", mappedName = "ejb/lcec/InspectionSession", description = "Inspection Business Proxy")
@Remote({ InspectionSessionRemote.class })
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class InspectionSession implements InspectionSessionRemote{

	private static Logger logger = Logger.getLogger(InspectionSession.class);
	
	private static final String LCEC = "LCEC";

	@PersistenceContext(unitName = "vendorPortalPU")
	private EntityManager em;
	private CriteriaBuilder cb;
	
	public InspectionSession() { }
	
	@SuppressWarnings("unchecked")
	public List<Inspection> getAllInspections() {
	
		Query query = em.createNamedQuery("Inspection.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Inspection> getInspectionByWoId(String woId) throws ValidationException, NoResultException, ProcessException{

		if(StringUtils.isEmpty(woId)) {
			logger.warn("ValidationException: Work Order ID is required.");
			throw new ValidationException("Work Order ID is required.");
		}
		
		List<Inspection> result = null;
		
		Query query = em.createNamedQuery("Inspection.findInspectionByWoId");
		query.setParameter("woId", woId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		
		try {
			result = query.getResultList();
		}
		catch(javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: Work Order ID " + woId + " is not found.");
			throw new NoResultException("Work Order ID " + woId + " is not found.");
		}
		catch(Exception e) {
			logger.error("Exception occurred: "+e.getMessage());
			throw new ProcessException(e.getMessage());
		}	
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<InspectionStatus> getInspetionStatus() {
	
		Query	query = em.createNamedQuery("InspectionStatus.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Inspection> getInspectionByStatus(String status)
			throws ValidationException, NoResultException, ProcessException {
		
		List<Inspection> result = null;
		
		Query query = em.createNamedQuery("Inspection.findInspectionByStatus");
		query.setParameter("woStat", status);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		
		try {
			result = query.getResultList();
		}
		catch(javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: No result found for status "+status);
			throw new NoResultException("No result found for status "+status);
		}
		catch(Exception e) {
			logger.error("Exception occurred: "+e.getMessage());
			throw new ProcessException(e.getMessage());
		}	
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Inspection> getInspectionByWoIdAndStatus(String woId, String status)
			throws ValidationException, NoResultException, ProcessException {
		
		if(StringUtils.isEmpty(woId) || StringUtils.isEmpty(status)) {
			logger.warn("ValidationException: Work Order No. and inspection status is required.");
			throw new ValidationException("Work Order No. and inspection status is required.");
		}
		List<Inspection> result = null;
		
		Query query = em.createNamedQuery("Inspection.findInspectionByWoIdAndStatus");
		query.setParameter("woId", woId);
		query.setParameter("woStat", status);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		
		try {
			result = query.getResultList();
		}
		catch(javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: No result found for Work Order No. "+woId+" and status "+status);
			throw new NoResultException("No result found for Work Order No. "+woId+" and status "+status);
		}
		catch(Exception e) {
			logger.error("Exception occurred: "+e.getMessage());
			throw new ProcessException(e.getMessage());
		}	
		return result;
	}

	public Integer insertInspection(Inspection inspection) throws ValidationException, ProcessException {

		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("NEW_INSPECTION");
		query.setParameter("IN_InspectionDT", inspection.getInspectionDt());
		query.setParameter("IN_InspectedBy", inspection.getInspectedBy());
		query.setParameter("IN_InspectionStatusID", inspection.getInspectionStatusId());
		query.setParameter("IN_COMMENTS", inspection.getComments());
		query.setParameter("IN_WORKORDER_ID", inspection.getWorkOrderId());
		query.setParameter("IN_InspectionType", inspection.getInspectionType());
		try {
			query.execute();
		}catch(EJBException e) {
			logger.error(e);
			throw new ProcessException(e.getMessage());
		}
		Integer inspectionId = (Integer) query.getOutputParameterValue("OUT_Inspection_ID");
		
		return inspectionId;
	}

	@SuppressWarnings("unchecked")
	public List<InspectionDetailVw> getInspectionDetailByInspId(Integer inspectionId)
			throws ValidationException, NoResultException, ProcessException {
		if(inspectionId == 0) {
			logger.warn("ValidationException: Inspection ID is required.");
			throw new ValidationException("Inspection ID is required.");
		}
		
		List<InspectionDetailVw> result = null;
		
		Query query = em.createNamedQuery("InspectionDetailVw.findByInspId");
		query.setParameter("inspId", inspectionId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		
		try {
			result = query.getResultList();
		}
		catch(javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: Inspection ID " + inspectionId + " is not found.");
			throw new NoResultException("Inspection ID " + inspectionId + " is not found.");
		}
		catch(Exception e) {
			logger.error("Exception occurred: "+e.getMessage());
			throw new ProcessException(e.getMessage());
		}	
		return result;
	}

	public int getResourceId(String resourceName) throws ValidationException, NoResultException, ProcessException {

		if (StringUtils.isEmpty(resourceName)) {
			logger.warn("ValidationException: Resource name is required.");
			throw new ValidationException("Resource name is required.");
		}

		int result = 0;

		Query query = em.createNamedQuery("Resource.findByResourceName");
		query.setParameter("name", resourceName);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = (int) query.getSingleResult();
		} catch (javax.persistence.NoResultException | EJBException ex) {
			logger.error(ex);
		}
		return result;
	}
	
	public void updateInspection(Integer inspectionId, Integer inspectionStatusId)
			throws ValidationException, ProcessException {
		try {
			Inspection inspection = (Inspection) em.find(Inspection.class, inspectionId);
			inspection.setInspectionStatusId(inspectionStatusId);

			em.merge(inspection);

		} catch (EJBException e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}
	}

	public List<Inspection> getInspections(String woId, Integer inspectionStatus, String workgroup)
			throws ValidationException, ProcessException {

		cb = em.getCriteriaBuilder();
		CriteriaQuery<Inspection> cq = cb.createQuery(Inspection.class);

		Root<Inspection> inspection = cq.from(Inspection.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (StringUtils.isNotEmpty(woId)) {
			predicates.add(cb.equal(inspection.get(Inspection_.workOrderId), woId));
		}
		if (inspectionStatus != null && inspectionStatus != 0) {
			predicates.add(cb.equal(inspection.get(Inspection_.inspectionStatusId), inspectionStatus));
		}
		
		if (StringUtils.isNotEmpty(workgroup) && (!workgroup.equals(LCEC))) {
			predicates.add(cb.equal(inspection.get(Inspection_.workFlow).get(WorkFlow_.workGroup), workgroup));
		}
		predicates.add(cb.equal(inspection.get(Inspection_.inspectionType), "SS"));

		cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

		cq.select(inspection);
		
		TypedQuery<Inspection> typedQuery = em.createQuery(cq);
		//typedQuery.setHint("javax.persistence.cache.storeMode", "REFRESH");
		
		List<Inspection> result = null;
		
		try {
			result = typedQuery.getResultList();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}

		return result;
	}
	
	public Inspection getInspectionStatusId(Integer inspectionId) throws ValidationException, ProcessException {
		
		Inspection result;

		Query query = em.createNamedQuery("Inspection.findInspectionStatus");
		query.setParameter("inspectionId", inspectionId);
		//query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = (Inspection) query.getSingleResult();
		} catch (EJBException ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return result;
	}
}
