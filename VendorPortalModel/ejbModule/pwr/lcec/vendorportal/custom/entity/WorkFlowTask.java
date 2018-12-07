package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;


/**
 * The persistent class for the WorkFlowTask database table.
 * 
 */
@Entity
@NamedQuery(name="WorkFlowTask.findWorkflowTaskById", query="SELECT w FROM WorkFlowTask w WHERE w.workFlowId = :wfId")
public class WorkFlowTask implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="WorkFlowTaskId")
	private int workFlowTaskId;

	private String WFCriticalTask;

	private Timestamp WFEventDt;

	private String WFTaskCode;

	private String WFTaskDescription;

	private int WFTasksCtr;

	@Column(name="WorkEventStatusId")
	private String workEventStatusId;

	@Column(name="WorkFlowId")
	private int workFlowId;

	@Column(name="WorkFlowTaskSeq")
	private String workFlowTaskSeq;

	@Column(name="WorkGroup")
	private String workGroup;

	@Column(name="WorkOrderId")
	private String workOrderId;
	
	@OneToOne
	@JoinColumn(name="WorkEventStatusId", insertable=false, updatable= false)
	private WorkEventStatus workEventStatus;

	public WorkFlowTask() {
	}

	public int getWorkFlowTaskId() {
		return this.workFlowTaskId;
	}

	public void setWorkFlowTaskId(int workFlowTaskId) {
		this.workFlowTaskId = workFlowTaskId;
	}

	public String getWFCriticalTask() {
		return this.WFCriticalTask;
	}

	public void setWFCriticalTask(String WFCriticalTask) {
		this.WFCriticalTask = WFCriticalTask;
	}

	public Timestamp getWFEventDt() {
		return this.WFEventDt;
	}

	public void setWFEventDt(Timestamp WFEventDt) {
		this.WFEventDt = WFEventDt;
	}

	public String getWFTaskCode() {
		return this.WFTaskCode;
	}

	public void setWFTaskCode(String WFTaskCode) {
		this.WFTaskCode = WFTaskCode;
	}

	public String getWFTaskDescription() {
		return this.WFTaskDescription;
	}

	public void setWFTaskDescription(String WFTaskDescription) {
		this.WFTaskDescription = WFTaskDescription;
	}

	public int getWFTasksCtr() {
		return this.WFTasksCtr;
	}

	public void setWFTasksCtr(int WFTasksCtr) {
		this.WFTasksCtr = WFTasksCtr;
	}

	public String getWorkEventStatusId() {
		return this.workEventStatusId;
	}

	public void setWorkEventStatusId(String workEventStatusId) {
		this.workEventStatusId = workEventStatusId;
	}

	public int getWorkFlowId() {
		return this.workFlowId;
	}

	public void setWorkFlowId(int workFlowId) {
		this.workFlowId = workFlowId;
	}

	public String getWorkFlowTaskSeq() {
		return this.workFlowTaskSeq;
	}

	public void setWorkFlowTaskSeq(String workFlowTaskSeq) {
		this.workFlowTaskSeq = workFlowTaskSeq;
	}

	public String getWorkGroup() {
		return this.workGroup;
	}

	public void setWorkGroup(String workGroup) {
		this.workGroup = workGroup;
	}

	public String getWorkOrderId() {
		return this.workOrderId;
	}

	public WorkEventStatus getWorkEventStatus() {
		return workEventStatus;
	}

	public void setWorkEventStatus(WorkEventStatus workEventStatus) {
		this.workEventStatus = workEventStatus;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

}