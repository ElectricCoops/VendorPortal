package pwr.lcec.vendorportal.custom.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-10T11:27:57.513-0400")
@StaticMetamodel(InvoiceDetail.class)
public class InvoiceDetail_ {
	public static volatile SingularAttribute<InvoiceDetail, Integer> invoiceDetailId;
	public static volatile SingularAttribute<InvoiceDetail, BigDecimal> amount;
	public static volatile SingularAttribute<InvoiceDetail, String> apInvDtlId;
	public static volatile SingularAttribute<InvoiceDetail, String> categoryCode;
	public static volatile SingularAttribute<InvoiceDetail, String> description;
	public static volatile SingularAttribute<InvoiceDetail, String> GL_Code;
	public static volatile SingularAttribute<InvoiceDetail, Integer> invoiceId;
	public static volatile SingularAttribute<InvoiceDetail, Integer> quantity;
	public static volatile SingularAttribute<InvoiceDetail, BigDecimal> gLDepartment;
	public static volatile SingularAttribute<InvoiceDetail, BigDecimal> gLActivity;
}
