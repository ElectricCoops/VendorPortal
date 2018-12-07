package pwr.lcec.vendorportal.custom.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-06-29T11:50:59.794-0400")
@StaticMetamodel(WorkFlowTask.class)
public class WorkFlowTask_ {
	public static volatile SingularAttribute<WorkFlowTask, Integer> workFlowTaskId;
	public static volatile SingularAttribute<WorkFlowTask, String> WFCriticalTask;
	public static volatile SingularAttribute<WorkFlowTask, Timestamp> WFEventDt;
	public static volatile SingularAttribute<WorkFlowTask, String> WFTaskCode;
	public static volatile SingularAttribute<WorkFlowTask, String> WFTaskDescription;
	public static volatile SingularAttribute<WorkFlowTask, Integer> WFTasksCtr;
	public static volatile SingularAttribute<WorkFlowTask, String> workEventStatusId;
	public static volatile SingularAttribute<WorkFlowTask, Integer> workFlowId;
	public static volatile SingularAttribute<WorkFlowTask, String> workFlowTaskSeq;
	public static volatile SingularAttribute<WorkFlowTask, String> workGroup;
	public static volatile SingularAttribute<WorkFlowTask, String> workOrderId;
	public static volatile SingularAttribute<WorkFlowTask, WorkEventStatus> workEventStatus;
}
