package opp.parica.megafon.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "kategorija")
public class Kategorija {




	private Long id;
	private String naziv;
	private String dodatneStavke;
	private List<Kategorija> podkategorije;
	private Kategorija nadkategorija;
	private List<Oglas> oglasi;
	private Boolean jeBesplatna;

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

	@Column(length = 600)
	public String getDodatneStavke() {
		return dodatneStavke;
	}

	public void setDodatneStavke(final String dodatneStavke) {
		this.dodatneStavke = dodatneStavke;
	}

	@ManyToOne
	@JoinColumn(nullable = true)
	public Kategorija getNadkategorija() {
		return nadkategorija;
	}

	public void setNadkategorija(final Kategorija nadkategorija) {
		this.nadkategorija = nadkategorija;
	}

	@OneToMany(mappedBy = "nadkategorija", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	public List<Kategorija> getPodkategorije() {
		return podkategorije;
	}

	public void setPodkategorije(final List<Kategorija> podkategorije) {
		this.podkategorije = podkategorije;
	}
	@OneToMany(mappedBy = "pripadaKategoriji", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	public List<Oglas> getOglasi() {
		return oglasi;
	}

	public void setOglasi(final List<Oglas> oglasi) {
		this.oglasi = oglasi;
	}
	@Column(name = "jeSkriven")
	public Boolean getJeBesplatna() {
		return jeBesplatna;
	}

	public void setJeBesplatna(final Boolean jeBesplatna) {
		this.jeBesplatna = jeBesplatna;
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
		Kategorija other = (Kategorija) obj;
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
		return "Kategorija [id=" + id + ", naziv=" + naziv + ", dodatneStavke=" + dodatneStavke
			+ ", podkategorije=" + podkategorije + ", nadkategorija=" + nadkategorija + ", oglasi=" + oglasi
			+ ", jeBesplatna=" + jeBesplatna + "]";
	}





}
