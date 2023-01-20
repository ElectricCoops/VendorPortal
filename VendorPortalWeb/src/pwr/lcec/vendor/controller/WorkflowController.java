package pwr.lcec.vendor.controller;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.primefaces.PrimeFaces;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.Visibility;
import pwr.lcec.vendor.controller.WorkflowController;
import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendorportal.entity.custom.AsBuiltStatus;
import pwr.lcec.vendorportal.entity.custom.AsBuiltSummaryVw;
import pwr.lcec.vendorportal.entity.custom.AssemblyAdhocVw;
import pwr.lcec.vendorportal.entity.custom.Inspection;
import pwr.lcec.vendorportal.entity.custom.InspectionDetailVw;
import pwr.lcec.vendorportal.entity.custom.InspectionStatus;
import pwr.lcec.vendorportal.entity.custom.InspectionUnlock;
import pwr.lcec.vendorportal.entity.custom.Invoice;
import pwr.lcec.vendorportal.entity.custom.InvoiceDetail;
import pwr.lcec.vendorportal.entity.custom.InvoiceGLSummaryVw;
import pwr.lcec.vendorportal.entity.custom.InvoiceStatus;
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
import pwr.lcec.vendorportal.entity.custom.WorkOrderAggVw;
import pwr.lcec.vendorportal.interfaces.InspectionLocal;
import pwr.lcec.vendorportal.interfaces.InvoiceLocal;
import pwr.lcec.vendorportal.interfaces.IvueLocal;
import pwr.lcec.vendorportal.interfaces.UserManagementLocal;
import pwr.lcec.vendorportal.interfaces.WorkFlowLocal;
import pwr.lcec.vendorportal.entity.sec.UserTbl;

