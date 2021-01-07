package pwr.lcec.vendor.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pwr.lcec.vendorportal.interfaces.AddWorkOrderRemote;

public class AddWorkOrderController implements Serializable {
	
private static final long serialVersionUID = 1L;
	
private static Logger logger = LogManager.getLogger(AddWorkOrderController.class);
	
	private String wrNo;
	
	@EJB 
	public AddWorkOrderRemote addWorkOrderService;
	
	public void insertWorkRequest() {
		
		try {
			addWorkOrderService.updateWMISWR(wrNo);
			facesInfo("Work Order \'"+wrNo+"\' saved succesfully.");
		} catch (Exception e) {
			logger.error(e);
			facesError("An exception occurred: "+e.getMessage());
		}
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

	public String getWrNo() {
		return wrNo;
	}

	public void setWrNo(String wrNo) {
		this.wrNo = wrNo;
	}

}
