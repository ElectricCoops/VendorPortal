package pwr.lcec.vendorportal.custom.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-07-16T16:45:37.300-0400")
@StaticMetamodel(Invoice.class)
public class Invoice_ {
	public static volatile SingularAttribute<Invoice, Integer> invoiceId;
	public static volatile SingularAttribute<Invoice, String> apInvId;
	public static volatile SingularAttribute<Invoice, String> businessRuleFlg;
	public static volatile SingularAttribute<Invoice, BigDecimal> invoiceAmount;
	public static volatile SingularAttribute<Invoice, String> invoicedBy;
	public static volatile SingularAttribute<Invoice, Timestamp> invoicedDt;
	public static volatile SingularAttribute<Invoice, Integer> invoiceStatusId;
	public static volatile SingularAttribute<Invoice, InvoiceStatus> invoiceStatus;
	public static volatile SingularAttribute<Invoice, String> invoiceType;
	public static volatile SingularAttribute<Invoice, Timestamp> paymentDt;
	public static volatile SingularAttribute<Invoice, String> paymentStatus;
	public static volatile SingularAttribute<Invoice, String> serviceOrderId;
	public static volatile SingularAttribute<Invoice, Integer> vendorId;
	public static volatile SingularAttribute<Invoice, Vendor> vendor;
	public static volatile SingularAttribute<Invoice, String> vendorReference;
	public static volatile SingularAttribute<Invoice, String> workFlowId;
	public static volatile SingularAttribute<Invoice, String> workOrderId;
	public static volatile SingularAttribute<Invoice, String> approvedBy;
	public static volatile SingularAttribute<Invoice, Timestamp> approvedDt;
	public static volatile SingularAttribute<Invoice, String> approvedComment;
	public static volatile SingularAttribute<Invoice, BigDecimal> designCost;
	public static volatile SingularAttribute<Invoice, Integer> designTotalAssembly;
	public static volatile SingularAttribute<Invoice, Integer> designUniqueAssembly;
	public static volatile SingularAttribute<Invoice, Integer> designTotalStation;
	public static volatile SingularAttribute<Invoice, Integer> invoiceUniqueAssembly;
	public static volatile SingularAttribute<Invoice, Integer> invoiceTotalAssembly;
	public static volatile SingularAttribute<Invoice, Integer> invoiceTotalStation;
	public static volatile SingularAttribute<Invoice, BigDecimal> invoiceVoucherCost;
	public static volatile SingularAttribute<Invoice, Integer> invoiceVoucherQty;
}
