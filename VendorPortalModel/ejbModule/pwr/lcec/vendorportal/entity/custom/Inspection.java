package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the Inspection database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Inspection.findAll", query = "SELECT i FROM Inspection i ORDER BY i.inspectionDt DESC"),
	@NamedQuery(name = "Inspection.findInspectionByWoId", query = "SELECT i FROM Inspection i WHERE i.workOrderId = :woId ORDER BY i.inspectionDt DESC"),
	@NamedQuery(name = "Inspection.findInspectionByStatus", query = "SELECT i FROM Inspection i WHERE i.inspectionStatus.status = :woStat"),
	@NamedQuery(name = "Inspection.findInspectionById", query = "SELECT i FROM Inspection i WHERE i.inspectionId = :inspectionId"),
	@NamedQuery(name = "Inspection.findInspectionByWoIdAndStatus", query = "SELECT i FROM Inspection i WHERE i.workOrderId = :woId AND i.inspectionStatus.status = :woStat"),
	@NamedQuery(name = "Inspection.findInspectionStatus", query = "SELECT i FROM Inspection i WHERE i.inspectionId = :inspectionId") })
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "Inspection.NEW_INSPECTION", procedureName = "NEW_INSPECTION", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, type = java.sql.Date.class, name = "IN_InspectionDT"),
			@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "IN_InspectedBy"),
			@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "IN_InspectionStatusID"),
			@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_COMMENTS"),
			@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_WORKORDER_ID"),
			@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_InspectionType"),
			@StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "OUT_Inspection_ID") }),
	@NamedStoredProcedureQuery(name = "Inspection.DELETE_NULL_INSPECTIONS", procedureName = "DELETE_NULL_INSPECTIONS", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_WorkOrderId"),
			@StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "OUT_Result") }) })
public class Inspection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="InspectionId")
	private Integer inspectionId;

	@Column(name="Comments")
	private String comments;

	@Column(name="InspectedBy")
	private int inspectedBy;

	@Column(name="InspectionDt")
	private Timestamp inspectionDt;

	@Column(name="InspectionStatusId")
	private int inspectionStatusId;

	@Column(name="InspectionType")
	private String inspectionType;

	@Column(name="ServiceOrderId")
	private String serviceOrderId;

	@Column(name="WorkFlowId")
	private int workFlowId;

	@Column(name="WorkOrderId")
	private String workOrderId;
	
	@OneToOne
	@JoinColumn(name = "InspectionStatusId", insertable = false, updatable = false)
	private InspectionStatus inspectionStatus;
	
	@OneToOne
	@JoinColumn(name = "InspectedBy", insertable = false, updatable = false)
	private Resource resource;
	
	@OneToOne
	@JoinColumn(name = "WorkFlowId", insertable = false, updatable = false)
	private WorkFlow workFlow;

	public Inspection() {
	}

	public Integer getInspectionId() {
		return this.inspectionId;
	}

	public void setInspectionId(Integer inspectionId) {
		this.inspectionId = inspectionId;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getInspectedBy() {
		return this.inspectedBy;
	}

	public void setInspectedBy(int inspectedBy) {
		this.inspectedBy = inspectedBy;
	}

	public Timestamp getInspectionDt() {
		return this.inspectionDt;
	}

	public void setInspectionDt(Timestamp inspectionDt) {
		this.inspectionDt = inspectionDt;
	}

	public int getInspectionStatusId() {
		return this.inspectionStatusId;
	}

	public void setInspectionStatusId(int inspectionStatusId) {
		this.inspectionStatusId = inspectionStatusId;
	}

	public String getInspectionType() {
		return this.inspectionType;
	}

	public void setInspectionType(String inspectionType) {
		this.inspectionType = inspectionType;
	}

	public String getServiceOrderId() {
		return this.serviceOrderId;
	}

	public void setServiceOrderId(String serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}

	public int getWorkFlowId() {
		return this.workFlowId;
	}

	public void setWorkFlowId(int workFlowId) {
		this.workFlowId = workFlowId;
	}

	public String getWorkOrderId() {
		return this.workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

	public InspectionStatus getInspectionStatus() {
		return inspectionStatus;
	}

	public void setInspectionStatus(InspectionStatus inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public WorkFlow getWorkFlow() {
		return workFlow;
	}

	public void setWorkFlow(WorkFlow workFlow) {
		this.workFlow = workFlow;
	}

}