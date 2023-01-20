package pwr.lcec.vendor.controller;

import java.io.Serializable;

/*import java.io.Serializable;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;
import pwr.lcec.vendor.controller.StreetLightController;
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
import pwr.lcec.vendorportal.sec.entity.User;*/

public class StreetLightController implements Serializable {
	private static final long serialVersionUID = 1L;
	/*private static Logger logger = LogManager.getLogger(StreetLightController.class);

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
			this.serviceOrder = this.streetLightService.getServiceOrders(this.woNo, this.soNo, this.vendorName,
					this.util.convertDtTm(this.startDate), this.util.convertDtTm(this.endDate), this.invStatus,
					this.inspectionStatusId, this.util.getWrkGrp(), this.ivueStatus);

			if (!this.util.getWrkGrp().equals(LCEC)) {
				serviceOrder = serviceOrder.stream()
						.filter(so -> !(!so.getInspectionStatus().getStatus().equals("Approved")
								&& !so.getInspectionStatus().getStatus().equals("Rejected")))
						.collect(Collectors.toList());
			}

			this.renderInvoiceRsltVw = false;
			this.renderSelection = false;
			this.renderSearchRsltVw = true;
			this.renderInspectionRsltVw = false;
			this.renderInspectionBtn = true;
			this.renderInvoiceBtn = true;
			this.renderSearchPanel = true;
		} catch (ProcessException e) {
			facesError(e.getMessage());
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return STREETLIGHT;
	}

	public String createInspection() throws ProcessException {
		this.renderInvoiceRsltVw = false;
		this.renderSearchRsltVw = false;
		this.renderInspectionRsltVw = true;
		this.renderInspectionBtn = false;
		this.renderSearchPanel = false;

		filterredInspectionStatuses = inspectionStatuses.stream()
				.filter(item -> !(!item.getStatus().equals("Rejected")
						&& !item.getStatus().equals("Ready for Inspection") && !item.getStatus().equals("Approved")))
				.collect(Collectors.toList());

		if (this.serviceOrder == null || this.serviceOrder.size() == 0) {

			inspectionServiceOrder = streetLightService.getServiceOrders(this.woNo, this.soNo,
					this.vendorName, this.util.convertDtTm(this.startDate), this.util.convertDtTm(this.endDate),
					this.invStatus, this.inspectionStatusId, this.util.getWrkGrp(), this.ivueStatus);

			inspectionServiceOrder = inspectionServiceOrder.stream()
					.filter(item -> !(item.getInspectionStatusId().intValue() != 2
							&& item.getInspectionStatusId().intValue() != 5))
					.collect(Collectors.toList());
		} else {
			inspectionServiceOrder = serviceOrder.stream()
					.filter(item -> !(item.getInspectionStatusId().intValue() != 2
							&& item.getInspectionStatusId().intValue() != 5))
					.collect(Collectors.toList());
		}
		return STREETLIGHT;
	}

	public String searchInvoiceServiceOrder() {
		this.renderInvoiceRsltVw = true;
		this.renderSearchRsltVw = false;
		this.renderInspectionRsltVw = false;
		this.renderInvoiceBtn = false;
		this.renderInspectionBtn = true;
		this.renderSearchPanel = false;

		try {
			this.invoiceServiceOrder = this.streetLightService.getServiceOrderForInvoice(this.util.getWrkGrp(),
					"Not Invoiced");
		} catch (ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
			e.printStackTrace();
		}
		return STREETLIGHT;
	}

	public String updateStreetlightInspection() throws ValidationException, ProcessException, NoResultException {
		User user = this.userManagementService.finUserByPrincipal(this.util.getCurrentUser());

		StringBuilder sb = new StringBuilder();
		sb.append(user.getFirstName());
		sb.append(" ");
		sb.append(user.getLastName());

		Integer resourceId = null;
		try {
			resourceId = Integer.valueOf(this.inspectionService.getResourceId(sb.toString()));
		} catch (ValidationException | pwr.lcec.vendorportal.exception.NoResultException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
			e.printStackTrace();
		}
		Inspection inspection = null;

		for (StreetLightSearchVw sl : this.inspectionServiceOrder) {
			if (sl.getInspectionStatusId().intValue() != 2) {

				inspection = new Inspection();

				inspection.setInspectionDt(this.util.currentDtTm());
				inspection.setInspectedBy(resourceId.intValue());
				inspection.setInspectionStatusId(sl.getInspectionStatusId().intValue());
				inspection.setComments(sl.getInspectedComment());
				inspection.setServiceOrderId(sl.getServiceOrderId());
				inspection.setWorkOrderId(sl.getServiceOrderId());
				inspection.setInspectionType(SL_INVOICE);

				try {
					Integer inspectionId = this.inspectionService.insertInspection(inspection);
					this.streetLightService.updateServiceOrderInspection(inspectionId, sl.getServiceOrderId(),
							sl.getInspectionStatusId(), sl.getInspectedComment(), this.util.currentDtTm(),
							this.util.getCurrentUser());

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
			this.streetLightService.updateServiceOrderComment(streetLightSearchVw.getInspectedComment(),
					streetLightSearchVw.getServiceOrderId());

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

	public void invoiceSearch() {
		this.renderSelection = true;

		serviceOrder = serviceOrder.stream().filter(
				item -> (item.getWorkGroup().equals(this.util.getWrkGrp()) && item.getInvoiceId().intValue() == 0))
				.collect(Collectors.toList());
	}

	public void searchInvSummary() throws ProcessException {
		List<String> so = new ArrayList<String>();
		for (StreetLightSearchVw soId : this.invoiceServiceOrder) {
			so.add(new String(soId.getServiceOrderId()));
		}

		this.invSummary = this.streetLightService.getServiceOrderSummary(so, this.util.getWrkGrp());
	}

	public String submitSLInvoice() throws ProcessException {
		if (this.selectedInvoiceServiceOrder.size() <= 0) {
			facesError("At least one Street Light item must be selected for invoice.");
			return null;
		}

		String guid = this.util.genGUID();

		if (this.selectedInvoiceServiceOrder.size() > 0) {
			for (StreetLightSearchVw sl : this.selectedInvoiceServiceOrder) {
				try {
					this.streetLightService.updateServiceOrder(guid, sl.getServiceOrderId(), Integer.valueOf(2));
				} catch (ValidationException ex) {
					logger.error(ex.getMessage());
					facesError(ex.getMessage());
					ex.printStackTrace();
				}
			}
		}

		try {
			facesInfo("Invoice submitted successfully.");
		} catch (Exception ex) {
			facesError(ex.getMessage());
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}

		this.invNoLink = this.invoiceId;

		return slInvoiceDetail();
	}

	public String submitInvoiceApproval() {
		try {
			for (StreetLightSearchVw sl : this.invSummary) {
				this.invoiceService.updateStreetlightInvoiceApproval(sl.getServiceOrderId(), Integer.valueOf(4),
						this.util.getCurrentUser(), this.util.currentDtTm(), sl.getInvoiceApprovedComments());
			}
			invoiceService.updateInvoiceStatus(this.invNoLink, Integer.valueOf(4), SL_INVOICE,
					this.util.getCurrentUser(), this.util.currentDtTm(), null);
			facesInfo("Invoice Approved.");
		} catch (ProcessException | ValidationException e) {
			logger.error(e);
			facesError(e.getMessage());
			e.printStackTrace();
		}

		return INVOICE_SEARCH;
	}

	public String submitInvoiceRejected() throws ProcessException {
		PrimeFaces.current().executeScript("PF('rejectedInvoiceDlg').hide()");
		try {
			for (StreetLightSearchVw sl : this.invSummary) {
				if (sl.getInvoiceStatus().equals("Rejected")) {
					invoiceService.updateStreetlightInvoiceApproval(sl.getServiceOrderId(), Integer.valueOf(3),
							util.getCurrentUser(), this.util.currentDtTm(), sl.getInvoiceApprovedComments());
				}
			}

			invoiceService.updateInvoiceStatus(this.invNoLink, Integer.valueOf(3), SL_INVOICE,
					util.getCurrentUser(), this.util.currentDtTm(), this.rejectedInvoiceComment);
			facesInfo("Invoice Rejected.");
		} catch (ValidationException e) {
			logger.error(e);
			facesError(e.getMessage());
			e.printStackTrace();
		}

		return INVOICE_SEARCH;
	}

	public String slInvoiceDetail() {
		searchInvoiceDetail();

		return INV_SUMMARY;
	}

	public String findInvoiceApproval() {
		searchInvoiceDetail();

		invoiceApprovalStatus = invoiceStatus.stream()
				.filter(item -> !(!item.getDescription().equals("Submitted")
						&& !item.getDescription().equals("Rejected") && !item.getDescription().equals("Approved")))
				.collect(Collectors.toList());

		for (StreetLightSearchVw sl : this.invSummary) {
			if (sl.getInvoiceStatus().equals("Rejected")) {
				this.disableApprovedBtn = true;
				return INVOICE_APPROVAL;
			}
			if (!sl.getInvoiceStatus().equals("Rejected")) {
				this.disableApprovedBtn = false;
			}
		}
		return INVOICE_APPROVAL;
	}

	public String updateApprovalBtn() {
		for (StreetLightSearchVw sl : this.invSummary) {
			if (sl.getInvoiceStatus().equals("Rejected")) {
				this.disableApprovedBtn = true;
				facesError("Comment required on rejected Streetlight Invoice item.");
				return INVOICE_APPROVAL;
			}
			if (!sl.getInvoiceStatus().equals("Rejected")) {
				this.disableApprovedBtn = false;
			}
		}
		return INVOICE_APPROVAL;
	}

	public void searchInvoiceDetail() {
		try {
			this.invoice = this.invoiceService.getInvoiceById(this.invNoLink);
			this.invoiceDetail = this.invoiceService.getInvoiceDetails(this.invNoLink);
			this.invSummary = this.streetLightService.getServiceOrderByInvoiceId(this.invNoLink);
		} catch (ValidationException | ProcessException | pwr.lcec.vendorportal.exception.NoResultException e) {
			logger.error(e);
			facesError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void slInvRowSelectCheckbox(SelectEvent event) {
		BigDecimal sum = BigDecimal.ZERO;
		if (this.selectedInvoiceServiceOrder.size() > 0) {
			for (StreetLightSearchVw so : this.selectedInvoiceServiceOrder) {

				this.slAmount = so.getExtPrice();

				sum = sum.add(this.slAmount);
			}
			this.slSubTotal = sum;
		}
	}

	public void slInvRowUnSelectCheckbox(UnselectEvent event) {
		this.slAmount = ((StreetLightSearchVw) event.getObject()).getExtPrice();
		this.slSubTotal = this.slSubTotal.subtract(this.slAmount);
	}

	public void slInvAllRowSelectCheckbox(ToggleSelectEvent event) {
		BigDecimal sum = BigDecimal.ZERO;

		if (this.selectedInvoiceServiceOrder.size() > 0) {
			for (StreetLightSearchVw so : this.selectedInvoiceServiceOrder) {
				this.slSubTotal = so.getExtPrice();

				sum = sum.add(this.slSubTotal);
			}
			this.slSubTotal = sum;
		}
	}

	public void findInvoiceStatus() {
		this.invoiceStatus = this.invoiceService.getAllInvStatus();

		slInvoiceStatus = invoiceStatus.stream().filter(
				item -> !(!item.getDescription().equals("Not Invoiced") && !item.getDescription().equals("Submitted")))
				.collect(Collectors.toList());

		this.invoiceStatus.sort(
				Comparator.comparing(InvoiceStatus::getDescription, Comparator.nullsLast(Comparator.naturalOrder())));
	}

	public void findInspectionStatus() {
		this.inspectionStatuses = this.inspectionService.getInspetionStatus();

		this.inspectionStatuses.sort(
				Comparator.comparing(InspectionStatus::getStatus, Comparator.nullsLast(Comparator.naturalOrder())));
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
		return this.woNo;
	}

	public void setWoNo(String woNo) {
		this.woNo = woNo;
	}

	public String getSoNo() {
		return this.soNo;
	}

	public void setSoNo(String soNo) {
		this.soNo = soNo;
	}

	public String getLcecInvNo() {
		return this.lcecInvNo;
	}

	public void setLcecInvNo(String lcecInvNo) {
		this.lcecInvNo = lcecInvNo;
	}

	public String getVendorRefNo() {
		return this.vendorRefNo;
	}

	public void setVendorRefNo(String vendorRefNo) {
		this.vendorRefNo = vendorRefNo;
	}

	public Integer getInvStatus() {
		return this.invStatus;
	}

	public void setInvStatus(Integer invStatus) {
		this.invStatus = invStatus;
	}

	public String getInvRefNo() {
		return this.invRefNo;
	}

	public void setInvRefNo(String invRefNo) {
		this.invRefNo = invRefNo;
	}

	public List<InvoiceStatus> getInvoiceStatus() {
		return this.invoiceStatus;
	}

	public void setInvoiceStatus(List<InvoiceStatus> invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public List<StreetLightSearchVw> getServiceOrder() {
		return this.serviceOrder;
	}

	public void setServiceOrder(List<StreetLightSearchVw> serviceOrder) {
		this.serviceOrder = serviceOrder;
	}

	public List<StreetLightSearchVw> getSelectedServiceOrder() {
		return this.selectedServiceOrder;
	}

	public void setSelectedServiceOrder(List<StreetLightSearchVw> selectedServiceOrder) {
		this.selectedServiceOrder = selectedServiceOrder;
	}

	public List<StreetLightSearchVw> getFilteredServiceOrder() {
		return this.filteredServiceOrder;
	}

	public void setFilteredServiceOrder(List<StreetLightSearchVw> filteredServiceOrder) {
		this.filteredServiceOrder = filteredServiceOrder;
	}

	public List<StreetLightSearchVw> getInvSummary() {
		return this.invSummary;
	}

	public void setInvSummary(List<StreetLightSearchVw> invSummary) {
		this.invSummary = invSummary;
	}

	public Integer getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public boolean isRenderSelection() {
		return this.renderSelection;
	}

	public void setRenderSelection(boolean renderSelection) {
		this.renderSelection = renderSelection;
	}

	public Integer getInvNoLink() {
		return this.invNoLink;
	}

	public void setInvNoLink(Integer invNoLink) {
		this.invNoLink = invNoLink;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getVendorName() {
		return this.vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Integer getInspectionStatusId() {
		return this.inspectionStatusId;
	}

	public void setInspectionStatusId(Integer inspectionStatusId) {
		this.inspectionStatusId = inspectionStatusId;
	}

	public List<StreetLightSearchVw> getInspectionServiceOrder() {
		return this.inspectionServiceOrder;
	}

	public void setInspectionServiceOrder(List<StreetLightSearchVw> inspectionServiceOrder) {
		this.inspectionServiceOrder = inspectionServiceOrder;
	}

	public List<InspectionStatus> getInspectionStatuses() {
		return this.inspectionStatuses;
	}

	public void setInspectionStatuses(List<InspectionStatus> inspectionStatuses) {
		this.inspectionStatuses = inspectionStatuses;
	}

	public boolean isRenderInspectionRsltVw() {
		return this.renderInspectionRsltVw;
	}

	public void setRenderInspectionRsltVw(boolean renderInspectionRsltVw) {
		this.renderInspectionRsltVw = renderInspectionRsltVw;
	}

	public boolean isRenderSearchRsltVw() {
		return this.renderSearchRsltVw;
	}

	public void setRenderSearchRsltVw(boolean renderSearchRsltVw) {
		this.renderSearchRsltVw = renderSearchRsltVw;
	}

	public boolean isRenderInvoiceRsltVw() {
		return this.renderInvoiceRsltVw;
	}

	public void setRenderInvoiceRsltVw(boolean renderInvoiceRsltVw) {
		this.renderInvoiceRsltVw = renderInvoiceRsltVw;
	}

	public List<StreetLightSearchVw> getInvoiceServiceOrder() {
		return this.invoiceServiceOrder;
	}

	public void setInvoiceServiceOrder(List<StreetLightSearchVw> invoiceServiceOrder) {
		this.invoiceServiceOrder = invoiceServiceOrder;
	}

	public List<InvoiceDetail> getInvoiceDetail() {
		return this.invoiceDetail;
	}

	public void setInvoiceDetail(List<InvoiceDetail> invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}

	public boolean isDisableApprovedBtn() {
		return this.disableApprovedBtn;
	}

	public void setDisableApprovedBtn(boolean disableApprovedBtn) {
		this.disableApprovedBtn = disableApprovedBtn;
	}

	public String getRejectedInvoiceComment() {
		return this.rejectedInvoiceComment;
	}

	public void setRejectedInvoiceComment(String rejectedInvoiceComment) {
		this.rejectedInvoiceComment = rejectedInvoiceComment;
	}

	public List<InvoiceStatus> getInvoiceApprovalStatus() {
		return this.invoiceApprovalStatus;
	}

	public void setInvoiceApprovalStatus(List<InvoiceStatus> invoiceApprovalStatus) {
		this.invoiceApprovalStatus = invoiceApprovalStatus;
	}

	public List<InspectionStatus> getFilterredInspectionStatuses() {
		return this.filterredInspectionStatuses;
	}

	public void setFilterredInspectionStatuses(List<InspectionStatus> filterredInspectionStatuses) {
		this.filterredInspectionStatuses = filterredInspectionStatuses;
	}

	public boolean isRenderInspectionBtn() {
		return this.renderInspectionBtn;
	}

	public void setRenderInspectionBtn(boolean renderInspectionBtn) {
		this.renderInspectionBtn = renderInspectionBtn;
	}

	public boolean isRenderInvoiceBtn() {
		return this.renderInvoiceBtn;
	}

	public void setRenderInvoiceBtn(boolean renderInvoiceBtn) {
		this.renderInvoiceBtn = renderInvoiceBtn;
	}

	public List<InvoiceStatus> getSlInvoiceStatus() {
		return this.slInvoiceStatus;
	}

	public void setSlInvoiceStatus(List<InvoiceStatus> slInvoiceStatus) {
		this.slInvoiceStatus = slInvoiceStatus;
	}

	public String getIvueStatus() {
		return this.ivueStatus;
	}

	public void setIvueStatus(String ivueStatus) {
		this.ivueStatus = ivueStatus;
	}

	public List<StreetLightSearchVw> getSelectedInvoiceServiceOrder() {
		return this.selectedInvoiceServiceOrder;
	}

	public void setSelectedInvoiceServiceOrder(List<StreetLightSearchVw> selectedInvoiceServiceOrder) {
		this.selectedInvoiceServiceOrder = selectedInvoiceServiceOrder;
	}

	public boolean isRenderBackToStreetLightBtn() {
		return this.renderBackToStreetLightBtn;
	}

	public void setRenderBackToStreetLightBtn(boolean renderBackToStreetLightBtn) {
		this.renderBackToStreetLightBtn = renderBackToStreetLightBtn;
	}

	public boolean isRenderSearchPanel() {
		return this.renderSearchPanel;
	}

	public void setRenderSearchPanel(boolean renderSearchPanel) {
		this.renderSearchPanel = renderSearchPanel;
	}

	public List<StreetLightSearchVw> getFilteredSearchServiceOrder() {
		return this.filteredSearchServiceOrder;
	}

	public void setFilteredSearchServiceOrder(List<StreetLightSearchVw> filteredSearchServiceOrder) {
		this.filteredSearchServiceOrder = filteredSearchServiceOrder;
	}

	public BigDecimal getSlSubTotal() {
		return this.slSubTotal;
	}

	public void setSlSubTotal(BigDecimal slSubTotal) {
		this.slSubTotal = slSubTotal;
	}

	public BigDecimal getSlAmount() {
		return this.slAmount;
	}

	public void setSlAmount(BigDecimal slAmount) {
		this.slAmount = slAmount;
	}*/
}
