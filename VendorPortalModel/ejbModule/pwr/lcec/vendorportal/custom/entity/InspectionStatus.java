package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the InspectionStatus database table.
 * 
 */
@Entity
@Table(name = "InspectionStatus")
@NamedQuery(name="InspectionStatus.findAll", query="SELECT i FROM InspectionStatus i")
public class InspectionStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="InspectionStatusId")
	private Integer inspectionStatusId;

	@Column(name="Active")
	private boolean active;

	@Column(name="Description")
	private String description;

	@Column(name="Status")
	private String status;
	
	@Column(name="HeaderDescription")
	private String headerDescription;
	
	@Column(name="HeaderFlg")
	private boolean headerFlg;

/*	//bi-directional many-to-one association to Inspection
	@OneToMany(mappedBy="inspectionStatus")
	private List<Inspection> inspections;*/

/*	//bi-directional many-to-one association to InspectionDetail
	@OneToMany(mappedBy="inspectionStatus")
	private List<InspectionDetail> inspectionDetails;
*/
	public InspectionStatus() {
	}

	public Integer getInspectionStatusId() {
		return this.inspectionStatusId;
	}

	public void setInspectionStatusId(Integer inspectionStatusId) {
		this.inspectionStatusId = inspectionStatusId;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHeaderDescription() {
		return headerDescription;
	}

	public void setHeaderDescription(String headerDescription) {
		this.headerDescription = headerDescription;
	}

	public boolean getHeaderFlg() {
		return headerFlg;
	}

	public void setHeaderFlg(boolean headerFlg) {
		this.headerFlg = headerFlg;
	}

/*	public List<Inspection> getInspections() {
		return this.inspections;
	}

	public void setInspections(List<Inspection> inspections) {
		this.inspections = inspections;
	}

	public Inspection addInspection(Inspection inspection) {
		getInspections().add(inspection);
		inspection.setInspectionStatus(this);

		return inspection;
	}

	public Inspection removeInspection(Inspection inspection) {
		getInspections().remove(inspection);
		inspection.setInspectionStatus(null);

		return inspection;
	}*/

/*	public List<InspectionDetail> getInspectionDetails() {
		return this.inspectionDetails;
	}

	public void setInspectionDetails(List<InspectionDetail> inspectionDetails) {
		this.inspectionDetails = inspectionDetails;
	}*/

	/*public InspectionDetail addInspectionDetail(InspectionDetail inspectionDetail) {
		getInspectionDetails().add(inspectionDetail);
		inspectionDetail.setInspectionStatus(this);

		return inspectionDetail;
	}

	public InspectionDetail removeInspectionDetail(InspectionDetail inspectionDetail) {
		getInspectionDetails().remove(inspectionDetail);
		inspectionDetail.setInspectionStatus(null);

		return inspectionDetail;
	}*/

}