package pwr.lcec.vendor.web.helper;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CurrentDateTime {

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");

	public String getCurrentTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// System.out.println(timestamp);
		return sdf.format(timestamp);
	}
}
