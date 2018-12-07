package pwr.lcec.vendorportal.custom.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-07-10T20:57:55.192-0400")
@StaticMetamodel(InvoiceGLSummaryVw.class)
public class InvoiceGLSummaryVw_ {
	public static volatile SingularAttribute<InvoiceGLSummaryVw, Integer> asBuiltQuantity;
	public static volatile SingularAttribute<InvoiceGLSummaryVw, String> assemblyActionCode;
	public static volatile SingularAttribute<InvoiceGLSummaryVw, String> assemblyDescription;
	public static volatile SingularAttribute<InvoiceGLSummaryVw, String> assemblyGuid;
	public static volatile SingularAttribute<InvoiceGLSummaryVw, Integer> assemblyQuantity;
	public static volatile SingularAttribute<InvoiceGLSummaryVw, Integer> assemblyRateGroupId;
	public static volatile SingularAttribute<InvoiceGLSummaryVw, BigDecimal> constCost;
	public static volatile SingularAttribute<InvoiceGLSummaryVw, BigDecimal> constGLAccount;
	public static volatile SingularAttribute<InvoiceGLSummaryVw, Integer> invoiceId;
	public static volatile SingularAttribute<InvoiceGLSummaryVw, BigDecimal> retireCost;
	public static volatile SingularAttribute<InvoiceGLSummaryVw, BigDecimal> retireGlAccount;
	public static volatile SingularAttribute<InvoiceGLSummaryVw, String> stakingSheetDetailId;
	public static volatile SingularAttribute<InvoiceGLSummaryVw, Integer> stakingSheetId;
	public static volatile SingularAttribute<InvoiceGLSummaryVw, String> stationDescription;
	public static volatile SingularAttribute<InvoiceGLSummaryVw, String> invoiceStatusId;
	public static volatile SingularAttribute<InvoiceGLSummaryVw, String> invoiceStatus;
	public static volatile SingularAttribute<InvoiceGLSummaryVw, String> invoiceApprovedComment;
}
