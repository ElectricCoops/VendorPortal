package pwr.lcec.vendorportal.ag.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-08-15T10:01:17.202-0400")
@StaticMetamodel(AGStakingSheet.class)
public class AGStakingSheet_ {
	public static volatile SingularAttribute<AGStakingSheet, Integer> AGStakingSheetID;
	public static volatile SingularAttribute<AGStakingSheet, Integer> asBuiltAppealed;
	public static volatile SingularAttribute<AGStakingSheet, Integer> asBuiltCompleted30Days;
	public static volatile SingularAttribute<AGStakingSheet, Integer> asBuiltCorrected;
	public static volatile SingularAttribute<AGStakingSheet, Integer> asbuiltInProgress;
	public static volatile SingularAttribute<AGStakingSheet, Integer> asBuiltNotStarted;
	public static volatile SingularAttribute<AGStakingSheet, Integer> asBuiltRejected;
	public static volatile SingularAttribute<AGStakingSheet, Integer> inspectionApproved30Days;
	public static volatile SingularAttribute<AGStakingSheet, Integer> inspectionInProgress;
	public static volatile SingularAttribute<AGStakingSheet, Integer> inspectionReady;
	public static volatile SingularAttribute<AGStakingSheet, Integer> inspectionRejected;
	public static volatile SingularAttribute<AGStakingSheet, Integer> invoiceApproved;
	public static volatile SingularAttribute<AGStakingSheet, Integer> invoiceRejected;
	public static volatile SingularAttribute<AGStakingSheet, Integer> invoiceSubmitted;
	public static volatile SingularAttribute<AGStakingSheet, Timestamp> lastSynced;
	public static volatile SingularAttribute<AGStakingSheet, Integer> notInspected;
	public static volatile SingularAttribute<AGStakingSheet, Integer> notInvoiced;
	public static volatile SingularAttribute<AGStakingSheet, Integer> total30Days;
	public static volatile SingularAttribute<AGStakingSheet, String> vendorType;
	public static volatile SingularAttribute<AGStakingSheet, Integer> WOCompleted30Days;
}
