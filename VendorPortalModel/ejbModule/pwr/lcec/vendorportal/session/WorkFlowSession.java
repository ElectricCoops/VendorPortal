package pwr.lcec.vendorportal.session;

import java.math.BigDecimal;
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

import pwr.lcec.vendorportal.entity.custom.AsBuiltStatus;
import pwr.lcec.vendorportal.entity.custom.AsBuiltSummaryVw;
import pwr.lcec.vendorportal.entity.custom.AssemblyAdhocVw;
import pwr.lcec.vendorportal.entity.custom.InspectionUnlock;
import pwr.lcec.vendorportal.entity.custom.Invoice;
import pwr.lcec.vendorportal.entity.custom.InvoiceDetail;
import pwr.lcec.vendorportal.entity.custom.Resource;
import pwr.lcec.vendorportal.entity.custom.ServiceOrder;
import pwr.lcec.vendorportal.entity.custom.StakingSheet;
import pwr.lcec.vendorportal.entity.custom.StakingSheetDetail;
import pwr.lcec.vendorportal.entity.custom.StakingSheetDetailGui;
import pwr.lcec.vendorportal.entity.custom.Vendor;
import pwr.lcec.vendorportal.entity.custom.Voucher;
import pwr.lcec.vendorportal.entity.custom.VoucherGui;
import pwr.lcec.vendorportal.entity.custom.WorkEventStatus;
import pwr.lcec.vendorportal.entity.custom.WorkFlow;
import pwr.lcec.vendorportal.entity.custom.WorkFlowSearch_VW;
import pwr.lcec.vendorportal.entity.custom.WorkFlowTask;
import pwr.lcec.vendorportal.entity.custom.WorkGroup;
import pwr.lcec.vendorportal.entity.custom.WorkOrderAggVw;
import pwr.lcec.vendorportal.interfaces.WorkFlowLocal;

