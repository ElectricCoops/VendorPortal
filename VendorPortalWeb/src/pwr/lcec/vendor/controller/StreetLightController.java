package pwr.lcec.vendor.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;

import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendorportal.custom.entity.Inspection;
import pwr.lcec.vendorportal.custom.entity.InspectionStatus;
import pwr.lcec.vendorportal.custom.entity.Invoice;
import pwr.lcec.vendorportal.custom.entity.InvoiceDetail;
import pwr.lcec.vendorportal.custom.entity.InvoiceStatus;
import pwr.lcec.vendorportal.custom.entity.StreetLightSearchVw;
import pwr.lcec.vendorportal.exception.NoResultException;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;
import pwr.lcec.vendorportal.interfaces.InspectionSessionRemote;
import pwr.lcec.vendorportal.interfaces.InvoiceSessionRemote;
import pwr.lcec.vendorportal.interfaces.StreetLightServiceRemote;
import pwr.lcec.vendorportal.interfaces.UserManagementRemote;
import pwr.lcec.vendorportal.sec.entity.User;

public class StreetLightController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(StreetLightController.class);
	
	private final String INV_SUMMARY = "streetlightinvoicedetail?faces-redirect=true";
	private final String SL_INVOICE = "SL";
	private final String STREETLIGHT = "streetlightsearch?faces-redirect=true";
	private final String INVOICE_APPROVAL = "streetlightinvoiceapproval?faces-redirect=true";
	private final String INVOICE_SEARCH = "invoicesearch?faces-redirect=true";
	private final String STREETLIGHT_FROM_INVOICE = "../streetlightsearch?faces-redirect=true";
	private final String LCEC = "LCEC";

	@EJB
	private StreetLightServiceRemote streetLightService;
	@EJB
	private InvoiceSessionRemote invoiceService;
	@EJB
	private InspectionSessionRemote inspectionService;
	@EJB
	private UserManagementRemote userManagementService;

	private String woNo;
	private String soNo;
	private String lcecInvNo;
	private String vendorRefNo;
	private Integer invStatus;
	private Integer inspectionStatusId;
	private String invRefNo;
	private String vendorName;
	private List<StreetLightSearchVw> serviceOrder;
	private List<StreetLightSearchVw> selectedServiceOrder;
	private List<StreetLightSearchVw> filteredServiceOrder;
	private List<StreetLightSearchVw> filteredSearchServiceOrder;
	private List<StreetLightSearchVw> inspectionServiceOrder;
	private List<InvoiceStatus> invoiceStatus;
	private List<InvoiceStatus> slInvoiceStatus;
	private List<StreetLightSearchVw> invSummary;
	private List<InspectionStatus> inspectionStatuses;
	private List<StreetLightSearchVw> invoiceServiceOrder;
	private List<StreetLightSearchVw> selectedInvoiceServiceOrder;
	private Invoice invoice;
	private Integer invoiceId;
	
	private String rejectedInvoiceComment;
	
	private List<InvoiceDetail> invoiceDetail;
	private List<InvoiceStatus> invoiceApprovalStatus;
	private List<InspectionStatus> filterredInspectionStatuses;
	
	private boolean renderSelection = false;
	private boolean renderInspectionRsltVw = false;
	private boolean renderSearchRsltVw = false;
	private boolean renderInvoiceRsltVw = false;
	private boolean disableApprovedBtn = false;
	private boolean renderInspectionBtn = true;
	private boolean renderInvoiceBtn = true;
	private boolean renderBackToStreetLightBtn = true;
	private boolean renderSearchPanel = true;
	
	private BigDecimal slSubTotal = new BigDecimal(0);
	private BigDecimal slAmount;
	
	private Integer invNoLink;
	
	private Date startDate;
	private Date endDate;
	
	private String ivueStatus;
	
	ControllerUtil util = new ControllerUtil();
	
	@PostConstruct
	void init() {
		findInvoiceStatus();
		findInspectionStatus();
	}

	public String searchStreetLights() {
		try {
			serviceOrder = streetLightService.getServiceOrders(woNo, soNo, vendorName, util.convertDtTm(startDate),
					util.convertDtTm(endDate), invStatus, inspectionStatusId, util.getWrkGrp(), ivueStatus);

			if (!util.getWrkGrp().equals(LCEC)) {
				serviceOrder = serviceOrder.stream()
						.filter(so -> so.getInspectionStatus().getStatus().equals("Approved")
								|| so.getInspectionStatus().getStatus().equals("Rejected"))
						.collect(Collectors.toList());
			}

			renderInvoiceRsltVw = false;
			renderSelection = false;
			renderSearchRsltVw = true;
			renderInspectionRsltVw = false;
			renderInspectionBtn = true;
			renderInvoiceBtn = true;
			renderSearchPanel = true;

		} catch (ProcessException e) {
			facesError(e.getMessage());
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return STREETLIGHT;
	}
	
	public String createInspection() throws ProcessException {

		renderInvoiceRsltVw = false;
		renderSearchRsltVw = false;
		renderInspectionRsltVw = true;
		renderInspectionBtn = false;
		renderSearchPanel = false;

		filterredInspectionStatuses = inspectionStatuses
				.stream().filter(item -> item.getStatus().equals("Rejected")
						|| item.getStatus().equals("Ready for Inspection") || item.getStatus().equals("Approved"))
				.collect(Collectors.toList());

		if (serviceOrder == null || serviceOrder.size() == 0) {

			inspectionServiceOrder = streetLightService.getServiceOrders(woNo, soNo, vendorName,
					util.convertDtTm(startDate), util.convertDtTm(endDate), invStatus, inspectionStatusId,
					util.getWrkGrp(), ivueStatus);

			inspectionServiceOrder = inspectionServiceOrder.stream().filter(item -> item.getInspectionStatusId() == 2 || item.getInspectionStatusId() == 5)
					.collect(Collectors.toList());
		} else {
			inspectionServiceOrder = serviceOrder.stream()
					.filter(item -> item.getInspectionStatusId() == 2 || item.getInspectionStatusId() == 5)
					.collect(Collectors.toList());
		}
		return STREETLIGHT;
	}
	
	public String searchInvoiceServiceOrder(){
		
		renderInvoiceRsltVw = true;
		renderSearchRsltVw = false;
		renderInspectionRsltVw = false;
		renderInvoiceBtn = false;
		renderInspectionBtn = true;
		renderSearchPanel = false;
		
		try {
			invoiceServiceOrder = streetLightService.getServiceOrderForInvoice(util.getWrkGrp(), "Not Invoiced");
		} catch (ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
			e.printStackTrace();
		}
		return STREETLIGHT;
	}
	
	public String updateStreetlightInspection() throws ProcessException, ValidationException, NoResultException {

		User user = userManagementService.finUserByPrincipal(util.getCurrentUser());
		
		StringBuilder sb = new StringBuilder();
		sb.append(user.getFirstName());
		sb.append(" ");
		sb.append(user.getLastName());

		Integer resourceId = null;
		try {
			resourceId = inspectionService.getResourceId(sb.toString());
		} catch (ValidationException | NoResultException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
			e.printStackTrace();
		}
		Inspection inspection = null;

		for (StreetLightSearchVw sl : inspectionServiceOrder) {
			if (sl.getInspectionStatusId() != 2) {

				inspection = new Inspection();

				inspection.setInspectionDt(util.currentDtTm());
				inspection.setInspectedBy(resourceId);
				inspection.setInspectionStatusId(sl.getInspectionStatusId());
				inspection.setComments(sl.getInspectedComment());
				inspection.setServiceOrderId(sl.getServiceOrderId());
				inspection.setWorkOrderId(sl.getServiceOrderId());
				inspection.setInspectionType("SL");

				try {
					Integer inspectionId = inspectionService.insertInspection(inspection);
					streetLightService.updateServiceOrderInspection(inspectionId, sl.getServiceOrderId(),
							sl.getInspectionStatusId(), sl.getInspectedComment(), util.currentDtTm(),
							util.getCurrentUser());

					facesInfo("Inspection Successful.");

				} catch (Exception e) {
					logger.error(e);
					facesError(e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return searchStreetLights();
	}
	
	public String updateStreetlightComment(StreetLightSearchVw streetLightSearchVw) {

		try {
			streetLightService.updateServiceOrderComment(streetLightSearchVw.getInspectedComment(),streetLightSearchVw.getServiceOrderId());

			facesInfo("Inspection Successful.");

		} catch (ValidationException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
			e.printStackTrace();
		}
		return searchStreetLights();
	}
	
	public String backToSearch() {
		System.out.println("backToSearch(): Start");
		return STREETLIGHT;
	}
	
	public String backToSearchFromInvoice() {
		System.out.println("backToSearchFromInvoice(): Start");
		return STREETLIGHT_FROM_INVOICE;
	}
	
	public void invoiceSearch() throws ProcessException {
		renderSelection = true;
		
		serviceOrder = serviceOrder.stream().filter(item -> item.getWorkGroup().equals(util.getWrkGrp()) && item.getInvoiceId() == 0).collect(Collectors.toList());
	}
	
	public void searchInvSummary() throws ProcessException {
		List<String> so = new ArrayList<String>();
		for(StreetLightSearchVw soId : invoiceServiceOrder) {
			so.add(new String(soId.getServiceOrderId()));
		}

		invSummary = streetLightService.getServiceOrderSummary(so, util.getWrkGrp());
	}

	public String submitSLInvoice() throws ProcessException, NoResultException, ValidationException {

		//searchInvSummary();
		
		if ((selectedInvoiceServiceOrder.size() <= 0)) {
			facesError("At least one Street Light item must be selected for invoice.");
			return null;
		}
		
		final String guid = util.genGUID();
		
		if ((selectedInvoiceServiceOrder.size() > 0)) {
		for (StreetLightSearchVw sl : selectedInvoiceServiceOrder) {
			try {
				streetLightService.updateServiceOrder(guid, sl.getServiceOrderId(), 2);
			} catch (ValidationException ex) {
				logger.error(ex.getMessage());
				facesError(ex.getMessage());
				ex.printStackTrace();
			}
		}
		}
		try {
			invoiceId = invoiceService.updateSubmitInvoice(guid, util.getCurrentUser(), invRefNo, SL_INVOICE);
			facesInfo("Invoice submitted successfully.");
		} catch (Exception ex) {
			facesError(ex.getMessage());
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		
		//util.refreshInvoiceDetails(invoiceId);
		this.invNoLink = invoiceId;
		
		return slInvoiceDetail();
	}
	
	@SuppressWarnings("unused")
	public String submitInvoiceApproval() throws NoResultException, ProcessException {
		
		try {
			for(StreetLightSearchVw sl : invSummary) {
				
				invoiceService.updateStreetlightInvoiceApproval(sl.getServiceOrderId(), 4, util.getCurrentUser(), util.currentDtTm(),sl.getInvoiceApprovedComments());	
			}
			int output = invoiceService.updateInvoiceStatus(invNoLink, 4, SL_INVOICE, util.getCurrentUser(), util.currentDtTm(), null);
			facesInfo("Invoice Approved.");
		} catch (ProcessException | ValidationException e) {
			logger.error(e);
			facesError(e.getMessage());
			e.printStackTrace();
		}
		util.refreshInvoiceSearch();
		
		return INVOICE_SEARCH;
	}
	
	@SuppressWarnings("unused")
	public String submitInvoiceRejected() throws NoResultException, ProcessException {

		PrimeFaces.current().executeScript("PF('rejectedInvoiceDlg').hide()");
		try {
			for (StreetLightSearchVw sl : invSummary) {
				if (sl.getInvoiceStatus().equals("Rejected")) {

					invoiceService.updateStreetlightInvoiceApproval(sl.getServiceOrderId(), 3, util.getCurrentUser(),util.currentDtTm(), sl.getInvoiceApprovedComments());
				}
			}
			
			int output = invoiceService.updateInvoiceStatus(invNoLink, 3, SL_INVOICE, util.getCurrentUser(), util.currentDtTm(), rejectedInvoiceComment);
			facesInfo("Invoice Rejected.");
		} catch (ValidationException e) {
			logger.error(e);
			facesError(e.getMessage());
			e.printStackTrace();
		}
		util.refreshInvoiceSearch();

		return INVOICE_SEARCH;
	}
	
	public String slInvoiceDetail() throws ValidationException, ProcessException, NoResultException {
		
		searchInvoiceDetail();
		
		return INV_SUMMARY;
	}
	
	public String findInvoiceApproval() {

		searchInvoiceDetail();
		
		invoiceApprovalStatus = invoiceStatus.stream().filter(item -> item.getDescription().equals("Submitted") || item.getDescription().equals("Rejected") || item.getDescription().equals("Approved")).collect(Collectors.toList());

		for (StreetLightSearchVw sl : invSummary) {
			if (sl.getInvoiceStatus().equals("Rejected")) {
				disableApprovedBtn = true;
				return INVOICE_APPROVAL;
			} else if (!sl.getInvoiceStatus().equals("Rejected")) {
				disableApprovedBtn = false;
			}
		}
		return INVOICE_APPROVAL;
	}
	
	public String updateApprovalBtn() {
		for (StreetLightSearchVw sl : invSummary) {
			if (sl.getInvoiceStatus().equals("Rejected")) {
				disableApprovedBtn = true;
				facesError("Comment required on rejected Streetlight Invoice item.");
				return INVOICE_APPROVAL;
			} else if (!sl.getInvoiceStatus().equals("Rejected")) {
				disableApprovedBtn = false;
			}
		}
		return INVOICE_APPROVAL;
	}
	
	public void searchInvoiceDetail() {
		
		try {
			invoice = invoiceService.getInvoiceById(invNoLink);
			invoiceDetail = invoiceService.getInvoiceDetails(invNoLink);
			invSummary= streetLightService.getServiceOrderByInvoiceId(invNoLink);
		} catch (ValidationException | ProcessException | NoResultException e) {
			logger.error(e);
			facesError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void slInvRowSelectCheckbox(SelectEvent event) {
		
		BigDecimal sum = BigDecimal.ZERO;
		if(selectedInvoiceServiceOrder.size() > 0) {
			for(StreetLightSearchVw so : selectedInvoiceServiceOrder) {
				
				slAmount = so.getExtPrice();
				
				sum = sum.add(slAmount);
			}
			slSubTotal = sum;
		}
	}
	
	public void slInvRowUnSelectCheckbox(UnselectEvent event) {
		
		slAmount = ((StreetLightSearchVw)event.getObject()).getExtPrice();
		slSubTotal = slSubTotal.subtract(slAmount);
	}
	
	public void slInvAllRowSelectCheckbox(ToggleSelectEvent event) {
		
		BigDecimal sum = BigDecimal.ZERO;
		
		if(selectedInvoiceServiceOrder.size() > 0) {
			for(StreetLightSearchVw so : selectedInvoiceServiceOrder) {
				slSubTotal = so.getExtPrice();
				
				sum = sum.add(slSubTotal);
			}
			slSubTotal = sum;
		}
	}
	
	public void findInvoiceStatus() {
		invoiceStatus = invoiceService.getAllInvStatus();
		
		slInvoiceStatus = invoiceStatus.stream().filter(item -> item.getDescription().equals("Not Invoiced") || item.getDescription().equals("Submitted")).collect(Collectors.toList());
		
		invoiceStatus.sort(Comparator.comparing(InvoiceStatus::getDescription, Comparator.nullsLast(Comparator.naturalOrder())));
	}
	
	public void findInspectionStatus() {
		inspectionStatuses = inspectionService.getInspetionStatus();
		
		inspectionStatuses.sort(Comparator.comparing(InspectionStatus::getStatus, Comparator.nullsLast(Comparator.naturalOrder())));
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
	
	public String getWoNo() {
		return woNo;
	}

	public void setWoNo(String woNo) {
		this.woNo = woNo;
	}

	public String getSoNo() {
		return soNo;
	}

	public void setSoNo(String soNo) {
		this.soNo = soNo;
	}

	public String getLcecInvNo() {
		return lcecInvNo;
	}

	public void setLcecInvNo(String lcecInvNo) {
		this.lcecInvNo = lcecInvNo;
	}

	public String getVendorRefNo() {
		return vendorRefNo;
	}

	public void setVendorRefNo(String vendorRefNo) {
		this.vendorRefNo = vendorRefNo;
	}

	public Integer getInvStatus() {
		return invStatus;
	}

	public void setInvStatus(Integer invStatus) {
		this.invStatus = invStatus;
	}

	public String getInvRefNo() {
		return invRefNo;
	}

	public void setInvRefNo(String invRefNo) {
		this.invRefNo = invRefNo;
	}

	public List<InvoiceStatus> getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(List<InvoiceStatus> invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public List<StreetLightSearchVw> getServiceOrder() {
		return serviceOrder;
	}

	public void setServiceOrder(List<StreetLightSearchVw> serviceOrder) {
		this.serviceOrder = serviceOrder;
	}

	public List<StreetLightSearchVw> getSelectedServiceOrder() {
		return selectedServiceOrder;
	}

	public void setSelectedServiceOrder(List<StreetLightSearchVw> selectedServiceOrder) {
		this.selectedServiceOrder = selectedServiceOrder;
	}

	public List<StreetLightSearchVw> getFilteredServiceOrder() {
		return filteredServiceOrder;
	}

	public void setFilteredServiceOrder(List<StreetLightSearchVw> filteredServiceOrder) {
		this.filteredServiceOrder = filteredServiceOrder;
	}

	public List<StreetLightSearchVw> getInvSummary() {
		return invSummary;
	}

	public void setInvSummary(List<StreetLightSearchVw> invSummary) {
		this.invSummary = invSummary;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public boolean isRenderSelection() {
		return renderSelection;
	}

	public void setRenderSelection(boolean renderSelection) {
		this.renderSelection = renderSelection;
	}

	public Integer getInvNoLink() {
		return invNoLink;
	}

	public void setInvNoLink(Integer invNoLink) {
		this.invNoLink = invNoLink;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Integer getInspectionStatusId() {
		return inspectionStatusId;
	}

	public void setInspectionStatusId(Integer inspectionStatusId) {
		this.inspectionStatusId = inspectionStatusId;
	}

	public List<StreetLightSearchVw> getInspectionServiceOrder() {
		return inspectionServiceOrder;
	}

	public void setInspectionServiceOrder(List<StreetLightSearchVw> inspectionServiceOrder) {
		this.inspectionServiceOrder = inspectionServiceOrder;
	}

	public List<InspectionStatus> getInspectionStatuses() {
		return inspectionStatuses;
	}

	public void setInspectionStatuses(List<InspectionStatus> inspectionStatuses) {
		this.inspectionStatuses = inspectionStatuses;
	}

	public boolean isRenderInspectionRsltVw() {
		return renderInspectionRsltVw;
	}

	public void setRenderInspectionRsltVw(boolean renderInspectionRsltVw) {
		this.renderInspectionRsltVw = renderInspectionRsltVw;
	}

	public boolean isRenderSearchRsltVw() {
		return renderSearchRsltVw;
	}

	public void setRenderSearchRsltVw(boolean renderSearchRsltVw) {
		this.renderSearchRsltVw = renderSearchRsltVw;
	}

	public boolean isRenderInvoiceRsltVw() {
		return renderInvoiceRsltVw;
	}

	public void setRenderInvoiceRsltVw(boolean renderInvoiceRsltVw) {
		this.renderInvoiceRsltVw = renderInvoiceRsltVw;
	}

	public List<StreetLightSearchVw> getInvoiceServiceOrder() {
		return invoiceServiceOrder;
	}

	public void setInvoiceServiceOrder(List<StreetLightSearchVw> invoiceServiceOrder) {
		this.invoiceServiceOrder = invoiceServiceOrder;
	}

	public List<InvoiceDetail> getInvoiceDetail() {
		return invoiceDetail;
	}

	public void setInvoiceDetail(List<InvoiceDetail> invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}

	public boolean isDisableApprovedBtn() {
		return disableApprovedBtn;
	}

	public void setDisableApprovedBtn(boolean disableApprovedBtn) {
		this.disableApprovedBtn = disableApprovedBtn;
	}

	public String getRejectedInvoiceComment() {
		return rejectedInvoiceComment;
	}

	public void setRejectedInvoiceComment(String rejectedInvoiceComment) {
		this.rejectedInvoiceComment = rejectedInvoiceComment;
	}

	public List<InvoiceStatus> getInvoiceApprovalStatus() {
		return invoiceApprovalStatus;
	}

	public void setInvoiceApprovalStatus(List<InvoiceStatus> invoiceApprovalStatus) {
		this.invoiceApprovalStatus = invoiceApprovalStatus;
	}

	public List<InspectionStatus> getFilterredInspectionStatuses() {
		return filterredInspectionStatuses;
	}

	public void setFilterredInspectionStatuses(List<InspectionStatus> filterredInspectionStatuses) {
		this.filterredInspectionStatuses = filterredInspectionStatuses;
	}

	public boolean isRenderInspectionBtn() {
		return renderInspectionBtn;
	}

	public void setRenderInspectionBtn(boolean renderInspectionBtn) {
		this.renderInspectionBtn = renderInspectionBtn;
	}

	public boolean isRenderInvoiceBtn() {
		return renderInvoiceBtn;
	}

	public void setRenderInvoiceBtn(boolean renderInvoiceBtn) {
		this.renderInvoiceBtn = renderInvoiceBtn;
	}

	public List<InvoiceStatus> getSlInvoiceStatus() {
		return slInvoiceStatus;
	}

	public void setSlInvoiceStatus(List<InvoiceStatus> slInvoiceStatus) {
		this.slInvoiceStatus = slInvoiceStatus;
	}

	public String getIvueStatus() {
		return ivueStatus;
	}

	public void setIvueStatus(String ivueStatus) {
		this.ivueStatus = ivueStatus;
	}

	public List<StreetLightSearchVw> getSelectedInvoiceServiceOrder() {
		return selectedInvoiceServiceOrder;
	}

	public void setSelectedInvoiceServiceOrder(List<StreetLightSearchVw> selectedInvoiceServiceOrder) {
		this.selectedInvoiceServiceOrder = selectedInvoiceServiceOrder;
	}

	public boolean isRenderBackToStreetLightBtn() {
		return renderBackToStreetLightBtn;
	}

	public void setRenderBackToStreetLightBtn(boolean renderBackToStreetLightBtn) {
		this.renderBackToStreetLightBtn = renderBackToStreetLightBtn;
	}

	public boolean isRenderSearchPanel() {
		return renderSearchPanel;
	}

	public void setRenderSearchPanel(boolean renderSearchPanel) {
		this.renderSearchPanel = renderSearchPanel;
	}

	public List<StreetLightSearchVw> getFilteredSearchServiceOrder() {
		return filteredSearchServiceOrder;
	}

	public void setFilteredSearchServiceOrder(List<StreetLightSearchVw> filteredSearchServiceOrder) {
		this.filteredSearchServiceOrder = filteredSearchServiceOrder;
	}

	public BigDecimal getSlSubTotal() {
		return slSubTotal;
	}

	public void setSlSubTotal(BigDecimal slSubTotal) {
		this.slSubTotal = slSubTotal;
	}

	public BigDecimal getSlAmount() {
		return slAmount;
	}

	public void setSlAmount(BigDecimal slAmount) {
		this.slAmount = slAmount;
	}
}
