package pwr.lcec.vendor.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.primefaces.PrimeFaces;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.Visibility;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;

import pwr.lcec.vendor.web.helper.Constants;
import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendorportal.custom.entity.AsBuiltStatus;
import pwr.lcec.vendorportal.custom.entity.AsBuiltSummaryVw;
import pwr.lcec.vendorportal.custom.entity.AssemblyAdhocVw;
import pwr.lcec.vendorportal.custom.entity.Inspection;
import pwr.lcec.vendorportal.custom.entity.InspectionDetailVw;
import pwr.lcec.vendorportal.custom.entity.InspectionStatus;
import pwr.lcec.vendorportal.custom.entity.Invoice;
import pwr.lcec.vendorportal.custom.entity.InvoiceDetail;
import pwr.lcec.vendorportal.custom.entity.InvoiceGLSummaryVw;
import pwr.lcec.vendorportal.custom.entity.InvoiceStatus;
import pwr.lcec.vendorportal.custom.entity.StakingSheet;
import pwr.lcec.vendorportal.custom.entity.StakingSheetDetail;
import pwr.lcec.vendorportal.custom.entity.Vendor;
import pwr.lcec.vendorportal.custom.entity.Voucher;
import pwr.lcec.vendorportal.custom.entity.WorkEventStatus;
import pwr.lcec.vendorportal.custom.entity.WorkFlow;
import pwr.lcec.vendorportal.custom.entity.WorkFlowSearch_VW;
import pwr.lcec.vendorportal.custom.entity.WorkFlowTask;
import pwr.lcec.vendorportal.custom.entity.WorkOrderAggVw;
import pwr.lcec.vendorportal.exception.NoResultException;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;
import pwr.lcec.vendorportal.interfaces.InspectionSessionRemote;
import pwr.lcec.vendorportal.interfaces.InvoiceSessionRemote;
import pwr.lcec.vendorportal.interfaces.IvueSessionRemote;
import pwr.lcec.vendorportal.interfaces.UserManagementRemote;
import pwr.lcec.vendorportal.interfaces.WorkFlowSessionRemote;
import pwr.lcec.vendorportal.sec.entity.User;

