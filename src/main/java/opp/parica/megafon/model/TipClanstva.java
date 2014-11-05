package opp.parica.megafon.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Razred koji modelira korisnika portala - oglasivaca.
 *
 * @author Marko Ratkovic
 * @version 1.0
 */
@Entity
@Table(name = "tip_clanstva")
public class TipClanstva {
	/** Identifikator korisnika. */
	private Long id;;
	/** Naziv tipa racuna. */
	private String naziv;
	/** Iznos mjesecne clanarine. */
	private Double clanarina;
	/** Lista korisnika tog tipa koji imaju modelirani tip clanarine. */
	private List<Oglasivac> oglasavaci;

	/**
	 * Metoda koja dohvaca identifikator.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue
	public final Long getId() {
		return id;
	}

	/**
	 * Metoda koja postavlja vrijednost identifikatora.
	 *
	 * @param id
	 *            the id to set
	 */
	public final void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Metoda koja dohvaca naziv tipa racuna.
	 *
	 * @return naziv
	 */
	@Column(length = 200, nullable = false)
	public String getNaziv() {
		return naziv;
	}

	/**
	 * Metoda koja postavlja naziv tipa racuna.
	 *
	 * @param naziv
	 *            naziv tipa racuna
	 */
	public void setNaziv(final String naziv) {
		this.naziv = naziv;
	}

	/**
	 * Metoda koja dohvaca iznos clanarine.
	 *
	 * @return iznos clanarine
	 */
	public Double getClanarina() {
		return clanarina;
	}

	/**
	 * Metoda koja postavlja iznos clanarine u formatu 'iznos valuta'.
	 *
	 * @param clanarina
	 *            iznos clanarine
	 */
	@Column(nullable = false)
	public void setClanarina(final Double clanarina) {
		this.clanarina = clanarina;
	}


	@Override
	public String toString() {
		return "TipClanstva [id=" + id + ", naziv=" + naziv + ", clanarina=" + clanarina + "]";
	}

	/**
	 * Metoda koja dohvaca sve oglasavace ovog tipa.
	 *
	 * @return lista oglasivaca
	 */
	@OneToMany(mappedBy = "tipClanstva", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	public List<Oglasivac> getOglasavaci() {
		return oglasavaci;
	}

	/**
	 * Metoda koja postavlja listu oglasivaca.
	 *
	 * @param oglasavaci
	 */
	public void setOglasavaci(final List<Oglasivac> oglasavaci) {
		this.oglasavaci = oglasavaci;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		TipClanstva other = (TipClanstva) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}



}
