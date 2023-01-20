package pwr.lcec.vendorportal.interfaces;

import java.util.List;

import javax.ejb.Local;

import pwr.lcec.vendorportal.entity.custom.Inspection;
import pwr.lcec.vendorportal.entity.custom.InspectionDetailVw;
import pwr.lcec.vendorportal.entity.custom.InspectionStatus;

@Local
public interface InspectionLocal {
	
	public List<Inspection> getAllInspections();

	public List<Inspection> getInspectionByWoId(String workOrderId);

	public List<Inspection> getInspectionByStatus(String inspectionStatusId);

	public List<Inspection> getInspectionByWoIdAndStatus(String workOrderId, String inspectionStatusId);

	public List<InspectionStatus> getInspectionStatus();

	public Integer insertInspection(Inspection inspection);

	public List<InspectionDetailVw> getInspectionDetailByInspId(Integer inspectionId);

	public int getResourceId(String resourceName);

	public void updateInspection(Integer inspectionId, Integer inspectionStatusId);

	public List<Inspection> getInspections(String workOrderId, Integer inspectionStatusId, String workgroup);

	public Inspection getInspectionStatusId(Integer inspectionId);

	public int removeNullInspections(String workOrderId);

	public Inspection getInspectionByInspectionId(int inspectionId);

	public void removeInspectionDetail(Integer inspectionId);

	public void removeInspection(Integer inspectionId);

	public boolean removeInspectionDetailById(int inspectionDetailId);

}
