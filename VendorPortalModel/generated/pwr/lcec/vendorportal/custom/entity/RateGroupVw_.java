package pwr.lcec.vendorportal.custom.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-06-29T11:50:59.697-0400")
@StaticMetamodel(RateGroupVw.class)
public class RateGroupVw_ {
	public static volatile SingularAttribute<RateGroupVw, String> assemblyGuid;
	public static volatile SingularAttribute<RateGroupVw, String> description;
	public static volatile SingularAttribute<RateGroupVw, Timestamp> effectiveEndDt;
	public static volatile SingularAttribute<RateGroupVw, Timestamp> effectiveStartDt;
	public static volatile SingularAttribute<RateGroupVw, String> fixedCost;
	public static volatile SingularAttribute<RateGroupVw, String> laborConstCost;
	public static volatile SingularAttribute<RateGroupVw, BigDecimal> laborConstHours;
	public static volatile SingularAttribute<RateGroupVw, String> laborRetireCost;
	public static volatile SingularAttribute<RateGroupVw, BigDecimal> laborRetireHours;
	public static volatile SingularAttribute<RateGroupVw, String> rateGroupName;
	public static volatile SingularAttribute<RateGroupVw, Integer> rateGroupPriceId;
}
