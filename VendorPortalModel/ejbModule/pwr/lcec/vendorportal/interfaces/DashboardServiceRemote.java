package pwr.lcec.vendorportal.interfaces;

import java.util.List;

import javax.ejb.Remote;

import pwr.lcec.vendorportal.ag.entity.AGInspection;
import pwr.lcec.vendorportal.ag.entity.AGInspector;
import pwr.lcec.vendorportal.ag.entity.AGInvoice;
import pwr.lcec.vendorportal.ag.entity.AGStakingSheet;
import pwr.lcec.vendorportal.ag.entity.AGStreetlight;

@Remote
public interface DashboardServiceRemote {

	public List<AGInspection> findAllAGInspection();

	public List<AGInspector> findAllAGInspector();

	public List<AGInvoice> findAllAGInvoice();

	public List<AGStakingSheet> findAllAGStakingSheet();

	public List<AGStreetlight> findAllAGStreetlight();
	
	public void runAggregateSync();

}
