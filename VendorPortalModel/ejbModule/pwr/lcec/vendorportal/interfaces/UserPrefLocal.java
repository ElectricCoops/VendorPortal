package pwr.lcec.vendorportal.interfaces;

import javax.ejb.Local;

import pwr.lcec.vendorportal.entity.userpref.UserPrefInspection;
import pwr.lcec.vendorportal.entity.userpref.UserPrefInspectionSearch;
import pwr.lcec.vendorportal.entity.userpref.UserPrefInvoice;
import pwr.lcec.vendorportal.entity.userpref.UserPrefInvoiceGLSummaryVw;
import pwr.lcec.vendorportal.entity.userpref.UserPrefInvoiceSearch;
import pwr.lcec.vendorportal.entity.userpref.UserPrefProjectWorkflow;
import pwr.lcec.vendorportal.entity.userpref.UserPrefStakingSheetDetail;
import pwr.lcec.vendorportal.entity.userpref.UserPrefVoucher;
import pwr.lcec.vendorportal.entity.userpref.UserPrefWorkFlowSearch;

@Local
public interface UserPrefLocal {
	
public UserPrefWorkFlowSearch getWFSearchPrefByUserId(Integer userId);
	
	public UserPrefWorkFlowSearch updateWFSearchPref(UserPrefWorkFlowSearch userPrefWorkFlowSearch);
	
	public UserPrefInspectionSearch getInspSearchPrefByUserId(Integer userId);
	
	public UserPrefInspectionSearch updateInspSearchPref(UserPrefInspectionSearch userPrefInspectionSearch);
	
	public UserPrefInvoiceSearch getInvSearchPrefByUserId(Integer userId);
	
	public UserPrefInvoiceSearch updateInvSearchPref(UserPrefInvoiceSearch userPrefInvoiceSearch);
	
	public UserPrefStakingSheetDetail getStakingSheetDetailPrefByUserId(Integer userId, String viewName);
	
	public UserPrefStakingSheetDetail updateStakingSheetDetailPref(UserPrefStakingSheetDetail userPrefStakingSheetDetail);
	
	public UserPrefInvoiceGLSummaryVw getInvoiceGLSummaryPrefByUserId(Integer userId);
	
	public UserPrefInvoiceGLSummaryVw updateInvoiceGLSummaryPref(UserPrefInvoiceGLSummaryVw userPrefInvoiceGLSummaryVw);
	
	public UserPrefVoucher getVoucherPrefByUserId(Integer userId, String viewName);
	
	public UserPrefVoucher updateVoucherPref(UserPrefVoucher userPrefVoucher);
	
	public UserPrefInvoice getInvoicePrefByUserId(Integer userId, String viewName);
	
	public UserPrefInvoice updateInvoicePref(UserPrefInvoice userPrefInvoice);
	
	public UserPrefInspection getInspectionPrefByUserId(Integer userId, String viewName);
	
	public UserPrefInspection updateInspectionPref(UserPrefInspection userPrefInspection);

	public UserPrefProjectWorkflow updateProjectWoPref(UserPrefProjectWorkflow projectWorkflow);

	public UserPrefProjectWorkflow getProjectWorkFlowPrefByUserId(Integer userID);

}
