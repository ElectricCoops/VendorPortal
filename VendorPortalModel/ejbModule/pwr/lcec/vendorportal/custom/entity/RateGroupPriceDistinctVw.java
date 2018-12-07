package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the RateGroupPriceDistinctVw database table.
 * 
 */
@Entity
@NamedQuery(name="RateGroupPriceDistinctVw.findAll", query="SELECT r FROM RateGroupPriceDistinctVw r WHERE r.assemblySource = 'NISC'")
public class RateGroupPriceDistinctVw implements Serializable {
	private static final long serialVersionUID = 1L;

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

	//private int GLAccountId;

	private BigDecimal GLActivity;

	private BigDecimal GLPercent;

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

	@Id
	@Column(name="RateGroupPriceId")
	private int rateGroupPriceId;
	
	private int GLConstAccountId;
	
	private int GLRetireAccountId;

	public RateGroupPriceDistinctVw() {
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

	/*public int getGLAccountId() {
		return this.GLAccountId;
	}

	public void setGLAccountId(int GLAccountId) {
		this.GLAccountId = GLAccountId;
	}*/

	public BigDecimal getGLActivity() {
		return this.GLActivity;
	}

	public void setGLActivity(BigDecimal GLActivity) {
		this.GLActivity = GLActivity;
	}

	public BigDecimal getGLPercent() {
		return this.GLPercent;
	}

	public void setGLPercent(BigDecimal GLPercent) {
		this.GLPercent = GLPercent;
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

	public int getRateGroupPriceId() {
		return this.rateGroupPriceId;
	}

	public void setRateGroupPriceId(int rateGroupPriceId) {
		this.rateGroupPriceId = rateGroupPriceId;
	}

	public int getGLConstAccountId() {
		return GLConstAccountId;
	}

	public void setGLConstAccountId(int gLConstAccountId) {
		GLConstAccountId = gLConstAccountId;
	}

	public int getGLRetireAccountId() {
		return GLRetireAccountId;
	}

	public void setGLRetireAccountId(int gLRetireAccountId) {
		GLRetireAccountId = gLRetireAccountId;
	}

}