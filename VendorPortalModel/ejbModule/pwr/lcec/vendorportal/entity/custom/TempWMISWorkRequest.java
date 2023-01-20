package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="TempWMISWorkRequest.findAll", query="SELECT t FROM TempWMISWorkRequest t")
public class TempWMISWorkRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="WorkRequest")
	private String workRequest;

	@Column(name="SyncDate")
	private Timestamp syncDate;

	public TempWMISWorkRequest() {
	}

	public String getWorkRequest() {
		return this.workRequest;
	}

	public void setWorkRequest(String workRequest) {
		this.workRequest = workRequest;
	}

	public Timestamp getSyncDate() {
		return this.syncDate;
	}

	public void setSyncDate(Timestamp syncDate) {
		this.syncDate = syncDate;
	}

}
