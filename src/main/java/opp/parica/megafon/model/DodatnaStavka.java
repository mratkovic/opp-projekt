package opp.parica.megafon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dodatna_stavka")
public class DodatnaStavka {
	/** Identifikator stavke. */
	private Long id;

	private String naziv;
	private String vrijednost;
	private Oglas oglasVlasnik;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(final Long id) {
		this.id = id;
	}
	@Column(length = 200, nullable = false)
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(final String naziv) {
		this.naziv = naziv;
	}
	@Column(length = 600, nullable = false)
	public String getVrijednost() {
		return vrijednost;
	}
	public void setVrijednost(final String vrijednost) {
		this.vrijednost = vrijednost;
	}
	@ManyToOne
	@JoinColumn(nullable = false)
	public Oglas getOglasVlasnik() {
		return oglasVlasnik;
	}
	public void setOglasVlasnik(final Oglas oglasVlasnik) {
		this.oglasVlasnik = oglasVlasnik;
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
		DodatnaStavka other = (DodatnaStavka) obj;
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
