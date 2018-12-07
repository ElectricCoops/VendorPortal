package pwr.lcec.vendorportal.custom.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-06-29T11:50:59.642-0400")
@StaticMetamodel(InspectionDetail.class)
public class InspectionDetail_ {
	public static volatile SingularAttribute<InspectionDetail, Integer> inspectionDetailId;
	public static volatile SingularAttribute<InspectionDetail, String> assembleUnitId;
	public static volatile SingularAttribute<InspectionDetail, String> comment;
	public static volatile SingularAttribute<InspectionDetail, Timestamp> inspectionDetailDt;
	public static volatile SingularAttribute<InspectionDetail, Integer> inspectionId;
	public static volatile SingularAttribute<InspectionDetail, Integer> inspectionStatusId;
	public static volatile SingularAttribute<InspectionDetail, String> stakingSheetDetailId;
	public static volatile SingularAttribute<InspectionDetail, String> stationId;
}
