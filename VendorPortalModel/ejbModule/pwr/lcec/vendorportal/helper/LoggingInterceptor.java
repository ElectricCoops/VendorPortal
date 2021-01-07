package pwr.lcec.vendorportal.helper;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingInterceptor {
	
	private static Logger logger = LogManager.getLogger(LoggingInterceptor.class);

	@AroundInvoke
	public Object log(InvocationContext ctx) throws Exception {
		logger.debug("Start: [" + ctx.getTarget().toString() + "." + ctx.getMethod().getName() + "]");

		long start = System.currentTimeMillis();

		try {
			return ctx.proceed();
		} catch (Exception e) {
			throw e;
		} finally {

			long time = System.currentTimeMillis() - start;
			logger.debug("End: [" + ctx.getTarget().toString() + "." + ctx.getMethod().getName() + "] - Total Time: "
					+ time + "ms.");
		}
	}

}
