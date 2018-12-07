package pwr.lcec.vendor.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import pwr.lcec.vendorportal.bean.RateGroupPivot;
import pwr.lcec.vendorportal.interfaces.WorkFlowSessionRemote;

public class RateController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(RateController.class);
	
	@EJB
	private WorkFlowSessionRemote workflowService;
	
	private String vendor;
	
	private List<RateGroupPivot> rateGroups;
	private List<RateGroupPivot> filteredRateGroups;
	
/*	@PostConstruct
	public void init() {
		findVendorRates();
	}*/
	
	public void findVendorRates() {
		try {
			rateGroups = workflowService.getRateGroupPivot();
		} catch (Exception e) {
			logger.error(e.getMessage());
			facesError(e.getMessage());
		} 
	}
	private void facesError(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public List<RateGroupPivot> getRateGroups() {
		return rateGroups;
	}

	public void setRateGroups(List<RateGroupPivot> rateGroups) {
		this.rateGroups = rateGroups;
	}

	public List<RateGroupPivot> getFilteredRateGroups() {
		return filteredRateGroups;
	}

	public void setFilteredRateGroups(List<RateGroupPivot> filteredRateGroups) {
		this.filteredRateGroups = filteredRateGroups;
	}

}
