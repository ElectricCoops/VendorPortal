package pwr.lcec.vendorportal.ag.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-08-15T10:01:17.147-0400")
@StaticMetamodel(AGInspection.class)
public class AGInspection_ {
	public static volatile SingularAttribute<AGInspection, Integer> AGInspectionID;
	public static volatile SingularAttribute<AGInspection, Integer> inspectionApproved30Days;
	public static volatile SingularAttribute<AGInspection, Integer> inspectionInProgress;
	public static volatile SingularAttribute<AGInspection, Integer> inspectionReady;
	public static volatile SingularAttribute<AGInspection, Integer> inspectionRejected;
	public static volatile SingularAttribute<AGInspection, Timestamp> lastSynced;
	public static volatile SingularAttribute<AGInspection, Integer> notInspected;
	public static volatile SingularAttribute<AGInspection, Integer> total30Days;
	public static volatile SingularAttribute<AGInspection, String> vendorType;
}
