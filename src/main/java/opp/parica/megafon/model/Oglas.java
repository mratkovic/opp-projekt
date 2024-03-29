package opp.parica.megafon.model;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "oglas")
public class Oglas {

	private Long id;
	private Oglasivac autor;
	private Kategorija pripadaKategoriji;
	private String naslov;
	private String opis;
	private Float cijena;
	private String videoURL;
	private Boolean jeSkriven;
	private List<Slika> slike = new ArrayList<>();;
	private Date datumObjave;
	private List<DodatnaStavka> stavke = new ArrayList<>();

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Oglasivac getAutor() {
		return autor;
	}

	public void setAutor(final Oglasivac autor) {
		this.autor = autor;
	}

	@Column(length = 200, nullable = false)
	public String getNaslov() {
		return naslov;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Kategorija getPripadaKategoriji() {
		return pripadaKategoriji;
	}

	public void setPripadaKategoriji(final Kategorija pripadaKategoriji) {
		this.pripadaKategoriji = pripadaKategoriji;
	}

	public void setNaslov(final String naslov) {
		this.naslov = naslov;
	}

	@Column(length = 600, nullable = false)
	public String getOpis() {
		return opis;
	}

	public void setOpis(final String opis) {
		this.opis = opis;
	}

	@Column(name = "cijena")
	public Float getCijena() {
		return cijena;
	}

	public void setCijena(final Float cijena) {
		this.cijena = cijena;
	}

	@Column(length = 400, nullable = false)
	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(final String videoURL) {
		this.videoURL = videoURL;
	}

	@Column(name = "jeSkriven")
	public Boolean getJeSkriven() {
		return jeSkriven;
	}

	public void setJeSkriven(final Boolean jeSkriven) {
		this.jeSkriven = jeSkriven;
	}

	@OneToMany(mappedBy = "pripadaOglasu", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	public List<Slika> getSlike() {
		return slike;
	}

	public void setSlike(final List<Slika> slike) {
		this.slike = slike;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	public Date getDatumObjave() {
		return datumObjave;
	}

	public void setDatumObjave(final Date datumObjave) {
		this.datumObjave = datumObjave;
	}

	@OneToMany(mappedBy = "oglasVlasnik", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	public List<DodatnaStavka> getStavke() {
		return stavke;
	}

	public void setStavke(final List<DodatnaStavka> stavke) {
		this.stavke = stavke;
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
		Oglas other = (Oglas) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public long prvaSlikaID() {
		if (slike.isEmpty()) {
			return -1;
		} else {
			return slike.get(0).getId();
		}
	}

	@Override
	public String toString() {
		return "Oglas [id=" + id + ", autor=" + autor + ", pripadaKategoriji=" + pripadaKategoriji + ", naslov="
			+ naslov + ", opis=" + opis + ", cijena=" + cijena + ", videoURL=" + videoURL + ", jeSkriven="
			+ jeSkriven + ", slike=" + slike + ", datumObjave=" + datumObjave + ", stavke=" + stavke + "]";
	}

}
