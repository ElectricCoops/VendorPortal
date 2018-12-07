package pwr.lcec.vendorportal.sec.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the StakingSearchCols database table.
 * 
 */
@Entity
@Table(name = "StakingSearchCols")
@NamedQueries({ @NamedQuery(name = "StakingSearchCol.findAll", query = "SELECT s FROM StakingSearchCol s"),
		@NamedQuery(name = "StakingSearchCol.findByUser", query = "SELECT s FROM StakingSearchCol s WHERE s.userName = :username")
})
public class StakingSearchCol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "DesignCommentCol")
	private boolean designCommentCol;

	@Column(name = "LcecOverallInspStatCol")
	private boolean lcecOverallInspStatCol;

	@Column(name = "OverallAsBuitStatCol")
	private boolean overallAsBuitStatCol;

	@Column(name = "OverallInvStatCol")
	private boolean overallInvStatCol;

	private boolean SOCol;

	private boolean SOTypeCol;

	@Column(name = "UserName")
	private String userName;

	@Column(name = "VendorCol")
	private boolean vendorCol;

	private boolean WOCol;

	private boolean WOCreateDt;

	private boolean WONameCol;

	private boolean WOOpenDtCol;

	private boolean WOStatusCol;

	public StakingSearchCol() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getDesignCommentCol() {
		return this.designCommentCol;
	}

	public void setDesignCommentCol(boolean designCommentCol) {
		this.designCommentCol = designCommentCol;
	}

	public boolean getLcecOverallInspStatCol() {
		return this.lcecOverallInspStatCol;
	}

	public void setLcecOverallInspStatCol(boolean lcecOverallInspStatCol) {
		this.lcecOverallInspStatCol = lcecOverallInspStatCol;
	}

	public boolean getOverallAsBuitStatCol() {
		return this.overallAsBuitStatCol;
	}

	public void setOverallAsBuitStatCol(boolean overallAsBuitStatCol) {
		this.overallAsBuitStatCol = overallAsBuitStatCol;
	}

	public boolean getOverallInvStatCol() {
		return this.overallInvStatCol;
	}

	public void setOverallInvStatCol(boolean overallInvStatCol) {
		this.overallInvStatCol = overallInvStatCol;
	}

	public boolean getSOCol() {
		return this.SOCol;
	}

	public void setSOCol(boolean SOCol) {
		this.SOCol = SOCol;
	}

	public boolean getSOTypeCol() {
		return this.SOTypeCol;
	}

	public void setSOTypeCol(boolean SOTypeCol) {
		this.SOTypeCol = SOTypeCol;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean getVendorCol() {
		return this.vendorCol;
	}

	public void setVendorCol(boolean vendorCol) {
		this.vendorCol = vendorCol;
	}

	public boolean getWOCol() {
		return this.WOCol;
	}

	public void setWOCol(boolean WOCol) {
		this.WOCol = WOCol;
	}

	public boolean getWOCreateDt() {
		return this.WOCreateDt;
	}

	public void setWOCreateDt(boolean WOCreateDt) {
		this.WOCreateDt = WOCreateDt;
	}

	public boolean getWONameCol() {
		return this.WONameCol;
	}

	public void setWONameCol(boolean WONameCol) {
		this.WONameCol = WONameCol;
	}

	public boolean getWOOpenDtCol() {
		return this.WOOpenDtCol;
	}

	public void setWOOpenDtCol(boolean WOOpenDtCol) {
		this.WOOpenDtCol = WOOpenDtCol;
	}

	public boolean getWOStatusCol() {
		return this.WOStatusCol;
	}

	public void setWOStatusCol(boolean WOStatusCol) {
		this.WOStatusCol = WOStatusCol;
	}

}