package pwr.lcec.vendor.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.component.columntoggler.ColumnToggler;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;
import org.primefaces.util.ComponentTraversalUtils;

import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendorportal.interfaces.UserManagementLocal;
import pwr.lcec.vendorportal.interfaces.UserPrefLocal;
import pwr.lcec.vendorportal.entity.sec.UserTbl;
import pwr.lcec.vendorportal.entity.userpref.UserPrefInspection;
import pwr.lcec.vendorportal.entity.userpref.UserPrefInspectionSearch;
import pwr.lcec.vendorportal.entity.userpref.UserPrefInvoice;
import pwr.lcec.vendorportal.entity.userpref.UserPrefInvoiceGLSummaryVw;
import pwr.lcec.vendorportal.entity.userpref.UserPrefInvoiceSearch;
import pwr.lcec.vendorportal.entity.userpref.UserPrefProjectWorkflow;
import pwr.lcec.vendorportal.entity.userpref.UserPrefStakingSheetDetail;
import pwr.lcec.vendorportal.entity.userpref.UserPrefVoucher;
import pwr.lcec.vendorportal.entity.userpref.UserPrefWorkFlowSearch;

public class UserPrefController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LogManager.getLogger(UserPrefController.class);
	
	@EJB
	private UserPrefLocal userPrefService;
	@EJB
	private UserManagementLocal userManagementService;
	
	private UserTbl user;
	private String tblName;
	
	private UserPrefWorkFlowSearch wfSearch;
	private UserPrefInspectionSearch inspSearch;
	private UserPrefInvoiceSearch invSearch;
	private UserPrefStakingSheetDetail stakingDet;
	private UserPrefInvoiceGLSummaryVw invoiceDetail;
	private UserPrefVoucher voucher;
	private UserPrefVoucher voucherNewInsp;
	private UserPrefInspection inspTab;
	private UserPrefInvoice invoiceTab;
	private UserPrefProjectWorkflow projectWorkflow;
	
	final ControllerUtil util = new ControllerUtil();
	
	Map<String, Boolean> visibleColumns = new HashMap<String,Boolean>();
	
	public void findUser() {
		try {
			user = userManagementService.findUserByPrincipal(util.getCurrentUser());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void findUserPrefWF() {
		findUser();

		if (user != null) {
			wfSearch = userPrefService.getWFSearchPrefByUserId(user.getUserID());
		}
		if (wfSearch == null) {
			wfSearch = new UserPrefWorkFlowSearch(true, true, true, true, true, true, true, true, true, true, true,
					true, true, true, true, true, true, true, true, true, true, true, true, true, user.getUserID(),
					true, true, "WF_SEARCH_TABLE", true, true, true, true, true, true, true);

			wfSearch = userPrefService.updateWFSearchPref(wfSearch);
		}
	}
	
	public void findUserPrefInspectionTab(){
		if (user != null) {
			inspTab = userPrefService.getInspectionPrefByUserId(user.getUserID(), "INSPECTION_HISTORY_TAB");
		}
		if (inspTab == null) {
			inspTab = new UserPrefInspection(true, true, true, true, true, true, true, user.getUserID(), true,
					"INSPECTION_HISTORY_TAB", true, true);
			
			inspTab = userPrefService.updateInspectionPref(inspTab);
		}
	}
	
	public void findUserPrefInvoiceTab() {
		if (user != null) {
			invoiceTab = userPrefService.getInvoicePrefByUserId(user.getUserID(), "INVOICE_DETAIL_TAB");
		}
		if (invoiceTab == null) {
			invoiceTab = new UserPrefInvoice(true, true, true, true, true, true, true, true, true, true, true, true,
					true, true, true, true, true, true, true, true, true, true, true, user.getUserID(), true, true,
					true, "INVOICE_DETAIL_TAB", true, true,true);

			invoiceTab = userPrefService.updateInvoicePref(invoiceTab);
		}
	}
	
	public void findUserPrefInspSearch() {
		findUser();
		if (user != null) {
			inspSearch = userPrefService.getInspSearchPrefByUserId(user.getUserID());
		}
		if (inspSearch == null) {
			inspSearch = new UserPrefInspectionSearch(true, true, true, true, true, true, true, user.getUserID(), true,
					"INSP_SEARCH_TABLE", true, true, true);

			inspSearch = userPrefService.updateInspSearchPref(inspSearch);
		}
	}

	public void findUserPrefInvSearch() {
		findUser();
		if (user != null) {
			invSearch = userPrefService.getInvSearchPrefByUserId(user.getUserID());
		}
		if (invSearch == null) {
			invSearch = new UserPrefInvoiceSearch(true, true, true, true, true, true, true, true, true, true, true,
					true, true, true, true, user.getUserID(), true, true, true, true, "INV_SEARCH_TABLE", true, true,
					true);

			invSearch = userPrefService.updateInvSearchPref(invSearch);
		}
	}
	
	public void findUserPrefStakingsheetDetail() {
		if (user != null) {
			stakingDet = userPrefService.getStakingSheetDetailPrefByUserId(user.getUserID(), "STAKINGSHEET_DETAIL_TABLE");
		}
		if(stakingDet == null) {
			stakingDet = new UserPrefStakingSheetDetail(true, true, true, true, true, true, true, true, true, true, true, true, true, true, 
					true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 
					true, true, true, user.getUserID(), true, "STAKINGSHEET_DETAIL_TABLE", true, true);
			
			stakingDet = userPrefService.updateStakingSheetDetailPref(stakingDet);
		}
		
	}
	
	public void findUserPrefInvoiceDetail() {
		if (user != null) {
			invoiceDetail = userPrefService.getInvoiceGLSummaryPrefByUserId(user.getUserID());
		}
		if (invoiceDetail == null) {
			invoiceDetail = new UserPrefInvoiceGLSummaryVw(true, true, true, true, true, true, true, true, true, true,
					true, true, true, true, true, true, true, true, true, true, user.getUserID(), true,
					"INVOICE_DETAIL_TABLE");

			invoiceDetail = userPrefService.updateInvoiceGLSummaryPref(invoiceDetail);
		}
	}
	
	public void findUserPrefNewInvoice() {
		if (user != null) {
			stakingDet = userPrefService.getStakingSheetDetailPrefByUserId(user.getUserID(), "NEW_INVOICE_TABLE");
		}
		if(stakingDet == null) {
			stakingDet = new UserPrefStakingSheetDetail(true, true, true, true, true, true, true, true, true, true, true, true, true, true, 
					true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 
					true, true, true, user.getUserID(), true, "NEW_INVOICE_TABLE", true, true);
			
			stakingDet = userPrefService.updateStakingSheetDetailPref(stakingDet);
		}
	}
	
	public void findUserPrefNewInspection() {
		if (user != null) {
			stakingDet = userPrefService.getStakingSheetDetailPrefByUserId(user.getUserID(), "NEW_INSPECTION_TABLE");
		}
		if(stakingDet == null) {
			stakingDet = new UserPrefStakingSheetDetail(true, true, true, true, true, true, true, true, true, true, true, true, true, true, 
					true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 
					true, true, true, user.getUserID(), true, "NEW_INSPECTION_TABLE", true, true);
			
			stakingDet = userPrefService.updateStakingSheetDetailPref(stakingDet);
		}
	}
	
	public void findUserPrefInspectionHistDetail() {
		if (user != null) {
			stakingDet = userPrefService.getStakingSheetDetailPrefByUserId(user.getUserID(), "INSPECTION_HISTORY_TABLE");
		}
		if(stakingDet == null) {
			stakingDet = new UserPrefStakingSheetDetail(true, true, true, true, true, true, true, true, true, true, true, true, true, true, 
					true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 
					true, true, true, user.getUserID(), true, "INSPECTION_HISTORY_TABLE", true, true);
			
			stakingDet = userPrefService.updateStakingSheetDetailPref(stakingDet);
		}
	}
	
	public void findUserPrefStakingVoucher() {
		if (user != null) {
			voucher = userPrefService.getVoucherPrefByUserId(user.getUserID(), "STAKING_VOUCHER_TABLE");
		}if (voucher == null) {
			voucher = new UserPrefVoucher(true, true, true, true, true, true, true, true, true, true, 
					true, true, true, true, true, true, true, true, true, true, 
					user.getUserID(), true, "STAKING_VOUCHER_TABLE", true, true);
			
			voucher = userPrefService.updateVoucherPref(voucher);
		}
	}
	
	public void findUserPrefVoucherInvoiceDetail() {
		if (user != null) {
			voucher = userPrefService.getVoucherPrefByUserId(user.getUserID(), "INVOICE_VOUCHER_DETAIL_TABLE");
		}if (voucher == null) {
			voucher = new UserPrefVoucher(true, true, true, true, true, true, true, true, true, true, 
					true, true, true, true, true, true, true, true, true, true, 
					user.getUserID(), true, "INVOICE_VOUCHER_DETAIL_TABLE", true, true);
			
			voucher = userPrefService.updateVoucherPref(voucher);
		}
	}
	public void findUserPrefVoucherNewInvoice() {
		if (user != null) {
			voucher = userPrefService.getVoucherPrefByUserId(user.getUserID(), "INVOICE_NEW_VOUCHER_TABLE");
		}if (voucher == null) {
			voucher = new UserPrefVoucher(true, true, true, true, true, true, true, true, true, true, 
					true, true, true, true, true, true, true, true, true, true, 
					user.getUserID(), true, "INVOICE_NEW_VOUCHER_TABLE", true, true);
			
			voucher = userPrefService.updateVoucherPref(voucher);
		}
	}
	
	public void findUserPrefInspectionDetailVoucher() {
		
		if (user != null) {
			voucher = userPrefService.getVoucherPrefByUserId(user.getUserID(), "INSPECTION_HISTORY_VOUCHER_TABLE");
		}if (voucher == null) {
			voucher = new UserPrefVoucher(true, true, true, true, true, true, true, true, true, true, 
					true, true, true, true, true, true, true, true, true, true, 
					user.getUserID(), true, "INSPECTION_HISTORY_VOUCHER_TABLE", true, true);
			
			voucher = userPrefService.updateVoucherPref(voucher);
		}
		if (user != null) {
			voucherNewInsp = userPrefService.getVoucherPrefByUserId(user.getUserID(), "INSPECTION_NEW_VOUCHER_TABLE");
		}if(voucherNewInsp == null) {
			voucherNewInsp = new UserPrefVoucher(true, true, true, true, true, true, true, true, true, true, 
					true, true, true, true, true, true, true, true, true, true, 
					user.getUserID(), true, "INSPECTION_NEW_VOUCHER_TABLE", true, true);
			
			voucherNewInsp = userPrefService.updateVoucherPref(voucherNewInsp);
		}
	}
	
	public void findUserPrefProjectWorkflow() {
		findUser();
		
		if(user != null) {
			projectWorkflow = userPrefService.getProjectWorkFlowPrefByUserId(user.getUserID());
		}

		if (projectWorkflow == null) {
			projectWorkflow = new UserPrefProjectWorkflow(true, true, true, true, true, true, true, 
					true, true, true, user.getUserID(), true, "dtProjectDetails", true, true, true, true, true, true);

			projectWorkflow = userPrefService.updateProjectWoPref(projectWorkflow);
		}
		
	}

	public void woSrchToggleEvent(ToggleEvent event) {
		visibleColumns.clear();

		if (event.getData() == null || !(event.getData() instanceof Integer)
				|| !(event.getComponent() instanceof ColumnToggler)) {
			return;
		}
		Integer index = (Integer) event.getData();
		boolean visible = Visibility.VISIBLE.equals(event.getVisibility());

		// Store by name
		ColumnToggler toggler = ((ColumnToggler) event.getComponent());
		UIComponent component = ComponentTraversalUtils.firstWithId(toggler.getDatasource(),
				FacesContext.getCurrentInstance().getViewRoot());
		if (component == null || !(component instanceof DataTable)) {
			return;
		}

		DataTable table = (DataTable) component;
		String columnFullId = index >= table.getColumns().size() ? null : table.getColumns().get(index).getClientId();
		if (columnFullId != null) {
			String columnId = columnFullId.replace(table.getClientId(), "").replace(":", "");
			visibleColumns.put(columnId, visible);
		}

		String tblFullId = event.getComponent().getClientId();
		int tblId = tblFullId.indexOf(":");

		if (tblId == -1) {
			return;
		} else {
			tblName = tblFullId.substring(0, tblId);
		}

		visibleColumns.forEach((key, value) -> {
			
			if (tblName.equals("WF_SEARCH_TABLE")) {
				buildWFPrefUpdate(key, value);
				try {
					userPrefService.updateWFSearchPref(wfSearch);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (tblName.equals("INSP_SEARCH_TABLE")) {
				buildInspSearchPrefUpdate(key, value);
				try {
					userPrefService.updateInspSearchPref(inspSearch);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (tblName.equals("INV_SEARCH_TABLE")) {
				buildInvSearchPrefUpdate(key, value);
				try {
					userPrefService.updateInvSearchPref(invSearch);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (tblName.equals("STAKINGSHEET_DETAIL_TABLE")) {
				buildstakingsheetDetPrefUpdate(key, value);
				try {
					userPrefService.updateStakingSheetDetailPref(stakingDet);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (tblName.equals("INVOICE_DETAIL_TABLE")) {
				buildInvoiceDetailPrefUpdate(key, value);
				try {
					userPrefService.updateInvoiceGLSummaryPref(invoiceDetail);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (tblName.equals("NEW_INVOICE_TABLE")) {
				buildstakingsheetDetPrefUpdate(key, value);
				try {
					userPrefService.updateStakingSheetDetailPref(stakingDet);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (tblName.equals("NEW_INSPECTION_TABLE")) {
				buildstakingsheetDetPrefUpdate(key, value);
				try {
					userPrefService.updateStakingSheetDetailPref(stakingDet);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (tblName.equals("INSPECTION_HISTORY_TABLE")) {
				buildstakingsheetDetPrefUpdate(key, value);
				try {
					userPrefService.updateStakingSheetDetailPref(stakingDet);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (tblName.equals("STAKING_VOUCHER_TABLE")) {
				buildVoucherPrefUpdate(key, value);
				try {
					userPrefService.updateVoucherPref(voucher);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (tblName.equals("INVOICE_NEW_VOUCHER_TABLE")) {
				buildVoucherPrefUpdate(key, value);
				try {
					userPrefService.updateVoucherPref(voucher);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (tblName.equals("INSPECTION_HISTORY_VOUCHER_TABLE")) {
				buildVoucherPrefUpdate(key, value);
				try {
					userPrefService.updateVoucherPref(voucher);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (tblName.equals("INSPECTION_NEW_VOUCHER_TABLE")) {
				buildNewInspVoucherPrefUpdate(key, value);
				try {
					userPrefService.updateVoucherPref(voucherNewInsp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (tblName.equals("INVOICE_VOUCHER_DETAIL_TABLE")) {
				buildVoucherPrefUpdate(key, value);
				try {
					userPrefService.updateVoucherPref(voucher);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (tblName.equals("INSPECTION_HISTORY_TAB")) {
				buildInspectionTabPrefUpdate(key, value);
				try {
					userPrefService.updateInspectionPref(inspTab);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (tblName.equals("INVOICE_DETAIL_TAB")) {
				buildInvoiceTabPrefUpdate(key, value);
				try {
					userPrefService.updateInvoicePref(invoiceTab);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (tblName.equals("dtProjectDetails")) {
				logger.info("Inside the columntoggler inside the project if statement" + key + ":" + value);
				buildProjectWFPrefUpdate(key, value);
				try {
					userPrefService.updateProjectWoPref(projectWorkflow);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public UserPrefInvoice buildInvoiceTabPrefUpdate(String key, Boolean value) {
		if (key.equals("viewDefault")) {
			invoiceTab.setViewDefault(value);
		}
		if (key.equals("invoiceId")) {
			invoiceTab.setInvoiceId(value);
		}
		if (key.equals("invoiceStatusId")) {
			invoiceTab.setInvoiceStatusId(value);
		}
		if (key.equals("invoicedBy")) {
			invoiceTab.setInvoicedBy(value);
		}
		if (key.equals("invoicedDt")) {
			invoiceTab.setInvoicedDt(value);
		}
		if (key.equals("invoiceType")) {
			invoiceTab.setInvoiceType(value);
		}
		if (key.equals("invoiceAmount")) {
			invoiceTab.setInvoiceAmount(value);
		}
		if (key.equals("businessRuleFlg")) {
			invoiceTab.setBusinessRuleFlg(value);
		}
		if (key.equals("vendorId")) {
			invoiceTab.setVendorId(value);
		}
		if (key.equals("vendorReference")) {
			invoiceTab.setVendorReference(value);
		}
		if (key.equals("workFlowId")) {
			invoiceTab.setWorkFlowId(value);
		}
		if (key.equals("workOrderId")) {
			invoiceTab.setWorkOrderId(value);
		}
		if (key.equals("serviceOrderId")) {
			invoiceTab.setServiceOrderId(value);
		}
		if (key.equals("paymentDt")) {
			invoiceTab.setPaymentDt(value);
		}
		if (key.equals("paymentStatus")) {
			invoiceTab.setPaymentStatus(value);
		}
		if (key.equals("apInvId")) {
			invoiceTab.setApInvId(value);
		}
		if (key.equals("approvedBy")) {
			invoiceTab.setApprovedBy(value);
		}
		if (key.equals("approvedDt")) {
			invoiceTab.setApprovedDt(value);
		}
		if (key.equals("approvedComment")) {
			invoiceTab.setApprovedComment(value);
		}
		if (key.equals("designCost")) {
			invoiceTab.setDesignCost(value);
		}
		if (key.equals("designUniqueAssembly")) {
			invoiceTab.setDesignUniqueAssembly(value);
		}
		if (key.equals("designTotalAssembly")) {
			invoiceTab.setDesignTotalAssembly(value);
		}
		if (key.equals("designTotalStation")) {
			invoiceTab.setDesignTotalStation(value);
		}
		if (key.equals("invoiceUniqueAssembly")) {
			invoiceTab.setInvoiceUniqueAssembly(value);
		}
		if (key.equals("invoiceTotalAssembly")) {
			invoiceTab.setInvoiceTotalAssembly(value);
		}
		if (key.equals("invoiceTotalStation")) {
			invoiceTab.setInvoiceTotalStation(value);
		}
		if (key.equals("invoiceVoucherCost")) {
			invoiceTab.setInvoiceVoucherCost(value);
		}
		if (key.equals("invoiceVoucherQty")) {
			invoiceTab.setInvoiceVoucherQty(value);
		}
		if (key.equals("lCECReference")) {
			invoiceTab.setLCECReference(value);
		}

		return invoiceTab;
	}
	
	public UserPrefInspection buildInspectionTabPrefUpdate(String key, Boolean value) {
		
		if (key.equals("viewDefault")) {
			inspTab.setViewDefault(value);
		}
		if (key.equals("inspectionId")) {
			inspTab.setInspectionId(value);
		}
		if (key.equals("inspectionDt")) {
			inspTab.setInspectionDt(value);
		}
		if (key.equals("inspectedBy")) {
			inspTab.setInspectedBy(value);
		}
		if (key.equals("inspectionStatusId")) {
			inspTab.setInspectionStatusId(value);
		}
		if (key.equals("inspectionType")) {
			inspTab.setInspectionType(value);
		}
		if (key.equals("comments")) {
			inspTab.setComments(value);
		}
		if (key.equals("workOrderId")) {
			inspTab.setWorkOrderId(value);
		}
		if (key.equals("workFlowId")) {
			inspTab.setWorkFlowId(value);
		}
		if (key.equals("serviceOrderId")) {
			inspTab.setServiceOrderId(value);
		}

		return inspTab;
	}
	
	public UserPrefVoucher buildNewInspVoucherPrefUpdate(String key, Boolean value) {
		
		if (key.equals("viewDefault")) {
			voucherNewInsp.setViewDefault(value);
		}
		if (key.equals("voucherId")) {
			voucherNewInsp.setVoucherId(value);
		}
		if (key.equals("stakingSheetId")) {
			voucherNewInsp.setStakingSheetId(value);
		}
		if (key.equals("workOrderId")) {
			voucherNewInsp.setWorkOrderId(value);
		}
		if (key.equals("serviceOrderId")) {
			voucherNewInsp.setServiceOrderId(value);
		}
		if (key.equals("stationDescription")) {
			voucherNewInsp.setStationDescription(value);
		}
		if (key.equals("description")) {
			voucherNewInsp.setDescription(value);
		}
		if (key.equals("crew")) {
			voucherNewInsp.setCrew(value);
		}
		if (key.equals("amount")) {
			voucherNewInsp.setAmount(value);
		}
		if (key.equals("requestor")) {
			voucherNewInsp.setRequestor(value);
		}
		if (key.equals("createdDt")) {
			voucherNewInsp.setCreatedDt(value);
		}
		if (key.equals("GLAccountId")) {
			voucherNewInsp.setGLAccountId(value);
		}
		if (key.equals("GLAccountIdSplit")) {
			voucherNewInsp.setGLAccountIdSplit(value);
		}
		if (key.equals("inspectionId")) {
			voucherNewInsp.setInspectionId(value);
		}
		if (key.equals("inspectionStatusId")) {
			voucherNewInsp.setInspectionStatusId(value);
		}
		if (key.equals("inspectionComment")) {
			voucherNewInsp.setInspectionComment(value);
		}
		if (key.equals("invoiceStatusId")) {
			voucherNewInsp.setInvoiceStatusId(value);
		}
		if (key.equals("invoiceId")) {
			voucherNewInsp.setInvoiceId(value);
		}
		if (key.equals("invoiceDetailId")) {
			voucherNewInsp.setInvoiceDetailId(value);
		}
		if (key.equals("approvedBy")) {
			voucherNewInsp.setApprovedBy(value);
		}
		if (key.equals("approvedDt")) {
			voucherNewInsp.setApprovedDt(value);
		}
		if (key.equals("approvedComment")) {
			voucherNewInsp.setApprovedComment(value);
		}
		if (key.equals("submitGuid")) {
			voucherNewInsp.setSubmitGuid(value);
		}
		return voucherNewInsp;
	}
	
	public UserPrefVoucher buildVoucherPrefUpdate(String key, Boolean value) {
		
		if (key.equals("viewDefault")) {
			voucher.setViewDefault(value);
		}
		if (key.equals("voucherId")) {
			voucher.setVoucherId(value);
		}
		if (key.equals("stakingSheetId")) {
			voucher.setStakingSheetId(value);
		}
		if (key.equals("workOrderId")) {
			voucher.setWorkOrderId(value);
		}
		if (key.equals("serviceOrderId")) {
			voucher.setServiceOrderId(value);
		}
		if (key.equals("stationDescription")) {
			voucher.setStationDescription(value);
		}
		if (key.equals("description")) {
			voucher.setDescription(value);
		}
		if (key.equals("crew")) {
			voucher.setCrew(value);
		}
		if (key.equals("amount")) {
			voucher.setAmount(value);
		}
		if (key.equals("requestor")) {
			voucher.setRequestor(value);
		}
		if (key.equals("createdDt")) {
			voucher.setCreatedDt(value);
		}
		if (key.equals("GLAccountId")) {
			voucher.setGLAccountId(value);
		}
		if (key.equals("GLAccountIdSplit")) {
			voucher.setGLAccountIdSplit(value);
		}
		if (key.equals("inspectionId")) {
			voucher.setInspectionId(value);
		}
		if (key.equals("inspectionStatusId")) {
			voucher.setInspectionStatusId(value);
		}
		if (key.equals("inspectionComment")) {
			voucher.setInspectionComment(value);
		}
		if (key.equals("invoiceStatusId")) {
			voucher.setInvoiceStatusId(value);
		}
		if (key.equals("invoiceId")) {
			voucher.setInvoiceId(value);
		}
		if (key.equals("invoiceDetailId")) {
			voucher.setInvoiceDetailId(value);
		}
		if (key.equals("approvedBy")) {
			voucher.setApprovedBy(value);
		}
		if (key.equals("approvedDt")) {
			voucher.setApprovedDt(value);
		}
		if (key.equals("approvedComment")) {
			voucher.setApprovedComment(value);
		}
		if (key.equals("submitGuid")) {
			voucher.setSubmitGuid(value);
		}
		return voucher;
	}
	
	public UserPrefInvoiceGLSummaryVw buildInvoiceDetailPrefUpdate(String key, Boolean value) {
		if (key.equals("viewDefault")) {
			invoiceDetail.setViewDefault(value);
		}
		if (key.equals("stakingSheetDetailId")) {
			invoiceDetail.setStakingSheetDetailId(value);
		}
		if (key.equals("stakingSheetId")) {
			invoiceDetail.setStakingSheetId(value);
		}
		if (key.equals("invoiceId")) {
			invoiceDetail.setInvoiceId(value);
		}
		if (key.equals("stationDescription")) {
			invoiceDetail.setStationDescription(value);
		}
		if (key.equals("assemblyActionCode")) {
			invoiceDetail.setAssemblyActionCode(value);
		}
		if (key.equals("assemblyGuid")) {
			invoiceDetail.setAssemblyGuid(value);
		}
		if (key.equals("assemblyRateGroupId")) {
			invoiceDetail.setAssemblyRateGroupId(value);
		}
		if (key.equals("assemblyDescription")) {
			invoiceDetail.setAssemblyDescription(value);
		}
		if (key.equals("assemblyQuantity")) {
			invoiceDetail.setAssemblyQuantity(value);
		}
		if (key.equals("constCost")) {
			invoiceDetail.setConstCost(value);
		}
		if (key.equals("constGLAccount")) {
			invoiceDetail.setConstGLAccount(value);
		}
		if (key.equals("retireCost")) {
			invoiceDetail.setRetireCost(value);
		}
		if (key.equals("retireGlAccount")) {
			invoiceDetail.setRetireGlAccount(value);
		}
		if (key.equals("invoiceStatusId")) {
			invoiceDetail.setInvoiceStatusId(value);
		}
		if (key.equals("invoiceStatus")) {
			invoiceDetail.setInvoiceStatus(value);
		}
		if (key.equals("invoiceApprovedComment")) {
			invoiceDetail.setInvoiceApprovedComment(value);
		}
		if (key.equals("extCost")) {
			invoiceDetail.setExtCost(value);
		}
		if (key.equals("energize")) {
			invoiceDetail.setEnergize(value);
		}
		if (key.equals("transfer")) {
			invoiceDetail.setTransfer(value);
		}
		return invoiceDetail;
	}

	public UserPrefStakingSheetDetail buildstakingsheetDetPrefUpdate(String key, Boolean value) {
		
		if (key.equals("viewDefault")) {
			stakingDet.setViewDefault(value);
		}
		if (key.equals("stakingSheetDetailId")) {
			stakingDet.setStakingSheetDetailId(value);
		}
		if (key.equals("stakingSheetId")) {
			stakingDet.setStakingSheetId(value);
		}
		if (key.equals("stationDescription")) {
			stakingDet.setStationDescription(value);
		}
		if (key.equals("stakingSource")) {
			stakingDet.setStakingSource(value);
		}
		if (key.equals("assemblyGuid")) {
			stakingDet.setAssemblyGuid(value);
		}
		if (key.equals("assemblyRateGroupId")) {
			stakingDet.setAssemblyRateGroupId(value);
		}
		if (key.equals("assemblyDescription")) {
			stakingDet.setAssemblyDescription(value);
		}
		if (key.equals("assemblyQuantity")) {
			stakingDet.setAssemblyQuantity(value);
		}
		if (key.equals("assemblyAmount")) {
			stakingDet.setAssemblyAmount(value);
		}
		if (key.equals("assemblyActionCode")) {
			stakingDet.setAssemblyActionCode(value);
		}
		if (key.equals("assemblyCreatedDt")) {
			stakingDet.setAssemblyCreatedDt(value);
		}
		if (key.equals("assemblyModifiedDt")) {
			stakingDet.setAssemblyModifiedDt(value);
		}
		if (key.equals("stStatusRefGuid")) {
			stakingDet.setStStatusRefGuid(value);
		}
		if (key.equals("statusDescription")) {
			stakingDet.setStatusDescription(value);
		}
		if (key.equals("asBuiltQuantity")) {
			stakingDet.setAsBuiltQuantity(value);
		}
		if (key.equals("asBuiltAmount")) {
			stakingDet.setAsBuiltAmount(value);
		}
		if (key.equals("asBuiltStatusId")) {
			stakingDet.setAsBuiltStatusId(value);
		}
		if (key.equals("asBuiltDt")) {
			stakingDet.setAsBuiltDt(value);
		}
		if (key.equals("asBuiltComments")) {
			stakingDet.setAsBuiltComments(value);
		}
		if (key.equals("asBuiltBy")) {
			stakingDet.setAsBuiltBy(value);
		}
		if (key.equals("lcecNotes")) {
			stakingDet.setLcecNotes(value);
		}
		if (key.equals("GL_AccountId")) {
			stakingDet.setGL_AccountId(value);
		}
		if (key.equals("currentInspectionDetailId")) {
			stakingDet.setCurrentInspectionDetailId(value);
		}
		if (key.equals("currentInspectionDetailStatusId")) {
			stakingDet.setCurrentInspectionDetailStatusId(value);
		}
		if (key.equals("currentInspectionDetailDt")) {
			stakingDet.setCurrentInspectionDetailDt(value);
		}
		if (key.equals("currentInspectorDetailComments")) {
			stakingDet.setCurrentInspectorDetailComments(value);
		}
		if (key.equals("currentInspectedDetailBy")) {
			stakingDet.setCurrentInspectedDetailBy(value);
		}
		if (key.equals("invoiceStatusId")) {
			stakingDet.setInvoiceStatusId(value);
		}
		if (key.equals("invoiceId")) {
			stakingDet.setInvoiceId(value);
		}
		if (key.equals("invoiceDetailId")) {
			stakingDet.setInvoiceDetailId(value);
		}
		if (key.equals("invoiceSubmitGuid")) {
			stakingDet.setInvoiceSubmitGuid(value);
		}
		if (key.equals("invoiceApprovedBy")) {
			stakingDet.setInvoiceApprovedBy(value);
		}
		if (key.equals("invoiceApprovedDt")) {
			stakingDet.setInvoiceApprovedDt(value);
		}
		if (key.equals("invoiceApprovedComment")) {
			stakingDet.setInvoiceApprovedComment(value);
		}
		if (key.equals("energize")) {
			stakingDet.setEnergize(value);
		}
		if (key.equals("transfer")) {
			stakingDet.setTransfer(value);
		}
		if (key.equals("invoicedBy")) {
			stakingDet.setInvoicedBy(value);
		}

		return stakingDet;
	}
	
	public UserPrefInvoiceSearch buildInvSearchPrefUpdate(String key, Boolean value) {
		if (key.equals("viewDefault")) {
			invSearch.setViewDefault(value);
		}
		if (key.equals("action")) {
			invSearch.setAction(value);
		}
		if (key.equals("lcecRef")) {
			invSearch.setLCECReference(value);
		}
		if (key.equals("vendorReference")) {
			invSearch.setVendorReference(value);
		}
		if (key.equals("invoiceDt")) {
			invSearch.setInvoiceDt(value);
		}
		if (key.equals("invoiceStatusId")) {
			invSearch.setInvoiceStatusId(value);
		}
		if (key.equals("invoiceAmount")) {
			invSearch.setInvoiceAmount(value);
		}
		if (key.equals("workOrderId")) {
			invSearch.setWorkOrderId(value);
		}
		if (key.equals("vendorName")) {
			invSearch.setVendorName(value);
		}
		if (key.equals("invoiceType")) {
			invSearch.setInvoiceType(value);
		}
		if (key.equals("invoiceBy")) {
			invSearch.setInvoiceBy(value);
		}
		if (key.equals("approvedDt")) {
			invSearch.setApprovedBy(value);
		}
		if (key.equals("approvedBy")) {
			invSearch.setApprovedBy(value);
		}
		if (key.equals("businessRuleFlg")) {
			invSearch.setBusinessRuleFlg(value);
		}
		if (key.equals("vendorId")) {
			invSearch.setVendorId(value);
		}
		if (key.equals("workFlowId")) {
			invSearch.setWorkFlowId(value);
		}
		if (key.equals("serviceOrderId")) {
			invSearch.setServiceOrderId(value);
		}
		if (key.equals("paymentDt")) {
			invSearch.setPaymentDt(value);
		}
		if (key.equals("paymentStatus")) {
			invSearch.setPaymentStatus(value);
		}
		if (key.equals("apInvId")) {
			invSearch.setApInvId(value);
		}
		if (key.equals("workGroup")) {
			invSearch.setWorkGroup(value);
		}

		return invSearch;
	}
	
	public UserPrefInspectionSearch buildInspSearchPrefUpdate(String key, Boolean value) {

		if (key.equals("viewDefault")) {
			inspSearch.setViewDefault(value);
		}
		if (key.equals("inspectionId")) {
			inspSearch.setInspectionId(value);
		}
		if (key.equals("inspectionDt")) {
			inspSearch.setInspectionDt(value);
		}
		if (key.equals("inspectedBy")) {
			inspSearch.setInspectedBy(value);
		}
		if (key.equals("inspectionStatusId")) {
			inspSearch.setInspectionStatusId(value);
		}
		if (key.equals("inspectionType")) {
			inspSearch.setInspectionType(value);
		}
		if (key.equals("comment")) {
			inspSearch.setComments(value);
		}
		if (key.equals("workOrderId")) {
			inspSearch.setWorkOrderId(value);
		}
		if (key.equals("workFlowId")) {
			inspSearch.setWorkFlowId(value);
		}
		if (key.equals("serviceOrderId")) {
			inspSearch.setServiceOrderId(value);
		}
		if (key.equals("workgroup")) {
			inspSearch.setWorkgroup(value);
		}

		return inspSearch;
	}
	
	public UserPrefProjectWorkflow buildProjectWFPrefUpdate(String key, Boolean value) {
		
		if(key.equals("viewDefault")) {
			projectWorkflow.setViewDefault(value);
		}
		if(key.equals("projectWorkFlowId")) {
			projectWorkflow.setProjectWorkFlowId(value);
		}
		if(key.equals("workFlowId")) {
			projectWorkflow.setWorkFlowId(value);
		}
		if(key.equals("serviceOrderId")) {
			projectWorkflow.setServiceOrderId(value);
		}
		if(key.equals("serviceOrderType")) {
			projectWorkflow.setServiceOrderType(value);
		}
		if(key.equals("workOrderId")) {
			projectWorkflow.setWorkOrderId(value);
		}
		if(key.equals("workOrderName")) {
			projectWorkflow.setWorkOrderName(value);
		}
		if(key.equals("workGroup")) {
			projectWorkflow.setWorkGroup(value);
		}
		if(key.equals("workEventStatusId")) {
			projectWorkflow.setWorkEventStatusId(value);
		}
		if(key.equals("workEventDt")) {
			projectWorkflow.setWorkEventDt(value);
		}
		if(key.equals("budgetedAmount")) {
			projectWorkflow.setBudgetedAmount(value);
		}
		if(key.equals("estimatedAmount")) {
			projectWorkflow.setEstimatedAmount(value);
		}
		if(key.equals("designedAmount")) {
			projectWorkflow.setDesignedAmount(value);
		}
		if(key.equals("builtAmount")) {
			projectWorkflow.setBuiltAmount(value);
		}
		if(key.equals("invoicedAmount")) {
			projectWorkflow.setInvoicedAmount(value);
		}
		if(key.equals("paidAmount")) {
			projectWorkflow.setPaidAmount(value);
		}
		if(key.equals("totalAmount")) {
			projectWorkflow.setTotalAmount(value);
		}
				
		return projectWorkflow;
	}

	public UserPrefWorkFlowSearch buildWFPrefUpdate(String key, Boolean value) {

		if (key.equals("viewDefault")) {
			wfSearch.setViewDefault(value);
		}
		if (key.equals("workFlowId")) {
			wfSearch.setWorkFlowId(value);
		}
		if (key.equals("workFlowId")) {
			wfSearch.setNeededDt(value);
		}
		if (key.equals("workEventDt")) {
			wfSearch.setWorkEventDt(value);
		}
		if (key.equals("serviceOrderId")) {
			wfSearch.setServiceOrderId(value);
		}
		if (key.equals("serviceOrderPriority")) {
			wfSearch.setServiceOrderPriority(value);
		}
		if (key.equals("workGroup")) {
			wfSearch.setWorkGroup(value);
		}
		if (key.equals("workOrderId")) {
			wfSearch.setWorkOrderId(value);
		}
		if (key.equals("workOrderName")) {
			wfSearch.setWorkOrderName(value);
		}
		if (key.equals("eventStatusId")) {
			wfSearch.setWorkEventStatusId(value);
		}
		if (key.equals("workEventStatus")) {
			wfSearch.setWorkEventStatus(value);
		}
		if (key.equals("assignedVendorId")) {
			wfSearch.setAssignedVendorId(value);
		}
		if (key.equals("accountId")) {
			wfSearch.setAccountId(value);
		}
		if (key.equals("serviceLocationId")) {
			wfSearch.setServiceLocationId(value);
		}
		if (key.equals("openDt")) {
			wfSearch.setOpenDt(value);
		}
		if (key.equals("resourceId")) {
			wfSearch.setResourceId(value);
		}
		if (key.equals("resourceName")) {
			wfSearch.setResourceName(value);
		}
		if (key.equals("stakingSheetId")) {
			wfSearch.setStakingSheetId(value);
		}
		if (key.equals("overallAsBuiltStatusId")) {
			wfSearch.setOverallAsBuiltStatusId(value);
		}
		if (key.equals("overallAsBuiltStatus")) {
			wfSearch.setOverallAsBuiltStatus(value);
		}
		if (key.equals("overallInspectionStatusId")) {
			wfSearch.setOverallInspectionStatusId(value);
		}
		if (key.equals("overallInspectionStatus")) {
			wfSearch.setOverallInspectionStatus(value);
		}
		if (key.equals("overallInvoiceStatusId")) {
			wfSearch.setOverallInvoiceStatusId(value);
		}
		if (key.equals("overallInvoiceStatus")) {
			wfSearch.setOverallInvoiceStatus(value);
		}
		if (key.equals("soTypeCode")) {
			wfSearch.setSoTypeCode(value);
		}
		if (key.equals("soTypeCodeDescription")) {
			wfSearch.setSoTypeCodeDescription(value);
		}
		if (key.equals("enterTypeCode")) {
			wfSearch.setEnterTypeCode(value);
		}
		if (key.equals("soFullName")) {
			wfSearch.setSoFullName(value);
		}
		if (key.equals("soStatCode")) {
			wfSearch.setSoStatCode(value);
		}
		if (key.equals("userName")) {
			wfSearch.setUserName(value);
		}
		if (key.equals("soFunction")) {
			wfSearch.setSoFunction(value);
		}
		if (key.equals("LCECComments")) {
			wfSearch.setLCECComments(value);
		}

		return wfSearch;
	}

	public UserPrefWorkFlowSearch getWfSearch() {
		return wfSearch;
	}

	public void setWfSearch(UserPrefWorkFlowSearch wfSearch) {
		this.wfSearch = wfSearch;
	}

	public UserTbl getUser() {
		return user;
	}

	public void setUser(UserTbl user) {
		this.user = user;
	}

	public String getTblName() {
		return tblName;
	}

	public void setTblName(String tblName) {
		this.tblName = tblName;
	}

	public UserPrefInspectionSearch getInspSearch() {
		return inspSearch;
	}

	public void setInspSearch(UserPrefInspectionSearch inspSearch) {
		this.inspSearch = inspSearch;
	}

	public UserPrefInvoiceSearch getInvSearch() {
		return invSearch;
	}

	public void setInvSearch(UserPrefInvoiceSearch invSearch) {
		this.invSearch = invSearch;
	}

	public UserPrefStakingSheetDetail getStakingDet() {
		return stakingDet;
	}

	public void setStakingDet(UserPrefStakingSheetDetail stakingDet) {
		this.stakingDet = stakingDet;
	}

	public UserPrefInvoiceGLSummaryVw getInvoiceDetail() {
		return invoiceDetail;
	}

	public void setInvoiceDetail(UserPrefInvoiceGLSummaryVw invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}

	public UserPrefVoucher getVoucher() {
		return voucher;
	}

	public void setVoucher(UserPrefVoucher voucher) {
		this.voucher = voucher;
	}

	public UserPrefVoucher getVoucherNewInsp() {
		return voucherNewInsp;
	}

	public void setVoucherNewInsp(UserPrefVoucher voucherNewInsp) {
		this.voucherNewInsp = voucherNewInsp;
	}

	public UserPrefInspection getInspTab() {
		return inspTab;
	}

	public void setInspTab(UserPrefInspection inspTab) {
		this.inspTab = inspTab;
	}

	public UserPrefInvoice getInvoiceTab() {
		return invoiceTab;
	}

	public void setInvoiceTab(UserPrefInvoice invoiceTab) {
		this.invoiceTab = invoiceTab;
	}

	public UserPrefProjectWorkflow getProjectWorkflow() {
		return projectWorkflow;
	}

	public void setProjectWorkflow(UserPrefProjectWorkflow projectWorkflow) {
		this.projectWorkflow = projectWorkflow;
	}

}
