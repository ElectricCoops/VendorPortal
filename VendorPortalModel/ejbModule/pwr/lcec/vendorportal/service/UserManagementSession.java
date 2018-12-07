package pwr.lcec.vendorportal.service;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import pwr.lcec.vendorportal.exception.ProcessException;
import pwr.lcec.vendorportal.exception.ValidationException;
import pwr.lcec.vendorportal.interfaces.UserManagementRemote;
import pwr.lcec.vendorportal.sec.entity.LoginAttempt;
import pwr.lcec.vendorportal.sec.entity.User;

@Stateless(name = "UserManagementBean", mappedName = "ejb/lcec/UserManagementProcessorBean", description = "UserManagementProcessorBean Business Facade")
@Remote({ UserManagementRemote.class })
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserManagementSession implements UserManagementRemote{

	private static Logger logger = Logger.getLogger(UserManagementSession.class);

	@PersistenceContext(unitName = "VPSECPU")
	private EntityManager em;
	
	public UserManagementSession() {}

	public User insertUser(User user) throws ValidationException, ProcessException {
		if(user == null) {
			logger.warn("User object is null");
			throw new ValidationException("User cannot be empty.");
		}
		try {
			em.merge(user);
		}
		catch(Exception e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}
		return user;
	}


	public LoginAttempt insertAttempt(LoginAttempt loginAttempt) throws ValidationException, ProcessException {

		if(loginAttempt == null) {
			logger.warn("LoginAttempt object is null");
			throw new ValidationException("LoginAttempt cannot be empty.");
		}
		try {
			em.merge(loginAttempt);
		}
		catch(Exception e) {
			logger.error(e.getMessage());
			throw new ProcessException(e.getMessage());
		}
		return loginAttempt;
	}

}
