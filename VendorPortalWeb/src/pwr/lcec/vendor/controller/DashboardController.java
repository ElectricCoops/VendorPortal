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
import pwr.lcec.vendor.controller.DashboardController;
import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendorportal.entity.ag.AGInspection;
import pwr.lcec.vendorportal.entity.ag.AGInvoice;
import pwr.lcec.vendorportal.entity.ag.AGStakingSheet;
import pwr.lcec.vendorportal.entity.ag.AGStreetlight;
import pwr.lcec.vendorportal.interfaces.DashboardLocal;


public class DashboardController implements Serializable {
	private static final long serialVersionUID = 5883181727196152461L;
	private static final String PIKE = "PIKE";
	private static final String MASTEC = "MASTEC";
	private static final String LCEC = "LCEC";
	
	@EJB
	private DashboardLocal dashboardService;
	
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
		//aGStreetlights = dashboardService.findAllAGStreetlight();
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
		/*for (AGStreetlight aGStreetlight : this.aGStreetlights) {
			if (aGStreetlight.getVendorType().equals(PIKE)) {
				this.pikeAGStreetlight = aGStreetlight;
			}
			if (aGStreetlight.getVendorType().equals(MASTEC)) {
				this.mastecAGStreetlight = aGStreetlight;
			}
		}*/
		for (AGInspection aGInspection : this.aGInspections) {
			if (aGInspection.getVendorType().equals(PIKE)) {
				this.pikeAGInspection = aGInspection;
			}
			if (aGInspection.getVendorType().equals(MASTEC)) {
				this.mastecAGInspection = aGInspection;
			}
		}
		for (AGInvoice aGInvoice : this.aGInvoices) {
			if (aGInvoice.getVendorType().equals(PIKE)) {
				this.pikeAGInvoice = aGInvoice;
			}
			if (aGInvoice.getVendorType().equals(MASTEC)) {
				this.mastecAGInvoice = aGInvoice;
			}
		}
	}

	private BarChartModel initStakingBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries pike = new ChartSeries();
		pike.setLabel(PIKE);
		pike.set("As-Built Not Started", this.pikeAGStakingSheet.getAsBuiltNotStarted());
		pike.set("As-Built Completed Last 30 Days", this.pikeAGStakingSheet.getAsBuiltCompleted30Days());
		pike.set("As-Built Not Inspected", this.pikeAGStakingSheet.getNotInspected());
		pike.set("Inspection In Progress", this.pikeAGStakingSheet.getInspectionInProgress());
		pike.set("Not Invoiced", this.pikeAGStakingSheet.getNotInvoiced());

		ChartSeries mastec = new ChartSeries();
		mastec.setLabel(MASTEC);
		mastec.set("As-Built Not Started", this.mastecAGStakingSheet.getAsBuiltNotStarted());
		mastec.set("As-Built Completed Last 30 Days", this.mastecAGStakingSheet.getAsBuiltCompleted30Days());
		mastec.set("As-Built Not Inspected", this.mastecAGStakingSheet.getNotInspected());
		mastec.set("Inspection In Progress", this.mastecAGStakingSheet.getInspectionInProgress());
		mastec.set("Not Invoiced", this.mastecAGStakingSheet.getNotInvoiced());

		model.setExtender("skinBar");
		
		if (util.getWrkGrp().equals(PIKE) || util.getWrkGrp().equals(LCEC)) {
			model.addSeries(pike);
		}
		if (util.getWrkGrp().equals(MASTEC) || util.getWrkGrp().equals(LCEC)) {
			model.addSeries(mastec);
		}

		return model;
	}

	/*private BarChartModel initStreetlightBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries pike = new ChartSeries();
		pike.setLabel(PIKE);
		pike.set("Invoice Submitted", pikeAGStreetlight.getInvoiceSubmitted());
		pike.set("Not Inspected", pikeAGStreetlight.getNotInspected());
		pike.set("Inspection In Progress", pikeAGStreetlight.getInspectionInProgress());
		pike.set("Not Invoiced", pikeAGStreetlight.getNotInvoiced());
		pike.set("Invoice Approved", pikeAGStreetlight.getInvoiceApproved());
		pike.set("Invoice Rejected", pikeAGStreetlight.getInvoiceRejected());

		ChartSeries mastec = new ChartSeries();
		mastec.setLabel(MASTEC);
		mastec.set("Invoice Submitted", this.mastecAGStreetlight.getInvoiceSubmitted());
		mastec.set("Not Inspected", this.mastecAGStreetlight.getNotInspected());
		mastec.set("Inspection In Progress", this.mastecAGStreetlight.getInspectionInProgress());
		mastec.set("Not Invoiced", this.mastecAGStreetlight.getNotInvoiced());
		mastec.set("Invoice Approved", this.mastecAGStreetlight.getInvoiceApproved());
		mastec.set("Invoice Rejected", this.mastecAGStreetlight.getInvoiceRejected());

		if (this.util.getWrkGrp().equals(PIKE) || this.util.getWrkGrp().equals(LCEC)) {
			model.addSeries(pike);
		}
		if (this.util.getWrkGrp().equals(MASTEC) || this.util.getWrkGrp().equals(LCEC)) {
			model.addSeries(mastec);
		}

		return model;
	}*/

	private BarChartModel initInspectionBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries pike = new ChartSeries();
		pike.setLabel(PIKE);
		pike.set("Not Inspected", this.pikeAGInspection.getNotInspected());
		pike.set("Inspection Ready", this.pikeAGInspection.getInspectionReady());
		pike.set("Inspection In Progress", this.pikeAGInspection.getInspectionInProgress());
		pike.set("Inspection Approved Last 30 Days", this.pikeAGInspection.getInspectionApproved30Days());
		pike.set("Inspection Rejected", this.pikeAGInspection.getInspectionRejected());

		ChartSeries mastec = new ChartSeries();
		mastec.setLabel(MASTEC);
		mastec.set("Not Inspected", this.mastecAGInspection.getNotInspected());
		mastec.set("Inspection Ready", this.mastecAGInspection.getInspectionReady());
		mastec.set("Inspection In Progress", this.mastecAGInspection.getInspectionInProgress());
		mastec.set("Inspection Approved Last 30 Days", this.mastecAGInspection.getInspectionApproved30Days());
		mastec.set("Inspection Rejected", this.mastecAGInspection.getInspectionRejected());

		model.setExtender("skinBar");
		
		if (this.util.getWrkGrp().equals(PIKE) || this.util.getWrkGrp().equals(LCEC)) {
			model.addSeries(pike);
		}
		if (this.util.getWrkGrp().equals(MASTEC) || this.util.getWrkGrp().equals(LCEC)) {
			model.addSeries(mastec);
		}

		return model;
	}

	private BarChartModel initInvoiceBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries pike = new ChartSeries();
		pike.setLabel(PIKE);
		pike.set("Not Invoiced", this.pikeAGInvoice.getNotInvoiced());
		pike.set("Invoice Submitted", this.pikeAGInvoice.getInvoiceSubmitted());
		pike.set("Invoice Approved", this.pikeAGInvoice.getInvoiceApproved());
		pike.set("Invoice Rejected", this.pikeAGInvoice.getInvoiceRejected());

		ChartSeries mastec = new ChartSeries();
		mastec.setLabel(MASTEC);
		mastec.set("Not Invoiced", this.mastecAGInvoice.getNotInvoiced());
		mastec.set("Invoice Submitted", this.mastecAGInvoice.getInvoiceSubmitted());
		mastec.set("Invoice Approved", this.mastecAGInvoice.getInvoiceApproved());
		mastec.set("Invoice Rejected", this.mastecAGInvoice.getInvoiceRejected());

		model.setExtender("skinBar");
		
		if (this.util.getWrkGrp().equals(PIKE) || this.util.getWrkGrp().equals(LCEC)) {
			model.addSeries(pike);
		}
		if (this.util.getWrkGrp().equals(MASTEC) || this.util.getWrkGrp().equals(LCEC)) {
			model.addSeries(mastec);
		}

		return model;
	}

	private void createInvoiceBarModel() {
		this.invoiceBarModel = initInvoiceBarModel();

		this.invoiceBarModel.setTitle("Invoice");
		this.invoiceBarModel.setLegendPosition("ne");

		Axis xAxis = this.invoiceBarModel.getAxis(AxisType.X);
		xAxis.setLabel("Status");

		Axis yAxis = this.invoiceBarModel.getAxis(AxisType.Y);
		yAxis.setLabel("Count");
	}

	private void createInspectionBarModel() {
		this.inspectionBarModel = initInspectionBarModel();

		this.inspectionBarModel.setTitle("Inspection");
		this.inspectionBarModel.setLegendPosition("ne");

		Axis xAxis = this.inspectionBarModel.getAxis(AxisType.X);
		xAxis.setLabel("Status");

		Axis yAxis = this.inspectionBarModel.getAxis(AxisType.Y);
		yAxis.setLabel("Count");
	}

	/*private void createStreetlightBarModel() {
		this.streetlightBarModel = initStreetlightBarModel();

		this.streetlightBarModel.setTitle("Streetlight");
		this.streetlightBarModel.setLegendPosition("ne");

		Axis xAxis = this.streetlightBarModel.getAxis(AxisType.X);
		xAxis.setLabel("Status");

		Axis yAxis = this.streetlightBarModel.getAxis(AxisType.Y);
		yAxis.setLabel("Count");
	}*/

	private void createBarModels() {
		createStakingBarModel();
		//createStreetlightBarModel();
		createInspectionBarModel();
		createInvoiceBarModel();
	}

	private void createStakingBarModel() {
		this.stakingBarModel = initStakingBarModel();

		this.stakingBarModel.setTitle("Stakingsheet");
		this.stakingBarModel.setLegendPosition("ne");

		Axis xAxis = this.stakingBarModel.getAxis(AxisType.X);
		xAxis.setLabel("Status");

		Axis yAxis = this.stakingBarModel.getAxis(AxisType.Y);
		yAxis.setLabel("Count");
	}

	private void facesError(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}

	public List<AGStakingSheet> getaGStakingSheets() {
		return this.aGStakingSheets;
	}

	public void setaGStakingSheets(List<AGStakingSheet> aGStakingSheets) {
		this.aGStakingSheets = aGStakingSheets;
	}

	public AGStakingSheet getPikeAGStakingSheet() {
		return this.pikeAGStakingSheet;
	}

	public void setPikeAGStakingSheet(AGStakingSheet pikeAGStakingSheet) {
		this.pikeAGStakingSheet = pikeAGStakingSheet;
	}

	public AGStakingSheet getMastecAGStakingSheet() {
		return this.mastecAGStakingSheet;
	}

	public void setMastecAGStakingSheet(AGStakingSheet mastecAGStakingSheet) {
		this.mastecAGStakingSheet = mastecAGStakingSheet;
	}

	public BarChartModel getStakingBarModel() {
		return this.stakingBarModel;
	}

	public void setStakingBarModel(BarChartModel stakingBarModel) {
		this.stakingBarModel = stakingBarModel;
	}

	public List<AGStreetlight> getaGStreetlights() {
		return this.aGStreetlights;
	}

	public void setaGStreetlights(List<AGStreetlight> aGStreetlights) {
		this.aGStreetlights = aGStreetlights;
	}

	public AGStreetlight getPikeAGStreetlight() {
		return this.pikeAGStreetlight;
	}

	public void setPikeAGStreetlight(AGStreetlight pikeAGStreetlight) {
		this.pikeAGStreetlight = pikeAGStreetlight;
	}

	public AGStreetlight getMastecAGStreetlight() {
		return this.mastecAGStreetlight;
	}

	public void setMastecAGStreetlight(AGStreetlight mastecAGStreetlight) {
		this.mastecAGStreetlight = mastecAGStreetlight;
	}

	public BarChartModel getStreetlightBarModel() {
		return this.streetlightBarModel;
	}

	public void setStreetlightBarModel(BarChartModel streetlightBarModel) {
		this.streetlightBarModel = streetlightBarModel;
	}

	public List<AGInspection> getaGInspections() {
		return this.aGInspections;
	}

	public void setaGInspections(List<AGInspection> aGInspections) {
		this.aGInspections = aGInspections;
	}

	public AGInspection getPikeAGInspection() {
		return this.pikeAGInspection;
	}

	public void setPikeAGInspection(AGInspection pikeAGInspection) {
		this.pikeAGInspection = pikeAGInspection;
	}

	public AGInspection getMastecAGInspection() {
		return this.mastecAGInspection;
	}

	public void setMastecAGInspection(AGInspection mastecAGInspection) {
		this.mastecAGInspection = mastecAGInspection;
	}

	public BarChartModel getInspectionBarModel() {
		return this.inspectionBarModel;
	}

	public void setInspectionBarModel(BarChartModel inspectionBarModel) {
		this.inspectionBarModel = inspectionBarModel;
	}

	public AGInvoice getPikeAGInvoice() {
		return this.pikeAGInvoice;
	}

	public void setPikeAGInvoice(AGInvoice pikeAGInvoice) {
		this.pikeAGInvoice = pikeAGInvoice;
	}

	public AGInvoice getMastecAGInvoice() {
		return this.mastecAGInvoice;
	}

	public void setMastecAGInvoice(AGInvoice mastecAGInvoice) {
		this.mastecAGInvoice = mastecAGInvoice;
	}

	public List<AGInvoice> getaGInvoices() {
		return this.aGInvoices;
	}

	public void setaGInvoices(List<AGInvoice> aGInvoices) {
		this.aGInvoices = aGInvoices;
	}

	public BarChartModel getInvoiceBarModel() {
		return this.invoiceBarModel;
	}

	public void setInvoiceBarModel(BarChartModel invoiceBarModel) {
		this.invoiceBarModel = invoiceBarModel;
	}
}
