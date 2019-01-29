package pwr.lcec.vendorportal.interfaces;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Remote;

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

@Remote
public interface WorkFlowSessionRemote {

	public List<WorkFlowSearch_VW> getWorkflowById(String woId, String workgroup)
			throws ValidationException, NoResultException, ProcessException;

	public List<WorkFlowTask> getWorkFlowTaskById(Integer wfId, String woId)
			throws ValidationException, NoResultException, ProcessException;

	public List<WorkEventStatus> getWorkEventStatus();

	public List<StakingSheet> getStakingSheets();

	public StakingSheet getStakingShetByWoId(String woId)
			throws ValidationException, NoResultException, ProcessException;

	public List<StakingSheetDetail> getStakingSheetDetail();

	public List<StakingSheetDetail> getStakingSheetByInvId(int invId)
			throws ValidationException, NoResultException, ProcessException;

	public List<StakingSheetDetail> getStakingSheetDetailById(String woId)
			throws ValidationException, NoResultException, ProcessException;

	public List<StakingSheetDetail> getStakingSheetAvailForInsp(Integer asBuiltStatus, String woId)
			throws ValidationException, NoResultException, ProcessException;

	public List<StakingSheetDetail> getStakingSheetAvailForInv(String inspectionStatus,
			String woId) throws ValidationException, NoResultException, ProcessException;

	public void updateAsBuiltStakingSheetDetail(StakingSheetDetail asBuiltStake)
			throws ValidationException, ProcessException;

	public StakingSheetDetail updateStakingSheetDetail(StakingSheetDetail stakingSheetDetail)
			throws ValidationException, ProcessException;

	public void updateInspectionStakingSheetDetail(StakingSheetDetail inspectionStake, Integer inspectionId)
			throws ValidationException, ProcessException;

	public List<AsBuiltSummaryVw> getStakingSheetByGrpSummary(String woId)
			throws ValidationException, NoResultException, ProcessException;

	public List<AsBuiltStatus> getAsBuiltStatus();

	public List<RateGroupPivot> getRateGroupPivot();

	public List<AssemblyAdhocVw> getDistinctRates(Integer rateGroupId, String energized, String transfer) throws ProcessException;

	public String getDistinctRateDesc(String unit) throws ValidationException, ProcessException;

	public List<Invoice> getInvoiceByWoId(String woId) throws ValidationException, NoResultException, ProcessException;

	public Invoice insertInvoice(Invoice invoice) throws ValidationException, NoResultException, ProcessException;

	public InvoiceDetail insertInvoiceDetail(InvoiceDetail invoiceDetail)
			throws ValidationException, NoResultException, ProcessException;

	public String getStatkingSheetId(String woId);

	public Integer getRateGroupId(String groupName) throws ValidationException, NoResultException, ProcessException;

	public StakingSheetDetail insertSheetDetail(StakingSheetDetail stakingSheetDetail) throws ProcessException;

	public List<WorkFlowSearch_VW> searchWorkflows(String woId, String soId, String woStatus, Integer inspectionStatus,
			Integer invoiceStatus, String workgroup, String vendorName) throws ProcessException;
	
	public WorkFlow getWorkflow(Integer wfId) throws ProcessException;
	
	public List<WorkOrderAggVw> getByWorkflowId(int wfId) throws ValidationException, NoResultException, ProcessException;
	
	public int updateOverallAsbuiltStatusId(String woId) throws ProcessException;
	
	public List<Voucher> getVouchers(String woId) throws ProcessException;
	
	public Voucher insertVoucher(Voucher voucher) throws ProcessException, ValidationException;
	
	public Voucher updateVoucher(Voucher voucher) throws ProcessException, ValidationException;
	
	public List<Voucher> getVoucherByInspectionId(Integer inspectionId) throws ProcessException;
	
	public List<Voucher> getVoucherByInvoiceId(Integer invoiceId) throws ProcessException;
	
	public Resource insertResource(Resource resource) throws ValidationException, ProcessException;
	
	public WorkGroup insertWorkGroup(WorkGroup workGroup) throws ValidationException, ProcessException;
	
	public Integer getMaxResourceId(); 
	
	public void updateWorkflowTask(Integer wfTaskId, String woId) throws ProcessException;
	
	public List<Vendor> getVendors();
	
	public BigDecimal getAssemblyAmount(String workgroup, String workType, String assemblyUnit) throws ProcessException;
	
	public Resource updateResource(Integer resourceId, Integer vpUserId) throws Exception;
	
	public Integer updateAsBuiltAmount(String stakingSheetDetailId) throws Exception;

}
