package pwr.lcec.vendorportal.service;

import java.sql.Timestamp;
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

import pwr.lcec.vendorportal.custom.entity.Invoice;
import pwr.lcec.vendorportal.custom.entity.InvoiceDetail;
import pwr.lcec.vendorportal.custom.entity.InvoiceGLSummaryVw;
import pwr.lcec.vendorportal.custom.entity.InvoiceSearchVw;
import pwr.lcec.vendorportal.custom.entity.InvoiceSearchVw_;
import pwr.lcec.vendorportal.custom.entity.InvoiceStatus;
import pwr.lcec.vendorportal.custom.entity.ServiceOrder;
import pwr.lcec.vendorportal.exception.NoResultException;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;
import pwr.lcec.vendorportal.interfaces.InvoiceSessionRemote;

@Stateless(name = "InvoiceSession", mappedName = "ejb/lcec/InvoiceSession", description = "Invoice Business Proxy")
@Remote({ InvoiceSessionRemote.class })
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class InvoiceSession implements InvoiceSessionRemote {

	private static Logger logger = Logger.getLogger(InvoiceSession.class);

	private static final String LCEC = "LCEC";

	@PersistenceContext(unitName = "vendorPortalPU")
	private EntityManager em;
	
	private CriteriaBuilder cb;

	public InvoiceSession() { }

	public Invoice getInvoiceById(Integer invId)
			throws ValidationException, ProcessException, NoResultException {

		if (invId == null) {
			logger.warn("ValidationException: Invoice ID is required.");
			throw new ValidationException("Invoice ID is required.");
		}

		Invoice result = null;

		Query query = em.createNamedQuery("Invoice.findByInvId");
		query.setParameter("invId", invId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = (Invoice) query.getSingleResult();
		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: No Invoice found for ID: " + invId);
			throw new NoResultException("No result found for Invoice ID: " + invId);
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Invoice> getAllInvoices() {

		Query query = em.createNamedQuery("Invoice.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Invoice> getInvoiceByWoId(String woid, String wrkgrp)
			throws ValidationException, ProcessException, NoResultException {
		List<Invoice> result = null;
		Query query = null;

		if (wrkgrp.equals(LCEC)) {
			query = em.createNamedQuery("Invoice.findByWoId");
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		} else {
			query = em.createNamedQuery("Invoice.findByWoId");
			query.setParameter("woId", woid);
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
	public List<InvoiceSearchVw> getAllSearchInvoices(String workgroup) throws ProcessException {
		List<InvoiceSearchVw> result = null;
		Query query = null;
		
		if(workgroup.equals(LCEC)) {
			query = em.createNamedQuery("InvoiceSearchVw.findAllLCEC");
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		}else {
			query = em.createNamedQuery("InvoiceSearchVw.findAll");
			query.setParameter("wrkgrp", workgroup);
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		}
		try {
			result = query.getResultList();
		}
		 catch (Exception ex) {
				logger.error("ProcessException: " + ex.getMessage());
				throw new ProcessException(ex.getMessage());
			}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceSearchVw> getSearchInvoiceById(String invId, String workgroup)
			throws ValidationException, ProcessException, NoResultException {
		if (invId == null) {
			logger.warn("ValidationException: Invoice Number is required.");
			throw new ValidationException("Invoice Number is required.");
		}

		Query query = null;
		List<InvoiceSearchVw> result = null;

		if (workgroup.equals(LCEC)) {
			query = em.createNamedQuery("InvoiceSearchVw.findByIdLCEC");
			query.setParameter("invId", invId);
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		} else {
			query = em.createNamedQuery("InvoiceSearchVw.findById");
			query.setParameter("invId", invId);
			query.setParameter("wrkgrp", workgroup);
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		}
		try {
			result = query.getResultList();
		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: No Invoice found for ID: " + invId);
			throw new NoResultException("No result found for Invoice ID: " + invId);
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceSearchVw> getSearchInvoiceByWoId(String woId, String workgroup)
			throws ValidationException, ProcessException, NoResultException {
		Query query = null;
		List<InvoiceSearchVw> result = null;

		if (workgroup.equals(LCEC)) {
			query = em.createNamedQuery("InvoiceSearchVw.findByWoIdLCEC");
			query.setParameter("woId", woId);
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		} else {
			query = em.createNamedQuery("InvoiceSearchVw.findByWoId");
			query.setParameter("woId", woId);
			query.setParameter("wrkgrp", workgroup);
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		}
		try {
			result = query.getResultList();
		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: No result found for Work Order: " + woId);
			throw new NoResultException("No result found for Work Order: " + woId);
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<String> getWoForInvoice(String workgroup) throws NoResultException, ProcessException {

		Query query = null;
		List<String> result = null;

		query = em.createNamedQuery("WorkFlowSearch_VW.findAllWorkFlowWoId");
		query.setParameter("wrkgrp", workgroup);
		query.setParameter("woId", "");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: No Work Orders found");
			throw new NoResultException("NoResultException: No Work Orders found");
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	public int updateSubmitInvoice(String guid, String invoicedBy, String vendorRefNo, String invoiceType)
			throws ValidationException, ProcessException {

		if (StringUtils.isEmpty(guid) || StringUtils.isEmpty(invoicedBy) || StringUtils.isEmpty(vendorRefNo)) {
			logger.warn("ValidationException: Vendor Reference, Invoice By or Guid ID cannot be blank.");
			throw new ValidationException(
					"ValidationException: Vendor Reference, Invoice By or Guid ID cannot be blank.");
		}

		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("UPDATE_SUBMIT_INVOICE");
		query.setParameter("IN_GUID", guid);
		query.setParameter("IN_InvoiceType", invoiceType);
		query.setParameter("IN_InvoicedBy", invoicedBy);
		query.setParameter("IN_VendorReference", vendorRefNo);

		try {
			query.execute();
		} catch (EJBException e) {
			logger.error(e);
			throw new ProcessException(e.getMessage());
		}
		int output = (int) query.getOutputParameterValue("OUT_InvoiceId");

		return output;
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceStatus> getAllInvStatus() {
		Query query = em.createNamedQuery("InvoiceStatus.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceSearchVw> getSearchInvoiceByVenRef(String venRefNo, String workgroup) throws ValidationException, ProcessException, NoResultException {

		if (venRefNo == null) {
			logger.warn("ValidationException: Vendor Reference Number is required.");
			throw new ValidationException("Vendor Reference Number is required.");
		}

		Query query = null;
		List<InvoiceSearchVw> result = null;

		if (workgroup.equals(LCEC)) {
			query = em.createNamedQuery("InvoiceSearchVw.findByVenRefNoLCEC");
			query.setParameter("venRefNo", venRefNo);
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		} else {
			query = em.createNamedQuery("InvoiceSearchVw.findByVenRefNo");
			query.setParameter("venRefNo", venRefNo);
			query.setParameter("wrkgrp", workgroup);
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		}
		try {
			result = query.getResultList();
		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: No Vendor Reference found for ID: " + venRefNo);
			throw new NoResultException("No result found for Vendor Reference ID: " + venRefNo);
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceSearchVw> getInvoiceByStatus(String workgroup, String invoiceStatus)
			throws NoResultException, ProcessException {

		Query query = null;
		List<InvoiceSearchVw> result = null;

		query = em.createNamedQuery("InvoiceSearchVw.findByInvoiceStatus");
		query.setParameter("invStatus", invoiceStatus);
		query.setParameter("wrkgrp", workgroup);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: No result found for Invoice Status: " + invoiceStatus);
			throw new NoResultException("No result found for InvoiceStatus: " + invoiceStatus);
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}

		return result;
	}

	public List<InvoiceSearchVw> getInvoices(String woId, String vendorName, String lcecRef, String vendorRef, Integer invoiceStatus, String workgroup, String woType)
			throws NoResultException, ProcessException {

		cb = em.getCriteriaBuilder();
		CriteriaQuery<InvoiceSearchVw> cq = cb.createQuery(InvoiceSearchVw.class);
		Root<InvoiceSearchVw> invoice = cq.from(InvoiceSearchVw.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (StringUtils.isNotEmpty(woId)) {
			predicates.add(cb.equal(invoice.get(InvoiceSearchVw_.workOrderId), woId));
		}
		if (StringUtils.isNotEmpty(vendorName)) {
			predicates.add(cb.equal(invoice.get(InvoiceSearchVw_.vendorName), vendorName));
		}
		if (StringUtils.isNotEmpty(lcecRef)) {
			predicates.add(cb.equal(invoice.get(InvoiceSearchVw_.invoiceId), lcecRef));
		}
		if (StringUtils.isNotEmpty(vendorRef)) {
			predicates.add(cb.equal(invoice.get(InvoiceSearchVw_.vendorReference), vendorRef));
		}
		if (StringUtils.isNotEmpty(woType)) {
			predicates.add(cb.equal(invoice.get(InvoiceSearchVw_.invoiceType), woType));
		}
		if (invoiceStatus != null && invoiceStatus != 0) {
			predicates.add(cb.equal(invoice.get(InvoiceSearchVw_.invoiceStatusId), invoiceStatus));
		}
		if (StringUtils.isNotEmpty(workgroup) && (!workgroup.equals(LCEC))) {
			predicates.add(cb.equal(invoice.get(InvoiceSearchVw_.workGroup), workgroup));
		}

		cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

		cq.select(invoice);
		cq.orderBy(cb.desc(invoice.get(InvoiceSearchVw_.invoicedDt)));
		
		TypedQuery<InvoiceSearchVw> typedQuery = em.createQuery(cq);
		typedQuery.setHint("javax.persistence.cache.storeMode", "REFRESH");
		
		List<InvoiceSearchVw> result = null;

		try {
			result = typedQuery.getResultList();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}		
		
		return result;
	}
	
	public int updateWorkflowCalc(Integer stakingsheetId, Timestamp rateDt, String typeFlg) throws ValidationException {

		if(stakingsheetId == null || rateDt == null || StringUtils.isEmpty(typeFlg)) {
			logger.warn("ValidationException: stakingsheetId, rateDt or typeFlg cannot be blank.");
			throw new ValidationException("ValidationException: stakingsheetId, rateDt or typeFlg cannot be blank.");
		}
		
		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("UPDATE_WORKFLOW_CALC");
		query.setParameter("IN_StakingSheetId", stakingsheetId);
		query.setParameter("IN_RateDate", rateDt);
		query.setParameter("IN_TypeFlag", typeFlg);
		query.execute();

		int output = (int) query.getOutputParameterValue("OUT_Response");

		return output;
	}
	
	public int updateInvoiceStatus(Integer invoiceId, Integer invoiceStatus, String invoiceType, String approvedBy,
			Timestamp approvedDt, String approvedComment) throws ValidationException {
		
		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("UPDATE_INVOICE_STATUS");
		query.setParameter("IN_InvoiceId", invoiceId);
		query.setParameter("IN_InvoiceStatus", invoiceStatus);
		query.setParameter("IN_InvoiceType", invoiceType);
		query.setParameter("IN_ApprovedBy", approvedBy);
		query.setParameter("IN_ApprovedDt", approvedDt);
		query.setParameter("IN_ApprovedComment", approvedComment);
		query.execute();
		
		int output = (int) query.getOutputParameterValue("OUT_Response");
		
		return output;
	}

	public void updateInvoiceApproval(Integer invoiceId, Integer invoiceStatusId, String invoiceAprrovedBy,
			Timestamp invoiceApprovedDt) throws ProcessException {
		try {
			Query query = em.createNativeQuery("UPDATE StakingSheetDetail SET InvoiceStatusId = ?, InvoiceApprovedBy = ?, InvoiceApprovedDt = ? WHERE InvoiceId = ?");
			query.setParameter(1, invoiceStatusId);
			query.setParameter(2, invoiceAprrovedBy);
			query.setParameter(3, invoiceApprovedDt);
			query.setParameter(4, invoiceId);
			query.executeUpdate();

		} catch (EJBException e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceGLSummaryVw> getInvoiceGLSummaryVw(Integer invoiceId) throws ProcessException, NoResultException {
		Query query = null;
		List<InvoiceGLSummaryVw> result = null;

		query = em.createNamedQuery("InvoiceGLSummaryVw.findAll");
		query.setParameter("invoiceId", invoiceId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: No result found for Invoice ID: " + invoiceId);
			throw new NoResultException("No result found for Invoice ID: " + invoiceId);
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceDetail> getInvoiceDetails(Integer invoiceId) throws ProcessException, NoResultException {
		Query query = null;
		List<InvoiceDetail> result = null;

		query = em.createNamedQuery("InvoiceDetail.findByInvoiceId");
		query.setParameter("invoiceId", invoiceId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: No result found for Invoice ID: " + invoiceId);
			throw new NoResultException("No result found for Invoice ID: " + invoiceId);
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return result;
	}
	
	public void updateStreetlightInvoiceApproval(String soId, Integer invoiceStatusId, String invoiceAprrovedBy,
			Timestamp invoiceApprovedDt, String invoiceApprovedComment) throws ProcessException {

		try {
			ServiceOrder serviceOrder = em.find(ServiceOrder.class, soId);
			serviceOrder.setInvoiceStatusId(invoiceStatusId);
			serviceOrder.setInvoiceApprovedBy(invoiceAprrovedBy);
			serviceOrder.setInvoiceApprovedDt(invoiceApprovedDt);
			serviceOrder.setInvoiceApprovedComments(invoiceApprovedComment);

			em.merge(serviceOrder);
			
		} catch (EJBException e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}
	}
}
