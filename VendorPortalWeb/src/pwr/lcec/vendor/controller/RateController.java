package pwr.lcec.vendor.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;

import pwr.lcec.vendorportal.entity.custom.RateGroupPrice;
import pwr.lcec.vendorportal.interfaces.RateLocal;

public class RateController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(RateController.class);
	
	@EJB
	private RateLocal rateService;
	
	private String searchRate;
	private String searchMethod = "equals";
	private String searchStart = "0";
	private boolean showRateTable = false;
	
	private RateGroupPrice currentAssembly = new RateGroupPrice();
	private List<RateGroupPrice> rates = new ArrayList<RateGroupPrice>();
	private List<Timestamp> startTimestamp = new ArrayList<Timestamp>();
	private List<String> startDt = new ArrayList<String>();
	
	public void onloadRateGroupPrice() {
		
		findRateYearRange();
		
	}
	
	private void findRateYearRange() {
		
		startDt = new ArrayList<String>();
		//endDt = new ArrayList<String>();
		
		startTimestamp = rateService.findDistinctStartDt();
		//startTimestamp.sort();
		
		startDt.add(0, "All");		
		
		startTimestamp.stream().forEach(t -> {
			
			startDt.add(new SimpleDateFormat("MMM-yyyy").format(t));
			
		});
		
		startDt.sort(String.CASE_INSENSITIVE_ORDER);
		
	}
	
	public void submitRateSearch() {
		
		send2Growl("Search Submitted", FacesMessage.SEVERITY_INFO);
		
		logger.debug("Search Value: " + searchRate);
		logger.debug("Search Method: " + searchMethod);
		logger.debug("Search Start Range String: " + searchStart + " Timestamp value: " + findTimestamp(searchStart, startTimestamp));
		
		rates = rateService.findRateGroupPrices(searchRate, searchMethod, findTimestamp(searchStart, startTimestamp));
		showRateTable = true;

	}
	
	private Timestamp findTimestamp(String searchResult, List<Timestamp> tslist) {
		if(searchResult.equals("0") || searchResult.equals("All")) {
			return null;
		}else {
			int year = 0;
			if(searchResult.length() > 4) {
				year = Integer.parseInt(searchResult.substring(searchResult.length() - 4));
			}
			for(Timestamp t : tslist) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(t);
				if(year == cal.get(Calendar.YEAR)) {
					return t;
				}
			}
			return null;
		}
	}
	
	public void resetRateSearch() {
		
		searchRate = "";
		searchMethod = "";
		searchStart = "";
		//searchEnd = "";
		rates = new ArrayList<RateGroupPrice>();
		currentAssembly = new RateGroupPrice();
		
		showRateTable = false;
		
		findRateYearRange();
		
		send2Growl("Search Reset", FacesMessage.SEVERITY_INFO);
		
	}
	
	public void openDlgNewAssemblyUnit() {
		
		currentAssembly = new RateGroupPrice();
		
		PrimeFaces.current().executeScript("PF('dlgNewAssemblyUnit').show();");
		
	}
	
	public void saveNewAssemblyUnit() {
		
		//Check that the new AU does not already exist.
		if(rateService.doesAuAlreadyExist(currentAssembly.getAssemblyGuid(), currentAssembly.getRateGroupId(), currentAssembly.getEffectiveStartDt())) {
			send2Growl("Sorry, this AU already exists for that vendor and date range.", FacesMessage.SEVERITY_ERROR);
		}else {
			if(currentAssembly.getAssemblyGuid().endsWith(".T")) {
				currentAssembly.setGLPercent(new BigDecimal(0.50));
			}else {
				currentAssembly.setGLPercent(new BigDecimal(1.00));
			}
			
			currentAssembly.setAssemblyGuid(currentAssembly.getAssemblyGuid().toUpperCase());
			
			currentAssembly.setAssemblySource("VP");
			currentAssembly.setGLConstAccountId(1);
			currentAssembly.setGLRetireAccountId(3);
			currentAssembly.setGLActivity(new BigDecimal(220));
			currentAssembly.setGL_Department(new BigDecimal(0));
			currentAssembly.setgLRetireActivity(new BigDecimal(955));
			currentAssembly.setgLRetireDepartment(new BigDecimal(0));
			
			RateGroupPrice rpg = rateService.findEndDtFromStartDt(currentAssembly.getEffectiveStartDt());
			currentAssembly.setEffectiveEndDt(rpg.getEffectiveEndDt());
			
			if(rateService.createAssemblyUnit(currentAssembly)) {
				send2Growl("New AU saved successfully!", FacesMessage.SEVERITY_INFO);	
			}
			
			PrimeFaces.current().executeScript("PF('dlgNewAssemblyUnit').hide();");
			PrimeFaces.current().ajax().update("newAuForm");
			currentAssembly = new RateGroupPrice();
		}
		
	}
	
	public void openDlgEditAssemblyUnit() {
		
		findRateYearRange();
		
		PrimeFaces.current().ajax().update("editAuForm:dlgEditAssemblyUnit");
		PrimeFaces.current().executeScript("PF('dlgEditAssemblyUnit').show();");
		
	}
	
	public void updateAssemblyUnit() {
		
		if(currentAssembly.getAssemblyGuid().endsWith(".T")) {
			currentAssembly.setGLPercent(new BigDecimal(0.50));
		}else {
			currentAssembly.setGLPercent(new BigDecimal(1.00));
		}
		
		currentAssembly.setAssemblySource("VP");
		
		RateGroupPrice rpg = rateService.findEndDtFromStartDt(currentAssembly.getEffectiveStartDt());
		currentAssembly.setEffectiveEndDt(rpg.getEffectiveEndDt());
		
		if(rateService.updateAssemblyUnit(currentAssembly)) {
			send2Growl("AU saved successfully!", FacesMessage.SEVERITY_INFO);	
		}
		
		PrimeFaces.current().executeScript("PF('dlgNewAssemblyUnit').hide();");
		PrimeFaces.current().ajax().update("editAuForm");
		currentAssembly = new RateGroupPrice();
	}
	
	public void deleteAssemblyUnit() {
		
		logger.debug(currentAssembly.getRateGroupPriceId() + "  -  -  -  " + currentAssembly.getAssemblyGuid());
		
		
		if(rateService.deleteAssemblyUnit(currentAssembly)) {
			send2Growl("AU removed successfully!", FacesMessage.SEVERITY_INFO);	
		}
		submitRateSearch();
	}
	
	private void send2Growl(String message, FacesMessage.Severity severity ) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(severity, message, null));
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
	}
	
	/******************************************************************************************************/

	public String getSearchRate() {
		return searchRate;
	}

	public void setSearchRate(String searchRate) {
		this.searchRate = searchRate;
	}

	public List<RateGroupPrice> getRates() {
		return rates;
	}

	public void setRates(List<RateGroupPrice> rates) {
		this.rates = rates;
	}

	public String getSearchMethod() {
		return searchMethod;
	}

	public void setSearchMethod(String searchMethod) {
		this.searchMethod = searchMethod;
	}

	public String getSearchStart() {
		return searchStart;
	}

	public void setSearchStart(String searchStart) {
		this.searchStart = searchStart;
	}

	public List<String> getStartDt() {
		return startDt;
	}

	public void setStartDt(List<String> startDt) {
		this.startDt = startDt;
	}

	public boolean isShowRateTable() {
		return showRateTable;
	}

	public void setShowRateTable(boolean showRateTable) {
		this.showRateTable = showRateTable;
	}

	public List<Timestamp> getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(List<Timestamp> startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public RateGroupPrice getCurrentAssembly() {
		return currentAssembly;
	}

	public void setCurrentAssembly(RateGroupPrice currentAssembly) {
		this.currentAssembly = currentAssembly;
	}

}
