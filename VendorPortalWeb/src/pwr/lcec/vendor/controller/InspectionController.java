package pwr.lcec.vendor.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;

import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendorportal.custom.entity.Inspection;
import pwr.lcec.vendorportal.custom.entity.InspectionStatus;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;
import pwr.lcec.vendorportal.interfaces.InspectionSessionRemote;

public class InspectionController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(InspectionController.class);
	
	ControllerUtil util = new ControllerUtil();
	
	@EJB
	private InspectionSessionRemote inspectionService;

	private String woId;
	private Integer inspectionStatus;
	
	private List<Inspection> inspections;
	private List<InspectionStatus> inspStatuses;
	
	@PostConstruct
	void init() {
		findInspectionStatus();
	}

	public void findInspetions(){
		
		try {
			inspections = inspectionService.getInspections(woId, inspectionStatus, util.getWrkGrp());
		} catch (ValidationException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
		
		clearInputs();
	}
	
	public void findInspectionStatus() {
		inspStatuses = inspectionService.getInspetionStatus();
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
		woId = null;
		inspectionStatus = null;
	}

	public String getWoId() {
		return woId;
	}

	public void setWoId(String woId) {
		this.woId = woId;
	}

	public List<Inspection> getInspections() {
		return inspections;
	}

	public void setInspections(List<Inspection> inspections) {
		this.inspections = inspections;
	}

	public List<InspectionStatus> getInspStatuses() {
		return inspStatuses;
	}

	public void setInspStatuses(List<InspectionStatus> inspStatuses) {
		this.inspStatuses = inspStatuses;
	}

	public Integer getInspectionStatus() {
		return inspectionStatus;
	}

	public void setInspectionStatus(Integer inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}
}
