package pwr.lcec.vendorportal.custom.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-21T09:11:42.790-0400")
@StaticMetamodel(Resource.class)
public class Resource_ {
	public static volatile SingularAttribute<Resource, Integer> resourceId;
	public static volatile SingularAttribute<Resource, String> absPersonId;
	public static volatile SingularAttribute<Resource, String> active;
	public static volatile SingularAttribute<Resource, String> cisPersonId;
	public static volatile SingularAttribute<Resource, String> crew;
	public static volatile SingularAttribute<Resource, String> docVaultPersonId;
	public static volatile SingularAttribute<Resource, Timestamp> effectiveBeginDt;
	public static volatile SingularAttribute<Resource, Timestamp> effectiveEndDt;
	public static volatile SingularAttribute<Resource, Integer> employeeNumber;
	public static volatile SingularAttribute<Resource, String> gisPersonId;
	public static volatile SingularAttribute<Resource, String> resourceName;
	public static volatile SingularAttribute<Resource, String> resourceTypeCd;
	public static volatile SingularAttribute<Resource, Integer> userID;
	public static volatile SingularAttribute<Resource, Integer> vpUserID;
	public static volatile SingularAttribute<Resource, String> sourceSystem;
}
