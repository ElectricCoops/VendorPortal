package pwr.lcec.vendorportal.entity.userpref;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
		@NamedQuery(name = "UserPrefInspectionSearch.findAll", query = "SELECT u FROM UserPrefInspectionSearch u"),
		@NamedQuery(name = "UserPrefInspectionSearch.findInspSearchPrefByUserId", query = "SELECT u FROM UserPrefInspectionSearch u WHERE u.userId = :userId")
})
@Table(schema="sec")
public class UserPrefInspectionSearch implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserPrefId")
	private int userPrefId;
	
	@Column(name = "Comments")
	private boolean comments;
	
	@Column(name = "InspectedBy")
	private boolean inspectedBy;
	
	@Column(name = "InspectionDt")
	private boolean inspectionDt;
	
	@Column(name = "InspectionId")
	private boolean inspectionId;
	
	@Column(name = "InspectionStatusId")
	private boolean inspectionStatusId;
	
	@Column(name = "InspectionType")
	private boolean inspectionType;
	
	@Column(name = "ServiceOrderId")
	private boolean serviceOrderId;
	
	@Column(name = "UserId")
	private int userId;
	
	@Column(name = "ViewDefault")
	private boolean viewDefault;
	
	@Column(name = "ViewName")
	private String viewName;
	
	@Column(name = "WorkFlowId")
	private boolean workFlowId;
	
	@Column(name = "WorkOrderId")
	private boolean workOrderId;
	
	@Column(name = "Workgroup")
	private boolean workgroup;
	
	public UserPrefInspectionSearch() {
	}

	public UserPrefInspectionSearch(boolean comments, boolean inspectedBy, boolean inspectionDt, boolean inspectionId,
			boolean inspectionStatusId, boolean inspectionType, boolean serviceOrderId, int userId, boolean viewDefault,
			String viewName, boolean workFlowId, boolean workOrderId, boolean workgroup) {
		this.comments = comments;
		this.inspectedBy = inspectedBy;
		this.inspectionDt = inspectionDt;
		this.inspectionId = inspectionId;
		this.inspectionStatusId = inspectionStatusId;
		this.inspectionType = inspectionType;
		this.serviceOrderId = serviceOrderId;
		this.userId = userId;
		this.viewDefault = viewDefault;
		this.viewName = viewName;
		this.workFlowId = workFlowId;
		this.workOrderId = workOrderId;
		this.workgroup = workgroup;
	}

	public int getUserPrefId() {
		return this.userPrefId;
	}

	public void setUserPrefId(int userPrefId) {
		this.userPrefId = userPrefId;
	}

	public boolean getComments() {
		return this.comments;
	}

	public void setComments(boolean comments) {
		this.comments = comments;
	}

	public boolean getInspectedBy() {
		return this.inspectedBy;
	}

	public void setInspectedBy(boolean inspectedBy) {
		this.inspectedBy = inspectedBy;
	}

	public boolean getInspectionDt() {
		return this.inspectionDt;
	}

	public void setInspectionDt(boolean inspectionDt) {
		this.inspectionDt = inspectionDt;
	}

	public boolean getInspectionId() {
		return this.inspectionId;
	}

	public void setInspectionId(boolean inspectionId) {
		this.inspectionId = inspectionId;
	}

	public boolean getInspectionStatusId() {
		return this.inspectionStatusId;
	}

	public void setInspectionStatusId(boolean inspectionStatusId) {
		this.inspectionStatusId = inspectionStatusId;
	}

	public boolean getInspectionType() {
		return this.inspectionType;
	}

	public void setInspectionType(boolean inspectionType) {
		this.inspectionType = inspectionType;
	}

	public boolean getServiceOrderId() {
		return this.serviceOrderId;
	}

	public void setServiceOrderId(boolean serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean getViewDefault() {
		return this.viewDefault;
	}

	public void setViewDefault(boolean viewDefault) {
		this.viewDefault = viewDefault;
	}

	public String getViewName() {
		return this.viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public boolean getWorkFlowId() {
		return this.workFlowId;
	}

	public void setWorkFlowId(boolean workFlowId) {
		this.workFlowId = workFlowId;
	}

	public boolean getWorkOrderId() {
		return this.workOrderId;
	}

	public void setWorkOrderId(boolean workOrderId) {
		this.workOrderId = workOrderId;
	}

	public boolean isWorkgroup() {
		return this.workgroup;
	}

	public void setWorkgroup(boolean workgroup) {
		this.workgroup = workgroup;
	}

	public String toString() {
		return "UserPrefInspectionSearch [userPrefId=" + this.userPrefId + ", comments=" + this.comments
				+ ", inspectedBy=" + this.inspectedBy + ", inspectionDt=" + this.inspectionDt + ", inspectionId="
				+ this.inspectionId + ", inspectionStatusId=" + this.inspectionStatusId + ", inspectionType="
				+ this.inspectionType + ", serviceOrderId=" + this.serviceOrderId + ", userId=" + this.userId
				+ ", viewDefault=" + this.viewDefault + ", viewName=" + this.viewName + ", workFlowId="
				+ this.workFlowId + ", workOrderId=" + this.workOrderId + ", workgroup=" + this.workgroup + "]";
	}
}
