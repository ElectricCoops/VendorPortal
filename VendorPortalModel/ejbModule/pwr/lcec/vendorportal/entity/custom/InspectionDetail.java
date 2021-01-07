package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the InspectionDetail database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "InspectionDetail.findAll", query = "SELECT i FROM InspectionDetail i"),
	@NamedQuery(name = "InspectionDetail.deleteInspectionDetailByInspectionId", query = "DELETE FROM InspectionDetail i WHERE i.inspectionId = :inspectionId") 
})
public class InspectionDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="InspectionDetailId")
	private int inspectionDetailId;

	@Column(name="AssembleUnitId")
	private String assembleUnitId;

	@Column(name="Comment")
	private String comment;

	@Column(name="InspectionDetailDt")
	private Timestamp inspectionDetailDt;

	@Column(name="InspectionId")
	private int inspectionId;

	@Column(name="InspectionStatusId")
	private int inspectionStatusId;

	@Column(name="StakingSheetDetailId")
	private String stakingSheetDetailId;

	@Column(name="StationId")
	private String stationId;

	public InspectionDetail() {
	}

	public int getInspectionDetailId() {
		return this.inspectionDetailId;
	}

	public void setInspectionDetailId(int inspectionDetailId) {
		this.inspectionDetailId = inspectionDetailId;
	}

	public String getAssembleUnitId() {
		return this.assembleUnitId;
	}

	public void setAssembleUnitId(String assembleUnitId) {
		this.assembleUnitId = assembleUnitId;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getInspectionDetailDt() {
		return this.inspectionDetailDt;
	}

	public void setInspectionDetailDt(Timestamp inspectionDetailDt) {
		this.inspectionDetailDt = inspectionDetailDt;
	}

	public int getInspectionId() {
		return this.inspectionId;
	}

	public void setInspectionId(int inspectionId) {
		this.inspectionId = inspectionId;
	}

	public int getInspectionStatusId() {
		return this.inspectionStatusId;
	}

	public void setInspectionStatusId(int inspectionStatusId) {
		this.inspectionStatusId = inspectionStatusId;
	}

	public String getStakingSheetDetailId() {
		return this.stakingSheetDetailId;
	}

	public void setStakingSheetDetailId(String stakingSheetDetailId) {
		this.stakingSheetDetailId = stakingSheetDetailId;
	}

	public String getStationId() {
		return this.stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

}