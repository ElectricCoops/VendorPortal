package pwr.lcec.vendorportal.entity.custom;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the RateGroupPrice database table.
 * 
 */

public class RateYearRange implements Serializable {
	private static final long serialVersionUID = 1L;

	private Timestamp effectiveEndDt;

	private Timestamp effectiveStartDt;

	public RateYearRange() {
	}

	public Timestamp getEffectiveEndDt() {
		return this.effectiveEndDt;
	}

	public void setEffectiveEndDt(Timestamp effectiveEndDt) {
		this.effectiveEndDt = effectiveEndDt;
	}

	public Timestamp getEffectiveStartDt() {
		return this.effectiveStartDt;
	}

	public void setEffectiveStartDt(Timestamp effectiveStartDt) {
		this.effectiveStartDt = effectiveStartDt;
	}

}