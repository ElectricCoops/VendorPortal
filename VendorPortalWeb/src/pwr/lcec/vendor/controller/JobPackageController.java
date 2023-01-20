package pwr.lcec.vendor.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import pwr.lcec.vendor.controller.JobPackageController;

public class JobPackageController implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<String> pkg;

	@PostConstruct
	void init() {
		this.pkg = new ArrayList<String>();
		this.pkg.add(new String("File Location 1"));
		this.pkg.add(new String("File Location 2"));
		this.pkg.add(new String("File Location 3"));
	}

	public List<String> getPkg() {
		return this.pkg;
	}

	public void setPkg(List<String> pkg) {
		this.pkg = pkg;
	}
}
