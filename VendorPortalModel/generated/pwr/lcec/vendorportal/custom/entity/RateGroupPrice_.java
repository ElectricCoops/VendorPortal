package pwr.lcec.vendorportal.custom.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-10T11:26:39.269-0400")
@StaticMetamodel(RateGroupPrice.class)
public class RateGroupPrice_ {
	public static volatile SingularAttribute<RateGroupPrice, Integer> rateGroupPriceId;
	public static volatile SingularAttribute<RateGroupPrice, String> assemblyAction;
	public static volatile SingularAttribute<RateGroupPrice, String> assemblyDescription;
	public static volatile SingularAttribute<RateGroupPrice, String> assemblyGuid;
	public static volatile SingularAttribute<RateGroupPrice, String> assemblySource;
	public static volatile SingularAttribute<RateGroupPrice, Timestamp> effectiveEndDt;
	public static volatile SingularAttribute<RateGroupPrice, Timestamp> effectiveStartDt;
	public static volatile SingularAttribute<RateGroupPrice, String> fixedCost;
	public static volatile SingularAttribute<RateGroupPrice, BigDecimal> GL_Department;
	public static volatile SingularAttribute<RateGroupPrice, Integer> GLConstAccountId;
	public static volatile SingularAttribute<RateGroupPrice, Integer> GLRetireAccountId;
	public static volatile SingularAttribute<RateGroupPrice, BigDecimal> GLActivity;
	public static volatile SingularAttribute<RateGroupPrice, BigDecimal> GLPercent;
	public static volatile SingularAttribute<RateGroupPrice, String> laborConstCost;
	public static volatile SingularAttribute<RateGroupPrice, BigDecimal> laborConstHours;
	public static volatile SingularAttribute<RateGroupPrice, String> laborRetireCost;
	public static volatile SingularAttribute<RateGroupPrice, BigDecimal> laborRetireHours;
	public static volatile SingularAttribute<RateGroupPrice, Integer> rateGroupId;
	public static volatile SingularAttribute<RateGroupPrice, BigDecimal> gLRetireActivity;
	public static volatile SingularAttribute<RateGroupPrice, BigDecimal> gLRetireDepartment;
}
