package pwr.lcec.vendor.controller;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import pwr.lcec.vendor.controller.InvoiceController;
import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendorportal.entity.custom.InvoiceSearchVw;
import pwr.lcec.vendorportal.entity.custom.InvoiceStatus;
import pwr.lcec.vendorportal.interfaces.InvoiceLocal;

public class InvoiceController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(InvoiceController.class);
	ControllerUtil util = new ControllerUtil();

	@EJB
	private InvoiceLocal invoiceService;

	private String woNumber;

	private String lcecRefNo;

	private String vendorRefNo;
	private String vendorName;
	private Integer invoiceStatus;
	private List<InvoiceStatus> invoiceStatuses;
	private List<InvoiceSearchVw> invoice;
	private List<String> invWoId;
	private List<String> selectedInvWoId;
	private List<String> selectedInvoiceStatuses;
	private List<String> selectedVendors;
	private String woType;
	private List<InvoiceSearchVw> slInvoice;
	private List<InvoiceSearchVw> ssInvoice;
	private List<InvoiceSearchVw> allInvoice;
	private List<InvoiceSearchVw> result;
	private List<String> distinctStatuses;
	private List<String> distinctVendors;
	private boolean renderApprove = false;
	private boolean renderBackToInvoiceTab = false;
	private boolean renderBackToInvoiceSearch = false;

	public void findInvoices() {
		invoice = invoiceService.getInvoices(woNumber, vendorName, lcecRefNo, vendorRefNo, invoiceStatus, util.getWrkGrp(), woType);
		
		logger.debug("Number of invoices: " + invoice.size());
		
		result = invoice;

		for (InvoiceSearchVw inv : invoice) {
			if (inv.getInvoiceStatus().getDescription().equals("Submitted")) {
				renderApprove = true;
				continue;
			}
			renderApprove = false;
		}

		if (util.subject().isPermitted("SL-INV:L-View") || util.subject().isPermitted("SL-INV:V-View")) {
			slInvoice = invoice.stream().filter(inv -> inv.getInvoiceType().equals("SL"))
					.collect(Collectors.toList());
			invoice = slInvoice;
		}
		if (util.subject().isPermitted("S-INV:V-View") || util.subject().isPermitted("S-INV:L-View")) {
			invoice = result;
			ssInvoice = invoice.stream().filter(inv -> inv.getInvoiceType().equals("SS"))
					.collect(Collectors.toList());
			invoice = ssInvoice;
		}
		if (util.subject().isPermitted("SL-INV:L-View")
				|| (util.subject().isPermitted("SL-INV:V-View") && util.subject().isPermitted("S-INV:V-View"))
				|| util.subject().isPermitted("S-INV:L-View")) {
			invoice = result;
		}

		findDistinctStatuses(invoice);
		findDistinctVendors(invoice);
	}
	
	public List<InvoiceStatus> findInvoiceStatus() {
		invoiceStatuses = invoiceService.getAllInvStatus();
		logger.debug("Invoice Statuses:  " + invoiceStatuses.size());

		/*invoiceStatuses = invoiceStatuses.stream().filter(
				item -> !(!item.getDescription().equals("Not Invoiced") && !item.getDescription().equals("Submitted")))
				.collect(Collectors.toList());*/

		invoiceStatuses.sort(
				Comparator.comparing(InvoiceStatus::getDescription, Comparator.nullsLast(Comparator.naturalOrder())));
		
		return invoiceStatuses;
	}

	public void resetInvoiceSearch() {
		this.woNumber = "";
		this.lcecRefNo = "";
		this.vendorRefNo = "";
		this.vendorName = "";
		this.invoiceStatus = Integer.valueOf(0);
		this.woType = "";
		this.selectedInvoiceStatuses = new ArrayList<String>();
		this.selectedVendors = new ArrayList<String>();
		this.distinctStatuses = new ArrayList<String>();
		this.distinctVendors = new ArrayList<String>();

		this.invoice = new ArrayList<InvoiceSearchVw>();

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent("invSearchForm:INV_SEARCH_TABLE");
		dataTable.setSortBy(null);

		dataTable.setFilteredValue(null);
		//TODO: The setFilters method must have been removed in primefaces 8.0 need to figure out if this is needed or what can replace it.
		//dataTable.setFilters(null);

		dataTable.reset();

		PrimeFaces.current().clearTableStates();
	}

	public List<String> findDistinctStatuses(List<InvoiceSearchVw> invoices) {
		distinctStatuses = new ArrayList<String>();

		if (invoices != null) {
			for (InvoiceSearchVw det : invoices) {
				distinctStatuses.add(det.getInvoiceStatus().getDescription());
			}
			distinctStatuses = distinctStatuses.stream().distinct().collect(Collectors.toList());
			Collections.sort(distinctStatuses);
		}
		return distinctStatuses;
	}

	public boolean filterStatusFunction(Object value, Object filter, Locale locale) {
		if (value == null || !(value instanceof String)) {
			return true;
		}

		String valueInRow = (String) value;

		if (this.selectedInvoiceStatuses == null || this.selectedInvoiceStatuses.size() == 0) {
			return true;
		}

		for (int i = 0; i < this.selectedInvoiceStatuses.size(); i++) {
			if (valueInRow.compareTo(String.valueOf(this.selectedInvoiceStatuses.get(i))) == 0) {
				return true;
			}
		}

		return false;
	}

	public List<String> findDistinctVendors(List<InvoiceSearchVw> invoices) {
		this.distinctVendors = new ArrayList<String>();

		if (invoices != null) {
			for (InvoiceSearchVw det : invoices) {
				this.distinctVendors.add(det.getVendorName());
			}
			distinctVendors = distinctVendors.stream().distinct().collect(Collectors.toList());
			Collections.sort(this.distinctVendors);
		}
		return this.distinctVendors;
	}

	public boolean filterVendorFunction(Object value, Object filter, Locale locale) {
		if (value == null || !(value instanceof String)) {
			return true;
		}

		String valueInRow = (String) value;

		if (this.selectedVendors == null || this.selectedVendors.size() == 0) {
			return true;
		}

		for (int i = 0; i < this.selectedVendors.size(); i++) {
			if (valueInRow.compareTo(String.valueOf(this.selectedVendors.get(i))) == 0) {
				return true;
			}
		}

		return false;
	}

	public void findInvWoId() {
		try {
			this.invWoId = this.invoiceService.getWoForInvoice(this.util.getWrkGrp());
		} catch (Exception e) {
			logger.error(e);
			facesError(e.getMessage());
		}
	}

	private void facesError(String message) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
	}

	public void preProcessorStakingSheetRsltPDF(Object document) {
		Document doc = (Document) document;
		doc.setPageSize(PageSize.A4.rotate());
	}

	public String getWoNumber() {
		return this.woNumber;
	}

	public void setWoNumber(String woNumber) {
		this.woNumber = woNumber;
	}

	public List<InvoiceSearchVw> getInvoice() {
		return this.invoice;
	}

	public void setInvoice(List<InvoiceSearchVw> invoice) {
		this.invoice = invoice;
	}

	public List<String> getInvWoId() {
		return this.invWoId;
	}

	public void setInvWoId(List<String> invWoId) {
		this.invWoId = invWoId;
	}

	public List<String> getSelectedInvWoId() {
		return this.selectedInvWoId;
	}

	public void setSelectedInvWoId(List<String> selectedInvWoId) {
		this.selectedInvWoId = selectedInvWoId;
	}

	public String getVendorRefNo() {
		return this.vendorRefNo;
	}

	public void setVendorRefNo(String vendorRefNo) {
		this.vendorRefNo = vendorRefNo;
	}

	public Integer getInvoiceStatus() {
		return this.invoiceStatus;
	}

	public void setInvoiceStatus(Integer invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getLcecRefNo() {
		return this.lcecRefNo;
	}

	public void setLcecRefNo(String lcecRefNo) {
		this.lcecRefNo = lcecRefNo;
	}

	public boolean isRenderApprove() {
		return this.renderApprove;
	}

	public void setRenderApprove(boolean renderApprove) {
		this.renderApprove = renderApprove;
	}

	public String getVendorName() {
		return this.vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public boolean isRenderBackToInvoiceTab() {
		return this.renderBackToInvoiceTab;
	}

	public void setRenderBackToInvoiceTab(boolean renderBackToInvoiceTab) {
		this.renderBackToInvoiceTab = renderBackToInvoiceTab;
	}

	public boolean isRenderBackToInvoiceSearch() {
		return this.renderBackToInvoiceSearch;
	}

	public void setRenderBackToInvoiceSearch(boolean renderBackToInvoiceSearch) {
		this.renderBackToInvoiceSearch = renderBackToInvoiceSearch;
	}

	public String getWoType() {
		return this.woType;
	}

	public void setWoType(String woType) {
		this.woType = woType;
	}

	public List<InvoiceSearchVw> getSlInvoice() {
		return this.slInvoice;
	}

	public void setSlInvoice(List<InvoiceSearchVw> slInvoice) {
		this.slInvoice = slInvoice;
	}

	public List<InvoiceSearchVw> getSsInvoice() {
		return this.ssInvoice;
	}

	public void setSsInvoice(List<InvoiceSearchVw> ssInvoice) {
		this.ssInvoice = ssInvoice;
	}

	public List<InvoiceSearchVw> getAllInvoice() {
		return this.allInvoice;
	}

	public void setAllInvoice(List<InvoiceSearchVw> allInvoice) {
		this.allInvoice = allInvoice;
	}

	public List<InvoiceSearchVw> getResult() {
		return this.result;
	}

	public void setResult(List<InvoiceSearchVw> result) {
		this.result = result;
	}

	public List<String> getSelectedInvoiceStatuses() {
		return this.selectedInvoiceStatuses;
	}

	public void setSelectedInvoiceStatuses(List<String> selectedInvoiceStatuses) {
		this.selectedInvoiceStatuses = selectedInvoiceStatuses;
	}

	public List<String> getSelectedVendors() {
		return this.selectedVendors;
	}

	public void setSelectedVendors(List<String> selectedVendors) {
		this.selectedVendors = selectedVendors;
	}

	public List<String> getDistinctStatuses() {
		return this.distinctStatuses;
	}

	public void setDistinctStatuses(List<String> distinctStatuses) {
		this.distinctStatuses = distinctStatuses;
	}

	public List<String> getDistinctVendors() {
		return this.distinctVendors;
	}

	public void setDistinctVendors(List<String> distinctVendors) {
		this.distinctVendors = distinctVendors;
	}

	public List<InvoiceStatus> getInvoiceStatuses() {
		return invoiceStatuses;
	}

	public void setInvoiceStatuses(List<InvoiceStatus> invoiceStatuses) {
		this.invoiceStatuses = invoiceStatuses;
	}
	
}
