package opp.parica.megafon.dao.jpa;


import javax.persistence.EntityManager;

import opp.parica.megafon.dao.DAOException;

/**
 * Pohrana veza prema bazi podataka u ThreadLocal object. ThreadLocal je zapravo
 * mapa čiji su ključevi identifikator dretve koji radi operaciju nad mapom.
 *
 * @author marcupic
 */
public class JPAEMProvider {

	private static ThreadLocal<LocalData> locals = new ThreadLocal<>();

	public static EntityManager getEntityManager() {
		LocalData ldata = locals.get();
		if (ldata == null) {
			ldata = new LocalData();
			ldata.em = JPAEMFProvider.getEmf().createEntityManager();
			ldata.em.getTransaction().begin();
			locals.set(ldata);
		}
		return ldata.em;
	}

	public static void close() {
		LocalData ldata = locals.get();
		if (ldata == null) {
			return;
		}
		DAOException dex = null;
		try {
			ldata.em.getTransaction().commit();
		} catch (Exception ex) {
			dex = new DAOException("Unable to commit transaction.", ex);
		}
		try {
			ldata.em.close();
		} catch (Exception ex) {
			if (dex != null) {
				dex = new DAOException("Unable to close entity manager.", ex);
			}
		}
		locals.remove();
		if (dex != null) {
			throw dex;
		}
	}

	private static class LocalData {
		EntityManager em;
	}

	/**
	 * Privatni konstruktor.
	 */
	private JPAEMProvider() {
	}

}
