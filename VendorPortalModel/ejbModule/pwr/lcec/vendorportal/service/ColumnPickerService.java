package pwr.lcec.vendorportal.service;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.interfaces.ColumnPickerServiceRemote;
import pwr.lcec.vendorportal.sec.entity.StakingSearchCol;

@Stateless(name = "ColumnPickerService", mappedName = "ejb/lcec/ColumnPickerService", description = "Column Picker Business Proxy")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ColumnPickerService implements ColumnPickerServiceRemote {

	private static Logger logger = Logger.getLogger(ColumnPickerService.class);

	@PersistenceContext(unitName = "VPSECPU")
	private EntityManager em;

	public StakingSearchCol getStakingColByUser(String username) throws ProcessException {

		StakingSearchCol result = null;

		Query query = em.createNamedQuery("StakingSearchCol.findByUser");
		query.setParameter("username", username);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = (StakingSearchCol) query.getSingleResult();
		} catch (EJBException ex) {
			logger.error(ex);
			throw new ProcessException(ex.getMessage());
		}
		return result;
	}

	public void updateStakingSearchCol(StakingSearchCol stakingSearchCol) throws ProcessException {
		try {
			em.merge(stakingSearchCol);
		} catch (EJBException ex) {
			logger.error(ex);
			throw new ProcessException(ex.getMessage());
		}
	}

}
