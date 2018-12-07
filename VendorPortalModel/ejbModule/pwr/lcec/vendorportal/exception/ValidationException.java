package pwr.lcec.vendorportal.exception;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 1881998514833388985L;

	public ValidationException(String msg) {
		super(msg);
	}
	
	public ValidationException(Throwable throwable) {
		super(throwable);
	}

}