@Stateless(name = "VPWorkFlowSession", mappedName = "ejb/lcec/VPWorkFlowSession", description = "VP WorkFlow Business Proxy")
@Local({ WorkFlowLocal.class })
@Interceptors({ pwr.lcec.vendorportal.helper.LoggingInterceptor.class })
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class WorkFlowSession implements WorkFlowLocal {
	
	private static Logger logger = LogManager.getLogger(WorkFlowSession.class);

	@PersistenceContext(unitName = "vendorPortalPU")
	private EntityManager em;

	private CriteriaBuilder cb;

	private static final String LCEC = "LCEC";

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkFlowSearch_VW> getWorkflowById(String woId, String workgroup) {

		if (StringUtils.isEmpty(woId)) {
			logger.warn("ValidationException: A valid Work ID is required.");
			//throw new ValidationException("A valid Work Order ID is required.");
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
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkFlowTask> getWorkFlowTaskById(Integer wfId, String woId) {
		
		if (wfId == null || wfId == 0) {
			logger.warn("ValidationException: A valid Task ID is required.");
			//throw new ValidationException("A valid Task ID is required.");
		}

		List<WorkFlowTask> result = null;

		Query query = em.createNamedQuery("WorkFlowTask.findWorkflowTaskById");
		query.setParameter("wfId", wfId);
		//query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();

		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: Workflow Task ID " + wfId + " is not found.");
			throw new NoResultException("Workflow Task ID " + wfId + " is not found.");
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkEventStatus> getWorkEventStatus() {

		Query query = em.createNamedQuery("WorkEventStatus.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StakingSheet> getStakingSheets() {

		Query query = em.createNamedQuery("StakingSheet.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	@Override
	public StakingSheet getStakingShetByWoId(String woId) {

		if (StringUtils.isEmpty(woId)) {
			logger.warn("ValidationException: A valid Work ID is required.");
			//throw new ValidationException("A valid Work Order ID is required.");
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
			//throw new ProcessException(ex.getMessage());
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StakingSheetDetail> getStakingSheetDetail() {
		Query query = em.createNamedQuery("StakingSheetDetail.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StakingSheetDetail> getStakingSheetByInvId(int invId) {

		if (invId == 0) {
			logger.warn("ValidationException: A valid Invoice ID is required.");
			//throw new ValidationException("A valid Invoice ID is required.");
		}

		List<StakingSheetDetail> result = null;

		Query query = em.createNamedQuery("StakingSheetDetail.findByInvoiceId");
		query.setParameter("invId", invId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();

		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: Invoice ID" + invId + " is not found.");
			//throw new NoResultException("Invoice ID " + invId + " is not found.");
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StakingSheetDetail> getStakingSheetDetailById(String woId) {

		if (StringUtils.isEmpty(woId)) {
			logger.warn("ValidationException: A valid Work ID is required.");
			//throw new ValidationException("A valid Work Order ID is required.");
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
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StakingSheetDetailGui> getStakingSheetDetailGuiByWOId(String woId) {

		if (StringUtils.isEmpty(woId)) {
			logger.warn("ValidationException: A valid Work ID is required.");
			//throw new ValidationException("A valid Work Order ID is required.");
		}

		List<StakingSheetDetailGui> result = null;

		Query query = em.createNamedQuery("StakingSheetDetailGui.findSSDGuiByWOID");
		query.setParameter("workOrderId", woId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();

		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: Work Order ID " + woId + " is not found.");
			throw new NoResultException("Work Order ID " + woId + " is not found.");
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StakingSheetDetail> getStakingSheetAvailForInsp(Integer asBuiltStatus, String woId) {

		if (asBuiltStatus == null || asBuiltStatus == 0) {
			logger.warn("ValidationException: As-built status is required.");
			//throw new ValidationException("As-built status is required.");
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
			//throw new ProcessException(ex.getMessage());
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StakingSheetDetail> getStakingSheetAvailForInv(String inspectionStatus, String woId) {

		if ( StringUtils.isEmpty(inspectionStatus)) {
			logger.warn("ValidationException: As-Built and Inspection Status are required.");
			//throw new ValidationException("As-Built and Inspection Status are required.");
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
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@Override
	public void updateAsBuiltStakingSheetDetail(StakingSheetDetail asBuiltStake) {

		if (asBuiltStake == null) {
			logger.warn("ValidationException: StakingSheetDetail cannot be blank.");
			//throw new ValidationException("ValidationException: StakingSheetDetail cannot be blank.");
		}
		try {
			em.merge(asBuiltStake);
		} catch (EJBException e) {
			logger.error(e.getMessage());
			//throw new ProcessException(e.getMessage());
		}
		
	}

	@Override
	public StakingSheetDetail updateStakingSheetDetail(StakingSheetDetail stakingSheetDetail) {

		if (stakingSheetDetail == null) {
			logger.warn("ValidationException: StakingSheetDetail cannot be blank.");
			//throw new ValidationException("ValidationException: StakingSheetDetail cannot be blank.");
		}
		try {
			em.merge(stakingSheetDetail);
			em.flush();
		} catch (EJBException e) {
			logger.error(e.getMessage());
			//throw new ProcessException(e.getMessage());
		}
		return stakingSheetDetail;
	}

	@Override
	public void updateInspectionStakingSheetDetail(StakingSheetDetail inspectionStake, Integer inspectionId) {

		if (inspectionStake == null) {
			logger.warn("ValidationException: StakingSheetDetail cannot be blank.");
			//throw new ValidationException("ValidationException: StakingSheetDetail cannot be blank.");
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
			//throw new ProcessException(e.getMessage());
		}

		String outResult = (String) query.getOutputParameterValue("OutResult");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AsBuiltSummaryVw> getStakingSheetByGrpSummary(String woId) {

		if (StringUtils.isEmpty(woId)) {
			logger.warn("ValidationException: WorkOrder ID is required.");
			//throw new ValidationException("WorkOrder ID is required.");
		}
		List<AsBuiltSummaryVw> result = null;

		Query query = em.createNamedQuery("AsBuiltSummaryVw.findByStakingSheetId");
		query.setParameter("sheetId", Integer.valueOf(getStatkingSheetId(woId)));
		//query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();

		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: " + nre.getMessage());
			//throw new NoResultException(nre.getMessage());
		} catch (EJBException ex) {
			logger.error("ProcessException: " + ex.getMessage());
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AsBuiltStatus> getAsBuiltStatus() {

		Query query = em.createNamedQuery("AsBuiltStatus.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssemblyAdhocVw> getDistinctRates(Integer rateGroupId, String energized, String transfer,
			Timestamp workEventDt) {

		Query query = em.createNamedQuery("AssemblyAdhocVw.findByRates");
		query.setParameter("rateGrpId", rateGroupId);
		query.setParameter("energized", energized);
		query.setParameter("transfer", transfer);
		query.setParameter("workEventDt", workEventDt);

		List<AssemblyAdhocVw> result = null;
		try {
			result = query.getResultList();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			//throw new ProcessException(ex.getMessage());
		}

		return result;
	}

	@Override
	public String getDistinctRateDesc(String unit) {

		if (StringUtils.isEmpty(unit)) {
			logger.warn("ValidationException: Assembly Unit is required.");
			//throw new ValidationException("Assembly Unit is required.");
		}
		String result = null;
		Query query = em.createNamedQuery("RateGroupPrice.findDistinctRateDesc");
		query.setParameter("assemblyUnit", unit);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		try {
			result = (String) query.getSingleResult();
		}catch(EJBException e) {
			logger.error(e);
			//throw new ProcessException(e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Invoice> getInvoiceByWoId(String woId) {

		if (StringUtils.isEmpty(woId)) {
			logger.warn("ValidationException: Work Order  is required.");
			//throw new ValidationException("Work Order is required.");
		}
		List<Invoice> result = null;

		Query query = em.createNamedQuery("Invoice.findByWoId");
		query.setParameter("woId", woId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();

		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: " + nre.getMessage());
			//throw new NoResultException(nre.getMessage());
		} catch (EJBException ex) {
			logger.error("ProcessException: " + ex.getMessage());
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@Override
	public Invoice insertInvoice(Invoice invoice) {

		if (invoice == null) {
			//throw new ValidationException("Invoice does not contain any record.");
		}
		try {
			em.persist(invoice);
		} catch (EJBException ex) {
			logger.error(ex.getMessage());
			//throw new ProcessException(ex.getMessage());
		}
		return invoice;
	}

	@Override
	public InvoiceDetail insertInvoiceDetail(InvoiceDetail invoiceDetail) {

		if (invoiceDetail == null) {
			//throw new ValidationException("InvoiceDetail does not contain any record.");
		}
		try {
			em.persist(invoiceDetail);
		} catch (EJBException ex) {
			logger.error(ex.getMessage());
			//throw new ProcessException(ex.getMessage());
		}
		return invoiceDetail;
	}

	@Override
	public String getStatkingSheetId(String woId) {

		String result;
		Query query = em.createNamedQuery("StakingSheet.findStakingIdByWoId");
		query.setParameter("woId", woId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		result = (String) query.getSingleResult();

		return result;
	}

	@Override
	public Integer getRateGroupId(String groupName) {

		if (StringUtils.isEmpty(groupName)) {
			logger.warn("Rate Group Name cannot be empty.");
			//throw new ValidationException("Rate Group Name cannot be empty.");
		}
		int result = 0;
		Query query = em.createNamedQuery("RateGroup.findByRateGrpName");
		query.setParameter("rateName", groupName);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		try {
			result = (int) query.getSingleResult();
		} catch (javax.persistence.NoResultException nre) {
			logger.error(nre.getMessage());
			//throw new NoResultException("No result found.");
		} catch (Exception ex) {
			logger.error(ex);
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@Override
	public StakingSheetDetail insertSheetDetail(StakingSheetDetail stakingSheetDetail) {

		try {
			em.persist(stakingSheetDetail);
			em.flush();
		} catch (EJBException e) {
			logger.error(e);
			//throw new ProcessException(e.getMessage());
		}
		return stakingSheetDetail;
	}

	@Override
	public List<WorkFlowSearch_VW> searchWorkflows(String woId, String soId, String woStatus, Integer inspectionStatus,
			Integer invoiceStatus, String workgroup, String vendorName) {

		cb = em.getCriteriaBuilder();
		CriteriaQuery<WorkFlowSearch_VW> cq = cb.createQuery(WorkFlowSearch_VW.class);

		Root<WorkFlowSearch_VW> workFlowSearch_VW = cq.from(WorkFlowSearch_VW.class);
		// EntityType<WorkFlowSearch_VW> workflow = workFlowSearch_VW.getModel();

		// Constructing list of parameters
		List<Predicate> predicates = new ArrayList<Predicate>();
	    
	    if (StringUtils.isNotEmpty(woId)) {
	      predicates.add(this.cb.equal(workFlowSearch_VW.get("workOrderId"), woId));
	    }
	    if (StringUtils.isNotEmpty(soId)) {
	      predicates.add(this.cb.equal(workFlowSearch_VW.get("serviceOrderId"), soId));
	    }
	    if (StringUtils.isNotEmpty(woStatus) && !woStatus.equals("All")) {
	      predicates.add(this.cb.equal(workFlowSearch_VW.get("workEventStatusId"), woStatus));
	    }
	    if (inspectionStatus != null && inspectionStatus.intValue() != 0) {
	      predicates.add(this.cb.equal(workFlowSearch_VW.get("overallInspectionStatusId"), inspectionStatus));
	    }
	    if (invoiceStatus != null && invoiceStatus.intValue() != 0) {
	      predicates.add(this.cb.equal(workFlowSearch_VW.get("overallInvoiceStatusId"), invoiceStatus));
	    }
	    if (StringUtils.isNotEmpty(workgroup) && !workgroup.equals("LCEC")) {
	      predicates.add(this.cb.equal(workFlowSearch_VW.get("workGroup"), workgroup));
	    }
	    if (StringUtils.isNotEmpty(vendorName) && !vendorName.equals("All")) {
	      predicates.add(this.cb.equal(workFlowSearch_VW.get("workGroup"), vendorName));
	    }
	    
	    cq.where(this.cb.and((Predicate[])predicates.toArray(new Predicate[predicates.size()])));
	    
	    cq.select(workFlowSearch_VW);
	    TypedQuery<WorkFlowSearch_VW> typedQuery = this.em.createQuery(cq);
	    typedQuery.setHint("javax.persistence.cache.storeMode", "REFRESH");
	    
	    List<WorkFlowSearch_VW> result = null;
	    try {
	      result = typedQuery.getResultList();
	    } catch (Exception ex) {
	      logger.error(ex.getMessage());
	    } 
	    
	    return result;
	}

	@Override
	public WorkFlow getWorkflow(Integer wfId) {

		WorkFlow result = null;
		
		Query query = em.createNamedQuery("WorkFlow.findWorkflowByWfId");
		query.setParameter("wfId", wfId);
		//query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = (WorkFlow) query.getSingleResult();
		} catch (EJBException nre) {
			logger.error(nre);
			//throw new ProcessException(nre.getMessage());
		} 
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkOrderAggVw> getByWorkflowId(int wfId) {

		List<WorkOrderAggVw> result = null;

		Query query = em.createNamedQuery("WorkOrderAggVw.findAll");
		query.setParameter("workflowId", wfId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();

		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			//throw new ProcessException(ex.getMessage());
		}
		
		return result;
	}

	@Override
	public int updateOverallAsbuiltStatusId(String woId) {

		StoredProcedureQuery query = null;
		try {
		query = em.createNamedStoredProcedureQuery("UPDATE_OVERALL_ASBUILT_STATUS");
		query.setParameter("IN_WorkOrderId", woId);
		query.execute();
		}catch(EJBException e) {
			logger.error(e);
			//throw new ProcessException(e.getMessage());
		}
		int output = (int) query.getOutputParameterValue("OUT_Response");
		
		return output;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VoucherGui> getVouchers(String woId) {

		List<VoucherGui> result = null;
		
		Query query = em.createNamedQuery("VoucherGui.findByWorkOrderId");
		query.setParameter("woId", woId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (EJBException nre) {
			logger.error(nre);
			//throw new ProcessException(nre.getMessage());
		} 
		return result;
	}

	@Override
	public Voucher insertVoucher(Voucher voucher) {
		
		if (voucher == null) {
			//throw new ValidationException("Voucher does not contain any record.");
		}
		try {
			em.persist(voucher);
			em.flush();
		} catch (EJBException ex) {
			logger.error(ex.getMessage());
			//throw new ProcessException(ex.getMessage());
		}
		return voucher;
	}

	@Override
	public Voucher updateVoucher(Voucher voucher) {

		if (voucher == null) {
			//throw new ValidationException("Voucher does not contain any record.");
			logger.warn("Voucher does not contain any record.");
		}
		try {
			em.merge(voucher);
			em.flush();
		} catch (EJBException ex) {
			logger.error(ex.getMessage());
			//throw new ProcessException(ex.getMessage());
		}
		return voucher;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VoucherGui> getVoucherByInspectionId(Integer inspectionId) {

		List<VoucherGui> result = null;

		Query query = em.createNamedQuery("VoucherGui.findByInspectionId");
		query.setParameter("inspectionId", inspectionId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (EJBException nre) {
			logger.error(nre);
			//throw new ProcessException(nre.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VoucherGui> getVoucherByInvoiceId(Integer invoiceId) {

		List<VoucherGui> result = null;

		Query query = em.createNamedQuery("VoucherGui.findByInvoiceId");
		query.setParameter("invoiceId", invoiceId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (EJBException nre) {
			logger.error(nre);
			//throw new ProcessException(nre.getMessage());
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Voucher getVoucherById(int voucherId) {
		
		List<Voucher> result = null;

		Query query = em.createNamedQuery("Voucher.findById");
		query.setParameter("voucherId", voucherId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (EJBException nre) {
			logger.error(nre);
			//throw new ProcessException(nre.getMessage());
		}
		
		if (result.size() == 1)
		      return (Voucher)result.get(0); 
		    if (result == null || result.isEmpty()) {
		      return null;
		    }
		    throw new NonUniqueResultException();
		    
	}

	@Override
	public Resource insertResource(Resource resource) {

		if (resource == null) {
			logger.warn("Resource does not contain any record.");
			//throw new ValidationException("Resource does not contain any record.");
		}
		try {
			em.persist(resource);
			em.flush();
		} catch (EJBException ex) {
			logger.error(ex.getMessage());
			//throw new ProcessException(ex.getMessage());
		}
		return resource;
	}

	@Override
	public WorkGroup insertWorkGroup(WorkGroup workGroup) {
		
		if (workGroup == null) {
			logger.warn("WorkGroup does not contain any record.");
			//throw new ValidationException("WorkGroup does not contain any record.");
		}
		try {
			em.persist(workGroup);
			em.flush();
		} catch (EJBException ex) {
			logger.error(ex.getMessage());
			//throw new ProcessException(ex.getMessage());
		}
		return workGroup;
	}

	@Override
	public Integer getMaxResourceId() {

		Integer result;
		
		Query query = em.createNamedQuery("Resource.findMaxResourceId");
		
		result = (Integer) query.getSingleResult();
		
		return result;
	}

	@Override
	public void updateWorkflowTask(Integer wfTaskId, String woId) {

		try {
			Query query = em.createNativeQuery("UPDATE WorkFlowTask SET WorkOrderId = ? WHERE WorkFlowTaskId = ?");
			query.setParameter(1, woId);
			query.setParameter(2, wfTaskId);
			query.executeUpdate();
		} catch (EJBException e) {
			logger.error(e.getMessage());
			//throw new ProcessException(e.getMessage());
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vendor> getVendors() {

		Query query = em.createNamedQuery("Vendor.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	@Override
	public BigDecimal getAssemblyAmount(String workgroup, String workType, String assemblyUnit, Timestamp workEventDt) {

		StoredProcedureQuery query = null;
		
		try {
			query = em.createNamedStoredProcedureQuery("GET_ASSEMBLY_AMOUNT");
			query.setParameter("IN_RATEGROUP", workgroup);
			query.setParameter("IN_WORKTYPE", workType);
			query.setParameter("IN_AssemblyUnit", assemblyUnit);
			query.setParameter("IN_WorkEventDt", workEventDt);
			query.execute();
		} catch (EJBException e) {
			logger.error(e);
			//throw new ProcessException(e.getMessage());
		}
		BigDecimal output = (BigDecimal) query.getOutputParameterValue("OUT_AMOUNT");

		return output;
	}

	@Override
	public Resource updateResource(Integer resourceId, Integer vpUserId) {

		Resource resource = null;

		try {
			resource = em.find(Resource.class, resourceId);
			resource.setVpUserID(vpUserId);

			em.merge(resource);
			
		} catch (EJBException e) {
			logger.error(e);
			//throw new Exception(e.getMessage());
		}
		return resource;
	}

	@Override
	public Integer updateAsBuiltAmount(String stakingSheetDetailId, Timestamp workEventDt) {

		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("UPDATE_STAKING_ASBUILTAMOUNT");
		try {
			query.setParameter("IN_StakingSheetDetailID", stakingSheetDetailId);
			query.setParameter("IN_WorkEventDt", workEventDt);
			query.execute();
		} catch (Exception ex) {
			logger.error(ex);
		}

		return (Integer) query.getOutputParameterValue("OUT_Response");
	}

	@SuppressWarnings("unchecked")
	@Override
	public StakingSheetDetail getStakingSheetDetailBySSDId(String stakingSheetDetailId) {

		if (StringUtils.isEmpty(stakingSheetDetailId)) {
		      logger.warn("ValidationException: A SSD ID is required.");
		      //throw new ValidationException("A valid Work Order ID is required.");
		    } 
		    
		    List<StakingSheetDetail> result = null;
		    
		    Query query = this.em.createNamedQuery("StakingSheetDetail.findBySSDId");
		    query.setParameter("stakingSheetDetailId", stakingSheetDetailId);
		    query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		    
		    try {
		      result = query.getResultList();
		    }
		    catch (NoResultException nre) {
		      logger.warn("NoResultException: SSD ID " + stakingSheetDetailId + " is not found.");
		      throw new NoResultException("SSD ID " + stakingSheetDetailId + " is not found.");
		    } catch (Exception ex) {
		      logger.error("ProcessException: " + ex.getMessage());
		      //throw new ProcessException(ex.getMessage());
		    } 
		    
		    if (result.size() == 1)
		      return (StakingSheetDetail)result.get(0); 
		    if (result == null || result.isEmpty()) {
		      return null;
		    }
		    throw new NonUniqueResultException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public StakingSheetDetailGui getStakingSheetDetailGuiBySSDId(String stakingSheetDetailId) {

		if (StringUtils.isEmpty(stakingSheetDetailId)) {
		      logger.warn("ValidationException: A SSD ID is required.");
		      //throw new ValidationException("A valid Work Order ID is required.");
		    } 
		    
		    List<StakingSheetDetailGui> result = null;
		    
		    Query query = this.em.createNamedQuery("StakingSheetDetailGui.findBySSDId");
		    query.setParameter("stakingSheetDetailId", stakingSheetDetailId);
		    query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		    
		    try {
		      result = query.getResultList();
		    }
		    catch (NoResultException nre) {
		      logger.warn("NoResultException: SSD ID " + stakingSheetDetailId + " is not found.");
		      throw new NoResultException("SSD ID " + stakingSheetDetailId + " is not found.");
		    } catch (Exception ex) {
		      logger.error("ProcessException: " + ex.getMessage());
		      //throw new ProcessException(ex.getMessage());
		    } 
		    
		    if (result.size() == 1)
		      return (StakingSheetDetailGui)result.get(0); 
		    if (result == null || result.isEmpty()) {
		      return null;
		    }
		    throw new NonUniqueResultException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public WorkFlow getWorkFlowByWorkOrderId(String woId) {
		
		List<WorkFlow> result = null;
	    
	    Query query = this.em.createNamedQuery("WorkFlow.findWorkflowById");
	    query.setParameter("woId", woId);
	    query.setHint("javax.persistence.cache.storeMode", "REFRESH");
	    
	    try {
	      result = query.getResultList();
	    } catch (Exception e) {
	      logger.error(e);
	    } 
	    
	    if (result.size() == 1)
	      return (WorkFlow)result.get(0); 
	    if (result == null || result.isEmpty()) {
	      return null;
	    }
	    throw new NonUniqueResultException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public WorkEventStatus getWorkEventStatusById(String workEventStatusId) {

		List<WorkEventStatus> result = null;
	    
	    Query query = this.em.createNamedQuery("WorkEventStatus.findStatusById");
	    query.setParameter("workEventStatusId", workEventStatusId);
	    query.setHint("javax.persistence.cache.storeMode", "REFRESH");
	    
	    try {
	      result = query.getResultList();
	    } catch (Exception e) {
	      logger.error(e);
	    } 
	    
	    if (result.size() == 1)
	      return (WorkEventStatus)result.get(0); 
	    if (result == null || result.isEmpty()) {
	      return null;
	    }
	    throw new NonUniqueResultException();
	}

	@Override
	public WorkFlow updateWorkflowTask(WorkFlow workflow) {

		return (WorkFlow) em.merge(workflow);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public WorkFlowSearch_VW getWorkFlowByStakingSheetId(int stakingSheetId) {

		List<WorkFlowSearch_VW> result = null;
	    
	    Query query = this.em.createNamedQuery("WorkFlowSearch_VW.findWorkflowByStakingSheetId");
	    query.setParameter("stakingSheetId", Integer.valueOf(stakingSheetId));
	    query.setHint("javax.persistence.cache.storeMode", "REFRESH");
	    
	    try {
	      result = query.getResultList();
	    } catch (Exception e) {
	      logger.error(e);
	    } 
	    
	    if (result.size() == 1)
	      return (WorkFlowSearch_VW)result.get(0); 
	    if (result == null || result.isEmpty()) {
	      return null;
	    }
	    throw new NonUniqueResultException();
	}

	@Override
	public void updateStakingSheetDetailStatus(String stakingSheetDetailId, int inspectionStatusId) {

		try {
		      StakingSheetDetail ssd = (StakingSheetDetail)this.em.find(StakingSheetDetail.class, stakingSheetDetailId);
		      ssd.setCurrentInspectionDetailStatusId(Integer.valueOf(inspectionStatusId));
		      
		      this.em.merge(ssd);
		    }
		    catch (EJBException e) {
		      logger.error(e.getMessage());
		    } 
		
	}

	@Override
	public void updateStakingSheetDetailStatusCancel(String stakingSheetDetailId, int inspectionStatusId) {

		StakingSheetDetail ssd = null;
	    try {
	      ssd = getStakingSheetDetailBySSDId(stakingSheetDetailId);
	    } catch (Exception e) {
	      logger.error(e.getMessage());
	    } 

	    
	    ssd.setCurrentInspectionDetailStatusId(Integer.valueOf(inspectionStatusId));
	    ssd.setCurrentInspectedDetailBy(null);
	    ssd.setCurrentInspectionDetailDt(null);
	    ssd.setCurrentInspectionDetailId(Integer.valueOf(0));
	    ssd.setCurrentInspectorDetailComments(null);
	    
	    ssd = (StakingSheetDetail)this.em.merge(ssd);
		
	}

	@Override
	public void updateOverallInspectionStatus(String woId, int inspectionStatusId) {

		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("WorkFlow.updateOverallWorkflowStatus");
	    try {
	      query.setParameter("IN_WorkOrderId", woId);
	      query.setParameter("IN_AsBuiltStatusId", null);
	      query.setParameter("IN_InspectionStatusId", Integer.valueOf(inspectionStatusId));
	      query.setParameter("IN_InvoiceId", null);
	      query.setParameter("IN_PaymentStatusId", null);
	      query.execute();
	    } catch (Exception ex) {
	      logger.error(ex);
	    } 
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public WorkFlow findWorkFlowbyWoId(String woId, String workgroup) {

		List<WorkFlow> result = null;
	    
	    Query query = null;
	    
	    if (workgroup.equals("LCEC")) {
	      query = this.em.createNamedQuery("WorkFlow.findWorkflowById");
	      query.setParameter("woId", woId);
	      query.setHint("javax.persistence.cache.storeMode", "REFRESH");
	    } else {
	      query = this.em.createNamedQuery("WorkFlow.findWorkflowByIdWorkGroup");
	      query.setParameter("woId", woId);
	      query.setParameter("workgroup", workgroup);
	      query.setHint("javax.persistence.cache.storeMode", "REFRESH");
	    } 
	    
	    try {
	      result = query.getResultList();
	    } catch (Exception e) {
	      logger.error(e);
	    } 
	    
	    if (result.size() == 1)
	      return (WorkFlow)result.get(0); 
	    if (result == null || result.isEmpty()) {
	      return null;
	    }
	    throw new NonUniqueResultException();
	}

	@Override
	public void updateOverallInvoiceStatus(String woId, int invoiceStatusId) {

		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("WorkFlow.updateOverallWorkflowStatus");
		
	    try {
	      query.setParameter("IN_WorkOrderId", woId);
	      query.setParameter("IN_AsBuiltStatusId", null);
	      query.setParameter("IN_InspectionStatusId", null);
	      query.setParameter("IN_InvoiceId", Integer.valueOf(invoiceStatusId));
	      query.setParameter("IN_PaymentStatusId", null);
	      query.execute();
	    } catch (Exception ex) {
	      logger.error(ex);
	    } 
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public StakingSheet getStakingSheetByWoId(String woId) {
		
		if (StringUtils.isEmpty(woId)) {
		      logger.warn("ValidationException: A valid Work ID is required.");
		    }

		    
		    List<StakingSheet> result = null;
		    
		    Query query = this.em.createNamedQuery("StakingSheet.findByWoId");
		    query.setParameter("woId", woId);
		    query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		    
		    result = query.getResultList();

		    
		    if (result.size() == 1)
		      return (StakingSheet)result.get(0); 
		    if (result == null || result.isEmpty()) {
		      return null;
		    }
		    throw new NonUniqueResultException();
	}

	@Override
	public ServiceOrder getServiceOrderByWOId(String woId) {
		
		if (StringUtils.isEmpty(woId)) {
			logger.warn("ValidationException: A valid Work ID is required.");
			//throw new ValidationException("A valid Work Order ID is required.");
		}

		ServiceOrder result = null;

		Query query = em.createNamedQuery("ServiceOrder.findByWoId");
		query.setParameter("woId", woId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = (ServiceOrder) query.getSingleResult();
		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: No Service Order found for Work Order ID: " + woId);
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			//throw new ProcessException(ex.getMessage());
		}

		return result;
	}

	@Override
	public ServiceOrder updateServiceOrder(ServiceOrder serviceOrder) {
		
		ServiceOrder result = null;
		
		try {
			result = em.merge(serviceOrder);
		} catch (Exception e) {
			logger.error("Error updating SO: " + serviceOrder.getServiceOrderId() + " - " + e.getMessage());
		}		
		
		return result;
	}

	@Override
	public boolean insertInspectionUnlock(InspectionUnlock inspectionUnlock) {
		
		try {
			em.persist(inspectionUnlock);
			return true;
		} catch (EJBException ex) {
			logger.error(ex.getMessage());
			return false;
		}
		
	}

}
