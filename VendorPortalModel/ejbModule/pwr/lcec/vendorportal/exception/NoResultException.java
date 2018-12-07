package pwr.lcec.vendorportal.exception;

public class NoResultException extends Exception {

	private static final long serialVersionUID = 8149864958551736210L;

	public NoResultException(String msg) {
		super(msg);
	}
	
	public NoResultException(Throwable throwable) {
		super(throwable);
	}
}
