package pwr.lcec.vendorportal.service;

import java.math.BigDecimal;
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

import pwr.lcec.vendorportal.bean.RateGroupPivot;
import pwr.lcec.vendorportal.custom.entity.AsBuiltStatus;
import pwr.lcec.vendorportal.custom.entity.AsBuiltSummaryVw;
import pwr.lcec.vendorportal.custom.entity.AssemblyAdhocVw;
import pwr.lcec.vendorportal.custom.entity.Invoice;
import pwr.lcec.vendorportal.custom.entity.InvoiceDetail;
import pwr.lcec.vendorportal.custom.entity.Resource;
import pwr.lcec.vendorportal.custom.entity.StakingSheet;
import pwr.lcec.vendorportal.custom.entity.StakingSheetDetail;
import pwr.lcec.vendorportal.custom.entity.Vendor;
import pwr.lcec.vendorportal.custom.entity.Voucher;
import pwr.lcec.vendorportal.custom.entity.WorkEventStatus;
import pwr.lcec.vendorportal.custom.entity.WorkFlow;
import pwr.lcec.vendorportal.custom.entity.WorkFlowSearch_VW;
import pwr.lcec.vendorportal.custom.entity.WorkFlowTask;
import pwr.lcec.vendorportal.custom.entity.WorkGroup;
import pwr.lcec.vendorportal.custom.entity.WorkOrderAggVw;
import pwr.lcec.vendorportal.exception.NoResultException;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;
import pwr.lcec.vendorportal.interfaces.WorkFlowSessionRemote;

