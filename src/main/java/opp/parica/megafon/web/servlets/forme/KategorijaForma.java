package opp.parica.megafon.web.servlets.forme;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.Kategorija;
import opp.parica.megafon.pomocno.Pair;

public class KategorijaForma extends ApstraktnaWebForma {

	private String naziv;
	private String nadkategorija;
	private String jeBesplatna;
	private List<Pair<String, String>> dodatneStavke;

	@Override
	public void fillFromHttpRequest(final HttpServletRequest req) {
		dodatneStavke = new ArrayList<>();
		naziv = trimParameter(req.getParameter("naziv"));
		nadkategorija = req.getParameter("nadkategorija");
		jeBesplatna = trimParameter(req.getParameter("jeBesplatna"));

		int cntr = 0;
		while (true) {

			String nazivStavke = req.getParameter("stavka" + cntr);
			if (nazivStavke == null) {
				break;
			}
			String tipStavke = req.getParameter("tip" + cntr);
			dodatneStavke.add(new Pair<String, String>(nazivStavke, tipStavke));
			cntr++;
		}
	}

	@Override
	public void fillToObject(final Object obj) {
		if (obj instanceof Kategorija) {
			Kategorija kat = (Kategorija) obj;
			kat.setNaziv(naziv);
			kat.setNadkategorija(
				DAOProvider.getDAO().dohvatiKategoriju(
					Long.parseLong(nadkategorija)));
			kat.setJeBesplatna(Boolean.parseBoolean(jeBesplatna));

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < dodatneStavke.size(); ++i) {
				sb.append(dodatneStavke.get(i).first);
				sb.append("|");
				if (dodatneStavke.get(i).second.equals("tekst")) {
					sb.append("txt");
				} else {
					sb.append("NUM");
				}
				sb.append(";");
			}
			kat.setDodatneStavke(sb.toString());

		} else {
			throw new IllegalArgumentException();
		}

	}

	@Override
	public void fillFromObject(final Object obj) {
		// ne koristi se

	}

	@Override
	public void validate() {
		if (naziv.isEmpty()) {
			getErrors().put("ime", "Polje 'Ime' je obavezno");
		}
		Set<String> nazivi = new HashSet<>();
		for (int i = 0; i < dodatneStavke.size(); ++i) {
			nazivi.add(dodatneStavke.get(i).first);
		}

		if (dodatneStavke.size() > nazivi.size()) {
			getErrors().put("stavke", "Svaka dodatna stavka mora imati jedinstven naziv");
		}

	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(final String naziv) {
		this.naziv = naziv;
	}

	public String getNadkategorija() {
		return nadkategorija;
	}

	public void setNadkategorija(final String nadkategorija) {
		this.nadkategorija = nadkategorija;
	}

	public String getJeBesplatna() {
		return jeBesplatna;
	}

	public void setJeBesplatna(final String jeBesplatna) {
		this.jeBesplatna = jeBesplatna;
	}

	public List<Pair<String, String>> getDodatneStavke() {
		return dodatneStavke;
	}

	public void setDodatneStavke(final List<Pair<String, String>> dodatneStavke) {
		this.dodatneStavke = dodatneStavke;
	}

}
