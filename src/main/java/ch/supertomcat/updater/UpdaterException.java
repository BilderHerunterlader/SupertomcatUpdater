package ch.supertomcat.updater;

/**
 * Updater Exception
 */
public class UpdaterException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param message Message
	 * @param cause Cause
	 */
	public UpdaterException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message Message
	 */
	public UpdaterException(String message) {
		super(message);
	}

}
