package opp.parica.megafon.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Razred koji modelira korisnika portala - oglasivaca.
 *
 * @author Marko Ratkovic
 * @version 1.0
 */
@Entity
@Table(name = "oglasivac")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Oglasivac extends Korisnik{

	/** OIB oglasivaca. */
	private String oib;
	/** Kontakt mail korisika. */
	private String email;
	/** Tip racuna korisnika. */
	private TipClanstva tipClanstva;
	/** Lista svih oglasa nekog autora. */
	private List<Oglas> sviOglasi;
	/** Adresa oglasivaca. */
	private String adresa;

	/** Kontakt telefon*/
	private String telefon;
	/** Datum stvaranja racuna */
	private Date datumRegistracije;

	/** Datum isteka clanarine. */
	private Date datumIstekaClanarine;

	/**
	 * Metoda koja dohvaca tip racuna korisnika.
	 *
	 * @return {@link TipClanstva} koji odgovara tipu racuna korinika
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	public TipClanstva getTipClanstva() {
		return tipClanstva;
	}

	/**
	 * Metoda koja postavlja tip racuna.
	 *
	 * @param tipClanstva
	 *            tip racuna
	 */
	public void setTipClanstva(final TipClanstva tipClanstva) {
		this.tipClanstva = tipClanstva;
	}



	/**
	 * Metoda koja dohvaca oib.
	 *
	 * @return oib
	 */
	@Column(length = 11, nullable = false)
	public String getOib() {
		return oib;
	}

	/**
	 * Metoda koja postavlja vrijednost oib-a.
	 *
	 * @param oib
	 *            oib oglasivaca
	 */
	public void setOib(final String oib) {
		this.oib = oib;
	}

	/**
	 * Metoda koja dohvaca email korisnika.
	 *
	 * @return the email
	 */
	@Column(length = 200, nullable = false)
	public final String getEmail() {
		return email;
	}

	/**
	 * Metoda koja postavlja vrijednost email-a korisnika.
	 *
	 * @param email
	 *            the email to set
	 */
	public final void setEmail(final String email) {
		this.email = email;
	}


	public String informacijeOOglasivacu() {
		return "";
	}
	/**
	 * Metoda koja dohvaca sve oglase nekog autora.
	 *
	 * @return lista oglasa
	 */
	@OneToMany(mappedBy = "autor", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	public List<Oglas> getSviOglasi() {
		return sviOglasi;
	}

	/**
	 * Metoda koja postavlja listu oglasa.
	 *
	 * @param sviOglasi
	 *            lista oglasa
	 */
	public void setSviOglasi(final List<Oglas> sviOglasi) {
		this.sviOglasi = sviOglasi;
	}

	/**
	 * Metoda koja dohvaca adresu korisnika.
	 *
	 * @return the adresa
	 */
	@Column(length = 200, nullable = false)
	public String getAdresa() {
		return adresa;
	}

	/**
	 * Metoda koja postavlja vrijednost adrese korisnika.
	 *
	 * @param adresa
	 *            the adresa to set
	 */
	public void setAdresa(final String adresa) {
		this.adresa = adresa;
	}

	/**
	 * Metoda koja dohvaca datum registracije korisnika.
	 *
	 * @return datum registracije
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	public Date getDatumRegistracije() {
		return datumRegistracije;
	}

	public void setDatumRegistracije(final Date datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	public Date getDatumIstekaClanarine() {
		return datumIstekaClanarine;
	}

	public void setDatumIstekaClanarine(final Date datumIstekaClanarine) {
		this.datumIstekaClanarine = datumIstekaClanarine;
	}
	@Column(length = 200, nullable = false)
	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(final String telefon) {
		this.telefon = telefon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Oglasivac other = (Oglasivac) obj;
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		return true;
	}


}
