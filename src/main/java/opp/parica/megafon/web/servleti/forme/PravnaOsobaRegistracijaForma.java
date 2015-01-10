package opp.parica.megafon.web.servleti.forme;

import javax.servlet.http.HttpServletRequest;

import opp.parica.megafon.model.PravnaOsoba;


public class PravnaOsobaRegistracijaForma extends OglasivacRegistracijaForma {
	/** Naziv tvrtke. */
	private String naziv;
	/** Kontakt fax. */
	private String fax;


	{
		setEkstenzija("_po");
	}

	public String getNaziv() {
		return naziv;
	}

	public String getFax() {
		return fax;
	}
	@Override
	public void fillFromHttpRequest(final HttpServletRequest req) {
		super.fillFromHttpRequest(req);
		naziv = trimParameter(req.getParameter("naziv"));
		fax = trimParameter(req.getParameter("fax"));

	}

	@Override
	public void fillToObject(final Object obj) {
		super.fillToObject(obj);
		if (obj instanceof PravnaOsoba) {
			PravnaOsoba usr = (PravnaOsoba) obj;
			usr.setNaziv(naziv);
			usr.setFax(fax);
		}

	}

	@Override
	public void fillFromObject(final Object obj) {
		super.fillFromObject(obj);
		if (obj instanceof PravnaOsoba) {
			PravnaOsoba usr = (PravnaOsoba) obj;
			naziv = usr.getNaziv();
			fax = usr.getFax();
		}

	}

	@Override
	public void validate() {
		super.validate();
		if (naziv.isEmpty()) {
			getErrors().put("naziv", "Polje 'Naziv tvrtke' je obavezno");
		}
		if (fax.isEmpty()) {
			getErrors().put("fax", "Polje 'Fax' je obavezno");
		}

	}

}
