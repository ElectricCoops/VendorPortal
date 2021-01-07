package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the InspectionDetailVw database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "InspectionDetailVw.findAll", query = "SELECT i FROM InspectionDetailVw i"),
	@NamedQuery(name = "InspectionDetailVw.findByInspId", query = "SELECT i FROM InspectionDetailVw i WHERE i.inspectionId = :inspId")
})
public class InspectionDetailVw implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AsBuiltAmount")
	private BigDecimal asBuiltAmount;

	@Column(name="AsBuiltBy")
	private String asBuiltBy;

	@Column(name="AsBuiltComments")
	private String asBuiltComments;

	@Column(name="AsBuiltDt")
	private Timestamp asBuiltDt;

	@Column(name="AsBuiltQuantity")
	private int asBuiltQuantity;

	@Column(name="AsBuiltStatusId")
	private int asBuiltStatusId;
	
	@OneToOne
	@JoinColumn(name = "AsBuiltStatusId", insertable = false, updatable = false)
	private AsBuiltStatus asBuiltStatus;

	@Column(name="AssembleUnitId")
	private String assembleUnitId;

	@Column(name="AssemblyActionCode")
	private String assemblyActionCode;

	@Column(name="AssemblyAmount")
	private BigDecimal assemblyAmount;

	@Column(name="AssemblyCreatedDt")
	private Timestamp assemblyCreatedDt;

	@Column(name="AssemblyDescription")
	private String assemblyDescription;

	@Column(name="AssemblyGuid")
	private String assemblyGuid;

	@Column(name="AssemblyModifiedDt")
	private Timestamp assemblyModifiedDt;

	@Column(name="AssemblyQuantity")
	private int assemblyQuantity;

	@Column(name="AssemblyRateGroupId")
	private int assemblyRateGroupId;

	@Column(name="Comment")
	private String comment;

	@Column(name="InspectionDetailDt")
	private Timestamp inspectionDetailDt;

	@Column(name="InspectionDetailId")
	private int inspectionDetailId;

	@Column(name="InspectionId")
	private int inspectionId;

	@Column(name="InspectionStatusId")
	private int inspectionStatusId;
	
	@OneToOne
	@JoinColumn(name = "InspectionStatusId", insertable = false, updatable = false)
	private InspectionStatus inspectionStatus;

	@Column(name="LcecNotes")
	private String lcecNotes;

	@Id
	@Column(name="StakingSheetDetailId")
	private String stakingSheetDetailId;

	@Column(name="StakingSheetId")
	private int stakingSheetId;

	@Column(name="StationDescription")
	private String stationDescription;

	@Column(name="StationId")
	private String stationId;

	@Column(name="StatusDescription")
	private String statusDescription;

	@Column(name="StStatusRefGuid")
	private String stStatusRefGuid;
	
	private transient String energize;

	private transient String transfer;

	public InspectionDetailVw() {
	}

	public BigDecimal getAsBuiltAmount() {
		return this.asBuiltAmount;
	}

	public void setAsBuiltAmount(BigDecimal asBuiltAmount) {
		this.asBuiltAmount = asBuiltAmount;
	}

	public String getAsBuiltBy() {
		return this.asBuiltBy;
	}

	public void setAsBuiltBy(String asBuiltBy) {
		this.asBuiltBy = asBuiltBy;
	}

	public String getAsBuiltComments() {
		return this.asBuiltComments;
	}

	public void setAsBuiltComments(String asBuiltComments) {
		this.asBuiltComments = asBuiltComments;
	}

	public Timestamp getAsBuiltDt() {
		return this.asBuiltDt;
	}

	public void setAsBuiltDt(Timestamp asBuiltDt) {
		this.asBuiltDt = asBuiltDt;
	}

	public int getAsBuiltQuantity() {
		return this.asBuiltQuantity;
	}

	public void setAsBuiltQuantity(int asBuiltQuantity) {
		this.asBuiltQuantity = asBuiltQuantity;
	}

	public int getAsBuiltStatusId() {
		return this.asBuiltStatusId;
	}

	public void setAsBuiltStatusId(int asBuiltStatusId) {
		this.asBuiltStatusId = asBuiltStatusId;
	}

	public String getAssembleUnitId() {
		return this.assembleUnitId;
	}

	public void setAssembleUnitId(String assembleUnitId) {
		this.assembleUnitId = assembleUnitId;
	}

	public String getAssemblyActionCode() {
		return this.assemblyActionCode;
	}

	public void setAssemblyActionCode(String assemblyActionCode) {
		this.assemblyActionCode = assemblyActionCode;
	}

	public BigDecimal getAssemblyAmount() {
		return this.assemblyAmount;
	}

	public void setAssemblyAmount(BigDecimal assemblyAmount) {
		this.assemblyAmount = assemblyAmount;
	}

	public Timestamp getAssemblyCreatedDt() {
		return this.assemblyCreatedDt;
	}

	public void setAssemblyCreatedDt(Timestamp assemblyCreatedDt) {
		this.assemblyCreatedDt = assemblyCreatedDt;
	}

	public String getAssemblyDescription() {
		return this.assemblyDescription;
	}

	public void setAssemblyDescription(String assemblyDescription) {
		this.assemblyDescription = assemblyDescription;
	}

	public String getAssemblyGuid() {
		return this.assemblyGuid;
	}

	public void setAssemblyGuid(String assemblyGuid) {
		this.assemblyGuid = assemblyGuid;
	}

	public Timestamp getAssemblyModifiedDt() {
		return this.assemblyModifiedDt;
	}

	public void setAssemblyModifiedDt(Timestamp assemblyModifiedDt) {
		this.assemblyModifiedDt = assemblyModifiedDt;
	}

	public int getAssemblyQuantity() {
		return this.assemblyQuantity;
	}

	public void setAssemblyQuantity(int assemblyQuantity) {
		this.assemblyQuantity = assemblyQuantity;
	}

	public int getAssemblyRateGroupId() {
		return this.assemblyRateGroupId;
	}

	public void setAssemblyRateGroupId(int assemblyRateGroupId) {
		this.assemblyRateGroupId = assemblyRateGroupId;
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

	public int getInspectionDetailId() {
		return this.inspectionDetailId;
	}

	public void setInspectionDetailId(int inspectionDetailId) {
		this.inspectionDetailId = inspectionDetailId;
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

	public String getLcecNotes() {
		return this.lcecNotes;
	}

	public void setLcecNotes(String lcecNotes) {
		this.lcecNotes = lcecNotes;
	}

	public String getStakingSheetDetailId() {
		return this.stakingSheetDetailId;
	}

	public void setStakingSheetDetailId(String stakingSheetDetailId) {
		this.stakingSheetDetailId = stakingSheetDetailId;
	}

	public int getStakingSheetId() {
		return this.stakingSheetId;
	}

	public void setStakingSheetId(int stakingSheetId) {
		this.stakingSheetId = stakingSheetId;
	}

	public String getStationDescription() {
		return this.stationDescription;
	}

	public void setStationDescription(String stationDescription) {
		this.stationDescription = stationDescription;
	}

	public String getStationId() {
		return this.stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStatusDescription() {
		return this.statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public String getStStatusRefGuid() {
		return this.stStatusRefGuid;
	}

	public void setStStatusRefGuid(String stStatusRefGuid) {
		this.stStatusRefGuid = stStatusRefGuid;
	}

	public AsBuiltStatus getAsBuiltStatus() {
		return asBuiltStatus;
	}

	public void setAsBuiltStatus(AsBuiltStatus asBuiltStatus) {
		this.asBuiltStatus = asBuiltStatus;
	}

	public InspectionStatus getInspectionStatus() {
		return inspectionStatus;
	}

	public void setInspectionStatus(InspectionStatus inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}

	public String getEnergize() {
		int startPos = this.assemblyGuid.lastIndexOf("E");

		if (StringUtils.substring(assemblyGuid, assemblyGuid.length() - 1).equals("E")) {
			energize = "Energized";
		}else if (StringUtils.substring(this.assemblyGuid, startPos, startPos + 1).equals("E")) {
			energize = "Energized";
		}else {
			energize = "De-Energized";
		} 
		return this.energize;
	}

	public void setEnergize(String energize) {
		this.energize = energize; 
	}

	public String getTransfer() {
		if (StringUtils.substring(assemblyGuid, assemblyGuid.length() - 1).equals("T")) {
			transfer = "Transfer";
		} else {
			transfer = "Non-Transfer";
		} 
		return 	transfer;
	}

	public void setTransfer(String transfer) { 
		this.transfer = transfer; 
	}
}