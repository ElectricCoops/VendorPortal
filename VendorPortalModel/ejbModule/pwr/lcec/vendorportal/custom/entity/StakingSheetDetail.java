package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import org.apache.commons.lang.StringUtils;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * The persistent class for the StakingSheetDetail database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "StakingSheetDetail.findAll", query = "SELECT s FROM StakingSheetDetail s"),
		@NamedQuery(name = "StakingSheetDetail.findByInvoiceId", query = "SELECT s FROM StakingSheetDetail s WHERE s.invoiceId = :invId"),
		@NamedQuery(name = "StakingSheetDetail.findByWoId", query = "SELECT s FROM StakingSheetDetail s WHERE s.stakingSheetId = :woId"),
		@NamedQuery(name = "StakingSheetDetail.findByAvailForInvoice", query = "SELECT s FROM StakingSheetDetail s WHERE s.stakingSheetId = :sheetId AND (s.invoiceStatusId IN ('1','3') AND s.inspectionStatus.status = :inspStat )"),
		@NamedQuery(name = "StakingSheetDetail.findByAvailForInsp", query = "SELECT s FROM StakingSheetDetail s WHERE s.stakingSheetId = :stakingSheetId AND s.asBuiltStatusId = :asBuiltStatus AND (s.currentInspectionDetailStatusId <> 3 OR s.currentInspectionDetailStatusId IS NULL)") })
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "MergeInspectionDetail", procedureName = "MergeInspectionDetail", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "InStakingSheetDetailId"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "InAssemblyGuid"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "InInspectionId"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "InInspectionDetailId"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "InInspectionDetailStatusId"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "InInspectionDetailDt"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "InInspectionDetailBy"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "InInspectionDetailComment"),
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "OutResult") }),
		@NamedStoredProcedureQuery(name = "GET_ASSEMBLY_AMOUNT", procedureName = "GET_ASSEMBLY_AMOUNT", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_RATEGROUP"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_WORKTYPE"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_AssemblyUnit"),
				//@StoredProcedureParameter(mode = ParameterMode.IN, type = Timestamp.class, name = "IN_WorkEventDt"),
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = BigDecimal.class, name = "OUT_AMOUNT") }),
		@NamedStoredProcedureQuery(name = "UPDATE_STAKING_ASBUILTAMOUNT", procedureName = "UPDATE_STAKING_ASBUILTAMOUNT", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_StakingSheetDetailID"),
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "OUT_Response") }),
		@NamedStoredProcedureQuery(name = "UPDATE_SUBMIT_INVOICE", procedureName = "UPDATE_SUBMIT_INVOICE", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_GUID"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_InvoiceType"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_InvoicedBy"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "IN_VendorReference"),
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "OUT_InvoiceId") }) })
public class StakingSheetDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "StakingSheetDetailId")
	private String stakingSheetDetailId;

	@Column(name = "AsBuiltBy")
	private String asBuiltBy;

	@Column(name = "AsBuiltComments")
	private String asBuiltComments;

	@Column(name = "AsBuiltDt")
	private Timestamp asBuiltDt;

	@Column(name = "AsBuiltQuantity")
	private int asBuiltQuantity;

	@Column(name = "AsBuiltStatusId")
	private Integer asBuiltStatusId;

	@OneToOne
	@JoinColumn(name = "AsBuiltStatusId", insertable = false, updatable = false)
	private AsBuiltStatus asBuiltStatus;

	@Column(name = "AssemblyActionCode")
	private String assemblyActionCode;

	@Column(name = "AssemblyCreatedDt")
	private Timestamp assemblyCreatedDt;

	@Column(name = "AssemblyDescription")
	private String assemblyDescription;

	@Column(name = "AssemblyGuid")
	private String assemblyGuid;

	@Column(name = "AssemblyModifiedDt")
	private Timestamp assemblyModifiedDt;

	@Column(name = "AssemblyQuantity")
	private int assemblyQuantity;

	@Column(name = "AssemblyRateGroupId")
	private int assemblyRateGroupId;

	@OneToOne
	@JoinColumns({
			@JoinColumn(name = "AssemblyRateGroupId", referencedColumnName = "RateGroupId", insertable = false, updatable = false),
			@JoinColumn(name = "AssemblyGuid", referencedColumnName = "AssemblyGuid", insertable = false, updatable = false) })
	private RateGroupPrice rateGroupPrice;

	@Column(name = "CurrentInspectedDetailBy")
	private String currentInspectedDetailBy;

	@Column(name = "CurrentInspectionDetailDt")
	private Timestamp currentInspectionDetailDt;

	@Column(name = "CurrentInspectionDetailId")
	private Integer currentInspectionDetailId;

	@Column(name = "CurrentInspectionDetailStatusId")
	private Integer currentInspectionDetailStatusId;

	@OneToOne
	@JoinColumn(name = "CurrentInspectionDetailStatusId", insertable = false, updatable = false)
	private InspectionStatus inspectionStatus;

	@Column(name = "CurrentInspectorDetailComments")
	private String currentInspectorDetailComments;

	@Column(name = "InvoiceId")
	private Integer invoiceId;

	@Column(name = "InvoiceStatusId")
	private Integer invoiceStatusId;

	@Column(name = "LcecNotes")
	private String lcecNotes;

	@Column(name = "StakingSheetId")
	private String stakingSheetId;

	@Column(name = "StationDescription")
	private String stationDescription;

	@Column(name = "StatusDescription")
	private String statusDescription;

	@Column(name = "StStatusRefGuid")
	private String stStatusRefGuid;

	@Column(name = "StakingSource")
	private String stakingSource;

	@Column(name = "InvoiceDetailId")
	private Integer invoiceDetailId;

	@Column(name = "InvoiceSubmitGuid")
	private String invoiceSubmitGuid;
	
	@Column(name = "GL_AccountId")
	private Integer glAccountId;
	
	@OneToOne
	@JoinColumn(name="InvoiceStatusId", insertable=false, updatable=false)
	private InvoiceStatus invoiceStatus;
	
	@OneToOne
	@JoinColumn(name="InvoiceId", insertable=false, updatable=false)
	private Invoice invoice;
	
	@OneToOne
	@JoinColumn(name="CurrentInspectedDetailBy", insertable=false, updatable=false)
	private Resource resource;
	
	@Column(name = "InvoiceApprovedBy")
	private String invoiceApprovedBy;
	
	@Column(name = "InvoiceApprovedDt")
	private Timestamp invoiceApprovedDt;
	
	@Column(name = "InvoiceApprovedComment")
	private String invoiceApprovedComment;
	
	@OneToOne
	@JoinColumn(name="InvoiceDetailId", insertable=false, updatable=false)
	private InvoiceDetail invoiceDetail;
	
	@Column(name = "AssemblyAmount")
	private BigDecimal assemblyAmount;
	
	@Column(name = "AsBuiltAmount")
	private BigDecimal asBuiltAmount;
	
	private transient String energize;
	
	private transient String transfer;

	public StakingSheetDetail() {
	}

	public String getStakingSheetDetailId() {
		return this.stakingSheetDetailId;
	}

	public void setStakingSheetDetailId(String stakingSheetDetailId) {
		this.stakingSheetDetailId = stakingSheetDetailId;
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

	public Integer getAsBuiltStatusId() {
		return this.asBuiltStatusId;
	}

	public void setAsBuiltStatusId(Integer asBuiltStatusId) {
		this.asBuiltStatusId = asBuiltStatusId;
	}

	public String getAssemblyActionCode() {
		return this.assemblyActionCode;
	}

	public void setAssemblyActionCode(String assemblyActionCode) {
		this.assemblyActionCode = assemblyActionCode;
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

	public String getCurrentInspectedDetailBy() {
		return this.currentInspectedDetailBy;
	}

	public void setCurrentInspectedDetailBy(String currentInspectedDetailBy) {
		this.currentInspectedDetailBy = currentInspectedDetailBy;
	}

	public Timestamp getCurrentInspectionDetailDt() {
		return this.currentInspectionDetailDt;
	}

	public void setCurrentInspectionDetailDt(Timestamp currentInspectionDetailDt) {
		this.currentInspectionDetailDt = currentInspectionDetailDt;
	}

	public Integer getCurrentInspectionDetailId() {
		return this.currentInspectionDetailId;
	}

	public void setCurrentInspectionDetailId(Integer currentInspectionDetailId) {
		this.currentInspectionDetailId = currentInspectionDetailId;
	}

	public Integer getCurrentInspectionDetailStatusId() {
		return this.currentInspectionDetailStatusId;
	}

	public void setCurrentInspectionDetailStatusId(Integer currentInspectionDetailStatusId) {
		this.currentInspectionDetailStatusId = currentInspectionDetailStatusId;
	}

	public String getCurrentInspectorDetailComments() {
		return this.currentInspectorDetailComments;
	}

	public void setCurrentInspectorDetailComments(String currentInspectorDetailComments) {
		this.currentInspectorDetailComments = currentInspectorDetailComments;
	}

	public Integer getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Integer getInvoiceStatusId() {
		return this.invoiceStatusId;
	}

	public void setInvoiceStatusId(Integer invoiceStatusId) {
		this.invoiceStatusId = invoiceStatusId;
	}

	public String getLcecNotes() {
		return this.lcecNotes;
	}

	public void setLcecNotes(String lcecNotes) {
		this.lcecNotes = lcecNotes;
	}

	public String getStakingSheetId() {
		return this.stakingSheetId;
	}

	public void setStakingSheetId(String stakingSheetId) {
		this.stakingSheetId = stakingSheetId;
	}

	public String getStationDescription() {
		return this.stationDescription;
	}

	public void setStationDescription(String stationDescription) {
		this.stationDescription = stationDescription;
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

	public RateGroupPrice getRateGroupPrice() {
		return rateGroupPrice;
	}

	public void setRateGroupPrice(RateGroupPrice rateGroupPrice) {
		this.rateGroupPrice = rateGroupPrice;
	}

	public String getStakingSource() {
		return stakingSource;
	}

	public void setStakingSource(String stakingSource) {
		this.stakingSource = stakingSource;
	}

	public Integer getInvoiceDetailId() {
		return invoiceDetailId;
	}

	public void setInvoiceDetailId(Integer invoiceDetailId) {
		this.invoiceDetailId = invoiceDetailId;
	}

	public String getInvoiceSubmitGuid() {
		return invoiceSubmitGuid;
	}

	public void setInvoiceSubmitGuid(String invoiceSubmitGuid) {
		this.invoiceSubmitGuid = invoiceSubmitGuid;
	}

	public Integer getGlAccountId() {
		return glAccountId;
	}

	public void setGlAccountId(Integer glAccountId) {
		this.glAccountId = glAccountId;
	}

	public InvoiceStatus getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getInvoiceApprovedBy() {
		return invoiceApprovedBy;
	}

	public void setInvoiceApprovedBy(String invoiceApprovedBy) {
		this.invoiceApprovedBy = invoiceApprovedBy;
	}

	public Timestamp getInvoiceApprovedDt() {
		return invoiceApprovedDt;
	}

	public void setInvoiceApprovedDt(Timestamp invoiceApprovedDt) {
		this.invoiceApprovedDt = invoiceApprovedDt;
	}

	public String getInvoiceApprovedComment() {
		return invoiceApprovedComment;
	}

	public void setInvoiceApprovedComment(String invoiceApprovedComment) {
		this.invoiceApprovedComment = invoiceApprovedComment;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public InvoiceDetail getInvoiceDetail() {
		return invoiceDetail;
	}

	public void setInvoiceDetail(InvoiceDetail invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}

	public String getEnergize() {

		int startPos = assemblyGuid.lastIndexOf("E");

		if (StringUtils.substring(assemblyGuid, assemblyGuid.length() - 1).equals("E")) {
			energize = "Energized";
		} else if ((StringUtils.substring(assemblyGuid, startPos, startPos + 1).equals("E"))) {
			energize = "Energized";
		}else {
			energize = "De-Energized";
		}
		return energize;
	}

	public void setEnergize(String energize) {
		this.energize = energize;
	}

	public String getTransfer() {
		if (StringUtils.substring(assemblyGuid, assemblyGuid.length() - 2).equals(".T")) {
			transfer = "Transfer";
		}else {
			transfer = "Non-Transfer";
		}
		return transfer;
	}

	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}

	public BigDecimal getAssemblyAmount() {
		return assemblyAmount;
	}

	public void setAssemblyAmount(BigDecimal assemblyAmount) {
		this.assemblyAmount = assemblyAmount;
	}

	public BigDecimal getAsBuiltAmount() {
		return asBuiltAmount;
	}

	public void setAsBuiltAmount(BigDecimal asBuiltAmount) {
		this.asBuiltAmount = asBuiltAmount;
	}

}