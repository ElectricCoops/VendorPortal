package pwr.lcec.vendorportal.interfaces;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Local;

import pwr.lcec.vendorportal.entity.custom.Invoice;
import pwr.lcec.vendorportal.entity.custom.InvoiceDetail;
import pwr.lcec.vendorportal.entity.custom.InvoiceGLSummaryVw;
import pwr.lcec.vendorportal.entity.custom.InvoiceSearchVw;
import pwr.lcec.vendorportal.entity.custom.InvoiceStatus;

@Local
public interface InvoiceLocal {
	
	public List<Invoice> getAllInvoices();

	public List<Invoice> getInvoiceByWoId(String woid, String wrkgrp);

	public Invoice getInvoiceById(Integer invId);
	
	public List<InvoiceSearchVw> getAllSearchInvoices(String workgroup);
	
	public List<InvoiceSearchVw> getSearchInvoiceById(String invId, String workgroup);
	
	public List<InvoiceSearchVw> getSearchInvoiceByWoId(String woId, String workgroup);
	
	public List<InvoiceSearchVw> getSearchInvoiceByVenRef(String venRefNo, String workgroup);
	
	public List<String> getWoForInvoice(String workgroup);
	
	public int updateSubmitInvoice(String guid, String invoicedBy, String vendorRefNo, String invoiceType, Timestamp workEventDt);
	
	public int updateWorkflowCalc(Integer stakingsheetId, Timestamp rateDt, String typeFlg);
	
	public List<InvoiceStatus> getAllInvStatus();
	
	public List<InvoiceSearchVw> getInvoiceByStatus(String workgroup, String invoiceStatus);
	
	public List<InvoiceSearchVw> getInvoices(String woId, String vendorName, String lcecRef, String vendorRef, Integer invoiceStatus, String workgroup, String woType);
 
	public int updateInvoiceStatus(Integer invoiceId, Integer invoiceStatus, String invoiceType, String approvedBy, Timestamp approvedDt, String approvedComment);

	public void updateInvoiceApproval(Integer invoiceId, Integer invoiceStatusId, String invoiceAprrovedBy, Timestamp invoiceApprovedDt);
	
	public List<InvoiceGLSummaryVw> getInvoiceGLSummaryVw(Integer invoiceId);
	
	public List<InvoiceDetail> getInvoiceDetails(Integer invoiceId);
	
	public void updateStreetlightInvoiceApproval(String soId, Integer invoiceStatusId,
			String invoiceAprrovedBy, Timestamp invoiceApprovedDt, String invoiceApprovedComment);
	
	public void updateInvoiceVoucherApproval(Integer invoiceId, Integer invoiceStatusId, String invoiceAprrovedBy, Timestamp invoiceApprovedDt);
	
/*	public List<Invoice> getAllInvoices();

	public List<Invoice> getInvoiceByWoId(String paramString1, String paramString2) throws ValidationException, ProcessException, NoResultException;

	public Invoice getInvoiceById(Integer paramInteger) throws ValidationException, ProcessException, NoResultException;

	public List<InvoiceSearchVw> getAllSearchInvoices(String paramString) throws ProcessException;

	public List<InvoiceSearchVw> getSearchInvoiceById(String paramString1, String paramString2) throws ValidationException, ProcessException, NoResultException;

	public List<InvoiceSearchVw> getSearchInvoiceByWoId(String paramString1, String paramString2) throws ValidationException, ProcessException, NoResultException;

	public List<InvoiceSearchVw> getSearchInvoiceByVenRef(String paramString1, String paramString2) throws ValidationException, ProcessException, NoResultException;

	public List<String> getWoForInvoice(String paramString) throws NoResultException, ProcessException;

	public int updateSubmitInvoice(String paramString1, String paramString2, String paramString3, String paramString4, Timestamp paramTimestamp) throws ValidationException, ProcessException;

	public int updateWorkflowCalc(Integer paramInteger, Timestamp paramTimestamp, String paramString) throws ValidationException;

	public List<InvoiceStatus> getAllInvStatus();

	public List<InvoiceSearchVw> getInvoiceByStatus(String paramString1, String paramString2) throws ValidationException, ProcessException, NoResultException;

	public List<InvoiceSearchVw> getInvoices(String paramString1, String paramString2, String paramString3, String paramString4, Integer paramInteger, String paramString5, String paramString6) throws NoResultException, ProcessException;

	public int updateInvoiceStatus(Integer paramInteger1, Integer paramInteger2, String paramString1, String paramString2, Timestamp paramTimestamp, String paramString3) throws ValidationException;

	public void updateInvoiceApproval(Integer paramInteger1, Integer paramInteger2, String paramString, Timestamp paramTimestamp) throws ProcessException;

	

	public List<InvoiceGLSummaryVw> getInvoiceGLSummaryVw(Integer paramInteger) throws ProcessException, NoResultException;

	public List<InvoiceDetail> getInvoiceDetails(Integer paramInteger) throws ProcessException, NoResultException;

	public void updateStreetlightInvoiceApproval(String paramString1, Integer paramInteger, String paramString2, Timestamp paramTimestamp, String paramString3) throws ProcessException;*/

}
