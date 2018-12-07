package pwr.lcec.vendorportal.custom.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-07-03T15:23:46.239-0400")
@StaticMetamodel(WorkOrderAggVw.class)
public class WorkOrderAggVw_ {
	public static volatile SingularAttribute<WorkOrderAggVw, WorkOrderAggVwPk> id;
	public static volatile SingularAttribute<WorkOrderAggVw, BigDecimal> cost;
	public static volatile SingularAttribute<WorkOrderAggVw, Integer> station;
	public static volatile SingularAttribute<WorkOrderAggVw, Integer> totalAssembly;
	public static volatile SingularAttribute<WorkOrderAggVw, String> type;
	public static volatile SingularAttribute<WorkOrderAggVw, Integer> uniqueAssembly;
	public static volatile SingularAttribute<WorkOrderAggVw, Integer> workFlowId;
}