@Stateless(name = "WorkFlowSession", mappedName = "ejb/lcec/WorkFlowSession", description = "WorkFlow Business Proxy")
@Remote({ WorkFlowSessionRemote.class })
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class WorkFlowSession implements WorkFlowSessionRemote {

	private static Logger logger = Logger.getLogger(WorkFlowSession.class);

	@PersistenceContext(unitName = "vendorPortalPU")
	private EntityManager em;
	private CriteriaBuilder cb;
	
	private static final String LCEC = "LCEC";

	public WorkFlowSession() { }


	@SuppressWarnings("unchecked")
	public List<WorkFlowSearch_VW> getWorkflowById(String woId, String workgroup)
			throws ValidationException, NoResultException, ProcessException {
		if (StringUtils.isEmpty(woId)) {
			logger.warn("ValidationException: A valid Work ID is required.");
			throw new ValidationException("A valid Work Order ID is required.");
		}

		List<WorkFlowSearch_VW> result = null;
		Query query = null;
		
		if(workgroup.equals(LCEC)) {
			query = em.createNamedQuery("WorkFlowSearch_VW.findLCECWorkflowByWoId");
			query.setParameter("woId", woId);
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		}
		else {

		query = em.createNamedQuery("WorkFlowSearch_VW.findWorkflowByWoId");
		query.setParameter("woId", woId);
		query.setParameter("workgrp", workgroup);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		}
		try {
			result = query.getResultList();

		} catch (javax.persistence.NoResultException nre) {

			logger.warn("NoResultException: Work OrderID " + woId + " is not found.");
			throw new NoResultException("Work Order ID " + woId + " is not found.");
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<WorkFlowTask> getWorkFlowTaskById(Integer wfId,String woId)
			throws ValidationException, NoResultException, ProcessException {

		if (wfId == null || wfId == 0) {
			logger.warn("ValidationException: A valid Task ID is required.");
			throw new ValidationException("A valid Task ID is required.");
		}

		List<WorkFlowTask> result = null;

		Query query = em.createNamedQuery("WorkFlowTask.findWorkflowTaskById");
		query.setParameter("wfId", wfId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();

		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: Workflow Task ID " + wfId + " is not found.");
			throw new NoResultException("Workflow Task ID " + wfId + " is not found.");
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<WorkEventStatus> getWorkEventStatus() {

		Query query = em.createNamedQuery("WorkEventStatus.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<StakingSheet> getStakingSheets() {

		Query query = em.createNamedQuery("StakingSheet.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	public StakingSheet getStakingShetByWoId(String woId)
			throws ValidationException, NoResultException, ProcessException {

		if (StringUtils.isEmpty(woId)) {
			logger.warn("ValidationException: A valid Work ID is required.");
			throw new ValidationException("A valid Work Order ID is required.");
		}

		StakingSheet result = null;

		Query query = em.createNamedQuery("StakingSheet.findByWoId");
		query.setParameter("woId", woId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = (StakingSheet) query.getSingleResult();
		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: No Stakingsheet detail found for  Work Order ID: " + woId);
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<StakingSheetDetail> getStakingSheetDetail() {

		Query query = em.createNamedQuery("StakingSheetDetail.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<StakingSheetDetail> getStakingSheetDetailById(String woId)
			throws ValidationException, NoResultException, ProcessException {

		if (StringUtils.isEmpty(woId)) {
			logger.warn("ValidationException: A valid Work ID is required.");
			throw new ValidationException("A valid Work Order ID is required.");
		}

		List<StakingSheetDetail> result = null;

		Query query = em.createNamedQuery("StakingSheetDetail.findByWoId");
		query.setParameter("woId", getStatkingSheetId(woId));
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();

		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: Work Order ID " + woId + " is not found.");
			throw new NoResultException("Work Order ID " + woId + " is not found.");
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	public String getStatkingSheetId(String woId) {

		String result;
		Query query = em.createNamedQuery("StakingSheet.findStakingIdByWoId");
		query.setParameter("woId", woId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		result = (String) query.getSingleResult();

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<WorkFlowSearch_VW> getWorkflowBySoId(String soId,  String workgroup)
			throws ValidationException, NoResultException, ProcessException {
		if (StringUtils.isEmpty(soId)) {
			logger.warn("ValidationException: A valid Service ID is required.");
			throw new ValidationException("A valid Service Order ID is required.");
		}

		List<WorkFlowSearch_VW> result = null;
		Query query = null;
		if(workgroup.equals(LCEC)) {
			query = em.createNamedQuery("WorkFlowSearch_VW.findLCECWorkflowBySoId");
			query.setParameter("soId", soId);
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		}else {

		query = em.createNamedQuery("WorkFlowSearch_VW.findWorkflowBySoId");
		query.setParameter("soId", soId);
		query.setParameter("workgrp", workgroup);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		}
		try {
			result = query.getResultList();

		} catch (javax.persistence.NoResultException nre) {

			logger.warn("NoResultException: Service OrderID " + soId + " is not found.");
			throw new NoResultException("Service Order ID " + soId + " is not found.");
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<WorkFlowSearch_VW> getWorkflowBystatusId( String statusId, String workgroup)
			throws ValidationException, NoResultException, ProcessException {
		if (StringUtils.isEmpty(statusId)) {
			logger.warn("ValidationException: Status is required.");
			throw new ValidationException("Status is required.");
		}

		List<WorkFlowSearch_VW> result = null;
		Query query = null;
		
		if(workgroup.equals(LCEC)) {
			query = em.createNamedQuery("WorkFlowSearch_VW.findLCECWorkflowBystatusId");
			query.setParameter("statusId", statusId);
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		}else {

		query = em.createNamedQuery("WorkFlowSearch_VW.findWorkflowBystatusId");
		query.setParameter("statusId", statusId);
		query.setParameter("workgrp", workgroup);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		}
		try {
			result = query.getResultList();

		} catch (javax.persistence.NoResultException nre) {

			logger.warn("NoResultException: Status " + statusId + " is not found.");
			throw new NoResultException("Status " + statusId + " is not found.");
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<AsBuiltStatus> getAsBuiltStatus() {
		Query query = em.createNamedQuery("AsBuiltStatus.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	public void updateAsBuiltStakingSheetDetail(StakingSheetDetail asBuiltStake)
			throws ValidationException, ProcessException {
		if (asBuiltStake == null) {
			logger.warn("ValidationException: StakingSheetDetail cannot be blank.");
			throw new ValidationException("ValidationException: StakingSheetDetail cannot be blank.");
		}
		try {
			em.merge(asBuiltStake);
		} catch (EJBException e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}
	}

	@SuppressWarnings("unused")
	public void updateInspectionStakingSheetDetail(StakingSheetDetail inspectionStake, Integer inspectionId)
			throws ValidationException, ProcessException {
		if (inspectionStake == null) {
			logger.warn("ValidationException: StakingSheetDetail cannot be blank.");
			throw new ValidationException("ValidationException: StakingSheetDetail cannot be blank.");
		}

		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("MergeInspectionDetail");
		try {
		query.setParameter("InStakingSheetDetailId", inspectionStake.getStakingSheetDetailId());
		query.setParameter("InAssemblyGuid", inspectionStake.getAssemblyGuid());
		query.setParameter("InInspectionId", inspectionId);
		query.setParameter("InInspectionDetailId", inspectionStake.getCurrentInspectionDetailId());
		query.setParameter("InInspectionDetailStatusId", inspectionStake.getCurrentInspectionDetailStatusId());
		query.setParameter("InInspectionDetailDt", inspectionStake.getCurrentInspectionDetailDt());
		query.setParameter("InInspectionDetailBy", inspectionStake.getCurrentInspectedDetailBy());
		query.setParameter("InInspectionDetailComment", inspectionStake.getCurrentInspectorDetailComments());
		query.execute();
		}catch(EJBException e) {
			logger.error(e);
			throw new ProcessException(e.getMessage());
		}

		String outResult = (String) query.getOutputParameterValue("OutResult");

	}

	@SuppressWarnings("unchecked")
	public List<RateGroupPivot> getRateGroupPivot() {

		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("GET_RATEGROUP_PIVOT");

		List<RateGroupPivot> rateGroupPivot = query.getResultList();

		return rateGroupPivot;
	}

	public List<AssemblyAdhocVw> getDistinctRates(Integer rateGroupId, String energized, String transfer) throws ProcessException {

		cb = em.getCriteriaBuilder();
		CriteriaQuery<AssemblyAdhocVw> cq = cb.createQuery(AssemblyAdhocVw.class);

		Root<AssemblyAdhocVw> assembly = cq.from(AssemblyAdhocVw.class);
		// EntityType<WorkFlowSearch_VW> workflow = workFlowSearch_VW.getModel();

		// Constructing list of parameters
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (rateGroupId != null && rateGroupId != 0) {
			predicates.add(cb.equal(assembly.get("rateGroupId"), rateGroupId));
		}
		if (StringUtils.isNotEmpty(energized)) {
			predicates.add(cb.equal(assembly.get("energized"), energized));
		}
		if (StringUtils.isNotEmpty(transfer)) {
			predicates.add(cb.equal(assembly.get("transfer"), transfer));
		}
		// cq.where(cb.equal(workFlowSearch_VW.get("workGroup"),workgroup));
		cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

		cq.select(assembly);
		TypedQuery<AssemblyAdhocVw> typedQuery = em.createQuery(cq);
		typedQuery.setHint("javax.persistence.cache.storeMode", "REFRESH");

		List<AssemblyAdhocVw> result = null;
		try {
			result = typedQuery.getResultList();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<StakingSheetDetail> getStakingSheetAvailForInsp(Integer asBuiltStatus, String woId)
			throws ValidationException, NoResultException, ProcessException {

		if (asBuiltStatus == null || asBuiltStatus == 0) {
			logger.warn("ValidationException: As-built status is required.");
			throw new ValidationException("As-built status is required.");
		}

		List<StakingSheetDetail> result = null;
		
		Query query = em.createNamedQuery("StakingSheetDetail.findByAvailForInsp");
		query.setParameter("asBuiltStatus", asBuiltStatus);
		query.setParameter("stakingSheetId", getStatkingSheetId(woId));
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		
		try {
			result = query.getResultList();
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Invoice> getInvoiceByWoId(String woId)
			throws ValidationException, NoResultException, ProcessException {

		if (StringUtils.isEmpty(woId)) {
			logger.warn("ValidationException: Work Order  is required.");
			throw new ValidationException("Work Order is required.");
		}
		List<Invoice> result = null;

		Query query = em.createNamedQuery("Invoice.findByWoId");
		query.setParameter("woId", woId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();

		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: " + nre.getMessage());
			throw new NoResultException(nre.getMessage());
		} catch (EJBException ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<StakingSheetDetail> getStakingSheetAvailForInv(String inspectionStatus,  String woId)
			throws ValidationException, NoResultException, ProcessException {
		if ( StringUtils.isEmpty(inspectionStatus)) {
			logger.warn("ValidationException: As-Built and Inspection Status are required.");
			throw new ValidationException("As-Built and Inspection Status are required.");
		}
		List<StakingSheetDetail> result = null;

		Query query = em.createNamedQuery("StakingSheetDetail.findByAvailForInvoice");
		query.setParameter("inspStat", inspectionStatus);
		query.setParameter("sheetId", getStatkingSheetId(woId));
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();

		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: " + nre.getMessage());
			throw new NoResultException(nre.getMessage());
		} catch (EJBException ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	public Invoice insertInvoice(Invoice invoice) throws ValidationException, NoResultException, ProcessException {
		if (invoice == null) {
			throw new ValidationException("Invoice does not contain any record.");
		}
		try {
			em.persist(invoice);
		} catch (EJBException ex) {
			logger.error(ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return invoice;
	}

	public InvoiceDetail insertInvoiceDetail(InvoiceDetail invoiceDetail)
			throws ValidationException, NoResultException, ProcessException {

		if (invoiceDetail == null) {
			throw new ValidationException("InvoiceDetail does not contain any record.");
		}
		try {
			em.persist(invoiceDetail);
		} catch (EJBException ex) {
			logger.error(ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return invoiceDetail;
	}

	public StakingSheetDetail updateStakingSheetDetail(StakingSheetDetail stakingSheetDetail)
			throws ValidationException, ProcessException {

		if (stakingSheetDetail == null) {
			logger.warn("ValidationException: StakingSheetDetail cannot be blank.");
			throw new ValidationException("ValidationException: StakingSheetDetail cannot be blank.");
		}
		try {
			em.merge(stakingSheetDetail);
			em.flush();
		} catch (EJBException e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}
		return stakingSheetDetail;
	}

	@SuppressWarnings("unchecked")
	public List<AsBuiltSummaryVw> getStakingSheetByGrpSummary(String woId)
			throws ValidationException, NoResultException, ProcessException {

		if (StringUtils.isEmpty(woId)) {
			logger.warn("ValidationException: WorkOrder ID is required.");
			throw new ValidationException("WorkOrder ID is required.");
		}
		List<AsBuiltSummaryVw> result = null;

		Query query = em.createNamedQuery("AsBuiltSummaryVw.findByStakingSheetId");
		query.setParameter("sheetId", Integer.valueOf(getStatkingSheetId(woId)));
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();

		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: " + nre.getMessage());
			throw new NoResultException(nre.getMessage());
		} catch (EJBException ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return result;
	}
	
	public String getDistinctRateDesc(String unit) throws ValidationException, ProcessException {
	
		if (StringUtils.isEmpty(unit)) {
			logger.warn("ValidationException: Assembly Unit is required.");
			throw new ValidationException("Assembly Unit is required.");
		}
		String result = null;
		Query query = em.createNamedQuery("RateGroupPrice.findDistinctRateDesc");
		query.setParameter("assemblyUnit", unit);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		try {
			result = (String) query.getSingleResult();
		}catch(EJBException e) {
			logger.error(e);
			throw new ProcessException(e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<StakingSheetDetail> getStakingSheetByInvId(int invId)
			throws ValidationException, NoResultException, ProcessException {
		if (invId == 0) {
			logger.warn("ValidationException: A valid Invoice ID is required.");
			throw new ValidationException("A valid Invoice ID is required.");
		}

		List<StakingSheetDetail> result = null;

		Query query = em.createNamedQuery("StakingSheetDetail.findByInvoiceId");
		query.setParameter("invId", invId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();

		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: Invoice ID" + invId + " is not found.");
			throw new NoResultException("Invoice ID " + invId + " is not found.");
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	public Integer getRateGroupId(String groupName) throws ValidationException, NoResultException, ProcessException {
		if (StringUtils.isEmpty(groupName)) {
			logger.warn("Rate Group Name cannot be empty.");
			throw new ValidationException("Rate Group Name cannot be empty.");
		}
		int result = 0;
		Query query = em.createNamedQuery("RateGroup.findByRateGrpName");
		query.setParameter("rateName", groupName);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		try {
			result = (int) query.getSingleResult();
		} catch (javax.persistence.NoResultException nre) {
			logger.error(nre.getMessage());
			throw new NoResultException("No result found.");
		} catch (Exception ex) {
			logger.error(ex);
			throw new ProcessException(ex.getMessage());
		}
		return result;
	}
	
	public StakingSheetDetail insertSheetDetail(StakingSheetDetail stakingSheetDetail) throws ProcessException {

		try {
			em.persist(stakingSheetDetail);
			em.flush();
		} catch (EJBException e) {
			logger.error(e);
			throw new ProcessException(e.getMessage());
		}
		return stakingSheetDetail;
	}

	public List<WorkFlowSearch_VW> searchWorkflows(String woId, String soId, String woStatus, Integer inspectionStatus,
			Integer invoiceStatus, String workgroup, String vendorName) throws ProcessException {
		
		cb = em.getCriteriaBuilder();
		CriteriaQuery<WorkFlowSearch_VW> cq = cb.createQuery(WorkFlowSearch_VW.class);

		Root<WorkFlowSearch_VW> workFlowSearch_VW = cq.from(WorkFlowSearch_VW.class);
		// EntityType<WorkFlowSearch_VW> workflow = workFlowSearch_VW.getModel();

		// Constructing list of parameters
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (StringUtils.isNotEmpty(woId)) {
			predicates.add(cb.equal(workFlowSearch_VW.get("workOrderId"), woId));
		}
		if (StringUtils.isNotEmpty(soId)) {
			predicates.add(cb.equal(workFlowSearch_VW.get("serviceOrderId"), soId));
		}
		if (StringUtils.isNotEmpty(woStatus)) {
			predicates.add(cb.equal(workFlowSearch_VW.get("workEventStatusId"), woStatus));
		}
		if (inspectionStatus != null && inspectionStatus != 0) {
			predicates.add(cb.equal(workFlowSearch_VW.get("overallInspectionStatusId"), inspectionStatus));
		}
		if (invoiceStatus != null && invoiceStatus != 0) {
			predicates.add(cb.equal(workFlowSearch_VW.get("overallInvoiceStatusId"), invoiceStatus));
		}
		if (StringUtils.isNotEmpty(workgroup) && (!workgroup.equals(LCEC))) {
			predicates.add(cb.equal(workFlowSearch_VW.get("workGroup"), workgroup));
		}
		if (StringUtils.isNotEmpty(vendorName)) {
			predicates.add(cb.equal(workFlowSearch_VW.get("workGroup"), vendorName));
		}
		// cq.where(cb.equal(workFlowSearch_VW.get("workGroup"),workgroup));
		cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

		cq.select(workFlowSearch_VW);
		TypedQuery<WorkFlowSearch_VW> typedQuery = em.createQuery(cq);
		typedQuery.setHint("javax.persistence.cache.storeMode", "REFRESH");

		List<WorkFlowSearch_VW> result = null;
		try {
			result = typedQuery.getResultList();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		
		return result;
	}

	public WorkFlow getWorkflow(Integer wfId) throws ProcessException {
		WorkFlow result = null;
		
		Query query = em.createNamedQuery("WorkFlow.findWorkflowByWfId");
		query.setParameter("wfId", wfId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = (WorkFlow) query.getSingleResult();
		} catch (EJBException nre) {
			logger.error(nre);
			throw new ProcessException(nre.getMessage());
		} 
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<WorkOrderAggVw> getByWorkflowId(int wfId) throws NoResultException, ValidationException, ProcessException {

		List<WorkOrderAggVw> result = null;

		Query query = em.createNamedQuery("WorkOrderAggVw.findAll");
		query.setParameter("workflowId", wfId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();

		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		
		return result;
	}

	public int updateOverallAsbuiltStatusId(String woId) throws ProcessException {
		
		StoredProcedureQuery query;
		try {
		query = em.createNamedStoredProcedureQuery("UPDATE_OVERALL_ASBUILT_STATUS");
		query.setParameter("IN_WorkOrderId", woId);
		query.execute();
		}catch(EJBException e) {
			logger.error(e);
			throw new ProcessException(e.getMessage());
		}
		int output = (int) query.getOutputParameterValue("OUT_Response");
		
		return output;
	}

	@SuppressWarnings("unchecked")
	public List<Voucher> getVouchers(String woId) throws ProcessException {
		
		List<Voucher> result = null;
		
		Query query = em.createNamedQuery("Voucher.findAll");
		query.setParameter("woId", woId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (EJBException nre) {
			logger.error(nre);
			throw new ProcessException(nre.getMessage());
		} 
		return result;
	}

	public Voucher insertVoucher(Voucher voucher) throws ProcessException, ValidationException {
		if (voucher == null) {
			throw new ValidationException("Voucher does not contain any record.");
		}
		try {
			em.persist(voucher);
			em.flush();
		} catch (EJBException ex) {
			logger.error(ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return voucher;
	}

	public Voucher updateVoucher(Voucher voucher) throws ProcessException, ValidationException {
		if (voucher == null) {
			throw new ValidationException("Voucher does not contain any record.");
		}
		try {
			em.merge(voucher);
			em.flush();
		} catch (EJBException ex) {
			logger.error(ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return voucher;
	}

	@SuppressWarnings("unchecked")
	public List<Voucher> getVoucherByInspectionId(Integer inspectionId) throws ProcessException {
		List<Voucher> result = null;

		Query query = em.createNamedQuery("Voucher.findByInspectionId");
		query.setParameter("inspectionId", inspectionId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (EJBException nre) {
			logger.error(nre);
			throw new ProcessException(nre.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Voucher> getVoucherByInvoiceId(Integer invoiceId) throws ProcessException {
		List<Voucher> result = null;

		Query query = em.createNamedQuery("Voucher.findByInvoiceId");
		query.setParameter("invoiceId", invoiceId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (EJBException nre) {
			logger.error(nre);
			throw new ProcessException(nre.getMessage());
		}
		return result;
	}
	
	public Integer getMaxResourceId() {
		
		Integer result;
		
		Query query = em.createNamedQuery("Resource.findMaxResourceId");
		
		result = (Integer) query.getSingleResult();
		
		return result;
	}
	
	public Resource insertResource(Resource resource) throws ValidationException, ProcessException {
		if (resource == null) {
			logger.warn("Resource does not contain any record.");
			throw new ValidationException("Resource does not contain any record.");
		}
		try {
			em.persist(resource);
			em.flush();
		} catch (EJBException ex) {
			logger.error(ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return resource;
	}

	public WorkGroup insertWorkGroup(WorkGroup workGroup) throws ValidationException, ProcessException {
		if (workGroup == null) {
			logger.warn("WorkGroup does not contain any record.");
			throw new ValidationException("WorkGroup does not contain any record.");
		}
		try {
			em.persist(workGroup);
			em.flush();
		} catch (EJBException ex) {
			logger.error(ex.getMessage());
			throw new ProcessException(ex.getMessage());
		}
		return workGroup;
	}

	public void updateWorkflowTask(Integer wfTaskId, String woId) throws ProcessException {

		try {
			Query query = em.createNativeQuery("UPDATE WorkFlowTask SET WorkOrderId = ? WHERE WorkFlowTaskId = ?");
			query.setParameter(1, woId);
			query.setParameter(2, wfTaskId);
			query.executeUpdate();
		} catch (EJBException e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<Vendor> getVendors() {
		Query query = em.createNamedQuery("Vendor.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	public BigDecimal getAssemblyAmount(String workgroup, String workType, String assemblyUnit, Timestamp workEventDt) throws ProcessException {

		StoredProcedureQuery query;
		
		try {
			query = em.createNamedStoredProcedureQuery("GET_ASSEMBLY_AMOUNT");
			query.setParameter("IN_RATEGROUP", workgroup);
			query.setParameter("IN_WORKTYPE", workType);
			query.setParameter("IN_AssemblyUnit", assemblyUnit);
			query.setParameter("IN_WorkEventDt", workEventDt);
			query.execute();
		} catch (EJBException e) {
			logger.error(e);
			throw new ProcessException(e.getMessage());
		}
		BigDecimal output = (BigDecimal) query.getOutputParameterValue("OUT_AMOUNT");

		return output;
	}
	
	public Resource updateResource(Integer resourceId, Integer vpUserId) throws Exception {

		Resource resource = null;

		try {
			resource = em.find(Resource.class, resourceId);
			resource.setVpUserID(vpUserId);

			em.merge(resource);
			
		} catch (EJBException e) {
			logger.error(e);
			throw new Exception(e.getMessage());
		}
		return resource;
	}

	public Integer updateAsBuiltAmount(String stakingSheetDetailId) throws Exception {

		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("UPDATE_STAKING_ASBUILTAMOUNT");
		try {
			query.setParameter("IN_StakingSheetDetailID", stakingSheetDetailId);
			query.execute();
		} catch (Exception ex) {
			logger.error(ex);
		}

		return (Integer) query.getOutputParameterValue("OUT_Response");
	}
}
