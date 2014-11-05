package opp.parica.megafon.web.servlets.forme;

import javax.servlet.http.HttpServletRequest;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.hash.SHA1;
import opp.parica.megafon.model.Oglasivac;

public class OglasivacRegistracijaForma extends ApstraktnaWebForma {

	/** Identifikator korisnika. */
	private String id;
	/** OIB oglasivaca. */
	private String oib;
	/** Kontakt mail korisika. */
	private String email;
	/** Korisnicko ime za prijavu */
	private String username;
	/** Lozinka. */
	private String password;
	/** Adresa oglasivaca. */
	private String adresa;
	/** Kontakt telefon */
	private String telefon;

	private boolean jePrihvaceno;

	public boolean isJePrihvaceno() {
		return jePrihvaceno;
	}

	public void setJePrihvaceno(final boolean jePrihvaceno) {
		this.jePrihvaceno = jePrihvaceno;
	}

	private String extension = "";

	public String getExtension() {
		return extension;
	}

	public void setExtension(final String extension) {
		this.extension = extension;
	}

	@Override
	public void fillFromHttpRequest(final HttpServletRequest req) {
		oib = trimParameter(req.getParameter("oib" + extension));
		adresa = trimParameter(req.getParameter("adresa" + extension));
		email = trimParameter(req.getParameter("email" + extension));
		username = trimParameter(req.getParameter("username" + extension));
		password = req.getParameter("password" + extension);
		telefon = req.getParameter("telefon" + extension);

		jePrihvaceno = !trimParameter(req.getParameter("prihvacam" + extension)).isEmpty();
	}

	@Override
	public void fillToObject(final Object obj) {
		if (obj instanceof Oglasivac) {
			Oglasivac usr = (Oglasivac) obj;
			usr.setUsername(username);
			try {
				String passwordHash = new SHA1(password).getHexDigest();
				usr.setPasswordHash(passwordHash);
			} catch (Exception e) {
			}

			usr.setAdresa(adresa);
			usr.setTelefon(telefon);
			usr.setEmail(email);
			usr.setOib(oib);
		}

	}

	@Override
	public void fillFromObject(final Object obj) {
		if (obj instanceof Oglasivac) {
			Oglasivac usr = (Oglasivac) obj;
			if (usr.getId() != null) {
				id = usr.getId().toString();
			} else {
				id = null;
			}
			username = usr.getUsername();
			password = null;
			email = usr.getEmail();
			oib = usr.getOib();
			adresa = usr.getAdresa();
			telefon = usr.getTelefon();
		}

	}

	@Override
	public void validate() {
		getErrors().clear();
		if (username.isEmpty()) {
			getErrors().put("username" + extension, "Polje 'Korisničko ime' je obavezno");
		} else if (DAOProvider.getDAO().postojiKorisnik(username)) {
			getErrors().put("username" + extension, "Korisničko ime vec postoji u bazi");
		}
		if (password.isEmpty()) {
			getErrors().put("password" + extension, "Polje 'Zaporka' je obavezno");
			password = "";
		}
		if (oib.isEmpty()) {
			getErrors().put("oib" + extension, "Polje 'OIB' je obavezno");
		} else if (!oib.matches("[0-9]*")) {
			getErrors().put("oib" + extension, "Polje 'OIB' smije sadržavati samo znamenke");
		} else if (oib.length() != 11) {
			getErrors().put("oib" + extension, "Polje 'OIB' neispravne duljine");
		}

		if (telefon.isEmpty()) {
			getErrors().put("telefon" + extension, "Polje 'telefon' je obavezno");
		} else if (!oib.matches("^[+]?[0-9 /-]*")) {
			getErrors().put("oib" + extension, "Polje 'telefon' neispravnog formata");
		}
		if (adresa.isEmpty()) {
			getErrors().put("adresa" + extension, "Polje 'adresa' je obavezno");
		}
		if (email.isEmpty()) {
			getErrors().put("email" + extension, "Polje 'E-Mail' je obavezno");
		} else if (!email.matches("[a-zA-Z0-9\\.\\-_]+[@][a-zA-Z0-9]+[\\.][a-zA-Z0-9]+")) {
			getErrors().put("email" + extension, "E-Mail nije ispravnog formata");
		}
		if (!jePrihvaceno) {
			getErrors().put("prihvacam" + extension, "Za nastavak je potrebno prihvatiti "
				+ "uvjete korištenja.");
		}

	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getOib() {
		return oib;
	}

	public void setOib(final String oib) {
		this.oib = oib;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(final String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(final String telefon) {
		this.telefon = telefon;
	}
}
