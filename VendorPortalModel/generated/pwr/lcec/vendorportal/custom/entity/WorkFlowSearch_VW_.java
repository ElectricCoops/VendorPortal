package pwr.lcec.vendorportal.custom.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-14T09:17:45.452-0400")
@StaticMetamodel(WorkFlowSearch_VW.class)
public class WorkFlowSearch_VW_ {
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> accountId;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> assignedVendorId;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> workEventStatus;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> enterTypeCode;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> LCECComments;
	public static volatile SingularAttribute<WorkFlowSearch_VW, Timestamp> neededDt;
	public static volatile SingularAttribute<WorkFlowSearch_VW, Timestamp> openDt;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> overallAsBuiltStatusId;
	public static volatile SingularAttribute<WorkFlowSearch_VW, Integer> resourceId;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> resourceName;
	public static volatile SingularAttribute<WorkFlowSearch_VW, Integer> serviceLocationId;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> serviceOrderId;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> serviceOrderPriority;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> serviceOrderType;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> soFullName;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> soFunction;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> soStatCode;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> soTypeCode;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> soTypeCodeDescription;
	public static volatile SingularAttribute<WorkFlowSearch_VW, Integer> stakingSheetId;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> userName;
	public static volatile SingularAttribute<WorkFlowSearch_VW, Timestamp> workEventDt;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> workEventStatusId;
	public static volatile SingularAttribute<WorkFlowSearch_VW, Integer> workFlowId;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> workGroup;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> workOrderId;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> workOrderName;
	public static volatile SingularAttribute<WorkFlowSearch_VW, Integer> overallInspectionStatusId;
	public static volatile SingularAttribute<WorkFlowSearch_VW, Integer> overallInvoiceStatusId;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> overallAsBuiltStatus;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> overallInspectionStatus;
	public static volatile SingularAttribute<WorkFlowSearch_VW, String> overallInvoiceStatus;
}
