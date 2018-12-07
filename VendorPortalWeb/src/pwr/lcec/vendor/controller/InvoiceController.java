package pwr.lcec.vendor.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendorportal.custom.entity.InvoiceSearchVw;
import pwr.lcec.vendorportal.exception.NoResultException;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.interfaces.InvoiceSessionRemote;

public class InvoiceController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(InvoiceController.class);
	ControllerUtil util = new ControllerUtil();

	@EJB
	private InvoiceSessionRemote invoiceService;

	private String woNumber;
	private String lcecRefNo;
	private String vendorRefNo;
	private String vendorName;
	private Integer invoiceStatus;
	private List<InvoiceSearchVw> invoice;
	private List<String> invWoId;
	private List<String> selectedInvWoId;
	private String woType;
	private List<InvoiceSearchVw> slInvoice;
	private List<InvoiceSearchVw> ssInvoice;
	private List<InvoiceSearchVw> allInvoice;
	private List<InvoiceSearchVw> result;
	
	
	private boolean renderApprove = false;
	private boolean renderBackToInvoiceTab = false;
	private boolean renderBackToInvoiceSearch = false;
	
	

	public void findInvoices() throws NoResultException, ProcessException {

		invoice = invoiceService.getInvoices(woNumber, vendorName, lcecRefNo, vendorRefNo, invoiceStatus,
				util.getWrkGrp(), woType);
		result = invoice;

		for (InvoiceSearchVw inv : invoice) {
			if (inv.getInvoiceStatus().getDescription().equals("Submitted")) {
				renderApprove = true;
			} else {
				renderApprove = false;
			}
		}
		if (util.subject().isPermitted("SL-INV:L-View") || util.subject().isPermitted("SL-INV:V-View")) {
			slInvoice = invoice.stream().filter(inv -> inv.getInvoiceType().equals("SL")).collect(Collectors.toList());
			invoice = slInvoice;
		} if (util.subject().isPermitted("S-INV:V-View") || util.subject().isPermitted("S-INV:L-View")) {
			invoice = result;
			ssInvoice = invoice.stream().filter(inv -> inv.getInvoiceType().equals("SS")).collect(Collectors.toList());
			invoice = ssInvoice;
		} if (util.subject().isPermitted("SL-INV:L-View") || util.subject().isPermitted("SL-INV:V-View")
				&& util.subject().isPermitted("S-INV:V-View") || util.subject().isPermitted("S-INV:L-View")) {
			invoice = result;
		}
	}

	public void findInvWoId() {
		try {
			invWoId = invoiceService.getWoForInvoice(util.getWrkGrp());
		} catch (NoResultException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
		}
	}
	
	private void facesError(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}

	public String getWoNumber() {
		return woNumber;
	}

	public void setWoNumber(String woNumber) {
		this.woNumber = woNumber;
	}

	public List<InvoiceSearchVw> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<InvoiceSearchVw> invoice) {
		this.invoice = invoice;
	}

	public List<String> getInvWoId() {
		return invWoId;
	}

	public void setInvWoId(List<String> invWoId) {
		this.invWoId = invWoId;
	}

	public List<String> getSelectedInvWoId() {
		return selectedInvWoId;
	}

	public void setSelectedInvWoId(List<String> selectedInvWoId) {
		this.selectedInvWoId = selectedInvWoId;
	}

	public String getVendorRefNo() {
		return vendorRefNo;
	}

	public void setVendorRefNo(String vendorRefNo) {
		this.vendorRefNo = vendorRefNo;
	}

	public Integer getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(Integer invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getLcecRefNo() {
		return lcecRefNo;
	}

	public void setLcecRefNo(String lcecRefNo) {
		this.lcecRefNo = lcecRefNo;
	}

	public boolean isRenderApprove() {
		return renderApprove;
	}

	public void setRenderApprove(boolean renderApprove) {
		this.renderApprove = renderApprove;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public boolean isRenderBackToInvoiceTab() {
		return renderBackToInvoiceTab;
	}

	public void setRenderBackToInvoiceTab(boolean renderBackToInvoiceTab) {
		this.renderBackToInvoiceTab = renderBackToInvoiceTab;
	}

	public boolean isRenderBackToInvoiceSearch() {
		return renderBackToInvoiceSearch;
	}

	public void setRenderBackToInvoiceSearch(boolean renderBackToInvoiceSearch) {
		this.renderBackToInvoiceSearch = renderBackToInvoiceSearch;
	}

	public String getWoType() {
		return woType;
	}

	public void setWoType(String woType) {
		this.woType = woType;
	}

	public List<InvoiceSearchVw> getSlInvoice() {
		return slInvoice;
	}

	public void setSlInvoice(List<InvoiceSearchVw> slInvoice) {
		this.slInvoice = slInvoice;
	}

	public List<InvoiceSearchVw> getSsInvoice() {
		return ssInvoice;
	}

	public void setSsInvoice(List<InvoiceSearchVw> ssInvoice) {
		this.ssInvoice = ssInvoice;
	}

	public List<InvoiceSearchVw> getAllInvoice() {
		return allInvoice;
	}

	public void setAllInvoice(List<InvoiceSearchVw> allInvoice) {
		this.allInvoice = allInvoice;
	}

	public List<InvoiceSearchVw> getResult() {
		return result;
	}

	public void setResult(List<InvoiceSearchVw> result) {
		this.result = result;
	}
}
