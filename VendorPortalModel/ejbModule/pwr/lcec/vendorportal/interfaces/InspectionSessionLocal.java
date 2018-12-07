package pwr.lcec.vendorportal.interfaces;

import java.util.List;

import javax.ejb.Local;

import pwr.lcec.vendorportal.custom.entity.Inspection;
import pwr.lcec.vendorportal.custom.entity.InspectionDetailVw;
import pwr.lcec.vendorportal.custom.entity.InspectionStatus;
import pwr.lcec.vendorportal.exception.NoResultException;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;

@Local
public interface InspectionSessionLocal {
	public List<Inspection> getInspections();

	public List<Inspection> getInspectionByWoId(String woId)
			throws ValidationException, NoResultException, ProcessException;

	public List<Inspection> getInspectionByStatus(String status)
			throws ValidationException, NoResultException, ProcessException;

	public List<Inspection> getInspectionByWoIdAndStatus(String woId, String status)
			throws ValidationException, NoResultException, ProcessException;

	public List<InspectionStatus> getInspetionStatus();

	public Integer insertInspection(Inspection inspection) throws ValidationException, ProcessException;

	public List<InspectionDetailVw> getInspectionDetailByInspId(Integer inspectionId)
			throws ValidationException, NoResultException, ProcessException;
}
