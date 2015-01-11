package opp.parica.megafon.web.servleti.forme;

import java.util.ArrayList;
import java.util.List;

import opp.parica.megafon.model.Oglas;
import opp.parica.megafon.pomocno.Potpora;

/**
 * Razred koji se koristi za kratki prikaz oglasa prilikom rezultata pretrage.
 *
 * @author Marko Ratkovic
 * @version 1.0
 */
public class OglasKratkaForma {
	private int title_len;
	private int description_len;

	private String id;
	private String kategorija;

	private String naslov;
	private String opis;
	private String cijena;

	private String datum;

	private String slikaID;

	public OglasKratkaForma(final Oglas oglas, final int title, final int description) {
		id = oglas.getId().toString();
		kategorija = oglas.getPripadaKategoriji().getNaziv();

		naslov = oglas.getNaslov();
		opis = oglas.getOpis();
		cijena = oglas.getCijena().toString() + " HRK";
		datum = Potpora.kratkiFormatDatum(oglas.getDatumObjave());

		slikaID = oglas.getSlike().get(0).getId().toString();
		title_len = title;
		description_len = description;
		trimParameters();
	}

	/**
	 * Metoda koja skracuje naslov i opis oglasa kako bi bio pogodan za kratki
	 * prikaz primjerice u rezultatima pretrage.
	 */
	private void trimParameters() {
		if (naslov.length() > title_len) {
			naslov = naslov.subSequence(0, title_len - 3) + "...";
			naslov = naslov.replace('\n', ' ');
		}

		if (opis.length() > description_len) {
			opis = opis.subSequence(0, description_len - 3) + "...";
			opis = opis.replace('\n', ' ');
		}
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getKategorija() {
		return kategorija;
	}

	public void setKategorija(final String kategorija) {
		this.kategorija = kategorija;
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

	public String getDatum() {
		return datum;
	}

	public void setDatum(final String datum) {
		this.datum = datum;
	}

	public String getSlikaID() {
		return slikaID;
	}

	public void setSlikaID(final String slikaID) {
		this.slikaID = slikaID;
	}

	static public List<OglasKratkaForma> prilagodiZaPrikaz(final List<Oglas> oglasi, final int title,
		final int description) {
		if (oglasi == null || oglasi.isEmpty()) {
			return null;
		}
		List<OglasKratkaForma> forme = new ArrayList<OglasKratkaForma>();
		for (Oglas o : oglasi) {
			forme.add(new OglasKratkaForma(o, title, description));
		}
		return forme;
	}

}
