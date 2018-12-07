package pwr.lcec.vendor.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

public class JobPackageController implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> pkg;

	@PostConstruct
	void init() {
		pkg = new ArrayList<String>();
		pkg.add(new String("File Location 1"));
		pkg.add(new String("File Location 2"));
		pkg.add(new String("File Location 3"));
	}

	public List<String> getPkg() {
		return pkg;
	}

	public void setPkg(List<String> pkg) {
		this.pkg = pkg;
	}

}
