package opp.parica.megafon.dao.jpa;

import javax.persistence.EntityManagerFactory;

public class JPAEMFProvider {

	static EntityManagerFactory emf;

	public static EntityManagerFactory getEmf() {
		return emf;
	}

	public static void setEmf(final EntityManagerFactory emf) {
		JPAEMFProvider.emf = emf;
	}
}
