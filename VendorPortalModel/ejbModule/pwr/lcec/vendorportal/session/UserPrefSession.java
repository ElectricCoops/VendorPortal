package pwr.lcec.vendorportal.session;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pwr.lcec.vendorportal.entity.userpref.UserPrefInspection;
import pwr.lcec.vendorportal.entity.userpref.UserPrefInspectionSearch;
import pwr.lcec.vendorportal.entity.userpref.UserPrefInvoice;
import pwr.lcec.vendorportal.entity.userpref.UserPrefInvoiceGLSummaryVw;
import pwr.lcec.vendorportal.entity.userpref.UserPrefInvoiceSearch;
import pwr.lcec.vendorportal.entity.userpref.UserPrefStakingSheetDetail;
import pwr.lcec.vendorportal.entity.userpref.UserPrefVoucher;
import pwr.lcec.vendorportal.entity.userpref.UserPrefWorkFlowSearch;
import pwr.lcec.vendorportal.interfaces.UserPrefLocal;

@Stateless(name = "VPUserPrefService", mappedName = "ejb/lcec/VPUserPrefService", description = "VP UserPrefService Business Proxy")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserPrefSession implements UserPrefLocal {
	
	private static Logger logger = LogManager.getLogger(UserPrefSession.class);

	@PersistenceContext(unitName = "vendorPortalPU")
	private EntityManager em;

	@Override
	public UserPrefWorkFlowSearch getWFSearchPrefByUserId(Integer userId) {

		UserPrefWorkFlowSearch result = null;

		Query query = em.createNamedQuery("UserPrefWorkFlowSearch.findAllWFSearchPrefByUserId");
		query.setParameter("userId", userId);
		try {
			result = (UserPrefWorkFlowSearch) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.error(ex);
			return null;
		}
		return result;
	}

	@Override
	public UserPrefWorkFlowSearch updateWFSearchPref(UserPrefWorkFlowSearch userPrefWorkFlowSearch) {

		UserPrefWorkFlowSearch result = null;

		try {
			result = em.merge(userPrefWorkFlowSearch);
		} catch (EJBException ex) {
			logger.error(ex);
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@Override
	public UserPrefInspectionSearch getInspSearchPrefByUserId(Integer userId) {

		UserPrefInspectionSearch result = null;

		Query query = em.createNamedQuery("UserPrefInspectionSearch.findInspSearchPrefByUserId");
		query.setParameter("userId", userId);

		try {
			result = (UserPrefInspectionSearch) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.error(ex);
			return null;
		}
		return result;
	}

	@Override
	public UserPrefInspectionSearch updateInspSearchPref(UserPrefInspectionSearch userPrefInspectionSearch) {

		UserPrefInspectionSearch result = null;

		try {
			result = em.merge(userPrefInspectionSearch);
		} catch (EJBException ex) {
			logger.error(ex);
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@Override
	public UserPrefInvoiceSearch getInvSearchPrefByUserId(Integer userId) {

		UserPrefInvoiceSearch result = null;

		Query query = em.createNamedQuery("UserPrefInvoiceSearch.findUserPrefInvSearchByUserId");
		query.setParameter("userId", userId);

		try {
			result = (UserPrefInvoiceSearch) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.error(ex);
			return null;
		}
		return result;
	}

	@Override
	public UserPrefInvoiceSearch updateInvSearchPref(UserPrefInvoiceSearch userPrefInvoiceSearch) {

		UserPrefInvoiceSearch result = null;

		try {
			result = em.merge(userPrefInvoiceSearch);
		} catch (EJBException ex) {
			logger.error(ex);
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@Override
	public UserPrefStakingSheetDetail getStakingSheetDetailPrefByUserId(Integer userId, String viewName) {

		UserPrefStakingSheetDetail result = null;

		Query query = em.createNamedQuery("UserPrefStakingSheetDetail.findUserPrefStakingDetByUserId");
		query.setParameter("userId", userId);
		query.setParameter("viewName", viewName);

		try {
			result = (UserPrefStakingSheetDetail) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.error(ex);
			return null;
		}
		return result;
	}

	@Override
	public UserPrefStakingSheetDetail updateStakingSheetDetailPref(
			UserPrefStakingSheetDetail userPrefStakingSheetDetail) {

		UserPrefStakingSheetDetail result = null;

		try {
			result = em.merge(userPrefStakingSheetDetail);
		} catch (EJBException ex) {
			logger.error(ex);
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@Override
	public UserPrefInvoiceGLSummaryVw getInvoiceGLSummaryPrefByUserId(Integer userId) {

		UserPrefInvoiceGLSummaryVw result = null;

		Query query = em.createNamedQuery("UserPrefInvoiceGLSummaryVw.findInvDetPrefByUserId");
		query.setParameter("userId", userId);

		try {
			result = (UserPrefInvoiceGLSummaryVw) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.error(ex);
			return null;
		}
		return result;
	}

	@Override
	public UserPrefInvoiceGLSummaryVw updateInvoiceGLSummaryPref(
			UserPrefInvoiceGLSummaryVw userPrefInvoiceGLSummaryVw) {

		UserPrefInvoiceGLSummaryVw result = null;

		try {
			result = em.merge(userPrefInvoiceGLSummaryVw);
		} catch (EJBException ex) {
			logger.error(ex);
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@Override
	public UserPrefVoucher getVoucherPrefByUserId(Integer userId, String viewName) {

		UserPrefVoucher result = null;

		Query query = em.createNamedQuery("UserPrefVoucher.findVoucherPrefByUserId");
		query.setParameter("userId", userId);
		query.setParameter("viewName", viewName);

		try {
			result = (UserPrefVoucher) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.error(ex);
			return null;
		}
		return result;
	}

	@Override
	public UserPrefVoucher updateVoucherPref(UserPrefVoucher userPrefVoucher) {

		UserPrefVoucher result = null;

		try {
			result = em.merge(userPrefVoucher);
		} catch (EJBException ex) {
			logger.error(ex);
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@Override
	public UserPrefInvoice getInvoicePrefByUserId(Integer userId, String viewName) {

		UserPrefInvoice result = null;

		Query query = em.createNamedQuery("UserPrefInvoice.findInvPrefByUserId");
		query.setParameter("userId", userId);
		query.setParameter("viewName", viewName);

		try {
			result = (UserPrefInvoice) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.error(ex);
			return null;
		}
		return result;
	}

	@Override
	public UserPrefInvoice updateInvoicePref(UserPrefInvoice userPrefInvoice) {
		
		UserPrefInvoice result = null;

		try {
			result = em.merge(userPrefInvoice);
		} catch (EJBException ex) {
			logger.error(ex);
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	@Override
	public UserPrefInspection getInspectionPrefByUserId(Integer userId, String viewName) {

		UserPrefInspection result = null;

		Query query = em.createNamedQuery("UserPrefInspection.findInspPrefByUserId");
		query.setParameter("userId", userId);
		query.setParameter("viewName", viewName);

		try {
			result = (UserPrefInspection) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.error(ex);
			return null;
		}
		return result;
	}

	@Override
	public UserPrefInspection updateInspectionPref(UserPrefInspection userPrefInspection) {
		UserPrefInspection result = null;

		try {
			result = em.merge(userPrefInspection);
		} catch (EJBException ex) {
			logger.error(ex);
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	
}
