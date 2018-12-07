package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;


@Entity
@NamedQueries({
		@NamedQuery(name = "StreetLightSearchVw.findAll", query = "SELECT s FROM StreetLightSearchVw s"),
		@NamedQuery(name = "StreetLightSearchVw.findForInvoice", query = "SELECT s FROM StreetLightSearchVw s WHERE s.workGroup = :wrkgrp AND s.inspectionStatusId = 4 AND s.invoiceStatus = :invoiceStatus"),
		@NamedQuery(name = "StreetLightSearchVw.findForInvoiceLCEC", query = "SELECT s FROM StreetLightSearchVw s WHERE s.inspectionStatusId = 4 AND s.invoiceStatus = :invoiceStatus"),
		@NamedQuery(name = "StreetLightSearchVw.findAllSum", query = "SELECT s FROM StreetLightSearchVw s WHERE s.serviceOrderId IN :soid AND s.workGroup = :wrkgrp"),
		@NamedQuery(name = "StreetLightSearchVw.findByWorkGroup", query = "SELECT s FROM StreetLightSearchVw s WHERE s.workGroup = :wrkgrp"),
		@NamedQuery(name = "StreetLightSearchVw.findByWoId", query = "SELECT s FROM StreetLightSearchVw s WHERE s.workOrderId = :woId AND s.workGroup = :wrkgrp"),
		@NamedQuery(name = "StreetLightSearchVw.findBySoId", query = "SELECT s FROM StreetLightSearchVw s WHERE s.serviceOrderId = :soId AND s.workGroup = :wrkgrp"),
		@NamedQuery(name = "StreetLightSearchVw.findAllDistinct", query = "SELECT s FROM StreetLightSearchVw s WHERE s.workGroup = :wrkgrp"),
		@NamedQuery(name = "StreetLightSearchVw.findByInvStatus", query = "SELECT s FROM StreetLightSearchVw s WHERE s.workGroup = :wrkgrp AND s.invoiceStatus = :invStatus"),
		@NamedQuery(name = "StreetLightSearchVw.findByInvoiceId", query = "SELECT s FROM StreetLightSearchVw s WHERE s.invoiceId = :invId")})
