package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the StreetLightSearchVw database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "StreetLightSearchVw.findAll", query = "SELECT s FROM StreetLightSearchVw s"),
	@NamedQuery(name = "StreetLightSearchVw.findForInvoice", query = "SELECT s FROM StreetLightSearchVw s WHERE s.workGroup = :wrkgrp AND s.inspectionStatusId = 4 AND s.invoiceStatus = :invoiceStatus"),
	@NamedQuery(name = "StreetLightSearchVw.findForInvoiceLCEC", query = "SELECT s FROM StreetLightSearchVw s WHERE s.inspectionStatusId = 4 AND s.invoiceStatus = :invoiceStatus"),
	@NamedQuery(name = "StreetLightSearchVw.findAllSum", query = "SELECT s FROM StreetLightSearchVw s WHERE s.serviceOrderId IN :soid AND s.workGroup = :wrkgrp"),
	@NamedQuery(name = "StreetLightSearchVw.findByWorkGroup", query = "SELECT s FROM StreetLightSearchVw s WHERE s.workGroup = :wrkgrp"),
	@NamedQuery(name = "StreetLightSearchVw.findByWoId", query = "SELECT s FROM StreetLightSearchVw s WHERE s.workOrderId = :woId AND s.workGroup = :wrkgrp"),
	@NamedQuery(name = "StreetLightSearchVw.findBySoId", query = "SELECT s FROM StreetLightSearchVw s WHERE s.serviceOrderId = :soId AND s.workGroup = :wrkgrp"),
	@NamedQuery(name = "StreetLightSearchVw.findAllDistinct", query = "SELECT s FROM StreetLightSearchVw s WHERE s.workGroup = :wrkgrp"),
	@NamedQuery(name = "StreetLightSearchVw.findByInvStatus", query = "SELECT s FROM StreetLightSearchVw s WHERE s.workGroup = :wrkgrp AND s.invoiceStatus = :invStatus"),
	@NamedQuery(name = "StreetLightSearchVw.findByInvoiceId", query = "SELECT s FROM StreetLightSearchVw s WHERE s.invoiceId = :invId") 
})
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
	
	@Column(name = "InspectionStatusId")
	private Integer inspectionStatusId;
	
	@OneToOne
	@JoinColumn(name = "InspectionStatusId", insertable = false, updatable = false)
	private InspectionStatus inspectionStatus;

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

	@Column(name="InvoiceStatusId")
	private Integer invoiceStatusId;

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

	@Column(name="WorkEventDt")
	private Timestamp workEventDt;

	@Column(name="WorkGroup")
	private String workGroup;

	@Column(name="WorkOrderId")
	private String workOrderId;

	public StreetLightSearchVw() {
	}

	public String getFixedCost() {
		return this.fixedCost;
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
		return inspectionStatusId;
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

	public Integer getInvoiceStatusId() {
		return this.invoiceStatusId;
	}

	public void setInvoiceStatusId(Integer invoiceStatusId) {
		this.invoiceStatusId = invoiceStatusId;
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

	public Timestamp getWorkEventDt() {
		return this.workEventDt;
	}

	public void setWorkEventDt(Timestamp workEventDt) {
		this.workEventDt = workEventDt;
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
		return this.inspectionStatus;
	}

	public void setInspectionStatus(InspectionStatus inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}

}