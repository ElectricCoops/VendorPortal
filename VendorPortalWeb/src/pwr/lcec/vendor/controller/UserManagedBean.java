package pwr.lcec.vendor.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.omnifaces.util.Faces;

import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendor.web.helper.Util;
import pwr.lcec.vendor.web.model.User;
import pwr.lcec.vendorportal.exception.NoResultException;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;
import pwr.lcec.vendorportal.interfaces.UserManagementRemote;
import pwr.lcec.vendorportal.sec.entity.LoginAttempt;

public class UserManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(UserManagedBean.class);
	
	@EJB
	private UserManagementRemote userManagementService;

	private User user = new User();
	private Subject currentUser;

	private String home_url;
	private String login_url;
	
	private String vendor;
	private StringBuilder fullname;
	
	ControllerUtil util = new ControllerUtil();

	public void doLogin() {

		ResourceBundle props = null;

		try {
			props = ResourceBundle.getBundle(Util.MSG_PKG);

			home_url = props.getString(Util.HOME_URL);
			login_url = props.getString(Util.LOGIN_URL);
		} catch (MissingResourceException mre) {
			logger.error(mre.getMessage());
		}
		
		boolean loginSucceed = false;

		currentUser = SecurityUtils.getSubject();

		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPwd());
			try {
				currentUser.login(token);
				
				pwr.lcec.vendorportal.sec.entity.User user = userManagementService.finUserByPrincipal(currentUser.getPrincipal().toString());
				fullname = new StringBuilder();
				if(user != null) {
					vendor = user.getWorkGroup();
			
					fullname.append(user.getFirstName());
					fullname.append(" ");
					fullname.append(user.getLastName());
				}
				
				loginSucceed = true;
				
				/*SavedRequest savedRequest = WebUtils.getSavedRequest(Faces.getRequest());
				Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : home_url);*/
				
				Faces.redirect(home_url);

			}  catch (LockedAccountException lae) {
				logger.error(lae);
				facesError("User account is locked.");
			} catch (AuthenticationException | IOException ex) {
				logger.error(ex);
				facesError("Incorrect username and/or password.");
			} catch (ValidationException | ProcessException | NoResultException e) {
				logger.error(e);
				facesError(e.getMessage());
			} 
			
			updateLoginActivity(loginSucceed);
		}
	}

	public void doLogout() throws IOException {
		SecurityUtils.getSubject().logout();
		Faces.redirect(login_url);
	}

	private void facesError(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}
	
	public void updateLoginActivity(boolean succeed) {
		LoginAttempt attempt = new LoginAttempt();
		
		final HttpServletRequest request =(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		final String userAgent = request.getHeader("user-agent");
		
		try {
			pwr.lcec.vendorportal.sec.entity.User user = null;
			
			if(currentUser.isAuthenticated()) {
				user = userManagementService.finUserByPrincipal(currentUser.getPrincipal().toString());
			}
			attempt.setUserID(user != null ? user.getUserID() : 0);
			attempt.setIPAddress(util.getClientIp(request));
			attempt.setAttemptDT(util.currentDtTm());
			attempt.setBrowserType(userAgent);
			attempt.setNameAttempt(this.user.getUserName());
			attempt.setSuccess(succeed);
			
			userManagementService.insertAttempt(attempt);	
			if(user != null){
				userManagementService.updateLastLogin(user.getUserID(), util.currentDtTm());
			}
			
		} catch (ValidationException | ProcessException | NoResultException e) {
			logger.error(e);
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Subject getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Subject currentUser) {
		this.currentUser = currentUser;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public StringBuilder getFullname() {
		return fullname;
	}

	public void setFullname(StringBuilder fullname) {
		this.fullname = fullname;
	}

}
