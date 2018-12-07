package pwr.lcec.vendorportal.custom.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-07-06T16:23:50.053-0400")
@StaticMetamodel(StakingSheetDetail.class)
public class StakingSheetDetail_ {
	public static volatile SingularAttribute<StakingSheetDetail, String> stakingSheetDetailId;
	public static volatile SingularAttribute<StakingSheetDetail, String> asBuiltBy;
	public static volatile SingularAttribute<StakingSheetDetail, String> asBuiltComments;
	public static volatile SingularAttribute<StakingSheetDetail, Timestamp> asBuiltDt;
	public static volatile SingularAttribute<StakingSheetDetail, Integer> asBuiltQuantity;
	public static volatile SingularAttribute<StakingSheetDetail, Integer> asBuiltStatusId;
	public static volatile SingularAttribute<StakingSheetDetail, AsBuiltStatus> asBuiltStatus;
	public static volatile SingularAttribute<StakingSheetDetail, String> assemblyActionCode;
	public static volatile SingularAttribute<StakingSheetDetail, Timestamp> assemblyCreatedDt;
	public static volatile SingularAttribute<StakingSheetDetail, String> assemblyDescription;
	public static volatile SingularAttribute<StakingSheetDetail, String> assemblyGuid;
	public static volatile SingularAttribute<StakingSheetDetail, Timestamp> assemblyModifiedDt;
	public static volatile SingularAttribute<StakingSheetDetail, Integer> assemblyQuantity;
	public static volatile SingularAttribute<StakingSheetDetail, Integer> assemblyRateGroupId;
	public static volatile SingularAttribute<StakingSheetDetail, RateGroupPrice> rateGroupPrice;
	public static volatile SingularAttribute<StakingSheetDetail, String> currentInspectedDetailBy;
	public static volatile SingularAttribute<StakingSheetDetail, Timestamp> currentInspectionDetailDt;
	public static volatile SingularAttribute<StakingSheetDetail, Integer> currentInspectionDetailId;
	public static volatile SingularAttribute<StakingSheetDetail, Integer> currentInspectionDetailStatusId;
	public static volatile SingularAttribute<StakingSheetDetail, InspectionStatus> inspectionStatus;
	public static volatile SingularAttribute<StakingSheetDetail, String> currentInspectorDetailComments;
	public static volatile SingularAttribute<StakingSheetDetail, Integer> invoiceId;
	public static volatile SingularAttribute<StakingSheetDetail, Integer> invoiceStatusId;
	public static volatile SingularAttribute<StakingSheetDetail, String> lcecNotes;
	public static volatile SingularAttribute<StakingSheetDetail, String> stakingSheetId;
	public static volatile SingularAttribute<StakingSheetDetail, String> stationDescription;
	public static volatile SingularAttribute<StakingSheetDetail, String> statusDescription;
	public static volatile SingularAttribute<StakingSheetDetail, String> stStatusRefGuid;
	public static volatile SingularAttribute<StakingSheetDetail, String> stakingSource;
	public static volatile SingularAttribute<StakingSheetDetail, Integer> invoiceDetailId;
	public static volatile SingularAttribute<StakingSheetDetail, String> invoiceSubmitGuid;
	public static volatile SingularAttribute<StakingSheetDetail, Integer> glAccountId;
	public static volatile SingularAttribute<StakingSheetDetail, InvoiceStatus> invoiceStatus;
	public static volatile SingularAttribute<StakingSheetDetail, Invoice> invoice;
	public static volatile SingularAttribute<StakingSheetDetail, Resource> resource;
	public static volatile SingularAttribute<StakingSheetDetail, String> invoiceApprovedBy;
	public static volatile SingularAttribute<StakingSheetDetail, Timestamp> invoiceApprovedDt;
	public static volatile SingularAttribute<StakingSheetDetail, String> invoiceApprovedComment;
	public static volatile SingularAttribute<StakingSheetDetail, InvoiceDetail> invoiceDetail;
}
