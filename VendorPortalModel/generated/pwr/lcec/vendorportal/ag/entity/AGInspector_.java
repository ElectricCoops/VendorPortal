package pwr.lcec.vendorportal.ag.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-08-15T10:01:17.188-0400")
@StaticMetamodel(AGInspector.class)
public class AGInspector_ {
	public static volatile SingularAttribute<AGInspector, Integer> AGInspectorID;
	public static volatile SingularAttribute<AGInspector, Integer> inspectionApproved30Days;
	public static volatile SingularAttribute<AGInspector, Integer> inspectionInProgress;
	public static volatile SingularAttribute<AGInspector, Integer> inspectionReady;
	public static volatile SingularAttribute<AGInspector, Integer> inspectionRejected;
	public static volatile SingularAttribute<AGInspector, Integer> inspectorID;
	public static volatile SingularAttribute<AGInspector, String> inspectorName;
	public static volatile SingularAttribute<AGInspector, Timestamp> lastSynced;
	public static volatile SingularAttribute<AGInspector, Integer> notInspected;
	public static volatile SingularAttribute<AGInspector, Integer> total30Days;
}
