package opp.parica.megafon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pravna_osoba")
public class PravnaOsoba extends Oglasivac {
	/** Naziv tvrtke. */
	private String naziv;
	/** Kontakt fax. */
	private String fax;

	/**
	 * Metoda koja dohvaca ime korisnika.
	 *
	 * @return the naslov
	 */
	@Column(length = 200, nullable = false)
	public final String getNaziv() {
		return naziv;
	}

	/**
	 * Metoda koja postavlja vrijednost naziva tvrtke.
	 *
	 * @param naziv
	 *            the naziv to set
	 */
	public final void setNaziv(final String naziv) {
		this.naziv = naziv;
	}

	/**
	 * Metoda koja dohvaca fax tvrtke.
	 *
	 * @return the fax
	 */
	@Column(length = 200, nullable = false)
	public final String getFax() {
		return fax;
	}

	/**
	 * Metoda koja postavlja vrijednost fax tvrtke.
	 *
	 * @param fax
	 *            the fax to set
	 */
	public final void setFax(final String fax) {
		this.fax = fax;
	}

	@Override
	public String toString() {
		return naziv + " \"" + getUsername() + "\"";
	}

	@Override
	public String dohvatiKorisnikInfo() {
		return String.format("%s '%s'", naziv, getUsername());
	}

}
