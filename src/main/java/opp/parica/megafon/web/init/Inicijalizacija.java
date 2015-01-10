package opp.parica.megafon.web.init;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import opp.parica.megafon.dao.jpa.JPAEMFProvider;

/**
 * Razred koji implementira sucelje {@link ServletContextListener}, te prilikom
 * pokretanja aplikacije se spaja na bazu podataka s pristupnim podacima
 * zadanim u persistence.xml datoteci.
 *
 * @author Marko Ratkovic
 * @version 1.0
 */

@WebListener
public class Inicijalizacija implements ServletContextListener {

	@Override
	public final void contextInitialized(final ServletContextEvent sce) {

		System.out.println("INIT start");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("baza.podataka");
		System.out.println("INIT: emf" + emf);
		sce.getServletContext().setAttribute("my.application.emf", emf);
		JPAEMFProvider.setEmf(emf);
	}

	@Override
	public final void contextDestroyed(final ServletContextEvent sce) {
		JPAEMFProvider.setEmf(null);
		EntityManagerFactory emf = (EntityManagerFactory) sce.getServletContext().getAttribute(
			"my.application.emf");
		if (emf != null) {
			emf.close();
		}
	}
}