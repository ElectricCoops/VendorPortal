package pwr.lcec.vendorportal.custom.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-07-18T11:13:52.856-0400")
@StaticMetamodel(WorkFlow.class)
public class WorkFlow_ {
	public static volatile SingularAttribute<WorkFlow, Integer> workFlowId;
	public static volatile SingularAttribute<WorkFlow, String> accountId;
	public static volatile SingularAttribute<WorkFlow, Integer> assignedVendorId;
	public static volatile SingularAttribute<WorkFlow, BigDecimal> designCost;
	public static volatile SingularAttribute<WorkFlow, Integer> designTotalAssembly;
	public static volatile SingularAttribute<WorkFlow, Integer> designTotalStation;
	public static volatile SingularAttribute<WorkFlow, Integer> designUniqueAssembly;
	public static volatile SingularAttribute<WorkFlow, BigDecimal> invoiceCost;
	public static volatile SingularAttribute<WorkFlow, Integer> invoiceTotalAssembly;
	public static volatile SingularAttribute<WorkFlow, Integer> invoiceTotalStation;
	public static volatile SingularAttribute<WorkFlow, Integer> invoiceUniqueAssembly;
	public static volatile SingularAttribute<WorkFlow, Timestamp> neededDt;
	public static volatile SingularAttribute<WorkFlow, Timestamp> openDt;
	public static volatile SingularAttribute<WorkFlow, String> overallAsBuiltStatusId;
	public static volatile SingularAttribute<WorkFlow, Integer> overallInspectionStatusId;
	public static volatile SingularAttribute<WorkFlow, Integer> overallInvoiceStatusId;
	public static volatile SingularAttribute<WorkFlow, String> overallPaymentStatusId;
	public static volatile SingularAttribute<WorkFlow, Integer> resourceId;
	public static volatile SingularAttribute<WorkFlow, String> resourceName;
	public static volatile SingularAttribute<WorkFlow, Integer> serviceLocationId;
	public static volatile SingularAttribute<WorkFlow, String> serviceOrderId;
	public static volatile SingularAttribute<WorkFlow, String> serviceOrderPriority;
	public static volatile SingularAttribute<WorkFlow, String> serviceOrderType;
	public static volatile SingularAttribute<WorkFlow, String> WONotFound;
	public static volatile SingularAttribute<WorkFlow, Timestamp> workEventDt;
	public static volatile SingularAttribute<WorkFlow, String> workEventStatusId;
	public static volatile SingularAttribute<WorkFlow, String> workGroup;
	public static volatile SingularAttribute<WorkFlow, String> workOrderId;
	public static volatile SingularAttribute<WorkFlow, String> workOrderName;
	public static volatile SingularAttribute<WorkFlow, BigDecimal> invoiceVoucherCost;
	public static volatile SingularAttribute<WorkFlow, Integer> invoiceVoucherStation;
	public static volatile SingularAttribute<WorkFlow, Integer> invoiceVoucherQty;
	public static volatile SingularAttribute<WorkFlow, InspectionStatus> inspectionStatus;
	public static volatile SingularAttribute<WorkFlow, InvoiceStatus> invoiceStatus;
	public static volatile SingularAttribute<WorkFlow, AsBuiltStatus> asBuiltStatus;
}
