package opp.parica.megafon.web.servleti.forme;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.Oglas;

public class PretragaForma extends ApstraktnaWebForma {
	private String gornjaCijena;
	private String donjaCijena;
	private String kategorija;
	private String naziv;

	private float donja;
	private long katID;
	private float gornja;

	public PretragaForma() {
		gornjaCijena = "";
		donjaCijena = "";
		kategorija = "";
		naziv = "";
	}

	@Override
	public void fillFromHttpRequest(final HttpServletRequest req) {
		naziv = req.getParameter("naziv");
		gornjaCijena = trimParameter(req.getParameter("gornjaCijena"));
		donjaCijena = trimParameter(req.getParameter("donjaCijena"));
		kategorija = trimParameter(req.getParameter("kategorija"));
		donja = -1;
		gornja = 1e6f;
		katID = -1;
	}

	@Override
	public void fillToObject(final Object obj) {
		// NOT USED

	}

	@Override
	public void fillFromObject(final Object obj) {
		// NOT USED

	}

	@Override
	public void validate() {
		try {
			if (!donjaCijena.isEmpty()) {
				donja = Float.parseFloat(donjaCijena);
			}
			if (!gornjaCijena.isEmpty()) {
				gornja = Float.parseFloat(gornjaCijena);
			}
		} catch (Exception ex) {
			getErrors()
				.put("greska",
					"Polje 'Raspon cijena' je pogrešnog formata. Očekivana numerička vrijednost. Točka separator decimalnog dijela");
		}
		try {
			katID = Long.parseLong(kategorija);
		} catch (NumberFormatException ex) {
		}
		if (donjaCijena.isEmpty() && gornjaCijena.isEmpty() && naziv.isEmpty() && katID == -1) {
			getErrors()
				.put("greska",
					"Potrebno navesti bar jedan kriterij pretrage");
		}
	}

	public List<Oglas> obaviUpit() {
		List<Oglas> oglasi = DAOProvider.getDAO().dohvatiSveOglase();
		int it = 0;
		for (Oglas o : new ArrayList<>(oglasi)) {

			if (!naziv.isEmpty() && !o.getNaslov().toUpperCase()
				.matches(".*" + naziv.toUpperCase() + ".*")) {
				oglasi.remove(it);
			} else if (katID != -1 && o.getPripadaKategoriji().getId() != katID) {
				oglasi.remove(it);
			} else if (o.getCijena() < donja || o.getCijena() > gornja) {
				oglasi.remove(it);
			} else {
				it++;
			}
		}
		return oglasi;
		// return DAOProvider.getDAO().dohvatiOglase(naziv, katID, donja,
		// gornja);
	}

	public String getGornjaCijena() {
		return gornjaCijena;
	}

	public void setGornjaCijena(final String gornjaCijena) {
		this.gornjaCijena = gornjaCijena;
	}

	public String getDonjaCijena() {
		return donjaCijena;
	}

	public void setDonjaCijena(final String donjaCijena) {
		this.donjaCijena = donjaCijena;
	}

	public String getKategorija() {
		return kategorija;
	}

	public void setKategorija(final String kategorija) {
		this.kategorija = kategorija;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(final String naziv) {
		this.naziv = naziv;
	}

	public Object getKriterijiString() {
		StringBuilder sb = new StringBuilder();
		if (!naziv.isEmpty()) {
			sb.append("naziv sadrži '" + naziv + "', ");
		}
		if (katID != -1) {
			sb.append("oglasi pripadaju kategoriji '" + DAOProvider.getDAO().dohvatiKategoriju(katID).getNaziv()
				+ "', ");
		}
		if (!donjaCijena.isEmpty() || !gornjaCijena.isEmpty()) {
			sb.append("raspon cijene od" + donja + " do " + gornja + ", ");
		} else if (!donjaCijena.isEmpty()) {
			sb.append("cijena veća od " + donja + ", ");
		} else if (!gornjaCijena.isEmpty()) {
			sb.append("cijena manja od " + gornja + ", ");
		}

		int len = sb.length();
		return "Kriteriji pretrage: " + sb.toString().substring(0, len - 2);

	}

}
