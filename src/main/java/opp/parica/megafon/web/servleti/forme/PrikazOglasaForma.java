package opp.parica.megafon.web.servleti.forme;

import java.util.ArrayList;
import java.util.List;

import opp.parica.megafon.model.Kategorija;
import opp.parica.megafon.model.Oglas;
import opp.parica.megafon.pomocno.Potpora;

public class PrikazOglasaForma {

	private Long id;
	private Kategorija kategorija;

	private String naslov;
	private String opis;
	private String cijena;

	private String slikaId;
	private String datumObjave;

	public PrikazOglasaForma(final Oglas oglas) {
		id = oglas.getId();
		kategorija = oglas.getPripadaKategoriji();

		naslov = oglas.getNaslov();
		opis = oglas.getOpis();
		if (opis.length() > 100) {
			opis = opis.substring(0, 97) + "...";
		}
		cijena = String.format("%.2f", oglas.getCijena());
		slikaId = oglas.getSlike().isEmpty() ?
			oglas.getSlike().get(0).getId().toString() : "-1";
		datumObjave = Potpora.formatirajDatum(oglas.getDatumObjave());

	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(final Kategorija kategorija) {
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

	public String getSlikaId() {
		return slikaId;
	}

	public void setSlikaId(final String slikaId) {
		this.slikaId = slikaId;
	}

	public String getDatumObjave() {
		return datumObjave;
	}

	public void setDatumObjave(final String datumObjave) {
		this.datumObjave = datumObjave;
	}

	static List<PrikazOglasaForma> stvoriListu(final List<Oglas> oglasi) {

		List<PrikazOglasaForma> lista = new ArrayList<PrikazOglasaForma>();
		if (oglasi == null || oglasi.isEmpty()) {
			return lista;
		}

		for(Oglas oglas : oglasi)  {
			lista.add(new PrikazOglasaForma(oglas));
		}
		return null;
	}
}
