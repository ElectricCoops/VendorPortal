package pwr.lcec.vendorportal.interfaces;

import javax.ejb.Remote;

import pwr.lcec.vendorportal.exception.ProcessException;

@Remote
public interface IvueSessionRemote {

	public void updateWorkflowTask(String workEventCd, Integer wfTaskKey) throws ProcessException;
}
