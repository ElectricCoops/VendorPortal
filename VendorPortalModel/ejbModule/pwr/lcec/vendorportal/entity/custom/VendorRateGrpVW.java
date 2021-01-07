package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;


@Entity
@NamedQueries({
		@NamedQuery(name = "VendorRateGrpVW.findAll", query = "SELECT v FROM VendorRateGrpVW v ORDER BY v.assemblyGuid ASC"),
		@NamedQuery(name = "VendorRateGrpVW.findByVendorName", query = "SELECT v FROM VendorRateGrpVW v WHERE v.vendorname = :vname ORDER BY v.assemblyGuid ASC") })
@NamedStoredProcedureQuery(name = "GET_RATEGROUP_PIVOT", procedureName = "GET_RATEGROUP_PIVOT", resultSetMappings = {
		"RateGroupPivotMapping" })
/*@SqlResultSetMapping(name = "RateGroupPivotMapping", classes = {
		@ConstructorResult(targetClass = RateGroupPivot.class, columns = {
				@ColumnResult(name = "assemblyUnit"), @ColumnResult(name = "gndc"), @ColumnResult(name = "gndr"),
				@ColumnResult(name = "gnec"), @ColumnResult(name = "gner"), @ColumnResult(name = "msdc"),
				@ColumnResult(name = "msdr"), @ColumnResult(name = "msec"), @ColumnResult(name = "mser"),
				@ColumnResult(name = "pkdc"), @ColumnResult(name = "pkdr"), @ColumnResult(name = "pkec"),
				@ColumnResult(name = "pker") }) })*/
public class VendorRateGrpVW implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private VendorRateGrpVWPK id;
	@Column(name = "AssemblyGuid")
	private String assemblyGuid;
	@Column(name = "Comments")
	private String comments;
	@Column(name = "Description")
	private String description;
	@Column(name = "EffectiveEndDt")
	private Timestamp effectiveEndDt;
	@Column(name = "EffectiveStartDt")
	private Timestamp effectiveStartDt;
	@Column(name = "Energized")
	private boolean energized;
	@Column(name = "FixedCost")
	private String fixedCost;
	@Column(name = "FixedRateSW")
	private String fixedRateSW;
	@Column(name = "LaborCost")
	private String laborCost;
	@Column(name = "LaborHours")
	private BigDecimal laborHours;
	@Column(name = "RateGroupId")
	private String rateGroupId;
	@Column(name = "StatusCode")
	private String statusCode;
	@Column(name = "VendorId")
	private int vendorId;
	private String vendorname;

	public String getAssemblyGuid() {
		return this.assemblyGuid;
	}

	public void setAssemblyGuid(String assemblyGuid) {
		this.assemblyGuid = assemblyGuid;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getEffectiveEndDt() {
		return this.effectiveEndDt;
	}

	public void setEffectiveEndDt(Timestamp effectiveEndDt) {
		this.effectiveEndDt = effectiveEndDt;
	}

	public Timestamp getEffectiveStartDt() {
		return this.effectiveStartDt;
	}

	public void setEffectiveStartDt(Timestamp effectiveStartDt) {
		this.effectiveStartDt = effectiveStartDt;
	}

	public boolean getEnergized() {
		return this.energized;
	}

	public void setEnergized(boolean energized) {
		this.energized = energized;
	}

	public String getFixedCost() {
		return this.fixedCost;
	}

	public void setFixedCost(String fixedCost) {
		this.fixedCost = fixedCost;
	}

	public String getFixedRateSW() {
		return this.fixedRateSW;
	}

	public void setFixedRateSW(String fixedRateSW) {
		this.fixedRateSW = fixedRateSW;
	}

	public String getLaborCost() {
		return this.laborCost;
	}

	public void setLaborCost(String laborCost) {
		this.laborCost = laborCost;
	}

	public BigDecimal getLaborHours() {
		return this.laborHours;
	}

	public void setLaborHours(BigDecimal laborHours) {
		this.laborHours = laborHours;
	}

	public String getRateGroupId() {
		return this.rateGroupId;
	}

	public void setRateGroupId(String rateGroupId) {
		this.rateGroupId = rateGroupId;
	}

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public int getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorname() {
		return this.vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}

	public VendorRateGrpVWPK getId() {
		return this.id;
	}
}
