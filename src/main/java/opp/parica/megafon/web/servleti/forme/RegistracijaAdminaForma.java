package opp.parica.megafon.web.servleti.forme;

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
	/** Ime korisnika. */
	private String ime;
	/** Prezime korisnika. */
	private String prezime;

	/**
	 * Metoda koja dohvaca identifikator.
	 *
	 * @return the id
	 */
	public final String getId() {
		return id;
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
	 * Metoda koja dohvaca zaporke.
	 *
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	@Override
	public void fillFromHttpRequest(final HttpServletRequest req) {
		username = trimParameter(req.getParameter("username"));
		password = req.getParameter("password");
		ime = trimParameter(req.getParameter("ime"));
		prezime = trimParameter(req.getParameter("prezime"));
		id = trimParameter(req.getParameter("id"));
	}

	@Override
	public void fillToObject(final Object obj) {
		if (obj instanceof Admin) {
			Admin usr = (Admin) obj;
			usr.setUsername(username);
			if (usr.getId() == null) {
				usr.setUsername(username);
				String hash = new SHA1(password).getHexDigest();
				usr.setPasswordHash(hash);
			}
			usr.setIme(ime);
			usr.setPrezime(prezime);
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
			ime = usr.getIme();
			prezime = usr.getPrezime();
		}
	}

	@Override
	public void validate() {
		getErrors().clear();
		if (username.isEmpty()) {
			getErrors().put("username", "Korisničko ime prazno");
		} else if (DAOProvider.getDAO().postojiKorisnik(username)) {
			getErrors().put("username", "Korisničko ime vec postoji u bazi");
		}
		if (password == null || password.isEmpty()) {
			getErrors().put("password", "Lozinka prazna");
		}
		if (ime.isEmpty()) {
			getErrors().put("ime", "Polje 'Ime' je obavezno");
		}
		if (prezime.isEmpty()) {
			getErrors().put("prezime", "Polje 'Prezime' je obavezno");
		}
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

}
