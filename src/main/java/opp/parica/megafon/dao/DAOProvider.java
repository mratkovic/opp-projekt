package opp.parica.megafon.dao;

import opp.parica.megafon.dao.jpa.JPADAOImpl;

/**
 * Singleton razred koji zna koga treba vratiti kao pružatelja
 * usluge pristupa podsustavu za perzistenciju podataka.
 * Uočite da, iako je odluka ovdje hardkodirana, naziv
 * razreda koji se stvara mogli smo dinamički pročitati iz
 * konfiguracijske datoteke i dinamički učitati -- time bismo
 * implementacije mogli mijenjati bez ikakvog ponovnog kompajliranja
 * koda.
 *
 * @author Marko Ratkovic
 * @version 1.0
 */
public final class DAOProvider {
	/**
	 * Jedina instanca razreda koji je pruzateljusluge pristupa podsustavu za
	 * perzistenciju podataka.
	 */
	private static DAO dao = new JPADAOImpl();

	/**
	 * Dohvat primjerka.
	 *
	 * @return objekt koji enkapsulira pristup sloju za perzistenciju podataka.
	 */
	public static DAO getDAO() {
		return dao;
	}

	/**
	 * Privatni konstruktor.
	 */
	private DAOProvider() {

	}

}
