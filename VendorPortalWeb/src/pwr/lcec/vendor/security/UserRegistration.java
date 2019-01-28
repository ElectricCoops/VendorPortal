package pwr.lcec.vendor.security;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DualListModel;

import pwr.lcec.vendor.web.helper.Constants;
import pwr.lcec.vendor.web.helper.ControllerUtil;
import pwr.lcec.vendorportal.custom.entity.Resource;
import pwr.lcec.vendorportal.custom.entity.WorkGroup;
import pwr.lcec.vendorportal.exception.NoResultException;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;
import pwr.lcec.vendorportal.interfaces.InspectionSessionRemote;
import pwr.lcec.vendorportal.interfaces.UserManagementRemote;
import pwr.lcec.vendorportal.interfaces.WorkFlowSessionRemote;
import pwr.lcec.vendorportal.sec.entity.Role;
import pwr.lcec.vendorportal.sec.entity.User;
import pwr.lcec.vendorportal.sec.entity.UserRole;

public class UserRegistration implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(UserRegistration.class);
	
	private final String DELETE_ACCT = "deleteuser?faces-redirect=true";

	@EJB
	private UserManagementRemote userManagementService;
	@EJB
	private WorkFlowSessionRemote workflowService;
	@EJB
	private InspectionSessionRemote inspectionService;

	private String userName;
	private String pwd = "";
	private String role;
	private String email;
	private String firstName;
	private String lastName;
	private boolean userLocked;
	private String phoneNo;
	private String phoneCarrier;
	private Integer roleId;
	private String workgroup;
	private Resource resource;
	
	private List<Role> rolesSource;
	private List<Role> rolesTarget;
	private DualListModel<Role> rolesList;

	private List<Role> updateRolesSource;
	private List<Role> updateRolesTarget;
	private DualListModel<Role> updateRolesList;
	private Set<Integer> ids;
	
	private List<UserRole> userRoles;
	
	private List<User> users;
	private User userUpdate;
	
	ControllerUtil util = new ControllerUtil();
	
	@PostConstruct
	void init() {
		findRoles();
		findUsers();
		//findRolesForUser();
	}
	
	public void findRoles() {
		rolesSource = userManagementService.findAllRoles();
		rolesTarget = new ArrayList<Role>();
		rolesList = new DualListModel<Role>(rolesSource, rolesTarget);
		
		updateRolesSource = new ArrayList<Role>();
		updateRolesTarget = new ArrayList<Role>();
		updateRolesList = new DualListModel<Role>(updateRolesSource, updateRolesTarget);
	}

	public void findRolesForUser() throws ValidationException, ProcessException {
		
		updateRolesTarget = new ArrayList<Role>();
		userRoles = userManagementService.existingRoles(userUpdate.getUserID());
		for(UserRole userRole : userRoles) {
			updateRolesTarget.add(userRole.getRole());
		}
		rolesSource = userManagementService.findAllRoles();
		
		ids = updateRolesTarget.stream().map(Role::getRoleID).collect(Collectors.toSet());
		
		updateRolesSource = rolesSource.stream().filter(src -> !ids.contains(src.getRoleID())).collect(Collectors.toList());

		updateRolesList = new DualListModel<Role>(updateRolesSource, updateRolesTarget);
	}
	
	public void findUsers() {
		users = userManagementService.findAllUsers();
		
		users = users.stream().sorted(Comparator.comparing(User::getUserName, String.CASE_INSENSITIVE_ORDER)).collect(Collectors.toList());
	}

	public void updateUserInfo() {

		List<UserRole> userRole = new ArrayList<UserRole>();

		try {
			for (Role role : updateRolesList.getTarget()) {
				userRole.add(new UserRole(null, role.getRoleID(), userUpdate.getUserID()));	
			}
			userUpdate.setUserRole(userRole);
			userManagementService.updateUser(userUpdate);

			facesInfo("User information updated successfully.");
		} catch (ValidationException | ProcessException e) {
			logger.error(e);
			facesError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void resetUserPwd() {
		final String securePassword = encryptPwd(pwd);
		userUpdate.setPassword(securePassword);
		if (isValidPassword()) {
			try {
				userManagementService.updateUserPwd(userUpdate);
				facesInfo("User information updated successfully.");
			} catch (ValidationException | ProcessException e) {
				logger.error(e);
				facesError(e.getMessage());
				e.printStackTrace();
			}
		} else {
			facesError("Password does not meet complexity requirement.");
			return;
		}
	}
	
	public String deleteUserAcct() {

		try {
			userUpdate.setEffectiveEndDate(util.currentDtTm());
			userManagementService.deleteUserAcct(userUpdate);
			facesInfo("User Account Deleted Successfully.");
		}catch (ValidationException | ProcessException e){
			logger.error(e);
			facesError(e.getMessage());
			e.printStackTrace();
		}
		return DELETE_ACCT;
	}

	public void register() throws NoSuchAlgorithmException, NoResultException {

		User user = new User();
		UserRole userRole = new UserRole();
		Resource resource = new Resource();
		WorkGroup wrkGrp = new WorkGroup();

		StringBuilder name = new StringBuilder();
		name.append(firstName);
		name.append(Constants.SPACE_SEPARATOR);
		name.append(lastName);

		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		final Object salt = rng.nextBytes();

		final String securePassword = encryptPwd(pwd);

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setUserName(userName.toLowerCase());
		user.setPhone(phoneNo);
		user.setPhoneCarrier(phoneCarrier);
		user.setPassword(securePassword);
		user.setPasswordSalt(salt.toString());
		user.setEffectiveStartDate(util.currentDtTm());
		user.setWorkGroup(workgroup);
		user.setCreatedBy(util.getCurrentUser());

		resource.setResourceName(name.toString());
		// resource.setWorkGroup(workgroup);
		resource.setActive("Y");
		resource.setEffectiveBeginDt(util.currentDtTm());
		resource.setSourceSystem("VP");

		if (isValidPassword()) {
			try {
				if (!userExist(userName)) {
					int userId = userManagementService.insertUser(user);

					rolesTarget = rolesList.getTarget();

					for (Role role : rolesTarget) {

						userRole.setUserID(userId);
						userRole.setRoleID(role.getRoleID());

						userManagementService.inserUserRole(userRole);
					}

					Integer resourceId = inspectionService.getResourceId(name.toString());
					
					if (resourceId == null || resourceId == 0) {
						resourceId = workflowService.getMaxResourceId();
						resource.setResourceId(resourceId + 1);
						resource.setVpUserID(userId);
						this.resource = workflowService.insertResource(resource);
					}else {
						try {
							workflowService.updateResource(resourceId, userId);
						}catch(Exception e) {
							logger.error(e);
							facesError(e.getMessage());
						}
					}

					wrkGrp.setResourceId(resourceId != null ? resourceId : this.resource.getResourceId());
					wrkGrp.setWorkGroupName(workgroup);
					workflowService.insertWorkGroup(wrkGrp);
					
					PrimeFaces.current().executeScript("PF('createUserDlg').hide()");  

					facesInfo("User created successfully.");
				} else {
					facesError("Username already exist. Please choose a different username.");
					return;
				}
			} catch (ValidationException | ProcessException e) {
				logger.error(e);
				facesError(e.getMessage());
				e.printStackTrace();
			}
			clearInputs();
		} else {
			facesError("Password does not meet complexity requirement.");
			return;
		}
	}

	public boolean isValidPassword() {

		PasswordValidator validator = new PasswordValidator(Arrays.asList(
				// length between 12 and 128 characters
				new LengthRule(12, 128),
				// at least one upper-case character
				new CharacterRule(EnglishCharacterData.UpperCase, 1),
				// at least one lower-case character
				new CharacterRule(EnglishCharacterData.LowerCase, 1),
				// at least one digit character
				new CharacterRule(EnglishCharacterData.Digit, 1),
				// no whitespace
				new WhitespaceRule()));

		RuleResult result = validator.validate(new PasswordData(pwd));
		if (result.isValid()) {
			return true;
		} else {
			facesError("Invalid password:");
			for (String msg : validator.getMessages(result)) {
				facesError(msg);
			}
			return false;
		}
	}

	private boolean userExist(String userName) {
		boolean exist = false;
		User user;
		try {
			user = userManagementService.finUserByPrincipal(userName);
			if (user != null) {
				exist = true;
			}
		} catch (ValidationException | ProcessException | NoResultException e) {
			logger.error(e);
		}
		return exist;
	}
	
	public void clearInputs() {
		userName = null;
		pwd = null;
		role = null;
		email = null;
		firstName = null;
		lastName = null;
		phoneNo = null;
		phoneCarrier = null;
		roleId = null;
		workgroup = null;
		resource = null;
		rolesTarget = null;
	}

	private String encryptPwd(String pwd) {
		
		PasswordMatcher auth = new PasswordMatcher();
		String securePassword = auth.getPasswordService().encryptPassword(pwd);
		
		return securePassword;
	}

	private void facesError(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}
	
	private void facesInfo(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isUserLocked() {
		return userLocked;
	}

	public void setUserLocked(boolean userLocked) {
		this.userLocked = userLocked;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPhoneCarrier() {
		return phoneCarrier;
	}

	public void setPhoneCarrier(String phoneCarrier) {
		this.phoneCarrier = phoneCarrier;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<Role> getRolesSource() {
		return rolesSource;
	}

	public void setRolesSource(List<Role> rolesSource) {
		this.rolesSource = rolesSource;
	}

	public List<Role> getRolesTarget() {
		return rolesTarget;
	}

	public void setRolesTarget(List<Role> rolesTarget) {
		this.rolesTarget = rolesTarget;
	}

	public DualListModel<Role> getRolesList() {
		return rolesList;
	}

	public void setRolesList(DualListModel<Role> rolesList) {
		this.rolesList = rolesList;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(User userUpdate) {
		this.userUpdate = userUpdate;
	}

	public String getWorkgroup() {
		return workgroup;
	}

	public void setWorkgroup(String workgroup) {
		this.workgroup = workgroup;
	}

	public List<Role> getUpdateRolesTarget() {
		return updateRolesTarget;
	}

	public void setUpdateRolesTarget(List<Role> updateRolesTarget) {
		this.updateRolesTarget = updateRolesTarget;
	}

	public DualListModel<Role> getUpdateRolesList() {
		return updateRolesList;
	}

	public void setUpdateRolesList(DualListModel<Role> updateRolesList) {
		this.updateRolesList = updateRolesList;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public List<Role> getUpdateRolesSource() {
		return updateRolesSource;
	}

	public void setUpdateRolesSource(List<Role> updateRolesSource) {
		this.updateRolesSource = updateRolesSource;
	}

	public Set<Integer> getIds() {
		return ids;
	}

	public void setIds(Set<Integer> ids) {
		this.ids = ids;
	}
}
