package pwr.lcec.vendor.security.validator;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import pwr.lcec.vendor.security.validator.CustomValidator;
import pwr.lcec.vendorportal.interfaces.UserManagementLocal;

public class CustomValidator
{
  @EJB
  private UserManagementLocal userManagementService;
  
  public void usernameValidate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    String userName = (String)value;
    
    if (!userManagementService.isUsernameAvailable(userName))
    {
      throw new ValidatorException(new FacesMessage("Username not avaliable"));
    }
  }

  
  public void passwordValidate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    String userName = (String)value;
    System.out.println("Password - " + value.toString());
    if (!userManagementService.isUsernameAvailable(userName))
    {
      throw new ValidatorException(new FacesMessage("Username not avaliable"));
    }
  }
}
