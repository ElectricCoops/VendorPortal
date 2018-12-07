package pwr.lcec.vendorportal.custom.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StreetLightSearchVwPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name="ServiceOrderId")
	private String serviceOrderId;
	
	private int GLAccountId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + GLAccountId;
		result = prime * result + ((serviceOrderId == null) ? 0 : serviceOrderId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StreetLightSearchVwPK other = (StreetLightSearchVwPK) obj;
		if (GLAccountId != other.GLAccountId)
			return false;
		if (serviceOrderId == null) {
			if (other.serviceOrderId != null)
				return false;
		} else if (!serviceOrderId.equals(other.serviceOrderId))
			return false;
		return true;
	}

	public String getServiceOrderId() {
		return serviceOrderId;
	}

	public void setServiceOrderId(String serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}

	public int getGLAccountId() {
		return GLAccountId;
	}

	public void setGLAccountId(int gLAccountId) {
		GLAccountId = gLAccountId;
	}
}
