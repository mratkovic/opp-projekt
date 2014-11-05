package opp.parica.megafon.hash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Klasa koja generira SHA-1 checksum navedenog teksta. Omogucava prikaz
 * dobivenog rezultata kao polje byte vrijednosti ili kao niz heksadecimalnih
 * znamenki, te takoder omogucuje usporedbu unesenog hash-a s generiranim
 * hash-om.
 *
 * @author Marko Ratkovic
 * @version 1.0
 */
public class SHA1 {
	/** Instanca klase {@link MessageDigest} koja odreduje hash vrijednost. */
	private MessageDigest msgDigest;
	/** Poruka ciji se hash racuna. */
	private String msg;
	/** Interno polje u koje se sprema izracunati hash. */
	private byte[] digest;

	/**
	 * Konstruktor koji prima kao jedini ulazni parametar putanju do datoteke
	 * ciji se hash
	 * provjerava.
	 *
	 * @param msg
	 *            poruka ciji se hash racuna
	 */
	public SHA1(final String msg) {
		if (msg == null) {
			throw new IllegalArgumentException("Argument can not be null");
		}

		this.msg = msg;
		try {
			msgDigest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			System.err.println("ALgorithm SHA-1 not found.");
		}
	}

	/**
	 * Metoda koja vraca izracunatu hash vrijednost poruke definirane prilikom
	 * instanciranja objekta.
	 *
	 * @return dobivena hash vrijednost
	 */
	public final byte[] getDigest() {
		if (digest == null) {
			calculateDigest();
		}
		return Arrays.copyOf(digest, digest.length);
	}

	/**
	 * Metoda koja vraca izracunatu hash vrijednost kao hiz heksadecimalnih
	 * znamenki.
	 *
	 * @return hash
	 */
	public final String getHexDigest() {
		return byteArrayToHex(getDigest());
	}

	/**
	 * Metoda koja usporeduje hash vrijednost dobivenu kao argument i izracunatu
	 * vrijednost.
	 *
	 * @param digest
	 *            hash vrijednost koja se usporeduje
	 * @return true ukoliko ulazna hash vrijednost odgovara generiranoj, false
	 *         uprotivnom
	 */
	public final boolean compareHexDigest(final String digest) {
		return byteArrayToHex(getDigest()).equals(digest);
	}

	/**
	 * Metoda koja polje byte-ova pretvara u niz heksadecimalnih znamenki.
	 *
	 * @param b
	 *            polje bytova koje se prbacuje u niz hex vrijednosti
	 * @return dobiveni string hex vrijednosti
	 */
	private static String byteArrayToHex(final byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			// ispisi i vodecu nulu ukoliko je broj manji od 0xF
			sb.append(String.format("%02x", b[i]));
		}
		return sb.toString();
	}

	/**
	 * Metoda koja cita poruku (definiranu pri instanciranju objekta klase
	 * {@link SHA1}) i odreduje njenu hash vrijednost.
	 */
	private void calculateDigest() {
		msgDigest.update(msg.getBytes(StandardCharsets.UTF_8));
		digest = msgDigest.digest();
	}
}
