package pwr.lcec.vendorportal.session;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pwr.lcec.vendorportal.entity.custom.RateGroupPrice;
import pwr.lcec.vendorportal.interfaces.RateLocal;

@Stateless(name = "VPRateSession", mappedName = "ejb/lcec/VPRateSession", description = "VP Rate Business Proxy")
@Local({ RateLocal.class })
@Interceptors({ pwr.lcec.vendorportal.helper.LoggingInterceptor.class })
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class RateSession implements RateLocal {
	
	private static Logger logger = LogManager.getLogger(RateSession.class);
	
	@PersistenceContext(unitName = "vendorPortalPU")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Timestamp> findDistinctStartDt() {
		
		List<Timestamp> result = null;
		
		Query query = em.createNamedQuery("RateGroupPrice.findDistinctStartDt");
		
		try {
			result = query.getResultList();
		} catch (Exception e) {
			logger.error("ProcessException: " + e.getMessage());
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Timestamp> findDistinctEndDt() {

		List<Timestamp> result = null;
		
		Query query = em.createNamedQuery("RateGroupPrice.findDistinctEndDt");
		
		try {
			result = query.getResultList();
		} catch (Exception e) {
			logger.error("ProcessException: " + e.getMessage());
		}
		
		return result;
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<RateYearRange> findDistinctYears() {
		
		List<RateYearRange> result = new ArrayList<RateYearRange>();

		Query query = em.createNamedQuery("RateGroupPrice.findDistinctYear");
		//query.setParameter("wfId", wfId);
		//query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		
		//Query query = em.createQuery("SELECT new RateYearRange(r.effectiveEndDt, r.effectiveStartDt) FROM RateGroupPrice r ");
		
		try {
			
			List<Object[]> found = query.getResultList();
			
			found.stream().forEach(o -> {
				
				RateYearRange y = new RateYearRange();
				y.setEffectiveStartDt((Timestamp)o[0]);
				y.setEffectiveEndDt((Timestamp)o[1]);
				result.add(y);
				
			});
			
		} catch (javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: Workflow Task ID "  + " is not found.");
			throw new NoResultException("Workflow Task ID "  + " is not found.");
		} catch (Exception ex) {
			logger.error("ProcessException: " + ex.getMessage());
			//throw new ProcessException(ex.getMessage());
		}
		return result;
	}*/

	@Override
	public List<RateGroupPrice> findRateGroupPrices(String searchRate, String searchMethod,  Timestamp startDt) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<RateGroupPrice> cq = cb.createQuery(RateGroupPrice.class);
		
		Root<RateGroupPrice> root = cq.from(RateGroupPrice.class);
		List<Predicate> p = new ArrayList<Predicate>();
		
		if (StringUtils.isNotEmpty(searchRate)) {
			logger.debug("SearchRate not empty");
			if("equals".equals(searchMethod)){
				p.add(cb.equal(root.get("assemblyGuid"), searchRate));
				logger.debug("equals");
			}else if("contains".equals(searchMethod)){
				p.add(cb.like(root.<String>get("assemblyGuid"), '%'+searchRate+'%'));
				logger.debug("contains");
			}else if("starts".equals(searchMethod)){
				p.add(cb.like(root.<String>get("assemblyGuid"), searchRate+'%'));
				logger.debug("starts");
			}else if("ends".equals(searchMethod)){
				p.add(cb.like(root.<String>get("assemblyGuid"), '%'+searchRate));
				logger.debug("ends");
			}
	    }
		
		if(startDt != null) {
			logger.debug("StartDt is not null: " + startDt.toString());
			//p.add(cb.equal(root.get("effectiveStartDt"), startDt.toString()));
			
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(startDt);
			
			p.add(cb.equal(root.get("effectiveStartDt"), cb.literal(new Date(cal1.getTime().getTime()))));
		}
		/*if(endDt != null) {
			logger.debug("EndDt is not null: " + endDt.toString());
			logger.debug("Formatted Timestamp:    " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(endDt));
			
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(endDt);
			logger.debug("EndDt Converted to date: " + new Date(cal2.getTime().getTime()));
			p.add(cb.equal(root.get("effectiveEndDt"), cb.literal(endDt)));
			
		}*/
		
		logger.debug("Predicate size: " + p.size());
		
		
		
		
		cq.select(root).where(cb.and((Predicate[])p.toArray(new Predicate[p.size()])));
	    
	    //cq.select(root);
	    
	    TypedQuery<RateGroupPrice> typedQuery = em.createQuery(cq);
	    typedQuery.setHint("javax.persistence.cache.storeMode", "REFRESH");
	    typedQuery.setMaxResults(500);
	    
	    List<RateGroupPrice> result = null;
	    try {
	      result = typedQuery.getResultList();
	      logger.debug("Size of return list: " + result.size());
	    } catch (Exception ex) {
	      logger.error(ex.getMessage());
	    } 
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public RateGroupPrice findEndDtFromStartDt(Timestamp startDt) {
logger.debug("Start Date: " + startDt);
		List<RateGroupPrice> result = null;
		
		Query query = em.createNamedQuery("RateGroupPrice.findEndFromStart");
		query.setParameter("effectiveStartDt", startDt);
		query.setMaxResults(1);
		
		try {
			result = query.getResultList();
			logger.debug("result size: " + result.size());
			if(result.size() == 1) {
				logger.debug("Start Date: " + result.get(0).getEffectiveEndDt());
				return result.get(0);
			}else {
				return null;
			}
			
		} catch (Exception e) {
			logger.error("ProcessException: " + e.getMessage());
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean doesAuAlreadyExist(String au, int rateGroupId, Timestamp startDt) {

		List<RateGroupPrice> result = null;
		
		Query query = em.createNamedQuery("RateGroupPrice.findAuBeforeSave");
		query.setParameter("assemblyGuid", au);
		query.setParameter("rateGroupId", rateGroupId);
		query.setParameter("effectiveStartDt", startDt);
		
		try {
			result = query.getResultList();
			
			if(result.size() >= 1) {
				return true;
			}else if (result == null || result.isEmpty()) {
				return false;
			}
			
		} catch (Exception e) {
			logger.error("ProcessException: " + e.getMessage());
		}
		
		return false;
	}

	@Override
	public boolean createAssemblyUnit(RateGroupPrice rpg) {
		
		try {
			em.persist(rpg);
			return true;
		} catch (Exception e) {
			logger.error("Error occured when adding new RateGroupPrice record: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateAssemblyUnit(RateGroupPrice rpg) {
		
		try {
			em.merge(rpg);
			return true;
		} catch (Exception e) {
			logger.error("Error occured when updating RateGroupPrice record: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteAssemblyUnit(RateGroupPrice rpg) {
		
		try {
			rpg = em.find(RateGroupPrice.class, rpg.getRateGroupPriceId());
			
			em.remove(rpg);
			return true;
		} catch (Exception e) {
			logger.error("Error occured when deleting RateGroupPrice record: " + e.getMessage());
			return false;
		}
	}

	

}
