package pwr.lcec.vendorportal.ag.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-08-15T10:01:17.208-0400")
@StaticMetamodel(AGStreetlight.class)
public class AGStreetlight_ {
	public static volatile SingularAttribute<AGStreetlight, Integer> AGStreetlightID;
	public static volatile SingularAttribute<AGStreetlight, Integer> inspectionApproved30Days;
	public static volatile SingularAttribute<AGStreetlight, Integer> inspectionInProgress;
	public static volatile SingularAttribute<AGStreetlight, Integer> inspectionReady;
	public static volatile SingularAttribute<AGStreetlight, Integer> inspectionRejected;
	public static volatile SingularAttribute<AGStreetlight, Integer> invoiceApproved;
	public static volatile SingularAttribute<AGStreetlight, Integer> invoiceRejected;
	public static volatile SingularAttribute<AGStreetlight, Integer> invoiceSubmitted;
	public static volatile SingularAttribute<AGStreetlight, Timestamp> lastSynced;
	public static volatile SingularAttribute<AGStreetlight, Integer> notInspected;
	public static volatile SingularAttribute<AGStreetlight, Integer> notInvoiced;
	public static volatile SingularAttribute<AGStreetlight, Integer> SOCompleted30Days;
	public static volatile SingularAttribute<AGStreetlight, Integer> total30Days;
	public static volatile SingularAttribute<AGStreetlight, String> vendorType;
}
