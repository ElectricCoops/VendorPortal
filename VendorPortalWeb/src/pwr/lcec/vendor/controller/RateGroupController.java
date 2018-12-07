package pwr.lcec.vendor.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import pwr.lcec.vendor.web.model.MyRateGroup;
import pwr.lcec.vendor.web.model.RateGroup;

public class RateGroupController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<RateGroup> vendorRates;
	private List<RateGroup> selectedVendorRates;
	private List<RateGroup> filteredVendorRates;

	private List<MyRateGroup> myRateGroup;
	private List<MyRateGroup> selectedMyRateGroup;
	private List<MyRateGroup> filteredMyRateGroup;
	
	@PostConstruct
	public void init() {
		vendorRates = new ArrayList<RateGroup>();
		vendorRates.add(new RateGroup("1","PIKE", "PIKE", "02-18-2018", "02-18-2019"));
		vendorRates.add(new RateGroup("2","PIKE", "PIKD", "02-18-2018", "02-18-2019"));
		vendorRates.add(new RateGroup("3","MASTEC", "MASE", "02-18-2010", "02-18-2019"));
		vendorRates.add(new RateGroup("4","MASTEC", "MASD", "02-18-2010", "02-18-2019"));
		
		myRateGroup = new ArrayList<MyRateGroup>();
		myRateGroup.add(new MyRateGroup("1", "40-5 Pole", null, "$500", "MASE"));
		myRateGroup.add(new MyRateGroup("2", "40-5 Pole", null, "$300", "MASD"));
	}
	

	public List<RateGroup> getVendorRates() {
		return vendorRates;
	}

	public void setVendorRates(List<RateGroup> vendorRates) {
		this.vendorRates = vendorRates;
	}


	public List<RateGroup> getSelectedVendorRates() {
		return selectedVendorRates;
	}


	public void setSelectedVendorRates(List<RateGroup> selectedVendorRates) {
		this.selectedVendorRates = selectedVendorRates;
	}


	public List<MyRateGroup> getMyRateGroup() {
		return myRateGroup;
	}


	public void setMyRateGroup(List<MyRateGroup> myRateGroup) {
		this.myRateGroup = myRateGroup;
	}


	public List<MyRateGroup> getSelectedMyRateGroup() {
		return selectedMyRateGroup;
	}


	public void setSelectedMyRateGroup(List<MyRateGroup> selectedMyRateGroup) {
		this.selectedMyRateGroup = selectedMyRateGroup;
	}
	public List<RateGroup> getFilteredVendorRates() {
		return filteredVendorRates;
	}


	public void setFilteredVendorRates(List<RateGroup> filteredVendorRates) {
		this.filteredVendorRates = filteredVendorRates;
	}


	public List<MyRateGroup> getFilteredMyRateGroup() {
		return filteredMyRateGroup;
	}


	public void setFilteredMyRateGroup(List<MyRateGroup> filteredMyRateGroup) {
		this.filteredMyRateGroup = filteredMyRateGroup;
	}
}
