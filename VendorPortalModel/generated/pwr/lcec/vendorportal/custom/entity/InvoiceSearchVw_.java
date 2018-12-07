package pwr.lcec.vendorportal.custom.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-06-29T11:50:59.669-0400")
@StaticMetamodel(InvoiceSearchVw.class)
public class InvoiceSearchVw_ {
	public static volatile SingularAttribute<InvoiceSearchVw, String> apInvId;
	public static volatile SingularAttribute<InvoiceSearchVw, String> approvedBy;
	public static volatile SingularAttribute<InvoiceSearchVw, Timestamp> approvedDt;
	public static volatile SingularAttribute<InvoiceSearchVw, String> businessRuleFlg;
	public static volatile SingularAttribute<InvoiceSearchVw, BigDecimal> invoiceAmount;
	public static volatile SingularAttribute<InvoiceSearchVw, String> invoicedBy;
	public static volatile SingularAttribute<InvoiceSearchVw, Timestamp> invoicedDt;
	public static volatile SingularAttribute<InvoiceSearchVw, Integer> invoiceId;
	public static volatile SingularAttribute<InvoiceSearchVw, Integer> invoiceStatusId;
	public static volatile SingularAttribute<InvoiceSearchVw, String> invoiceType;
	public static volatile SingularAttribute<InvoiceSearchVw, Timestamp> paymentDt;
	public static volatile SingularAttribute<InvoiceSearchVw, String> paymentStatus;
	public static volatile SingularAttribute<InvoiceSearchVw, String> serviceOrderId;
	public static volatile SingularAttribute<InvoiceSearchVw, Integer> vendorId;
	public static volatile SingularAttribute<InvoiceSearchVw, String> vendorName;
	public static volatile SingularAttribute<InvoiceSearchVw, String> vendorReference;
	public static volatile SingularAttribute<InvoiceSearchVw, String> workFlowId;
	public static volatile SingularAttribute<InvoiceSearchVw, String> workGroup;
	public static volatile SingularAttribute<InvoiceSearchVw, String> workOrderId;
	public static volatile SingularAttribute<InvoiceSearchVw, InvoiceStatus> invoiceStatus;
}
