package pwr.lcec.vendorportal.service;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pwr.lcec.vendorportal.exception.NoResultException;
import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;
import pwr.lcec.vendorportal.interfaces.UserManagementRemote;
import pwr.lcec.vendorportal.sec.entity.LoginAttempt;
import pwr.lcec.vendorportal.sec.entity.Role;
import pwr.lcec.vendorportal.sec.entity.User;
import pwr.lcec.vendorportal.sec.entity.UserRole;

@Stateless(name = "UserManagementBean", mappedName = "ejb/lcec/UserManagementProcessorBean", description = "UserManagementProcessorBean Business Facade")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserManagementService implements UserManagementRemote{

	private static Logger logger = Logger.getLogger(UserManagementService.class);

	@PersistenceContext(unitName = "VPSECPU")
	private EntityManager em;
	
	public UserManagementService() {}

	public Integer insertUser(User user) throws ValidationException, ProcessException {
		if(user == null) {
			logger.warn("User object is null");
			throw new ValidationException("User cannot be empty.");
		}
		try {
			em.persist(user);
			em.flush();
		}
		catch(Exception e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}
		return user.getUserID();
	}

	public LoginAttempt insertAttempt(LoginAttempt loginAttempt) throws ValidationException, ProcessException {

		if(loginAttempt == null) {
			logger.warn("LoginAttempt object is null");
			throw new ValidationException("LoginAttempt cannot be empty.");
		}
		try {
			em.persist(loginAttempt);
		}
		catch(Exception e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}
		return loginAttempt;
	}

	public User finUserByPrincipal(String user) throws ValidationException, ProcessException, NoResultException {
		if(StringUtils.isEmpty(user)) {
			logger.warn("ValidationException: Username is required.");
			throw new ValidationException("Username is required.");
		}
		
		User result = null;
		
		Query query = em.createNamedQuery("User.findByPrincipal");
		query.setParameter("user", user);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		
		try {
			result = (User) query.getSingleResult();
		}
		catch(javax.persistence.NoResultException nre) {
			logger.warn("NoResultException: Username " + user + " is not found.");
			throw new NoResultException("Username " + user + " is not found.");
		}
		catch(Exception e) {
			logger.error("Exception occurred: "+e.getMessage());
			throw new ProcessException(e.getMessage());
		}	
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Role> findAllRoles() {
		
		Query query = em.createNamedQuery("Role.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		
		/*List<Role> result = query.getResultList();
		result.size();*/

		return query.getResultList();
	}

	public UserRole inserUserRole(UserRole userRole) throws ValidationException, ProcessException {
		if(userRole == null) {
			logger.warn("UserRole object is null");
			throw new ValidationException("User Role cannot be empty.");
		}
		try {
			em.persist(userRole);
			em.flush();
		}
		catch(EJBException e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}
		return userRole;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		
		Query query = em.createNamedQuery("User.findAll");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		return query.getResultList();
	}

	public User updateUser(User user) throws ValidationException, ProcessException {
		if (user == null) {
			logger.warn("User object is Null.");
			throw new ValidationException("User information is null.");
		}
		try {
			deleteUserRole(user.getUserID());
			
			em.merge(user);
		} catch (EJBException e) {
			logger.error(e);
			throw new ProcessException(e.getMessage());
		}
		return user;
	}
	
	public User updateUserPwd(User user) throws ValidationException, ProcessException {
		if (user == null) {
			logger.warn("User object is Null.");
			throw new ValidationException("User information is null.");
		}
		try {
			User update = em.find(User.class, user.getUserID());
			update.setPassword(user.getPassword());
			em.merge(update);
		} catch (EJBException e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<UserRole> existingRoles(Integer userId) throws ValidationException, ProcessException{
		if(userId == null || userId == 0) {
			logger.warn("User ID is null.");
			throw new ValidationException("User ID is null.");
		}
		List<UserRole> result = null;
		
		Query query = em.createNamedQuery("UserRole.findByUserId");
		query.setParameter("userId", userId);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		try {
			result = query.getResultList();
		}catch(EJBException e) {
			logger.error(e);
			throw new ProcessException(e.getMessage());
		}
		return result;
	}

	public void deleteUserRole(Integer userId) throws ValidationException, ProcessException {
		if (userId == null) {
			throw new ValidationException("User Id is empty");
		}
		try {
			em.createNativeQuery("DELETE FROM UserRole WHERE UserID = ?").setParameter(1, userId).executeUpdate();
			em.flush();
		} catch (EJBException e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}
	}

	public UserRole updateUserRole(UserRole userRole) throws ValidationException, ProcessException {
		if (userRole == null) {
			logger.warn("UserRole object is Null.");
			throw new ValidationException("UserRole information is null.");
		}
		try {
			em.persist(userRole);
			em.flush();	
		} catch (EJBException e) {
			logger.error(e);
			throw new ProcessException(e.getMessage());
		}

		return userRole;
	}

	public User deleteUserAcct(User user) throws ValidationException, ProcessException {
		if (user == null) {
			logger.warn("User object is Null.");
			throw new ValidationException("User information is null.");
		}
		try {
			User update = em.find(User.class, user.getUserID());
			update.setEffectiveEndDate(user.getEffectiveEndDate());
			em.merge(update);
		} catch (EJBException e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}
		return user;
	}

	public User updateLastLogin(Integer userId, Timestamp lastLogin) throws ValidationException, ProcessException {

		if (userId == null) {
			logger.warn("User Id is Null.");
			throw new ValidationException("User Id is null.");
		}

		User update = null;

		try {
			update = em.find(User.class, userId);
			update.setLastLogin(lastLogin);
			em.merge(update);
		} catch (EJBException e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}

		return update;
	}
}
