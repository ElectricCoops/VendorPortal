package pwr.lcec.vendorportal.custom.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-18T16:35:18.670-0400")
@StaticMetamodel(StreetLightSearchVw.class)
public class StreetLightSearchVw_ {
	public static volatile SingularAttribute<StreetLightSearchVw, String> fixedCost;
	public static volatile SingularAttribute<StreetLightSearchVw, Integer> GLConstAccountId;
	public static volatile SingularAttribute<StreetLightSearchVw, String> GLConstDescription;
	public static volatile SingularAttribute<StreetLightSearchVw, BigDecimal> GLPercent;
	public static volatile SingularAttribute<StreetLightSearchVw, Integer> GLRetireAccountId;
	public static volatile SingularAttribute<StreetLightSearchVw, String> GLRetireDescription;
	public static volatile SingularAttribute<StreetLightSearchVw, String> inspectedBy;
	public static volatile SingularAttribute<StreetLightSearchVw, String> inspectedComment;
	public static volatile SingularAttribute<StreetLightSearchVw, Timestamp> inspectedDt;
	public static volatile SingularAttribute<StreetLightSearchVw, Integer> inspectionId;
	public static volatile SingularAttribute<StreetLightSearchVw, Integer> inspectionStatusId;
	public static volatile SingularAttribute<StreetLightSearchVw, String> invoiceApprovedBy;
	public static volatile SingularAttribute<StreetLightSearchVw, String> invoiceApprovedComments;
	public static volatile SingularAttribute<StreetLightSearchVw, Timestamp> invoiceApprovedDt;
	public static volatile SingularAttribute<StreetLightSearchVw, Integer> invoiceId;
	public static volatile SingularAttribute<StreetLightSearchVw, String> invoiceStatus;
	public static volatile SingularAttribute<StreetLightSearchVw, Integer> quantity;
	public static volatile SingularAttribute<StreetLightSearchVw, String> serviceMapLocation;
	public static volatile SingularAttribute<StreetLightSearchVw, String> serviceOrderId;
	public static volatile SingularAttribute<StreetLightSearchVw, Timestamp> soCloseDt;
	public static volatile SingularAttribute<StreetLightSearchVw, String> soCrewId;
	public static volatile SingularAttribute<StreetLightSearchVw, String> soStatCode;
	public static volatile SingularAttribute<StreetLightSearchVw, String> soTypeCode;
	public static volatile SingularAttribute<StreetLightSearchVw, String> vendorName;
	public static volatile SingularAttribute<StreetLightSearchVw, String> vendorReference;
	public static volatile SingularAttribute<StreetLightSearchVw, String> workGroup;
	public static volatile SingularAttribute<StreetLightSearchVw, String> workOrderId;
	public static volatile SingularAttribute<StreetLightSearchVw, Integer> invoiceStatusId;
	public static volatile SingularAttribute<StreetLightSearchVw, InspectionStatus> inspectionStatus;
}
