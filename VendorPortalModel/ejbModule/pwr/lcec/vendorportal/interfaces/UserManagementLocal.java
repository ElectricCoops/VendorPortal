package pwr.lcec.vendorportal.interfaces;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Local;

import pwr.lcec.vendorportal.entity.sec.LoginAttempt;
import pwr.lcec.vendorportal.entity.sec.Role;
import pwr.lcec.vendorportal.entity.sec.UserRole;
import pwr.lcec.vendorportal.entity.sec.UserTbl;

@Local
public interface UserManagementLocal {

	public UserTbl findUserByPrincipal(String username);

	public LoginAttempt insertAttempt(LoginAttempt attempt);

	public UserTbl insertUser(UserTbl user);
	
	public boolean isUsernameAvailable(String username);

	public List<Role> findAllRoles();

	public UserRole insertUserRole(UserRole userRole);

	public List<UserTbl> findAllUsers();

	public List<UserTbl> findActiveUsers();

	public UserTbl updateUser(UserTbl user);

	public UserRole updateUserRole(UserRole userRole);

	public void deleteUserRole(Integer userId);

	public List<UserRole> existingRoles(Integer paramInteger);

	public UserTbl updateUserPwd(UserTbl user);

	public UserTbl deleteUserAcct(UserTbl user);

	public UserTbl updateLastLogin(Integer userId, Timestamp lastLogin);

}
