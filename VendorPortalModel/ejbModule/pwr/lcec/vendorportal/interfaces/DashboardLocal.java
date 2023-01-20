package pwr.lcec.vendorportal.interfaces;

import java.util.List;

import javax.ejb.Local;

import pwr.lcec.vendorportal.entity.ag.AGInspection;
import pwr.lcec.vendorportal.entity.ag.AGInvoice;
import pwr.lcec.vendorportal.entity.ag.AGStakingSheet;
import pwr.lcec.vendorportal.entity.ag.AGStreetlight;

@Local
public interface DashboardLocal {
	
	public List<AGInspection> findAllAGInspection();

	public List<AGInvoice> findAllAGInvoice();

	public List<AGStakingSheet> findAllAGStakingSheet();

	public List<AGStreetlight> findAllAGStreetlight();

	public void runAggregateSync();

}
