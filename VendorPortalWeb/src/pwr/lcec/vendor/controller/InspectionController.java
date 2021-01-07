package pwr.lcec.vendor.controller;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pwr.lcec.vendor.controller.InspectionController;
import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendorportal.entity.custom.Inspection;
import pwr.lcec.vendorportal.entity.custom.InspectionStatus;
import pwr.lcec.vendorportal.interfaces.InspectionLocal;

public class InspectionController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(InspectionController.class);

	final ControllerUtil util = new ControllerUtil();
	private final String INSPSEARCH = "inspectionsearch?faces-redirect=true";

	@EJB
	private InspectionLocal inspectionService;

	private String woId;

	private Integer inspectionStatus;

	private List<Inspection> inspections;
	private List<InspectionStatus> inspStatuses;

	@PostConstruct
	void init() {
		findInspectionStatus();
	}

	public String findInspections() {
		try {
			inspections = inspectionService.getInspections(woId, inspectionStatus, util.getWrkGrp());
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		
		inspections.sort((Inspection i1, Inspection i2) -> i2.getInspectionDt().compareTo(i1.getInspectionDt()));

		return INSPSEARCH;
	}

	public void resetInspectionSearch() {
		clearInputs();
		inspections = new ArrayList<Inspection>();
	}

	public void findInspectionStatus() {
		inspStatuses = inspectionService.getInspectionStatus();
	}

	public void preProcessorPDF(Object document) {
		Document doc = (Document) document;
		doc.setPageSize(PageSize.A4.rotate());
	}

	private void facesError(String message) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
	}

	public void clearInputs() {
		this.woId = null;
		this.inspectionStatus = null;
	}

	public String getWoId() {
		return this.woId;
	}

	public void setWoId(String woId) {
		this.woId = woId;
	}

	public List<Inspection> getInspections() {
		return this.inspections;
	}

	public void setInspections(List<Inspection> inspections) {
		this.inspections = inspections;
	}

	public List<InspectionStatus> getInspStatuses() {
		return this.inspStatuses;
	}

	public void setInspStatuses(List<InspectionStatus> inspStatuses) {
		this.inspStatuses = inspStatuses;
	}

	public Integer getInspectionStatus() {
		return this.inspectionStatus;
	}

	public void setInspectionStatus(Integer inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}
}
