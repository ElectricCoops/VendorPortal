package pwr.lcec.vendorportal.interfaces;

import javax.ejb.Remote;

@Remote
public interface AddWorkOrderRemote {
	
	public void updateWMISWR(String workRequest) throws Exception;

}
