package pwr.lcec.vendor.listener;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class SessionTimeoutListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(SessionTimeoutListener.class);

	private String getLoginPath() {
		return "/login.xhtml";
	}

	@Override
	public void beforePhase(final PhaseEvent event) {
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		if (!facesContext.getPartialViewContext().isAjaxRequest() || facesContext.getRenderResponse()) {
			return;
		}

		final HttpServletRequest request = HttpServletRequest.class
				.cast(facesContext.getExternalContext().getRequest());
		if (request.getDispatcherType() == DispatcherType.FORWARD && getLoginPath().equals(request.getServletPath())) {
			final String redirect = facesContext.getExternalContext().getRequestContextPath()
					+ request.getServletPath();
			try {
				facesContext.getExternalContext().redirect(redirect);
			} catch (final IOException e) {
				logger.error(e.getMessage());
			}
		}
	}

	@Override
	public void afterPhase(PhaseEvent arg0) { }

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
