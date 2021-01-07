package pwr.lcec.vendor.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.omnifaces.util.Faces;

import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendor.web.helper.Util;
import pwr.lcec.vendorportal.entity.sec.LoginAttempt;
import pwr.lcec.vendorportal.entity.sec.UserTbl;
import pwr.lcec.vendorportal.interfaces.UserManagementLocal;

public class UserManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LogManager.getLogger(UserManagedBean.class);
	
	@EJB
	private UserManagementLocal userManagementService;

	private UserTbl user = new UserTbl();
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
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
			try {
				currentUser.login(token);
				
				UserTbl attemptedUser = userManagementService.findUserByPrincipal(currentUser.getPrincipal().toString());
				fullname = new StringBuilder();
				if(attemptedUser != null) {
					
					vendor = attemptedUser.getWorkGroup();
					//logger.info("Vendor: " + vendor);
					fullname.append(attemptedUser.getFirstName());
					fullname.append(" ");
					fullname.append(attemptedUser.getLastName());
				}
				
				loginSucceed = true;
				
				/*SavedRequest savedRequest = WebUtils.getSavedRequest(Faces.getRequest());
				logger.info(savedRequest.getRequestURI());
				Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : home_url);*/
				
				Faces.redirect(home_url);
				//return home_url;

			}  catch (LockedAccountException lae) {
				logger.error(lae);
				facesError("User account is locked.");
			} catch (AuthenticationException ex) {
				logger.error(ex);
				facesError("Incorrect username and/or password.");
			} catch (Exception e) {
				logger.error(e);
				facesError("Something Went wrong, please contact ITfor support.");
			} 
			
			updateLoginActivity(loginSucceed);
		}

	}

	public void doLogout() throws IOException {
		SecurityUtils.getSubject().logout();
		Faces.redirect(login_url);
		//return "/" + login_url;
	}
	
	public void updateLoginActivity(boolean succeed) {
		LoginAttempt attempt = new LoginAttempt();
		
		final HttpServletRequest request =(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		final String userAgent = request.getHeader("user-agent");
		
		try {
			UserTbl user = null;
			
			if(currentUser.isAuthenticated()) {
				user = userManagementService.findUserByPrincipal(currentUser.getPrincipal().toString());
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
			
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	private void facesError(String message) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
	}

	public UserTbl getUser() {
		return user;
	}

	public void setUser(UserTbl user) {
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
