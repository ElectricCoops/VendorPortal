package pwr.lcec.vendor.web.model;

public class Inspection {

	private String inspId;
	private String date;
	private String inspector;
	private String status;
	private String comments;
	
	public Inspection() {
		
	}
	
	
	public Inspection(String inspId, String date, String inspector, String status, String comments) {
		this.inspId = inspId;
		this.date = date;
		this.inspector = inspector;
		this.status = status;
		this.comments = comments;
	}
	public String getInspId() {
		return inspId;
	}
	public void setInspId(String inspId) {
		this.inspId = inspId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getInspector() {
		return inspector;
	}
	public void setInspector(String inspector) {
		this.inspector = inspector;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
