package pwr.lcec.vendorportal.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="RateGroup")
public class RateGroupPivot implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String assemblyUnit;
	private String gndc;
	private String gndr;
	private String gnec;
	private String gner;
	private String msdc;
	private String msdr;
	private String msec;
	private String mser;
	private String pkdc;
	private String pkdr;
	private String pkec;
	private String pker;

	public RateGroupPivot() {
	}

	public RateGroupPivot(String assemblyUnit, String gndc, String gndr, String gnec, String gner, String msdc,
			String msdr, String msec, String mser, String pkdc, String pkdr, String pkec, String pker) {
		this.assemblyUnit = assemblyUnit;
		this.gndc = gndc;
		this.gndr = gndr;
		this.gnec = gnec;
		this.gner = gner;
		this.msdc = msdc;
		this.msdr = msdr;
		this.msec = msec;
		this.mser = mser;
		this.pkdc = pkdc;
		this.pkdr = pkdr;
		this.pkec = pkec;
		this.pker = pker;
	}

	public String getAssemblyUnit() {
		return assemblyUnit;
	}

	public void setAssemblyUnit(String assemblyUnit) {
		this.assemblyUnit = assemblyUnit;
	}

	public String getGndc() {
		return gndc;
	}

	public void setGndc(String gndc) {
		this.gndc = gndc;
	}

	public String getGndr() {
		return gndr;
	}

	public void setGndr(String gndr) {
		this.gndr = gndr;
	}

	public String getGnec() {
		return gnec;
	}

	public void setGnec(String gnec) {
		this.gnec = gnec;
	}

	public String getGner() {
		return gner;
	}

	public void setGner(String gner) {
		this.gner = gner;
	}

	public String getMsdc() {
		return msdc;
	}

	public void setMsdc(String msdc) {
		this.msdc = msdc;
	}

	public String getMsdr() {
		return msdr;
	}

	public void setMsdr(String msdr) {
		this.msdr = msdr;
	}

	public String getMsec() {
		return msec;
	}

	public void setMsec(String msec) {
		this.msec = msec;
	}

	public String getMser() {
		return mser;
	}

	public void setMser(String mser) {
		this.mser = mser;
	}

	public String getPkdc() {
		return pkdc;
	}

	public void setPkdc(String pkdc) {
		this.pkdc = pkdc;
	}

	public String getPkdr() {
		return pkdr;
	}

	public void setPkdr(String pkdr) {
		this.pkdr = pkdr;
	}

	public String getPkec() {
		return pkec;
	}

	public void setPkec(String pkec) {
		this.pkec = pkec;
	}

	public String getPker() {
		return pker;
	}

	public void setPker(String pker) {
		this.pker = pker;
	}
}
