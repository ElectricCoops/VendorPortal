package pwr.lcec.vendorportal.custom.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-08-20T08:53:40.150-0400")
@StaticMetamodel(AlertNotification.class)
public class AlertNotification_ {
	public static volatile SingularAttribute<AlertNotification, Integer> alertNotificationId;
	public static volatile SingularAttribute<AlertNotification, String> body;
	public static volatile SingularAttribute<AlertNotification, Timestamp> effectiveEndDt;
	public static volatile SingularAttribute<AlertNotification, Timestamp> effectiveStartDt;
	public static volatile SingularAttribute<AlertNotification, String> title;
}
