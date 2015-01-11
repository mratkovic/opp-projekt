package opp.parica.megafon.web.servleti.forme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.Kategorija;
import opp.parica.megafon.pomocno.Pair;

public class KategorijaForma extends ApstraktnaWebForma {
	private static final int MAX_STAVKI = 10;
	private static Set<String> defaultneStavke;
	static {
		defaultneStavke = new HashSet<>();
		defaultneStavke.add("Cijena");
		defaultneStavke.add("Opis");
		defaultneStavke.add("Video link");
		defaultneStavke.add("Slike");
	}
	private String naziv;
	private String nadkategorija;
	private boolean jeBesplatna;
	private List<Pair<String, String>> dodatneStavke;
	private HashMap<String, String> stavkeMap;
	private int brojStavki;

	public KategorijaForma() {
		dodatneStavke = new ArrayList<>();
		stavkeMap = new HashMap<>();
		for (int i = 0; i <= MAX_STAVKI; ++i) {
			dodatneStavke.add(new Pair<String, String>("", ""));
		}
	}

	@Override
	public void fillFromHttpRequest(final HttpServletRequest req) {
		dodatneStavke = new ArrayList<>();
		naziv = trimParameter(req.getParameter("naziv"));
		nadkategorija = trimParameter(req.getParameter("kategorija"));
		brojStavki = Integer.parseInt(trimParameter(req.getParameter("brStavki")));
		if (nadkategorija != null && nadkategorija.startsWith("kat")) {
			nadkategorija = nadkategorija.substring(3, nadkategorija.length());
		}
		jeBesplatna = !trimParameter(req.getParameter("jeBesplatna")).isEmpty();
		stavkeMap.clear();

		for (int i = 0; i < brojStavki; ++i) {
			if (i >= dodatneStavke.size()) {
				dodatneStavke.add(new Pair<String, String>("", ""));
			}
			String nazivStavke = trimParameter(req.getParameter("stavka" + (i + 1)));
			String tipStavke = req.getParameter("tipStavke" + (i + 1));
			if(nazivStavke.isEmpty()) {
				getErrors().put("stavke",
					"Nazivi stavki ne smiju biti prazni.");
			}
			dodatneStavke.get(i).first = nazivStavke;
			dodatneStavke.get(i).second = tipStavke;
			if (!nazivStavke.isEmpty() && stavkeMap.get(nazivStavke) != null) {
				getErrors().put("stavke", "Nazivi stavki moraju biti jedinstveni");
			}
			if(defaultneStavke.contains(nazivStavke) ) {
				getErrors().put("stavke", "Nazivi stavki moraju biti jedinstveni");
			}
			stavkeMap.put(nazivStavke, tipStavke);
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

			kat.setJeBesplatna(jeBesplatna);

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < brojStavki; ++i) {
				sb.append(dodatneStavke.get(i).first);
				sb.append("|");
				sb.append(dodatneStavke.get(i).second);
				sb.append(";");
			}
			kat.setDodatneStavke(sb.toString());
			System.out.println("FORMAT - --- " + sb.toString());
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
			getErrors().put("naziv", "Polje 'Naziv' je obavezno");
		}
		Kategorija k = DAOProvider.getDAO().dohvatiKategoriju(naziv);
		if (k != null) {
			getErrors().put("naziv", "Postoji vec kategorija imena '" + naziv + "'");
		}
		Kategorija nadKat = DAOProvider.getDAO().dohvatiKategoriju(Long.parseLong(nadkategorija));
		// Snaga|NUM;
		List<Pair<String, String>> nasljedene = parsirajDodatneStavke(nadKat);
		for (Pair<String, String> p : nasljedene) {
			System.out.println("NASLJEDENO" + p.first);
			if (stavkeMap.get(p.first) != null) {
				getErrors().put("stavke",
					"Nazivi stavki moraju biti jedinstveni. " + p.first +
						" nasljeđena stavka od nadkategorije.");
			}
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

	public boolean isJeBesplatna() {
		return jeBesplatna;
	}

	public void setJeBesplatna(final boolean jeBesplatna) {
		this.jeBesplatna = jeBesplatna;
	}

	public List<Pair<String, String>> getDodatneStavke() {
		return dodatneStavke;
	}

	public void setDodatneStavke(final List<Pair<String, String>> dodatneStavke) {
		this.dodatneStavke = dodatneStavke;
	}

	public static List<Pair<String, String>> parsirajDodatneStavke(final Kategorija k) {
		String[] stavke = k.getDodatneStavke().split(";");
		List<Pair<String, String>> dodatneStavke = new ArrayList<>();

		for (String stavka : stavke) {
			if (!stavka.isEmpty()) {
				dodatneStavke.add(new Pair<String, String>(stavka.split("\\|")[0], ""));
			}
		}
		return dodatneStavke;
	}

	@Override
	public String toString() {
		return "KategorijaForma [naziv=" + naziv + ", nadkategorija=" + nadkategorija + ", jeBesplatna="
			+ jeBesplatna + ", dodatneStavke=" + dodatneStavke + "]";
	}

	public void initDodatneStavke() {
		for(int i = dodatneStavke.size(); i < MAX_STAVKI; ++i) {
			dodatneStavke.add(new Pair<String, String>("", ""));
		}

	}
}
