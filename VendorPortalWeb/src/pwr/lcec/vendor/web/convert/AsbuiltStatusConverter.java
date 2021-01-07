package pwr.lcec.vendor.web.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pwr.lcec.vendor.controller.WorkflowController;
import pwr.lcec.vendorportal.entity.custom.AsBuiltStatus;

@FacesConverter("asBuiltStatusConverter")
public class AsbuiltStatusConverter implements Converter {
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		System.out.println("Arg2 value: " + arg2);
		System.out.println("Arg0" + arg0.getNamingContainerSeparatorChar());
		System.out.println("UIComponent ID: " + arg1.getId());
		System.out.println("Arg2 trim length: " + arg2.trim().length());
		
		if (arg2 != null && arg2.trim().length() > 0) {
			WorkflowController controller = (WorkflowController) FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{wfController}", WorkflowController.class);
			
			AsBuiltStatus abs = controller.getAsBuiltStatuses().stream().filter(s -> (Integer.parseInt(arg2) == s.getAsBuiltStatusId().intValue())).findFirst().orElse(null);
			
			if(abs == null) {
				System.out.println("Object Null......ABS");
			}else {
				System.out.println("getAsObject: " + abs.getAsBuiltStatusId() + " : " + abs.getDescription());
			}
			
			//return controller.getAsBuiltStatuses().stream().filter(s -> (Integer.parseInt(arg2) == s.getAsBuiltStatusId().intValue())).findFirst().orElse(null);
			return abs;
		}
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof AsBuiltStatus) {
			AsBuiltStatus stf = (AsBuiltStatus) arg2;
			
			System.out.println("GetAsString: " + stf.getAsBuiltStatusId() + " : " + stf.getDescription());
			
			return String.valueOf(stf.getAsBuiltStatusId());
		}
		/*if(arg2 instanceof String) {
			System.out.println("Arg2:  " + arg2);
			return arg2.toString();
		}*/
		System.out.println("Not an instance of AsBuiltStatus..." + arg2);
		return "";
	}
}