public class WorkflowController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(WorkflowController.class);

	private final String STAKING = "staking?faces-redirect=true";
	private final String STAKING_INVOICE = "SS";
	private final String INSPECTION_DETAIL = "inspectiondetail?faces-redirect=true";
	private final String WOSEARCH = "wosearch?faces-redirect=true";
	private final String NEW_INVOICE = "newinvoice?faces-redirect=true";
	private final String INVOICE_APPROVAL = "invoiceapproval?faces-redirect=true";
	private final String INVOICE_SEARCH = "invoicesearch?faces-redirect=true";
	private final String APPROVED = "Approved";
	private final String INVOICE_DETAIL = "invoicedetail?faces-redirect=true";
	

	@EJB
	private WorkFlowSessionRemote workflowService;
	@EJB
	private InspectionSessionRemote inspectionService;
	@EJB
	private InvoiceSessionRemote invoiceService;
	@EJB
	private IvueSessionRemote ivueService;
	@EJB
	private UserManagementRemote userManagementService;

	private String woId;
	private String soId;
	private String woStatus;
	private String woName;
	private String asBuiltStatus;
	private int wfId;
	private List<WorkFlowSearch_VW> workflows;
	private WorkFlowSearch_VW selectedWorkflows;
	private List<WorkFlowSearch_VW> filteredWorkflows;
	private List<WorkEventStatus> workEventStatus;
	private List<WorkFlowTask> workFlowTasks;
	private WorkFlowTask selectedWorkFlowTask;
	private StakingSheet stakingSheet;
	private List<StakingSheetDetail> stakingSheetDetail;
	private List<StakingSheetDetail> asBuiltStakingSheetDetail;
	private List<StakingSheetDetail> invoiceStakingSheetDetail;
	private StakingSheetDetail selectedStakingSheetDetail;
	private List<StakingSheetDetail> selectedStakingSheetDetails;
	private List<StakingSheetDetail> filteredStakingSheetDetail;
	private List<StakingSheetDetail> invoicedDetail;
	private List<StakingSheetDetail> inspStakingSheetDetail;
	private List<Inspection> inspections;
	private Inspection inspection;
	private List<InspectionDetailVw> inspectionDetailVw;
	private List<Invoice> invoices;
	private Invoice invoice;
	private List<WorkOrderAggVw> workOrderAggVw;
	private List<InvoiceGLSummaryVw> invoiceGLSummaryVw;
	private List<InvoiceDetail> invoiceDetail;
	private List<AssemblyAdhocVw> assemblyunits;
	private List<Voucher> vouchers;
	private List<Voucher> newInspVouchers;
	private List<Voucher> inspectedVouchers;
	private List<Voucher> invoiceVouchers;
	private List<Voucher> selectedInvoiceVouchers;
	private List<Voucher> invoiceApprovalVouchers;
	private List<Voucher> voucherSubmitInvoiceApproval;
	private List<StakingSheetDetail> invApprovalStakingSheetDet;
	
	private List<InvoiceStatus> invoiceStatus;
	
	private WorkFlow workflow;
	
	private List<InspectionStatus> inspStatuses;
	private List<InspectionStatus> newInspStatuses;
	private List<AsBuiltStatus> asBuiltStatuses;
	private List<AsBuiltStatus> addStationUnitAsBuiltStatuses;
	private List<AsBuiltSummaryVw> asBuiltSummaryVw;
	private List<Vendor> vendors;
	private String vendor;
	
	private String inspectionDetStatus;
	private String inspectionComment;
	private Integer invoiceNo;
	private int invoiceStatusId;
	private String vendorRefNo;
	private BigDecimal invAmt;
	
	ControllerUtil util = new ControllerUtil();
	
	private Integer inspectionId;
	private String currentStation;
	private int invoiceId;
	
	private boolean inspHist = false;
	private boolean newInsp = false;
	boolean showDetail = false;
	private boolean showSubmitInspectionBtn = false;
	private boolean asBuiltDisable = true;
	private boolean showAsBuiltStatus = true;
	private boolean editAsBuilt = true;
	private boolean renderAsBuiltChkBox = false;
	private boolean renderAddStationUnit = true;
	private boolean renderBackToInspTab = false;
	private boolean renderBackToInspSearch = false;
	private boolean disableApprovedBtn;
	private boolean renderBackToInvoiceTab = false;
	
	private List<String> distinctStation;
	private String inspectionStatusId;
	private String inspectedBy;
	private String cr;
	private String transfer;
	private String energized;
	
	private String overallInvoiceStatus;
	
	private String tempWoId;
	private String tempSoId;
	private String tempwoStatus;
	private Integer tempStakingInspectionStatus;
	private Integer tempStakingInvoiceStatus;
	
	private BigDecimal amtInvoicedToDt;
	private BigDecimal amtPaidToDt;
	private BigDecimal inspectionCompleted;
	private BigDecimal invoiceGLSummaryVwTotal;
	private BigDecimal auSubTotal = new BigDecimal(0);
	private BigDecimal auAmount;
	private BigDecimal voucherSubTotal = new BigDecimal(0);
	private BigDecimal voucherAmount;
	
	private int colSpan;
	
	private Integer stakingInspectionStatus;
	private Integer stakingInvoiceStatus;	
	
	//private List<Boolean> asBuiltColList;
	
	private int totalUnits;
	private int inspPassed;
	private int unitsRejected;
	private int notInspected;
	private Timestamp inspectionDt;
	//private Timestamp workEventDt;
	
	private Integer asBuiltActiveTab;
	
	private int wfOutput;
	private String invoiceComment;
	
	private String inspectionFilter;

	@PostConstruct
	public void init() {
		getAllWorkEvenStatus();
		findAsBuiltStatus();
		findInspectionStatus();
		findVendors();
		//asBuiltColList = Arrays.asList(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true);
	}

	public String findWorkFlows() throws ValidationException, NoResultException, ProcessException {

		workflows = workflowService.searchWorkflows(tempWoId, tempSoId, tempwoStatus, tempStakingInspectionStatus, tempStakingInvoiceStatus, util.getWrkGrp(), vendor);

			tempWoId = woId;
			tempSoId = soId;
			tempwoStatus = woStatus;
			tempStakingInspectionStatus = stakingInspectionStatus;
			tempStakingInvoiceStatus = stakingInvoiceStatus;
			
			clearInputs();
			
			return WOSEARCH;
	}

	public List<WorkEventStatus> getAllWorkEvenStatus() {

		workEventStatus = workflowService.getWorkEventStatus();

		Collections.sort(workEventStatus, new Comparator<WorkEventStatus>() {
			public int compare(WorkEventStatus o1, WorkEventStatus o2) {
				return o1.getDescription().compareToIgnoreCase(o2.getDescription());
			}
		});

		return workEventStatus;
	}

	public String findStakingDetailByWoId() {

		try {
			workflows = workflowService.getWorkflowById(woId, util.getWrkGrp());
			for (WorkFlowSearch_VW wf : workflows) {
				if (woId.equalsIgnoreCase(wf.getWorkOrderId())) {
					woStatus = wf.getWorkEventStatus();
					//woName = wf.getWorkOrderName();
					wfId = wf.getWorkFlowId();
					soId = wf.getServiceOrderId();
					//workEventDt = wf.getWorkEventDt();
				}
			}
			workOrderAggVw = workflowService.getByWorkflowId(wfId);
			
			stakingSheet = workflowService.getStakingShetByWoId(woId);
			
			if(stakingSheet == null) {
				facesError("No Stakingsheet available for selected Work Order.");
				return null;
			}
			
			woName = stakingSheet.getWorkOrderDescription();
			
			stakingSheetDetail = workflowService.getStakingSheetDetailById(woId);
			
			asBuiltStakingSheetDetail = stakingSheetDetail;
			
			workFlowTasks = workflowService.getWorkFlowTaskById(wfId, woId);
			
			asBuiltSummaryVw = workflowService.getStakingSheetByGrpSummary(woId);
			
			workflow = workflowService.getWorkflow(wfId);
			
			findInspetions();
			findAsBuiltStatus();
			sortWFTaskSeq(workFlowTasks);	
			findInvoices();
			findDistinctStation();
			findVouchers();
			
			setAsBuiltDisable(true);
			showAsBuiltStatus = true;
			colSpan = 23;
			editAsBuilt = true;
			renderAsBuiltChkBox = false;
			renderAddStationUnit = false;
			//multiViewState = true;
			
			//sortStations(asBuiltStakingSheetDetail);
			
			PrimeFaces.current().clearTableStates();
		} 
		catch (ValidationException | NoResultException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
		} 

		return STAKING;
	}

	public List<String> findDistinctStation() {

		distinctStation = new ArrayList<String>();
		
		if (getStakingSheetDetail() != null) {
			for (StakingSheetDetail det : getStakingSheetDetail()) {
				distinctStation.add(det.getStationDescription());
			}
			distinctStation = distinctStation.stream().distinct().collect(Collectors.toList());
			Collections.sort(distinctStation, String.CASE_INSENSITIVE_ORDER);
		}
		return distinctStation;
	}

	public void findInspetions() {
		
		inspPassed = 0;
		unitsRejected = 0;
		notInspected = 0;

		setTotalUnits(stakingSheetDetail.size());
		for (StakingSheetDetail detail : stakingSheetDetail) {
			if (detail.getInspectionStatus() != null) {
				if (detail.getInspectionStatus().getStatus().equals("Approved")) {
					inspPassed++;
				}
				if (detail.getInspectionStatus().getStatus().equals("Rejected")) {
					unitsRejected++;
				}
			}
			if (detail.getInspectionStatus().getStatus().equals("Ready for Inspection") || detail.getInspectionStatus().getStatus().equals("Not Inspected")) {
				notInspected++;
			}
		}
		try {
			inspections = inspectionService.getInspectionByWoId(woId);
			if (inspections.size() > 0) {
				// inspectionDetStatus = inspections.get(0).getInspectionStatus().getStatus();
			}
		} catch (ValidationException | NoResultException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
	}
	
	public void findAsBuiltStatus() {
		asBuiltStatuses = workflowService.getAsBuiltStatus();
		
		addStationUnitAsBuiltStatuses = asBuiltStatuses.stream().filter(item -> item.getDescription().equals("Completed") || item.getDescription().equals("Not Started")).collect(Collectors.toList());
		
		asBuiltStatuses = asBuiltStatuses.stream().filter(item -> item.getDescription().equals("Completed") || item.getDescription().equals("Rejected") || item.getDescription().equals("Appealed") || item.getDescription().equals("Not Started")).collect(Collectors.toList());
		
		asBuiltStatuses = asBuiltStatuses.stream().sorted(Comparator.comparing(AsBuiltStatus::getDescription)).collect(Collectors.toList());
	}
	
	public void findInvoices() {
		try {
			invoices = workflowService.getInvoiceByWoId(woId);
		} catch (ValidationException | NoResultException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
	}
	
	public int getInspectorId() {
		
		User user;
		StringBuilder sb = new StringBuilder();
		try {
			user = userManagementService.finUserByPrincipal(getCurrentUser());
			
			sb.append(user.getFirstName());
			sb.append(Constants.SPACE_SEPARATOR);
			sb.append(user.getLastName());
			
		} catch (ValidationException | ProcessException | NoResultException e1) {
			logger.error(e1);
			facesError(e1.getMessage());
		}
		
		int inspectorId = 0;
		
		try {
			inspectorId = inspectionService.getResourceId(sb.toString());
			if(inspectorId == 0) {
				logger.warn("NoResultException: "+util.getCurrentUser()+" does not exist in the Resource Table.");
				facesError("User does not exist in Resource Table.");
				return inspectorId;
			}
		} catch (ValidationException | NoResultException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		return inspectorId;
	}

	public String findInspectionDetails() throws ProcessException, ValidationException, NoResultException {

		showSubmitInspectionBtn = false;

		inspStakingSheetDetail = stakingSheetDetail.stream().filter(item -> item.getCurrentInspectionDetailStatusId() == 2).collect(Collectors.toList());
		
		newInspVouchers = vouchers.stream().filter(item -> item.getInspectionStatusId() == 2).collect(Collectors.toList());

		if (inspStakingSheetDetail.size() <= 0 && newInspVouchers.size() <= 0) {
			facesError("No Assembly Unit or Vouchers available for inspection!");
			return null;
		} else {
			newInspStatuses = inspStatuses.stream().filter(item -> item.getStatus().equals("Rejected")
							|| item.getStatus().equals("Ready for Inspection") || item.getStatus().equals("Approved")).collect(Collectors.toList());
			inspHist = false;
			newInsp = true;
			//renderBackToInspTab = true;
		}

		return INSPECTION_DETAIL;
	}

	public String findInspectionById() {

		showSubmitInspectionBtn = true;

		try {
			inspectionDetailVw = inspectionService.getInspectionDetailByInspId(inspectionId);

			inspection = inspectionService.getInspectionStatusId(inspectionId);
			
			inspectedVouchers = workflowService.getVoucherByInspectionId(inspectionId);

			inspectionDetStatus = inspection.getInspectionStatus().getStatus();
			inspectionDt = inspection.getInspectionDt();
			woId = inspection.getWorkOrderId();
			woName = inspection.getWorkFlow().getWorkOrderName();
			inspectedBy = inspection.getResource().getResourceName();

		} catch (ValidationException | NoResultException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		
		inspHist = true;
		newInsp = false;

		return INSPECTION_DETAIL;
	}

	public String findSoDetailBySoId() {
		
		return WOSEARCH;
	}
	
	public String createInvoice() {
		
		invoiceVouchers = vouchers.stream().filter(item -> item.getInspectionStatusId() == 4 && (item.getInvoiceStatusId() == 1 || item.getInvoiceStatusId() == 3)).collect(Collectors.toList());
		
		try {
			invoiceStakingSheetDetail = workflowService.getStakingSheetAvailForInv(APPROVED, woId);
			if(invoiceStakingSheetDetail.size() <= 0 && invoiceVouchers.size() <= 0) {
				facesError("No assembly available for invoice.");
				return null;
			}
		} catch (ValidationException | NoResultException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		return NEW_INVOICE;
	}
	
	public String submitInvoice() throws ProcessException, NoResultException {

		if ((selectedStakingSheetDetails.size() <= 0) && (selectedInvoiceVouchers.size() <= 0)) {
			facesError("At least one assembly must be selected for Staking and/or Voucher for invoice.");
			return null;
		}
		
		final String guid = util.genGUID();
		
		if (selectedInvoiceVouchers.size() > 0) {
			for (Voucher voucher : selectedInvoiceVouchers) {
				voucher.setInvoiceStatusId(2);
				voucher.setSubmitGuid(guid);
				try {
					workflowService.updateVoucher(voucher);
				} catch (ValidationException e) {
					facesError(e.getMessage());
					logger.error(e.getMessage());
					throw new ProcessException(e.getMessage());
				}
			}
		}

		if (selectedStakingSheetDetails.size() > 0) {
			for (StakingSheetDetail detail : selectedStakingSheetDetails) {
				detail.setInvoiceSubmitGuid(guid);
				try {
					workflowService.updateStakingSheetDetail(detail);
				} catch (ValidationException e) {
					facesError(e.getMessage());
					logger.error(e.getMessage());
				}
			}
			String stakingSheetId = selectedStakingSheetDetails.get(0).getStakingSheetId();

			try {
				invoiceId = invoiceService.updateSubmitInvoice(guid, getCurrentUser(), vendorRefNo, STAKING_INVOICE);

				wfOutput = invoiceService.updateWorkflowCalc(Integer.valueOf(stakingSheetId), util.currentDtTm(),"INV");

			} catch (Exception e) {
				logger.error(e);
				facesError(e.getMessage());
			}
		}
		return findInvoiceDetail(invoiceId);
	}	
	
	public void auInvRowSelectCheckbox(SelectEvent event) {

		BigDecimal sum = BigDecimal.ZERO;

		if (selectedStakingSheetDetails.size() > 0) {
			for (StakingSheetDetail detail : selectedStakingSheetDetails) {
				try {
					//auAmount = workflowService.getAssemblyAmount(util.getWrkGrp(), detail.getAssemblyActionCode(),detail.getAssemblyGuid(),workEventDt);
					auAmount = workflowService.getAssemblyAmount(util.getWrkGrp(), detail.getAssemblyActionCode(),detail.getAssemblyGuid());
					if(auAmount != null) {
						auAmount = auAmount.multiply(new BigDecimal(detail.getAsBuiltQuantity()));
					}
				} catch (ProcessException e) {
					logger.error(e);
					facesError(e.getMessage());
				}
				if(auAmount != null) {
					sum = sum.add(auAmount);
				}
			}
		}
		auSubTotal = sum;
	}
	
	public void auInvRowUnSelectCheckbox(UnselectEvent event) {
		
		String actionCode = ((StakingSheetDetail) event.getObject()).getAssemblyActionCode();
		String assemblyGuid = ((StakingSheetDetail) event.getObject()).getAssemblyGuid();
		int asBuiltQty = ((StakingSheetDetail) event.getObject()).getAsBuiltQuantity();

		try {
			//auAmount = workflowService.getAssemblyAmount(util.getWrkGrp(), actionCode, assemblyGuid, workEventDt);
			auAmount = workflowService.getAssemblyAmount(util.getWrkGrp(), actionCode, assemblyGuid);
			if(auAmount != null) {
				auAmount = auAmount.multiply(new BigDecimal(asBuiltQty));
			}
		} catch (ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		if(auAmount != null) {
			auSubTotal = auSubTotal.subtract(auAmount);
		}
	}
	
	public void auInvAllRowSelectCheckbox(ToggleSelectEvent event) {

		BigDecimal sum = BigDecimal.ZERO;

		if (selectedStakingSheetDetails.size() > 0) {
			for (StakingSheetDetail detail : selectedStakingSheetDetails) {
				try {
					//auAmount = workflowService.getAssemblyAmount(util.getWrkGrp(), detail.getAssemblyActionCode(),detail.getAssemblyGuid(), workEventDt);
					auAmount = workflowService.getAssemblyAmount(util.getWrkGrp(), detail.getAssemblyActionCode(),detail.getAssemblyGuid());
					if(auAmount != null) {
						auAmount = auAmount.multiply(new BigDecimal(detail.getAsBuiltQuantity()));
					}
				} catch (ProcessException e) {
					logger.error(e);
					facesError(e.getMessage());
				}
				if(auAmount != null) {
					sum = sum.add(auAmount);
				}
			}
		}
		auSubTotal = sum;
	}
	
	public void voucherInvRowSelectCheckbox(SelectEvent event) {
		
		BigDecimal sum = BigDecimal.ZERO;
		if(selectedInvoiceVouchers.size() > 0) {
			for(Voucher voucher : selectedInvoiceVouchers) {
				voucherAmount = voucher.getAmount();
				
				sum = sum.add(voucherAmount);
			}
			voucherSubTotal = sum;
		}
	}
	
	public void voucherInvRowUnSelectCheckbox(UnselectEvent event) {
		
		voucherAmount = ((Voucher)event.getObject()).getAmount();
		
		voucherSubTotal = voucherSubTotal.subtract(voucherAmount);
	}
	
	public void voucherInvAllRowSelectCheckbox(ToggleSelectEvent event) {
		
		BigDecimal sum = BigDecimal.ZERO;
		
		if(selectedInvoiceVouchers.size() > 0) {
			for(Voucher voucher : selectedInvoiceVouchers) {
				voucherSubTotal = voucher.getAmount();
				
				sum = sum.add(voucherSubTotal);
			}
			voucherSubTotal = sum;
		}
	}
	
	public String findInvoiceSummaryById() throws NoResultException {
		try {
			invoice = invoiceService.getInvoiceById(invoiceId);
			showDetail = true;
		} catch (ValidationException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		return INVOICE_DETAIL;
	}
	
	public String findInvoiceDetail(Integer invoiceId) throws NoResultException {
		getInvoiceDetails(invoiceId);
		showDetail = true;
		//renderBackToInvoiceTab = true;

		return INVOICE_DETAIL;
	}

	public String findInvoiceApproval() {
		invoiceStatus = invoiceService.getAllInvStatus();
		
		invoiceStatus = invoiceStatus.stream().filter(item -> item.getInvoiceStatusId() == 2 || item.getInvoiceStatusId() == 3 || item.getInvoiceStatusId() == 4).collect(Collectors.toList());		
		
		getInvoiceDetails(invoiceId);
		
		voucherSubmitInvoiceApproval = invoiceApprovalVouchers.stream().filter(item -> item.getInvoiceStatusId() == 2).collect(Collectors.toList());
		
		for(Voucher voucher : invoiceApprovalVouchers) {
			if(voucher.getInvoiceStatusId() == 3) {				
				disableApprovedBtn = true;
				return INVOICE_APPROVAL;
			}else if (voucher.getInvoiceStatusId() != 3) {
				disableApprovedBtn = false;
			}
		}
		for (StakingSheetDetail ss : invApprovalStakingSheetDet) {
			if (ss.getInvoiceStatusId() != null && ss.getInvoiceStatusId() == 3) {
				disableApprovedBtn = true;
				return INVOICE_APPROVAL;
			} else if (ss.getInvoiceStatusId() != null && ss.getInvoiceStatusId() != 3) {
				disableApprovedBtn = false;
			}
		}
		return INVOICE_APPROVAL;
	}
	
	public String updateApprovalBtn() {
		for (StakingSheetDetail ss : invApprovalStakingSheetDet) {
			if (ss.getInvoiceStatusId() == 3) {
				disableApprovedBtn = true;
				facesError("Comment required on rejected Invoice item.");
				return INVOICE_APPROVAL;
			} else if (ss.getInvoiceStatusId() != 3)  {
				disableApprovedBtn = false;
			}
		}
		for(Voucher voucher : invoiceApprovalVouchers) {
			if(voucher.getInvoiceStatusId() == 3) {				
				disableApprovedBtn = true;
				return INVOICE_APPROVAL;
			}else if (voucher.getInvoiceStatusId() != 3) {
				disableApprovedBtn = false;
			}
		}
		return INVOICE_APPROVAL;
	}
	
	@SuppressWarnings("unused")
	public String submitInvoiceApproval() throws NoResultException, ProcessException {
		int output = 0;
		try {
			invoiceService.updateInvoiceApproval(invoiceId, 4, util.getCurrentUser(), util.currentDtTm());
			output = invoiceService.updateInvoiceStatus(invoiceId, 4, STAKING_INVOICE, util.getCurrentUser(), util.currentDtTm(), invoiceComment);
			facesInfo("Invoice Approved.");
		} catch (ValidationException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		util.refreshInvoiceSearch();
		
		return INVOICE_SEARCH;
	}
	
	@SuppressWarnings("unused")
	public String submitInvoiceRejected() throws NoResultException, ProcessException {
		int output = 0;
		try {
			output = invoiceService.updateInvoiceStatus(invoiceId, 3, STAKING_INVOICE, util.getCurrentUser(), util.currentDtTm(), invoiceComment);
			facesInfo("Invoice Rejected.");
		} catch (ValidationException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		util.refreshInvoiceSearch();
		
		return INVOICE_SEARCH;
	}
	
	public String updateInvoiceApproval(StakingSheetDetail stakingSheetDetail) throws NoResultException {
		try {
			stakingSheetDetail.setInvoiceApprovedBy(util.getCurrentUser());
			stakingSheetDetail.setInvoiceApprovedDt(util.currentDtTm());

				disableApprovedBtn = false;
				workflowService.updateStakingSheetDetail(stakingSheetDetail);

				getInvoiceDetails(invoiceId);
				findInvoiceApproval();
				
				facesInfo("Record updated successfully.");	
		} catch (ValidationException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		return INVOICE_APPROVAL;
	}
	
	public String updateVoucherInvoiceApproval(Voucher voucher) {

		try {
			voucher.setApprovedBy(util.getCurrentUser());
			voucher.setApprovedDt(util.currentDtTm());

			disableApprovedBtn = false;
			workflowService.updateVoucher(voucher);

			getInvoiceDetails(invoiceId);
			findInvoiceApproval();
			
			facesInfo("Record updated successfully.");	
			
		} catch (ProcessException | ValidationException e) {
			logger.error(e);
			facesError(e.getMessage());
		}

		return INVOICE_APPROVAL;
	}

	public void getInvoiceDetails(Integer invoiceId) {
		try {
			invoiceDetail = invoiceService.getInvoiceDetails(invoiceId);
			invoiceGLSummaryVw = invoiceService.getInvoiceGLSummaryVw(invoiceId);
			invApprovalStakingSheetDet = workflowService.getStakingSheetByInvId(invoiceId);
			invoice = invoiceService.getInvoiceById(invoiceId);	
			invoiceApprovalVouchers = workflowService.getVoucherByInvoiceId(invoiceId);
			
			//invoiceGLSummaryVwTotal = invoiceGLSummaryVw.stream().map(InvoiceGLSummaryVw::getExtCost).reduce(BigDecimal::add).get();
			
		} catch (ValidationException | NoResultException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
	}

	public String findInvoiceById(Integer invoiceNo) throws NoResultException {
		try {
			invoice = invoiceService.getInvoiceById(invoiceNo);
			showDetail = true;
		} catch (ValidationException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		return INVOICE_DETAIL;
	}
	
	public void findInspectionStatus() {
		inspStatuses = inspectionService.getInspetionStatus();
	}
	
	public void sortWFTaskSeq(List<WorkFlowTask> tasks) {
		Collections.sort(tasks, new Comparator<WorkFlowTask>() {
			public int compare(WorkFlowTask s1, WorkFlowTask s2) {
				double i1 = Double.parseDouble(s1.getWorkFlowTaskSeq());
				double i2 = Double.parseDouble(s2.getWorkFlowTaskSeq());
				return Double.compare(i1, i2);
			}
		});
	}
	
	public void sortStations(List<StakingSheetDetail> details) {
		Collections.sort(details, new Comparator<StakingSheetDetail>() {
			public int compare(StakingSheetDetail o1, StakingSheetDetail o2) {

				int i1 = Integer.valueOf(o1.getStationDescription());
				int i2 = Integer.valueOf(o2.getStationDescription());
				if (i1 > i2) {
					return -1;
				} else {
					return 0;
				}

			}
		});
	}

	public void onStakingRowEdit(RowEditEvent event) {
		StakingSheetDetail asBuiltStake = (StakingSheetDetail) event.getObject();

		asBuiltStake.setAsBuiltDt(currentDtTm());
		asBuiltStake.setAsBuiltBy(getCurrentUser());
		try {
			workflowService.updateAsBuiltStakingSheetDetail(asBuiltStake);
		} catch (ValidationException | ProcessException e) {
			logger.error(e.getMessage());
			facesError(e.getMessage());
		}
	}
	
	public String submitInspection() throws ValidationException, ProcessException {

		Inspection inspection = new Inspection();

		inspection.setInspectionDt(currentDtTm());
		inspection.setInspectedBy(getInspectorId());
		inspection.setInspectionStatusId(2);
		inspection.setWorkOrderId(woId);
		inspection.setWorkFlowId(wfId);
		inspection.setServiceOrderId(soId);
		inspection.setInspectionType("SS");

		try {
			inspectionId = inspectionService.insertInspection(inspection);
		} catch (ValidationException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		int rejected = 0;
		for (StakingSheetDetail inspected : inspStakingSheetDetail) {
			if (inspected.getCurrentInspectionDetailStatusId() != 2) {
				inspected.setCurrentInspectionDetailDt(currentDtTm());
				inspected.setCurrentInspectedDetailBy(String.valueOf(getInspectorId()));

				if (inspected.getCurrentInspectionDetailStatusId() == 0
						|| inspected.getCurrentInspectionDetailStatusId() == null) {
					facesError("Please select an Inpection Status from the drop-down menu.");
					return null;
				}
				try {
					workflowService.updateInspectionStakingSheetDetail(inspected, inspectionId);
				} catch (ProcessException e) {
					logger.error(e);
					facesError(e.getMessage());
				}
				if (inspected.getCurrentInspectionDetailStatusId() == 5) {
					rejected++;
				}
			}
		}
		for (Voucher voucher : newInspVouchers) {
			if (voucher.getInspectionStatusId() != 2) {
				voucher.setInspectionId(inspectionId);
				if (voucher.getInspectionStatusId() == null || voucher.getInspectionStatusId() == 0) {
					facesError("Please select an Inpection Status from the drop-down menu.");
					return null;
				}
				workflowService.updateVoucher(voucher);

				if (voucher.getInspectionStatusId() == 5) {
					rejected++;
				}
			}
		}
		if (rejected > 0) {
			inspectionService.updateInspection(inspectionId, 5);
		} else {
			inspectionService.updateInspection(inspectionId, 4);
		}

		return findStakingDetailByWoId();
	}
	
	public String asBuiltEdit() {

		PrimeFaces.current().clearTableStates();

		asBuiltStakingSheetDetail = stakingSheetDetail.stream().filter(item -> (item.getAsBuiltStatus() == null
						|| (item.getAsBuiltStatus().getAsBuiltStatusId() == 3 && item.getInspectionStatus().getStatus().equals("Ready for Inspection"))
						|| (item.getAsBuiltStatusId() != 6 && item.getAsBuiltStatusId() != 3 && item.getAsBuiltStatusId() != 5))).collect(Collectors.toList());

		setAsBuiltDisable(false);
		showAsBuiltStatus = false;
		colSpan = 12;
		editAsBuilt = false;
		renderAsBuiltChkBox = true;
		renderAddStationUnit = true;
		// multiViewState = true;

		return STAKING;
	}
	
	public String asBuiltSave() throws ValidationException, ProcessException, NoResultException {

		for (StakingSheetDetail staking : asBuiltStakingSheetDetail) {
			if ((staking.getAsBuiltStatusId() == 3) || (staking.getAsBuiltStatusId() == 6) ) {
				
				staking.setAsBuiltStatusId(staking.getAsBuiltStatusId());
				staking.setCurrentInspectionDetailStatusId(2);
				staking.setAsBuiltDt(util.currentDtTm());
				staking.setAsBuiltBy(util.getCurrentUser());
				staking.setAsBuiltComments(staking.getAsBuiltComments());

				if(staking.getAsBuiltStatusId() == 6 && StringUtils.isBlank(staking.getAsBuiltComments())) {
					facesError("As-Built comment required if status is 'Appealed'.");
					return null;
				}
				workflowService.updateStakingSheetDetail(staking);
				
				try {
					workflowService.updateAsBuiltAmount(staking.getStakingSheetDetailId());
				} catch (Exception e) {
					logger.error(e);
					facesError(e.getMessage());
				}
			}
		}
		int wfTaskId = workflowService.updateOverallAsbuiltStatusId(woId);

		if ((wfTaskId != 0) || (wfTaskId != 1)) {
			workflowService.updateWorkflowTask(wfTaskId, "COMP");
		}

		asBuiltStakingSheetDetail = workflowService.getStakingSheetDetailById(woId);
		/*asBuiltStakingSheetDetail = asBuiltStakingSheetDetail.stream()
				.filter(item -> (item.getAsBuiltStatus() == null || (item.getAsBuiltStatusId() != 3) && (item.getAsBuiltStatusId() != 6)))
				.collect(Collectors.toList());*/
		asBuiltStakingSheetDetail = asBuiltStakingSheetDetail.stream().filter(item -> (item.getAsBuiltStatus() == null
				|| (item.getAsBuiltStatus().getAsBuiltStatusId() == 3 && item.getInspectionStatus().getStatus().equals("Ready for Inspection"))
				|| (item.getAsBuiltStatusId() != 6 && item.getAsBuiltStatusId() != 3 && item.getAsBuiltStatusId() != 5))).collect(Collectors.toList());

		facesInfo("Record(s) saved successful.");

		return STAKING;
	}
	
	public void asBuiltCellEdit(StakingSheetDetail stakingSheetDetail) {

		if(stakingSheetDetail.getAsBuiltStatus().getDescription().equals("Appealed")
				&& StringUtils.isEmpty(stakingSheetDetail.getAsBuiltComments())) {
			facesError("As-Built Comment is required on Appealed As-Built Status");
			return;
		}
		updateStakingsheetDetail(stakingSheetDetail);
	}
	
	public void updateStakingsheetDetail(StakingSheetDetail stakingSheetDetail) {
		
		try {
			workflowService.updateStakingSheetDetail(stakingSheetDetail);
			facesInfo("Record updated successfully.");
		} catch (ValidationException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
	}

	public void inspectionDetailFilter() {

		if (StringUtils.isNotBlank(inspectionFilter)) {
			if (inspectionFilter.equals("all")) {
				this.inspStakingSheetDetail = asBuiltStakingSheetDetail;
			} else if (inspectionFilter.equals("ready")) {
				this.inspStakingSheetDetail = stakingSheetDetail.stream().filter(item -> item.getCurrentInspectionDetailStatusId() == 2).collect(Collectors.toList());
			}
		}
	}

	public void findVouchers() {
		try {
			vouchers = workflowService.getVouchers(woId);
		} catch (ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
	}

	public void onAsBuiltTabChange(TabChangeEvent event) {

		Tab activeTab = event.getTab();

		asBuiltActiveTab = ((TabView) event.getSource()).getChildren().indexOf(activeTab);
	}
	
	public void onAsBuiltToggle(ToggleEvent event) {

		if (event.getVisibility() == Visibility.VISIBLE) {
			colSpan++;
		} else if (event.getVisibility() == Visibility.HIDDEN) {
			colSpan--;
		}
	}
	
	public void updateAssemblyUnitEnergized() {
		
		assemblyunits = assemblyunits.stream().filter(item -> item.getEnergized().equals(energized)).collect(Collectors.toList());
	}

	public void preProcessorStakingSheetRsltPDF(Object document) {
		Document doc = (Document) document;
		doc.setPageSize(PageSize.A4.rotate());
	}
	
	public void preProcessorRejectionRpt(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        
        asBuiltStakingSheetDetail= asBuiltStakingSheetDetail.stream().filter(item -> item.getInspectionStatus().getStatus().equals("Rejected")).collect(Collectors.toList());
	}
	
	public void findVendors() {
		vendors = workflowService.getVendors();
	}

	public void onRowCancel(RowEditEvent event) { }
	
	public Timestamp currentDtTm() {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		return now;
	}
	
	public String getCurrentUser() {
		Subject currentUser = SecurityUtils.getSubject();
		
		return currentUser.getPrincipal().toString();
	}
	
	private void facesError(String message) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
	}
	
	private void facesInfo(String message) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
	}
	
	public void clearInputs() {
		tempWoId = null;
		tempSoId = null;
		tempwoStatus = null;
		vendor = null;
		tempStakingInspectionStatus = null;
		tempStakingInvoiceStatus = null;
		inspPassed = 0;
		unitsRejected = 0;
		notInspected = 0;
	}

	public List<WorkFlowSearch_VW> getWorkflows() {
		return workflows;
	}

	public void setWorkflows(List<WorkFlowSearch_VW> workflows) {
		this.workflows = workflows;
	}

	public List<WorkFlowSearch_VW> getFilteredWorkflows() {
		return filteredWorkflows;
	}

	public void setFilteredWorkflows(List<WorkFlowSearch_VW> filteredWorkflows) {
		this.filteredWorkflows = filteredWorkflows;
	}

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String getWoId() {
		return woId;
	}

	public void setWoId(String woId) {
		this.woId = woId;
	}

	public List<WorkEventStatus> getWorkEventStatus() {
		return workEventStatus;
	}

	public void setWorkEventStatus(List<WorkEventStatus> workEventStatus) {
		this.workEventStatus = workEventStatus;
	}

	public String getWoStatus() {
		return woStatus;
	}

	public void setWoStatus(String woStatus) {
		this.woStatus = woStatus;
	}

	public String getWoName() {
		return woName;
	}

	public void setWoName(String woName) {
		this.woName = woName;
	}

	public WorkFlowSearch_VW getSelectedWorkflows() {
		return selectedWorkflows;
	}

	public void setSelectedWorkflows(WorkFlowSearch_VW selectedWorkflows) {
		this.selectedWorkflows = selectedWorkflows;
	}

	public int getWfId() {
		return wfId;
	}

	public void setWfId(int wfId) {
		this.wfId = wfId;
	}

	public List<WorkFlowTask> getWorkFlowTasks() {
		return workFlowTasks;
	}

	public void setWorkFlowTasks(List<WorkFlowTask> workFlowTasks) {
		this.workFlowTasks = workFlowTasks;
	}

	public WorkFlowTask getSelectedWorkFlowTask() {
		return selectedWorkFlowTask;
	}

	public void setSelectedWorkFlowTask(WorkFlowTask selectedWorkFlowTask) {
		this.selectedWorkFlowTask = selectedWorkFlowTask;
	}

	public List<StakingSheetDetail> getStakingSheetDetail() {
		return stakingSheetDetail;
	}

	public void setStakingSheetDetail(List<StakingSheetDetail> stakingSheetDetail) {
		this.stakingSheetDetail = stakingSheetDetail;
	}

	public List<StakingSheetDetail> getFilteredStakingSheetDetail() {
		return filteredStakingSheetDetail;
	}

	public void setFilteredStakingSheetDetail(List<StakingSheetDetail> filteredStakingSheetDetail) {
		this.filteredStakingSheetDetail = filteredStakingSheetDetail;
	}

	public List<Inspection> getInspections() {
		return inspections;
	}

	public void setInspections(List<Inspection> inspections) {
		this.inspections = inspections;
	}
	
	public String getInspectionComment() {
		return inspectionComment;
	}

	public void setInspectionComment(String inspectionComment) {
		this.inspectionComment = inspectionComment;
	}

	public List<InspectionStatus> getInspStatuses() {
		return inspStatuses;
	}

	public void setInspStatuses(List<InspectionStatus> inspStatuses) {
		this.inspStatuses = inspStatuses;
	}

	public StakingSheetDetail getSelectedStakingSheetDetail() {
		return selectedStakingSheetDetail;
	}

	public void setSelectedStakingSheetDetail(StakingSheetDetail selectedStakingSheetDetail) {
		this.selectedStakingSheetDetail = selectedStakingSheetDetail;
	}

	public List<AsBuiltStatus> getAsBuiltStatuses() {
		return asBuiltStatuses;
	}

	public void setAsBuiltStatuses(List<AsBuiltStatus> asBuiltStatuses) {
		this.asBuiltStatuses = asBuiltStatuses;
	}

	public String getInspectionDetStatus() {
		return inspectionDetStatus;
	}

	public void setInspectionDetStatus(String inspectionDetStatus) {
		this.inspectionDetStatus = inspectionDetStatus;
	}

	public Integer getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(Integer inspectionId) {
		this.inspectionId = inspectionId;
	}

	public String getAsBuiltStatus() {
		return asBuiltStatus;
	}

	public void setAsBuiltStatus(String asBuiltStatus) {
		this.asBuiltStatus = asBuiltStatus;
	}

	public List<InspectionDetailVw> getInspectionDetailVw() {
		return inspectionDetailVw;
	}

	public void setInspectionDetailVw(List<InspectionDetailVw> inspectionDetailVw) {
		this.inspectionDetailVw = inspectionDetailVw;
	}

	public boolean isInspHist() {
		return inspHist;
	}

	public void setInspHist(boolean inspHist) {
		this.inspHist = inspHist;
	}

	public boolean isNewInsp() {
		return newInsp;
	}

	public void setNewInsp(boolean newInsp) {
		this.newInsp = newInsp;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public Integer getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(Integer invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public List<StakingSheetDetail> getSelectedStakingSheetDetails() {
		return selectedStakingSheetDetails;
	}

	public void setSelectedStakingSheetDetails(List<StakingSheetDetail> selectedStakingSheetDetails) {
		this.selectedStakingSheetDetails = selectedStakingSheetDetails;
	}

	public String getVendorRefNo() {
		return vendorRefNo;
	}

	public void setVendorRefNo(String vendorRefNo) {
		this.vendorRefNo = vendorRefNo;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public int getInvoiceStatusId() {
		return invoiceStatusId;
	}

	public void setInvoiceStatusId(int invoiceStatusId) {
		this.invoiceStatusId = invoiceStatusId;
	}

	public BigDecimal getInvAmt() {
		BigDecimal x;
		BigDecimal y;
		invAmt = new BigDecimal(0);
		for (StakingSheetDetail detail : selectedStakingSheetDetails) {
			x = BigDecimal.valueOf(detail.getAsBuiltQuantity());
			y = x.multiply(new BigDecimal(detail.getRateGroupPrice().getFixedCost()));

			invAmt = invAmt.add(y);
		}
		return invAmt;
	}

	public void setInvAmt(BigDecimal invAmt) {
		this.invAmt = invAmt;
	}

	public List<AsBuiltSummaryVw> getAsBuiltSummaryVw() {
		return asBuiltSummaryVw;
	}

	public void setAsBuiltSummaryVw(List<AsBuiltSummaryVw> asBuiltSummaryVw) {
		this.asBuiltSummaryVw = asBuiltSummaryVw;
	}
	
	public String getCurrentStation() {
		return currentStation;
	}

	public void setCurrentStation(String currentStation) {
		this.currentStation = currentStation;
	}

	public List<String> getDistinctStation() {
		return distinctStation;
	}

	public void setDistinctStation(List<String> distinctStation) {
		this.distinctStation = distinctStation;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public boolean isShowDetail() {
		return showDetail;
	}

	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;
	}

	public List<StakingSheetDetail> getInvoicedDetail() {
		return invoicedDetail;
	}

	public void setInvoicedDetail(List<StakingSheetDetail> invoicedDetail) {
		this.invoicedDetail = invoicedDetail;
	}

	public String getInspectionStatusId() {
		return inspectionStatusId;
	}

	public void setInspectionStatusId(String inspectionStatusId) {
		this.inspectionStatusId = inspectionStatusId;
	}

	public boolean isShowSubmitInspectionBtn() {
		return showSubmitInspectionBtn;
	}

	public void setShowSubmitInspectionBtn(boolean showSubmitInspectionBtn) {
		this.showSubmitInspectionBtn = showSubmitInspectionBtn;
	}

	public String getTempWoId() {
		return tempWoId;
	}

	public void setTempWoId(String tempWoId) {
		this.tempWoId = tempWoId;
	}

	public String getTempSoId() {
		return tempSoId;
	}

	public void setTempSoId(String tempSoId) {
		this.tempSoId = tempSoId;
	}

	public String getTempwoStatus() {
		return tempwoStatus;
	}

	public void setTempwoStatus(String tempwoStatus) {
		this.tempwoStatus = tempwoStatus;
	}

	public String getOverallInvoiceStatus() {
		return overallInvoiceStatus;
	}

	public void setOverallInvoiceStatus(String overallInvoiceStatus) {
		this.overallInvoiceStatus = overallInvoiceStatus;
	}

	public BigDecimal getAmtInvoicedToDt() {
		amtInvoicedToDt = new BigDecimal(0);
		amtPaidToDt = new BigDecimal(0);
		if (invoices != null) {
			for (Invoice invoice : invoices) {
				if (invoice.getInvoiceAmount() != null) {
					if (invoice.getInvoiceStatus().getDescription().equals("Submitted")) {
						amtInvoicedToDt = amtInvoicedToDt.add(invoice.getInvoiceAmount());
					} else {
						amtInvoicedToDt = BigDecimal.ZERO;
					}
					if (invoice.getInvoiceStatus().getDescription().equals("Paid")) {
						amtPaidToDt = amtPaidToDt.add(invoice.getInvoiceAmount());
					} else {
						amtPaidToDt = BigDecimal.ZERO;
					}
				}
			}
		}
		return amtInvoicedToDt;
	}

	public void setAmtInvoicedToDt(BigDecimal amtInvoicedToDt) {
		this.amtInvoicedToDt = amtInvoicedToDt;
	}

	public BigDecimal getInspectionCompleted() {
		inspectionCompleted = new BigDecimal(0);
		int count = 0;
		for(Inspection inspection : inspections) {
			if(inspection.getInspectionStatus() != null && inspection.getInspectionStatus().getDescription().equals("Passed")) {
				count++;
			}
		}
		if(count > 0 && inspections.size() > 0) {
			inspectionCompleted = new BigDecimal(count).divide(new BigDecimal(inspections.size()));
		}
		
		return inspectionCompleted;
	}

	public void setInspectionCompleted(BigDecimal inspectionCompleted) {
		this.inspectionCompleted = inspectionCompleted;
	}

	public BigDecimal getAmtPaidToDt() {
		return amtPaidToDt;
	}

	public void setAmtPaidToDt(BigDecimal amtPaidToDt) {
		this.amtPaidToDt = amtPaidToDt;
	}

	public boolean isShowAsBuiltStatus() {
		return showAsBuiltStatus;
	}

	public void setShowAsBuiltStatus(boolean showAsBuiltStatus) {
		this.showAsBuiltStatus = showAsBuiltStatus;
	}

	public boolean isAsBuiltDisable() {
		return asBuiltDisable;
	}

	public void setAsBuiltDisable(boolean asBuiltDisable) {
		this.asBuiltDisable = asBuiltDisable;
	}

	public int getColSpan() {
		return colSpan;
	}

	public void setColSpan(int colSpan) {
		this.colSpan = colSpan;
	}

	public boolean isEditAsBuilt() {
		return editAsBuilt;
	}

	public void setEditAsBuilt(boolean editAsBuilt) {
		this.editAsBuilt = editAsBuilt;
	}

	public boolean isRenderAsBuiltChkBox() {
		return renderAsBuiltChkBox;
	}

	public void setRenderAsBuiltChkBox(boolean renderAsBuiltChkBox) {
		this.renderAsBuiltChkBox = renderAsBuiltChkBox;
	}

	public Integer getStakingInspectionStatus() {
		return stakingInspectionStatus;
	}

	public void setStakingInspectionStatus(Integer stakingInspectionStatus) {
		this.stakingInspectionStatus = stakingInspectionStatus;
	}

	public Integer getStakingInvoiceStatus() {
		return stakingInvoiceStatus;
	}

	public void setStakingInvoiceStatus(Integer stakingInvoiceStatus) {
		this.stakingInvoiceStatus = stakingInvoiceStatus;
	}

	public boolean isRenderAddStationUnit() {
		return renderAddStationUnit;
	}

	public void setRenderAddStationUnit(boolean renderAddStationUnit) {
		this.renderAddStationUnit = renderAddStationUnit;
	}

	/*public List<Boolean> getAsBuiltColList() {
		return asBuiltColList;
	}

	public void setAsBuiltColList(List<Boolean> asBuiltColList) {
		this.asBuiltColList = asBuiltColList;
	}
*/
	public Integer getTempStakingInspectionStatus() {
		return tempStakingInspectionStatus;
	}

	public void setTempStakingInspectionStatus(Integer tempStakingInspectionStatus) {
		this.tempStakingInspectionStatus = tempStakingInspectionStatus;
	}

	public Integer getTempStakingInvoiceStatus() {
		return tempStakingInvoiceStatus;
	}

	public void setTempStakingInvoiceStatus(Integer tempStakingInvoiceStatus) {
		this.tempStakingInvoiceStatus = tempStakingInvoiceStatus;
	}

	public int getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(int totalUnits) {
		this.totalUnits = totalUnits;
	}

	public int getInspPassed() {
		return inspPassed;
	}

	public void setInspPassed(int inspPassed) {
		this.inspPassed = inspPassed;
	}

	public int getUnitsRejected() {
		return unitsRejected;
	}

	public void setUnitsRejected(int unitsRejected) {
		this.unitsRejected = unitsRejected;
	}

	public int getNotInspected() {
		return notInspected;
	}

	public void setNotInspected(int notInspected) {
		this.notInspected = notInspected;
	}

	public Timestamp getInspectionDt() {
		return inspectionDt;
	}

	public void setInspectionDt(Timestamp inspectionDt) {
		this.inspectionDt = inspectionDt;
	}

	public Integer getAsBuiltActiveTab() {
		return asBuiltActiveTab;
	}

	public void setAsBuiltActiveTab(Integer asBuiltActiveTab) {
		this.asBuiltActiveTab = asBuiltActiveTab;
	}

	public boolean isRenderBackToInspTab() {
		return renderBackToInspTab;
	}

	public void setRenderBackToInspTab(boolean renderBackToInspTab) {
		this.renderBackToInspTab = renderBackToInspTab;
	}

	public boolean isRenderBackToInspSearch() {
		return renderBackToInspSearch;
	}

	public void setRenderBackToInspSearch(boolean renderBackToInspSearch) {
		this.renderBackToInspSearch = renderBackToInspSearch;
	}

	public Inspection getInspection() {
		return inspection;
	}

	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}

	public String getInspectedBy() {
		return inspectedBy;
	}

	public void setInspectedBy(String inspectedBy) {
		this.inspectedBy = inspectedBy;
	}

	public WorkFlow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(WorkFlow workflow) {
		this.workflow = workflow;
	}

	public List<WorkOrderAggVw> getWorkOrderAggVw() {
		return workOrderAggVw;
	}

	public void setWorkOrderAggVw(List<WorkOrderAggVw> workOrderAggVw) {
		this.workOrderAggVw = workOrderAggVw;
	}

	public int getWfOutput() {
		return wfOutput;
	}

	public void setWfOutput(int wfOutput) {
		this.wfOutput = wfOutput;
	}

	public String getInvoiceComment() {
		return invoiceComment;
	}

	public void setInvoiceComment(String invoiceComment) {
		this.invoiceComment = invoiceComment;
	}

	public boolean isDisableApprovedBtn() {
		return disableApprovedBtn;
	}

	public void setDisableApprovedBtn(boolean disableApprovedBtn) {
		this.disableApprovedBtn = disableApprovedBtn;
	}

	public List<InvoiceGLSummaryVw> getInvoiceGLSummaryVw() {
		return invoiceGLSummaryVw;
	}

	public void setInvoiceGLSummaryVw(List<InvoiceGLSummaryVw> invoiceGLSummaryVw) {
		this.invoiceGLSummaryVw = invoiceGLSummaryVw;
	}

	public BigDecimal getInvoiceGLSummaryVwTotal() {
		return invoiceGLSummaryVwTotal;
	}

	public void setInvoiceGLSummaryVwTotal(BigDecimal invoiceGLSummaryVwTotal) {
		this.invoiceGLSummaryVwTotal = invoiceGLSummaryVwTotal;
	}

	public List<InvoiceDetail> getInvoiceDetail() {
		return invoiceDetail;
	}

	public void setInvoiceDetail(List<InvoiceDetail> invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}

	public List<AssemblyAdhocVw> getAssemblyunits() {
		return assemblyunits;
	}

	public void setAssemblyunits(List<AssemblyAdhocVw> assemblyunits) {
		this.assemblyunits = assemblyunits;
	}

	public String getCr() {
		return cr;
	}

	public void setCr(String cr) {
		this.cr = cr;
	}

	public String getTransfer() {
		return transfer;
	}

	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}

	public String getEnergized() {
		return energized;
	}

	public void setEnergized(String energized) {
		this.energized = energized;
	}

	public List<InvoiceStatus> getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(List<InvoiceStatus> invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public List<Voucher> getVouchers() {
		return vouchers;
	}

	public void setVouchers(List<Voucher> vouchers) {
		this.vouchers = vouchers;
	}

	public List<Voucher> getInspectedVouchers() {
		return inspectedVouchers;
	}

	public void setInspectedVouchers(List<Voucher> inspectedVouchers) {
		this.inspectedVouchers = inspectedVouchers;
	}

	public List<StakingSheetDetail> getInspStakingSheetDetail() {
		return inspStakingSheetDetail;
	}

	public void setInspStakingSheetDetail(List<StakingSheetDetail> inspStakingSheetDetail) {
		this.inspStakingSheetDetail = inspStakingSheetDetail;
	}

	public List<StakingSheetDetail> getInvoiceStakingSheetDetail() {
		return invoiceStakingSheetDetail;
	}

	public void setInvoiceStakingSheetDetail(List<StakingSheetDetail> invoiceStakingSheetDetail) {
		this.invoiceStakingSheetDetail = invoiceStakingSheetDetail;
	}

	public List<Voucher> getInvoiceVouchers() {
		return invoiceVouchers;
	}

	public void setInvoiceVouchers(List<Voucher> invoiceVouchers) {
		this.invoiceVouchers = invoiceVouchers;
	}

	public List<Voucher> getSelectedInvoiceVouchers() {
		return selectedInvoiceVouchers;
	}

	public void setSelectedInvoiceVouchers(List<Voucher> selectedInvoiceVouchers) {
		this.selectedInvoiceVouchers = selectedInvoiceVouchers;
	}

	public List<Voucher> getInvoiceApprovalVouchers() {
		return invoiceApprovalVouchers;
	}

	public void setInvoiceApprovalVouchers(List<Voucher> invoiceApprovalVouchers) {
		this.invoiceApprovalVouchers = invoiceApprovalVouchers;
	}

	public List<Voucher> getVoucherSubmitInvoiceApproval() {
		return voucherSubmitInvoiceApproval;
	}

	public void setVoucherSubmitInvoiceApproval(List<Voucher> voucherSubmitInvoiceApproval) {
		this.voucherSubmitInvoiceApproval = voucherSubmitInvoiceApproval;
	}

	public boolean isRenderBackToInvoiceTab() {
		return renderBackToInvoiceTab;
	}

	public void setRenderBackToInvoiceTab(boolean renderBackToInvoiceTab) {
		this.renderBackToInvoiceTab = renderBackToInvoiceTab;
	}

	public List<StakingSheetDetail> getInvApprovalStakingSheetDet() {
		return invApprovalStakingSheetDet;
	}

	public void setInvApprovalStakingSheetDet(List<StakingSheetDetail> invApprovalStakingSheetDet) {
		this.invApprovalStakingSheetDet = invApprovalStakingSheetDet;
	}

	public List<InspectionStatus> getNewInspStatuses() {
		return newInspStatuses;
	}

	public void setNewInspStatuses(List<InspectionStatus> newInspStatuses) {
		this.newInspStatuses = newInspStatuses;
	}

	public List<Voucher> getNewInspVouchers() {
		return newInspVouchers;
	}

	public void setNewInspVouchers(List<Voucher> newInspVouchers) {
		this.newInspVouchers = newInspVouchers;
	}

	public List<StakingSheetDetail> getAsBuiltStakingSheetDetail() {
		return asBuiltStakingSheetDetail;
	}

	public void setAsBuiltStakingSheetDetail(List<StakingSheetDetail> asBuiltStakingSheetDetail) {
		this.asBuiltStakingSheetDetail = asBuiltStakingSheetDetail;
	}

	public List<Vendor> getVendors() {
		return vendors;
	}

	public void setVendors(List<Vendor> vendors) {
		this.vendors = vendors;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public List<AsBuiltStatus> getAddStationUnitAsBuiltStatuses() {
		return addStationUnitAsBuiltStatuses;
	}

	public void setAddStationUnitAsBuiltStatuses(List<AsBuiltStatus> addStationUnitAsBuiltStatuses) {
		this.addStationUnitAsBuiltStatuses = addStationUnitAsBuiltStatuses;
	}

	public BigDecimal getAuSubTotal() {

		return auSubTotal;
	}

	public void setAuSubTotal(BigDecimal auSubTotal) {
		this.auSubTotal = auSubTotal;
	}

	public BigDecimal getAuAmount() {
		return auAmount;
	}

	public void setAuAmount(BigDecimal auAmount) {
		this.auAmount = auAmount;
	}

	public BigDecimal getVoucherSubTotal() {
		return voucherSubTotal;
	}

	public void setVoucherSubTotal(BigDecimal voucherSubTotal) {
		this.voucherSubTotal = voucherSubTotal;
	}

	public BigDecimal getVoucherAmount() {
		return voucherAmount;
	}

	public void setVoucherAmount(BigDecimal voucherAmount) {
		this.voucherAmount = voucherAmount;
	}

	public String getInspectionFilter() {
		return inspectionFilter;
	}

	public void setInspectionFilter(String inspectionFilter) {
		this.inspectionFilter = inspectionFilter;
	}

	/*public Timestamp getWorkEventDt() {
		return workEventDt;
	}

	public void setWorkEventDt(Timestamp workEventDt) {
		this.workEventDt = workEventDt;
	}*/
}
