package pwr.lcec.vendorportal.interfaces;

import java.util.List;

import javax.ejb.Remote;

import pwr.lcec.vendorportal.custom.entity.Inspection;
import pwr.lcec.vendorportal.custom.entity.InspectionDetailVw;
import pwr.lcec.vendorportal.custom.entity.InspectionStatus;
import pwr.lcec.vendorportal.exception.NoResultException;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;

@Remote
public interface InspectionSessionRemote {

	public List<Inspection> getAllInspections();
	
	public List<Inspection> getInspectionByWoId(String woId) throws ValidationException, NoResultException, ProcessException;
	
	public List<Inspection> getInspectionByStatus(String status) throws ValidationException, NoResultException, ProcessException;
	
	public List<Inspection> getInspectionByWoIdAndStatus(String woId, String status) throws ValidationException, NoResultException, ProcessException;
	
	public List<InspectionStatus> getInspetionStatus();

	public Integer insertInspection(Inspection inspection) throws ValidationException, ProcessException;
	
	public List<InspectionDetailVw> getInspectionDetailByInspId(Integer inspectionId) throws ValidationException, NoResultException, ProcessException;
	
	public int getResourceId(String resourceName) throws ValidationException, NoResultException, ProcessException;
	
	public void updateInspection(Integer inspectionId, Integer inspectionStatusId) throws ValidationException, ProcessException;
	
	public List<Inspection> getInspections(String woId, Integer inspectionStatus, String workgroup)  throws ValidationException, ProcessException;
	
	public Inspection getInspectionStatusId(Integer inspectionId)  throws ValidationException, ProcessException;
}
