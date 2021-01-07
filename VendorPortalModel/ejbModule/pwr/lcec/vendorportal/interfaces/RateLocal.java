package pwr.lcec.vendorportal.interfaces;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Local;

import pwr.lcec.vendorportal.entity.custom.RateGroupPrice;

@Local
public interface RateLocal {
	
	public List<Timestamp> findDistinctStartDt();
	
	public List<Timestamp> findDistinctEndDt();
	
	public List<RateGroupPrice> findRateGroupPrices(String searchRate, String searchMethod, Timestamp startDt);
	
	public RateGroupPrice findEndDtFromStartDt(Timestamp startDt);
	
	public boolean doesAuAlreadyExist(String au, int rateGroupId, Timestamp startDt);
	
	public boolean createAssemblyUnit(RateGroupPrice rpg);
	
	public boolean updateAssemblyUnit(RateGroupPrice rpg);
	
	public boolean deleteAssemblyUnit(RateGroupPrice rpg);

}
