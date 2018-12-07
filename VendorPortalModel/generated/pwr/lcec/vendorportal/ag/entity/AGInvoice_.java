package pwr.lcec.vendorportal.ag.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-08-17T09:59:03.148-0400")
@StaticMetamodel(AGInvoice.class)
public class AGInvoice_ {
	public static volatile SingularAttribute<AGInvoice, Integer> AGInvoiceID;
	public static volatile SingularAttribute<AGInvoice, Integer> invoiceApproved;
	public static volatile SingularAttribute<AGInvoice, Integer> invoiceRejected;
	public static volatile SingularAttribute<AGInvoice, Integer> invoiceSubmitted;
	public static volatile SingularAttribute<AGInvoice, Timestamp> lastSynced;
	public static volatile SingularAttribute<AGInvoice, Integer> notInvoiced;
	public static volatile SingularAttribute<AGInvoice, Integer> total30Days;
	public static volatile SingularAttribute<AGInvoice, String> vendorType;
}
