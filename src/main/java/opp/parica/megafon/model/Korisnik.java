package opp.parica.megafon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
@Entity
@Table(name = "korisnik")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Korisnik {

	/** Identifikator administratora. */
	private Long id;
	/** Korisnicko ime za prijavu */
	private String username;
	/** Hash lozinke. */
	private String passwordHash;

	/**
	 * Metoda koja dohvaca identifikator.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	 * Metoda koja dohvaca korisnicko ime.
	 *
	 * @return korisnicko ime
	 */
	@Column(length = 200, nullable = false)
	public String getUsername() {
		return username;
	}

	/**
	 * Metoda koja postavlja korisnicko ime.
	 *
	 * @param username
	 *            korisnicko ime
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * Metoda koja dohvaca hash zaporke.
	 *
	 * @return the passwordHash
	 */
	@Column(length = 80, nullable = false)
	public final String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * Metoda koja postavlja vrijednost hash zaporke korisnika.
	 *
	 * @param passwordHash
	 *            the passwordHash to set
	 */
	public final void setPasswordHash(final String passwordHash) {
		this.passwordHash = passwordHash;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", passwordHash=" + passwordHash + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Korisnik other = (Korisnik) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

	public abstract  String dohvatiKorisnikInfo();


}
