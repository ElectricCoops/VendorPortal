package pwr.lcec.vendorportal.custom.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-07-03T15:36:39.734-0400")
@StaticMetamodel(RateGroupPriceDistinctVw.class)
public class RateGroupPriceDistinctVw_ {
	public static volatile SingularAttribute<RateGroupPriceDistinctVw, String> assemblyAction;
	public static volatile SingularAttribute<RateGroupPriceDistinctVw, String> assemblyDescription;
	public static volatile SingularAttribute<RateGroupPriceDistinctVw, String> assemblyGuid;
	public static volatile SingularAttribute<RateGroupPriceDistinctVw, String> assemblySource;
	public static volatile SingularAttribute<RateGroupPriceDistinctVw, Timestamp> effectiveEndDt;
	public static volatile SingularAttribute<RateGroupPriceDistinctVw, Timestamp> effectiveStartDt;
	public static volatile SingularAttribute<RateGroupPriceDistinctVw, String> fixedCost;
	public static volatile SingularAttribute<RateGroupPriceDistinctVw, BigDecimal> GL_Department;
	public static volatile SingularAttribute<RateGroupPriceDistinctVw, BigDecimal> GLActivity;
	public static volatile SingularAttribute<RateGroupPriceDistinctVw, BigDecimal> GLPercent;
	public static volatile SingularAttribute<RateGroupPriceDistinctVw, String> laborConstCost;
	public static volatile SingularAttribute<RateGroupPriceDistinctVw, BigDecimal> laborConstHours;
	public static volatile SingularAttribute<RateGroupPriceDistinctVw, String> laborRetireCost;
	public static volatile SingularAttribute<RateGroupPriceDistinctVw, BigDecimal> laborRetireHours;
	public static volatile SingularAttribute<RateGroupPriceDistinctVw, Integer> rateGroupId;
	public static volatile SingularAttribute<RateGroupPriceDistinctVw, Integer> rateGroupPriceId;
	public static volatile SingularAttribute<RateGroupPriceDistinctVw, Integer> GLConstAccountId;
	public static volatile SingularAttribute<RateGroupPriceDistinctVw, Integer> GLRetireAccountId;
}
