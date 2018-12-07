package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the StakingSheet database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "StakingSheet.findAll", query = "SELECT s FROM StakingSheet s"),
		@NamedQuery(name = "StakingSheet.findByWoId", query = "SELECT s FROM StakingSheet s WHERE s.workOrderId = :woId"),
		@NamedQuery(name = "StakingSheet.findStakingIdByWoId", query = "SELECT s.stakingSheetId FROM StakingSheet s WHERE s.workOrderId = :woId")})
public class StakingSheet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "StakingSheetId")
	private String stakingSheetId;

	@Column(name = "ServiceOrderId")
	private String serviceOrderId;

	@Column(name = "StServiceAddress")
	private String stServiceAddress;

	@Column(name = "StServiceAddressCity")
	private String stServiceAddressCity;

	@Column(name = "StServiceAddressPhone")
	private String stServiceAddressPhone;

	@Column(name = "StServiceName")
	private String stServiceName;

	@Column(name = "WorkFlowId")
	private int workFlowId;

	@Column(name = "WorkOrderComments")
	private String workOrderComments;

	@Column(name = "WorkOrderDescription")
	private String workOrderDescription;

	@Column(name = "WorkOrderId")
	private String workOrderId;

	@Column(name = "WorkOrderTypeCode")
	private String workOrderTypeCode;

	public StakingSheet() {
	}

	public String getStakingSheetId() {
		return this.stakingSheetId;
	}

	public void setStakingSheetId(String stakingSheetId) {
		this.stakingSheetId = stakingSheetId;
	}

	public String getServiceOrderId() {
		return this.serviceOrderId;
	}

	public void setServiceOrderId(String serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}

	public String getStServiceAddress() {
		return this.stServiceAddress;
	}

	public void setStServiceAddress(String stServiceAddress) {
		this.stServiceAddress = stServiceAddress;
	}

	public String getStServiceAddressCity() {
		return this.stServiceAddressCity;
	}

	public void setStServiceAddressCity(String stServiceAddressCity) {
		this.stServiceAddressCity = stServiceAddressCity;
	}

	public String getStServiceAddressPhone() {
		return this.stServiceAddressPhone;
	}

	public void setStServiceAddressPhone(String stServiceAddressPhone) {
		this.stServiceAddressPhone = stServiceAddressPhone;
	}

	public String getStServiceName() {
		return this.stServiceName;
	}

	public void setStServiceName(String stServiceName) {
		this.stServiceName = stServiceName;
	}

	public int getWorkFlowId() {
		return this.workFlowId;
	}

	public void setWorkFlowId(int workFlowId) {
		this.workFlowId = workFlowId;
	}

	public String getWorkOrderComments() {
		return this.workOrderComments;
	}

	public void setWorkOrderComments(String workOrderComments) {
		this.workOrderComments = workOrderComments;
	}

	public String getWorkOrderDescription() {
		return this.workOrderDescription;
	}

	public void setWorkOrderDescription(String workOrderDescription) {
		this.workOrderDescription = workOrderDescription;
	}

	public String getWorkOrderId() {
		return this.workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

	public String getWorkOrderTypeCode() {
		return this.workOrderTypeCode;
	}

	public void setWorkOrderTypeCode(String workOrderTypeCode) {
		this.workOrderTypeCode = workOrderTypeCode;
	}

}