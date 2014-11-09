package opp.parica.megafon.web.servlets.forme;

import javax.servlet.http.HttpServletRequest;

import opp.parica.megafon.model.FizickaOsoba;

public class FizickaOsobaRegistracijaForma extends OglasivacRegistracijaForma {
	/** Ime korisnika. */
	private String ime;
	/** Prezime korisnika. */
	private String prezime;
	{
		setEkstenzija("_fo");
	}
	@Override
	public void fillFromHttpRequest(final HttpServletRequest req) {

		super.fillFromHttpRequest(req);
		ime = trimParameter(req.getParameter("ime"));
		prezime = trimParameter(req.getParameter("prezime"));

	}

	@Override
	public void fillToObject(final Object obj) {
		super.fillToObject(obj);
		if (obj instanceof FizickaOsoba) {
			FizickaOsoba usr = (FizickaOsoba) obj;
			usr.setIme(ime);
			usr.setPrezime(prezime);
		}

	}

	@Override
	public void fillFromObject(final Object obj) {
		super.fillFromObject(obj);
		if (obj instanceof FizickaOsoba) {
			FizickaOsoba usr = (FizickaOsoba) obj;
			ime = usr.getIme();
			prezime = usr.getPrezime();
		}

	}

	@Override
	public void validate() {
		super.validate();
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
