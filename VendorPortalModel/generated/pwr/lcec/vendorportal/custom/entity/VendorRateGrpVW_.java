package pwr.lcec.vendorportal.custom.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-06-29T11:50:59.754-0400")
@StaticMetamodel(VendorRateGrpVW.class)
public class VendorRateGrpVW_ {
	public static volatile SingularAttribute<VendorRateGrpVW, VendorRateGrpVWPK> id;
	public static volatile SingularAttribute<VendorRateGrpVW, String> assemblyGuid;
	public static volatile SingularAttribute<VendorRateGrpVW, String> comments;
	public static volatile SingularAttribute<VendorRateGrpVW, String> description;
	public static volatile SingularAttribute<VendorRateGrpVW, Timestamp> effectiveEndDt;
	public static volatile SingularAttribute<VendorRateGrpVW, Timestamp> effectiveStartDt;
	public static volatile SingularAttribute<VendorRateGrpVW, Boolean> energized;
	public static volatile SingularAttribute<VendorRateGrpVW, String> fixedCost;
	public static volatile SingularAttribute<VendorRateGrpVW, String> fixedRateSW;
	public static volatile SingularAttribute<VendorRateGrpVW, String> laborCost;
	public static volatile SingularAttribute<VendorRateGrpVW, BigDecimal> laborHours;
	public static volatile SingularAttribute<VendorRateGrpVW, String> rateGroupId;
	public static volatile SingularAttribute<VendorRateGrpVW, String> statusCode;
	public static volatile SingularAttribute<VendorRateGrpVW, Integer> vendorId;
	public static volatile SingularAttribute<VendorRateGrpVW, String> vendorname;
}
