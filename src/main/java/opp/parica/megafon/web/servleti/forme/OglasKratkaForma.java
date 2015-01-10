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
	private static final int TITLE_LEN = 25;

	private static final int DESCRIPTION_LEN = 45;

	private String id;
	private String kategorija;

	private String naslov;
	private String opis;
	private String cijena;

	private String datum;

	private String slikaID;

	public OglasKratkaForma(final Oglas oglas) {
		id = oglas.getId().toString();
		kategorija = oglas.getPripadaKategoriji().getNaziv();

		naslov = oglas.getNaslov();
		opis = oglas.getOpis();
		cijena = oglas.getCijena().toString() + " HRK";
		datum = Potpora.formatirajDatum(oglas.getDatumObjave());

		slikaID = oglas.getSlike().get(0).getId().toString();

		trimParameters();
	}

	/**
	 * Metoda koja skracuje naslov i opis oglasa kako bi bio pogodan za kratki
	 * prikaz primjerice u rezultatima pretrage.
	 */
	private void trimParameters() {
		if (naslov.length() > TITLE_LEN) {
			naslov = naslov.subSequence(0, TITLE_LEN - 3) + "...";
		}
		naslov = naslov.replace('\n', ' ');

		if (opis.length() > DESCRIPTION_LEN) {
			opis = opis.subSequence(0, DESCRIPTION_LEN - 3) + "...";
		}
		opis = opis.replace('\n', ' ');
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

	static public List<OglasKratkaForma> prilagodiZaPrikaz(final List<Oglas> oglasi) {
		if (oglasi == null || oglasi.isEmpty()) {
			return null;
		}
		List<OglasKratkaForma> forme = new ArrayList<OglasKratkaForma>();
		for (Oglas o : oglasi) {
			forme.add(new OglasKratkaForma(o));
		}
		return forme;
	}

}
