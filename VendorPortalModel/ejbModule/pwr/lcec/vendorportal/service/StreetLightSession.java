package pwr.lcec.vendorportal.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pwr.lcec.vendorportal.custom.entity.ServiceOrder;
import pwr.lcec.vendorportal.custom.entity.StreetLightSearchVw;
import pwr.lcec.vendorportal.custom.entity.StreetLightSearchVw_;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;
import pwr.lcec.vendorportal.helper.LoggingInterceptor;
import pwr.lcec.vendorportal.interfaces.StreetLightServiceRemote;

@Stateless(name = "StreetLightServiceBean", mappedName = "ejb/lcec/StreetLightProcessorBean", description = "StreetLightProcessorBean Business Facade")
@Remote({ StreetLightServiceRemote.class })
@Interceptors(LoggingInterceptor.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class StreetLightSession implements StreetLightServiceRemote {

	private static Logger logger = Logger.getLogger(StreetLightSession.class);

	private static final String LCEC = "LCEC";
	
	@PersistenceContext(unitName = "vendorPortalPU")
	private EntityManager em;
	
	private CriteriaBuilder cb;

	public List<StreetLightSearchVw> getServiceOrders(String woId, String soId, String vendorName, Timestamp startDt,
			Timestamp endDt, Integer invoiceStatusId, Integer inspectionStatusId, String workgroup, String iVueStat)
			throws ProcessException {

		cb = em.getCriteriaBuilder();
		CriteriaQuery<StreetLightSearchVw> cq = cb.createQuery(StreetLightSearchVw.class);
		Root<StreetLightSearchVw> sl = cq.from(StreetLightSearchVw.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (StringUtils.isNotEmpty(woId)) {
			predicates.add(cb.equal(sl.get(StreetLightSearchVw_.workOrderId), woId));
		}
		if (StringUtils.isNotEmpty(vendorName)) {
			predicates.add(cb.equal(sl.get(StreetLightSearchVw_.vendorName), vendorName));
		}
		if (StringUtils.isNotEmpty(soId)) {
			predicates.add(cb.equal(sl.get(StreetLightSearchVw_.serviceOrderId), soId));
		}
		if (inspectionStatusId != null && inspectionStatusId != 0) {
			predicates.add(cb.equal(sl.get(StreetLightSearchVw_.inspectionStatusId), inspectionStatusId));
		}
		if (invoiceStatusId != null && invoiceStatusId != 0) {
			predicates.add(cb.equal(sl.get(StreetLightSearchVw_.invoiceStatusId), invoiceStatusId));
		}else {
			predicates.add(cb.equal(sl.get(StreetLightSearchVw_.invoiceStatusId), 1));
		}
		if (StringUtils.isNotEmpty(workgroup) && (!workgroup.equals(LCEC))) {
			predicates.add(cb.equal(sl.get(StreetLightSearchVw_.workGroup), workgroup));
		}
		if (startDt != null && endDt != null) {
			predicates.add(cb.between(sl.get(StreetLightSearchVw_.soCloseDt), startDt, endDt));
		}
		if (StringUtils.isNotEmpty(iVueStat)) {
			predicates.add(cb.equal(sl.get(StreetLightSearchVw_.soStatCode), iVueStat));
		}

		cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		cq.select(sl);
		
		TypedQuery<StreetLightSearchVw> query = em.createQuery(cq);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		List<StreetLightSearchVw> result = null;

		try {
			result = query.getResultList();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}

		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<StreetLightSearchVw> getServiceOrderSummary(List<String> soId, String wrkgrp) throws ProcessException {
	
		List<StreetLightSearchVw> result = null;		
		Query query = null;
		
		query = em.createNamedQuery("StreetLightSearchVw.findAllSum");
		query.setParameter("wrkgrp", wrkgrp);
		query.setParameter("soid", soId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		try {
			result = query.getResultList();
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	public void updateServiceOrder(String invSubmitGuid, String soId, Integer invoiceStatusId)
			throws ValidationException, ProcessException {

		try {
			ServiceOrder so = (ServiceOrder) em.find(ServiceOrder.class, soId);
			so.setInvoiceStatusId(invoiceStatusId);
			so.setInvoiceSubmitGuid(invSubmitGuid);

			em.merge(so);

		} catch (EJBException e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<StreetLightSearchVw> getServiceOrderForInvoice(String wrkgrp, String invoiceStatus) throws ProcessException {
		
		List<StreetLightSearchVw> result = null;
		Query query = null;

		if (wrkgrp.equals(LCEC)) {
			query = em.createNamedQuery("StreetLightSearchVw.findForInvoiceLCEC");
			query.setParameter("invoiceStatus", invoiceStatus);
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		} else {
			query = em.createNamedQuery("StreetLightSearchVw.findForInvoice");
			query.setParameter("wrkgrp", wrkgrp);
			query.setParameter("invoiceStatus", invoiceStatus);
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		}
		try {
			result = query.getResultList();
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<StreetLightSearchVw> getServiceOrderByInvoiceId(Integer invId) throws ProcessException{
		
		List<StreetLightSearchVw> result = null;
		Query query = null;
		
		query = em.createNamedQuery("StreetLightSearchVw.findByInvoiceId");
		query.setParameter("invId", invId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		try {
			result = query.getResultList();
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	public void updateServiceOrderInspection(Integer inspectionId, String soId, Integer inspectionStatusId, String inspectionComment, Timestamp inspectedDt, String inspectedBy)
			throws ValidationException, ProcessException {

		try {
			Query query = em.createNativeQuery(
					"UPDATE ServiceOrder SET inspectionId = ?, inspectionStatusId = ?, inspectedComment = ?, inspectedDt = ?, inspectedBy = ? WHERE serviceOrderId = ?");
			query.setParameter(1, inspectionId);
			query.setParameter(2, inspectionStatusId);
			query.setParameter(3, inspectionComment);
			query.setParameter(4, inspectedDt);
			query.setParameter(5, inspectedBy);
			query.setParameter(6, soId);
			query.executeUpdate();
		} catch (EJBException e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}
	}

	public void updateServiceOrderComment(String comment, String soId) throws ValidationException, ProcessException {
		try {
			Query query = em.createNativeQuery("UPDATE ServiceOrder SET inspectedComment = ? WHERE serviceOrderId = ?");
			query.setParameter(1, comment);
			query.setParameter(2, soId);
			query.executeUpdate();
		} catch (EJBException e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}
	}
}
