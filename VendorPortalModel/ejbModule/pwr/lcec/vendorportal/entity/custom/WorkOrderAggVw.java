package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the WorkOrderAggVw database table.
 * 
 */
@Entity
@NamedQuery(name = "WorkOrderAggVw.findAll", query = "SELECT w FROM WorkOrderAggVw w WHERE w.workFlowId = :workflowId")
public class WorkOrderAggVw implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WorkOrderAggVwPk id;
	
	@Column(name="Cost")
	private BigDecimal cost;

	@Column(name="Station")
	private int station;

	@Column(name="TotalAssembly")
	private int totalAssembly;

	//@Id
	@Column(name="Type")
	private String type;

	@Column(name="UniqueAssembly")
	private int uniqueAssembly;

	//@Id
	@Column(name="WorkFlowId")
	private int workFlowId;

	public WorkOrderAggVw() {
	}

	public BigDecimal getCost() {
		return this.cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public int getStation() {
		return this.station;
	}

	public void setStation(int station) {
		this.station = station;
	}

	public int getTotalAssembly() {
		return this.totalAssembly;
	}

	public void setTotalAssembly(int totalAssembly) {
		this.totalAssembly = totalAssembly;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getUniqueAssembly() {
		return this.uniqueAssembly;
	}

	public void setUniqueAssembly(int uniqueAssembly) {
		this.uniqueAssembly = uniqueAssembly;
	}

	public int getWorkFlowId() {
		return this.workFlowId;
	}

	public void setWorkFlowId(int workFlowId) {
		this.workFlowId = workFlowId;
	}

}