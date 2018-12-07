package pwr.lcec.vendorportal.interfaces;

import javax.ejb.Remote;

import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.sec.entity.StakingSearchCol;

@Remote
public interface ColumnPickerServiceRemote {
	
	public StakingSearchCol getStakingColByUser(String username) throws ProcessException;
	
	public void updateStakingSearchCol(StakingSearchCol stakingSearchCol)  throws ProcessException;
}