public class WorkflowController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(WorkflowController.class);

	private final String STAKING = "/pages/staking?faces-redirect=true";
	private final String STAKING_INVOICE = "SS";
	private final String INSPECTION_DETAIL = "inspectiondetail?faces-redirect=true";
	private final String WOSEARCH = "wosearch?faces-redirect=true";
	private final String NEW_INVOICE = "newinvoice?faces-redirect=true";
	private final String INVOICE_APPROVAL = "invoiceapproval?faces-redirect=true";
	private final String INVOICE_SEARCH = "invoicesearch?faces-redirect=true";
	private final String APPROVED = "Approved";
	private final String INVOICE_DETAIL = "invoicedetail?faces-redirect=true";

	@EJB
	private WorkFlowLocal workflowService;

	@EJB
	private InspectionLocal inspectionService;
	@EJB
	private InvoiceLocal invoiceService;
	@EJB
	private IvueLocal ivueService;
	@EJB
	private UserManagementLocal userManagementService;
	
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
	private List<StakingSheetDetailGui> asBuiltStakingSheetDetailGui;
	private List<StakingSheetDetail> invoiceStakingSheetDetail;
	private StakingSheetDetailGui selectedStakingSheetDetail = new StakingSheetDetailGui();
	private List<StakingSheetDetail> selectedStakingSheetDetails;
	private List<StakingSheetDetailGui> filteredStakingSheetDetail;
	private List<StakingSheetDetail> invoicedDetail;
	private List<StakingSheetDetailGui> inspStakingSheetDetail;
	private List<Inspection> inspections;
	private Inspection inspection;
	private List<InspectionDetailVw> inspectionDetailVw;
	private InspectionDetailVw selectedInspectionDetailVw;
	private List<Invoice> invoices;
	private Invoice invoice;
	private List<WorkOrderAggVw> workOrderAggVw;
	private List<InvoiceGLSummaryVw> invoiceGLSummaryVw;
	private List<InvoiceDetail> invoiceDetail;
	private List<AssemblyAdhocVw> assemblyunits;
	private List<VoucherGui> vouchers;
	private List<VoucherGui> newInspVouchers;
	private List<VoucherGui> inspectedVouchers;
	private List<VoucherGui> invoiceVouchers;
	private List<VoucherGui> selectedInvoiceVouchers;
	private List<VoucherGui> invoiceApprovalVouchers;
	private List<VoucherGui> voucherSubmitInvoiceApproval;
	private List<StakingSheetDetail> invApprovalStakingSheetDet;
	private List<BigDecimal> selectedItems = new ArrayList<BigDecimal>();

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
	private boolean cancelInspOnBack = false;
	private boolean continueInspection = false;
	private List<BigDecimal> distinctStation;
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

	private Integer stakingInspectionStatus;

	private Integer stakingInvoiceStatus;

	private int totalUnits;

	private int inspPassed;

	private int unitsRejected;
	private int notInspected;
	private Timestamp inspectionDt;
	private Timestamp workEventDt;
	private Integer asBuiltActiveTab;
	private int wfOutput;
	private String invoiceComment;
	private boolean inspectionFilter;
	private boolean inspectionVoucherFilter;
	private String source;
	private boolean asBuiltShowAllBool = false;
	private VoucherGui selectedVoucher = new VoucherGui();

	@PostConstruct
	public void init() {
		getAllWorkEvenStatus();
		findAsBuiltStatus();
		findInspectionStatus();
		findVendors();
	}

	public void findWorkFlows() {
		workflows = workflowService.searchWorkflows(tempWoId, tempSoId, tempwoStatus, tempStakingInspectionStatus, tempStakingInvoiceStatus, util.getWrkGrp(), vendor);
	}

	public void resetWorkOrderSearch() {
		workflows = new ArrayList<WorkFlowSearch_VW>();

		clearInputs();
		
		/*asBuiltStakingSheetDetailGui = new ArrayList<StakingSheetDetailGui>();
		invoiceStakingSheetDetail = new ArrayList<StakingSheetDetail>();
		selectedStakingSheetDetail = new StakingSheetDetailGui();
		selectedStakingSheetDetails = new ArrayList<StakingSheetDetail>();
		filteredStakingSheetDetail = new ArrayList<StakingSheetDetailGui>();
		invoicedDetail = new ArrayList<StakingSheetDetail>();
		inspStakingSheetDetail = new ArrayList<StakingSheetDetailGui>();*/
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

	public String onloadStakingJob() {
		asBuiltActiveTab = Integer.valueOf(0);

		return findStakingDetailByWoId();
	}

	public String findStakingDetailByWoId() {
		//workflow = workflowService.findWorkFlowbyWoId(woId, util.getWrkGrp());
		workflow = workflowService.findWorkflowByWfId(wfId, util.getWrkGrp());

		if (workflow != null) {

			woStatus = workflow.getWorkEventStatus().getDescription();
			//wfId = workflow.getWorkFlowId();
			soId = workflow.getServiceOrderId();
			workEventDt = workflow.getWorkEventDt();

			try {
				workOrderAggVw = workflowService.getByWorkflowId(wfId);
			} catch (Exception e) {
				logger.error(e);
				facesError(e.getMessage());
			}

			stakingSheet = workflowService.getStakingSheetByWoId(woId);

			if (stakingSheet == null) {

				facesError("No Assembly Units available for selected Work Order.");
				return null;
			}

			woName = stakingSheet.getWorkOrderDescription();

			/*Remove from here to the next comment section*/
			/*try {
				stakingSheetDetail = workflowService.getStakingSheetDetailById(woId);
			} catch (Exception e) {
				logger.error(e);
				facesError(e.getMessage());
			}

			if (!CollectionUtils.isEmpty(stakingSheetDetail)) {

				source = ((StakingSheetDetail) stakingSheetDetail.stream()
						.filter(s -> (s.getAssemblyQuantity() != 0)).findFirst().get()).getStakingSource();

				asBuiltStakingSheetDetail = stakingSheetDetail;

				Collections.sort(asBuiltStakingSheetDetail, new Comparator<StakingSheetDetail>() {
					public int compare(StakingSheetDetail o1, StakingSheetDetail o2) {
						return o1.getAssemblyGuid().compareToIgnoreCase(o2.getAssemblyGuid());
					}
				});

				findDistinctStation(asBuiltStakingSheetDetail);
			}*/
			/*To here */
			
			asBuiltStakingSheetDetailGui = new ArrayList<StakingSheetDetailGui>();
			
			asBuiltStakingSheetDetailGui = findStakingDetail(woId);
			
			if(!asBuiltStakingSheetDetailGui.isEmpty()) {
				
				findDistinctStationGui(asBuiltStakingSheetDetailGui);
				
				try {
					asBuiltSummaryVw = workflowService.getStakingSheetByGrpSummary(woId);
				} catch (Exception e) {
					logger.error(e);
					facesError(e.getMessage());
				}

				findAsBuiltStatus();

				setAsBuiltDisable(true);
				showAsBuiltStatus = true;
				editAsBuilt = true;
				renderAsBuiltChkBox = false;
				renderAddStationUnit = false;
				
			}else {
				facesError("No Assembly Units available for selected Work Order.");
			}
			logger.debug("SSDetail Rows: " + asBuiltStakingSheetDetailGui.size());
			
		}

		return STAKING;
	}
	
	public List<StakingSheetDetailGui> findStakingDetail(String woId) {
		filteredStakingSheetDetail = new ArrayList<StakingSheetDetailGui>();
		logger.debug("findStakingDetail workorderid: " + woId);
		
		List<StakingSheetDetailGui> guiList = new ArrayList<StakingSheetDetailGui>();
		guiList = workflowService.getStakingSheetDetailGuiByWOId(woId);
		
		//logger.debug("findStakingDetail workorderid from asBuiltStakingSheetDetailGui: " + guiList.get(0).getWorkOrderId());
		
		
		Comparator<StakingSheetDetailGui> compareSsd = Comparator.comparing(StakingSheetDetailGui::getStationDescription)
																.thenComparing(StakingSheetDetailGui::getAssemblyGuid)
																.thenComparing(StakingSheetDetailGui::getStakingSheetDetailId);
		
		guiList = guiList.stream().sorted(compareSsd).collect(Collectors.toList());
		
		/*if(!asBuiltStakingSheetDetailGui.isEmpty()) {
			
			//asBuiltStakingSheetDetailGui
			
			Collections.sort(asBuiltStakingSheetDetailGui, new Comparator<StakingSheetDetailGui>() {
				@Override
				public int compare(StakingSheetDetailGui o1, StakingSheetDetailGui o2) {
					return o1.getAssemblyGuid().compareToIgnoreCase(o2.getAssemblyGuid());
				}
			});
			
		}*/
		
		return guiList;
	}

	public String exitAsBuiltEditMode() {
		WorkFlow workflow = workflowService.getWorkFlowByWorkOrderId(woId);

		if (workflow != null) {
			if (asBuiltStakingSheetDetailGui.stream().allMatch(s -> s.getAsBuiltStatus().equals("Not Started"))) {
				workflow.setOverallAsBuiltStatusId(1);
			} else if (asBuiltStakingSheetDetailGui.stream().allMatch(s -> !(!s.getAsBuiltStatus().equals("Completed") && !s.getAsBuiltStatus().equals("Appealed")))) {
				workflow.setOverallAsBuiltStatusId(3);
				workflow.setOverallInspectionStatusId(2);
			} else {
				workflow.setOverallAsBuiltStatusId(2);
			}

			workflowService.updateWorkflowTask(workflow);

			int wfTaskId = 0;
			try {
				wfTaskId = workflowService.updateOverallAsbuiltStatusId(woId);
			} catch (Exception e) {
				logger.error(e);
				facesError(e.getMessage());
			}

			if (wfTaskId != 0 || wfTaskId != 1) {
				try {
					workflowService.updateWorkflowTask(Integer.valueOf(wfTaskId), "COMP");
				} catch (Exception e) {
					logger.error(e);
					facesError(e.getMessage());
				}
			}
		}
		return findStakingDetailByWoId();
	}
	
	
	public void onStakingDetailCellEdit(StakingSheetDetailGui gui) {

		boolean update = false;
		StakingSheetDetail ssd = workflowService.getStakingSheetDetailBySSDId(gui.getStakingSheetDetailId());
		logger.debug("gui stake WorkOrderId: " + gui.getWorkOrderId() + " AsBuiltStatusId: " + gui.getAsBuiltStatusId());
		
		logger.debug("AU ::  " + gui.getAssemblyGuid() + " SSDID:  " + gui.getStakingSheetDetailId());
		
		Optional<StakingSheetDetailGui> selected = asBuiltStakingSheetDetailGui.stream().filter(g -> g.getStakingSheetDetailId().equals(gui.getStakingSheetDetailId())).findFirst();
		
		StakingSheetDetailGui temp = selected.get();
		
		logger.debug("AU ::  " + temp.getAssemblyGuid() + " SSDID:  " + temp.getStakingSheetDetailId());
		int index = asBuiltStakingSheetDetailGui.indexOf(selected.get());
		logger.debug("Index of selected Item in list:  " + index);
		
		if(ssd != null) {
			logger.debug("ssd is not null: " + ssd.getStakingSheetDetailId() + " AsBuiltStatusId From DB: " + ssd.getAsBuiltStatusId());
			if(ssd.getAsBuiltQuantity() != gui.getAsBuiltQuantity()) {
				ssd.setAsBuiltQuantity(gui.getAsBuiltQuantity());
				update = true;
			}
			
			if(gui.getAsBuiltStatusId() == 1) {//Not Started
				ssd.setAsBuiltDt(null);
				ssd.setAsBuiltBy(null);
				//ssd.setAsBuiltQuantity(gui.getAsBuiltQuantity());
				ssd.setAsBuiltStatusId(gui.getAsBuiltStatusId());
				ssd.setAsBuiltComments(gui.getAsBuiltComments());
				ssd.setCurrentInspectionDetailStatusId(Integer.valueOf(1));//Not Inspected
				update = true;
			}else if(gui.getAsBuiltStatusId() == 3) {//Completed
				//if(ssd.getAsBuiltDt() == null) {
					ssd.setAsBuiltDt(util.currentDtTm());
					ssd.setAsBuiltBy(util.getCurrentUser());
				//}
				
				ssd.setAsBuiltStatusId(gui.getAsBuiltStatusId());
				ssd.setAsBuiltComments(gui.getAsBuiltComments());
				ssd.setCurrentInspectionDetailStatusId(Integer.valueOf(2));//Ready for Inspection
				update = true;
			}else if(gui.getAsBuiltStatusId() == 4) {//Rejected
				ssd.setAsBuiltStatusId(gui.getAsBuiltStatusId());
				ssd.setAsBuiltComments(gui.getAsBuiltComments());
				
				update = true;
			}else if(gui.getAsBuiltStatusId() == 6 && StringUtils.isBlank(gui.getAsBuiltComments())) {
				facesError("As-Built comment required if status is 'Appealed'.");	
				PrimeFaces.current().executeScript("PF('STAKINGSHEET_DETAIL_TABLE:asBuiltComments:asBuiltCommentEditId').click();");
				ssd.setAsBuiltStatusId(gui.getAsBuiltStatusId());
				update = true;
			}else if(gui.getAsBuiltStatusId() == 6 && !StringUtils.isBlank(gui.getAsBuiltComments())) {
				ssd.setAsBuiltStatusId(gui.getAsBuiltStatusId());
				ssd.setAsBuiltComments(gui.getAsBuiltComments());
				update = true;
			}
			
			logger.debug("Update flag set too: " + update);

			if(update) {
				try {
					workflowService.updateStakingSheetDetail(ssd);
					logger.debug("AsBuiltStatusId that should be saved to BD: " + ssd.getAsBuiltStatusId());
					workflowService.updateAsBuiltAmount(ssd.getStakingSheetDetailId(), workEventDt);
				} catch (Exception e) {
					logger.error(e);
					facesError("Sorry the record was not saved.");
				}
				logger.debug("asBuiltStakingSheetDetailGui before status cell edit: " + asBuiltStakingSheetDetailGui.size() + " WOrdOrderID: " + woId);
				asBuiltStakingSheetDetailGui = new ArrayList<StakingSheetDetailGui>();
				filteredStakingSheetDetail = new ArrayList<StakingSheetDetailGui>();
				//asBuiltStakingSheetDetailGui = findStakingDetail(gui.getWorkOrderId());
				
				asBuiltStakingSheetDetailGui = findStakingDetail(woId);
				//DataModel<StakingSheetDetailGui> guiDataModel = new ArrayDataModel<StakingSheetDetailGui>((StakingSheetDetailGui[]) asBuiltStakingSheetDetailGui.toArray());
				
				logger.debug("asBuiltStakingSheetDetailGui after status cell edit: " + asBuiltStakingSheetDetailGui.size());
				asBuiltStakingSheetDetailGui = asBuiltStakingSheetDetailGui.stream().filter(item -> !(item.getAsBuiltStatus() != null && item.getCurrentInspectionDetailStatus().equals(APPROVED))).collect(Collectors.toList());
				logger.debug("asBuiltStakingSheetDetailGui after filter: " + asBuiltStakingSheetDetailGui.size());
			}
			
		}else {
			facesError("Assembly Unit not found");
		}
		
		
		/*PrimeFaces.current().ajax().update("stakingTabForm:STAKINGSHEET_DETAIL_TABLE:" + index + ":asBuiltQuantity");
		PrimeFaces.current().ajax().update("stakingTabForm:STAKINGSHEET_DETAIL_TABLE:" + index + ":asBuiltStatusId");
		PrimeFaces.current().ajax().update("stakingTabForm:STAKINGSHEET_DETAIL_TABLE:" + index + ":asBuiltComments");
		*/
	}

	public List<BigDecimal> findDistinctStationGui(List<StakingSheetDetailGui> ssd) {
		distinctStation = new ArrayList<BigDecimal>();

		if (ssd != null) {
			for (StakingSheetDetailGui det : ssd) {
				distinctStation.add(det.getStationDescription());
			}
			distinctStation = distinctStation.stream().distinct().collect(Collectors.toList());
			Collections.sort(distinctStation);
		}
		return distinctStation;
	}
	
	//TEMP can remove laster.
	public List<BigDecimal> findDistinctStation(List<StakingSheetDetail> ssd) {
		distinctStation = new ArrayList<BigDecimal>();

		if (ssd != null) {
			for (StakingSheetDetail det : ssd) {
				distinctStation.add(det.getStationDescription());
			}
			distinctStation = distinctStation.stream().distinct().collect(Collectors.toList());
			Collections.sort(distinctStation);
		}
		return distinctStation;
	}

	public boolean filterStationFunction(Object value, Object filter, Locale locale) {
		if (value == null || !(value instanceof BigDecimal)) {
			return true;
		}

		BigDecimal valueInRow = (BigDecimal) value;

		if (selectedItems == null || selectedItems.size() == 0) {
			return true;
		}

		for (int i = 0; i < selectedItems.size(); i++) {
			if (valueInRow.compareTo(new BigDecimal(String.valueOf(selectedItems.get(i)))) == 0) {
				return true;
			}
		}

		return false;
	}

	public void findInspections() {
		inspPassed = 0;
		unitsRejected = 0;
		notInspected = 0;

		if (woId != null) {
			inspectionService.removeNullInspections(woId);
		}

		setTotalUnits(asBuiltStakingSheetDetailGui.size());
		for (StakingSheetDetailGui detail : asBuiltStakingSheetDetailGui) {
			if (detail.getCurrentInspectionDetailStatus() != null) {
				if (detail.getCurrentInspectionDetailStatus().equals(APPROVED)) {
					inspPassed++;
				}
				if (detail.getCurrentInspectionDetailStatus().equals("Rejected")) {
					unitsRejected++;
				}
			}
			if (detail.getCurrentInspectionDetailStatus().equals("Ready for Inspection")
					|| detail.getCurrentInspectionDetailStatus().equals("Not Inspected")) {
				notInspected++;
			}
		}
		try {
			inspections = inspectionService.getInspectionByWoId(woId);
			//inspections.size();

		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}
	}

	public void findAsBuiltStatus() {
		asBuiltStatuses = workflowService.getAsBuiltStatus();

		addStationUnitAsBuiltStatuses = asBuiltStatuses.stream().filter(
				item -> !(!item.getDescription().equals("Completed") && !item.getDescription().equals("Not Started")))
				.collect(Collectors.toList());

		asBuiltStatuses = asBuiltStatuses.stream()
				.filter(item -> !(!item.getDescription().equals("Completed")
						&& !item.getDescription().equals("Rejected") && !item.getDescription().equals("Appealed")
						&& !item.getDescription().equals("Not Started")))
				.collect(Collectors.toList());

		asBuiltStatuses = asBuiltStatuses.stream().sorted(Comparator.comparing(AsBuiltStatus::getDescription)).collect(Collectors.toList());
	}

	public List<AsBuiltStatus> findFilteredAsBuiltStatuses(int asBuiltStatusId) {
		List<AsBuiltStatus> abs = workflowService.getAsBuiltStatus();
		
		//logger.info("AsBuiltStatusId" + asBuiltStatusId);

		if (asBuiltStatusId == 1)
			asBuiltStatuses = abs.stream().filter(s -> !(!s.getDescription().equals("Completed") && !s.getDescription().equals("Not Started"))).collect(Collectors.toList());
		if (asBuiltStatusId == 3)
			asBuiltStatuses = abs.stream().filter(s -> !(!s.getDescription().equals("Completed") && !s.getDescription().equals("Not Started") && !s.getDescription().equals("Rejected"))).collect(Collectors.toList());
		if (asBuiltStatusId == 4)
			asBuiltStatuses = abs.stream().filter(s -> !(!s.getDescription().equals("Completed") && !s.getDescription().equals("Appealed") && !s.getDescription().equals("Rejected"))).collect(Collectors.toList());
		if (asBuiltStatusId == 6) {
			asBuiltStatuses = abs.stream().filter(s -> !(!s.getDescription().equals("Appealed") && !s.getDescription().equals("Rejected"))).collect(Collectors.toList());
		}
		return asBuiltStatuses;
	}

	public void findInvoices() {
		try {
			invoices = workflowService.getInvoiceByWoId(woId);
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}
	
	}

	public int getInspectorId() {
		StringBuilder sb = new StringBuilder();
		try {
			UserTbl user = userManagementService.findUserByPrincipal(getCurrentUser());

			sb.append(user.getFirstName());
			sb.append(" ");
			sb.append(user.getLastName());
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}

		int inspectorId = 0;

		try {
			inspectorId = inspectionService.getResourceId(sb.toString());
			if (inspectorId == 0) {
				logger.warn(
						"NoResultException: " + util.getCurrentUser() + " does not exist in the Resource Table.");
				facesError("User does not exist in Resource Table.");
				return inspectorId;
			}
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		return inspectorId;
	}
	
	public String newInspection() {
		continueInspection = false;

		Inspection inspection = new Inspection();

		inspection.setInspectionDt(currentDtTm());
		inspection.setInspectedBy(getInspectorId());
		inspection.setInspectionStatusId(1);
		inspection.setWorkOrderId(woId);
		inspection.setWorkFlowId(wfId);
		inspection.setServiceOrderId(soId);
		inspection.setInspectionType(STAKING_INVOICE);

		try {
			inspectionId = inspectionService.insertInspection(inspection);
			logger.debug("New Inspection method called - InspectionId: " + inspectionId);
			inspection = inspectionService.getInspectionStatusId(inspectionId);
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}

		inspectionDt = inspection.getInspectionDt();
		inspectedBy = inspection.getResource().getResourceName();
		inspectionDetStatus = inspection.getInspectionStatus().getStatus();
		
		inspectionFilter = false;
	    inspectionVoucherFilter = false;

		return findInspectionDetails();
	}

	public String continueInProgressInspection() {
		continueInspection = true;

		try {
			inspection = inspectionService.getInspectionStatusId(inspectionId);
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}

		inspectionDt = inspection.getInspectionDt();
		inspectedBy = inspection.getResource().getResourceName();
		inspectionDetStatus = inspection.getInspectionStatus().getStatus();
		
		inspectionFilter = false;
	    inspectionVoucherFilter = false;

		return findInspectionDetails();
	}
	
	public String findInspectionDetails() {
	    
		showSubmitInspectionBtn = false;
		
		try {
			inspStakingSheetDetail = findStakingDetail(woId);
		
			if(!inspectionFilter) {
				List<InspectionDetailVw> idList = inspectionService.getInspectionDetailByInspId(inspectionId);
				
				inspStakingSheetDetail = inspStakingSheetDetail.stream()
				        .filter(item -> ((item.getAsBuiltStatus().equals("Completed") || 
				          item.getAsBuiltStatus().equals("Appealed")) && (
				          item.getCurrentInspectionDetailStatus().equals("Ready for Inspection") || 
				          item.getCurrentInspectionDetailStatus().equals("Rejected"))))
				        .collect(Collectors.toList());
				
				idList.stream().forEach(i -> {
					
					StakingSheetDetailGui ssdg = workflowService.getStakingSheetDetailGuiBySSDId(i.getStakingSheetDetailId());
					
					if(!inspStakingSheetDetail.stream().anyMatch(in -> in.getStakingSheetDetailId().equals(ssdg.getStakingSheetDetailId()))) {
						inspStakingSheetDetail.add(ssdg);
					}
					
				});
				
				Comparator<StakingSheetDetailGui> compareSsd = Comparator.comparing(StakingSheetDetailGui::getStationDescription)
						.thenComparing(StakingSheetDetailGui::getAssemblyGuid).thenComparing(StakingSheetDetailGui::getStakingSheetDetailId);
				
				inspStakingSheetDetail = inspStakingSheetDetail.stream().sorted(compareSsd).collect(Collectors.toList());
			}
			
			inspectionDetailVoucherFilter();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			facesError("Sorry we could not find the inspection details.");
			
		}
		    
	    if (inspStakingSheetDetail.size() <= 0 && newInspVouchers.size() <= 0) {
	      facesError("No Assembly Unit or Vouchers available for inspection!");
	      return null;
	    } 
	
	    newInspStatuses = inspStatuses.stream().filter(item -> !(!item.getStatus().equals("Rejected") && 
	        !item.getStatus().equals("Ready for Inspection") && !item.getStatus().equals(APPROVED))).collect(Collectors.toList());
	    inspHist = false;
	    newInsp = true;
	    /*inspectionFilter = false;
	    inspectionVoucherFilter = false;*/
	
	    
	    findDistinctStationGui(inspStakingSheetDetail);
		
	    return INSPECTION_DETAIL;
	}

	public String submitInspection() {
		boolean testValue1 = newInspVouchers.stream().anyMatch(
				v -> (v.getInspectionStatusId().intValue() == 5 && StringUtils.isBlank(v.getInspectionComment())));
		boolean testValue2 = inspStakingSheetDetail.stream()
				.anyMatch(i -> (i.getCurrentInspectionDetailStatusId().intValue() == 5
						&& StringUtils.isBlank(i.getCurrentInspectorDetailComments())));

		boolean testValue3 = !(!newInspVouchers.stream().anyMatch(v -> (v.getInspectionStatusId().intValue() == 5))
				&& !inspStakingSheetDetail.stream()
						.anyMatch(i -> (i.getCurrentInspectionDetailStatusId().intValue() == 5)));

		logger.debug("Voucher noneMatch: " + testValue1);
		logger.debug("AU noneMatch: " + testValue2);
		logger.debug("AnyMatch: " + testValue3);

		if (testValue3) {
			if (testValue1) {

				facesError("Inspection comment required for Rejected Voucher.");
				return null;
			}
			if (testValue2) {

				facesError("Inspection comment required for Rejected Assembly Units.");
				return null;
			}

			inspectionService.updateInspection(inspectionId, Integer.valueOf(5));
			updateOverallInspectionStatus();
		} else {

			inspectionService.updateInspection(inspectionId, Integer.valueOf(4));
			updateOverallInspectionStatus();
		}

		findInspections();

		return findStakingDetailByWoId();
	}

	public void updateOverallInspectionStatus() {
		List<VoucherGui> vch = null;
		List<StakingSheetDetail> ssd = null;

		try {
			vch = workflowService.getVouchers(woId);
			ssd = workflowService.getStakingSheetDetailById(woId);
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}

		boolean conditionFlg1 = vch.stream().allMatch(v -> v.getInspectionStatus().equals(APPROVED));
		boolean conditionFlg2 = ssd.stream().allMatch(s -> s.getInspectionStatus().getStatus().equals(APPROVED));

		boolean conditionFlg3 = vch.stream().allMatch(v -> v.getInspectionStatus().equals("Not Inspected"));
		boolean conditionFlg4 = ssd.stream().allMatch(s -> s.getInspectionStatus().getStatus().equals("Not Inspected"));

		boolean conditionFlg5 = vch.stream()
				.allMatch(v -> !(!v.getInspectionStatus().equals("Ready for Inspection")
						&& v.getInspectionStatus().equals("Not Inspected")));
		boolean conditionFlg6 = ssd.stream()
				.allMatch(s -> !(!s.getInspectionStatus().getStatus().equals("Ready for Inspection")
						&& s.getInspectionStatus().getStatus().equals("Not Inspected")));

		if (conditionFlg1 && conditionFlg2) {
			logger.debug("Update Overall Status: 4");
			workflowService.updateOverallInspectionStatus(woId, 4);
		} else if (conditionFlg3 && conditionFlg4) {
			logger.debug("Update Overall Status: 1");
			workflowService.updateOverallInspectionStatus(woId, 1);
		} else if (conditionFlg5 && conditionFlg6) {
			logger.debug("Update Overall Status: 2");
			workflowService.updateOverallInspectionStatus(woId, 2);
		} else {
			logger.debug("Update Overall Status: 3");
			workflowService.updateOverallInspectionStatus(woId, 3);
		}
	}

	public String cancelInspection() {
		if (cancelInspOnBack) {

			List<InspectionDetailVw> inspDetails = null;
			try {
				inspDetails = inspectionService.getInspectionDetailByInspId(inspectionId);
			} catch (Exception e) {
				logger.error(e);
				facesError(e.getMessage());
			}
			for (InspectionDetailVw i : inspDetails) {

				workflowService.updateStakingSheetDetailStatusCancel(i.getStakingSheetDetailId(), 2);
			}

			inspectionService.removeInspectionDetail(inspectionId);
			findInspections();

			List<VoucherGui> inspVoucher = null;
			try {
				inspVoucher = workflowService.getVoucherByInspectionId(inspectionId);
			} catch (Exception e1) {
				logger.error(e1);
				facesError(e1.getMessage());
			}
			inspVoucher.stream().forEach(v -> {
				
				Voucher vchr = workflowService.getVoucherById(v.getVoucherId());
				
				vchr.setInspectionStatusId(Integer.valueOf(2));
				vchr.setInspectionComment(null);
				vchr.setInspectionId(Integer.valueOf(0));

				try {
					workflowService.updateVoucher(vchr);
				} catch (Exception e) {
					logger.error(e);
					facesError(e.getMessage());
				}
			});

			findVouchers();
			inspectionService.removeInspection(inspectionId);
			findInspections();
		}
		return STAKING;
	}

	public String backInspectionList() {
		findInspections();

		return STAKING;
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
		} catch (Exception e) {
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
		findVouchers();

		invoiceVouchers = vouchers.stream()
				.filter(item -> (item.getInspectionStatusId().intValue() == 4
						&& (item.getInvoiceStatusId().intValue() == 1 || item.getInvoiceStatusId().intValue() == 3)))
				.collect(Collectors.toList());

		try {
			invoiceStakingSheetDetail = workflowService.getStakingSheetAvailForInv(APPROVED, woId);
			if (invoiceStakingSheetDetail.size() <= 0 && invoiceVouchers.size() <= 0) {
				facesError("No assembly available for invoice.");
				return null;
			}
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}

		vendorRefNo = "";

		return NEW_INVOICE;
	}

	public String submitInvoice() {
		if (selectedStakingSheetDetails.size() <= 0 && selectedInvoiceVouchers.size() <= 0) {
			facesError("At least one Assembly or Voucher is required to submit an invoice.");
			return null;
		}
		String guid = util.genGUID();
		int stakingSheetId = 0;

		if (selectedInvoiceVouchers.size() > 0) {
			for (VoucherGui voucher : selectedInvoiceVouchers) {
				Voucher v = workflowService.getVoucherById(voucher.getVoucherId());
				v.setSubmitGuid(guid);
				try {
					workflowService.updateVoucher(v);
				} catch (Exception e) {
					facesError(e.getMessage());
					logger.error(e.getMessage());
					//throw new ProcessException(e.getMessage());
				}
			}
			stakingSheetId = ((VoucherGui) selectedInvoiceVouchers.get(0)).getStakingSheetId().intValue();
		}

		if (selectedStakingSheetDetails.size() > 0) {
			for (StakingSheetDetail detail : selectedStakingSheetDetails) {
				detail.setInvoiceSubmitGuid(guid);
				try {
					workflowService.updateStakingSheetDetail(detail);
				} catch (Exception e) {
					facesError(e.getMessage());
					logger.error(e.getMessage());
				}
			}
			stakingSheetId = Integer
					.valueOf(((StakingSheetDetail) selectedStakingSheetDetails.get(0)).getStakingSheetId())
					.intValue();
		}

		Timestamp woEventDate = workflowService.getWorkFlowByStakingSheetId(stakingSheetId).getWorkEventDt();

		try {
			invoiceId = invoiceService.updateSubmitInvoice(guid, getCurrentUser(), vendorRefNo, STAKING_INVOICE,
					workEventDt);

			wfOutput = invoiceService.updateWorkflowCalc(Integer.valueOf(stakingSheetId), woEventDate, "INV");
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}

		updateOverallInvoiceStatus();

		findInvoices();
		
		updateServiceOrderInvoice(woId, invoiceId);

		return findInvoiceDetail(Integer.valueOf(invoiceId));
	}
	
	public void updateServiceOrderInvoice(String woId, int invoiceId) {
		
		ServiceOrder so = workflowService.getServiceOrderByWOId(woId);
		Invoice inv = invoiceService.getInvoiceById(invoiceId);
		
		so.setInvoiceId(invoiceId);
		so.setInvoiceStatusId(inv.getInvoiceStatusId());
		
		if(inv.getInvoiceStatusId() == 3 || inv.getInvoiceStatusId() == 4) {
			so.setInvoiceApprovedDt(inv.getApprovedDt());
			so.setInvoiceApprovedBy(inv.getApprovedBy());
		}else {
			so.setInvoiceApprovedDt(inv.getInvoicedDt());
			so.setInvoiceApprovedBy(inv.getInvoicedBy());
		}
		
		workflowService.updateServiceOrder(so);
		
	}

	public void updateOverallInvoiceStatus() {
		List<VoucherGui> vch = null;
		List<StakingSheetDetail> ssd = null;

		try {
			vch = workflowService.getVouchers(woId);
			ssd = workflowService.getStakingSheetDetailById(woId);
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}

		boolean conditionFlg1 = vch.stream().allMatch(v -> !(!v.getInvoiceStatus().equals(APPROVED) && v.getInvoiceStatusId().intValue() < 6));
		boolean conditionFlg2 = ssd.stream().allMatch(s -> !(!s.getInvoiceStatus().getDescription().equals(APPROVED) && s.getInvoiceStatusId().intValue() < 6));

		boolean conditionFlg3 = vch.stream().allMatch(v -> v.getInvoiceStatus().equals("Not Invoiced"));
		boolean conditionFlg4 = ssd.stream().allMatch(s -> s.getInvoiceStatus().getDescription().equals("Not Invoiced"));

		boolean conditionFlg5 = vch.stream().allMatch(v -> v.getInvoiceStatus().equals("Submitted"));
		boolean conditionFlg6 = ssd.stream().allMatch(s -> s.getInvoiceStatus().getDescription().equals("Submitted"));

		if (conditionFlg1 && conditionFlg2) {

			workflowService.updateOverallInvoiceStatus(woId, 4);
		} else if (conditionFlg3 && conditionFlg4) {

			workflowService.updateOverallInvoiceStatus(woId, 1);
		} else if (conditionFlg5 && conditionFlg6) {

			workflowService.updateOverallInvoiceStatus(woId, 2);
		} else {

			workflowService.updateOverallInvoiceStatus(woId, 5);
		}
	}

	public String backInvoiceList() {
		findInvoices();

		return findStakingDetailByWoId();
	}

	public void auInvRowSelectCheckbox(SelectEvent event) {
		BigDecimal sum = BigDecimal.ZERO;

		if (selectedStakingSheetDetails.size() > 0) {
			for (StakingSheetDetail detail : selectedStakingSheetDetails) {
				try {
					auAmount = workflowService.getAssemblyAmount(util.getWrkGrp(),
							detail.getAssemblyActionCode(), detail.getAssemblyGuid(), workEventDt);
					if (auAmount != null) {
						auAmount = auAmount.multiply(new BigDecimal(detail.getAsBuiltQuantity()));
					}
				} catch (Exception e) {
					logger.error(e);
					facesError(e.getMessage());
				}
				if (auAmount != null) {
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
			auAmount = workflowService.getAssemblyAmount(util.getWrkGrp(), actionCode, assemblyGuid,
					workEventDt);
			if (auAmount != null) {
				auAmount = auAmount.multiply(new BigDecimal(asBuiltQty));
			}
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		if (auAmount != null) {
			auSubTotal = auSubTotal.subtract(auAmount);
		}
	}

	public void auInvAllRowSelectCheckbox(ToggleSelectEvent event) {
		BigDecimal sum = BigDecimal.ZERO;

		if (selectedStakingSheetDetails.size() > 0) {
			for (StakingSheetDetail detail : selectedStakingSheetDetails) {
				try {
					auAmount = workflowService.getAssemblyAmount(util.getWrkGrp(),
							detail.getAssemblyActionCode(), detail.getAssemblyGuid(), workEventDt);
					if (auAmount != null) {
						auAmount = auAmount.multiply(new BigDecimal(detail.getAsBuiltQuantity()));
					}
				} catch (Exception e) {
					logger.error(e);
					facesError(e.getMessage());
				}
				if (auAmount != null) {
					sum = sum.add(auAmount);
				}
			}
		}
		auSubTotal = sum;
	}

	public void voucherInvRowSelectCheckbox(SelectEvent event) {
		BigDecimal sum = BigDecimal.ZERO;
		if (selectedInvoiceVouchers.size() > 0) {
			for (VoucherGui voucher : selectedInvoiceVouchers) {
				voucherAmount = voucher.getAmount();

				sum = sum.add(voucherAmount);
			}
			voucherSubTotal = sum;
		}
	}

	public void voucherInvRowUnSelectCheckbox(UnselectEvent event) {
		voucherAmount = ((VoucherGui) event.getObject()).getAmount();

		voucherSubTotal = voucherSubTotal.subtract(voucherAmount);
	}

	public void voucherInvAllRowSelectCheckbox(ToggleSelectEvent event) {
		BigDecimal sum = BigDecimal.ZERO;

		if (selectedInvoiceVouchers.size() > 0) {
			for (VoucherGui voucher : selectedInvoiceVouchers) {
				voucherSubTotal = voucher.getAmount();

				sum = sum.add(voucherSubTotal);
			}
			voucherSubTotal = sum;
		}
	}

	public String findInvoiceSummaryById() {
		try {
			invoice = invoiceService.getInvoiceById(Integer.valueOf(invoiceId));
			showDetail = true;
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		return INVOICE_DETAIL;
	}

	public String findInvoiceDetail(Integer invoiceId) {
		getInvoiceDetails(invoiceId);
		showDetail = true;

		return INVOICE_DETAIL;
	}

	public String findInvoiceApproval() {
		logger.debug("findInvoiceApproval method call started...");
		invoiceStatus = invoiceService.getAllInvStatus();

		invoiceStatus = invoiceStatus
				.stream().filter(item -> !(item.getInvoiceStatusId().intValue() != 2
						&& item.getInvoiceStatusId().intValue() != 3 && item.getInvoiceStatusId().intValue() != 4))
				.collect(Collectors.toList());

		getInvoiceDetails(Integer.valueOf(invoiceId));

		logger.debug("	invoiceApprovalVouchers " + invoiceApprovalVouchers.size());
		
		voucherSubmitInvoiceApproval = invoiceApprovalVouchers.stream().filter(item -> (item.getInvoiceStatusId().intValue() == 2)).collect(Collectors.toList());
		logger.debug("     Before voucher for loop");
		
		logger.debug("	voucherSubmitInvoiceApproval " + voucherSubmitInvoiceApproval.size());
		
		
		for (VoucherGui voucher : invoiceApprovalVouchers) {
			logger.debug("Inside for loop ");
			if (voucher.getInvoiceStatusId().intValue() == 3) {
				disableApprovedBtn = true;
				logger.debug("Inside for loop if rejected ");
				return INVOICE_APPROVAL;
			}
			if (voucher.getInvoiceStatusId().intValue() != 3) {
				disableApprovedBtn = false;
				logger.debug("Inside for loop if not rejected ");
			}
		}
		logger.debug("     After voucher for loop");
		for (StakingSheetDetail ss : invApprovalStakingSheetDet) {
			if (ss.getInvoiceStatusId() != null && ss.getInvoiceStatusId().intValue() == 3) {
				disableApprovedBtn = true;
				return INVOICE_APPROVAL;
			}
			if (ss.getInvoiceStatusId() != null && ss.getInvoiceStatusId().intValue() != 3) {
				disableApprovedBtn = false;
			}
		}
		logger.debug("findInvoiceApproval method call finished...");
		return INVOICE_APPROVAL;
	}

	/*public void onInvApprovalCellEdit(CellEditEvent event) {
		StakingSheetDetail ssd = (StakingSheetDetail) ((DataTable) event.getComponent()).getRowData();

		ssd.setInvoiceStatusId(ssd.getInvoiceStatus().getInvoiceStatusId());
		ssd.setInvoiceApprovedBy(util.getCurrentUser());
		ssd.setInvoiceApprovedDt(util.currentDtTm());

		if (ssd.getInvoiceStatus().getDescription().equals("Rejected")
				&& StringUtils.isBlank(ssd.getInvoiceApprovedComment())) {
			facesError("Comment required on rejected Invoice item.");
		}
		try {
			workflowService.updateStakingSheetDetail(ssd);
		} catch (Exception e1) {
			logger.error(e1);
			facesError(e1.getMessage());
		}

		updateApprovalBtn();

		PrimeFaces.current().ajax().update(new String[] { "INVOICE_APPROVAL_TABLE" });
	}*/

	public void updateApprovalBtn() {
		if (invApprovalStakingSheetDet.stream()
				.anyMatch(i -> i.getInvoiceStatus().getDescription().equals("Rejected"))
				|| invoiceApprovalVouchers.stream()
						.anyMatch(i -> i.getInvoiceStatus().equals("Rejected"))) {
			disableApprovedBtn = true;
			logger.debug("disable the button true");
		} else if (invApprovalStakingSheetDet.stream()
				.noneMatch(i -> i.getInvoiceStatus().getDescription().equals("Rejected"))
				|| invoiceApprovalVouchers.stream()
						.noneMatch(i -> i.getInvoiceStatus().equals("Rejected"))) {
			disableApprovedBtn = false;
			logger.debug("disable the button false");
		}

		//PrimeFaces.current().ajax().update(":invApprovBtnFrm:approvalBtnId");
	}

	public String submitInvoiceApproval() {
		try {
			invoiceService.updateInvoiceApproval(Integer.valueOf(invoiceId), Integer.valueOf(4),
					util.getCurrentUser(), util.currentDtTm());
			invoiceService.updateInvoiceVoucherApproval(Integer.valueOf(invoiceId), Integer.valueOf(4),
					util.getCurrentUser(), util.currentDtTm());

			invoiceService.updateInvoiceStatus(Integer.valueOf(invoiceId), Integer.valueOf(4), STAKING_INVOICE,
					util.getCurrentUser(), util.currentDtTm(), invoiceComment);
			facesInfo("Invoice Approved.");
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}

		//findInvoices();

		updateOverallInvoiceStatus();

		if (renderBackToInvoiceTab) {
			findInvoices();
			return findStakingDetailByWoId();
		}
		
		InvoiceController controller = (InvoiceController)FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{invController}", InvoiceController.class);
		controller.findInvoices();
		
		updateServiceOrderInvoice(woId, invoiceId);
		
		return INVOICE_SEARCH;
	}

	public String submitInvoiceRejected() {
		if (invoiceComment != null && !StringUtils.isBlank(invoiceComment)) {

			try {

				invoiceService.updateInvoiceStatus(Integer.valueOf(invoiceId), Integer.valueOf(3), STAKING_INVOICE,
						util.getCurrentUser(), util.currentDtTm(), invoiceComment);
				facesInfo("Invoice Rejected.");
			} catch (Exception e) {
				logger.error(e);
				facesError(e.getMessage());
			}

			//findInvoices();
			invoiceComment = "";
			if (renderBackToInvoiceTab) {
				findInvoices();
				return findStakingDetailByWoId();
			}
			
			InvoiceController controller = (InvoiceController)FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{invController}", InvoiceController.class);
			controller.findInvoices();
			
			updateServiceOrderInvoice(woId, invoiceId);
			
			return INVOICE_SEARCH;
		}

		facesError("Comment required on rejected Invoice.");

		return null;
	}

	public String updateInvoiceApproval(StakingSheetDetail ssd) {
		ssd.setInvoiceStatusId(ssd.getInvoiceStatus().getInvoiceStatusId());
		ssd.setInvoiceApprovedBy(util.getCurrentUser());
		ssd.setInvoiceApprovedDt(util.currentDtTm());

		if (ssd.getInvoiceStatus().getDescription().equals("Rejected")
				&& StringUtils.isBlank(ssd.getInvoiceApprovedComment())) {
			facesError("Comment required on rejected Invoice item.");
		}
		try {
			workflowService.updateStakingSheetDetail(ssd);
		} catch (Exception e1) {
			logger.error(e1);
			facesError(e1.getMessage());
		}

		updateApprovalBtn();

		return INVOICE_APPROVAL;
	}

	public void updateVoucherInvoiceApproval(VoucherGui voucher) {
		logger.debug("Method called from client side - updateVoucherInvoiceApproval " + voucher.getAmount());
		Voucher v = workflowService.getVoucherById(voucher.getVoucherId());
		
		try {
			v.setApprovedBy(util.getCurrentUser());
			v.setApprovedDt(util.currentDtTm());
			v.setInvoiceStatusId(voucher.getInvoiceStatusId());
			v.setApprovedComment(voucher.getApprovedComment());

			v = workflowService.updateVoucher(v);
			logger.debug("Voucher invoice ststus id: " + v.getInvoiceStatusId());
			logger.debug("Voucher Comment: " + v.getApprovedComment());

			if (v.getInvoiceStatusId() == 3 && StringUtils.isBlank(v.getApprovedComment())) {
				facesError("Comment required on rejected Invoice voucher.");
				logger.debug("Comment required from bean...");
				//PrimeFaces.current().ajax().update("messages");
			}

		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		
		findInvoiceApproval();
		
		updateApprovalBtn();

		//return INVOICE_APPROVAL;
	}

	public void getInvoiceDetails(Integer invoiceId) {
		try {
			invoiceDetail = invoiceService.getInvoiceDetails(invoiceId);
			invoiceGLSummaryVw = invoiceService.getInvoiceGLSummaryVw(invoiceId);
			invApprovalStakingSheetDet = workflowService.getStakingSheetByInvId(invoiceId.intValue());
			invoice = invoiceService.getInvoiceById(invoiceId);
			invoiceApprovalVouchers = workflowService.getVoucherByInvoiceId(invoiceId);
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}
	}

	public String findInvoiceById(Integer invoiceNo) {
		try {
			invoice = invoiceService.getInvoiceById(invoiceNo);
			showDetail = true;
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		return INVOICE_DETAIL;
	}

	public void findInspectionStatus() {
		inspStatuses = inspectionService.getInspectionStatus();
	}

	public String asBuiltEdit() {
		//PrimeFaces.current().clearTableStates();
		logger.debug("AsBuiltEdit Method called....");
		asBuiltStakingSheetDetailGui = new ArrayList<StakingSheetDetailGui>();
		PrimeFaces.current().ajax().update("stakingTabForm");
		asBuiltStakingSheetDetailGui = findStakingDetail(woId);
		
		asBuiltStakingSheetDetailGui = asBuiltStakingSheetDetailGui.stream().filter(item -> !(item.getAsBuiltStatus() != null && item.getCurrentInspectionDetailStatus().equals(APPROVED))).collect(Collectors.toList());

		if(asBuiltStakingSheetDetailGui.size() > 0) {
			logger.debug("asbuiltEdit button: WorkorderId: " + asBuiltStakingSheetDetailGui.get(0).getWorkOrderId());
		}else {
			logger.debug("AsBuiltEdit button pressed but no current AU's available to edit");
		}
		
		setAsBuiltDisable(false);
		showAsBuiltStatus = false;

		editAsBuilt = false;
		renderAsBuiltChkBox = true;
		renderAddStationUnit = true;
		
		PrimeFaces.current().ajax().update("stakingTabForm");
		
		return STAKING;
	}

	public void onInspectionCellEdit(StakingSheetDetailGui gui) {
		
		StakingSheetDetail ssd = workflowService.getStakingSheetDetailBySSDId(gui.getStakingSheetDetailId());
		
		if(gui.getCurrentInspectionDetailStatusId() == 2) {//Ready for Inspection
			ssd.setCurrentInspectionDetailStatusId(gui.getCurrentInspectionDetailStatusId());
			ssd.setCurrentInspectionDetailDt(null);
			ssd.setCurrentInspectedDetailBy(null);
			ssd.setCurrentInspectorDetailComments(null);
		}else if(gui.getCurrentInspectionDetailStatusId() == 4) {//Approved
			ssd.setCurrentInspectionDetailStatusId(gui.getCurrentInspectionDetailStatusId());
			ssd.setCurrentInspectionDetailDt(util.currentDtTm());
			ssd.setCurrentInspectedDetailBy(String.valueOf(getInspectorId()));
		}else if(gui.getCurrentInspectionDetailStatusId() == 5) {//Rejected
			ssd.setCurrentInspectionDetailStatusId(gui.getCurrentInspectionDetailStatusId());
			ssd.setCurrentInspectionDetailDt(util.currentDtTm());
			ssd.setCurrentInspectedDetailBy(String.valueOf(getInspectorId()));
			//ssd.setCurrentInspectorDetailComments(gui.getCurrentInspectorDetailComments());
		}
		/*ssd.setCurrentInspectionDetailStatusId(gui.getCurrentInspectionDetailStatusId());
		ssd.setCurrentInspectionDetailDt(util.currentDtTm());
		ssd.setCurrentInspectedDetailBy(String.valueOf(getInspectorId()));*/

		try {
			workflowService.updateInspectionStakingSheetDetail(ssd, inspectionId);
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}

		try {
			inspectionService.updateInspection(inspectionId, Integer.valueOf(3));
		} catch (Exception e) {
			logger.error(e);
		}

		if (gui.getCurrentInspectionDetailStatusId() == 5 && StringUtils.isBlank(ssd.getCurrentInspectorDetailComments())) {
			facesError("Inspection comment required.");
		}
		
		findInspectionDetails();
		PrimeFaces.current().ajax().update("NEW_INSPECTION_TABLE");
	}
	
	public void onInspectionCommentCellEdit(StakingSheetDetailGui gui) {
		
		StakingSheetDetail ssd = workflowService.getStakingSheetDetailBySSDId(gui.getStakingSheetDetailId());
		
		ssd.setCurrentInspectorDetailComments(gui.getCurrentInspectorDetailComments());
		
		try {
			workflowService.updateInspectionStakingSheetDetail(ssd, inspectionId);
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		
		findInspectionDetails();
		PrimeFaces.current().ajax().update("NEW_INSPECTION_TABLE");
	}

	public void onVoucherInspectCellEdit(VoucherGui voucher) {
		
		Voucher vchr = workflowService.getVoucherById(voucher.getVoucherId());
		
		vchr.setInspectionId(inspectionId);
		vchr.setInspectionStatusId(voucher.getInspectionStatusId());

		try {
			workflowService.updateVoucher(vchr);
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}

		try {
			inspectionService.updateInspection(inspectionId, Integer.valueOf(3));
		} catch (Exception e) {
			logger.error(e);
		}

		if (voucher.getInspectionStatusId() == 5 && StringUtils.isBlank(voucher.getInspectionComment())) {
			facesError("Inspection comment required.");
		}
		
		findInspectionDetails();
		
		PrimeFaces.current().ajax().update("INSPECTION_NEW_VOUCHER_TABLE");
	}
	
	public void onVoucherInspCommentCellEdit(VoucherGui voucher) {
		
		Voucher vchr = workflowService.getVoucherById(voucher.getVoucherId());
		
		vchr.setInspectionComment(voucher.getInspectionComment());
		try {
			workflowService.updateVoucher(vchr);
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}

		findVouchers();
	}

	public String pickAsBuiltStyleClass(StakingSheetDetailGui ssd) {
		if (ssd != null) {

			if (ssd.getAsBuiltStatus().equals("Not Started"))
				return "inspEditDefault";
			if (ssd.getAsBuiltStatus().equals("Completed")) {
				return "inspGreen";
			}

			if (ssd.getAsBuiltStatus().equals("Appealed")) {
				return "inspGreen";
			}

			return "inspRed";
		}

		return null;
	}

	public void pickAsBuiltDisable(StakingSheetDetail ssd) {
	}

	public String pickInspectionStyleClass(StakingSheetDetailGui ssd) {
		if (ssd != null) {

			if (ssd.getCurrentInspectionDetailStatus() .equals("Not Inspected"))
				return "null";
			if (ssd.getCurrentInspectionDetailStatus().equals("Ready for Inspection")) {
				return "inspEditDefault";
			}

			if (ssd.getCurrentInspectionDetailStatus().equals(APPROVED)) {
				return "inspGreen";
			}

			return "inspRed";
		}

		return null;
	}

	public boolean pickInspectionDisable(StakingSheetDetailGui ssd) {
		if (ssd.getCurrentInspectionDetailStatus().equals("Not Inspected"))
			return true;
		if (ssd.getCurrentInspectionDetailStatus().equals("Ready for Inspection"))
			return false;
		if (ssd.getCurrentInspectionDetailStatus().equals(APPROVED)) {
			if (ssd.getInvoiceStatus().equals("Not Invoiced")) {
				return false;
			}
			return true;
		}

		return false;
	}

	public String pickVoucherStyleClass(VoucherGui v) {
		if (v != null) {
			if (v.getInspectionStatus().equals("Not Inspected"))
				return "null";
			if (v.getInspectionStatus().equals("Ready for Inspection"))
				return "inspEditDefault";
			if (v.getInspectionStatus().equals(APPROVED)) {
				return "inspGreen";
			}
			return "inspRed";
		}

		return null;
	}

	public boolean pickVoucherDisable(VoucherGui v) {
		if (v.getInspectionStatus().equals("Not Inspected"))
			return true;
		if (v.getInspectionStatus().equals("Ready for Inspection"))
			return false;
		if (v.getInspectionStatus().equals(APPROVED)) {
			if (v.getInvoiceStatus().equals("Not Invoiced")) {
				return false;
			}
			return true;
		}

		return false;
	}

	public void inspectionDetailVoucherFilter() {
		findVouchers();

		if (inspectionVoucherFilter) {
			newInspVouchers = vouchers;
			logger.debug("InspectionVoucherFilter: " + inspectionVoucherFilter);
		} else {
			if (continueInspection) {
				logger.debug("ContinueInspection: " + continueInspection);
				newInspVouchers = vouchers.stream().filter(v -> !(v.getInspectionStatusId().intValue() != 2
						&& !v.getInspectionId().equals(inspectionId))).collect(Collectors.toList());

			} else {
				logger.debug("ContinueInspection: " + continueInspection);
				newInspVouchers = vouchers.stream().filter(v -> ((v.getInspectionStatusId().intValue() == 2 || (v.getInspectionStatusId().intValue() != 2 && v.getInspectionId().equals(inspectionId))))).collect(Collectors.toList());
			}
		}
	}

	public void inspectionDetailAuFilter() {
		
		inspStakingSheetDetail = findStakingDetail(woId);//workflowService.getStakingSheetDetailGuiByWOId(woId);
		
		if (!inspectionFilter)  {

			inspStakingSheetDetail = inspStakingSheetDetail.stream()
					.filter(item -> ((item.getAsBuiltStatus().equals("Completed")
							|| item.getAsBuiltStatus().equals("Appealed"))
							&& (item.getCurrentInspectionDetailStatus().equals("Ready for Inspection")
									|| item.getCurrentInspectionDetailStatus().equals("Rejected"))))
					.collect(Collectors.toList());
		}
	}

	public void findVouchers() {
		try {
			vouchers = workflowService.getVouchers(woId);
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}
	}

	public void onAsBuiltTabChange(TabChangeEvent event) {
		Tab activeTab = event.getTab();

		asBuiltActiveTab = Integer.valueOf(((TabView) event.getSource()).getChildren().indexOf(activeTab));
		
		if(activeTab.getTitle().equals("As-Builts")) {
			onloadStakingJob();
		}else if (activeTab.getTitle().equals("Inspections")) {
			findInspections();
		}else if (activeTab.getTitle().equals("Invoices")) {
			findInvoices();
		}else if (activeTab.getTitle().equals("Vouchers")) {
			findVouchers();
		}
	}

	public void onAsBuiltToggle(ToggleEvent event) {
    if (event.getVisibility() != Visibility.VISIBLE) {
      
      event.getVisibility(); 
      //Visibility.HIDDEN;
      
      //TODO: Will need to look at this*****
      
    } 
  }

	public void updateAssemblyUnitEnergized() {
		assemblyunits = assemblyunits.stream().filter(item -> item.getEnergized().equals(energized))
				.collect(Collectors.toList());
	}

	public void preProcessorStakingSheetRsltPDF(Object document) {
		Document doc = (Document) document;
		doc.setPageSize(PageSize.A4.rotate());
	}

	public void preProcessorRejectionRpt(Object document) {
		asBuiltStakingSheetDetailGui = asBuiltStakingSheetDetailGui.stream()
				.filter(item -> item.getCurrentInspectionDetailStatus().equals("Rejected")).collect(Collectors.toList());

		postProcessorRejectionRpt(document);
	}

	public void postProcessorRejectionRpt(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("Work Order Number");

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		sheet.shiftRows(0, asBuiltStakingSheetDetailGui.size(), 2);
	}

	public void findVendors() {
		vendors = workflowService.getVendors();
	}

	public Timestamp currentDtTm() {
		return new Timestamp(System.currentTimeMillis());
	}

	public String getCurrentUser() {
		Subject currentUser = SecurityUtils.getSubject();

		return currentUser.getPrincipal().toString();
	}
	
	public void unlockInspectionDetail() {
		
		StakingSheetDetail ssd = workflowService.getStakingSheetDetailBySSDId(selectedInspectionDetailVw.getStakingSheetDetailId());
		
		if(ssd.getInvoiceStatusId() == 1 || ssd.getInvoiceStatusId() == 3) {
			
			ssd.setCurrentInspectionDetailStatusId(2);
			ssd.setCurrentInspectedDetailBy(null);
			ssd.setCurrentInspectionDetailDt(null);
			ssd.setCurrentInspectionDetailId(0);
			ssd.setCurrentInspectorDetailComments(null);
			
			
			workflowService.updateAsBuiltStakingSheetDetail(ssd);
			
			if(inspectionService.removeInspectionDetailById(selectedInspectionDetailVw.getInspectionDetailId())) {
				facesInfo(ssd.getAssemblyGuid() + " has been unlocked.");
			}else {
				facesError("Sorry something went wrong.");
			}
			
		}else {
			facesError("Sorry this AU has already been invoiced, and connot be unlocked.");
		}

		findInspectionById();
		PrimeFaces.current().ajax().update("inspectionForm");
		findStakingDetailByWoId();
		
		InspectionUnlock iu = new InspectionUnlock();
		
		iu.setInspectionId(selectedInspectionDetailVw.getInspectionId());
		iu.setStakingSheetDetailId(selectedInspectionDetailVw.getStakingSheetDetailId());
		iu.setWorkOrderId(woId);
		iu.setUnlockedBy(getCurrentUser());
		iu.setUnlockedDt(currentDtTm());
		
		workflowService.insertInspectionUnlock(iu);
	}
	
	public void unlockInspectionVoucher() {
		
		if(selectedVoucher.getInvoiceStatusId() == 1 || selectedVoucher.getInvoiceStatusId() == 3) {
			
			Voucher v = workflowService.getVoucherById(selectedVoucher.getVoucherId());
			
			v.setInspectionId(0);
			v.setInspectionComment("");
			v.setInspectionStatusId(2);
			
			workflowService.updateVoucher(v);
			
			facesInfo("This voucher has been unlocked.");
		}else {
			facesError("Sorry this Voucher has already been invoiced, and connot be unlocked.");
		}

		findInspectionById();
		PrimeFaces.current().ajax().update("inspectionForm");
		//findStakingDetailByWoId();
		
		InspectionUnlock iu = new InspectionUnlock();
		
		iu.setInspectionId(selectedVoucher.getInspectionId());
		iu.setVoucherId(selectedVoucher.getVoucherId());
		iu.setWorkOrderId(woId);
		iu.setUnlockedBy(getCurrentUser());
		iu.setUnlockedDt(currentDtTm());
		
		workflowService.insertInspectionUnlock(iu);
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

	/***************************TODO: Getters and Setters*************************/
	
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

/*	public List<StakingSheetDetail> getStakingSheetDetail() {
		return stakingSheetDetail;
	}

	public void setStakingSheetDetail(List<StakingSheetDetail> stakingSheetDetail) {
		this.stakingSheetDetail = stakingSheetDetail;
	}*/

	public List<StakingSheetDetailGui> getFilteredStakingSheetDetail() {
		return filteredStakingSheetDetail;
	}

	public void setFilteredStakingSheetDetail(List<StakingSheetDetailGui> filteredStakingSheetDetail) {
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
		invAmt = new BigDecimal(0);
		for (StakingSheetDetail detail : selectedStakingSheetDetails) {
			BigDecimal x = BigDecimal.valueOf(detail.getAsBuiltQuantity());
			BigDecimal y = x.multiply(new BigDecimal(detail.getRateGroupPrice().getFixedCost()));

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

	public List<BigDecimal> getDistinctStation() {
		return distinctStation;
	}

	public void setDistinctStation(List<BigDecimal> distinctStation) {
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
						continue;
					}
					amtPaidToDt = BigDecimal.ZERO;
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
		for (Inspection inspection : inspections) {
			if (inspection.getInspectionStatus() != null
					&& inspection.getInspectionStatus().getDescription().equals("Passed")) {
				count++;
			}
		}
		if (count > 0 && inspections.size() > 0) {
			inspectionCompleted = (new BigDecimal(count)).divide(new BigDecimal(inspections.size()));
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

	public List<VoucherGui> getVouchers() {
		return vouchers;
	}

	public void setVouchers(List<VoucherGui> vouchers) {
		this.vouchers = vouchers;
	}

	public List<VoucherGui> getInspectedVouchers() {
		return inspectedVouchers;
	}

	public void setInspectedVouchers(List<VoucherGui> inspectedVouchers) {
		this.inspectedVouchers = inspectedVouchers;
	}

	public List<StakingSheetDetailGui> getInspStakingSheetDetail() {
		return inspStakingSheetDetail;
	}

	public void setInspStakingSheetDetail(List<StakingSheetDetailGui> inspStakingSheetDetail) {
		this.inspStakingSheetDetail = inspStakingSheetDetail;
	}

	public List<StakingSheetDetail> getInvoiceStakingSheetDetail() {
		return invoiceStakingSheetDetail;
	}

	public void setInvoiceStakingSheetDetail(List<StakingSheetDetail> invoiceStakingSheetDetail) {
		this.invoiceStakingSheetDetail = invoiceStakingSheetDetail;
	}

	public List<VoucherGui> getInvoiceVouchers() {
		return invoiceVouchers;
	}

	public void setInvoiceVouchers(List<VoucherGui> invoiceVouchers) {
		this.invoiceVouchers = invoiceVouchers;
	}

	public List<VoucherGui> getSelectedInvoiceVouchers() {
		return selectedInvoiceVouchers;
	}

	public void setSelectedInvoiceVouchers(List<VoucherGui> selectedInvoiceVouchers) {
		this.selectedInvoiceVouchers = selectedInvoiceVouchers;
	}

	public List<VoucherGui> getInvoiceApprovalVouchers() {
		return invoiceApprovalVouchers;
	}

	public void setInvoiceApprovalVouchers(List<VoucherGui> invoiceApprovalVouchers) {
		this.invoiceApprovalVouchers = invoiceApprovalVouchers;
	}

	public List<VoucherGui> getVoucherSubmitInvoiceApproval() {
		return voucherSubmitInvoiceApproval;
	}

	public void setVoucherSubmitInvoiceApproval(List<VoucherGui> voucherSubmitInvoiceApproval) {
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

	public List<VoucherGui> getNewInspVouchers() {
		return newInspVouchers;
	}

	public void setNewInspVouchers(List<VoucherGui> newInspVouchers) {
		this.newInspVouchers = newInspVouchers;
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

	public Timestamp getWorkEventDt() {
		return workEventDt;
	}

	public void setWorkEventDt(Timestamp workEventDt) {
		this.workEventDt = workEventDt;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isInspectionFilter() {
		return inspectionFilter;
	}

	public void setInspectionFilter(boolean inspectionFilter) {
		this.inspectionFilter = inspectionFilter;
	}

	public boolean isInspectionVoucherFilter() {
		return inspectionVoucherFilter;
	}

	public void setInspectionVoucherFilter(boolean inspectionVoucherFilter) {
		this.inspectionVoucherFilter = inspectionVoucherFilter;
	}

	public List<BigDecimal> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<BigDecimal> selectedItems) {
		this.selectedItems = selectedItems;
	}

	public boolean isAsBuiltShowAllBool() {
		return asBuiltShowAllBool;
	}

	public void setAsBuiltShowAllBool(boolean asBuiltShowAllBool) {
		this.asBuiltShowAllBool = asBuiltShowAllBool;
	}

	public boolean isCancelInspOnBack() {
		return cancelInspOnBack;
	}

	public void setCancelInspOnBack(boolean cancelInspOnBack) {
		this.cancelInspOnBack = cancelInspOnBack;
	}

	public boolean isContinueInspection() {
		return continueInspection;
	}

	public void setContinueInspection(boolean continueInspection) {
		this.continueInspection = continueInspection;
	}

	public List<StakingSheetDetailGui> getAsBuiltStakingSheetDetailGui() {
		return asBuiltStakingSheetDetailGui;
	}

	public void setAsBuiltStakingSheetDetailGui(List<StakingSheetDetailGui> asBuiltStakingSheetDetailGui) {
		this.asBuiltStakingSheetDetailGui = asBuiltStakingSheetDetailGui;
	}

	public InspectionDetailVw getSelectedInspectionDetailVw() {
		return selectedInspectionDetailVw;
	}

	public void setSelectedInspectionDetailVw(InspectionDetailVw selectedInspectionDetailVw) {
		this.selectedInspectionDetailVw = selectedInspectionDetailVw;
	}

	public VoucherGui getSelectedVoucher() {
		return selectedVoucher;
	}

	public void setSelectedVoucher(VoucherGui selectedVoucher) {
		this.selectedVoucher = selectedVoucher;
	}

	public StakingSheetDetailGui getSelectedStakingSheetDetail() {
		return selectedStakingSheetDetail;
	}

	public void setSelectedStakingSheetDetail(StakingSheetDetailGui selectedStakingSheetDetail) {
		this.selectedStakingSheetDetail = selectedStakingSheetDetail;
	}
	
}
