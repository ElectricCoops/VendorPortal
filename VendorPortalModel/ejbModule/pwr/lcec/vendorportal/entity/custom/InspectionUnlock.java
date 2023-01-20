package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the InspectionUnlock database table.
 * 
 */
@Entity
@NamedQuery(name="InspectionUnlock.findAll", query="SELECT i FROM InspectionUnlock i")
public class InspectionUnlock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="InspectionUnlockId")
	private int inspectionUnlockId;

	@Column(name="InspectionId")
	private int inspectionId;

	@Column(name="StakingSheetDetailId")
	private String stakingSheetDetailId;

	@Column(name="UnlockedBy")
	private String unlockedBy;

	@Column(name="UnlockedDt")
	private Timestamp unlockedDt;

	@Column(name="VoucherId")
	private Integer voucherId;

	@Column(name="WorkOrderId")
	private String workOrderId;

	public InspectionUnlock() {
	}

	public int getInspectionUnlockId() {
		return this.inspectionUnlockId;
	}

	public void setInspectionUnlockId(int inspectionUnlockId) {
		this.inspectionUnlockId = inspectionUnlockId;
	}

	public int getInspectionId() {
		return this.inspectionId;
	}

	public void setInspectionId(int inspectionId) {
		this.inspectionId = inspectionId;
	}

	public String getStakingSheetDetailId() {
		return this.stakingSheetDetailId;
	}

	public void setStakingSheetDetailId(String stakingSheetDetailId) {
		this.stakingSheetDetailId = stakingSheetDetailId;
	}

	public String getUnlockedBy() {
		return this.unlockedBy;
	}

	public void setUnlockedBy(String unlockedBy) {
		this.unlockedBy = unlockedBy;
	}

	public Timestamp getUnlockedDt() {
		return this.unlockedDt;
	}

	public void setUnlockedDt(Timestamp unlockedDt) {
		this.unlockedDt = unlockedDt;
	}

	public Integer getVoucherId() {
		return this.voucherId;
	}

	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}

	public String getWorkOrderId() {
		return this.workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

}