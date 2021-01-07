package pwr.lcec.vendorportal.interfaces;

import javax.ejb.Local;

@Local
public interface IvueLocal {

	public void updateWorkflowTask(String workEventCd, Integer wfTaskKey);
	
}
