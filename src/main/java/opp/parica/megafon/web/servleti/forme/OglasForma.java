package opp.parica.megafon.web.servleti.forme;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.DodatnaStavka;
import opp.parica.megafon.model.Kategorija;
import opp.parica.megafon.model.Oglas;
import opp.parica.megafon.model.Slika;
import opp.parica.megafon.pomocno.Pair;
import opp.parica.megafon.pomocno.Potpora;

public class OglasForma extends ApstraktnaWebForma {

	private String id;
	private Long kategorijaID;
	private Kategorija kategorija;
	private String naslov;
	private String opis;
	private String cijena;
	private String videoURL;
	private String jeSkriven;
	private String datum;

	private List<Pair<String, String>> dodatneStavke = new ArrayList<>();
	private List<String> slikeID = new ArrayList<>();

	@Override
	public void fillFromHttpRequest(final HttpServletRequest req) {
		id = req.getParameter("id");

		dodatneStavke = new ArrayList<>();
		naslov = trimParameter(req.getParameter("naslov"));
		jeSkriven = trimParameter(req.getParameter("jeSkriven"));
		opis = trimParameter(req.getParameter("opis"));
		cijena = trimParameter(req.getParameter("cijena"));
		videoURL = trimParameter(req.getParameter("videoURL"));
		kategorijaID = Long.parseLong(req.getParameter("kategorijaID"));

		String[] stavke = DAOProvider.getDAO().dohvatiKategoriju(kategorijaID).getDodatneStavke().split(";");

		for (int i = 0; i < stavke.length && !stavke[i].isEmpty(); ++i) {
			String nazivStavke = stavke[i].split("\\|")[0];
			String vrijednost = trimParameter(req.getParameter(nazivStavke));
			dodatneStavke.add(new Pair<String, String>(nazivStavke, vrijednost));
			System.out.println("Dodano  " + new Pair<String, String>(nazivStavke, vrijednost));
		}
	}

	@Override
	public void fillToObject(final Object obj) {
		if (obj instanceof Oglas) {
			Oglas oglas = (Oglas) obj;
			oglas.setNaslov(naslov);
			oglas.setCijena(Float.parseFloat(cijena));
			oglas.setVideoURL(videoURL);
			oglas.setOpis(opis);
			oglas.setPripadaKategoriji(DAOProvider.getDAO().dohvatiKategoriju(kategorijaID));
		} else {
			throw new IllegalArgumentException();
		}

	}

	public void spremiStavke(final Oglas o) {
		for (Pair<String, String> dodatno : dodatneStavke) {
			DodatnaStavka s = new DodatnaStavka();
			s.setNaziv(dodatno.first);
			s.setVrijednost(dodatno.second);
			s.setOglasVlasnik(o);
			DAOProvider.getDAO().dodajStavku(s);
		}
	}

	@Override
	public void fillFromObject(final Object obj) {
		if (obj instanceof Oglas) {
			Oglas oglas = (Oglas) obj;
			id = oglas.getId().toString();
			kategorijaID = oglas.getPripadaKategoriji().getId();
			kategorija = oglas.getPripadaKategoriji();
			naslov = oglas.getNaslov();
			opis = oglas.getOpis();
			cijena = oglas.getCijena().toString();
			videoURL = oglas.getVideoURL();
			jeSkriven = oglas.getJeSkriven().toString();

			datum = Potpora.formatirajDatum(oglas.getDatumObjave());

			for (DodatnaStavka stavka : oglas.getStavke()) {
				dodatneStavke.add(new Pair<String, String>(stavka.getNaziv(), stavka.getVrijednost()));
			}

			for (Slika s : oglas.getSlike()) {
				slikeID.add(s.getId().toString());
			}

		} else {
			throw new IllegalArgumentException();
		}

	}

	@Override
	public void validate() {
		if (naslov.isEmpty()) {
			getErrors().put("naslov", "Polje 'Naslov' je obavezno");
		}
		if (opis.isEmpty()) {
			getErrors().put("opis", "Polje 'Opis' je obavezno");
		}
		if (cijena.isEmpty()) {
			getErrors().put("cijena", "Polje 'Cijena' je obavezno");
		} else {
			try {
				Float.parseFloat(cijena);
			} catch (NumberFormatException ex) {
				getErrors()
					.put("cijena",
						"Polje 'Cijena' je pogrešnog formata. Očekivana numerička vrijednost. Točka separator decimalnog dijela");
			}
		}

		if (videoURL.isEmpty()) {
			getErrors().put("videoURL", "Polje 'videoURL' je obavezno");
		}

		String[] dodatne = DAOProvider.getDAO().dohvatiKategoriju(kategorijaID).
			getDodatneStavke().split(";");
		for (int i = 0; i < dodatne.length && !dodatne[i].isEmpty(); ++i) {
			String nazivPolja = dodatneStavke.get(i).first;
			String uneseno = dodatneStavke.get(i).second;
			if (uneseno == null || uneseno.isEmpty()) {
				getErrors().put(nazivPolja, "Polje '" + nazivPolja + "' je obavezno");
			}
		}

	}

	public void setKategorijaID(final Long kategorijaID) {
		this.kategorijaID = kategorijaID;

		Kategorija k = DAOProvider.getDAO().dohvatiKategoriju(kategorijaID);
		String[] stavke = k.getDodatneStavke().split(";");
		dodatneStavke = new ArrayList<>();
		for (String stavka : stavke) {
			if (!stavka.isEmpty()) {
				dodatneStavke.add(new Pair<String, String>(stavka.split("\\|")[0], ""));
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(final String naslov) {
		this.naslov = naslov;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(final String opis) {
		this.opis = opis;
	}

	public String getCijena() {
		return cijena;
	}

	public void setCijena(final String cijena) {
		this.cijena = cijena;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(final String videoURL) {
		this.videoURL = videoURL;
	}

	public String getJeSkriven() {
		return jeSkriven;
	}

	public void setJeSkriven(final String jeSkriven) {
		this.jeSkriven = jeSkriven;
	}

	public List<Pair<String, String>> getDodatneStavke() {
		return dodatneStavke;
	}

	public void setDodatneStavke(final List<Pair<String, String>> dodatneStavke) {
		this.dodatneStavke = dodatneStavke;
	}

	public Long getKategorijaID() {
		return kategorijaID;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(final Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public List<String> getSlikeID() {
		return slikeID;
	}

	public void setSlikeID(final List<String> slikeID) {
		this.slikeID = slikeID;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(final String datum) {
		this.datum = datum;
	}



}
