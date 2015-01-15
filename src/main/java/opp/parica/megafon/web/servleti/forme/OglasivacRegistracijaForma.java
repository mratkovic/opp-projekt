package opp.parica.megafon.web.servleti.forme;

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
	private String ekstenzija = "";
	private boolean uvjetiPrihvaceni;

	@Override
	public void fillFromHttpRequest(final HttpServletRequest req) {
		oib = trimParameter(req.getParameter("oib" + ekstenzija));
		adresa = trimParameter(req.getParameter("adresa" + ekstenzija));
		email = trimParameter(req.getParameter("email" + ekstenzija));
		username = trimParameter(req.getParameter("username" + ekstenzija));
		password = req.getParameter("password" + ekstenzija);
		telefon = req.getParameter("telefon" + ekstenzija);
		id = req.getParameter("id" + ekstenzija);
		System.out.println("PARSIRANI ID: "+ id);
		uvjetiPrihvaceni = !trimParameter(req.getParameter("prihvacam" + ekstenzija)).isEmpty();
	}

	@Override
	public void fillToObject(final Object obj) {
		if (obj instanceof Oglasivac) {
			Oglasivac usr = (Oglasivac) obj;

			if (usr.getId() == null) {
				usr.setUsername(username);
				try {
					String passwordHash = new SHA1(password).getHexDigest();
					usr.setPasswordHash(passwordHash);
				} catch (Exception e) {
				}
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
			getErrors().put("username" + ekstenzija, "Polje 'Korisničko ime' je obavezno");
		} else if (DAOProvider.getDAO().postojiKorisnik(username)) {
			getErrors().put("username" + ekstenzija, "Korisničko ime vec postoji u bazi");
		}
		if (password != null && password.isEmpty()) {
			getErrors().put("password" + ekstenzija, "Polje 'Zaporka' je obavezno");
			password = "";
		}
		if (oib.isEmpty()) {
			getErrors().put("oib" + ekstenzija, "Polje 'OIB' je obavezno");
		} else if (!oib.matches("[0-9]*")) {
			getErrors().put("oib" + ekstenzija, "Polje 'OIB' smije sadržavati samo znamenke");
		} else if (oib.length() != 11) {
			getErrors().put("oib" + ekstenzija, "Polje 'OIB' neispravne duljine");
		}

		if (telefon.isEmpty()) {
			getErrors().put("telefon" + ekstenzija, "Polje 'telefon' je obavezno");
		} else if (!telefon.matches("^[+]?[0-9 /-]*")) {
			getErrors().put("telefon" + ekstenzija, "Polje 'telefon' neispravnog formata");
		}
		if (adresa.isEmpty()) {
			getErrors().put("adresa" + ekstenzija, "Polje 'adresa' je obavezno");
		}
		if (email.isEmpty()) {
			getErrors().put("email" + ekstenzija, "Polje 'E-Mail' je obavezno");
		} else if (!email.matches("[a-zA-Z0-9\\.\\-_]+[@][a-zA-Z0-9]+[\\.][a-zA-Z0-9]+")) {
			getErrors().put("email" + ekstenzija, "E-Mail nije ispravnog formata");
		}
		if (!uvjetiPrihvaceni) {
			getErrors().put("prihvacam" + ekstenzija, "Za nastavak je potrebno prihvatiti "
				+ "uvjete korištenja.");
		}

	}

	public String getId() {
		return id;
	}

	public String getOib() {
		return oib;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getAdresa() {
		return adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setEkstenzija(final String ekstenzija) {
		this.ekstenzija = ekstenzija;
	}

}
