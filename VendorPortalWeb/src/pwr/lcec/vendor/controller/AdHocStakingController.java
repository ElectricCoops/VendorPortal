package pwr.lcec.vendor.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;

import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendorportal.custom.entity.AssemblyAdhocVw;
import pwr.lcec.vendorportal.custom.entity.StakingSheet;
import pwr.lcec.vendorportal.custom.entity.StakingSheetDetail;
import pwr.lcec.vendorportal.custom.entity.Voucher;
import pwr.lcec.vendorportal.exception.NoResultException;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;
import pwr.lcec.vendorportal.interfaces.WorkFlowSessionRemote;

public class AdHocStakingController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(AdHocStakingController.class);

	private final String ADHOC_STAKING = "adhocstaking";
	private final String VP = "VP";

	@EJB
	private WorkFlowSessionRemote workflowService;

	private String station;
	private String stationDesc;
	private String assemblyUnit;
	private Integer unitQty;
	private Integer asBuiltStatus;
	private List<AssemblyAdhocVw> assemblyunits;
	private String asBuiltQty;
	private String asBuiltComment;
	private String stakingsheetId;
	private String assemblyUnitDesc;

	private String cr;
	private String transfer;
	private String energized;

	private StakingSheet stakingSheet;
	private StakingSheetDetail stakingSheetDetail;
	
	private String workType;
	private String voucherDesc;
	private BigDecimal voucherAmt;
	private String voucherCrew;

	ControllerUtil util = new ControllerUtil();

	public String newStaking() {

		return ADHOC_STAKING;
	}

	public String insertVoucher() {
		Voucher voucher = new Voucher();
		String stakingSheetId = workflowService.getStatkingSheetId(util.getWoId());

		voucher.setStationDescription(station);
		if (workType.equals("C")) {
			voucher.setGLAccountId(1);
		} else if (workType.equals("R")) {
			voucher.setGLAccountId(3);
		} else if (workType.equals("M")) {
			voucher.setGLAccountId(1);
			voucher.setGLAccountIdSplit(3);
		}
		voucher.setDescription(voucherDesc);
		voucher.setAmount(voucherAmt);
		voucher.setCrew(voucherCrew);
		voucher.setStakingSheetId(Integer.valueOf(stakingSheetId));
		voucher.setWorkOrderId(util.getWoId());
		voucher.setCreatedDt(util.currentDtTm());
		voucher.setRequestor(util.getCurrentUser());
		voucher.setInspectionStatusId(2);
		voucher.setInvoiceStatusId(1);
		
		try {
			voucher = workflowService.insertVoucher(voucher);
		} catch (ProcessException | ValidationException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		PrimeFaces.current().executeScript("PF('addVoucherDlg').hide()");  
		facesInfo("Voucher added successfully.");
		
		return updateVouchers();
	}
	
	public String addStation() {
		for (String exist : existingStations()) {
			if (exist.equals(station)) {
				facesError("Station No. " + station + " already exisit. Please select a new station number.");
				return null;
			}
		}
		try {
			addStationOrUnit();
		} catch (ValidationException | NoResultException | ProcessException e) {
			logger.error(e.getMessage());
			facesError(e.getMessage());
		}
		PrimeFaces.current().executeScript("PF('addStationDlg').hide()");  
		facesInfo("Station added successfully.");
		return updateStakingTbl();
	}

	public String addUnit() {
		try {
			addStationOrUnit();
			
		} catch (ValidationException | NoResultException | ProcessException e) {
			logger.error(e.getMessage());
			facesError(e.getMessage());
		}
		PrimeFaces.current().executeScript("PF('addUnitDlg').hide()");   
		facesInfo("Unit added successfully.");
		return updateStakingTbl();
	}

	public void addStationOrUnit() throws ValidationException, NoResultException, ProcessException {

		int rateGroupId = workflowService.getRateGroupId(util.getWrkGrp());
		String stakingSheetId = workflowService.getStatkingSheetId(util.getWoId());

		assemblyunits = workflowService.getDistinctRates(rateGroupId, energized, transfer);

		stakingSheetDetail = new StakingSheetDetail();

		stakingSheetDetail.setStakingSheetDetailId(genStakingsheetDetailId());
		stakingSheetDetail.setStakingSheetId(stakingSheetId);
		stakingSheetDetail.setStationDescription(station);
		stakingSheetDetail.setStakingSource(VP);
		stakingSheetDetail.setAssemblyGuid(assemblyUnit);
		stakingSheetDetail.setAssemblyRateGroupId(rateGroupId);
		stakingSheetDetail.setAssemblyDescription(findAssemblyUnitDesc());
		stakingSheetDetail.setAssemblyActionCode(cr);
		stakingSheetDetail.setAssemblyCreatedDt(util.currentDtTm());
		stakingSheetDetail.setAssemblyModifiedDt(util.currentDtTm());
		stakingSheetDetail.setAsBuiltQuantity(unitQty);
		if(asBuiltStatus == null) {
			stakingSheetDetail.setAsBuiltStatusId(1);
		}else {
			stakingSheetDetail.setAsBuiltStatusId(asBuiltStatus);
		}
		stakingSheetDetail.setAsBuiltDt(util.currentDtTm());
		stakingSheetDetail.setAsBuiltComments(asBuiltComment);
		stakingSheetDetail.setAsBuiltBy(util.getCurrentUser());
		if(asBuiltStatus != null && asBuiltStatus == 3) {
			stakingSheetDetail.setCurrentInspectionDetailStatusId(2);
		}else {
			stakingSheetDetail.setCurrentInspectionDetailStatusId(1);
		}
		stakingSheetDetail.setInvoiceStatusId(1);

		workflowService.insertSheetDetail(stakingSheetDetail);
		
		try {
			workflowService.updateAsBuiltAmount(stakingSheetDetail.getStakingSheetDetailId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public String updateStakingTbl() {
		FacesContext context = FacesContext.getCurrentInstance();
		WorkflowController stations = context.getApplication().evaluateExpressionGet(context, "#{wfController}",
				WorkflowController.class);
		stations.findStakingDetailByWoId();
		stations.asBuiltEdit();

		String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		return viewId + "?faces-redirect=true";
	}
	
	public String updateVouchers() {
		FacesContext context = FacesContext.getCurrentInstance();
		WorkflowController stations = context.getApplication().evaluateExpressionGet(context, "#{wfController}",
				WorkflowController.class);
		stations.findStakingDetailByWoId();

		String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		return viewId + "?faces-redirect=true";
	}
	
	public void crEvent() {
		setCr(cr);
	}

	public void energizedEvent() {
		setEnergized(energized);
	}

	public void transferEvent() {
		setTransfer(transfer);
	}

	public void findAssemblyUnits() {
		try {
			int rateGroupId = workflowService.getRateGroupId(util.getWrkGrp());

			assemblyunits = workflowService.getDistinctRates(rateGroupId, energized, transfer);
			assemblyunits = assemblyunits.stream().sorted(Comparator.comparing(AssemblyAdhocVw::getAssemblyGuid))
					.collect(Collectors.toList());

		} catch (ValidationException | NoResultException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
	}

	public String findAssemblyUnitDesc() {

		try {
			assemblyUnitDesc = workflowService.getDistinctRateDesc(assemblyUnit);
		} catch (ValidationException | ProcessException e) {
			logger.error(e.getMessage());
			facesError(e.getMessage());
		}
		return assemblyUnitDesc;
	}

	public String genStakingsheetDetailId() {

		StringBuilder sb = new StringBuilder();

		UUID uuid = UUID.randomUUID();
		String randomUUIDString = uuid.toString();
		sb.append("{");
		sb.append(randomUUIDString);
		sb.append("}");

		return sb.toString().toUpperCase();
	}

	public List<String> existingStations() {

		List<String> result;

		FacesContext context = FacesContext.getCurrentInstance();
		WorkflowController stations = context.getApplication().evaluateExpressionGet(context, "#{wfController}",
				WorkflowController.class);
		result = stations.findDistinctStation();

		return result;
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

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getStationDesc() {
		return stationDesc;
	}

	public void setStationDesc(String stationDesc) {
		this.stationDesc = stationDesc;
	}

	public String getCr() {
		return cr;
	}

	public void setCr(String cr) {
		this.cr = cr;
	}

	public Integer getUnitQty() {
		return unitQty;
	}

	public void setUnitQty(Integer unitQty) {
		this.unitQty = unitQty;
	}

	public Integer getAsBuiltStatus() {
		return asBuiltStatus;
	}

	public void setAsBuiltStatus(Integer asBuiltStatus) {
		this.asBuiltStatus = asBuiltStatus;
	}

	public StakingSheet getStakingSheet() {
		return stakingSheet;
	}

	public void setStakingSheet(StakingSheet stakingSheet) {
		this.stakingSheet = stakingSheet;
	}

	public String getAsBuiltComment() {
		return asBuiltComment;
	}

	public void setAsBuiltComment(String asBuiltComment) {
		this.asBuiltComment = asBuiltComment;
	}

	public String getAsBuiltQty() {
		return asBuiltQty;
	}

	public void setAsBuiltQty(String asBuiltQty) {
		this.asBuiltQty = asBuiltQty;
	}

	public StakingSheetDetail getStakingSheetDetail() {
		return stakingSheetDetail;
	}

	public void setStakingSheetDetail(StakingSheetDetail stakingSheetDetail) {
		this.stakingSheetDetail = stakingSheetDetail;
	}

	public String getStakingsheetId() {
		return stakingsheetId;
	}

	public void setStakingsheetId(String stakingsheetId) {
		this.stakingsheetId = stakingsheetId;
	}

	public String getAssemblyUnitDesc() {
		return assemblyUnitDesc;
	}

	public void setAssemblyUnitDesc(String assemblyUnitDesc) {
		this.assemblyUnitDesc = assemblyUnitDesc;
	}

	public List<AssemblyAdhocVw> getAssemblyunits() {
		return assemblyunits;
	}

	public void setAssemblyunits(List<AssemblyAdhocVw> assemblyunits) {
		this.assemblyunits = assemblyunits;
	}

	public String getAssemblyUnit() {
		return assemblyUnit;
	}

	public void setAssemblyUnit(String assemblyUnit) {
		this.assemblyUnit = assemblyUnit;
	}

	public String getEnergized() {
		return energized;
	}

	public void setEnergized(String energized) {
		this.energized = energized;
	}

	public String getTransfer() {
		return transfer;
	}

	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getVoucherDesc() {
		return voucherDesc;
	}

	public void setVoucherDesc(String voucherDesc) {
		this.voucherDesc = voucherDesc;
	}

	public BigDecimal getVoucherAmt() {
		return voucherAmt;
	}

	public void setVoucherAmt(BigDecimal voucherAmt) {
		this.voucherAmt = voucherAmt;
	}

	public String getVoucherCrew() {
		return voucherCrew;
	}

	public void setVoucherCrew(String voucherCrew) {
		this.voucherCrew = voucherCrew;
	}
}
