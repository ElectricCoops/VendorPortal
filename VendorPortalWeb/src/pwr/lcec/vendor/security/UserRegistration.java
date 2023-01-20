package pwr.lcec.vendor.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DualListModel;
import pwr.lcec.vendor.security.UserRegistration;
import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendorportal.entity.custom.Resource;
import pwr.lcec.vendorportal.entity.custom.WorkGroup;
import pwr.lcec.vendorportal.interfaces.InspectionLocal;
import pwr.lcec.vendorportal.interfaces.UserManagementLocal;
import pwr.lcec.vendorportal.interfaces.WorkFlowLocal;
import pwr.lcec.vendorportal.entity.sec.Role;
import pwr.lcec.vendorportal.entity.sec.UserTbl;
import pwr.lcec.vendorportal.entity.sec.UserRole;

public class UserRegistration implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(UserRegistration.class);

	@EJB
	private UserManagementLocal userManagementService;

	@EJB
	private WorkFlowLocal workflowService;
	@EJB
	private InspectionLocal inspectionService;
	private String lengthStyle = "";
	private String upperStyle = "";
	private String lowerStyle = "";
	private String digitStyle = "";
	private String spaceStyle = "";

	private boolean activeFlg = true;

	private boolean showReactivate = false;
	private boolean reactivateFlg = false;
	private boolean passwordValid = false;
	private boolean passwordMatch = false;
	private boolean disableResetSubmit = true;
	private UserTbl newUser = new UserTbl();
	private UserTbl selectedUser = new UserTbl();
	private List<UserTbl> users = new ArrayList<UserTbl>();

	private DualListModel<Role> rolesList = new DualListModel<Role>();
	private DualListModel<Role> updateRolesList = new DualListModel<Role>();

	ControllerUtil util = new ControllerUtil();

	public void onloadUsers() {
		users = new ArrayList<UserTbl>();
		List<UserTbl> list = new ArrayList<UserTbl>();

		if (activeFlg) {
			list = userManagementService.findActiveUsers();
		} else {
			list = userManagementService.findAllUsers();
		}
		
		logger.debug("Number of users: " + list.size());

		list.stream().forEach(u -> {

			UserTbl user = u;
			user.setPassword("");

			users.add(user);
		});
	}

	public void loadCreateUserDlg() {
		clear("new");

		List<Role> rolesSource = this.userManagementService.findAllRoles();
		List<Role> rolesTarget = new ArrayList<Role>();
		rolesList = new DualListModel<Role>(rolesSource, rolesTarget);

		PrimeFaces.current().ajax().update(new String[] { "createUserFrm" });
		PrimeFaces.current().executeScript("PF('createUserDlg').show();");
	}

	public void loadUpdateUserDlg() {

		logger.info("Username " + selectedUser.getUserName());
		clear("update");
		
		if (selectedUser.getEffectiveEndDate() != null) {
		  showReactivate = true;
		} else {
		  showReactivate = false;
		} 
		
		List<Role> source = userManagementService.findAllRoles();
		List<Role> target = new ArrayList<Role>();
		
		List<UserRole> userRoles = null;
		
		userRoles = userManagementService.existingRoles(selectedUser.getUserID());
		
		userRoles.stream().forEach(r -> {
			
			target.add(r.getRole());
			    
		});
		
		
		source = source.stream().filter(s -> target.stream().noneMatch(t -> s.getRoleName().equals(t.getRoleName()))).collect(Collectors.toList());
		
		updateRolesList = new DualListModel<Role>(source, target);
		
		PrimeFaces.current().ajax().update("updateUserFrm");
		PrimeFaces.current().executeScript("PF('updateUserDlg').show();");
	}

	public void loadResetUserDlg() {
		clear("reset");

		PrimeFaces.current().ajax().update(new String[] { "resetPasswordFrm" });
		PrimeFaces.current().ajax().update(new String[] { "resetUserDlg" });
	}

	public Boolean[] passwordValidator(UserTbl test) {
		Boolean[] ready = new Boolean[5];

		if (test.getPassword().matches(".*\\d+.*")) {
			ready[0] = Boolean.valueOf(true);
			this.digitStyle = "color:#00CC00;";
		} else {
			ready[0] = Boolean.valueOf(false);
			this.digitStyle = "color:white;";
		}

		if (test.getPassword().matches(".*[A-Z].*")) {
			ready[1] = Boolean.valueOf(true);
			this.upperStyle = "color:#00CC00;";
		} else {
			ready[1] = Boolean.valueOf(false);
			this.upperStyle = "color:white;";
		}

		if (test.getPassword().matches(".*[a-z].*")) {
			ready[2] = Boolean.valueOf(true);
			this.lowerStyle = "color:#00CC00;";
		} else {
			ready[2] = Boolean.valueOf(false);
			this.lowerStyle = "color:white;";
		}

		if (test.getPassword().length() >= 12 && test.getPassword().length() < 128) {
			ready[3] = Boolean.valueOf(true);
			this.lengthStyle = "color:#00CC00;";
		} else {
			ready[3] = Boolean.valueOf(false);
			this.lengthStyle = "color:white;";
		}

		if (!StringUtils.contains(test.getPassword(), " ")) {
			ready[4] = Boolean.valueOf(true);
			this.spaceStyle = "color:#00CC00;";
		} else {
			ready[4] = Boolean.valueOf(false);
			this.spaceStyle = "color:white;";
		}

		return ready;
	}

	public void pwd1CreateListener() {
		passwordValidator(this.newUser);

		PrimeFaces.current().ajax().update(new String[] { ":createUserFrm:tooltipPanel" });
	}

	public void pwd1Listener() {
		Boolean[] ready = passwordValidator(this.selectedUser);

		if (Arrays.stream(ready).allMatch(Boolean::valueOf)) {
			this.passwordValid = true;
		} else {
			this.passwordValid = false;
		}

		this.disableResetSubmit = resetSubmitStatus();
	}

	public void pwd2CreateListener(AjaxBehaviorEvent event) {
		String confirm = (String) ((UIOutput) event.getSource()).getValue();

		if (!confirm.equals(this.newUser.getPassword())) {
			this.passwordMatch = false;
			sendMessenge("Passwords don't match.", FacesMessage.SEVERITY_ERROR);
		} else {
			this.passwordMatch = true;
		}
	}

	public void pwd2Listener(AjaxBehaviorEvent event) {
		String confirm = (String) ((UIOutput) event.getSource()).getValue();

		if (!confirm.equals(this.selectedUser.getPassword())) {
			this.passwordMatch = false;
			sendMessenge("Passwords don't match.", FacesMessage.SEVERITY_ERROR);
		} else {
			this.passwordMatch = true;
		}

		this.disableResetSubmit = resetSubmitStatus();
	}

	public boolean resetSubmitStatus() {
		if (this.passwordValid && this.passwordMatch) {
			return false;
		}
		if (this.passwordValid && !this.passwordMatch) {
			return true;
		}
		if (!this.passwordValid && this.passwordMatch) {
			return true;
		}

		return true;
	}

	public void clear(String operation) {
		if (operation == "new") {
			newUser = new UserTbl();
			PrimeFaces.current().resetInputs(new String[] { "createUserFrm:addUserGrid" });
		} else if (operation == "update") {
			selectedUser.setPassword("");
		} else {
			selectedUser.setPassword("");
			PrimeFaces.current().resetInputs(new String[] { "resetPasswordFrm:resetPnlGrid" });
		}

		lengthStyle = "color:white;";
		upperStyle = "color:white;";
		lowerStyle = "color:white;";
		digitStyle = "color:white;";
		spaceStyle = "color:white;";

		passwordValid = false;
		passwordMatch = false;
	}

	public void updateUserInfo() {
		
		UserTbl found = userManagementService.findUserByPrincipal(selectedUser.getUserName());
		
		if(found != null) {
			selectedUser.setPassword(found.getPassword());
		}

		//remove all roles for selected user from UserRole table
		userManagementService.deleteUserRole(selectedUser.getUserID());
		// Then add each role in target list to UserRole table
		updateRolesList.getTarget().stream().forEach(ur -> {
			
			UserRole userRole = new UserRole();
			userRole.setUserID(selectedUser.getUserID());
			userRole.setRoleID(ur.getRoleID());
			
			userManagementService.updateUserRole(userRole);
			
		});
		
		userManagementService.updateUser(selectedUser);
		
		onloadUsers();
		PrimeFaces.current().ajax().update("userListForm");
		PrimeFaces.current().executeScript("PF('updateUserDlg').hide();");
		logger.info("User updated successfully - " + this.selectedUser.getUserName());
		sendMessenge("User information updated successfully.", FacesMessage.SEVERITY_INFO);
		
	}

	public void resetUserPwd() {
		if (isValidPassword(this.selectedUser.getPassword())) {
			String securePassword = encryptPwd(this.selectedUser.getPassword());
			this.selectedUser.setPassword(securePassword);

			try {
				this.userManagementService.updateUserPwd(this.selectedUser);
				sendMessenge("User Password reset successfully.", FacesMessage.SEVERITY_INFO);
				PrimeFaces.current().executeScript("PF('resetUserDlg').hide();");
			} catch (Exception e) {
				logger.error(e);
				sendMessenge(e.getMessage(), FacesMessage.SEVERITY_ERROR);
			}
		}

		onloadUsers();
		PrimeFaces.current().ajax().update(new String[] { "userListForm" });
		logger.info("User password reset successfully - " + this.selectedUser.getUserName());
	}

	public void deleteUserAcct() {
		try {
			this.selectedUser.setEffectiveEndDate(this.util.currentDtTm());
			this.userManagementService.deleteUserAcct(this.selectedUser);
			sendMessenge("User Account Deleted Successfully.", FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			logger.error(e);
			sendMessenge(e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}

		onloadUsers();
		PrimeFaces.current().ajax().update(new String[] { "userListForm" });
		logger.info("User deleted successfully - " + this.selectedUser.getUserName());
	}

	public void register() throws NoResultException {
		UserRole userRole = new UserRole();
		Resource resource = new Resource();
		WorkGroup wrkGrp = new WorkGroup();

		StringBuilder name = new StringBuilder();
		name.append(this.newUser.getFirstName());
		name.append(" ");
		name.append(this.newUser.getLastName());

		SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
		Object salt = secureRandomNumberGenerator.nextBytes();

		String securePassword = encryptPwd(this.newUser.getPassword());

		this.newUser.setUserName(this.newUser.getUserName().toLowerCase());
		this.newUser.setPassword(securePassword);
		this.newUser.setPasswordSalt(salt.toString());
		this.newUser.setEffectiveStartDate(this.util.currentDtTm());
		this.newUser.setCreatedBy(this.util.getCurrentUser());

		resource.setResourceName(name.toString());

		resource.setActive("Y");
		resource.setEffectiveBeginDt(this.util.currentDtTm());
		resource.setSourceSystem("VP");

		if (isValidPassword(this.newUser.getPassword())) {
			try {
				if (!userExist(this.newUser.getUserName())) {

					this.newUser = this.userManagementService.insertUser(this.newUser);

					for (Role role : this.rolesList.getTarget()) {

						userRole = new UserRole();
						userRole.setUserID(this.newUser.getUserID());
						userRole.setRoleID(Integer.valueOf(role.getRoleID()));

						this.userManagementService.insertUserRole(userRole);
					}

					Integer resourceId = Integer.valueOf(inspectionService.getResourceId(name.toString()));

					if (resourceId == null || resourceId.intValue() == 0) {
						logger.debug("Inside the if statement resource ID is NULL or 0");
						resourceId = workflowService.getMaxResourceId();
						logger.debug("Latest resource ID: " + resourceId);
						resource.setResourceId(resourceId.intValue() + 1);
						resource.setVpUserID(newUser.getUserID().intValue());
						resource = workflowService.insertResource(resource);
					} else {
						logger.debug("Resource found and updating user ID");
						try {
							this.workflowService.updateResource(resourceId, this.newUser.getUserID());
						} catch (Exception e) {
							logger.error(e);
							sendMessenge(e.getMessage(), FacesMessage.SEVERITY_ERROR);
						}
					}

					wrkGrp.setResourceId(
							Integer.valueOf((resourceId != null) ? resourceId.intValue() : resource.getResourceId()));
					wrkGrp.setWorkGroupName(this.newUser.getWorkGroup());
					this.workflowService.insertWorkGroup(wrkGrp);

					PrimeFaces.current().executeScript("PF('createUserDlg').hide()");

					sendMessenge("User created successfully.", FacesMessage.SEVERITY_INFO);
					onloadUsers();
					PrimeFaces.current().ajax().update(new String[] { "userListForm" });
					logger.info("User created successfully - " + this.newUser.getUserName());
				} else {
					sendMessenge("Username already exist. Please choose a different username.",
							FacesMessage.SEVERITY_ERROR);
					return;
				}
			} catch (Exception e) {
				logger.error(e);
				sendMessenge(e.getMessage(), FacesMessage.SEVERITY_ERROR);
			}
		} else {
			sendMessenge("Password does not meet complexity requirement.", FacesMessage.SEVERITY_ERROR);
			return;
		}
	}

	public boolean isValidPassword(String pwd) {
		PasswordValidator validator = new PasswordValidator(Arrays.asList(new Rule[] {

				new LengthRule(12, 128),

				new CharacterRule(EnglishCharacterData.UpperCase, 1),

				new CharacterRule(EnglishCharacterData.LowerCase, 1),

				new CharacterRule(EnglishCharacterData.Digit, 1),

				new WhitespaceRule() }));
		RuleResult result = validator.validate(new PasswordData(pwd));
		if (result.isValid()) {
			return true;
		}
		sendMessenge("Invalid password:", FacesMessage.SEVERITY_ERROR);
		for (String msg : validator.getMessages(result)) {
			sendMessenge(msg, FacesMessage.SEVERITY_ERROR);
		}
		return false;
	}

	private boolean userExist(String userName) {
		boolean exist = false;

		try {
			UserTbl user = this.userManagementService.findUserByPrincipal(userName);
			if (user != null) {
				exist = true;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return exist;
	}

	private String encryptPwd(String pwd) {
		PasswordMatcher auth = new PasswordMatcher();
		return auth.getPasswordService().encryptPassword(pwd);
	}

	public void sendMessenge(String msg, FacesMessage.Severity level) {
		FacesMessage message = new FacesMessage();
		message.setSummary(msg);
		message.setSeverity(level);

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public DualListModel<Role> getRolesList() {
		return this.rolesList;
	}

	public void setRolesList(DualListModel<Role> rolesList) {
		this.rolesList = rolesList;
	}

	public List<UserTbl> getUsers() {
		return this.users;
	}

	public void setUsers(List<UserTbl> users) {
		this.users = users;
	}

	public DualListModel<Role> getUpdateRolesList() {
		return this.updateRolesList;
	}

	public void setUpdateRolesList(DualListModel<Role> updateRolesList) {
		this.updateRolesList = updateRolesList;
	}

	public boolean isActiveFlg() {
		return this.activeFlg;
	}

	public void setActiveFlg(boolean activeFlg) {
		this.activeFlg = activeFlg;
	}

	public UserTbl getNewUser() {
		return this.newUser;
	}

	public void setNewUser(UserTbl newUser) {
		this.newUser = newUser;
	}

	public UserTbl getSelectedUser() {
		return this.selectedUser;
	}

	public void setSelectedUser(UserTbl selectedUser) {
		this.selectedUser = selectedUser;
	}

	public boolean isShowReactivate() {
		return this.showReactivate;
	}

	public void setShowReactivate(boolean showReactivate) {
		this.showReactivate = showReactivate;
	}

	public boolean isReactivateFlg() {
		return this.reactivateFlg;
	}

	public void setReactivateFlg(boolean reactivateFlg) {
		this.reactivateFlg = reactivateFlg;
	}

	public String getLengthStyle() {
		return this.lengthStyle;
	}

	public void setLengthStyle(String lengthStyle) {
		this.lengthStyle = lengthStyle;
	}

	public String getUpperStyle() {
		return this.upperStyle;
	}

	public void setUpperStyle(String upperStyle) {
		this.upperStyle = upperStyle;
	}

	public String getLowerStyle() {
		return this.lowerStyle;
	}

	public void setLowerStyle(String lowerStyle) {
		this.lowerStyle = lowerStyle;
	}

	public String getDigitStyle() {
		return this.digitStyle;
	}

	public void setDigitStyle(String digitStyle) {
		this.digitStyle = digitStyle;
	}

	public String getSpaceStyle() {
		return this.spaceStyle;
	}

	public void setSpaceStyle(String spaceStyle) {
		this.spaceStyle = spaceStyle;
	}

	public boolean isDisableResetSubmit() {
		return this.disableResetSubmit;
	}

	public void setDisableResetSubmit(boolean disableResetSubmit) {
		this.disableResetSubmit = disableResetSubmit;
	}
}
