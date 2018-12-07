package pwr.lcec.vendorportal.exception;

public class ProcessException extends Exception {

	private static final long serialVersionUID = 6910068095135377761L;

	public ProcessException(String msg) {
		super(msg);
	}
	
	public ProcessException(Throwable throwable) {
		super(throwable);
	}
}
