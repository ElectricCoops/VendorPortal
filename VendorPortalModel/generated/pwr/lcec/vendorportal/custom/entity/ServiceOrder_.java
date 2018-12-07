package pwr.lcec.vendorportal.custom.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-07-18T23:46:00.595-0400")
@StaticMetamodel(ServiceOrder.class)
public class ServiceOrder_ {
	public static volatile SingularAttribute<ServiceOrder, String> serviceOrderId;
	public static volatile SingularAttribute<ServiceOrder, String> enterTypeCode;
	public static volatile SingularAttribute<ServiceOrder, String> inspectedBy;
	public static volatile SingularAttribute<ServiceOrder, String> inspectedComment;
	public static volatile SingularAttribute<ServiceOrder, Timestamp> inspectedDt;
	public static volatile SingularAttribute<ServiceOrder, String> invoiceApprovedBy;
	public static volatile SingularAttribute<ServiceOrder, String> invoiceApprovedComments;
	public static volatile SingularAttribute<ServiceOrder, Timestamp> invoiceApprovedDt;
	public static volatile SingularAttribute<ServiceOrder, Integer> invoiceDetailId;
	public static volatile SingularAttribute<ServiceOrder, Integer> invoiceId;
	public static volatile SingularAttribute<ServiceOrder, Integer> invoiceStatusId;
	public static volatile SingularAttribute<ServiceOrder, String> invoiceSubmitGuid;
	public static volatile SingularAttribute<ServiceOrder, Timestamp> neededDt;
	public static volatile SingularAttribute<ServiceOrder, Timestamp> openDt;
	public static volatile SingularAttribute<ServiceOrder, Integer> quantity;
	public static volatile SingularAttribute<ServiceOrder, String> serviceAddress;
	public static volatile SingularAttribute<ServiceOrder, String> serviceCity;
	public static volatile SingularAttribute<ServiceOrder, String> serviceDesc;
	public static volatile SingularAttribute<ServiceOrder, Integer> serviceLocationId;
	public static volatile SingularAttribute<ServiceOrder, String> serviceMapLocation;
	public static volatile SingularAttribute<ServiceOrder, String> servicezip;
	public static volatile SingularAttribute<ServiceOrder, Timestamp> soCloseDt;
	public static volatile SingularAttribute<ServiceOrder, String> soComments;
	public static volatile SingularAttribute<ServiceOrder, String> soCrewId;
	public static volatile SingularAttribute<ServiceOrder, String> soDescription;
	public static volatile SingularAttribute<ServiceOrder, String> soFullName;
	public static volatile SingularAttribute<ServiceOrder, String> soFunction;
	public static volatile SingularAttribute<ServiceOrder, String> soStatCode;
	public static volatile SingularAttribute<ServiceOrder, String> soTypeCode;
	public static volatile SingularAttribute<ServiceOrder, String> soTypeCodeDescription;
	public static volatile SingularAttribute<ServiceOrder, Integer> stakingSheetId;
	public static volatile SingularAttribute<ServiceOrder, String> userName;
	public static volatile SingularAttribute<ServiceOrder, Integer> workFlowId;
	public static volatile SingularAttribute<ServiceOrder, String> workOrderId;
	public static volatile SingularAttribute<ServiceOrder, Integer> inspectionStatusId;
	public static volatile SingularAttribute<ServiceOrder, Integer> inspectionId;
}
