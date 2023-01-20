package pwr.lcec.vendor.web.filter;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class ViewExpiredExceptionHandlerFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory factory;

	public ViewExpiredExceptionHandlerFactory(ExceptionHandlerFactory factory) {
		this.factory = factory;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		ExceptionHandler handler = factory.getExceptionHandler();
		handler = new ViewExpiredExceptionHandler(handler);
		return handler;
	}

}
