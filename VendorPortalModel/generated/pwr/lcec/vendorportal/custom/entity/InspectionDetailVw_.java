package pwr.lcec.vendorportal.custom.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-12-12T16:11:52.693-0500")
@StaticMetamodel(InspectionDetailVw.class)
public class InspectionDetailVw_ {
	public static volatile SingularAttribute<InspectionDetailVw, String> asBuiltBy;
	public static volatile SingularAttribute<InspectionDetailVw, String> asBuiltComments;
	public static volatile SingularAttribute<InspectionDetailVw, Timestamp> asBuiltDt;
	public static volatile SingularAttribute<InspectionDetailVw, Integer> asBuiltQuantity;
	public static volatile SingularAttribute<InspectionDetailVw, String> asBuiltStatusId;
	public static volatile SingularAttribute<InspectionDetailVw, AsBuiltStatus> asBuiltStatus;
	public static volatile SingularAttribute<InspectionDetailVw, String> assembleUnitId;
	public static volatile SingularAttribute<InspectionDetailVw, String> assemblyActionCode;
	public static volatile SingularAttribute<InspectionDetailVw, Timestamp> assemblyCreatedDt;
	public static volatile SingularAttribute<InspectionDetailVw, String> assemblyDescription;
	public static volatile SingularAttribute<InspectionDetailVw, String> assemblyGuid;
	public static volatile SingularAttribute<InspectionDetailVw, Timestamp> assemblyModifiedDt;
	public static volatile SingularAttribute<InspectionDetailVw, Integer> assemblyQuantity;
	public static volatile SingularAttribute<InspectionDetailVw, Integer> assemblyRateGroupId;
	public static volatile SingularAttribute<InspectionDetailVw, String> comment;
	public static volatile SingularAttribute<InspectionDetailVw, Timestamp> inspectionDetailDt;
	public static volatile SingularAttribute<InspectionDetailVw, Integer> inspectionDetailId;
	public static volatile SingularAttribute<InspectionDetailVw, Integer> inspectionId;
	public static volatile SingularAttribute<InspectionDetailVw, Integer> inspectionStatusId;
	public static volatile SingularAttribute<InspectionDetailVw, InspectionStatus> inspectionStatus;
	public static volatile SingularAttribute<InspectionDetailVw, String> lcecNotes;
	public static volatile SingularAttribute<InspectionDetailVw, String> stakingSheetDetailId;
	public static volatile SingularAttribute<InspectionDetailVw, Integer> stakingSheetId;
	public static volatile SingularAttribute<InspectionDetailVw, String> stationDescription;
	public static volatile SingularAttribute<InspectionDetailVw, String> stationId;
	public static volatile SingularAttribute<InspectionDetailVw, String> statusDescription;
	public static volatile SingularAttribute<InspectionDetailVw, String> stStatusRefGuid;
	public static volatile SingularAttribute<InspectionDetailVw, BigDecimal> assemblyAmount;
	public static volatile SingularAttribute<InspectionDetailVw, BigDecimal> asBuiltAmount;
}
