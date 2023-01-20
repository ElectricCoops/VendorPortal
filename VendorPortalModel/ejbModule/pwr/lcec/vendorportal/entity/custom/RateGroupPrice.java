package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the RateGroupPrice database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "RateGroupPrice.findAll", query = "SELECT r FROM RateGroupPrice r WHERE r.rateGroupId = :rateGrpId"),
	@NamedQuery(name = "RateGroupPrice.findAllDistinct", query = "SELECT DISTINCT r.assemblyGuid FROM RateGroupPrice r ORDER BY r.assemblyGuid ASC"),
	@NamedQuery(name = "RateGroupPrice.findDistinctRateDesc", query = "SELECT DISTINCT r.assemblyDescription FROM RateGroupPrice r WHERE r.assemblyGuid = :assemblyUnit"),
	@NamedQuery(name = "RateGroupPrice.findDistinctYear", query = "SELECT DISTINCT r.effectiveStartDt, r.effectiveEndDt FROM RateGroupPrice r "),
	@NamedQuery(name = "RateGroupPrice.findDistinctStartDt", query = "SELECT DISTINCT r.effectiveStartDt FROM RateGroupPrice r "),
	@NamedQuery(name = "RateGroupPrice.findDistinctEndDt", query = "SELECT DISTINCT r.effectiveEndDt FROM RateGroupPrice r "),
	@NamedQuery(name = "RateGroupPrice.findEndFromStart", query = "SELECT r FROM RateGroupPrice r WHERE r.effectiveStartDt = :effectiveStartDt"),
	@NamedQuery(name = "RateGroupPrice.findAuBeforeSave", query = "SELECT r FROM RateGroupPrice r WHERE r.assemblyGuid = :assemblyGuid AND r.rateGroupId = :rateGroupId AND r.effectiveStartDt = :effectiveStartDt"),
	@NamedQuery(name = "RateGroupPrice.findById", query = "SELECT r FROM RateGroupPrice r WHERE r.rateGroupPriceId = :rateGroupPriceId")
})
public class RateGroupPrice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RateGroupPriceId")
	private int rateGroupPriceId;

	@Column(name="AssemblyAction")
	private String assemblyAction;

	@Column(name="AssemblyDescription")
	private String assemblyDescription;

	@Column(name="AssemblyGuid")
	private String assemblyGuid;

	@Column(name="AssemblySource")
	private String assemblySource;

	@Column(name="EffectiveEndDt")
	private Timestamp effectiveEndDt;

	@Column(name="EffectiveStartDt")
	private Timestamp effectiveStartDt;

	@Column(name="FixedCost")
	private String fixedCost;

	private BigDecimal GL_Department;

	private BigDecimal GLActivity;

	private int GLConstAccountId;

	private BigDecimal GLPercent;

	private int GLRetireAccountId;

	@Column(name = "GLRetireActivity")
	private BigDecimal gLRetireActivity;

	@Column(name = "GLRetireDepartment")
	private BigDecimal gLRetireDepartment;

	@Column(name="LaborConstCost")
	private String laborConstCost;

	@Column(name="LaborConstHours")
	private BigDecimal laborConstHours;

	@Column(name="LaborRetireCost")
	private String laborRetireCost;

	@Column(name="LaborRetireHours")
	private BigDecimal laborRetireHours;

	@Column(name="RateGroupId")
	private int rateGroupId;

	public RateGroupPrice() {
	}

	public int getRateGroupPriceId() {
		return this.rateGroupPriceId;
	}

	public void setRateGroupPriceId(int rateGroupPriceId) {
		this.rateGroupPriceId = rateGroupPriceId;
	}

	public String getAssemblyAction() {
		return this.assemblyAction;
	}

	public void setAssemblyAction(String assemblyAction) {
		this.assemblyAction = assemblyAction;
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

	public String getAssemblySource() {
		return this.assemblySource;
	}

	public void setAssemblySource(String assemblySource) {
		this.assemblySource = assemblySource;
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

	public String getFixedCost() {
		return this.fixedCost;
	}

	public void setFixedCost(String fixedCost) {
		this.fixedCost = fixedCost;
	}

	public BigDecimal getGL_Department() {
		return this.GL_Department;
	}

	public void setGL_Department(BigDecimal GL_Department) {
		this.GL_Department = GL_Department;
	}

	public BigDecimal getGLActivity() {
		return this.GLActivity;
	}

	public void setGLActivity(BigDecimal GLActivity) {
		this.GLActivity = GLActivity;
	}

	public int getGLConstAccountId() {
		return this.GLConstAccountId;
	}

	public void setGLConstAccountId(int GLConstAccountId) {
		this.GLConstAccountId = GLConstAccountId;
	}

	public BigDecimal getGLPercent() {
		return this.GLPercent;
	}

	public void setGLPercent(BigDecimal GLPercent) {
		this.GLPercent = GLPercent;
	}

	public int getGLRetireAccountId() {
		return this.GLRetireAccountId;
	}

	public void setGLRetireAccountId(int GLRetireAccountId) {
		this.GLRetireAccountId = GLRetireAccountId;
	}

	public BigDecimal getgLRetireActivity() {
		return gLRetireActivity;
	}

	public void setgLRetireActivity(BigDecimal gLRetireActivity) {
		this.gLRetireActivity = gLRetireActivity;
	}

	public BigDecimal getgLRetireDepartment() {
		return gLRetireDepartment;
	}

	public void setgLRetireDepartment(BigDecimal gLRetireDepartment) {
		this.gLRetireDepartment = gLRetireDepartment;
	}

	public String getLaborConstCost() {
		return this.laborConstCost;
	}

	public void setLaborConstCost(String laborConstCost) {
		this.laborConstCost = laborConstCost;
	}

	public BigDecimal getLaborConstHours() {
		return this.laborConstHours;
	}

	public void setLaborConstHours(BigDecimal laborConstHours) {
		this.laborConstHours = laborConstHours;
	}

	public String getLaborRetireCost() {
		return this.laborRetireCost;
	}

	public void setLaborRetireCost(String laborRetireCost) {
		this.laborRetireCost = laborRetireCost;
	}

	public BigDecimal getLaborRetireHours() {
		return this.laborRetireHours;
	}

	public void setLaborRetireHours(BigDecimal laborRetireHours) {
		this.laborRetireHours = laborRetireHours;
	}

	public int getRateGroupId() {
		return this.rateGroupId;
	}

	public void setRateGroupId(int rateGroupId) {
		this.rateGroupId = rateGroupId;
	}

}