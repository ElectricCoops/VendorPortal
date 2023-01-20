package pwr.lcec.vendorportal.session;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pwr.lcec.vendorportal.entity.sec.LoginAttempt;
import pwr.lcec.vendorportal.entity.sec.Role;
import pwr.lcec.vendorportal.entity.sec.UserRole;
import pwr.lcec.vendorportal.entity.sec.UserTbl;
import pwr.lcec.vendorportal.interfaces.UserManagementLocal;

@Stateless(name = "VPUserManagementBean", mappedName = "ejb/lcec/VPUserManagementProcessorBean", description = "VPUserManagementProcessorBean Business Facade")
@Local(UserManagementLocal.class)
/*@Interceptors(LoggingInterceptor.class)*/
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserManagementSession implements UserManagementLocal{
	
	private static Logger logger = LogManager.getLogger(UserManagementSession.class);

	@PersistenceContext(unitName = "vendorPortalPU")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public UserTbl findUserByPrincipal(String username) {
		if (StringUtils.isEmpty(username)) {
			logger.warn("ValidationException: Username is required.");
			//throw new ValidationException("Username is required.");
		}

		List<UserTbl> result = null;

		Query query = em.createNamedQuery("UserTbl.findByPrincipal");
		//Query query = em.createNativeQuery("SELECT u.* FROM UserTbl u WHERE u.userName = ? AND u.effectiveEndDate IS NULL");
		query.setParameter("user", username);
		//query.setParameter(1, user);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		try {
			result = query.getResultList();
		} catch (NoResultException nre) {
			logger.warn("NoResultException: Username " + username + " is not found.");
			throw new NoResultException("Username " + username + " is not found.");
		} catch (Exception e) {
			logger.error("Exception occurred: " + e.getMessage());
			//throw new ProcessException(e.getMessage());
		}
		
		if(result.size() == 1) {
			return result.get(0);	
		}else if(result == null || result.size() == 0) {
			return null;
		}else {
			throw new NonUniqueResultException();
		}
	}

	@Override
	public LoginAttempt insertAttempt(LoginAttempt loginAttempt) {

		if (loginAttempt == null) {
		      logger.warn("LoginAttempt object is null");
		      //throw new ValidationException("LoginAttempt cannot be empty.");
		    } 
		    try {
		      em.persist(loginAttempt);
		    }
		    catch (Exception e) {
		      logger.error(e.getMessage());
		      //throw new ProcessException(e.getMessage());
		    } 
		    return loginAttempt;
		
	}

	@Override
	public UserTbl insertUser(UserTbl user) {

		if (user == null) {
		      logger.warn("User object is null");
		      //throw new ValidationException("User cannot be empty.");
		    } 
		    try {
		      em.persist(user);
		      em.flush();
		    }
		    catch (Exception e) {
		      logger.error(e.getMessage());
		      //throw new ProcessException(e.getMessage());
		    } 
		    return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isUsernameAvailable(String username) {
		
		List<UserTbl> results = null;
	    
	    Query query = em.createNamedQuery("UserTbl.findByPrincipal");
	    query.setParameter("user", username);
	    query.setHint("javax.persistence.cache.storeMode", "REFRESH");
	    
	    try {
	      results = query.getResultList();
	    } catch (Exception e) {
	      
	      e.printStackTrace();
	    } 
	    
	    if (results.size() == 1)
	      return false; 
	    if (results == null || results.isEmpty()) {
	      return true;
	    }
	    throw new NonUniqueResultException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findAllRoles() {

		Query query = this.em.createNamedQuery("Role.findAll");
	    query.setHint("javax.persistence.cache.storeMode", "REFRESH");

	    return query.getResultList();
		
	}

	@Override
	public UserRole insertUserRole(UserRole userRole) {

		if (userRole == null) {
		      logger.warn("UserRole object is null");
		      //throw new ValidationException("User Role cannot be empty.");
		    } 
		    try {
		      em.persist(userRole);
		      em.flush();
		    }
		    catch (EJBException e) {
		      logger.error(e.getMessage());
		      //throw new ProcessException(e.getMessage());
		    } 
		    return userRole;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserTbl> findAllUsers() {
		
		List<UserTbl> result = null;
	    
	    Query query = this.em.createNamedQuery("UserTbl.findAll");
	    query.setHint("javax.persistence.cache.storeMode", "REFRESH");
	    
	    try {
	      result = query.getResultList();
	    } catch (Exception e) {
	      logger.error(e);
	    } 
	    
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserTbl> findActiveUsers() {

		List<UserTbl> result = null;
	    
	    Query query = this.em.createNamedQuery("UserTbl.findActive");
	    query.setHint("javax.persistence.cache.storeMode", "REFRESH");
	    
	    try {
	      result = query.getResultList();
	    } catch (Exception e) {
	      logger.error(e);
	    } 
	    
	    return result;
	    
	}

	@Override
	public UserTbl updateUser(UserTbl user) {

		if (user == null) {
		      logger.warn("User object is Null.");
		      //throw new ValidationException("User information is null.");
		    } 
		    try {
		      //deleteUserRole(user.getUserID());
		      
		      em.merge(user);
		    } catch (EJBException e) {
		      logger.error(e);
		      //throw new ProcessException(e.getMessage());
		    } 
		    return user;
		    
	}

	@Override
	public UserRole updateUserRole(UserRole userRole) {

		if (userRole == null) {
		      logger.warn("UserRole object is Null.");
		      //throw new ValidationException("UserRole information is null.");
		    } 
		    try {
		      em.persist(userRole);
		      em.flush();
		    } catch (EJBException e) {
		      logger.error(e);
		      //throw new ProcessException(e.getMessage());
		    } 
		    
		    return userRole;
	}

	@Override
	public void deleteUserRole(Integer userId) {

		if (userId == null) {
		      //throw new ValidationException("User Id is empty");
	    }
		
	    try {
	      em.createNativeQuery("DELETE FROM sec.UserRole WHERE UserID = ?").setParameter(1, userId).executeUpdate();
	      em.flush();
	    } catch (EJBException e) {
	      logger.error(e.getMessage());
	      //throw new ProcessException(e.getMessage());
	    } 
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> existingRoles(Integer userId) {

		if (userId == null || userId.intValue() == 0) {
		      logger.warn("User ID is null.");
		      //throw new ValidationException("User ID is null.");
		    } 
		    List<UserRole> result = null;
		    
		    Query query = this.em.createNamedQuery("UserRole.findByUserId");
		    query.setParameter("userId", userId);
		    query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		    try {
		      result = query.getResultList();
		    } catch (EJBException e) {
		      logger.error(e);
		      //throw new ProcessException(e.getMessage());
		    } 
		    return result;
	}

	@Override
	public UserTbl updateUserPwd(UserTbl user) {
		
		if (user == null) {
		      logger.warn("User object is Null.");
		      //throw new ValidationException("User information is null.");
		    } 
		    try {
		    	UserTbl update = (UserTbl)em.find(UserTbl.class, user.getUserID());
		      update.setPassword(user.getPassword());
		      this.em.merge(update);
		    } catch (EJBException e) {
		      logger.error(e.getMessage());
		      //throw new ProcessException(e.getMessage());
		    } 
		    return user;
		
	}

	@Override
	public UserTbl deleteUserAcct(UserTbl user) {

		if (user == null) {
		      logger.warn("User object is Null.");
		      //throw new ValidationException("User information is null.");
		    } 
		    try {
		      UserTbl update = (UserTbl) em.find(UserTbl.class, user.getUserID());
		      update.setEffectiveEndDate(user.getEffectiveEndDate());
		      this.em.merge(update);
		    } catch (EJBException e) {
		      logger.error(e.getMessage());
		      //throw new ProcessException(e.getMessage());
		    } 
		    return user;
		
	}

	@Override
	public UserTbl updateLastLogin(Integer userId, Timestamp lastLogin) {
		
		if (userId == null) {
		      logger.warn("User Id is Null.");
		      //throw new ValidationException("User Id is null.");
		    } 
		    
		    UserTbl update = null;
		    
		    try {
		      update = (UserTbl)em.find(UserTbl.class, userId);
		      update.setLastLogin(lastLogin);
		      this.em.merge(update);
		    } catch (EJBException e) {
		      logger.error(e.getMessage());
		      //throw new ProcessException(e.getMessage());
		    } 
		    
		    return update;
	}

}
