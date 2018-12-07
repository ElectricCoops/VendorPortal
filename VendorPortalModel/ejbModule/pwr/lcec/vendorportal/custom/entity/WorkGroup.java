package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the WorkGroup database table.
 * 
 */
@Entity
@NamedQuery(name="WorkGroup.findAll", query="SELECT w FROM WorkGroup w")
public class WorkGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="WorkGroupId")
	private Integer workGroupId;

	@Column(name="ListenSw")
	private String listenSw;

	@Column(name="ResourceId")
	private Integer resourceId;

	@Column(name="WorkGroupName")
	private String workGroupName;

	public WorkGroup() {
	}

	public Integer getWorkGroupId() {
		return this.workGroupId;
	}

	public void setWorkGroupId(Integer workGroupId) {
		this.workGroupId = workGroupId;
	}

	public String getListenSw() {
		return this.listenSw;
	}

	public void setListenSw(String listenSw) {
		this.listenSw = listenSw;
	}

	public Integer getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getWorkGroupName() {
		return this.workGroupName;
	}

	public void setWorkGroupName(String workGroupName) {
		this.workGroupName = workGroupName;
	}

}