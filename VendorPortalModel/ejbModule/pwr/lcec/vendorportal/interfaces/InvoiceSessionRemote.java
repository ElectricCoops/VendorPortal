package pwr.lcec.vendorportal.interfaces;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Remote;

import pwr.lcec.vendorportal.custom.entity.Invoice;
import pwr.lcec.vendorportal.custom.entity.InvoiceDetail;
import pwr.lcec.vendorportal.custom.entity.InvoiceGLSummaryVw;
import pwr.lcec.vendorportal.custom.entity.InvoiceSearchVw;
import pwr.lcec.vendorportal.custom.entity.InvoiceStatus;
import pwr.lcec.vendorportal.exception.NoResultException;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;

@Remote
public interface InvoiceSessionRemote {

	public List<Invoice> getAllInvoices();

	public List<Invoice> getInvoiceByWoId(String woid, String wrkgrp) throws ValidationException, ProcessException, NoResultException;

	public Invoice getInvoiceById(Integer invId) throws ValidationException, ProcessException, NoResultException;
	
	public List<InvoiceSearchVw> getAllSearchInvoices(String workgroup) throws ProcessException;
	
	public List<InvoiceSearchVw> getSearchInvoiceById(String invId, String workgroup) throws ValidationException, ProcessException, NoResultException;
	
	public List<InvoiceSearchVw> getSearchInvoiceByWoId(String woId, String workgroup) throws ValidationException, ProcessException, NoResultException;
	
	public List<InvoiceSearchVw> getSearchInvoiceByVenRef(String venRefNo, String workgroup) throws ValidationException, ProcessException, NoResultException;
	
	public List<String> getWoForInvoice(String workgroup) throws NoResultException, ProcessException;
	
	public int updateSubmitInvoice(String guid, String invoicedBy, String vendorRefNo, String invoiceType) throws ValidationException, ProcessException;
	
	public int updateWorkflowCalc(Integer stakingsheetId, Timestamp rateDt, String typeFlg) throws ValidationException;
	
	public List<InvoiceStatus> getAllInvStatus();
	
	public List<InvoiceSearchVw> getInvoiceByStatus(String workgroup, String invoiceStatus) throws NoResultException, ProcessException;
	
	public List<InvoiceSearchVw> getInvoices(String woId, String vendorName, String lcecRef, String vendorRef, Integer invoiceStatus, String workgroup, String woType) throws NoResultException, ProcessException;
 
	public int updateInvoiceStatus(Integer invoiceId, Integer invoiceStatus, String invoiceType, String approvedBy, Timestamp approvedDt, String approvedComment) throws ValidationException;

	public void updateInvoiceApproval(Integer invoiceId, Integer invoiceStatusId, String invoiceAprrovedBy, Timestamp invoiceApprovedDt) throws ProcessException;
	
	public List<InvoiceGLSummaryVw> getInvoiceGLSummaryVw(Integer invoiceId) throws ProcessException, NoResultException;
	
	public List<InvoiceDetail> getInvoiceDetails(Integer invoiceId) throws ProcessException, NoResultException;
	
	public void updateStreetlightInvoiceApproval(String soId, Integer invoiceStatusId,
			String invoiceAprrovedBy, Timestamp invoiceApprovedDt, String invoiceApprovedComment) throws ProcessException;
}
