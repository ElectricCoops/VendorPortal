package pwr.lcec.vendorportal.interfaces;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Remote;

import pwr.lcec.vendorportal.custom.entity.StreetLightSearchVw;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;

@Remote
public interface StreetLightServiceRemote {

	public List<StreetLightSearchVw> getServiceOrders(String woId, String soId, String vendorName, Timestamp startDt, Timestamp endDt, Integer invoiceStatusId, Integer inspectionStatusId, String workgroup, String iVueStat) throws ProcessException;
	
	public List<StreetLightSearchVw> getServiceOrderSummary(List<String> soId, String wrkgrp) throws ProcessException;
	
	public List<StreetLightSearchVw> getServiceOrderForInvoice(String wrkgrp, String invoiceStatus) throws ProcessException;
	
	public void updateServiceOrder(String invSubmitGuid, String soId, Integer invoiceStatusId) throws ValidationException, ProcessException;
	
	public List<StreetLightSearchVw> getServiceOrderByInvoiceId(Integer invId)throws ValidationException, ProcessException;
	
	public void updateServiceOrderInspection(Integer inspectionId, String soId, Integer inspectionStatusId, String inspectionComment, Timestamp inspectedDt, String inspectedBy) throws ValidationException, ProcessException;
	
	public void updateServiceOrderComment(String comment, String soId) throws ValidationException, ProcessException;
}
