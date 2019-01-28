package pwr.lcec.vendor.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import pwr.lcec.vendor.web.helper.Constants;
import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendorportal.ag.entity.AGInspection;
import pwr.lcec.vendorportal.ag.entity.AGInvoice;
import pwr.lcec.vendorportal.ag.entity.AGStakingSheet;
import pwr.lcec.vendorportal.ag.entity.AGStreetlight;
import pwr.lcec.vendorportal.interfaces.DashboardServiceRemote;

public class DashboardController implements Serializable {

	private static final long serialVersionUID = 5883181727196152461L;

	private final static String PIKE = "PIKE";
	private final static String MASTEC = "MASTEC";
	private final static String LCEC = "LCEC";

	@EJB
	private DashboardServiceRemote dashboardService;

	private BarChartModel stakingBarModel;
	private BarChartModel streetlightBarModel;
	private BarChartModel inspectionBarModel;
	private BarChartModel invoiceBarModel;

	private List<AGStakingSheet> aGStakingSheets;
	private AGStakingSheet pikeAGStakingSheet;
	private AGStakingSheet mastecAGStakingSheet;

	private List<AGStreetlight> aGStreetlights;
	private AGStreetlight pikeAGStreetlight;
	private AGStreetlight mastecAGStreetlight;

	private List<AGInspection> aGInspections;
	private AGInspection pikeAGInspection;
	private AGInspection mastecAGInspection;

	private List<AGInvoice> aGInvoices;
	private AGInvoice pikeAGInvoice;
	private AGInvoice mastecAGInvoice;
	
	ControllerUtil util = new ControllerUtil();

	@PostConstruct
	public void init() {
		getAggregateSync();
		getStakingsheeetDetail();
		createBarModels();
	}
	
	public void getAggregateSync() {
		try {
			dashboardService.runAggregateSync();
		} catch (Exception ex) {
			facesError(ex.getMessage());
		}
	}

	public void getStakingsheeetDetail() {
		aGStakingSheets = dashboardService.findAllAGStakingSheet();
		aGStreetlights = dashboardService.findAllAGStreetlight();
		aGInspections = dashboardService.findAllAGInspection();
		aGInvoices = dashboardService.findAllAGInvoice();

		for (AGStakingSheet aGStakingSheet : aGStakingSheets) {
			if (aGStakingSheet.getVendorType().equals(PIKE)) {
				pikeAGStakingSheet = aGStakingSheet;
			}
			if (aGStakingSheet.getVendorType().equals(MASTEC)) {
				mastecAGStakingSheet = aGStakingSheet;
			}
		}
		for (AGStreetlight aGStreetlight : aGStreetlights) {
			if (aGStreetlight.getVendorType().equals(PIKE)) {
				pikeAGStreetlight = aGStreetlight;
			}
			if (aGStreetlight.getVendorType().equals(MASTEC)) {
				mastecAGStreetlight = aGStreetlight;
			}
		}
		for (AGInspection aGInspection : aGInspections) {
			if (aGInspection.getVendorType().equals(PIKE)) {
				pikeAGInspection = aGInspection;
			}
			if (aGInspection.getVendorType().equals(MASTEC)) {
				mastecAGInspection = aGInspection;
			}
		}
		for (AGInvoice aGInvoice : aGInvoices) {
			if (aGInvoice.getVendorType().equals(PIKE)) {
				pikeAGInvoice = aGInvoice;
			}
			if (aGInvoice.getVendorType().equals(MASTEC)) {
				mastecAGInvoice = aGInvoice;
			}
		}
	}

	private BarChartModel initStakingBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries pike = new ChartSeries();
		pike.setLabel(PIKE);
		pike.set(Constants.AS_BUILT_NOT_STARTED, pikeAGStakingSheet.getAsBuiltNotStarted());
		pike.set(Constants.AS_BUILT_COMP_LAST30, pikeAGStakingSheet.getAsBuiltCompleted30Days());
		pike.set(Constants.AS_BUILT_NOT_INSP, pikeAGStakingSheet.getNotInspected());
		pike.set(Constants.INSP_IN_PROGRESS, pikeAGStakingSheet.getInspectionInProgress());
		pike.set(Constants.NOT_INVOICED, pikeAGStakingSheet.getNotInvoiced());

