package opp.parica.megafon.dao;

/**
 * Klasa koja predstavlja sve iznimke nastale kao posljedica operacije s
 * razredom {@link DAO}.
 *
 * @author Marko Ratkovic
 * @version 1.0
 */
public class DAOException extends RuntimeException {
	/** Defaultni serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor.
	 *
	 * @param message
	 *            poruka greske
	 * @param cause
	 *            uzrok greske
	 */
	public DAOException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Kontruktor.
	 *
	 * @param message
	 *            poruka greske
	 */
	public DAOException(final String message) {
		super(message);
	}
}
