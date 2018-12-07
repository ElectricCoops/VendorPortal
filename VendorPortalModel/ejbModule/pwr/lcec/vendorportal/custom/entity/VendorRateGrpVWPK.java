package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VendorRateGrpVWPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "AssemblyGuid")
	private String assemblyGuid;
	
	@Column(name = "RateGroupId")
	private String rateGroupId;
	

	public String getAssemblyGuid() {
		return assemblyGuid;
	}

	public void setAssemblyGuid(String assemblyGuid) {
		this.assemblyGuid = assemblyGuid;
	}

	public String getRateGroupId() {
		return rateGroupId;
	}

	public void setRateGroupId(String rateGroupId) {
		this.rateGroupId = rateGroupId;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assemblyGuid == null) ? 0 : assemblyGuid.hashCode());
		result = prime * result + ((rateGroupId == null) ? 0 : rateGroupId.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VendorRateGrpVWPK other = (VendorRateGrpVWPK) obj;
		if (assemblyGuid == null) {
			if (other.assemblyGuid != null)
				return false;
		} else if (!assemblyGuid.equals(other.assemblyGuid))
			return false;
		if (rateGroupId == null) {
			if (other.rateGroupId != null)
				return false;
		} else if (!rateGroupId.equals(other.rateGroupId))
			return false;
		return true;
	}
}
