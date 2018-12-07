package pwr.lcec.vendorportal.ivue.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_WRKFLW_TASKS database table.
 * 
 */
@Entity
@Table(name="BI_WRKFLW_TASKS", schema="CIS11013")
@NamedQueries({ 
@NamedQuery(name="BiWrkflwTask.findAll", query="SELECT b FROM BiWrkflwTask b"),
@NamedQuery(name="BiWrkflwTask.updateWorkEventCd", query="SELECT b FROM BiWrkflwTask b"),
})
public class BiWrkflwTask implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BI_WRKFLW_TASKS_KEY")
	private long biWrkflwTasksKey;

	@Column(name="BI_ASSG_EXISTS_SW")
	private String biAssgExistsSw;

	@Column(name="BI_CLOSE_CD")
	private String biCloseCd;

	@Column(name="BI_CRITICAL_TASK_SW")
	private String biCriticalTaskSw;

	@Column(name="BI_DEPEND_COMPLETE_SW")
	private String biDependCompleteSw;

	@Column(name="BI_DEPEND_ON_PREV_SW")
	private String biDependOnPrevSw;

	@Column(name="BI_DT_TM_CD")
	private String biDtTmCd;

	@Column(name="BI_DURATION_DAYS")
	private BigDecimal biDurationDays;

	@Column(name="BI_ELECT_SIG_ID")
	private String biElectSigId;

	@Temporal(TemporalType.DATE)
	@Column(name="BI_EVENT_DT_TM")
	private Date biEventDtTm;

	@Column(name="BI_EVENT_REAS_CD")
	private String biEventReasCd;

	@Column(name="BI_FOLLOW_UP_TASK_SW")
	private String biFollowUpTaskSw;

	@Column(name="BI_GL_ACCOUNT")
	private BigDecimal biGlAccount;

	@Column(name="BI_GL_ACTCD")
	private String biGlActcd;

	@Column(name="BI_GL_DEPARTMENT")
	private BigDecimal biGlDepartment;

	@Column(name="BI_GL_DIV")
	private BigDecimal biGlDiv;

	@Column(name="BI_GL_SUB_ACCT")
	private BigDecimal biGlSubAcct;

	@Column(name="BI_INITIATE_PARAMS")
	private String biInitiateParams;

	@Column(name="BI_INITIATE_SCREEN_ID")
	private String biInitiateScreenId;

	@Column(name="BI_INITIATED_SW")
	private String biInitiatedSw;

	@Column(name="BI_IVR_SW")
	private String biIvrSw;

	@Column(name="BI_KEY_NBR_1")
	private BigDecimal biKeyNbr1;

	@Column(name="BI_KEY_NBR_2")
	private BigDecimal biKeyNbr2;

	@Column(name="BI_LOCKED_BY")
	private BigDecimal biLockedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="BI_LOCKED_DT_TM")
	private Date biLockedDtTm;

	@Column(name="BI_MSG_TARGET_CD")
	private String biMsgTargetCd;

	@Column(name="BI_NEEDED_CD")
	private String biNeededCd;

	@Temporal(TemporalType.DATE)
	@Column(name="BI_NEEDED_DT_TM")
	private Date biNeededDtTm;

	@Temporal(TemporalType.DATE)
	@Column(name="BI_NOTIFIED_DT_TM")
	private Date biNotifiedDtTm;

	@Column(name="BI_PREDECESSOR_TASK_KEY")
	private BigDecimal biPredecessorTaskKey;

	@Column(name="BI_PRT_DELAY_DAYS")
	private BigDecimal biPrtDelayDays;

	@Column(name="BI_PRT_DELAY_HRS_MIN")
	private BigDecimal biPrtDelayHrsMin;

	@Column(name="BI_PRT_FROM")
	private String biPrtFrom;

	@Column(name="BI_REQUIRED_SW")
	private String biRequiredSw;

	@Temporal(TemporalType.DATE)
	@Column(name="BI_SCHED_DT_TM")
	private Date biSchedDtTm;

	@Column(name="BI_SO_PRTY_CD")
	private String biSoPrtyCd;

	@Column(name="BI_TASK_ACT_COST")
	private BigDecimal biTaskActCost;

	@Column(name="BI_TASK_ACT_HRS")
	private BigDecimal biTaskActHrs;

	@Column(name="BI_TASK_CD")
	private String biTaskCd;

	@Column(name="BI_TASK_EST_COST")
	private BigDecimal biTaskEstCost;

	@Column(name="BI_TASK_EST_HRS")
	private BigDecimal biTaskEstHrs;

	@Column(name="BI_TASK_HANDLE_CD")
	private String biTaskHandleCd;

	@Column(name="BI_TASK_ORIG_EST_HRS")
	private BigDecimal biTaskOrigEstHrs;

	@Column(name="BI_TASK_REM_HRS")
	private BigDecimal biTaskRemHrs;

	@Column(name="BI_TASK_RMKS")
	private String biTaskRmks;

	@Column(name="BI_TASK_SO_EQUIP")
	private String biTaskSoEquip;

	@Column(name="BI_TASK_TITLE")
	private String biTaskTitle;

	@Column(name="BI_WO_JOB")
	private String biWoJob;

	@Column(name="BI_WO_LOAN_DESGN")
	private String biWoLoanDesgn;

	@Column(name="BI_WO_LOAN_PROJ")
	private String biWoLoanProj;

	@Column(name="BI_WO_LOAN_PROJ_EXT")
	private String biWoLoanProjExt;

	@Column(name="BI_WO_LOAN_YR")
	private BigDecimal biWoLoanYr;

	@Column(name="BI_WO_PROJECT")
	private String biWoProject;

	@Column(name="BI_WO_WORKORD")
	private String biWoWorkord;

	@Column(name="BI_WORK_EVENT_CD")
	private String biWorkEventCd;

	@Column(name="BI_WORKGRP")
	private String biWorkgrp;

	@Column(name="BI_WRKFLW_KEY")
	private BigDecimal biWrkflwKey;

	@Column(name="BI_WRKFLW_TASK_SEQ_NBR")
	private BigDecimal biWrkflwTaskSeqNbr;

	@Column(name="BI_WRKFLW_TASKS_CTR")
	private BigDecimal biWrkflwTasksCtr;

	@Column(name="SI_TASK_GRP")
	private String siTaskGrp;

	@Column(name="SY_JOB_DEFINITION_ID")
	private BigDecimal syJobDefinitionId;

	@Column(name="SY_REPORT_ID")
	private String syReportId;

	@Column(name="SY_REPORT_PARAMS")
	private String syReportParams;

	@Column(name="SY_USER_ID")
	private BigDecimal syUserId;

	@Column(name="WM_X_COORD")
	private String wmXCoord;

	@Column(name="WM_Y_COORD")
	private String wmYCoord;

	@Column(name="WM_Z_COORD")
	private String wmZCoord;

	public BiWrkflwTask() {
	}

	public long getBiWrkflwTasksKey() {
		return this.biWrkflwTasksKey;
	}

	public void setBiWrkflwTasksKey(long biWrkflwTasksKey) {
		this.biWrkflwTasksKey = biWrkflwTasksKey;
	}

	public String getBiAssgExistsSw() {
		return this.biAssgExistsSw;
	}

	public void setBiAssgExistsSw(String biAssgExistsSw) {
		this.biAssgExistsSw = biAssgExistsSw;
	}

	public String getBiCloseCd() {
		return this.biCloseCd;
	}

	public void setBiCloseCd(String biCloseCd) {
		this.biCloseCd = biCloseCd;
	}

	public String getBiCriticalTaskSw() {
		return this.biCriticalTaskSw;
	}

	public void setBiCriticalTaskSw(String biCriticalTaskSw) {
		this.biCriticalTaskSw = biCriticalTaskSw;
	}

	public String getBiDependCompleteSw() {
		return this.biDependCompleteSw;
	}

	public void setBiDependCompleteSw(String biDependCompleteSw) {
		this.biDependCompleteSw = biDependCompleteSw;
	}

	public String getBiDependOnPrevSw() {
		return this.biDependOnPrevSw;
	}

	public void setBiDependOnPrevSw(String biDependOnPrevSw) {
		this.biDependOnPrevSw = biDependOnPrevSw;
	}

	public String getBiDtTmCd() {
		return this.biDtTmCd;
	}

	public void setBiDtTmCd(String biDtTmCd) {
		this.biDtTmCd = biDtTmCd;
	}

	public BigDecimal getBiDurationDays() {
		return this.biDurationDays;
	}

	public void setBiDurationDays(BigDecimal biDurationDays) {
		this.biDurationDays = biDurationDays;
	}

	public String getBiElectSigId() {
		return this.biElectSigId;
	}

	public void setBiElectSigId(String biElectSigId) {
		this.biElectSigId = biElectSigId;
	}

	public Date getBiEventDtTm() {
		return this.biEventDtTm;
	}

	public void setBiEventDtTm(Date biEventDtTm) {
		this.biEventDtTm = biEventDtTm;
	}

	public String getBiEventReasCd() {
		return this.biEventReasCd;
	}

	public void setBiEventReasCd(String biEventReasCd) {
		this.biEventReasCd = biEventReasCd;
	}

	public String getBiFollowUpTaskSw() {
		return this.biFollowUpTaskSw;
	}

	public void setBiFollowUpTaskSw(String biFollowUpTaskSw) {
		this.biFollowUpTaskSw = biFollowUpTaskSw;
	}

	public BigDecimal getBiGlAccount() {
		return this.biGlAccount;
	}

	public void setBiGlAccount(BigDecimal biGlAccount) {
		this.biGlAccount = biGlAccount;
	}

	public String getBiGlActcd() {
		return this.biGlActcd;
	}

	public void setBiGlActcd(String biGlActcd) {
		this.biGlActcd = biGlActcd;
	}

	public BigDecimal getBiGlDepartment() {
		return this.biGlDepartment;
	}

	public void setBiGlDepartment(BigDecimal biGlDepartment) {
		this.biGlDepartment = biGlDepartment;
	}

	public BigDecimal getBiGlDiv() {
		return this.biGlDiv;
	}

	public void setBiGlDiv(BigDecimal biGlDiv) {
		this.biGlDiv = biGlDiv;
	}

	public BigDecimal getBiGlSubAcct() {
		return this.biGlSubAcct;
	}

	public void setBiGlSubAcct(BigDecimal biGlSubAcct) {
		this.biGlSubAcct = biGlSubAcct;
	}

	public String getBiInitiateParams() {
		return this.biInitiateParams;
	}

	public void setBiInitiateParams(String biInitiateParams) {
		this.biInitiateParams = biInitiateParams;
	}

	public String getBiInitiateScreenId() {
		return this.biInitiateScreenId;
	}

	public void setBiInitiateScreenId(String biInitiateScreenId) {
		this.biInitiateScreenId = biInitiateScreenId;
	}

	public String getBiInitiatedSw() {
		return this.biInitiatedSw;
	}

	public void setBiInitiatedSw(String biInitiatedSw) {
		this.biInitiatedSw = biInitiatedSw;
	}

	public String getBiIvrSw() {
		return this.biIvrSw;
	}

	public void setBiIvrSw(String biIvrSw) {
		this.biIvrSw = biIvrSw;
	}

	public BigDecimal getBiKeyNbr1() {
		return this.biKeyNbr1;
	}

	public void setBiKeyNbr1(BigDecimal biKeyNbr1) {
		this.biKeyNbr1 = biKeyNbr1;
	}

	public BigDecimal getBiKeyNbr2() {
		return this.biKeyNbr2;
	}

	public void setBiKeyNbr2(BigDecimal biKeyNbr2) {
		this.biKeyNbr2 = biKeyNbr2;
	}

	public BigDecimal getBiLockedBy() {
		return this.biLockedBy;
	}

	public void setBiLockedBy(BigDecimal biLockedBy) {
		this.biLockedBy = biLockedBy;
	}

	public Date getBiLockedDtTm() {
		return this.biLockedDtTm;
	}

	public void setBiLockedDtTm(Date biLockedDtTm) {
		this.biLockedDtTm = biLockedDtTm;
	}

	public String getBiMsgTargetCd() {
		return this.biMsgTargetCd;
	}

	public void setBiMsgTargetCd(String biMsgTargetCd) {
		this.biMsgTargetCd = biMsgTargetCd;
	}

	public String getBiNeededCd() {
		return this.biNeededCd;
	}

	public void setBiNeededCd(String biNeededCd) {
		this.biNeededCd = biNeededCd;
	}

	public Date getBiNeededDtTm() {
		return this.biNeededDtTm;
	}

	public void setBiNeededDtTm(Date biNeededDtTm) {
		this.biNeededDtTm = biNeededDtTm;
	}

	public Date getBiNotifiedDtTm() {
		return this.biNotifiedDtTm;
	}

	public void setBiNotifiedDtTm(Date biNotifiedDtTm) {
		this.biNotifiedDtTm = biNotifiedDtTm;
	}

	public BigDecimal getBiPredecessorTaskKey() {
		return this.biPredecessorTaskKey;
	}

	public void setBiPredecessorTaskKey(BigDecimal biPredecessorTaskKey) {
		this.biPredecessorTaskKey = biPredecessorTaskKey;
	}

	public BigDecimal getBiPrtDelayDays() {
		return this.biPrtDelayDays;
	}

	public void setBiPrtDelayDays(BigDecimal biPrtDelayDays) {
		this.biPrtDelayDays = biPrtDelayDays;
	}

	public BigDecimal getBiPrtDelayHrsMin() {
		return this.biPrtDelayHrsMin;
	}

	public void setBiPrtDelayHrsMin(BigDecimal biPrtDelayHrsMin) {
		this.biPrtDelayHrsMin = biPrtDelayHrsMin;
	}

	public String getBiPrtFrom() {
		return this.biPrtFrom;
	}

	public void setBiPrtFrom(String biPrtFrom) {
		this.biPrtFrom = biPrtFrom;
	}

	public String getBiRequiredSw() {
		return this.biRequiredSw;
	}

	public void setBiRequiredSw(String biRequiredSw) {
		this.biRequiredSw = biRequiredSw;
	}

	public Date getBiSchedDtTm() {
		return this.biSchedDtTm;
	}

	public void setBiSchedDtTm(Date biSchedDtTm) {
		this.biSchedDtTm = biSchedDtTm;
	}

	public String getBiSoPrtyCd() {
		return this.biSoPrtyCd;
	}

	public void setBiSoPrtyCd(String biSoPrtyCd) {
		this.biSoPrtyCd = biSoPrtyCd;
	}

	public BigDecimal getBiTaskActCost() {
		return this.biTaskActCost;
	}

	public void setBiTaskActCost(BigDecimal biTaskActCost) {
		this.biTaskActCost = biTaskActCost;
	}

	public BigDecimal getBiTaskActHrs() {
		return this.biTaskActHrs;
	}

	public void setBiTaskActHrs(BigDecimal biTaskActHrs) {
		this.biTaskActHrs = biTaskActHrs;
	}

	public String getBiTaskCd() {
		return this.biTaskCd;
	}

	public void setBiTaskCd(String biTaskCd) {
		this.biTaskCd = biTaskCd;
	}

	public BigDecimal getBiTaskEstCost() {
		return this.biTaskEstCost;
	}

	public void setBiTaskEstCost(BigDecimal biTaskEstCost) {
		this.biTaskEstCost = biTaskEstCost;
	}

	public BigDecimal getBiTaskEstHrs() {
		return this.biTaskEstHrs;
	}

	public void setBiTaskEstHrs(BigDecimal biTaskEstHrs) {
		this.biTaskEstHrs = biTaskEstHrs;
	}

	public String getBiTaskHandleCd() {
		return this.biTaskHandleCd;
	}

	public void setBiTaskHandleCd(String biTaskHandleCd) {
		this.biTaskHandleCd = biTaskHandleCd;
	}

	public BigDecimal getBiTaskOrigEstHrs() {
		return this.biTaskOrigEstHrs;
	}

	public void setBiTaskOrigEstHrs(BigDecimal biTaskOrigEstHrs) {
		this.biTaskOrigEstHrs = biTaskOrigEstHrs;
	}

	public BigDecimal getBiTaskRemHrs() {
		return this.biTaskRemHrs;
	}

	public void setBiTaskRemHrs(BigDecimal biTaskRemHrs) {
		this.biTaskRemHrs = biTaskRemHrs;
	}

	public String getBiTaskRmks() {
		return this.biTaskRmks;
	}

	public void setBiTaskRmks(String biTaskRmks) {
		this.biTaskRmks = biTaskRmks;
	}

	public String getBiTaskSoEquip() {
		return this.biTaskSoEquip;
	}

	public void setBiTaskSoEquip(String biTaskSoEquip) {
		this.biTaskSoEquip = biTaskSoEquip;
	}

	public String getBiTaskTitle() {
		return this.biTaskTitle;
	}

	public void setBiTaskTitle(String biTaskTitle) {
		this.biTaskTitle = biTaskTitle;
	}

	public String getBiWoJob() {
		return this.biWoJob;
	}

	public void setBiWoJob(String biWoJob) {
		this.biWoJob = biWoJob;
	}

	public String getBiWoLoanDesgn() {
		return this.biWoLoanDesgn;
	}

	public void setBiWoLoanDesgn(String biWoLoanDesgn) {
		this.biWoLoanDesgn = biWoLoanDesgn;
	}

	public String getBiWoLoanProj() {
		return this.biWoLoanProj;
	}

	public void setBiWoLoanProj(String biWoLoanProj) {
		this.biWoLoanProj = biWoLoanProj;
	}

	public String getBiWoLoanProjExt() {
		return this.biWoLoanProjExt;
	}

	public void setBiWoLoanProjExt(String biWoLoanProjExt) {
		this.biWoLoanProjExt = biWoLoanProjExt;
	}

	public BigDecimal getBiWoLoanYr() {
		return this.biWoLoanYr;
	}

	public void setBiWoLoanYr(BigDecimal biWoLoanYr) {
		this.biWoLoanYr = biWoLoanYr;
	}

	public String getBiWoProject() {
		return this.biWoProject;
	}

	public void setBiWoProject(String biWoProject) {
		this.biWoProject = biWoProject;
	}

	public String getBiWoWorkord() {
		return this.biWoWorkord;
	}

	public void setBiWoWorkord(String biWoWorkord) {
		this.biWoWorkord = biWoWorkord;
	}

	public String getBiWorkEventCd() {
		return this.biWorkEventCd;
	}

	public void setBiWorkEventCd(String biWorkEventCd) {
		this.biWorkEventCd = biWorkEventCd;
	}

	public String getBiWorkgrp() {
		return this.biWorkgrp;
	}

	public void setBiWorkgrp(String biWorkgrp) {
		this.biWorkgrp = biWorkgrp;
	}

	public BigDecimal getBiWrkflwKey() {
		return this.biWrkflwKey;
	}

	public void setBiWrkflwKey(BigDecimal biWrkflwKey) {
		this.biWrkflwKey = biWrkflwKey;
	}

	public BigDecimal getBiWrkflwTaskSeqNbr() {
		return this.biWrkflwTaskSeqNbr;
	}

	public void setBiWrkflwTaskSeqNbr(BigDecimal biWrkflwTaskSeqNbr) {
		this.biWrkflwTaskSeqNbr = biWrkflwTaskSeqNbr;
	}

	public BigDecimal getBiWrkflwTasksCtr() {
		return this.biWrkflwTasksCtr;
	}

	public void setBiWrkflwTasksCtr(BigDecimal biWrkflwTasksCtr) {
		this.biWrkflwTasksCtr = biWrkflwTasksCtr;
	}

	public String getSiTaskGrp() {
		return this.siTaskGrp;
	}

	public void setSiTaskGrp(String siTaskGrp) {
		this.siTaskGrp = siTaskGrp;
	}

	public BigDecimal getSyJobDefinitionId() {
		return this.syJobDefinitionId;
	}

	public void setSyJobDefinitionId(BigDecimal syJobDefinitionId) {
		this.syJobDefinitionId = syJobDefinitionId;
	}

	public String getSyReportId() {
		return this.syReportId;
	}

	public void setSyReportId(String syReportId) {
		this.syReportId = syReportId;
	}

	public String getSyReportParams() {
		return this.syReportParams;
	}

	public void setSyReportParams(String syReportParams) {
		this.syReportParams = syReportParams;
	}

	public BigDecimal getSyUserId() {
		return this.syUserId;
	}

	public void setSyUserId(BigDecimal syUserId) {
		this.syUserId = syUserId;
	}

	public String getWmXCoord() {
		return this.wmXCoord;
	}

	public void setWmXCoord(String wmXCoord) {
		this.wmXCoord = wmXCoord;
	}

	public String getWmYCoord() {
		return this.wmYCoord;
	}

	public void setWmYCoord(String wmYCoord) {
		this.wmYCoord = wmYCoord;
	}

	public String getWmZCoord() {
		return this.wmZCoord;
	}

	public void setWmZCoord(String wmZCoord) {
		this.wmZCoord = wmZCoord;
	}

}