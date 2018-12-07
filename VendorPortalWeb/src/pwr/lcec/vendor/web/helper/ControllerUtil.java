package pwr.lcec.vendor.web.helper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import pwr.lcec.vendor.controller.InvoiceController;
import pwr.lcec.vendor.controller.UserManagedBean;
import pwr.lcec.vendor.controller.WorkflowController;
import pwr.lcec.vendorportal.exception.NoResultException;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;

public class ControllerUtil {
	
	private static Logger logger = Logger.getLogger(ControllerUtil.class);

	private final String MBEAN = "#{userMBean}";
	private final String WFCONTROLLER = "#{wfController}";
	private final String INVCONTROLLER = "#{invController}";

	public String getWrkGrp() {

		FacesContext context = FacesContext.getCurrentInstance();
		UserManagedBean umbean = context.getApplication().evaluateExpressionGet(context, MBEAN, UserManagedBean.class);

		String workgroup = umbean.getVendor();

		return workgroup;
	}

	public String getWoId() {

		FacesContext context = FacesContext.getCurrentInstance();
		WorkflowController umbean = context.getApplication().evaluateExpressionGet(context, WFCONTROLLER,
				WorkflowController.class);

		return umbean.getWoId();
	}

	public String genGUID() {

		UUID uuid = UUID.randomUUID();
		String randomUUIDString = uuid.toString().toUpperCase();

		return randomUUIDString;
	}

	public Timestamp currentDtTm() {
		Timestamp now = new Timestamp(System.currentTimeMillis());

		return now;
	}
	
	public Timestamp convertDtTm(Date date) {
		
		Timestamp timestamp = (date == null ? null : new Timestamp(date.getTime()));
		
		return timestamp;	
	}

	public String getCurrentUser() {
		final Subject currentUser = SecurityUtils.getSubject();

		return currentUser.getPrincipal().toString();
	}
	
	public Subject subject() {
		final Subject currentUser = SecurityUtils.getSubject();
		
		return currentUser;
	}

	public String refreshInvoiceSearch() throws NoResultException, ProcessException {
		FacesContext context = FacesContext.getCurrentInstance();
		InvoiceController inv = context.getApplication().evaluateExpressionGet(context, INVCONTROLLER, InvoiceController.class);
		inv.findInvoices();
		
		String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		return viewId + "?faces-redirect=true";
	}
	
	public void refreshInvoiceDetails(Integer invoiceId) throws NoResultException, ProcessException, ValidationException {
		FacesContext context = FacesContext.getCurrentInstance();
		WorkflowController workflowController = context.getApplication().evaluateExpressionGet(context, WFCONTROLLER, WorkflowController.class);
		workflowController.findInvoiceDetail(invoiceId);
		
		/*String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		return viewId + "?faces-redirect=true";*/
	}
	
	public String refreshVouchersTab() {
		FacesContext context = FacesContext.getCurrentInstance();
		WorkflowController umbean = context.getApplication().evaluateExpressionGet(context, WFCONTROLLER,
				WorkflowController.class);

		umbean.findStakingDetailByWoId();
		
		String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		return viewId + "?faces-redirect=true";
	}
	
	public String getClientIp(HttpServletRequest request) {

		String remoteAddr = "";

		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}
		return remoteAddr;
	}
	
	/**
	 * Review the client’s HTTP request header, and identify where the IP address is stored.
	 * @param request
	 * @return
	 */
	@SuppressWarnings({"rawtypes" })
	public Map<String, String> getRequestHeadersInMap(HttpServletRequest request) {
		Map<String, String> result = new HashMap<>();

		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			result.put(key, value);
		}
		logger.info(result);
		return result;
	}
}