public class StreetLightSearchVw implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="FixedCost")
	private String fixedCost;

	private Integer GLConstAccountId;

	private String GLConstDescription;

	private BigDecimal GLPercent;

	private Integer GLRetireAccountId;

	private String GLRetireDescription;

	@Column(name="InspectedBy")
	private String inspectedBy;

	@Column(name="InspectedComment")
	private String inspectedComment;

	@Column(name="InspectedDt")
	private Timestamp inspectedDt;

	@Column(name="InspectionId")
	private Integer inspectionId;

	@Column(name="InspectionStatusId")
	private Integer inspectionStatusId;

	@Column(name="InvoiceApprovedBy")
	private String invoiceApprovedBy;

	@Column(name="InvoiceApprovedComments")
	private String invoiceApprovedComments;

	@Column(name="InvoiceApprovedDt")
	private Timestamp invoiceApprovedDt;

	@Column(name="InvoiceId")
	private Integer invoiceId;

	@Column(name="InvoiceStatus")
	private String invoiceStatus;

	@Column(name="Quantity")
	private Integer quantity;

	@Column(name="ServiceMapLocation")
	private String serviceMapLocation;

	@Id
	@Column(name="ServiceOrderId")
	private String serviceOrderId;

	@Column(name="SoCloseDt")
	private Timestamp soCloseDt;

	@Column(name="SoCrewId")
	private String soCrewId;

	@Column(name="SoStatCode")
	private String soStatCode;

	@Column(name="SoTypeCode")
	private String soTypeCode;

	@Column(name="VendorName")
	private String vendorName;

	@Column(name="VendorReference")
	private String vendorReference;

	@Column(name="WorkGroup")
	private String workGroup;

	@Column(name="WorkOrderId")
	private String workOrderId;
	
	@Column(name="InvoiceStatusId")
	private Integer invoiceStatusId;
	
	private transient BigDecimal extPrice;
	
	@OneToOne
	@JoinColumn(name="InspectionStatusId", insertable=false, updatable=false)
	private InspectionStatus inspectionStatus;

	public StreetLightSearchVw() {
	}

	public String getFixedCost() {
		return this.fixedCost.trim();
	}

	public void setFixedCost(String fixedCost) {
		this.fixedCost = fixedCost;
	}

	public Integer getGLConstAccountId() {
		return this.GLConstAccountId;
	}

	public void setGLConstAccountId(Integer GLConstAccountId) {
		this.GLConstAccountId = GLConstAccountId;
	}

	public String getGLConstDescription() {
		return this.GLConstDescription;
	}

	public void setGLConstDescription(String GLConstDescription) {
		this.GLConstDescription = GLConstDescription;
	}

	public BigDecimal getGLPercent() {
		return this.GLPercent;
	}

	public void setGLPercent(BigDecimal GLPercent) {
		this.GLPercent = GLPercent;
	}

	public Integer getGLRetireAccountId() {
		return this.GLRetireAccountId;
	}

	public void setGLRetireAccountId(Integer GLRetireAccountId) {
		this.GLRetireAccountId = GLRetireAccountId;
	}

	public String getGLRetireDescription() {
		return this.GLRetireDescription;
	}

	public void setGLRetireDescription(String GLRetireDescription) {
		this.GLRetireDescription = GLRetireDescription;
	}

	public String getInspectedBy() {
		return this.inspectedBy;
	}

	public void setInspectedBy(String inspectedBy) {
		this.inspectedBy = inspectedBy;
	}

	public String getInspectedComment() {
		return this.inspectedComment;
	}

	public void setInspectedComment(String inspectedComment) {
		this.inspectedComment = inspectedComment;
	}

	public Timestamp getInspectedDt() {
		return this.inspectedDt;
	}

	public void setInspectedDt(Timestamp inspectedDt) {
		this.inspectedDt = inspectedDt;
	}

	public Integer getInspectionId() {
		return this.inspectionId;
	}

	public void setInspectionId(Integer inspectionId) {
		this.inspectionId = inspectionId;
	}

	public Integer getInspectionStatusId() {
		return this.inspectionStatusId;
	}

	public void setInspectionStatusId(Integer inspectionStatusId) {
		this.inspectionStatusId = inspectionStatusId;
	}

	public String getInvoiceApprovedBy() {
		return this.invoiceApprovedBy;
	}

	public void setInvoiceApprovedBy(String invoiceApprovedBy) {
		this.invoiceApprovedBy = invoiceApprovedBy;
	}

	public String getInvoiceApprovedComments() {
		return this.invoiceApprovedComments;
	}

	public void setInvoiceApprovedComments(String invoiceApprovedComments) {
		this.invoiceApprovedComments = invoiceApprovedComments;
	}

	public Timestamp getInvoiceApprovedDt() {
		return this.invoiceApprovedDt;
	}

	public void setInvoiceApprovedDt(Timestamp invoiceApprovedDt) {
		this.invoiceApprovedDt = invoiceApprovedDt;
	}

	public Integer getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceStatus() {
		return this.invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getServiceMapLocation() {
		return this.serviceMapLocation;
	}

	public void setServiceMapLocation(String serviceMapLocation) {
		this.serviceMapLocation = serviceMapLocation;
	}

	public String getServiceOrderId() {
		return this.serviceOrderId;
	}

	public void setServiceOrderId(String serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}

	public Timestamp getSoCloseDt() {
		return this.soCloseDt;
	}

	public void setSoCloseDt(Timestamp soCloseDt) {
		this.soCloseDt = soCloseDt;
	}

	public String getSoCrewId() {
		return this.soCrewId;
	}

	public void setSoCrewId(String soCrewId) {
		this.soCrewId = soCrewId;
	}

	public String getSoStatCode() {

		if (soStatCode.equals("O")) {
			this.soStatCode = "Open";
		} else if (soStatCode.equals("X")) {
			this.soStatCode = "Cancelled";
		} else if (soStatCode.equals("C")) {
			this.soStatCode = "Closed";
		} else if (soStatCode.equals("I")) {
			this.soStatCode = "Initiated";
		}
		return this.soStatCode;
	}

	public void setSoStatCode(String soStatCode) {
		this.soStatCode = soStatCode;
	}

	public String getSoTypeCode() {
		return this.soTypeCode;
	}

	public void setSoTypeCode(String soTypeCode) {
		this.soTypeCode = soTypeCode;
	}

	public String getVendorName() {
		return this.vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorReference() {
		return this.vendorReference;
	}

	public void setVendorReference(String vendorReference) {
		this.vendorReference = vendorReference;
	}

	public String getWorkGroup() {
		return this.workGroup;
	}

	public void setWorkGroup(String workGroup) {
		this.workGroup = workGroup;
	}

	public String getWorkOrderId() {
		return this.workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

	public InspectionStatus getInspectionStatus() {
		return inspectionStatus;
	}

	public void setInspectionStatus(InspectionStatus inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}

	public Integer getInvoiceStatusId() {
		return invoiceStatusId;
	}

	public void setInvoiceStatusId(Integer invoiceStatusId) {
		this.invoiceStatusId = invoiceStatusId;
	}

	public BigDecimal getExtPrice() {
		
		extPrice = new BigDecimal(quantity).multiply(new BigDecimal(fixedCost));
		
		return extPrice;
	}

	public void setExtPrice(BigDecimal extPrice) {
		this.extPrice = extPrice;
	}

}