		ChartSeries mastec = new ChartSeries();
		mastec.setLabel(MASTEC);
		mastec.set(Constants.AS_BUILT_NOT_STARTED, mastecAGStakingSheet.getAsBuiltNotStarted());
		mastec.set(Constants.AS_BUILT_COMP_LAST30, mastecAGStakingSheet.getAsBuiltCompleted30Days());
		mastec.set(Constants.AS_BUILT_NOT_INSP, mastecAGStakingSheet.getNotInspected());
		mastec.set(Constants.INSP_IN_PROGRESS, mastecAGStakingSheet.getInspectionInProgress());
		mastec.set(Constants.NOT_INVOICED, mastecAGStakingSheet.getNotInvoiced());

		if(util.getWrkGrp().equals(PIKE) || util.getWrkGrp().equals(LCEC)) {
			model.addSeries(pike);
		}
		if(util.getWrkGrp().equals(MASTEC) || util.getWrkGrp().equals(LCEC)) {
			model.addSeries(mastec);
		}

		return model;
	}

	private BarChartModel initStreetlightBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries pike = new ChartSeries();
		pike.setLabel(PIKE);
		pike.set(Constants.INVOICE_SUBMITTED, pikeAGStreetlight.getInvoiceSubmitted());
		pike.set(Constants.NOT_INSPECTED, pikeAGStreetlight.getNotInspected());
		pike.set(Constants.INSP_IN_PROGRESS, pikeAGStreetlight.getInspectionInProgress());
		pike.set(Constants.NOT_INVOICED, pikeAGStreetlight.getNotInvoiced());
		pike.set(Constants.INV_APPROVED, pikeAGStreetlight.getInvoiceApproved());
		pike.set(Constants.INV_REJECTED, pikeAGStreetlight.getInvoiceRejected());

		ChartSeries mastec = new ChartSeries();
		mastec.setLabel(MASTEC);
		mastec.set(Constants.INVOICE_SUBMITTED, mastecAGStreetlight.getInvoiceSubmitted());
		mastec.set(Constants.NOT_INSPECTED, mastecAGStreetlight.getNotInspected());
		mastec.set(Constants.INSP_IN_PROGRESS, mastecAGStreetlight.getInspectionInProgress());
		mastec.set(Constants.NOT_INVOICED, mastecAGStreetlight.getNotInvoiced());
		mastec.set(Constants.INV_APPROVED, mastecAGStreetlight.getInvoiceApproved());
		mastec.set(Constants.INV_REJECTED, mastecAGStreetlight.getInvoiceRejected());

		if(util.getWrkGrp().equals(PIKE) || util.getWrkGrp().equals(LCEC)) {
			model.addSeries(pike);
		}
		if(util.getWrkGrp().equals(MASTEC) || util.getWrkGrp().equals(LCEC)) {
			model.addSeries(mastec);
		}

		return model;
	}

	private BarChartModel initInspectionBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries pike = new ChartSeries();
		pike.setLabel(PIKE);
		pike.set(Constants.NOT_INSPECTED, pikeAGInspection.getNotInspected());
		pike.set(Constants.INSP_READY, pikeAGInspection.getInspectionReady());
		pike.set(Constants.INSP_IN_PROGRESS, pikeAGInspection.getInspectionInProgress());
		pike.set(Constants.INSP_APPROVED_LAST30, pikeAGInspection.getInspectionApproved30Days());
		pike.set(Constants.INSP_REJECTED, pikeAGInspection.getInspectionRejected());

		ChartSeries mastec = new ChartSeries();
		mastec.setLabel(MASTEC);
		mastec.set(Constants.NOT_INSPECTED, mastecAGInspection.getNotInspected());
		mastec.set(Constants.INSP_READY, mastecAGInspection.getInspectionReady());
		mastec.set(Constants.INSP_IN_PROGRESS, mastecAGInspection.getInspectionInProgress());
		mastec.set(Constants.INSP_APPROVED_LAST30, mastecAGInspection.getInspectionApproved30Days());
		mastec.set(Constants.INSP_REJECTED, mastecAGInspection.getInspectionRejected());

		if(util.getWrkGrp().equals(PIKE) || util.getWrkGrp().equals(LCEC)) {
			model.addSeries(pike);
		}
		if(util.getWrkGrp().equals(MASTEC) || util.getWrkGrp().equals(LCEC)) {
			model.addSeries(mastec);
		}

		return model;
	}

	private BarChartModel initInvoiceBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries pike = new ChartSeries();
		pike.setLabel(PIKE);
		pike.set(Constants.NOT_INVOICED, pikeAGInvoice.getNotInvoiced());
		pike.set(Constants.INVOICE_SUBMITTED, pikeAGInvoice.getInvoiceSubmitted());
		pike.set(Constants.INV_APPROVED, pikeAGInvoice.getInvoiceApproved());
		pike.set(Constants.INV_REJECTED, pikeAGInvoice.getInvoiceRejected());

		ChartSeries mastec = new ChartSeries();
		mastec.setLabel(MASTEC);
		mastec.set(Constants.NOT_INVOICED, mastecAGInvoice.getNotInvoiced());
		mastec.set(Constants.INVOICE_SUBMITTED, mastecAGInvoice.getInvoiceSubmitted());
		mastec.set(Constants.INV_APPROVED, mastecAGInvoice.getInvoiceApproved());
		mastec.set(Constants.INV_REJECTED, mastecAGInvoice.getInvoiceRejected());

		if(util.getWrkGrp().equals(PIKE) || util.getWrkGrp().equals(LCEC)) {
			model.addSeries(pike);
		}
		if(util.getWrkGrp().equals(MASTEC) || util.getWrkGrp().equals(LCEC)) {
			model.addSeries(mastec);
		}

		return model;
	}

	private void createInvoiceBarModel() {
		invoiceBarModel = initInvoiceBarModel();

		invoiceBarModel.setTitle("Invoice");
		invoiceBarModel.setLegendPosition("ne");

		Axis xAxis = invoiceBarModel.getAxis(AxisType.X);
		xAxis.setLabel("Status");

		Axis yAxis = invoiceBarModel.getAxis(AxisType.Y);
		yAxis.setLabel("Count");
	}

	private void createInspectionBarModel() {
		inspectionBarModel = initInspectionBarModel();

		inspectionBarModel.setTitle("Inspection");
		inspectionBarModel.setLegendPosition("ne");

		Axis xAxis = inspectionBarModel.getAxis(AxisType.X);
		xAxis.setLabel("Status");

		Axis yAxis = inspectionBarModel.getAxis(AxisType.Y);
		yAxis.setLabel("Count");
	}

	private void createStreetlightBarModel() {
		streetlightBarModel = initStreetlightBarModel();

		streetlightBarModel.setTitle("Streetlight");
		streetlightBarModel.setLegendPosition("ne");

		Axis xAxis = streetlightBarModel.getAxis(AxisType.X);
		xAxis.setLabel("Status");

		Axis yAxis = streetlightBarModel.getAxis(AxisType.Y);
		yAxis.setLabel("Count");
	}

	private void createBarModels() {
		createStakingBarModel();
		createStreetlightBarModel();
		createInspectionBarModel();
		createInvoiceBarModel();
	}

	private void createStakingBarModel() {
		stakingBarModel = initStakingBarModel();

		stakingBarModel.setTitle("Stakingsheet");
		stakingBarModel.setLegendPosition("ne");

		Axis xAxis = stakingBarModel.getAxis(AxisType.X);
		xAxis.setLabel("Status");

		Axis yAxis = stakingBarModel.getAxis(AxisType.Y);
		yAxis.setLabel("Count");
	}
	
	private void facesError(String message) {

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}

	public List<AGStakingSheet> getaGStakingSheets() {
		return aGStakingSheets;
	}

	public void setaGStakingSheets(List<AGStakingSheet> aGStakingSheets) {
		this.aGStakingSheets = aGStakingSheets;
	}

	public AGStakingSheet getPikeAGStakingSheet() {
		return pikeAGStakingSheet;
	}

	public void setPikeAGStakingSheet(AGStakingSheet pikeAGStakingSheet) {
		this.pikeAGStakingSheet = pikeAGStakingSheet;
	}

	public AGStakingSheet getMastecAGStakingSheet() {
		return mastecAGStakingSheet;
	}

	public void setMastecAGStakingSheet(AGStakingSheet mastecAGStakingSheet) {
		this.mastecAGStakingSheet = mastecAGStakingSheet;
	}

	public BarChartModel getStakingBarModel() {
		return stakingBarModel;
	}

	public void setStakingBarModel(BarChartModel stakingBarModel) {
		this.stakingBarModel = stakingBarModel;
	}

	public List<AGStreetlight> getaGStreetlights() {
		return aGStreetlights;
	}

	public void setaGStreetlights(List<AGStreetlight> aGStreetlights) {
		this.aGStreetlights = aGStreetlights;
	}

	public AGStreetlight getPikeAGStreetlight() {
		return pikeAGStreetlight;
	}

	public void setPikeAGStreetlight(AGStreetlight pikeAGStreetlight) {
		this.pikeAGStreetlight = pikeAGStreetlight;
	}

	public AGStreetlight getMastecAGStreetlight() {
		return mastecAGStreetlight;
	}

	public void setMastecAGStreetlight(AGStreetlight mastecAGStreetlight) {
		this.mastecAGStreetlight = mastecAGStreetlight;
	}

	public BarChartModel getStreetlightBarModel() {
		return streetlightBarModel;
	}

	public void setStreetlightBarModel(BarChartModel streetlightBarModel) {
		this.streetlightBarModel = streetlightBarModel;
	}

	public List<AGInspection> getaGInspections() {
		return aGInspections;
	}

	public void setaGInspections(List<AGInspection> aGInspections) {
		this.aGInspections = aGInspections;
	}

	public AGInspection getPikeAGInspection() {
		return pikeAGInspection;
	}

	public void setPikeAGInspection(AGInspection pikeAGInspection) {
		this.pikeAGInspection = pikeAGInspection;
	}

	public AGInspection getMastecAGInspection() {
		return mastecAGInspection;
	}

	public void setMastecAGInspection(AGInspection mastecAGInspection) {
		this.mastecAGInspection = mastecAGInspection;
	}

	public BarChartModel getInspectionBarModel() {
		return inspectionBarModel;
	}

	public void setInspectionBarModel(BarChartModel inspectionBarModel) {
		this.inspectionBarModel = inspectionBarModel;
	}

	public AGInvoice getPikeAGInvoice() {
		return pikeAGInvoice;
	}

	public void setPikeAGInvoice(AGInvoice pikeAGInvoice) {
		this.pikeAGInvoice = pikeAGInvoice;
	}

	public AGInvoice getMastecAGInvoice() {
		return mastecAGInvoice;
	}

	public void setMastecAGInvoice(AGInvoice mastecAGInvoice) {
		this.mastecAGInvoice = mastecAGInvoice;
	}

	public List<AGInvoice> getaGInvoices() {
		return aGInvoices;
	}

	public void setaGInvoices(List<AGInvoice> aGInvoices) {
		this.aGInvoices = aGInvoices;
	}

	public BarChartModel getInvoiceBarModel() {
		return invoiceBarModel;
	}

	public void setInvoiceBarModel(BarChartModel invoiceBarModel) {
		this.invoiceBarModel = invoiceBarModel;
	}
}
