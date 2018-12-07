package pwr.lcec.vendorportal.custom.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-06-29T11:50:59.683-0400")
@StaticMetamodel(RateGroup.class)
public class RateGroup_ {
	public static volatile SingularAttribute<RateGroup, Integer> rateGroupId;
	public static volatile SingularAttribute<RateGroup, String> description;
	public static volatile SingularAttribute<RateGroup, Timestamp> effectiveEndDt;
	public static volatile SingularAttribute<RateGroup, Timestamp> effectiveStartDt;
	public static volatile SingularAttribute<RateGroup, String> fixedRateSW;
	public static volatile SingularAttribute<RateGroup, String> rateGroupName;
	public static volatile SingularAttribute<RateGroup, String> statusCode;
	public static volatile SingularAttribute<RateGroup, Integer> vendorId;
}
