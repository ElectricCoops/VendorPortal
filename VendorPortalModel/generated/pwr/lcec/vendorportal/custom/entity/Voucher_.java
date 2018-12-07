package pwr.lcec.vendorportal.custom.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-07-17T14:01:23.668-0400")
@StaticMetamodel(Voucher.class)
public class Voucher_ {
	public static volatile SingularAttribute<Voucher, Integer> voucherId;
	public static volatile SingularAttribute<Voucher, BigDecimal> amount;
	public static volatile SingularAttribute<Voucher, String> approvedBy;
	public static volatile SingularAttribute<Voucher, String> approvedComment;
	public static volatile SingularAttribute<Voucher, String> inspectionComment;
	public static volatile SingularAttribute<Voucher, Timestamp> approvedDt;
	public static volatile SingularAttribute<Voucher, Timestamp> createdDt;
	public static volatile SingularAttribute<Voucher, String> crew;
	public static volatile SingularAttribute<Voucher, String> description;
	public static volatile SingularAttribute<Voucher, Integer> GLAccountId;
	public static volatile SingularAttribute<Voucher, Integer> GLAccountIdSplit;
	public static volatile SingularAttribute<Voucher, Integer> inspectionId;
	public static volatile SingularAttribute<Voucher, Integer> inspectionStatusId;
	public static volatile SingularAttribute<Voucher, Integer> invoiceDetailId;
	public static volatile SingularAttribute<Voucher, Integer> invoiceId;
	public static volatile SingularAttribute<Voucher, Integer> invoiceStatusId;
	public static volatile SingularAttribute<Voucher, String> requestor;
	public static volatile SingularAttribute<Voucher, String> serviceOrderId;
	public static volatile SingularAttribute<Voucher, Integer> stakingSheetId;
	public static volatile SingularAttribute<Voucher, String> stationDescription;
	public static volatile SingularAttribute<Voucher, String> submitGuid;
	public static volatile SingularAttribute<Voucher, String> workOrderId;
	public static volatile SingularAttribute<Voucher, InspectionStatus> inspectionStatus;
	public static volatile SingularAttribute<Voucher, InvoiceStatus> invoiceStatus;
}
