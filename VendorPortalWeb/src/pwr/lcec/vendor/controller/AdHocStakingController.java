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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import pwr.lcec.vendor.controller.AdHocStakingController;
import pwr.lcec.vendor.controller.WorkflowController;
import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendorportal.entity.custom.AssemblyAdhocVw;
import pwr.lcec.vendorportal.entity.custom.StakingSheet;
import pwr.lcec.vendorportal.entity.custom.StakingSheetDetail;
import pwr.lcec.vendorportal.entity.custom.Voucher;
import pwr.lcec.vendorportal.entity.custom.VoucherGui;
/*import pwr.lcec.vendorportal.exception.NoResultException;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;*/
import pwr.lcec.vendorportal.interfaces.WorkFlowLocal;

public class AdHocStakingController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(AdHocStakingController.class);
	private WorkflowController controller = (WorkflowController) FacesContext.getCurrentInstance().getApplication()
			.evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{wfController}", WorkflowController.class);

	private final String ADHOC_STAKING = "adhocstaking";
	private final String VP = "VP";

	@EJB
	private WorkFlowLocal workflowService;

	private BigDecimal station;

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
	private VoucherGui selectedVoucher = new VoucherGui();
	
	ControllerUtil util = new ControllerUtil();

	public String newStaking() {
		return ADHOC_STAKING;
	}
	
	public void openVoucherDlg() {
		
		station = BigDecimal.ZERO;
		workType = "";
		voucherDesc = "";
		voucherAmt = BigDecimal.ZERO;
		voucherCrew = "";
		
		PrimeFaces.current().ajax().update("addVoucherDiagForm");
		PrimeFaces.current().executeScript("PF('addVoucherDlg').show();");
		
	}
	
	public void openEditVoucherDlg() {
		
		station = new BigDecimal(selectedVoucher.getStationDescription());
		
		if(selectedVoucher.getGLAccountId() == 1 && selectedVoucher.getGLAccountIdSplit() == 3) {
			workType = "M";
		}else if(selectedVoucher.getGLAccountId() == 1 && selectedVoucher.getGLAccountIdSplit() != 3) {
			workType = "C";
		}else if(selectedVoucher.getGLAccountId() == 3) {
			workType = "R";
		}
		
		voucherDesc = selectedVoucher.getDescription();
		voucherAmt = selectedVoucher.getAmount();
		voucherCrew = selectedVoucher.getCrew();
		
		PrimeFaces.current().ajax().update("editVoucherDlgForm");
		PrimeFaces.current().executeScript("PF('editVoucherDlg').show();");
	}

	public void insertVoucher() {
		logger.debug(util.getWoId());
		logger.debug(station);
		logger.debug(workType);
		logger.debug(voucherDesc);
		logger.debug(voucherAmt);
		logger.debug(voucherCrew);

		Voucher voucher = new Voucher();
		String stakingSheetId = workflowService.getStatkingSheetId(util.getWoId());

		voucher.setStationDescription(station);
		if (workType.equals("C")) {
			voucher.setGLAccountId(Integer.valueOf(1));
		} else if (workType.equals("R")) {
			voucher.setGLAccountId(Integer.valueOf(3));
		} else if (workType.equals("M")) {
			voucher.setGLAccountId(Integer.valueOf(1));
			voucher.setGLAccountIdSplit(Integer.valueOf(3));
		}
		voucher.setDescription(voucherDesc);
		voucher.setAmount(voucherAmt);
		voucher.setCrew(voucherCrew);
		voucher.setStakingSheetId(Integer.valueOf(stakingSheetId));
		voucher.setWorkOrderId(util.getWoId());
		voucher.setCreatedDt(util.currentDtTm());
		voucher.setRequestor(util.getCurrentUser());
		voucher.setInspectionStatusId(Integer.valueOf(2));
		voucher.setInvoiceStatusId(Integer.valueOf(1));

		try {
			voucher = workflowService.insertVoucher(voucher);
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		PrimeFaces.current().executeScript("PF('addVoucherDlg').hide()");
		facesInfo("Voucher added successfully.");

		controller.findVouchers();
	}
	
	public void updateVoucher() {
		logger.debug(util.getWoId());
		logger.debug(station);
		logger.debug(workType);
		logger.debug(voucherDesc);
		logger.debug(voucherAmt);
		logger.debug(voucherCrew);

		Voucher voucher = workflowService.getVoucherById(selectedVoucher.getVoucherId());
		//String stakingSheetId = workflowService.getStatkingSheetId(util.getWoId());

		voucher.setStationDescription(station);
		if (workType.equals("C")) {
			voucher.setGLAccountId(Integer.valueOf(1));
		} else if (workType.equals("R")) {
			voucher.setGLAccountId(Integer.valueOf(3));
		} else if (workType.equals("M")) {
			voucher.setGLAccountId(Integer.valueOf(1));
			voucher.setGLAccountIdSplit(Integer.valueOf(3));
		}
		voucher.setDescription(voucherDesc);
		voucher.setAmount(voucherAmt);
		voucher.setCrew(voucherCrew);
		voucher.setCreatedDt(util.currentDtTm());
		voucher.setRequestor(util.getCurrentUser());
		voucher.setInspectionStatusId(Integer.valueOf(2));
		voucher.setInvoiceStatusId(Integer.valueOf(1));

		try {
			voucher = workflowService.updateVoucher(voucher);
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		PrimeFaces.current().executeScript("PF('editVoucherDlg').hide()");
		facesInfo("Voucher updated successfully.");

		controller.findVouchers();
	}

	public String addStation() {
		logger.debug("Inside addStation Method...");
		for (BigDecimal exist : existingStations()) {
			if (exist.equals(station)) {
				facesError("Station No. " + station + " already exisit. Please select a new station number.");
				return null;
			}
		}
		addStationOrUnit();
		PrimeFaces.current().executeScript("PF('addStationDlg').hide()");
		facesInfo("Station added successfully.");
		return updateStakingTbl();
	}

	public String addUnit() {
		addStationOrUnit();
		PrimeFaces.current().executeScript("PF('addUnitDlg').hide()");
		facesInfo("Unit added successfully.");
		return updateStakingTbl();
	}

	public void addStationOrUnit() {
		int rateGroupId = workflowService.getRateGroupId(util.getWrkGrp()).intValue();
		String stakingSheetId = workflowService.getStatkingSheetId(util.getWoId());

		assemblyunits = workflowService.getDistinctRates(Integer.valueOf(rateGroupId), energized, transfer, util.workEventDt());

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
		stakingSheetDetail.setAsBuiltQuantity(unitQty.intValue());
		
		if (asBuiltStatus == null) {
			stakingSheetDetail.setAsBuiltStatusId(Integer.valueOf(1));
		} else {
			stakingSheetDetail.setAsBuiltStatusId(asBuiltStatus);
		}
		
		stakingSheetDetail.setAsBuiltDt(util.currentDtTm());
		stakingSheetDetail.setAsBuiltComments(asBuiltComment);
		stakingSheetDetail.setAsBuiltBy(util.getCurrentUser());
		
		if (asBuiltStatus != null && asBuiltStatus.intValue() == 3) {
			stakingSheetDetail.setCurrentInspectionDetailStatusId(Integer.valueOf(2));
		} else {
			stakingSheetDetail.setCurrentInspectionDetailStatusId(Integer.valueOf(1));
		}
		stakingSheetDetail.setInvoiceStatusId(Integer.valueOf(1));

		workflowService.insertSheetDetail(stakingSheetDetail);

		try {
			workflowService.updateAsBuiltAmount(stakingSheetDetail.getStakingSheetDetailId(), util.workEventDt());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public String updateStakingTbl() {
		controller.findStakingDetailByWoId();
		controller.asBuiltEdit();

		String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		return String.valueOf(viewId) + "?faces-redirect=true";
	}

	public String updateVouchers() {
		String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		logger.info("View ID: " + viewId);
		return String.valueOf(viewId) + "?faces-redirect=true";
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
			int rateGroupId = workflowService.getRateGroupId(util.getWrkGrp()).intValue();

			assemblyunits = workflowService.getDistinctRates(Integer.valueOf(rateGroupId), energized, transfer, util.workEventDt());
			assemblyunits = assemblyunits.stream().sorted(Comparator.comparing(AssemblyAdhocVw::getAssemblyGuid)).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}
	}

	public String findAssemblyUnitDesc() {
		try {
			assemblyUnitDesc = workflowService.getDistinctRateDesc(assemblyUnit);
		} catch (Exception e) {
			logger.error(e.getMessage());
			facesError(e.getMessage());
		}
		return this.assemblyUnitDesc;
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

	public List<BigDecimal> existingStations() {
		return controller.findDistinctStationGui(controller.getAsBuiltStakingSheetDetailGui());
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

	public BigDecimal getStation() {
		return this.station;
	}

	public void setStation(BigDecimal station) {
		this.station = station;
	}

	public String getStationDesc() {
		return this.stationDesc;
	}

	public void setStationDesc(String stationDesc) {
		this.stationDesc = stationDesc;
	}

	public String getCr() {
		return this.cr;
	}

	public void setCr(String cr) {
		this.cr = cr;
	}

	public Integer getUnitQty() {
		return this.unitQty;
	}

	public void setUnitQty(Integer unitQty) {
		this.unitQty = unitQty;
	}

	public Integer getAsBuiltStatus() {
		return this.asBuiltStatus;
	}

	public void setAsBuiltStatus(Integer asBuiltStatus) {
		this.asBuiltStatus = asBuiltStatus;
	}

	public StakingSheet getStakingSheet() {
		return this.stakingSheet;
	}

	public void setStakingSheet(StakingSheet stakingSheet) {
		this.stakingSheet = stakingSheet;
	}

	public String getAsBuiltComment() {
		return this.asBuiltComment;
	}

	public void setAsBuiltComment(String asBuiltComment) {
		this.asBuiltComment = asBuiltComment;
	}

	public String getAsBuiltQty() {
		return this.asBuiltQty;
	}

	public void setAsBuiltQty(String asBuiltQty) {
		this.asBuiltQty = asBuiltQty;
	}

	public StakingSheetDetail getStakingSheetDetail() {
		return this.stakingSheetDetail;
	}

	public void setStakingSheetDetail(StakingSheetDetail stakingSheetDetail) {
		this.stakingSheetDetail = stakingSheetDetail;
	}

	public String getStakingsheetId() {
		return this.stakingsheetId;
	}

	public void setStakingsheetId(String stakingsheetId) {
		this.stakingsheetId = stakingsheetId;
	}

	public String getAssemblyUnitDesc() {
		return this.assemblyUnitDesc;
	}

	public void setAssemblyUnitDesc(String assemblyUnitDesc) {
		this.assemblyUnitDesc = assemblyUnitDesc;
	}

	public List<AssemblyAdhocVw> getAssemblyunits() {
		return this.assemblyunits;
	}

	public void setAssemblyunits(List<AssemblyAdhocVw> assemblyunits) {
		this.assemblyunits = assemblyunits;
	}

	public String getAssemblyUnit() {
		return this.assemblyUnit;
	}

	public void setAssemblyUnit(String assemblyUnit) {
		this.assemblyUnit = assemblyUnit;
	}

	public String getEnergized() {
		return this.energized;
	}

	public void setEnergized(String energized) {
		this.energized = energized;
	}

	public String getTransfer() {
		return this.transfer;
	}

	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}

	public String getWorkType() {
		return this.workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getVoucherDesc() {
		return this.voucherDesc;
	}

	public void setVoucherDesc(String voucherDesc) {
		this.voucherDesc = voucherDesc;
	}

	public BigDecimal getVoucherAmt() {
		return this.voucherAmt;
	}

	public void setVoucherAmt(BigDecimal voucherAmt) {
		this.voucherAmt = voucherAmt;
	}

	public String getVoucherCrew() {
		return this.voucherCrew;
	}

	public void setVoucherCrew(String voucherCrew) {
		this.voucherCrew = voucherCrew;
	}

	public VoucherGui getSelectedVoucher() {
		return selectedVoucher;
	}

	public void setSelectedVoucher(VoucherGui selectedVoucher) {
		this.selectedVoucher = selectedVoucher;
	}
	
}
