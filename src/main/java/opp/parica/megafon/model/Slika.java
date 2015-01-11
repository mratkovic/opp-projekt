package opp.parica.megafon.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "slika")
public class Slika {
	private Long id;
	private byte[] slika;
	private Oglas pripadaOglasu;
	private String ekstenzija;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	public byte[] getSlika() {
		return slika;
	}

	public void setSlika(final byte[] slika) {
		this.slika = slika;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Oglas getPripadaOglasu() {
		return pripadaOglasu;
	}

	public void setPripadaOglasu(final Oglas pripadaOglasu) {
		this.pripadaOglasu = pripadaOglasu;
	}

	@Column(length = 200)
	public String getEkstenzija() {
		return this.ekstenzija;
	}

	public void setEkstenzija(final String ekstenzija) {
		this.ekstenzija = ekstenzija;
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
		Slika other = (Slika) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Slika [id=" + id + ", ekstenzija=" + ekstenzija + "]";
	}



}
