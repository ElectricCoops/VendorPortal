package pwr.lcec.vendorportal.helper;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

public class LoggingInterceptor {
	
	private static Logger logger = Logger.getLogger(LoggingInterceptor.class);

	@AroundInvoke
	public Object log(InvocationContext ctx) throws Exception {

		logger.info("Start: [" + ctx.getTarget().toString() + "." + ctx.getMethod().getName() + "]");

		long start = System.currentTimeMillis();

		try {
			return ctx.proceed();
		} 
		catch (Exception e) {
			throw e;
		} 
		finally {
			long time = System.currentTimeMillis() - start;
			logger.info("End: [" + ctx.getTarget().toString() + "." + ctx.getMethod().getName() + "] - Total Time: " + time + "ms.");
		}
	}
}
