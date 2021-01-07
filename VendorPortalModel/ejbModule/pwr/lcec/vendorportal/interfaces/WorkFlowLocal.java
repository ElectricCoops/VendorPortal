package pwr.lcec.vendorportal.interfaces;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Local;

//import pwr.lcec.vendorportal.entity.bean.RateGroupPivot;
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

@Local
public interface WorkFlowLocal {

	public List<WorkFlowSearch_VW> getWorkflowById(String woId, String workgroup);

	public List<WorkFlowTask> getWorkFlowTaskById(Integer wfId, String woId);

	public List<WorkEventStatus> getWorkEventStatus();

	public List<StakingSheet> getStakingSheets();

	public StakingSheet getStakingShetByWoId(String woId);

	public List<StakingSheetDetail> getStakingSheetDetail();

	public List<StakingSheetDetail> getStakingSheetByInvId(int invId);

	public List<StakingSheetDetail> getStakingSheetDetailById(String woId);

	public List<StakingSheetDetail> getStakingSheetAvailForInsp(Integer asBuiltStatus, String woId);

	public List<StakingSheetDetail> getStakingSheetAvailForInv(String inspectionStatus, String woId);

	public void updateAsBuiltStakingSheetDetail(StakingSheetDetail asBuiltStake);

	public StakingSheetDetail updateStakingSheetDetail(StakingSheetDetail stakingSheetDetail);

	public void updateInspectionStakingSheetDetail(StakingSheetDetail inspectionStake, Integer inspectionId);

	public List<AsBuiltSummaryVw> getStakingSheetByGrpSummary(String woId);

	public List<AsBuiltStatus> getAsBuiltStatus();

	//public List<RateGroupPivot> getRateGroupPivot();

	public List<AssemblyAdhocVw> getDistinctRates(Integer rateGroupId, String energized, String transfer, Timestamp workEventDt);

	public String getDistinctRateDesc(String unit);

	public List<Invoice> getInvoiceByWoId(String woId);

	public Invoice insertInvoice(Invoice invoice);

	public InvoiceDetail insertInvoiceDetail(InvoiceDetail invoiceDetail);

	public String getStatkingSheetId(String woId);

	public Integer getRateGroupId(String groupName);

	public StakingSheetDetail insertSheetDetail(StakingSheetDetail stakingSheetDetail);

	public List<WorkFlowSearch_VW> searchWorkflows(String woId, String soId, String woStatus, Integer inspectionStatus,
			Integer invoiceStatus, String workgroup, String vendorName);
	
	public WorkFlow getWorkflow(Integer wfId);
	
	public List<WorkOrderAggVw> getByWorkflowId(int wfId);
	
	public int updateOverallAsbuiltStatusId(String woId);
	
	public List<VoucherGui> getVouchers(String woId);
	
	public Voucher getVoucherById(int voucherId);
	
	public Voucher insertVoucher(Voucher voucher);
	
	public Voucher updateVoucher(Voucher voucher);
	
	public List<VoucherGui> getVoucherByInspectionId(Integer inspectionId);
	
	public List<VoucherGui> getVoucherByInvoiceId(Integer invoiceId);
	
	public Resource insertResource(Resource resource);
	
	public WorkGroup insertWorkGroup(WorkGroup workGroup);
	
	public Integer getMaxResourceId(); 
	
	public void updateWorkflowTask(Integer wfTaskId, String woId);
	
	public List<Vendor> getVendors();
	
	public BigDecimal getAssemblyAmount(String workgroup, String workType, String assemblyUnit, Timestamp workEventDt);
	
	public Resource updateResource(Integer resourceId, Integer vpUserId);
	
	public Integer updateAsBuiltAmount(String stakingSheetDetailId, Timestamp workEventDt);
	
	public StakingSheetDetail getStakingSheetDetailBySSDId(String stakingSheetDetailId);
	
	public StakingSheetDetailGui getStakingSheetDetailGuiBySSDId(String stakingSheetDetailId);

	public WorkFlow getWorkFlowByWorkOrderId(String woId);
	
	public WorkEventStatus getWorkEventStatusById(String workEventStatusId);

	public WorkFlow updateWorkflowTask(WorkFlow workflow);

	public WorkFlowSearch_VW getWorkFlowByStakingSheetId(int stakingSheetId);

	public void updateStakingSheetDetailStatus(String stakingSheetDetailId, int inspectionStatusId);

	public void updateStakingSheetDetailStatusCancel(String stakingSheetDetailId, int inspectionStatusId);

	public void updateOverallInspectionStatus(String woId, int inspectionStatusId);

	public WorkFlow findWorkFlowbyWoId(String woId, String workgroup);

	public void updateOverallInvoiceStatus(String woId, int invoiceStatusId);
	
	public StakingSheet getStakingSheetByWoId(String woId);

	public List<StakingSheetDetailGui> getStakingSheetDetailGuiByWOId(String woId);
	
	public ServiceOrder getServiceOrderByWOId(String woId);
	
	public ServiceOrder updateServiceOrder(ServiceOrder serviceOrder);
	
	public boolean insertInspectionUnlock(InspectionUnlock inspectionUnlock);

}
