package pwr.lcec.vendor.web.model;

public class Workflow {

	private String task;
	private String date;
	private String person;
	private String notes;

	public Workflow(String task, String date, String person, String notes) {
		this.task = task;
		this.date = date;
		this.person = person;
		this.notes = notes;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
