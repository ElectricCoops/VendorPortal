package pwr.lcec.vendor.web.convert;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@FacesConverter("timeConverter")
public class TimestampConverter implements Converter {
	
	private static Logger logger = LogManager.getLogger(TimestampConverter.class);

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Timestamp time = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			
			Date date = dateFormat.parse(value);
			
			time = new Timestamp(date.getTime());
		} catch (ParseException e) {
			logger.error("Coverter Error: " + e.getMessage());
		}
		
		
		return time;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		logger.debug("getAsString() " + value);
		return value.toString();
	}

}
