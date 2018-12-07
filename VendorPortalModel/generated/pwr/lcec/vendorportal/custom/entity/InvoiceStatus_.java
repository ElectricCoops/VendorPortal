package pwr.lcec.vendorportal.custom.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-27T10:24:09.463-0400")
@StaticMetamodel(InvoiceStatus.class)
public class InvoiceStatus_ {
	public static volatile SingularAttribute<InvoiceStatus, Integer> invoiceStatusId;
	public static volatile SingularAttribute<InvoiceStatus, Boolean> active;
	public static volatile SingularAttribute<InvoiceStatus, String> description;
	public static volatile SingularAttribute<InvoiceStatus, String> transactionStatus;
}
