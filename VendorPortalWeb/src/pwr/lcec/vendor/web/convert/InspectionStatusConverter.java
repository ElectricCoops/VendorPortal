package pwr.lcec.vendor.web.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import pwr.lcec.vendor.controller.WorkflowController;
import pwr.lcec.vendorportal.entity.custom.InspectionStatus;


@FacesConverter("inspectionStatusConverter")
public class InspectionStatusConverter
  implements Converter
{
  public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
    if (arg2 != null && arg2.trim().length() > 0) {
      WorkflowController controller = (WorkflowController)FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{wfController}", WorkflowController.class);
      return controller.getNewInspStatuses().stream().filter(i -> (Integer.parseInt(arg2) == i.getInspectionStatusId().intValue())).findFirst().orElse(null);
    } 
    return null;
  }



  
  public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
    if (arg2 instanceof InspectionStatus) {
      InspectionStatus stf = (InspectionStatus)arg2;
      return String.valueOf(stf.getInspectionStatusId());
    } 
    return "";
  }
}
