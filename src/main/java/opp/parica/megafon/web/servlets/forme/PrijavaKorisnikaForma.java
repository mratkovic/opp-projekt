package opp.parica.megafon.web.servlets.forme;

import javax.servlet.http.HttpServletRequest;

import opp.parica.megafon.hash.SHA1;
import opp.parica.megafon.model.Admin;
import opp.parica.megafon.model.Oglasivac;

/**
 * Klasa koja modelira formu za onos prilikom logiranja.
 *
 * @author Marko Ratkovic
 * @version 1.0
 */
public class PrijavaKorisnikaForma extends ApstraktnaWebForma {
	/** Korisnicko ime. */
	private String username;
	/** Lozeinka. */
	private String password;

	@Override
	public final void fillFromHttpRequest(final HttpServletRequest req) {
		username = trimParameter(req.getParameter("username"));
		password = req.getParameter("password");
	}

	@Override
	public final void fillToObject(final Object obj) {
		if (obj instanceof Oglasivac) {
			Oglasivac usr = (Oglasivac) obj;
			usr.setUsername(username);
			String hash = new SHA1(password).getHexDigest();
			usr.setPasswordHash(hash);

		} else if (obj instanceof Admin) {
			Admin usr = (Admin) obj;
			usr.setUsername(username);
			String hash = new SHA1(password).getHexDigest();
			usr.setPasswordHash(hash);
		}
	}

	@Override
	public final void validate() {
		getErrors().clear();
		if (username.isEmpty()) {
			getErrors().put("username", "Korisničko ime prazno");
		}
		if (password == null || password.isEmpty()) {
			getErrors().put("password", "Lozinka prazna");
			password = "";
		}
	}

	/**
	 * Metoda koja dohvaca korisnicko ime.
	 *
	 * @return the korisnickoIme
	 */
	public final String getUsername() {
		return username;
	}

	/**
	 * Metoda koja postavlja vrijednost korisnickom imena.
	 *
	 * @param nick
	 *            the nick to set
	 */
	public final void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * Metoda koja dohvaca lozinku.
	 *
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * Metoda koja postavlja vrijednost lozinke.
	 *
	 * @param password
	 *            the password to set
	 */
	public final void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public void fillFromObject(final Object obj) {
		// ne koristi se
	}

}
