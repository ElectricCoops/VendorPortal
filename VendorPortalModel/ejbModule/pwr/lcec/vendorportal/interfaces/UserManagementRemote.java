package pwr.lcec.vendorportal.interfaces;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Remote;

import pwr.lcec.vendorportal.exception.NoResultException;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;
import pwr.lcec.vendorportal.sec.entity.LoginAttempt;
import pwr.lcec.vendorportal.sec.entity.Role;
import pwr.lcec.vendorportal.sec.entity.User;
import pwr.lcec.vendorportal.sec.entity.UserRole;

@Remote
public interface UserManagementRemote {

	public Integer insertUser(User user) throws ValidationException, ProcessException;
	
	public User finUserByPrincipal(String user) throws ValidationException, ProcessException, NoResultException;
	
	public LoginAttempt insertAttempt(LoginAttempt loginAttempt) throws ValidationException, ProcessException;
	
	public List<Role> findAllRoles();
	
	public UserRole inserUserRole(UserRole userRole) throws ValidationException, ProcessException;
	
	public List<User> findAllUsers();
	
	public User updateUser(User user) throws ValidationException, ProcessException;
	
	public UserRole updateUserRole(UserRole userRole) throws ValidationException, ProcessException;
	
	public void deleteUserRole(Integer userId) throws ValidationException, ProcessException;
	
	public List<UserRole> existingRoles(Integer userId) throws ValidationException, ProcessException;
	
	public User updateUserPwd(User user) throws ValidationException, ProcessException;
	
	public User deleteUserAcct(User user) throws ValidationException, ProcessException;
	
	public User updateLastLogin(Integer userId, Timestamp lastLogin)throws ValidationException, ProcessException;
}
