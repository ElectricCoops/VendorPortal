package pwr.lcec.vendor.web.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pwr.lcec.vendorportal.sec.entity.User;

@FacesConverter(value = "pfUserConverter")
public class UserConverter implements Converter{
	
	private User user;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if(value instanceof User){
			User user = (User)value;
			return String.valueOf(user.getUserID());
		}
		return "";
	}
}
