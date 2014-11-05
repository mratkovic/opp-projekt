package opp.parica.megafon.web.servlets.forme;

import javax.servlet.http.HttpServletRequest;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.hash.SHA1;
import opp.parica.megafon.model.Admin;

public class RegistracijaAdminaForma extends ApstraktnaWebForma {
	/** Identifikator. */
	private String id;
	/** Korisnicko ime za prijavu */
	private String username;
	/** Hash lozinke. */
	private String password;

	/**
	 * Metoda koja dohvaca identifikator.
	 *
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * Metoda koja postavlja vrijednost identifikatora.
	 *
	 * @param id
	 *            the id to set
	 */
	public final void setId(final String id) {
		this.id = id;
	}

	/**
	 * Metoda koja dohvaca korisnicko ime.
	 *
	 * @return korisnicko ime
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Metoda koja postavlja korisnicko ime.
	 *
	 * @param username
	 *            korisnicko ime
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * Metoda koja dohvaca zaporke.
	 *
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * Metoda koja postavlja vrijednost zaporke korisnika.
	 *
	 * @param password
	 *            the password to set
	 */
	public final void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public void fillFromHttpRequest(final HttpServletRequest req) {
		username = trimParameter(req.getParameter("username"));
		password = trimParameter(req.getParameter("password"));

	}

	@Override
	public void fillToObject(final Object obj) {
		if (obj instanceof Admin) {
			Admin usr = (Admin) obj;
			usr.setUsername(username);
			String hash = new SHA1(password).getHexDigest();
			usr.setPasswordHash(hash);
		}

	}

	@Override
	public void fillFromObject(final Object obj) {
		if (obj instanceof Admin) {
			Admin usr = (Admin) obj;
			if (usr.getId() != null) {
				id = usr.getId().toString();
			} else {
				id = null;
			}
			username = usr.getUsername();
			password = null;
		}
	}

	@Override
	public void validate() {
		getErrors().clear();
		if (username == null || username.length() == 0) {
			getErrors().put("username", "Korisničko ime prazno");
		} else if (DAOProvider.getDAO().postojiKorisnik(username)) {
			getErrors().put("username", "Korisničko ime vec postoji u bazi");
		}if (password == null || password.isEmpty()) {
			getErrors().put("password", "Lozinka prazna");
		}
	}

}
