package pwr.lcec.vendorportal.entity.userpref;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the UserPrefProjectWorkflow database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="UserPrefProjectWorkflow.findAll", query="SELECT u FROM UserPrefProjectWorkflow u"),
	@NamedQuery(name="UserPrefProjectWorkflow.findProjectWorkFlowPrefByUserId", query="SELECT u FROM UserPrefProjectWorkflow u WHERE u.userId = :userId")
})
@Table(schema="sec")
public class UserPrefProjectWorkflow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="UserPrefId")
	private int userPrefId;

	@Column(name="BudgetedAmount")
	private boolean budgetedAmount;

	@Column(name="BuiltAmount")
	private boolean builtAmount;

	@Column(name="DesignedAmount")
	private boolean designedAmount;

	@Column(name="EstimatedAmount")
	private boolean estimatedAmount;

	@Column(name="InvoicedAmount")
	private boolean invoicedAmount;

	@Column(name="PaidAmount")
	private boolean paidAmount;

	@Column(name="ProjectWorkFlowId")
	private boolean projectWorkFlowId;

	@Column(name="ServiceOrderId")
	private boolean serviceOrderId;

	@Column(name="ServiceOrderType")
	private boolean serviceOrderType;

	@Column(name="TotalAmount")
	private boolean totalAmount;

	@Column(name="UserId")
	private int userId;

	@Column(name="ViewDefault")
	private boolean viewDefault;

	@Column(name="ViewName")
	private String viewName;

	@Column(name="WorkEventDt")
	private boolean workEventDt;

	@Column(name="WorkEventStatusId")
	private boolean workEventStatusId;

	@Column(name="WorkFlowId")
	private boolean workFlowId;

	@Column(name="WorkGroup")
	private boolean workGroup;

	@Column(name="WorkOrderId")
	private boolean workOrderId;

	@Column(name="WorkOrderName")
	private boolean workOrderName;

	public UserPrefProjectWorkflow() {
	}

	public UserPrefProjectWorkflow(boolean budgetedAmount, boolean builtAmount, boolean designedAmount,
			boolean estimatedAmount, boolean invoicedAmount, boolean paidAmount, boolean projectWorkFlowId,
			boolean serviceOrderId, boolean serviceOrderType, boolean totalAmount, int userId, boolean viewDefault,
			String viewName, boolean workEventDt, boolean workEventStatusId, boolean workFlowId, boolean workGroup,
			boolean workOrderId, boolean workOrderName) {
		this.budgetedAmount = budgetedAmount;
		this.builtAmount = builtAmount;
		this.designedAmount = designedAmount;
		this.estimatedAmount = estimatedAmount;
		this.invoicedAmount = invoicedAmount;
		this.paidAmount = paidAmount;
		this.projectWorkFlowId = projectWorkFlowId;
		this.serviceOrderId = serviceOrderId;
		this.serviceOrderType = serviceOrderType;
		this.totalAmount = totalAmount;
		this.userId = userId;
		this.viewDefault = viewDefault;
		this.viewName = viewName;
		this.workEventDt = workEventDt;
		this.workEventStatusId = workEventStatusId;
		this.workFlowId = workFlowId;
		this.workGroup = workGroup;
		this.workOrderId = workOrderId;
		this.workOrderName = workOrderName;
	}



	public int getUserPrefId() {
		return this.userPrefId;
	}

	public void setUserPrefId(int userPrefId) {
		this.userPrefId = userPrefId;
	}

	public boolean getBudgetedAmount() {
		return this.budgetedAmount;
	}

	public void setBudgetedAmount(boolean budgetedAmount) {
		this.budgetedAmount = budgetedAmount;
	}

	public boolean getBuiltAmount() {
		return this.builtAmount;
	}

	public void setBuiltAmount(boolean builtAmount) {
		this.builtAmount = builtAmount;
	}

	public boolean getDesignedAmount() {
		return this.designedAmount;
	}

	public void setDesignedAmount(boolean designedAmount) {
		this.designedAmount = designedAmount;
	}

	public boolean getEstimatedAmount() {
		return this.estimatedAmount;
	}

	public void setEstimatedAmount(boolean estimatedAmount) {
		this.estimatedAmount = estimatedAmount;
	}

	public boolean getInvoicedAmount() {
		return this.invoicedAmount;
	}

	public void setInvoicedAmount(boolean invoicedAmount) {
		this.invoicedAmount = invoicedAmount;
	}

	public boolean getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(boolean paidAmount) {
		this.paidAmount = paidAmount;
	}

	public boolean getProjectWorkFlowId() {
		return this.projectWorkFlowId;
	}

	public void setProjectWorkFlowId(boolean projectWorkFlowId) {
		this.projectWorkFlowId = projectWorkFlowId;
	}

	public boolean getServiceOrderId() {
		return this.serviceOrderId;
	}

	public void setServiceOrderId(boolean serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}

	public boolean getServiceOrderType() {
		return this.serviceOrderType;
	}

	public void setServiceOrderType(boolean serviceOrderType) {
		this.serviceOrderType = serviceOrderType;
	}

	public boolean getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(boolean totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean getViewDefault() {
		return this.viewDefault;
	}

	public void setViewDefault(boolean viewDefault) {
		this.viewDefault = viewDefault;
	}

	public String getViewName() {
		return this.viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public boolean getWorkEventDt() {
		return this.workEventDt;
	}

	public void setWorkEventDt(boolean workEventDt) {
		this.workEventDt = workEventDt;
	}

	public boolean getWorkEventStatusId() {
		return this.workEventStatusId;
	}

	public void setWorkEventStatusId(boolean workEventStatusId) {
		this.workEventStatusId = workEventStatusId;
	}

	public boolean getWorkFlowId() {
		return this.workFlowId;
	}

	public void setWorkFlowId(boolean workFlowId) {
		this.workFlowId = workFlowId;
	}

	public boolean getWorkGroup() {
		return this.workGroup;
	}

	public void setWorkGroup(boolean workGroup) {
		this.workGroup = workGroup;
	}

	public boolean getWorkOrderId() {
		return this.workOrderId;
	}

	public void setWorkOrderId(boolean workOrderId) {
		this.workOrderId = workOrderId;
	}

	public boolean getWorkOrderName() {
		return this.workOrderName;
	}

	public void setWorkOrderName(boolean workOrderName) {
		this.workOrderName = workOrderName;
	}

}