package pwr.lcec.vendorportal.custom.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-07-18T23:46:00.523-0400")
@StaticMetamodel(Inspection.class)
public class Inspection_ {
	public static volatile SingularAttribute<Inspection, Integer> inspectionId;
	public static volatile SingularAttribute<Inspection, String> comments;
	public static volatile SingularAttribute<Inspection, Integer> inspectedBy;
	public static volatile SingularAttribute<Inspection, Timestamp> inspectionDt;
	public static volatile SingularAttribute<Inspection, Integer> inspectionStatusId;
	public static volatile SingularAttribute<Inspection, String> serviceOrderId;
	public static volatile SingularAttribute<Inspection, Integer> workFlowId;
	public static volatile SingularAttribute<Inspection, String> workOrderId;
	public static volatile SingularAttribute<Inspection, String> inspectionType;
	public static volatile SingularAttribute<Inspection, InspectionStatus> inspectionStatus;
	public static volatile SingularAttribute<Inspection, Resource> resource;
	public static volatile SingularAttribute<Inspection, WorkFlow> workFlow;
}
