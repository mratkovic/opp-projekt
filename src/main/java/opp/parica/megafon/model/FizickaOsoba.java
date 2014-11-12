package opp.parica.megafon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fizicka_osoba")
public class FizickaOsoba extends Oglasivac {
	/** Ime korisnika. */
	private String ime;
	/** Prezime korisnika. */
	private String prezime;

	/**
	 * Metoda koja dohvaca ime korisnika.
	 *
	 * @return the ime
	 */
	@Column(length = 200, nullable = false)
	public final String getIme() {
		return ime;
	}

	/**
	 * Metoda koja postavlja vrijednost imena korisnika.
	 *
	 * @param ime
	 *            the ime to set
	 */
	public final void setIme(final String ime) {
		this.ime = ime;
	}

	/**
	 * Metoda koja dohvaca prezime korisnika.
	 *
	 * @return the prezime
	 */
	@Column(length = 200, nullable = false)
	public final String getPrezime() {
		return prezime;
	}

	/**
	 * Metoda koja postavlja vrijednost prezimena korisnika.
	 *
	 * @param prezime
	 *            the prezime to set
	 */
	public final void setPrezime(final String prezime) {
		this.prezime = prezime;
	}



	@Override
	public String toString() {
		return ime + " \"" + getUsername() + "\" " + prezime;
	}

	@Override
	public String dohvatiKorisnikInfo() {
		return String.format("%s '%s' %s", ime, getUsername(), prezime);
	}




}
